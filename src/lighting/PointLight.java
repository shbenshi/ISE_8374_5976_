package lighting;

import primitives.Point;
import primitives.Vector;
import primitives.Color;



public class PointLight extends Light implements LightSource{

    private Point position;
    private double Kc = 1;
    private double Kl = 0;
    private double Kq = 0;

    /**
     * Calculates the distance between the light source and a given point.
     *
     * @param point The point for which to calculate the distance.
     * @return The distance between the light source and the given point.
     */
    @Override
    public double getDistance(Point point) {
        return point.distance(this.position);
    }
    /**
     * Calculates the direction vector from the light source to a given point.
     *
     * @param p The point for which to calculate the direction vector.
     * @return The direction vector from the light source to the given point.
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();

    }
    /**
     * Calculates the intensity of the light at a given point.
     *
     * @param p The point at which to calculate the intensity.
     * @return The intensity of the light at the given point.
     */
    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        double denominator = Kc + Kl * d + Kq * d * d;
        return getIntensity().reduce(denominator);

    }
    /**
     * Sets the quadratic attenuation coefficient (Kq) for the light source.
     *
     * @param kq The quadratic attenuation coefficient to set.
     * @return The PointLight object with the updated quadratic attenuation coefficient.
     */
    public PointLight setKq(double kq) {
        this.Kq = kq;
        return this;
    }

    /**
     * Sets the linear attenuation coefficient (Kl) for the light source.
     *
     * @param kl The linear attenuation coefficient to set.
     * @return The PointLight object with the updated linear attenuation coefficient.
     */
    public PointLight setKl(double kl) {
        this.Kl = kl;
        return this;
    }

    /**
     * Sets the constant attenuation coefficient (Kc) for the light source.
     *
     * @param kc The constant attenuation coefficient to set.
     * @return The PointLight object with the updated constant attenuation coefficient.
     */
    public PointLight setKc(double kc) {
        this.Kc = kc;
        return this;
    }

    /**
     * Constructs a PointLight object with the specified intensity and position.
     *
     * @param intensity The intensity of the light.
     * @param _position  The position of the light source.
     */
    public PointLight(Color intensity,Point _position)
    {
        super(intensity);
        this.position = _position;
    }


}
