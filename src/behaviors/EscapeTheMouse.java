package behaviors;

import alg.Vektor2D;
import framentLoops.SwarmLayerLoop;
import objects.MovingObject;

import static framentLoops.SwarmLayerLoop.MOUSE_AREA;

public class EscapeTheMouse implements MouseListenerBehavior {
    SwarmLayerLoop layerLoop;

    public EscapeTheMouse(SwarmLayerLoop layerLoop) {
        this.layerLoop = layerLoop;
    }

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
