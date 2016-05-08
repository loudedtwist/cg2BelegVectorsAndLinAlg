package alg;

public class Vektor2D extends Vektor {

    /**
     * @return alg.Vektor2D copy of the object
     */
    @Override
    public Vektor clone() {
        return new Vektor2D(this.getX(), this.getY());
    }

    private Vektor2D setPosition(int x, int y) {
        super.setPosition(x, y);
        return this;
    }

    public Vektor2D() {
        this(0, 0);
    }

    public Vektor2D(double x, double y) {
        this.setPosition(x, y);
    }

    public Vektor2D(Vektor2D vek) {
        this(vek.getY(), vek.getY());
    }

    public Vektor2D setX(double x) {
        getCoords()[0] = x;
        return this;
    }

    public Vektor2D setY(double y) {
        getCoords()[1] = y;
        return this;
    }

    public double getX() {
        return getCoords()[0];
    }

    public double getY() {
        return getCoords()[1];
    }
}
