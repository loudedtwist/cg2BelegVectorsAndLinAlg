package behaviors;

import objects.MovingObject;

public class VelocityBasedMovementBehavior implements Behavior {
    private MovingObject obj;

    public VelocityBasedMovementBehavior(MovingObject obj) {
        this.obj = obj;
    }

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
