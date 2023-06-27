package lighting;

import primitives.Color;
import primitives.Vector;
import primitives.Point;


public interface LightSource {
    /**
     * Calculates the intensity of the light at a given point.
     *
     * @param p The point at which to calculate the intensity.
     * @return The intensity of the light at the given point.
     */
    public Color getIntensity(Point p);
    /**
     * Calculates the direction vector from the light source to a given point.
     *
     * @param p The point for which to calculate the direction vector.
     * @return The direction vector from the light source to the given point.
     */
    public Vector getL(Point p);

    /**
     * Calculates the distance between the light source and a given point.
     *
     * @param point The point for which to calculate the distance.
     * @return The distance between the light source and the given point.
     */
    double getDistance(Point point);
}
