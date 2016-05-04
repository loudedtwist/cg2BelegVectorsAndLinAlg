package behaviors;

import objects.MovingObject;

public class FlummiBehavior implements Behavior {
    private MovingObject obj;

    public FlummiBehavior(MovingObject obj) {
        this.obj = obj;
    }

    @Override
    public void updateState() {
        obj.setYPos(obj.getYPos() + obj.getMaxSpeed());
        if (obj.getYPos() > 480 || obj.getYPos() < 0 || obj.getXPos() < 0)
            obj.setMaxSpeed(obj.getMaxSpeed() * -1);
    }
}
