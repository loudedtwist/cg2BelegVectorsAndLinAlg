package alg;

public class Vektor3D extends Vektor {

    /**
     * @return alg.Vektor3D copy of the object
     */
    @Override
    public Vektor clone() {
        return new Vektor3D(this.getX(), this.getY(), this.getZ());
    }

    private Vektor3D setPosition(int x, int y, int z) {
        super.setPosition(x, y, z);
        return this;
    }

    public Vektor3D(double x, double y, double z) {
        this.setPosition(x, y, z);
    }

    public Vektor3D(Vektor3D vek) {
        this(vek.getY(), vek.getY(), vek.getZ());
    }

    public Vektor3D() {
        this(0, 0, 0);
    }

    public Vektor3D setX(double x) {
        getCoords()[0] = x;
        return this;
    }

    public Vektor3D setY(double y) {
        getCoords()[1] = y;
        return this;
    }

    public Vektor3D setZ(double y) {
        getCoords()[1] = y;
        return this;
    }

    public double getX() {
        return getCoords()[0];
    }

    public double getY() {
        return getCoords()[1];
    }

    public double getZ() {
        return getCoords()[2];
    }


}
