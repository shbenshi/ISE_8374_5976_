package lighting;
import primitives.Color;
import primitives.Vector;
import primitives.Point;


public class SpotLight extends PointLight{
    protected double narrowBeam = 1;//default value, so this will not affect the result

    private Vector direction;

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }


    @Override
    public Color getIntensity(Point point) {
        double cos = direction.dotProduct(getL(point));
        return cos > 0 ? super.getIntensity(point).scale(Math.pow(cos, narrowBeam)) : Color.BLACK;
    }


    public PointLight setNarrowBeam(int i) {
        this.narrowBeam = i;
        return this;
    }
/*    private Vector direction;


    public SpotLight(Color intensity, Point position, Vector direction) throws IllegalAccessException {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    public Color getIntensity(Point p){
        Color basic=super.getIntensity(p);
        double max=Math.max(0,direction.dotProduct(getL(p)));//maybe normalize needed
        Color IL=basic.scale(max);
        return IL;//all the functions it uses looks fine
    }*/

}
