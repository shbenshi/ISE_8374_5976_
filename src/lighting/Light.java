package lighting;

import primitives.Color;

public abstract  class Light {
    private final Color intensity;

    /**
     * Constructs a light with the given intensity.
     *
     * @param _intensity The intensity of the light.
     */
    protected Light(Color _intensity) {
        this.intensity = _intensity;
    }

    /**
     * Retrieves the intensity of the light.
     *
     * @return The intensity of the light.
     */
    public Color getIntensity() {
        return intensity;
    }
}
