package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import java.util.List;

public class DirectionalLight extends Light implements LightSource{
    private final Vector direction;

    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }


    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }


}
