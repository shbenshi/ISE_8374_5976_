package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        return getIntensity();// same intensity for all points
    }

    @Override
    public Vector getL(Point p) {
        return null;
    }

    @Override
    public double getDistance(Point point) {
        return 0;
    }


}
