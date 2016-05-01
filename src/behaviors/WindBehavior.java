package behaviors;

import objects.MovingObject;

public class WindBehavior implements Behavior {
    MovingObject object;
    float windSpeed;

    public WindBehavior(MovingObject object) {
        this.object = object;
        windSpeed = 0.1f;
    }

    @Override
    public void updateState() {
        object.setXPos(
                object.getXPos() + windSpeed
        );
    }
}
