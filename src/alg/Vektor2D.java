package alg;

public class Vektor2D extends Vektor {

    /**
     * @return alg.Vektor2D copy of the object
     */
    @Override
    public Vektor clone() {
        return new Vektor2D(this.getX(), this.getY());
    }

    private void setPosition(int x, int y) {
        super.setPosition(x, y);
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

    public double setX(double x) {
        return getCoords()[0] = x;
    }

    public double setY(double y) {
        return getCoords()[1] = y;
    }

    public double getX() {
        return getCoords()[0];
    }

    public double getY() {
        return getCoords()[1];
    }
}
