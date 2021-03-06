package renderingLayers;

import alg.Vektor2D;
import behaviors.*;
import com.sun.istack.internal.NotNull;
import com.sun.prism.paint.Color;
import lwjgl.BasisWindow;
import lwjgl.MyWindow;
import objects.Circle;
import objects.MovingGroup;
import objects.MovingObject;
import objects.Swarm;
import org.lwjgl.glfw.GLFWCursorPosCallback;

import java.util.ArrayList;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;

public class SwarmLayer implements ILoop {
    public static final int MOUSE_AREA = 60;
    BasisWindow window;
    ArrayList<MovingGroup> swarms;
    MouseListenerBehavior leftButtonClickedMoving;
    MouseListenerBehavior rightButtonClickedMoving;

    public ArrayList<MovingGroup> getSwarms() {
        return swarms;
    }

    public SwarmLayer(@NotNull final BasisWindow window) {
        super();
        swarms = new ArrayList<>();
        this.window = window;

    }

    public void addObjectToScene(MovingGroup swarm) {
        swarms.add(swarm);
    }

    @Override
    public void init() {
        leftButtonClickedMoving = new EscapeTheMouse(this);
        rightButtonClickedMoving = new AlignToTheMouseBehavior(this);

        setOnMouseMoveLButtonClicked();
        createSwarmAgents();
    }

    private void createSwarmAgents() {
        Random rand = new Random();

        Swarm swarm = new Swarm();
        swarm.addBehavior(new SwarmFunctionalBehavior(swarm));
        for (int i = 0; i < 500; i++) {
            Circle circle = new Circle(
                    new Vektor2D(
                            (0 + i * (5 + i * rand.nextFloat())) % 640,
                            (0 + i * (i + 5 * rand.nextFloat())) % 480),
                    rand.nextFloat(),
                    (35) * rand.nextFloat() + 5,
                    3 * rand.nextFloat() + 0.5f,
                    new Color(rand.nextFloat() % 1f, rand.nextFloat(), 0.7f, 1));
            circle.addBehavior(new VelocityBasedMovementBehavior(circle));
            circle.addBehavior(new WallAvoidanceBehavior(circle));
            swarm.addAgent(circle);
        }
        addObjectToScene(swarm);
    }

    @Override
    public void loop() {
        for (MovingGroup swarm : swarms) {
            swarm.getSwarmAgents().forEach(MovingObject::executeBehavior);
            swarm.executeBehavior();
            swarm.render();
        }

    }

    @Override
    public void setWindowObject(BasisWindow window) {
        this.window = window;
    }

    private void setOnMouseMoveLButtonClicked() {
        GLFWCursorPosCallback cursorPosCallback;
        glfwSetCursorPosCallback(window.getWindowHandle(), cursorPosCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                ypos = MyWindow.WINDOW_HEIGHT - ypos ;

                int leftButton = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT);
                if (leftButton == GLFW_PRESS) {
                    if (leftButtonClickedMoving != null) {
                        leftButtonClickedMoving.mouseMovingWithButtonPressed(new Vektor2D(xpos,ypos));
                    }
                }
                int rightButton = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_RIGHT);
                if (rightButton == GLFW_PRESS) {
                    if (leftButtonClickedMoving != null) {
                        rightButtonClickedMoving.mouseMovingWithButtonPressed(new Vektor2D(xpos,ypos));
                    }
                }
            }
        });
        window.setCursorPosCallback(cursorPosCallback);
    }
}
