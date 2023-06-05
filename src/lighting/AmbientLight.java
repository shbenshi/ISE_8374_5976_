package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * This class represents the ambient light in a scene.
 * It defines the intensity of the ambient light.
 * Authors:
 * Tzofiya David (209918374)
 * Shira Ben Shimol (326065976)
 */

public class AmbientLight extends Light{


    /**
     * A constant representingg no ambient light, with color black.
     */
    public static final Color NONE = Color.BLACK;

    /**
     * A constant representing the ambient coefficient as zero.
     */

 //todo: chanage commit
    /**
     * Constructs an AmbientLight object with the specified intensity and coefficient.
     *
     * @param Ia The intensity of the ambient light.
     * @param Ka The coefficient that affects the ambient light's intensity.
     */
    public AmbientLight(Color Ia, Double3 Ka) {

        super(Ia.scale(Ka));
    }

    public AmbientLight(Color Ia, double Ka) {

        super(Ia.scale(Ka));
    }
//    /**
//     * Returns the intensity of the ambient light.
//     *
//     * @return The intensity of the ambient light.
//     */
//    public Color getIntesity() {
//
//        return intesity;
//    }
}
