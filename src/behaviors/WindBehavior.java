package behaviors;

import objects.MovingObject;

import java.util.Random;
/**
 * Class defining a behavior where the {@link MovingObject} is influenced by a simulated wind. The wind got a certain direction
 * and windspeed
 */
public class WindBehavior implements Behavior {
    MovingObject object;
    float windSpeed;

    /**
     * Constructor for GravityBehavior
     * @param object The {@link MovingObject} which the behavior should be applied to
     */
    public WindBehavior(MovingObject object) {
        this.object = object;
        windSpeed = 0.1f;

    }
    /**
     * update method which lets the {@link MovingObject} float into the set wind
     * direction with the set wind speed
     */
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
