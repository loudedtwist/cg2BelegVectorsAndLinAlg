package objects;

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

}