public class StartController {

    private SceneManager sceneManager;

    public void pressBtn() {
        sceneManager.goToConfigScene(sceneManager);
    }

    public void setSceneManager(SceneManager sm) {
        sceneManager = sm;
    }


}
