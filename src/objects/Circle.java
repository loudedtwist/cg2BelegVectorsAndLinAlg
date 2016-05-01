package objects;

import behaviors.FlummiBehavior;
import behaviors.WindBehavior;
import org.lwjgl.opengl.GL11;

public class Circle extends MovingObject {
    private float radius, r, g, b;
    static int a = 0;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Circle(float xPos, float yPos, float speed, float radius, float r, float g, float b) {
        super(xPos, yPos, speed);
        this.radius = radius;
        this.r = r;
        this.g = g;
        this.b = b;
        super.addBehavior(new FlummiBehavior(this));
        super.addBehavior(new WindBehavior(this));
    }

    public Circle(float speed, float radius, float r, float g, float b) {
        this(0, 0, speed, radius, r, g, b);
    }

    @Override
    public void render() {
        System.out.println(this.toString());
        GL11.glColor3f(r,g,b);
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(getXPos(),getYPos());
            GL11.glVertex2f(getXPos()+getRadius(),getYPos());
            GL11.glVertex2f(getXPos()+getRadius(),getYPos()+getRadius());
            GL11.glVertex2f(getXPos(),getYPos()+getRadius());
        GL11.glEnd();
    }

    public Circle() {
        this(0, 0, 0, 5, 0, 0, 0);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", r=" + r +
                ", g=" + g +
                ", b=" + b +
                "} " + super.toString();
    }
}
