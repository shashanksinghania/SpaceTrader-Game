import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Map;

/**
 * This class represents the Character Sheet Screen which displays the
 * character's attributes and the starting number of credits.
 */
public class CharSheetController {
    /**
     * The Scene manager.
     */
    private SceneManager sceneManager;
    /**
     * The Char scene.
     */
    private Scene charScene;
    /**
     * The Player.
     */
    private Player player;

    /**
     * The Btn control.
     */
    private Button btnControl;


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
     * The Name.
     */
    @FXML
    private Text name;
    /**
     * The Skill points.
     */
    @FXML
    private Text skillPoints;
    /**
     * The Allocations.
     */
    @FXML
    private Text allocations;
    /**
     * The Credits.
     */
    @FXML
    private Text credits;
    /**
     * The Difficulty.
     */
    @FXML
    private Text difficulty;

    /**
     * Go to next scene.
     */
    @FXML
    private void goToNextScene() {
        sceneManager.goToUniverseView(sceneManager, player, null, null);
    }

    /**
     * Populate texts.
     */
    public void populateTexts() {
        name.setText(player.getName());
        name.setFill(Color.WHITE);
        name.setFont(Font.font("SansSerif Bold", FontWeight.BOLD, 80));

        skillPoints.setText("\n" + "Skill Points: " + player.getSkillPoints() + "\n");
        String allocationsText = " | ";
        for (Map.Entry<String, Integer> entry : player.getSkillPointAllocations().entrySet()) {
            allocationsText += entry.getKey() + ": " + entry.getValue() + " | ";
        }
        skillPoints.setFont(Font.font("Georgia", FontWeight.BOLD, 25));
        skillPoints.setFill(Color.WHITE);

        allocations.setText(allocationsText + "\n");
        allocations.setFont(Font.font("Georgia", FontWeight.BOLD, 25));
        allocations.setFill(Color.WHITE);

        credits.setText("Credits: " + player.getCredits() + "\n");
        credits.setFont(Font.font("Georgia", FontWeight.BOLD, 25));
        credits .setFill(Color.WHITE);

        difficulty.setText("Difficulty: " + player.getDifficulty() + "\n");
        difficulty.setFont(Font.font("Georgia", FontWeight.BOLD, 25));
        difficulty.setFill(Color.WHITE);
    }
}