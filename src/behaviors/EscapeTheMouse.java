package behaviors;

import alg.Vektor;
import objects.MovingGroup;
import objects.MovingObject;

public class EscapeTheMouse implements MouseListenerBehavior {
    MovingGroup movingGroup ;

    public EscapeTheMouse(MovingGroup movingGroup) {
        this.movingGroup = movingGroup;
    }

    @Override
    public void mouseMovingWithButtonPressed(Vektor coord) {
        for (MovingObject agent : movingGroup.getSwarmAgents()) {
            MovingGroup.isNeighbor(agent,agent);
        }
    }

}
