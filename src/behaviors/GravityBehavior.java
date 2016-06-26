package behaviors;

import objects.MovingObject;

/**
 * Class defining a behavior which simulates a gravitational influnece
 */
public class GravityBehavior implements Behavior {
    MovingObject obj;
    float gravity = 0.001f;

    /**
     * Constructor for GravityBehavior
     * @param obj The {@link MovingObject} which the behavior should be applied to
     */
    public GravityBehavior(MovingObject obj) {
        this.obj = obj;
    }

    /**
     * update method which lets the {@link MovingObject} be influenced by
     * gravity
     */
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
