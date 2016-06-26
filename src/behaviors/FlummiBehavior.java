package behaviors;

import objects.MovingObject;

/**
 * Class defining a Flummi like behavior
 */
public class FlummiBehavior implements Behavior {
    private MovingObject obj;

    /**
     * Constructor for FlummiBehavior
     * @param obj The {@link MovingObject} which the behavior should be applied to
     */
    public FlummiBehavior(MovingObject obj) {
        this.obj = obj;
    }

    /**
     * update method which lets the {@link MovingObject} bounce off the window boarders
     * like a bouncy ball
     */
    @Override
    public void updateState() {
        obj.setYPos(obj.getYPos() + obj.getMaxSpeed());
        if (obj.getYPos() > 480 || obj.getYPos() < 0 || obj.getXPos() < 0)
            obj.setMaxSpeed(obj.getMaxSpeed() * -1);
    }
}
