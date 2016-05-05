package behaviors;

import alg.LinAlg;
import alg.Vektor2D;
import objects.MovingGroup;
import objects.MovingObject;
import objects.Swarm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class SwarmFunctionalBehavior implements Behavior {
    public static final int NEIGHBORHOOD_DISTANCE = 300;
    public static final double WEIGHT_ALIGMENT = 0.08;//1/12;
    public static final double WEIGHT_COHESION = 0.1;//0.01;
    public static final double WEIGHT_SEPARATION = 0.04;
    MovingGroup swarm;

    public SwarmFunctionalBehavior(Swarm swarm) {
        this.swarm = swarm;
    }

    @Override
    public void updateState() {
        computeSwarmAcceleration();
    }

    private void computeSwarmAcceleration() {
        ArrayList<MovingObject> buffer = new ArrayList<>(swarm.getSwarmAgents());
        buffer.parallelStream().forEach(agent -> {
            Vektor2D alignment = (Vektor2D) computeAlignment(agent).mult(WEIGHT_ALIGMENT);//gewicht 1/12
            Vektor2D cohesion = (Vektor2D) computeCohesion(agent).mult(WEIGHT_COHESION);// 1/100
            Vektor2D separation = (Vektor2D) computeSeparation(agent).mult(WEIGHT_SEPARATION);//gewicht 1

            Vektor2D desired = (Vektor2D) LinAlg.add(alignment, cohesion, separation);
            desired.normalize();
            desired.mult(agent.getMaxSpeed());
            Vektor2D steer = (Vektor2D) LinAlg.sub(desired, agent.getVelocity());
            steer.limit(agent.getMaxRotationDeegry());
            agent.getAcceleration().add(steer);
        });
        Collections.copy(swarm.getSwarmAgents(), buffer);
    }

    public Vektor2D computeAlignment(MovingObject currentAgent) {
        AtomicInteger neighborCount = new AtomicInteger() ;
        Vektor2D alignment = new Vektor2D();

        swarm.getSwarmAgents().parallelStream().filter(agent -> agent!=currentAgent).forEach(agent ->{
            if (MovingGroup.isNeighbor(currentAgent, agent)) {
                alignment.add(agent.getVelocity());
                neighborCount.incrementAndGet();
            }
        });

        if (neighborCount.intValue() == 0)
            return alignment;

        alignment.div(neighborCount.intValue());
        alignment.normalize();
        return alignment;
    }

    //Mittelpunkt der Gruppe folgen
    public Vektor2D computeCohesion(MovingObject currentAgent) {
        int neighborCount = 0;
        Vektor2D alignment = new Vektor2D();

        for (MovingObject agent : swarm.getSwarmAgents()) {
            if (agent == currentAgent) continue;
            if (MovingGroup.isNeighbor(currentAgent, agent)) {
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

    public Vektor2D computeSeparation(MovingObject currentAgent) {
        int neighborCount = 0;
        Vektor2D alignment = new Vektor2D();

        for (MovingObject agent : swarm.getSwarmAgents()) {
            if (agent == currentAgent) continue;
            if (MovingGroup.isNeighbor(currentAgent, agent)) {
                alignment.add(
                        LinAlg.sub(agent.getPos(), currentAgent.getPos())
                );
                neighborCount++;
            }
        }

        if (neighborCount == 0)
            return alignment;

        alignment.div(neighborCount);
        alignment.mult(-1);
        alignment.normalize();
        return alignment;
    }

}
