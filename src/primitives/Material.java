package primitives;


/**
 * The Material class represents the properties of a material used for shading in ray tracing.
 * It defines the diffuse reflection coefficient (kD), specular reflection coefficient (kS),
 * and the shininess factor (nShininess).
 * The values are represented using the Double3 class, which stores three double values (RGB).
 * The class provides methods for setting the values of kD, kS, and nShininess.
 * Authors:
 * shira
 * tzofia
 */
public class Material {
    /** The transmission coefficient for refracted rays */

    public Double3 kT = Double3.ZERO;
    /** The reflection coefficient for reflected rays */
    public Double3 kR = Double3.ZERO;
    /** The diffuse reflection coefficient */
    public Double3 kD=Double3.ZERO;
    /** The specular reflection coefficient */
    public Double3 kS=Double3.ZERO;
    /** The shininess factor */
    public int nShininess =0;

    /**
     * Returns the transmission coefficient for refracted rays.
     *
     * @return The transmission coefficient
     */
    public Double3 getkT() {
        return kT;
    }

    /**
     * Sets the transmission coefficient for refracted rays to the specified value.
     *
     * @param kt The transmission coefficient to set
     * @return The Material object with the updated transmission coefficient
     */
    public Material setKt(double kt) {
        this.kT = new Double3(kt, kt, kt);
        return this;
    }
    /**
     * Sets the transmission coefficient for refracted rays to the specified value.
     *
     * @param kT The transmission coefficient to set
     * @return The Material object with the updated transmission coefficient
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }


    /**
     * Returns the reflection coefficient for reflected rays.
     *
     * @return The reflection coefficient
     */
    public Double3 getKr() {
        return kR;
    }
    /**
     * Sets the reflection coefficient for reflected rays to the specified value.
     *
     * @param kR The reflection coefficient to set
     * @return The Material object with the updated reflection coefficient
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }
    /**
     * Sets the reflection coefficient for reflected rays to the specified value.
     *
     * @param kr The reflection coefficient to set
     * @return The Material object with the updated reflection coefficient
     */
    public Material setKr(double kr) {
        this.kR = new Double3(kr);
        return this;
    }


    /**
     * Sets the diffuse reflection coefficient.
     *
     * @param kD The diffuse reflection coefficient as a Double3 (RGB value)
     * @return The Material object (for method chaining)
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }
    /**
     * Sets the diffuse reflection coefficient with a single value.
     *
     * @param kD The diffuse reflection coefficient as a double value
     * @return The Material object (for method chaining)
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }
    /**
     * Sets the shininess factor.
     *
     * @param nShininess The shininess factor
     * @return The Material object (for method chaining)
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
    /**
     * Sets the specular reflection coefficient.
     *
     * @param kS The specular reflection coefficient as a Double3 (RGB value)
     * @return The Material object (for method chaining)
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }
    /**
     * Sets the specular reflection coefficient with a single value.
     *
     * @param kS The specular reflection coefficient as a double value
     * @return The Material object (for method chaining)
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

}
