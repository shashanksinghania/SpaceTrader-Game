import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

/**
 * This class manages the transitions between scenes.
 */
public class SceneManager {
    /**
     * The constant stage from main.
     */
    private Stage stage;

    private Scene universe;

    private TreeMap<String, List<List<Item>>> map;

    /**
     * Creates a SceneManager object.
     *
     * @param primaryStage to set scenes on
     */
    public SceneManager(Stage primaryStage) {
        this.stage = primaryStage;
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getUniverse() {
        return universe;
    }

    /**
     * Go to start scene.
     *
     * @param sceneManager the scene manager
     */
    public void goToStartScene(SceneManager sceneManager) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("startScreen.fxml"));
            root = loader.load();
            StartController controller = loader.getController();
            controller.setSceneManager(sceneManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene startScene = new Scene(root, 800, 480);
        stage.setScene(startScene);
    }

    /**
     * Go to config scene.
     *
     * @param sceneManager the scene manager
     */
    public void goToConfigScene(SceneManager sceneManager) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("config.fxml"));
            root = loader.load();
            ConfigController controller = loader.getController();
            controller.setSceneManager(sceneManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene configScene = new Scene(root, 800, 480);
        stage.setScene(configScene);
    }

    /**
     * Go to char sheet.
     *
     * @param sceneManager the scene manager
     * @param player       the player
     */
    public void goToCharSheet(SceneManager sceneManager, Player player) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("charSheet.fxml"));
            root = loader.load();
            CharSheetController controller = loader.<CharSheetController>getController();
            controller.setSceneManager(sceneManager);
            controller.setPlayer(player);
            controller.populateTexts();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene charSheetScene = new Scene(root, 800, 480);
        stage.setScene(charSheetScene);
    }

    public void goToUpgradesView(SceneManager sceneManager, Player player,
                                 Region currentRegion, List<Region> regions) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("upgrades.fxml"));
            root = loader.load();
            UpgradesController controller = loader.<UpgradesController>getController();
            controller.setSceneManager(sceneManager);
            controller.setPlayer(player);
            controller.setVisitingRegion(currentRegion);
            controller.setRegions(regions);
            controller.setMap(map);
            controller.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene upgradesScene = new Scene(root, 800, 480);
        stage.setScene(upgradesScene);
    }

    public void goBackToUniverseView(SceneManager sceneManager,
                                     TreeMap<String, List<List<Item>>> map) {
        Parent root = null;
        this.map = map;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("universe.fxml"));
            root = loader.load();
            UniverseController controller = loader.<UniverseController>getController();
            controller.setSceneManager(sceneManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(universe);
    }

    public void goToUniverseView(SceneManager sceneManager, Player player,
                                 Region visitingRegion, List<Region> regions) {
        Parent root = null;
        map = new TreeMap<>();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("universe.fxml"));
            root = loader.load();
            UniverseController controller = loader.getController();
            controller.setSceneManager(sceneManager);
            controller.setPlayer(player);
            if (visitingRegion == null) {
                controller.initRegions();
                controller.randomCoordinates(0, 370, 0, 300);
            } else {
                controller.setVisitingRegion(visitingRegion);
                controller.setRegions(regions);
            }
            controller.addRegions(visitingRegion == null);
            controller.createPlayerShip();
            universe = new Scene(root, 800, 480);
            stage.setScene(universe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToMarketPlace(SceneManager sceneManager,
                                Region region, Player player, Ship ship) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("marketplace.fxml"));
            root = loader.load();
            MarketController controller = loader.getController();
            controller.setPlayer(player);
            controller.setShip(ship);
            controller.setRegion(region, map);
            controller.setSceneManager(sceneManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene startScene = new Scene(root, 800, 480);
        stage.setScene(startScene);
    }

    public void goToEndCredits(SceneManager sceneManager, Player player) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("endCredits.fxml"));
            root = loader.load();
            EndCreditsController controller = loader.getController();
            controller.setPlayer(player);
            controller.setSceneManager(sceneManager);
            controller.addFeatures();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene startScene = new Scene(root, 800, 480);
        stage.setScene(startScene);
    }

    public void goToGameOverScreen(SceneManager sceneManager, Player player, String status) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
            root = loader.load();
            GameOverController controller = loader.getController();
            controller.setPlayer(player);
            controller.setStatus(status);
            controller.setSceneManager(sceneManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene startScene = new Scene(root, 800, 480);
        stage.setScene(startScene);
    }
}
