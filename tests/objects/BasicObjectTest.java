package objects;

import alg.Vektor2D;
import behaviors.Behavior;
import behaviors.FlummiBehavior;
import org.junit.Test;
import static org.junit.Assert.*;


public class BasicObjectTest {
    @Test
    public void addBehavior() throws Exception {
        Circle circle = new Circle();
        Behavior behavior = new FlummiBehavior(circle);
        circle.addBehavior(behavior);
        boolean result = circle.behaviors.contains(behavior);
        assertTrue(result);
    }

    @Test
    public void removeBehavior() throws Exception {

    }

    @Test
    public void testModVelocity(){
        Swarm swarm = new Swarm();
        System.out.println(swarm.getVelocity().toString());
        swarm.getVelocity().add(new Vektor2D(2,2)).add(new Vektor2D(4,3));
        System.out.println(swarm.getVelocity().toString());
        assertEquals(6,swarm.getVelocity().getX(),0.001);
        assertEquals(5,swarm.getVelocity().getY(),0.001);
    }


}