package behaviors;

import alg.Vektor2D;
import renderingLayers.SwarmLayer;
import objects.MovingObject;

import static renderingLayers.SwarmLayer.MOUSE_AREA;

/**
 * Class for the calculation of Swarm behavior, in context of mouse movement and clicks.
 * Let the swarm Objects escape from the mouse
 */

public class EscapeTheMouse implements MouseListenerBehavior {
    SwarmLayer layerLoop;

    /**
     * Constructor for EscapeTheMouse behavior
     * @param layerLoop the {@link SwarmLayer} with swarms of {@link MovingObject}s which the behavior should be used on.
     */
    public EscapeTheMouse(SwarmLayer layerLoop) {
        this.layerLoop = layerLoop;
    }

    /**
     *Calculates the altering properties (like alignment, etc.) of MovingObjects while a mouse is present
     * in the window area and moved while clicked. This method makes that the swarm Objects seemingly try to escape from the mouse cursor, by multiplying
     * the alignment vector with a negative value.
     * @param coord Coordinates of the mouse position as {@link Vektor2D}
     */
    @Override
    public void mouseMovingWithButtonPressed(Vektor2D coord) {
        for (MovingObject mo : layerLoop.getSwarms().get(0).getSwarmAgents()) {
            if ((coord.getX() < mo.getXPos() + MOUSE_AREA && coord.getX() > mo.getXPos() - MOUSE_AREA) &&
                    (coord.getY() < mo.getYPos() + MOUSE_AREA && coord.getY() > mo.getYPos() - MOUSE_AREA)) {
                Vektor2D aligment = new Vektor2D(coord.getX(),coord.getY());
                aligment.sub(mo.getPos());
                aligment.mult(-10);
                mo.getVelocity().add(aligment);
            }
        }
    }

}
