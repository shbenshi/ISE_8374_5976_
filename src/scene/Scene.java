package scene;
import primitives.Color;
import primitives.Double3;
import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;

import java.util.LinkedList;
import java.util.List;

/**
 * A class representing a scene in a ray tracing application.
 * It contains the name of the scene, background color, ambient light, and geometries.
 * Provides methods to set and retrieve scene properties.
 * @author Tzofiya David 209918374
 *  Shira Ben Shimol 326065976
 */
public class Scene {

    private String sceneName;
    private Color sceneBack = Color.BLACK;
    private AmbientLight ambientLight = new AmbientLight(AmbientLight.NONE, Double3.ZERO);
    private Geometries geometries = new Geometries();
    private List<LightSource> lights=new LinkedList<>();

    public String getSceneName() {
        return sceneName;
    }

    public Color getSceneBack() {
        return sceneBack;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public List<LightSource> getLights() {
        return lights;
    }

    private Scene(SceneBuilder builder) {
        this.sceneName = builder.sceneName;
        this.ambientLight = builder.ambientLight;
        this.sceneBack = builder.sceneBack;
        this.geometries = builder.geometries;
        this.lights = builder.lights;
    }


    /**
     * Sets the background color of the scene.
     *
     * @param _sceneBack The background color to set.
     * @return The current Scene object for method chaining.
     */
    public Scene setSceneBack(Color _sceneBack) {
        this.sceneBack = _sceneBack;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param _sceneAmbient The ambient light to set.
     * @return The current Scene object for method chaining.
     */
    public Scene setAmbientLight(AmbientLight _sceneAmbient) {
        this.ambientLight= _sceneAmbient;
        return this;

    }

    /**
     * Sets the geometries of the scene.
     *
     * @param _geometries The geometries to set.
     * @return The current Scene object for method chaining.
     */
    public Scene setGeometries(Geometries _geometries) {
        this.geometries = _geometries;
        return this;

    }


    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
    public static class SceneBuilder {
        private final String sceneName;
        private Color sceneBack = Color.BLACK;
        private List<LightSource> lights = new LinkedList<>();
        private Geometries geometries = new Geometries();
        private AmbientLight ambientLight = new AmbientLight(AmbientLight.NONE, Double3.ZERO);

        public SceneBuilder(String _sceneName) {
            this.sceneName = _sceneName;
        }


        public SceneBuilder setLights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }

        public SceneBuilder setBackground(Color _sceneBack) {
            this.sceneBack = _sceneBack;

            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public Scene build() {
            return new Scene(this);
        }
    }



}
