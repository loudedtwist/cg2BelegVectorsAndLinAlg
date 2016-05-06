import alg.LinAlg;
import alg.Vektor;
import alg.Vektor2D;
import alg.Vektor3D;
import exceptions.DoublesOutOfRangeException;
import exceptions.NotInstanceOfException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class LinAlgTest {

    @Rule
    public ExpectedException expect = ExpectedException.none();


    @Test
    public void add2D() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor2D v1 = new Vektor2D(1, 3);
        Vektor2D v2 = new Vektor2D(3, 1);
        Vektor2D v3 = (Vektor2D) LinAlg.add(v1, v2);
        Vektor2D expected = new Vektor2D(4, 4);
        assertTrue(v3.equals(expected));
        assertFalse(v1.equals(v2));
    }

    @Test
    public void add3D() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor3D v1 = new Vektor3D(1, 3, 7);
        Vektor3D v2 = new Vektor3D(3, 1, -2);
        Vektor3D v3 = (Vektor3D) LinAlg.add(v1, v2);
        Vektor3D expected = new Vektor3D(4, 4, 5);
        assertTrue(v3.equals(expected));
        assertFalse(v1.equals(v2));
    }

    @Test
    public void sub2D() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor2D v1 = new Vektor2D(5, 3);
        Vektor2D v2 = new Vektor2D(3, 1);
        Vektor2D v3 = (Vektor2D) LinAlg.sub(v1, v2);
        Vektor2D expected = new Vektor2D(2, 2);
        assertTrue(v3.equals(expected));
        assertFalse(v1.equals(v2));
    }

    @Test
    public void sub3D() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor3D v1 = new Vektor3D(5, 3, 7);
        Vektor3D v2 = new Vektor3D(3, 1, 2);
        Vektor3D v3 = (Vektor3D) LinAlg.sub(v1, v2);
        Vektor3D expected = new Vektor3D(2, 2, 5);
        assertTrue(v3.equals(expected));
        assertFalse(v1.equals(v2));
    }

    @Test
    public void length() throws DoublesOutOfRangeException {
        Vektor3D v1 = new Vektor3D(5, 3, 7);
        double expected = v1.length();
        double result = LinAlg.length(v1);
        assertEquals(expected,result,v1.getDeltaFloatError());
    }
    @Test
    public void multTwoVectors2D() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor2D v1 = new Vektor2D(5, 3);
        Vektor2D v2 = new Vektor2D(3, 1);
        Vektor2D v3 = (Vektor2D) LinAlg.multTwoVectors(v1, v2);
        Vektor2D expected = new Vektor2D(15, 3);
        assertTrue(v3.equals(expected));
        assertFalse(v1.equals(v2));
    }

    @Test
    public void multTwoVectors3D() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor3D v1 = new Vektor3D(5, 3, 7);
        Vektor3D v2 = new Vektor3D(3, 1, 2);
        Vektor3D v3 = (Vektor3D) LinAlg.multTwoVectors(v1, v2);
        Vektor3D expected = new Vektor3D(15, 3, 14);
        assertTrue(v3.equals(expected));
        assertFalse(v1.equals(v2));
    }

    @Test
    public void divTwoVectors() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor2D v1 = new Vektor2D(6, 4);
        Vektor2D v2 = new Vektor2D(3, 2);
        Vektor2D v3 = (Vektor2D) LinAlg.divTwoVectors(v1, v2);
        Vektor2D expected = new Vektor2D(2, 2);
        assertTrue(v3.equals(expected));
        assertFalse(v1.equals(v2));
    }

    @Test
    public void euclDist() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor v1 = new Vektor3D(4, 1, -2);
        Vektor v2 = new Vektor3D(2, 3, -1);
        double eucl = LinAlg.euclDist(v1, v2);
        assertEquals(3, eucl, .00001);
    }

    @Test
    public void degreeToRad() throws DoublesOutOfRangeException, NotInstanceOfException {
        double rad = LinAlg.degreeToRad(1.4);
        assertEquals(80.2, rad, .02);
    }

    @Test
    public void radToDegree() throws DoublesOutOfRangeException, NotInstanceOfException {
        double rad = LinAlg.radToDegree(200);
        assertEquals(3.49, rad, .02);
    }

    @Test
    public void determinante3D() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor3D v1 = new Vektor3D(-4, 5, 9);
        Vektor3D v2 = new Vektor3D(2.5, 6, 10);
        Vektor3D v3 = new Vektor3D(3, 4, -9);
        double result = LinAlg.determinante(v1,v2,v3);
        double expected = 566.5;
        assertEquals(expected, result, .01);
    }

    @Test
    public void determinante2D() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor2D v1 = new Vektor2D(-4, 5);
        Vektor2D v2 = new Vektor2D(2.5, 7);
        double result = LinAlg.determinante(v1,v2);
        double expected = (-40.5);
        assertEquals(expected, result, .01);
    }

    @Test
    public void normalize() throws DoublesOutOfRangeException {
        Vektor3D v1 = new Vektor3D(1, 2, 3);

        double lengthBefore = v1.length();
        Vektor3D resV = (Vektor3D) LinAlg.getNormalizeFrom(v1);
        double lengthAfter = resV.length();
        double delta = 0.000000001;
        double x = resV.getX();
        double y = resV.getY();
        double z = resV.getZ();
        assertEquals(x, 1/lengthBefore,delta);
        assertEquals(y, 2/lengthBefore,delta);
        assertEquals(z, 3/lengthBefore,delta);
        assertEquals(1, lengthAfter,delta);
    }

    @Test
    public void equals() {
        Vektor v1 = new Vektor3D(4, 1, -2);
        Vektor v2 = new Vektor3D(4, 1, -2);
        assertTrue(LinAlg.equals(v1, v2));
    }

    @Test
    public void notEquals() {
        Vektor v1 = new Vektor3D(4, 1, -2);
        Vektor v3 = new Vektor3D(3, 2, -2);
        assertTrue(LinAlg.notEquals(v1, v3));
    }

    @Test
    public void show(){
        Vektor v2d = new Vektor2D(37, -21);
        Vektor v3d = new Vektor3D(4, 1, -2);
        LinAlg.show(v2d);
        LinAlg.show(v3d);
    }

    @Test
    public void abs() throws DoublesOutOfRangeException {
        Vektor2D v = new Vektor2D(-37, -21);
        Vektor2D expected = new Vektor2D(37, 21);
        Vektor2D result = (Vektor2D) LinAlg.abs(v);
        assertEquals(expected.getX(),result.getX(),v.getDeltaFloatError());
        assertEquals(expected.getY(),result.getY(),v.getDeltaFloatError());
        assertEquals(-37,v.getX(),v.getDeltaFloatError());
        assertEquals(-21,v.getY(),v.getDeltaFloatError());
    }

    @Test
    public void manhattanDist() throws NotInstanceOfException, DoublesOutOfRangeException {
        Vektor v1 = new Vektor3D(1, 2, 3);
        Vektor v2 = new Vektor3D(2, 4, 6);
        Vektor v3 = new Vektor3D(2, 4, 2);
        double expected1 = 6;
        double resultFalse = LinAlg.manhatDist(v1, v3);
        double resultTrue1 = LinAlg.manhatDist(v1, v2);
        assertNotEquals(expected1, resultFalse, .0000001);
        assertEquals(expected1, resultTrue1, .0000001);
    }

    @Test
    public void manhattanDistBigNegative() throws NotInstanceOfException, DoublesOutOfRangeException {
        Vektor v4 = new Vektor3D(1244,33333,55555);
        Vektor v5 = new Vektor3D(1445, -1244, -1246);
        double expected2 = 91579;
        double resultTrue2 = LinAlg.manhatDist(v4, v5);
        double resultTrue3 = LinAlg.manhatDist(v5, v4);
        assertEquals(expected2, resultTrue2, .0000001);
        assertEquals(expected2, resultTrue3, .0000001);
    }

    @Test
    public void crossProduct3D() throws DoublesOutOfRangeException {
        Vektor3D v1 = new Vektor3D(1244,33333,55555);
        Vektor3D v2 = new Vektor3D(1445, -1244, -1246);
        Vektor3D result = LinAlg.crossProduct(v1,v2);
        Vektor3D expected = new Vektor3D(27577502, 81826999, -49713721);
        assertEquals(expected.getX(),result.getX(),.00000000001);
        assertEquals(expected.getY(),result.getY(),.00000000001);
        assertEquals(expected.getZ(),result.getZ(),.00000000001);
    }

    @Test
    public void crossProduct2D() throws DoublesOutOfRangeException {
        Vektor2D v1 = new Vektor2D(1244,33333);
        Vektor2D v2 = new Vektor2D(1445, -1244);
        Vektor2D v3 = new Vektor2D(1, 2);
        Vektor2D v4 = new Vektor2D(4,5);
        double result12 = LinAlg.crossProduct(v1,v2);
        double result34 = LinAlg.crossProduct(v3,v4);
        double expected12 = -49713721;
        double expected34 = -3;
        assertEquals(expected12,result12,.00000000001);
        assertEquals(expected34,result34,.00000000001);
    }

    @Test
    public void dotProduct() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor3D v1 = new Vektor3D(99,88,-77);
        Vektor3D v2 = new Vektor3D(33, 11, -11);
        double result =  LinAlg.dotProduct(v1,v2);
        double expected = 5082;
        assertEquals(expected,result,0.0000001);
    }

    @Test
    public void sinEquation() throws DoublesOutOfRangeException {
        Vektor3D v1 = new Vektor3D(15,32,8);
        Vektor3D v2 = new Vektor3D(144, 324, 246);

        double expected = 0.37499494;
        double result = LinAlg.sinEquation(v1,v2);

        assertEquals(expected,result,0.001);
    }

    @Test
    public void cosEquation() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor3D v1 = new Vektor3D(15,32,8);
        Vektor3D v2 = new Vektor3D(144, 324, 246);

        double expected = 0.9270290158487304;
        double result = LinAlg.cosEquation(v1,v2);

        assertEquals(expected,result,0.001);
    }

    @Test
    public void angleDegree() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor3D v1 = new Vektor3D(15,32,8);
        Vektor3D v2 = new Vektor3D(144, 324, 246);

        double expected = 22.02367036;
        double result = LinAlg.angleDegree(v1,v2);

        assertEquals(expected,result,0.001);
    }

    @Test
    public void angleRad() throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor3D v1 = new Vektor3D(15,32,8);
        Vektor3D v2 = new Vektor3D(144, 324, 246);

        double expected = 0.38438556;
        double result = LinAlg.angleRad(v1,v2);

        assertEquals(expected,result,0.001);
    }


}