package objects;

abstract public class MovingObject extends BasicObject {
    protected float speed;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public MovingObject() {
        super();
        this.speed = 0;
    }

    public MovingObject(float speed) {
        this.speed = speed;
    }

    public MovingObject(float xPos, float yPos, float speed) {
        super(xPos, yPos);
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "MovingObject{" +
                "speed=" + speed +
                "} " + super.toString();
    }
}
