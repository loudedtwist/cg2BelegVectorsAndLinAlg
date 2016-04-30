package alg;

import exceptions.DoublesOutOfRangeException;
import exceptions.NotInstanceOfException;

public class LinAlg {

    private LinAlg(){}

    public static Vektor add(Vektor v1, Vektor v2) throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor result = v1.clone();
        result.add(v2);
        return result;
    }

    public static Vektor sub(Vektor v1, Vektor v2) throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor result = v1.clone();
        result.sub(v2);
        return result;
    }

    public static Vektor mult(Vektor v1, double d) throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor result = v1.clone();
        result.mult(d);
        return result;
    }

    public static Vektor mult(double d, Vektor v1) throws DoublesOutOfRangeException, NotInstanceOfException {
        return mult(v1,d);
    }

    public static Vektor div(Vektor v1, double d) throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor result = v1.clone();
        result.div(d);
        return result;
    }

    public static Vektor div(double d, Vektor v1) throws DoublesOutOfRangeException, NotInstanceOfException {
        return div(v1,d);
    }

    public static double length(Vektor v ) throws DoublesOutOfRangeException {
        return v.clone().length();
    }

    public static Vektor multTwoVectors(Vektor v1, Vektor v2) throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor result = v1.clone();
        result.multComponentwise(v2);
        return result;
    }

    public static Vektor divTwoVectors(Vektor v1, Vektor v2) throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor result = v1.clone();
        result.divComponentwise(v2);
        return result;
    }

    public static double euclDist(Vektor v1, Vektor v2) throws NotInstanceOfException, DoublesOutOfRangeException {
        return LinAlg.sub(v1, v2).length();
    }

    public static double manhatDist(Vektor v1, Vektor v2) throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor v = LinAlg.sub(v2, v1);
        double distance = 0;
        for (double d : v.getCoords()) {
            distance += Math.abs(d);
        }
        return distance;
    }

    //returns normalized alg.Vektor,
    //arg alg.Vektor will not be changed
    public static Vektor getNormalizeFrom(final Vektor v) throws DoublesOutOfRangeException {
        Vektor result = v.clone();
        result.normalize();
        return result;
    }

    public static double radToDegree(double rad) throws DoublesOutOfRangeException {
        double divPiBy180 = Math.PI / 180;
        return Vektor.multTwoDoubles(rad, divPiBy180);
    }

    public static double degreeToRad(double degree) throws DoublesOutOfRangeException {
        double div180ByPi = 180 / Math.PI;
        return Vektor.multTwoDoubles(degree, div180ByPi);
    }

    public static boolean equals(Vektor v1, Vektor v2) {
        return v1.equals(v2);
    }

    public static boolean notEquals(Vektor v1, Vektor v2) {
        return v1.notEquals(v2);
    }

    public static void show(Vektor v) {
        System.out.println(v.toString());
    }

    public static Vektor abs(Vektor v) throws DoublesOutOfRangeException {
        Vektor result = v.clone();
        result.abs();
        return result;
    }

    public static double dotProduct(Vektor v1, Vektor v2) throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor v = LinAlg.multTwoVectors(v1, v2);
        return v.sumOfAllCoords();
    }

    public static Vektor3D crossProduct(Vektor3D v1, Vektor3D v2) throws DoublesOutOfRangeException {
        double x = Vektor.subTwoDoubles(
                Vektor.multTwoDoubles(v1.getY(), v2.getZ()),
                Vektor.multTwoDoubles(v1.getZ(), v2.getY()));
        double y = Vektor.subTwoDoubles(
                Vektor.multTwoDoubles(v1.getZ(), v2.getX()),
                Vektor.multTwoDoubles(v1.getX(), v2.getZ()));
        double z = Vektor.subTwoDoubles(
                Vektor.multTwoDoubles(v1.getX(), v2.getY()),
                Vektor.multTwoDoubles(v1.getY(), v2.getX()));
        return new Vektor3D(x, y, z);
    }

    public static double crossProduct(Vektor2D v1, Vektor2D v2) throws DoublesOutOfRangeException {
        Vektor3D reuslt = crossProduct(
                new Vektor3D(v1.getX(), v1.getY(), 0),
                new Vektor3D(v2.getX(), v2.getY(), 0)
        );
        return reuslt.getZ();
    }

    /*
       v1       v2       v3
    a_{11} & a_{12} & a_{13}
    a_{21} & a_{22} & a_{23}
    a_{31} & a_{32} & a_{33}
    */
    public static double determinante(Vektor3D v1, Vektor3D v2, Vektor3D v3) throws DoublesOutOfRangeException {
        Vektor3D a11a22a33 = new Vektor3D(v1.getX(), v2.getY(), v3.getZ());
        Vektor3D a12a23a31 = new Vektor3D(v2.getX(), v3.getY(), v1.getZ());
        Vektor3D a13a21a32 = new Vektor3D(v3.getX(), v1.getY(), v2.getZ());
        Vektor3D a13a22a31 = new Vektor3D(v3.getX(), v2.getY(), v1.getZ());
        Vektor3D a12a21a33 = new Vektor3D(v2.getX(), v1.getY(), v3.getZ());
        Vektor3D a11a23a32 = new Vektor3D(v1.getX(), v3.getY(), v2.getZ());
        return a11a22a33.productOfallCoordinats() + a12a23a31.productOfallCoordinats()
                + a13a21a32.productOfallCoordinats() - a13a22a31.productOfallCoordinats()
                - a12a21a33.productOfallCoordinats() - a11a23a32.productOfallCoordinats();
    }

    public static double determinante(Vektor2D v1, Vektor2D v2) throws DoublesOutOfRangeException {
        Vektor2D a11a22 = new Vektor2D(v1.getX(), v2.getY());
        Vektor2D a12a21 = new Vektor2D(v2.getX(), v1.getY());
        return a11a22.productOfallCoordinats() - a12a21.productOfallCoordinats();
    }

    public static double sinEquation(Vektor3D v1, Vektor3D v2) throws DoublesOutOfRangeException {
        Vektor3D crossProd = LinAlg.crossProduct(v1, v2);
        return Vektor.divTwoDoubles(
                crossProd.length(),
                Vektor.multTwoDoubles(v1.length(), v2.length())
        );
    }

    public static double sinEquation(Vektor2D v1, Vektor2D v2) throws DoublesOutOfRangeException {
        double crossProd = LinAlg.crossProduct(v1, v2);
        return Vektor.divTwoDoubles(
                crossProd,
                Vektor.multTwoDoubles(v1.length(), v2.length())
        );
    }

    /*cosθ = (u⃗ · v⃗) / (||u⃗|| ||v ⃗||)
            ||u⃗|| means "the length of vector u⃗."
    u⃗ · v⃗ is the dot productOfallCoordinats (scalar productOfallCoordinats) of the two vectors, explained below.*/
    //cos∆ = ( v1 * v2 ) / ( ||v1||*||v2|| )
    public static double cosEquation(Vektor v1, Vektor v2) throws DoublesOutOfRangeException, NotInstanceOfException {
        Vektor vWorker1  = LinAlg.getNormalizeFrom(v1);
        Vektor vWorker2  = LinAlg.getNormalizeFrom(v2);
        return LinAlg.dotProduct(vWorker1,vWorker2);
    }

    public static double angleRad(Vektor v1, Vektor v2) throws DoublesOutOfRangeException, NotInstanceOfException {
        double cos  = cosEquation(v1,v2);
        return Math.acos(cos);
    }

    public static double angleDegree(Vektor v1, Vektor v2) throws DoublesOutOfRangeException, NotInstanceOfException {
        double rad  = angleRad(v1,v2);
        return degreeToRad(rad);
    }
}
