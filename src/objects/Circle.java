package objects;

import alg.LinAlg;
import alg.Vektor;
import alg.Vektor2D;
import com.sun.prism.paint.Color;
import lwjgl.MyWindow;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class Circle extends MovingObject {
    private float radius;
    private float r, g, b , a;

    public double getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Circle(Vektor2D pos, double zPos, float radius, float speed, Color color) {
        super(pos, speed);
        this.setZPos(zPos);
        this.radius = radius;
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.a = color.getAlpha();
    }

    public Circle(float radius, float speed, Color color) {
        this(new Vektor2D(0, 0), 0, radius, speed, color);
    }

    public Circle() {
        this(new Vektor2D(0, 0), 0, 5, 0, new Color(0,0,0,0));
    }

    @Override
    public void render() {
        drawTriangle();
        drawForcec();

        //drawSquer();
        //drawCircle();
        //drawCircleShader();
    }

    private void drawCircle() {
        int triangleAmount = 3;// <=20
        double twicePi = 2.0f * Math.PI;
        glColor3f(r, g, b);
        glBegin(GL_TRIANGLE_FAN);
        double z = new Random().nextFloat();
        glVertex3d(getXPos(), getYPos(),z); // center of circle
        for (int i = 0; i <= triangleAmount; i++) {
            glVertex3d(
                    getXPos() + (radius * Math.cos(i * twicePi / triangleAmount)),
                    getYPos() + (radius * Math.sin(i * twicePi / triangleAmount)),
                    -z
            );
        }
        glEnd();

        drawForcec();
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

    private void drawSquer() {
        glColor3f(r, g, b);
        glBegin(GL_QUADS);
        glVertex2d(getXPos(), getYPos());
        glVertex2d(getXPos() + getRadius(), getYPos());
        glVertex2d(getXPos() + getRadius(), getYPos() + getRadius());
        glVertex2d(getXPos(), getYPos() + getRadius());
        glEnd();
    }

    private void drawTriangle() {

        double z = new Random().nextFloat();
        glColor3f(r, g, b);
        glBegin(GL_TRIANGLE_STRIP);
        glVertex3d(getXPos(), getYPos(), -getZPos() -  getXPos()/MyWindow.WINDOW_WIDTH);

        glColor3f(b, r, b);
        glVertex3d(getXPos() + Vektor.divTwoDoubles(getRadius(),2), getYPos() +getRadius() , getZPos());

        glColor3f(r, b, g);
        glVertex3d(getXPos() + getRadius(), getYPos() ,-getZPos() - getYPos()/MyWindow.WINDOW_HEIGHT);
        glEnd();

    }
    private void drawForcec() {
        drawVelocity();
        //drawAcceleration();
    }

    private void drawVelocity() {
        glColor3f(0.6f, 0.6f, 0.6f);
        glBegin(GL_LINE_STRIP);
        Vektor2D lineEnd = (Vektor2D) LinAlg.add(getPos(), LinAlg.mult(getVelocity(), 30));
        glVertex2d(getXPos(), getYPos());
        glVertex2d(lineEnd.getX(), lineEnd.getY());
        glEnd();
    }

    private void drawAcceleration() {
        glColor3f(0, 1, 1);
        glBegin(GL_LINE_STRIP);
        Vektor2D lineAcs = (Vektor2D) LinAlg.add(getPos(), LinAlg.mult(getAcceleration(), 600));
        glVertex2d(getXPos(), getYPos());
        glVertex2d(lineAcs.getX(), lineAcs.getY());
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

    @Override
    public String toString() {
        return getPos().toString();
    }

}
