package lighting;
import primitives.Color;
import primitives.Vector;
import primitives.Point;


public class SpotLight extends PointLight{
    private Vector direction;


    public SpotLight(Color intensity, Point position, Vector direction) throws IllegalAccessException {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    public Color getIntensity(Point p){
        Color basic=super.getIntensity(p);
        double max=Math.max(0,direction.dotProduct(getL(p)));//maybe normalize needed
        Color IL=basic.scale(max);
        return IL;//all the functions it uses looks fine
    }

}
