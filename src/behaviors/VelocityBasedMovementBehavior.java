package behaviors;

import objects.MovingObject;

/**
 * Class defining a VelocityBasedMovementBehavior
 */
public class VelocityBasedMovementBehavior implements Behavior {
    private MovingObject obj;

    /**
     * Constructor for VelocityBasedMovementBehavior
     * @param obj The {@link MovingObject} which the behavior should be applied to
     */
    public VelocityBasedMovementBehavior(MovingObject obj) {
        this.obj = obj;
    }
    /**
     * update method which lets the {@link MovingObject} move based on its velocity
     * and acceleration
     */
    @Override
    public void updateState() {
        obj.getVelocity().add(obj.getAcceleration());
        obj.getVelocity().limit(obj.getMaxSpeed());
        obj.getPos().add(obj.getVelocity());
        resetAcceleration();
    }

    private void resetAcceleration() {
        obj.getAcceleration().mult(0);
    }
}
