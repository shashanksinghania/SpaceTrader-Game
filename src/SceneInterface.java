import javafx.scene.Scene;

/**
 * This Interface is implemented by any class that generates a Scene to
 * standardize how all the Scenes in the game are designed.
 *
 * @author Sarah Cheah
 * @version 1.0
 */

interface SceneInterface {
    /**
     * @param width  of the Scene
     * @param height of the Scene
     * @return Scene with the specified characteristics
     */
    Scene init(int width, int height);
}
