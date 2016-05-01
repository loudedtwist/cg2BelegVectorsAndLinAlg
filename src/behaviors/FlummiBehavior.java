package behaviors;

import objects.MovingObject;

public class FlummiBehavior implements Behavior {
    private MovingObject object;

    public FlummiBehavior(MovingObject object) {
        this.object = object;
    }

    @Override
    public void updateState() {
        object.setYPos(object.getYPos() + object.getSpeed());
        if (object.getYPos() > 480 || object.getYPos() < 0 ||object.getXPos() < 0)
            object.setSpeed(object.getSpeed() * -1);
    }
}
