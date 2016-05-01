package framentLoops;

import objects.Circle;
import objects.MovingObject;

import static org.lwjgl.opengl.GL11.glClearColor;

public class MyFragmentLoop implements FragmentLoop {
    MovingObject movObject;

    public MyFragmentLoop() {
        this.movObject = new Circle(1.5f,5,0,0,0);
    }

    static float color = 0.3f;
    @Override
    public void loop() {
        movObject.update();
        movObject.render();
        glClearColor((color+=0.01)%1, 0.3f, 0.3f, 0.3f);
    }
}
