package objects;

import behaviors.Behavior;

import java.util.ArrayList;

abstract public class BasicObject {

    protected float xPos;
    protected float yPos;
    protected ArrayList<Behavior> behaviors;

    public float getXPos() {
        return xPos;
    }

    public void setXPos(float xPos) {
        this.xPos = xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public void setYPos(float yPos) {
        this.yPos = yPos;
    }

    public abstract void render();

    @Override
    public String toString() {
        return "BasicObject{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                '}';
    }

    public BasicObject() {
        this(0, 0);
    }

    public BasicObject(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.behaviors = new ArrayList<>(5);
    }

    public void update(){
        for (Behavior behavior : behaviors) {
            behavior.updateState();
        }
    }
    public void addBehavior(Behavior behavior){
        behaviors.add(behavior);
    }

    public boolean removeBehavior(Behavior behavior){
        final int NO_ELEMENT_FOUND = -1;

        int indexOfElement = behaviors.indexOf(behavior);
        if(indexOfElement == NO_ELEMENT_FOUND)
            return false;
        behaviors.remove(indexOfElement);
        return true;
    }
}
