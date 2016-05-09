package behaviors;

import objects.MovingObject;

import java.util.Random;

public class WindBehavior implements Behavior {
    MovingObject object;
    float windSpeed;

    public WindBehavior(MovingObject object) {
        this.object = object;
        windSpeed = 0.1f;

    }

    @Override
    public void updateState() {
        //thin-plate-spline
        Random random = new Random();
        int richtung = 1;
        if(!random.nextBoolean()) richtung = -1;
        //windSpeed = random.nextFloat() ;
        object.setXPos(
                object.getXPos() + windSpeed
        );
    }
}
