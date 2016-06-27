package behaviors;

import alg.Vektor2D;
import com.sun.prism.paint.Color;
import objects.BasicObject;
import objects.Circle;
import objects.Swarm;
import org.junit.Assert;
import org.junit.Test;

public class SwarmBehaviorTest {
    @Test
    public void testSwarm() throws Exception {
        Swarm swarm = new Swarm();
        SwarmBehavior testBehavior = new SwarmBehavior(swarm);
        swarm.addBehavior(testBehavior);

        for (int i = 0; i < 3; i++) {
            Circle c = new Circle(new Vektor2D(i, i), 0, 1, 1, Color.GREEN);
            c.addBehavior(new VelocityBasedMovementBehavior(c));
            swarm.addAgent(c);
        }

        swarm.getSwarmAgents().forEach((it) -> System.out.println(it.getPos().toString()));
        swarm.executeBehavior();
        swarm.getSwarmAgents().forEach(BasicObject::executeBehavior);
        swarm.getSwarmAgents().forEach((it) -> System.out.println(it.getPos().toString()));
        Assert.assertTrue(swarm.getSwarmAgents().get(0).getXPos() > 0);
        Assert.assertTrue(swarm.getSwarmAgents().get(0).getYPos() > 0);
        Assert.assertTrue(swarm.getSwarmAgents().get(2).getYPos() < 2);
        Assert.assertTrue(swarm.getSwarmAgents().get(2).getYPos() < 2);
    }

    @Test
    public void computeCohesion() throws Exception {
        Swarm swarm = new Swarm();
        SwarmBehavior testBehavior = new SwarmBehavior(swarm);
        swarm.addBehavior(testBehavior);

        System.out.println("Circles");
        for (int i = -1; i < 2; i++) {
            Circle c = new Circle(new Vektor2D(i, 0), 0, 1, 1, Color.GREEN);
            c.addBehavior(new VelocityBasedMovementBehavior(c));
            System.out.println(c.toString());
            swarm.addAgent(c);
        }

        Circle testMovObj = new Circle(new Vektor2D(0, -1), 0, 1, 1, Color.GREEN);
        Vektor2D cohesion = testBehavior.computeCohesion(testMovObj);
        Assert.assertEquals(1, cohesion.getY(), 0.01);
        System.out.println("Cohesion:\n" + cohesion.toString());

    }

    @Test
    public void computeSeparation() throws Exception {
        Swarm swarm = new Swarm();
        SwarmBehavior testBehavior = new SwarmBehavior(swarm);
        swarm.addBehavior(testBehavior);

        System.out.println("Circles");
        for (int i = -1; i < 2; i++) {
            Circle c = new Circle(new Vektor2D(0, 2), 0, 1, 1, Color.GREEN);
            c.addBehavior(new VelocityBasedMovementBehavior(c));
            System.out.println(c.toString());
            swarm.addAgent(c);
        }

        Circle testMovObj = new Circle(new Vektor2D(0, 1), 0, 1, 1, Color.GREEN);
        Vektor2D separation = testBehavior.computeSeparation(testMovObj);
        System.out.println("Separation:\n" + separation.toString());
        Assert.assertEquals(-1, separation.getY(), 0.01);
    }

    @Test
    public void computeAlignment() throws Exception {
        Swarm swarm = new Swarm();
        SwarmBehavior testBehavior = new SwarmBehavior(swarm);
        swarm.addBehavior(testBehavior);

        System.out.println("Circles");
        for (int i = -1; i < 2; i++) {
            Circle c = new Circle(new Vektor2D(0, 2), 0, 1, 1, Color.GREEN);
            c.setVelocity(new Vektor2D(-1,0));
            c.addBehavior(new VelocityBasedMovementBehavior(c));
            System.out.println(c.toString());
            swarm.addAgent(c);
        }

        Circle testMovObj = new Circle(new Vektor2D(0, 0), 0, 1, 1, Color.GREEN);
        Vektor2D alignment = testBehavior.computeAlignment(testMovObj);
        System.out.println("Alignment:\n" + alignment.toString());
        Assert.assertEquals(-1, alignment.getX(), 0.01);
    }

}