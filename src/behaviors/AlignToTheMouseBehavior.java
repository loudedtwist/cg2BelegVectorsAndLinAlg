package behaviors;

import alg.Vektor2D;
import framentLoops.SwarmLayerLoop;
import objects.MovingObject;

import static framentLoops.SwarmLayerLoop.MOUSE_AREA;

public class AlignToTheMouseBehavior implements MouseListenerBehavior {
    SwarmLayerLoop layerLoop;

    public AlignToTheMouseBehavior(SwarmLayerLoop layerLoop) {
        this.layerLoop = layerLoop;
    }

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
