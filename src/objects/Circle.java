package objects;

import alg.LinAlg;
import alg.Vektor2D;
import com.sun.prism.paint.Color;
import lwjgl.MyWindow;

import static org.lwjgl.opengl.GL11.*;

public class Circle extends MovingObject {
    private float radius;
    private float r, g, b , a;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Circle(Vektor2D pos, float radius, float speed, Color color) {
        super(pos, speed);
        this.radius = radius;
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.a = color.getAlpha();
    }

    public Circle(float radius, float speed, Color color) {
        this(new Vektor2D(0, 0), radius, speed, color);
    }

    public Circle() {
        this(new Vektor2D(0, 0), 5, 0, new Color(0,0,0,0));
    }

    @Override
    public void render() {
        //drawSquer();
        drawCircle();
        //drawCircleShader();
    }

    private void drawCircle() {
        int triangleAmount = 3;// <=20
        double twicePi = 2.0f * Math.PI;
        glColor3f(r, g, b);
        glBegin(GL_TRIANGLE_FAN);
        glVertex2d(getXPos(), getYPos()); // center of circle
        for (int i = 0; i <= triangleAmount; i++) {
            glVertex2d(
                    getXPos() + (radius * Math.cos(i * twicePi / triangleAmount)),
                    getYPos() + (radius * Math.sin(i * twicePi / triangleAmount))
            );
        }
        glEnd();
        glColor3f(1, 0, 0);
        glBegin(GL_LINE_STRIP);
        Vektor2D lineEnd = (Vektor2D) LinAlg.add(getPos(), LinAlg.mult(getVelocity(), 30));
        glVertex2d(getXPos(), getYPos());
        glVertex2d(lineEnd.getX(), lineEnd.getY());
        glEnd();
    }

    private void drawCircleShader() {
        int triangleAmount = 15;// <=20
        double twicePi = 2.0f * Math.PI;
        glColor3f(r, g, b);
        glBegin(GL_TRIANGLE_STRIP);
        for (int i = 0; i <= triangleAmount; i++) {
            glVertex2d(
                    pxToOpenGLCoordX(getXPos() + (radius * Math.cos(i * twicePi / triangleAmount))),
                    pxToOpenGLCoordY(getYPos() + (radius * Math.sin(i * twicePi / triangleAmount)))
            );
        }
        glEnd();
        glColor3f(1, 0, 0);
        glBegin(GL_LINE_STRIP);
        Vektor2D lineEnd = (Vektor2D) LinAlg.add(getPos(), LinAlg.mult(getVelocity(), 30));
        glVertex2d(pxToOpenGLCoordX(getXPos()), pxToOpenGLCoordY(getYPos()));
        glVertex2d(pxToOpenGLCoordX(lineEnd.getX()), pxToOpenGLCoordY(lineEnd.getY()));
        glEnd();
    }

    private double pxToOpenGLCoordY(double y) {
        double v2 = 2.0 / (double) MyWindow.WINDOW_HEIGHT;
        return v2 * y - 1.0;
    }

    private double pxToOpenGLCoordX(double x) {
        double v1 = 2.0 / (double) MyWindow.WINDOW_WIDTH;
        return v1 * x - 1.0;
    }

    private void drawSquer() {
        glColor3f(r, g, b);
        glBegin(GL_QUADS);
        glVertex2d(getXPos(), getYPos());
        glVertex2d(getXPos() + getRadius(), getYPos());
        glVertex2d(getXPos() + getRadius(), getYPos() + getRadius());
        glVertex2d(getXPos(), getYPos() + getRadius());
        glEnd();
    }

    @Override
    public String toString() {
        return getPos().toString();
    }

}
