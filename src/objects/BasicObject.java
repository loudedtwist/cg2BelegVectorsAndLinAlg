package objects;

import alg.Vektor2D;
import behaviors.Behavior;

import java.util.ArrayList;

abstract public class BasicObject {

    protected Vektor2D pos;
    protected ArrayList<Behavior> behaviors;

    public Vektor2D getPos() {
        return pos;
    }

    public double getXPos() {
        return pos.getX();
    }

    public double getYPos() {
        return pos.getY();
    }

    public void setPos(Vektor2D pos) {
        this.pos = pos;
    }

    public void setXPos(double xPos) {
        pos.setX(xPos);
    }

    public void setYPos(double yPos) {
        pos.setY(yPos);
    }

    public abstract void render();

    public BasicObject() {
        this(0, 0);
    }

    public BasicObject(double xPos, double yPos) {
        pos = new Vektor2D(xPos,yPos);
        this.behaviors = new ArrayList<>(5);
    }

    public void callBehavior() {
        for (Behavior behavior : behaviors) {
            behavior.updateState();
        }
    }

    public void addBehavior(Behavior behavior) {
        behaviors.add(behavior);
    }

    public boolean removeBehavior(Behavior behavior) {
        final int NO_ELEMENT_FOUND = -1;

        int indexOfElement = behaviors.indexOf(behavior);
        if (indexOfElement == NO_ELEMENT_FOUND) return false;
        behaviors.remove(indexOfElement);
        return true;
    }
}
