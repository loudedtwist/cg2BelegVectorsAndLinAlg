package objects;

import alg.LinAlg;
import behaviors.SwarmBehavior;

import java.util.ArrayList;

abstract public class MovingGroup extends MovingObject {
    ArrayList<MovingObject> swarmAgents;

    public MovingGroup() {
        this.swarmAgents = new ArrayList<>();
    }

    public void addAgent(MovingObject newAgent) {
        if(newAgent!=null)
        swarmAgents.add(newAgent);
    }

    public void setSwarmAgents(ArrayList<MovingObject> swarmAgents) {
        this.swarmAgents = swarmAgents;
    }

    public ArrayList<MovingObject> getSwarmAgents() {
        return swarmAgents;
    }

    static public boolean isNeighbor(MovingObject currentAgent, MovingObject agent ) {
        double distanceBetweenAgents = LinAlg.manhatDist(agent.getPos(), currentAgent.getPos());
        if (distanceBetweenAgents < SwarmBehavior.NEIGHBORHOOD_DISTANCE) {
            return true;
        }
        return false;
    }

}
