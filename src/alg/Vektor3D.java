package alg;

public class Vektor3D extends Vektor {

    /**
     * @return alg.Vektor3D copy of the object
     */
    @Override
    public Vektor clone() {
        return new Vektor3D(this.getX(), this.getY(),this.getZ());
    }

    private void setPosition(int x , int y, int z) {
        super.setPosition(x,y,z);
    }

    public Vektor3D(double x, double y, double z) {
        this.setPosition(x,y,z);
    }

    public Vektor3D(Vektor3D vek) {
        this(vek.getY(), vek.getY(), vek.getZ());
    }

    public Vektor3D() {
        this(0, 0, 0);
    }

    public double setX(double x) {
        return getCoords()[0] = x;
    }

    public double setY(double y) {
        return getCoords()[1] = y;
    }

    public double setZ(double y) {
        return getCoords()[1] = y;
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
