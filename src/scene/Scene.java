package scene;
import primitives.Color;
import primitives.Double3;
import lighting.AmbientLight;
import geometries.Geometries;

/**
 * A class representing a scene in a ray tracing application.
 * It contains the name of the scene, background color, ambient light, and geometries.
 * Provides methods to set and retrieve scene properties.
 * @author Tzofiya David 209918374
 *  Shira Ben Shimol 326065976
 */
public class Scene {
    public String sceneName;
    public Color sceneBack = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight(AmbientLight.NONE, Double3.ZERO);
    public Geometries geometries = new Geometries();

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

    /**
     * Constructs a new Scene object with the specified scene name.
     *
     * @param _sceneName The name of the scene.
     */
    public Scene(String _sceneName) {
        this.sceneName = _sceneName;
        geometries = new Geometries();
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
}
