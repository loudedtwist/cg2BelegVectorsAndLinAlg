package behaviors;

import alg.Vektor2D;
import lwjgl.MyWindow;
import objects.MovingObject;

/**
 * Class defining a behavior where the {@link MovingObject} avoid the window borders.
 */
public class WallAvoidanceBehavior implements Behavior {
    MovingObject movingObject;
    double wallAvoidanceWeight = 0.2;
    /**
     * Constructor for FlummiBehavior
     * @param movingObject The {@link MovingObject} which the behavior should be applied to
     */
    public WallAvoidanceBehavior(MovingObject movingObject) {
        this.movingObject = movingObject;
    }

    /**
     * update method which lets the {@link MovingObject} go in the opposite direction of the window border
     * if it goes near it.
     */
    @Override
    public void updateState() {
        Vektor2D avoidanceVelocity = new Vektor2D(0,0);
        if (movingObject.getXPos() < 40)
        {
            avoidanceVelocity.setX(10);
        }
        if (movingObject.getXPos() > MyWindow.WINDOW_WIDTH-40)
        {
            avoidanceVelocity.setX(-10);
        }
        if (movingObject.getYPos() < 40)
        {
            avoidanceVelocity.setY(10);
        }
        if (movingObject.getYPos() > MyWindow.WINDOW_HEIGHT-40)
        {
            avoidanceVelocity.setY(-10);
        }
        avoidanceVelocity.mult(wallAvoidanceWeight);

        movingObject.getVelocity().add(avoidanceVelocity);
    }
}
