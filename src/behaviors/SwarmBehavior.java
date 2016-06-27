package behaviors;

import alg.LinAlg;
import alg.Vektor2D;
import objects.MovingGroup;
import objects.MovingObject;
import objects.Swarm;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  A Class for calculating the behavior of separate {@link MovingObject},
 *  while acting in a swarm.
 */

public class SwarmBehavior implements Behavior {
    public static final int NEIGHBORHOOD_DISTANCE = 300;
    public static final double WEIGHT_ALIGMENT = 0.08;//1/12;
    public static final double WEIGHT_COHESION = 0.1;//0.01;
    public static final double WEIGHT_SEPARATION = 0.04;
    MovingGroup swarm;

    /**
     *  Contructor for SwarmBehavior.
     *
     * @param swarm group of MovingObjects
     */
    public SwarmBehavior(Swarm swarm) {
        this.swarm = swarm;
    }

    @Override
    public void updateState() { 
        computeSwarmAcceleration();
    }

    /**
     * Method which calculates the acceleration for every {@link MovingObject} of a swarm, by multipying
     * the alignment, cohesion and separation width a corresponding weight constant.
     */
    private void computeSwarmAcceleration() {
        ArrayList<MovingObject> buffer = new ArrayList<>(swarm.getSwarmAgents());

        for (MovingObject agent : buffer) {
            Vektor2D alignment = (Vektor2D) computeAlignment(agent).mult(WEIGHT_ALIGMENT);//gewicht 1/12
            Vektor2D cohesion = (Vektor2D) computeCohesion(agent).mult(WEIGHT_COHESION);// 1/100
            Vektor2D separation = (Vektor2D) computeSeparation(agent).mult(WEIGHT_SEPARATION);//gewicht 1

            Vektor2D desired = (Vektor2D) LinAlg.add(alignment, cohesion, separation);
            desired.normalize().mult(agent.getMaxSpeed());
            Vektor2D steer = (Vektor2D) LinAlg.sub(desired, agent.getVelocity());
            steer.limit(agent.getMaxRotationDeegry());
            agent.getAcceleration().add(steer);
        }

        Collections.copy(swarm.getSwarmAgents(), buffer);
    }

    /**
     * Calculates the alignment of a {@link MovingObject}, by adding each velocity-vector
     * of the neighbouring MovingObjects, dividing by
     * the number of neighbours and normalizing the resulting vector.
     *
     * This method computes the data for the the alignment of the MovingObjects to the group of MovingObjects
     * @param currentAgent swarmAgent for which the alignment should be calculated
     * @return Alignmentvector as {@link Vektor2D}
     */
    public Vektor2D computeAlignment(MovingObject currentAgent) {
        int neighborCount = 0;
        Vektor2D alignment = new Vektor2D();

        for (MovingObject agent : swarm.getSwarmAgents()) {
            if (agent == currentAgent) continue;
            if (MovingGroup.areNeighbors(currentAgent, agent)) {
                alignment.add(agent.getVelocity());
                neighborCount++;
            }
        }

        if (neighborCount == 0)
            return alignment;

        alignment.div(neighborCount);
        alignment.normalize();
        return alignment;
    }

    //Mittelpunkt der Gruppe folgen

    /**
     * Calculates the cohesion of a {@link MovingObject}, by adding each positional-vector
     * of the neighbouring MovingObjects , dividing by
     * the number of neighbours, subtracting the position-vector of the {@code MovingObject currentAgent} you calculate the cohesion on and normalizing the resulting vector.
     *
     * This method computes the data for the MovingObjects to follow the center of a group of MovingObjects.
     * @param currentAgent swarmAgent for which the cohesion should be calculated
     * @return Cohesionvector as {@link Vektor2D}
     */
    public Vektor2D computeCohesion(MovingObject currentAgent) {
        int neighborCount = 0;
        Vektor2D alignment = new Vektor2D();

        for (MovingObject agent : swarm.getSwarmAgents()) {
            if (agent == currentAgent) continue;
            if (MovingGroup.areNeighbors(currentAgent, agent)) {
                alignment.add(agent.getPos());
                neighborCount++;
            }
        }

        if (neighborCount == 0)
            return alignment;

        alignment.div(neighborCount);
        alignment.sub(currentAgent.getPos());
        alignment.normalize();
        return alignment;

    }

    /**
     *  Calculates the separation of a {@link MovingObject}, by adding each distance
     * of the neighbouring MovingObjects to {@code MovingObject currentAgent}, dividing by
     * the number of neighbours, multiplying by -1 and normalizing the resulting vector.
     *
     * This method computes the data for the the separation of the MovingObjects to the group of MovingObjects
     * @param currentAgent swarmAgent for which the separation should be calculated
     * @return Separationvector as {@link Vektor2D}
     */
    public Vektor2D computeSeparation(MovingObject currentAgent) {
        int neighborCount = 0;
        Vektor2D separation = new Vektor2D();

        for (MovingObject agent : swarm.getSwarmAgents()) {
            if (agent == currentAgent) continue;
            if (MovingGroup.areNeighbors(currentAgent, agent)) {
                separation.add(
                        LinAlg.sub(agent.getPos(), currentAgent.getPos())
                );
                neighborCount++;
            }
        }

        if (neighborCount == 0)
            return separation;

        separation.div(neighborCount);
        separation.mult(-1);
        separation.normalize();
        return separation;
    }

}
