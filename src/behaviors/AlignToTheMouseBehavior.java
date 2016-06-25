package behaviors;

import alg.Vektor2D;
import framentLoops.SwarmLayerLoop;
import objects.MovingObject;

import static framentLoops.SwarmLayerLoop.MOUSE_AREA;

/**
 * Class for the calculation of Swarm behavior, in context of mouse movement and clicks.
 * Aligning the swarm Objects to the mouse
 */
public class AlignToTheMouseBehavior implements MouseListenerBehavior {
    SwarmLayerLoop layerLoop;

    /**
     * Constructor for AlignToTheMouseBehavior
     * @param layerLoop the {@link SwarmLayerLoop} with swarms of {@link MovingObject}s which the behavior should be used on.
     */
    public AlignToTheMouseBehavior(SwarmLayerLoop layerLoop) {
        this.layerLoop = layerLoop;
    }

    /**
     *Calculates the altering properties (like alignment, etc.) of MovingObjects while a mouse is present
     * in the window area and moved while clicked. This method aligns the swarm Objects to the mouse cursor, by multiplying
     * the alignment vector with a positive value.
     * @param coord Coordinates of the mouse position as {@link Vektor2D}
     */
    @Override
    public void mouseMovingWithButtonPressed(Vektor2D coord) {
        for (MovingObject mo : layerLoop.getSwarms().get(0).getSwarmAgents()) {
            if ((coord.getX() < mo.getXPos() + MOUSE_AREA && coord.getX() > mo.getXPos() - MOUSE_AREA) &&
                    (coord.getY() < mo.getYPos() + MOUSE_AREA && coord.getY() > mo.getYPos() - MOUSE_AREA)) {
                Vektor2D aligment = new Vektor2D(coord.getX(),coord.getY());
                aligment.sub(mo.getPos());
                aligment.mult(10);
                mo.getVelocity().add(aligment);
            }
        }
    }

}
