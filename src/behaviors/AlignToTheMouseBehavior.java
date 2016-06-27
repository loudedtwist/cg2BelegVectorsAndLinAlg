package behaviors;

import alg.Vektor2D;
import renderingLayers.SwarmLayer;
import objects.MovingObject;

import static renderingLayers.SwarmLayer.MOUSE_AREA;

/**
 * Class for the calculation of Swarm behavior, in context of mouse movement and clicks.
 * Aligning the swarm Objects to the mouse
 */
public class AlignToTheMouseBehavior implements MouseListenerBehavior {
    SwarmLayer layerLoop;

    /**
     * Constructor for AlignToTheMouseBehavior
     * @param layerLoop the {@link SwarmLayer} with swarms of {@link MovingObject}s which the behavior should be used on.
     */
    public AlignToTheMouseBehavior(SwarmLayer layerLoop) {
        this.layerLoop = layerLoop;
    }

    /**
     *Calculates the altering properties (like alignment, etc.) of MovingObjects while a mouse is present
     * in the window area and moved while clicked. This method aligns the swarm Objects to the mouse cursor, by multiplying
     * the alignment vector with a positive value.
     * @param mouseCoord Coordinates of the mouse position as {@link Vektor2D}
     */
    @Override
    public void mouseMovingWithButtonPressed(Vektor2D mouseCoord) {
        for (MovingObject mo : layerLoop.getSwarms().get(0).getSwarmAgents()) {
            if ((mouseCoord.getX() < mo.getXPos() + MOUSE_AREA && mouseCoord.getX() > mo.getXPos() - MOUSE_AREA) &&
                    (mouseCoord.getY() < mo.getYPos() + MOUSE_AREA && mouseCoord.getY() > mo.getYPos() - MOUSE_AREA)) {
                Vektor2D aligment = new Vektor2D(mouseCoord.getX(),mouseCoord.getY());
                aligment.sub(mo.getPos());
                aligment.mult(10);
                mo.getVelocity().add(aligment);
            }
        }
    }

}
