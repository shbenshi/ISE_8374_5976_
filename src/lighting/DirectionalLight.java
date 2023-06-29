package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    private final Vector direction;


    /**
     * Constructs a directional light with the given intensity and direction.
     *
     * @param intensity The intensity of the light.
     * @param direction The direction of the light.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * Represents a directional light source that emits light in a specific direction.
     */
    @Override
    public Color getIntensity(Point point) {
        // The intensity of a directional light is constant regardless of the point
        return getIntensity();
    }
    /**
     * Calculates and returns the direction from the given point to the directional light source.
     *
     * @param p The point from which to calculate the direction
     * @return The normalized direction vector pointing towards the light source
     */
    @Override
    public Vector getL(Point p) {
        // The direction to the light is constant for a directional light
        return direction.normalize();
    }
    /**
     * Retrieves the distance from the given point to the directional light source.
     *
     * @param point The point for which to calculate the distance
     * @return The distance to the light source, which is considered infinite for a directional light
     */
    @Override
    public double getDistance(Point point) {
        // For a directional light, the distance is considered infinite
        return Double.POSITIVE_INFINITY;
    }

}
