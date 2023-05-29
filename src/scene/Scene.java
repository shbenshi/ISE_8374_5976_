package scene;
import primitives.Color;
import primitives.Double3;
import lighting.AmbientLight;
import geometries.Geometries;
// try upload

public class Scene {
    public String sceneName;
    public Color sceneBack = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight(AmbientLight.NONE, Double3.ZERO);
    public Geometries geometries = new Geometries();

    public Scene(String _sceneName) {
        this.sceneName = _sceneName;
        geometries = new Geometries();
    }

    public Scene setSceneBack(Color _sceneBack) {
        this.sceneBack = _sceneBack;
        return this;
    }

    public Scene setAmbientLight(AmbientLight _sceneAmbient) {
        this.ambientLight= _sceneAmbient;
        return this;

    }

    public Scene setGeometries(Geometries _geometries) {
        this.geometries = _geometries;
        return this;

    }
}
