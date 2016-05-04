package behaviors;

import objects.MovingObject;

public class GravityBehavior implements Behavior {
    MovingObject obj;
    float gravity = 0.001f;

    public GravityBehavior(MovingObject obj) {
        this.obj = obj;
    }

    @Override
    public void updateState() {
        double offset = obj.getYPos() * gravity;
        /*obj.getAcceleration().add((new Vektor2D(0, 1).mult(1))).limit(obj.getMaxSpeed());

        obj.setMaxSpeed(
                Math.min(obj.getMaxSpeed() + offset, 5)
        );*/
        obj.setMaxSpeed(
                obj.getMaxSpeed() + offset
        );

    }
}
