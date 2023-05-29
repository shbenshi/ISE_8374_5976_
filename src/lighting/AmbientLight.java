package lighting;

import primitives.Color;
import primitives.Double3;


public class AmbientLight {
    private Color intesity;

    //private Double3 KA;
    public static final Color NONE = Color.BLACK;
    public static final Double3 KA = Double3.ZERO;

    public AmbientLight(Color Ia, Double3 Ka) {
        this.intesity = Ia.scale(Ka);
    }

    public Color getIntesity() {
        return intesity;
    }
}
