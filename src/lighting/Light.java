package lighting;

import primitives.Color;

public abstract  class Light {
    private final Color intensity;

    protected Light(Color _intensity) {
        this.intensity = _intensity;
    }

    public Color getIntensity() {
        return intensity;
    }
}
