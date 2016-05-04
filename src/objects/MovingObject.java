package objects;

import alg.Vektor2D;

abstract public class MovingObject extends BasicObject {
    protected double maxSpeed;
    protected double maxRotationDeegry;

    protected Vektor2D ahead;
    protected Vektor2D velocity;
    protected Vektor2D acceleration;
    protected Vektor2D perpendicular;

    public Vektor2D getVelocity() {
        return velocity;
    }

    public Vektor2D getAcceleration() {
        return acceleration;
    }

    public double getMaxRotationDeegry() {
        return maxRotationDeegry;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setVelocity(Vektor2D velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Vektor2D acceleration) {
        this.acceleration = acceleration;
    }

    public void setMaxRotationDeegry(double maxRotationDeegry) {
        this.maxRotationDeegry = maxRotationDeegry;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public MovingObject() {
        super();
        initMembers(1);
    }

    public MovingObject(float maxSpeed) {
        this(new Vektor2D(0,0), maxSpeed);
    }

    public MovingObject(Vektor2D pos, double maxSpeed) {
        super(pos.getX(), pos.getY());
        initMembers(maxSpeed);
    }

    private void initMembers(double maxSpeed) {
        this.maxSpeed = maxSpeed;
        this.maxRotationDeegry = 0.05;
        ahead = new Vektor2D(0,0);
        velocity = new Vektor2D(0,0);
        acceleration = new Vektor2D(0,0);
    }

}
