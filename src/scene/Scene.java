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

    /**
     * Sets the list of light sources in the scene.
     *
     * @param lights The list of light sources to set.
     * @return The current Scene object for method chaining.
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
    /**
     * A builder class for creating Scene objects.
     * Allows for fluent construction of Scene objects with different properties.
     */
     public static class SceneBuilder {
        private final String sceneName;
        private Color sceneBack = Color.BLACK;
        private List<LightSource> lights = new LinkedList<>();
        private Geometries geometries = new Geometries();
        private AmbientLight ambientLight = new AmbientLight(AmbientLight.NONE, Double3.ZERO);

        /**
         * Constructs a SceneBuilder object with the specified scene name.
         *
         * @param _sceneName The name of the scene.
         */
        public SceneBuilder(String _sceneName) {
            this.sceneName = _sceneName;
        }

        /**
         * Sets the list of light sources in the scene.
         *
         * @param lights The list of light sources to set.
         * @return The current SceneBuilder object for method chaining.
         */
        public SceneBuilder setLights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }

        /**
         * Sets the background color of the scene.
         *
         * @param _sceneBack The background color to set.
         * @return The current SceneBuilder object for method chaining.
         */

        public SceneBuilder setBackground(Color _sceneBack) {
            this.sceneBack = _sceneBack;

            return this;
        }
        /**
         * Sets the geometries of the scene.
         *
         * @param geometries The geometries to set.
         * @return The current SceneBuilder object for method chaining.
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        /**
         * Sets the ambient light of the scene.
         *
         * @param ambientLight The ambient light to set.
         * @return The current SceneBuilder object for method chaining.
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }
        /**
         * Constructs a Scene object with the set properties.
         *
         * @return The constructed Scene object.
         */

        public Scene build() {
            return new Scene(this);
        }
    }



}
