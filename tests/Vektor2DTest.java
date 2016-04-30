import alg.Vektor2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Vektor2DTest {
    @Test
    public void getX() throws Exception {
        Vektor2D v = new Vektor2D(1000,1);
        assertEquals(1000, v.getX(), .00000001);
    }

    @Test
    public void getY() throws Exception {
        Vektor2D v = new Vektor2D(1000,1);
        assertEquals(1, v.getY(), .00000001);
    }

}