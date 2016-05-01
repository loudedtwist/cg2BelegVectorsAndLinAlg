package alg;

import exceptions.DoublesOutOfRangeException;
import exceptions.NotInstanceOfException;
//lange beschneiden auf bistimte länge 12 auf 10
//länge hoch zwei, ohne sqrt um abstand zu vergleichen
//klassen diagramm, entscheidung eklärrung, einpaar funktionen
//In Euclidean space, a vector is a geometrical object that possesses both a magnitude and a direction.

/**
 * Mein Klassendiagram sieht so aus:
 * Es gibt eine abstakte Klasse alg.Vektor, von der folgende Klassen abgeleitet werden: alg.Vektor2D, alg.Vektor3D
 * Ich habe mich für eine Oberklasse entschieden, weil es viele funktionen gibt, die für alle Vektoren belibiger Demension algemeingültig sind.
 * Es ist auch offensichtlich, dass jede abgeleitete von alg.Vektor Klasse , ein array aus Koordinaten hat (nach Demension unterschiedlich:
 * alg.Vektor2D : coords[2], alg.Vektor3D: coords[3] und so weiter) und (@member deltaByFloatCompartment ) ein Deltawert für den Floatvergleich hat.
 * Dank der Architektur kann man von der Klasse verschiedene Vektoren ableiten, die dann gleich alle benötigte Funktionen
 * zu verfügung haben.
 *
 * Das ist möglich, da die Funktionen add, sub, mult, div, equals, notEquals, toString, isNullVektor, length, normalize, setPosition
 * algemeingültig definiert werden können.
 *
 *
 *
 *
 */
abstract public class Vektor implements Cloneable {

    private double coords[];

    private double deltaByFloatCompartment = 0.0000001;

    abstract public Vektor clone();

    protected double[] getCoords() {
        return coords;
    }

    public void setDeltaByFloatCompartment(double deltaByFloatCompartment) {
        this.deltaByFloatCompartment = deltaByFloatCompartment;
    }

    public double getDeltaByFloatCompartment() {
        return this.deltaByFloatCompartment;
    }

    public void setPosition(double... params) {
        this.coords = params;
    }

    /**
     * Adds an generic alg.Vektor to a alg.Vektor of the same inherit class. Works with all possible Vectors.
     * Example:
     * this:{ 3, 5 , 2} + v1:{ 2 , 3 , 4 }  = this{ 5 , 8 , 6 }
     *
     * @param summand inherit from alg.Vektor class
     *
     * @throws DoublesOutOfRangeException by (+,-)Double.MaxValue overflow
     * @throws NotInstanceOfException if class of one vector is not assignable from another vector
     */
    public void add(Vektor summand) throws DoublesOutOfRangeException, NotInstanceOfException {
        if (coords.length != summand.getCoords().length) throw new NotInstanceOfException("Expected: " + this.getClass());
        for (int i = 0; i < coords.length; i++) {
            coords[i] = addTwoDoubles(coords[i], summand.getCoords()[i]);
        }
    }

    /**
     * Subtracts an generic alg.Vektor from a alg.Vektor of the same inherit class. Works with all possible Vectors.
     * Example:
     * this:{ 3, 5 , 2} + v1:{ 2 , 3 , 4 }  = this{ 1 , 2 , -2 }
     *
     * @param subtrahend inherit from alg.Vektor class
     *
     * @throws DoublesOutOfRangeException by (+,-)Double.MaxValue overflow
     * @throws NotInstanceOfException if class of one vector is not assignable from another vector
     */
    public void sub(Vektor subtrahend) throws DoublesOutOfRangeException, NotInstanceOfException {
        double[] summand = subtrahend.getCoords();
        if (coords.length != summand.length) throw new NotInstanceOfException("Expected: " + this.getClass());
        for (int i = 0; i < coords.length; i++) {
            coords[i] = subTwoDoubles(coords[i], subtrahend.getCoords()[i]);
        }
    }

    /**
     * Multiplies each component of the alg.Vektor by @param multiplicand
     *
     * @param  multiplicand (double)
     * @throws DoublesOutOfRangeException by (+,-)Double.MaxValue overflow
     */
    public void mult(double multiplicand) throws DoublesOutOfRangeException{
        for (int i = 0; i < coords.length; i++) {
            coords[i] = multTwoDoubles(coords[i], multiplicand);
        }
    }

    //double as param
    public void div(double d) throws DoublesOutOfRangeException{
        for (int i = 0; i < coords.length; i++) {
            coords[i] = divTwoDoubles(coords[i], d);
        }
    }

    public void multComponentwise(Vektor v) throws DoublesOutOfRangeException, NotInstanceOfException {
        if (coords.length != v.getCoords().length) throw new NotInstanceOfException("Expected: " + this.getClass());
        for (int i = 0; i < coords.length; i++) {
            coords[i] = multTwoDoubles(coords[i], v.getCoords()[i]);
        }
    }

    public void divComponentwise(Vektor v) throws DoublesOutOfRangeException, NotInstanceOfException {
        if (coords.length != v.getCoords().length) throw new NotInstanceOfException("Expected: " + this.getClass());
        for (int i = 0; i < coords.length; i++) {
            coords[i] = divTwoDoubles(coords[i], v.getCoords()[i]);
        }
    }

    public static double addTwoDoubles(double a, double b) throws DoublesOutOfRangeException {
        if ((a > 0) && (b > Double.MAX_VALUE - a)) {
            throw new DoublesOutOfRangeException("DOUBLES ARE TOO BIG");
        } else if ((a < 0) && (b < (-Double.MAX_VALUE) - a)) {
            throw new DoublesOutOfRangeException("DOUBLES ARE TOO SMALL");
        }
        return a + b;
    }

    static public double subTwoDoubles(double minuend, double subtrahend) throws DoublesOutOfRangeException {
        if ((minuend > 0) && ((subtrahend * -1) > (Double.MAX_VALUE - minuend))) {
            throw new DoublesOutOfRangeException("SUBTRAHEND IS TOO BIG(NEGATIVE)");
        } else if ((minuend < 0) && (subtrahend > Double.MAX_VALUE + minuend)) {
            throw new DoublesOutOfRangeException("SUBTRAHEND IS TOO BIG(POSITIVE)");
        }
        return minuend - subtrahend;
    }

    static public double multTwoDoubles(double d1, double d2) throws DoublesOutOfRangeException {
        if ( (d1!=0) && Math.abs(d2) > Math.abs(Double.MAX_VALUE / d1)) {
            throw new DoublesOutOfRangeException("DOUBLES ARE TOO BIG");
        }
        if (d1*d2 == Double.NEGATIVE_INFINITY || d1*d2 == Double.POSITIVE_INFINITY) {
            throw new DoublesOutOfRangeException("INFINITY");
        }
        return d1 * d2;
    }

    static public double divTwoDoubles(double d1, double d2) throws IllegalArgumentException {
        if ( d2 == 0) {
            throw new IllegalArgumentException("DIVISOR IS 0");
        } else if ((Math.abs(d2) < 1) && Math.abs(d1 / Double.MAX_VALUE) > Math.abs(d2)) {
            throw new IllegalArgumentException("DIVISOR IS TO SMALL (-1 < D < 1)");
        }
        return d1 / d2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;

        if (!getClass().isAssignableFrom(obj.getClass())) return false;
        Vektor p = (Vektor) obj;
        double[] deltaArray = new double[p.getCoords().length];

        for (int i = 0; i < deltaArray.length; i++) {
            try {
                deltaArray[i] = Math.abs(Vektor.subTwoDoubles(getCoords()[i], p.getCoords()[i]));
                if (deltaArray[i] > deltaByFloatCompartment) return false;
            } catch (DoublesOutOfRangeException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean notEquals(Vektor obj) {
        return !this.equals(obj);
    }

    @Override
    public String toString() {
        String string = this.getClass() + " : { ";
        for (double d : coords)
            string += d + ", ";
        string = string.subSequence(0, string.length() - 2).toString();
        string += " }";
        return string;
    }

    public boolean isNullVektor() {
        for (double coord : coords) {
            if (coord != 0)
                return false;
        }
        return true;
    }

    public void abs(){
        for(int coord = 0; coord< coords.length; coord++){
            coords[coord] = Math.abs(coords[coord]);
        }
    }

    public double length() throws DoublesOutOfRangeException {
        double result = 0;
        for (double coord : coords) {
            result = Vektor.addTwoDoubles(result, Vektor.multTwoDoubles(coord, coord));
        }
        return Math.sqrt(result);
    }

    public double lengthExp2() throws DoublesOutOfRangeException {
        double result = 0;
        for (double coord : coords) {
            result = Vektor.addTwoDoubles(result, Vektor.multTwoDoubles(coord, coord));
        }
        return result;
    }

    public double sumOfAllCoords() throws DoublesOutOfRangeException {
        double result = 0;
        for (double coord : coords) {
            result = Vektor.addTwoDoubles(result, coord);
        }
        return result;
    }

    //return productOfallCoordinats of all coordinates , example: (4,2,3) -> 4*2*3 = 24
    public double productOfallCoordinats() throws DoublesOutOfRangeException {
        double result = 1;
        for (double coord : coords) {
            result = Vektor.multTwoDoubles(result, coord);
        }
        return result;
    }

    public void normalize() throws IllegalArgumentException, DoublesOutOfRangeException {
        double length = length();
        for (int i = 0; i < coords.length; i++) {
            coords[i] = Vektor.divTwoDoubles(coords[i], length);
        }
    }
}