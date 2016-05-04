import alg.Vektor;
import alg.Vektor2D;
import alg.Vektor3D;
import exceptions.DoublesOutOfRangeException;
import exceptions.NotInstanceOfException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class VektorTest {
    @Rule
    public ExpectedException dOutOfRange = ExpectedException.none();

    @Test
    public void multVecByDouble() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor3D v = new Vektor3D(1, 2, 3);
        Vektor3D expected = new Vektor3D(4, 8, 12);
        v.mult(4);
        assertEquals(v.getX(), expected.getX(), v.getDeltaByFloatCompartment());
        assertEquals(v.getY(), expected.getY(), v.getDeltaByFloatCompartment());
        assertEquals(v.getZ(), expected.getZ(), v.getDeltaByFloatCompartment());
    }
    @Test
    public void multVecByDoubleMaxValueBy1() throws DoublesOutOfRangeException, NotInstanceOfException {;
        Vektor3D v = new Vektor3D(Double.MAX_VALUE, 2, 3);
        v.mult(1);
    }
    @Test
    public void diVecByDouble() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor3D v = new Vektor3D(4, 8, 12);
        Vektor3D expected = new Vektor3D(1, 2, 3);
        v.div(4);
        assertEquals(v.getX(), expected.getX(), v.getDeltaByFloatCompartment());
        assertEquals(v.getY(), expected.getY(), v.getDeltaByFloatCompartment());
        assertEquals(v.getZ(), expected.getZ(), v.getDeltaByFloatCompartment());
    }

    @Test
    public void subBigDoublesException() throws DoublesOutOfRangeException {
        dOutOfRange.expect(DoublesOutOfRangeException.class);
        dOutOfRange.expectMessage("SUBTRAHEND IS TOO BIG(NEGATIVE)");
        Vektor.subTwoDoubles(Double.MAX_VALUE, -Double.MAX_VALUE);
    }

    @Test
    public void subSmallDoublesException() throws DoublesOutOfRangeException {
        dOutOfRange.expect(DoublesOutOfRangeException.class);
        dOutOfRange.expectMessage("SUBTRAHEND IS TOO BIG(POSITIVE)");
        Vektor.subTwoDoubles(-Double.MAX_VALUE, Double.MAX_VALUE);
    }

    @Test
    public void addBigDoublesException() throws DoublesOutOfRangeException {
        dOutOfRange.expect(DoublesOutOfRangeException.class);
        dOutOfRange.expectMessage("DOUBLES ARE TOO BIG");
        Vektor.addTwoDoubles(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    @Test
    public void addSmallDoublesException() throws DoublesOutOfRangeException {
        dOutOfRange.expect(DoublesOutOfRangeException.class);
        dOutOfRange.expectMessage("DOUBLES ARE TOO SMALL");
        System.out.println(Vektor.addTwoDoubles(-Double.MAX_VALUE, -Double.MAX_VALUE));
    }

    @Test
    public void multBigDoublesException() throws DoublesOutOfRangeException {
        dOutOfRange.expect(DoublesOutOfRangeException.class);
        dOutOfRange.expectMessage("DOUBLES ARE TOO BIG");
        Vektor.multTwoDoubles(Double.MAX_VALUE, Double.MAX_VALUE );
        Vektor.multTwoDoubles(Double.MAX_VALUE / 2, 3);
    }

    @Test
    public void multBigNegativDoublesException() throws DoublesOutOfRangeException {
        dOutOfRange.expect(DoublesOutOfRangeException.class);
        dOutOfRange.expectMessage("DOUBLES ARE TOO BIG");
        Vektor.multTwoDoubles(-Double.MAX_VALUE, -Double.MAX_VALUE);
        Vektor.multTwoDoubles(-Double.MAX_VALUE / 2, -3);
    }

    @Test
    public void multBigMixedDoublesException() throws DoublesOutOfRangeException {
        dOutOfRange.expect(DoublesOutOfRangeException.class);
        dOutOfRange.expectMessage("DOUBLES ARE TOO BIG");
        Vektor.multTwoDoubles(-Double.MAX_VALUE, Double.MAX_VALUE);
        Vektor.multTwoDoubles(-Double.MAX_VALUE / 2, 3);
    }

    @Test
    public void div0DivisorDoublesException() throws IllegalArgumentException {
        dOutOfRange.expect(IllegalArgumentException.class);
        dOutOfRange.expectMessage("DIVISOR IS 0");
        Vektor.divTwoDoubles(Double.MAX_VALUE, 0);
    }

    @Test
    public void divSmallDoublesException() throws IllegalArgumentException {
        dOutOfRange.expect(IllegalArgumentException.class);
        dOutOfRange.expectMessage("DIVISOR IS TO SMALL (-1 < D < 1)");
        Vektor.divTwoDoubles(Double.MAX_VALUE, Double.MIN_VALUE);
    }

    @Test
    public void getCopy() throws Exception {
        Vektor2D v1 = new Vektor2D(5, 5);
        Vektor2D v2 = (Vektor2D) v1.clone();
        assertEquals(v1.getX(), v2.getX(), .000001);
        assertEquals(v1.getY(), v2.getY(), .000001);
    }

    @Test
    public void setPosition() throws Exception {
        Vektor2D v1 = new Vektor2D();
        v1.setPosition(3, 4);
        assertEquals(3, v1.getX(), .00000001);
        assertEquals(4, v1.getY(), .00000001);
    }

    @Test
    public void toStringTest() {
        Vektor3D v = new Vektor3D(14, 32, 5);
        System.out.println(v.toString());
        assertEquals("class alg.Vektor3D : { 14.0, 32.0, 5.0 }", v.toString());
    }

    @Test
    public void addTwoDoubles() throws Exception {
        double deltaRange = 0.001;
        assertEquals(5, Vektor.addTwoDoubles(3, 2), deltaRange);
        assertEquals(5.2, Vektor.addTwoDoubles(3.1, 2.1), deltaRange);
        assertEquals(-5.2, Vektor.addTwoDoubles(-3.1, -2.1), deltaRange);
    }

    @Test
    public void subTwoDoubles() throws Exception {
        double deltaRange = 0.001;
        assertEquals(5, Vektor.subTwoDoubles(7, 2), deltaRange);
        assertEquals(5.2, Vektor.subTwoDoubles(7.3, 2.1), deltaRange);
        assertEquals(-5.2, Vektor.subTwoDoubles(-7.3, -2.1), deltaRange);
    }

    @Test
    public void multTwoDoubles() throws Exception {
        double deltaRange = 0.000001;
        assertEquals(15, Vektor.multTwoDoubles(5, 3), deltaRange);
    }

    @Test
    public void divTwoDoubles() throws Exception {
        double deltaRange = 0.000001;
        assertEquals(5, Vektor.divTwoDoubles(10, 2), deltaRange);
        assertEquals(6.5, Vektor.divTwoDoubles(13, 2), deltaRange);
    }

    @Test
    public void equals() throws Exception {
        Vektor2D p1 = new Vektor2D(1, 1);
        Vektor2D p2 = new Vektor2D(1, 1);
        Vektor2D p3 = new Vektor2D(2, 3);
        Vektor2D p4 = new Vektor2D(4, 4);
        Vektor2D p5 = new Vektor2D(4.00000009, 4.00000009);
        Vektor2D p6 = new Vektor2D(4.00000009, 4.00000009);
        p4.setDeltaByFloatCompartment(.0000001);
        p6.setDeltaByFloatCompartment(.0000000001);


        assertTrue(p1.equals(p2));
        assertTrue(p4.equals(p5));
        assertFalse(p1.equals(p3));
        assertFalse(p6.equals(p4));
        p6.setDeltaByFloatCompartment(.0000001);
        assertTrue(p6.equals(p4));
        assertFalse(p1.equals(null));
    }

    @Test
    public void notEquals() throws Exception {
        Vektor2D p1 = new Vektor2D(1, 1);
        Vektor2D p2 = new Vektor2D(1, 1);
        Vektor2D p3 = new Vektor2D(2, 3);
        Vektor2D p4 = new Vektor2D(4, 4);
        Vektor2D p5 = new Vektor2D(4.00000009, 4.00000009);
        Vektor2D p6 = new Vektor2D(4.00000009, 4.00000009);
        p4.setDeltaByFloatCompartment(.0000001);
        p6.setDeltaByFloatCompartment(.0000000001);


        assertFalse(p1.notEquals(p2));
        assertFalse(p4.notEquals(p5));
        assertTrue(p1.notEquals(p3));
        assertTrue(p6.notEquals(p4));
        p6.setDeltaByFloatCompartment(.0000001);
        assertFalse(p6.notEquals(p4));
        assertTrue(p1.notEquals(null));
    }

    @Test
    public void isNullVektor() {
        Vektor2D v1 = new Vektor2D(0, 0);
        Vektor2D v2 = new Vektor2D(3, 0);
        Vektor2D v3 = new Vektor2D(0, 3);
        Vektor2D v4 = new Vektor2D(1, 3);

        assertTrue(v1.isNullVektor());
        assertFalse(v2.isNullVektor());
        assertFalse(v3.isNullVektor());
        assertFalse(v4.isNullVektor());
    }

    @Test
    public void length() throws DoublesOutOfRangeException {
        Vektor3D v1 = new Vektor3D(1, 2, 3);
        Vektor2D v2 = new Vektor2D(2, 3);
        assertEquals(3.742, v1.length(), 0.001);
        assertEquals(Math.sqrt(13), v2.length(), 0.000001);
    }

    @Test
    public void lengthExp2() throws DoublesOutOfRangeException {

        Vektor3D v1 = new Vektor3D(1, 2, 3);
        Vektor2D v2 = new Vektor2D(2, 3);
        assertEquals(14, v1.lengthExp2(), 0.001);
        assertEquals(13, v2.lengthExp2(), 0.000001);
    }
    @Test
    public void getSumOfCoords() throws DoublesOutOfRangeException {
        Vektor3D v1 = new Vektor3D(99, -50, 1);
        double expected = 50;
        assertEquals(expected, v1.sumOfAllCoords(), 0.001);
    }

    @Test
    public void normalize() throws IllegalArgumentException, DoublesOutOfRangeException {
        Vektor3D v1 = new Vektor3D(1, 2, 3);

        double lengthBefore = v1.length();
        v1.normalize();
        double lengthAfter = v1.length();
        double delta = 0.000000001;
        double x = v1.getX();
        double y = v1.getY();
        double z = v1.getZ();
        assertEquals(x, 1 / lengthBefore, delta);
        assertEquals(y, 2 / lengthBefore, delta);
        assertEquals(z, 3 / lengthBefore, delta);
        assertEquals(1, lengthAfter, delta);
    }

    @Test
    public void productOfVector() throws IllegalArgumentException, DoublesOutOfRangeException {
        Vektor3D v1 = new Vektor3D(4, 2, 3);
        double product = v1.productOfallCoordinats();
        assertEquals(24, product, .00001);
    }

    @Test
    public void limit() throws IllegalArgumentException, DoublesOutOfRangeException {
        Vektor3D v1 = new Vektor3D(40, 211, 32);
        v1.limit(10);
        double lengthNew = v1.length();
        assertEquals(10, lengthNew, 0.001);
    }
}