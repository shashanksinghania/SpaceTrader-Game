import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The type End credits controller.
 */
public class EndCreditsController {
    /**
     * The Scene manager.
     */
    private SceneManager sceneManager;

    /**
     * The Player.
     */
    private Player player;

    /**
     * The Credits title.
     */
    @FXML
    private Text title;

    /**
     * The Credits.
     */
    @FXML
    private Text credits;

    /**
     * The Btn control.
     */
    @FXML
    private Button btnStartNewGame;

    /**
     * Go to next scene.
     */
    @FXML
    private void goToStartScreen() {
        sceneManager.goToStartScene(sceneManager);
    }

    /**
     * Sets scene manager.
     *
     * @param sceneManager the scene manager
     */
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Add features.
     */
    public void addFeatures() {
        title.setText("\nCredits:");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 40));
        title.setFill(Color.YELLOW);
        credits.setText("\nDavid Burns\nEleanor Sim\nRochan Madhusudhana\n"
                + "Sarah Cheah\nShashank Singhania\n\n");
        credits.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        credits.setFill(Color.WHITE);
    }
}