package lighting;

import primitives.Color;
import primitives.Vector;
import primitives.Point;


public interface LightSource {
    public Color getIntensity(Point p);
    public Vector getL(Point p);

    double getDistance(Point point);
}
