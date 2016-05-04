package framentLoops;

import alg.Vektor2D;
import behaviors.SwarmBehavior;
import behaviors.VelocityBasedMovementBehavior;
import behaviors.WallAvoidanceBehavior;
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

public class SwarmLayerLoop implements LayerLoop {
    ArrayList<MovingGroup> swarms;
    BasisWindow window;

    public SwarmLayerLoop(@NotNull final BasisWindow window) {
        this.window = window;
        swarms = new ArrayList<>();
    }

    @Override
    public void init() {
        setOnMouseMoveLButtonClicked();
        createSwarmAgents();
    }

    private void setOnMouseMoveLButtonClicked() {
        GLFWCursorPosCallback cursorPosCallback;
        System.out.println(window.getWindowHandle());
        glfwSetCursorPosCallback(window.getWindowHandle(), cursorPosCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                ypos = MyWindow.WINDOW_HEIGHT - ypos ;
                int state = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT);
                if (state == GLFW_PRESS) {
                    for (MovingObject mo : swarms.get(0).getSwarmAgents()) {
                        //mo.setPos(new Vektor2D(xpos,ypos));
                        //System.out.println("X: " + xpos + "  Y: " + ypos);
                        //System.out.println("MOX: " + mo.getXPos() + "  MOY: " + mo.getYPos());
                        if (
                                (xpos < mo.getXPos() + 30 && xpos > mo.getXPos() - 30) &&
                                (ypos < mo.getYPos() + 30 && ypos > mo.getYPos() - 30)
                                ) {
                            System.out.println(true);
                            mo.getAcceleration().add(new Vektor2D(xpos,ypos).mult(100));
                            //mo.setPos(new Vektor2D(xpos,ypos));
                        }
                    }
                }
            }
        });
        window.setCursorPosCallback(cursorPosCallback);
    }

    private void createSwarmAgents() {
        Swarm swarm = new Swarm();
        swarm.addBehavior(new SwarmBehavior(swarm));
        Random rand = new Random();
        for (int i = 0; i < 400; i++) {
            Circle circle = new Circle(
                    new Vektor2D((0 + i * (5 + i * rand.nextFloat())) % 640, (0 + i * (i + 5 * rand.nextFloat())) % 480),
                    (25) * rand.nextFloat(),
                    2 * rand.nextFloat() + 0.5f,
                    new Color(rand.nextFloat() % 1f, rand.nextFloat(), 0.7f, 1)
            );
            circle.addBehavior(new VelocityBasedMovementBehavior(circle));
            circle.addBehavior(new WallAvoidanceBehavior(circle));
            swarm.addAgent(circle);
        }
        addObjectToScene(swarm);
    }

    public void addObjectToScene(MovingGroup swarm) {
        swarms.add(swarm);
    }

    @Override
    public void loop() {
        for (MovingGroup swarm : swarms) {
            swarm.callBehavior();
            swarm.getSwarmAgents().forEach(MovingObject::callBehavior);
            swarm.render();
        }
    }

    @Override
    public void setWindowObject(BasisWindow window) {
        this.window = window;
    }
}
