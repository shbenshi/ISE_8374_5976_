package lighting;
import primitives.Color;
import primitives.Vector;
import primitives.Point;


public class SpotLight extends PointLight {
    protected double narrowBeam = 1; // Does not affect the result

    private Vector direction;

    /**
     * Constructs a SpotLight object with the specified intensity, position, and direction.
     *
     * @param intensity The intensity of the light.
     * @param position  The position of the light source.
     * @param direction The direction of the spotlight.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Calculates the intensity of the light at a given point.
     *
     * @param point The point at which to calculate the intensity.
     * @return The intensity of the light at the given point.
     */
    @Override
    public Color getIntensity(Point point) {
        double cos = direction.dotProduct(getL(point));
        return cos > 0 ? super.getIntensity(point).scale(Math.pow(cos, narrowBeam)) : Color.BLACK;
    }

    /**
     * Sets the narrow beam value for the spotlight.
     *
     * @param narrowBeam The narrow beam value to set.
     * @return The SpotLight object with the updated narrow beam value.
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }
}

