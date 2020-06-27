import javafx.fxml.FXML;

import javafx.scene.control.Label;

/**
 * The type Game over controller.
 */
public class GameOverController {
    /**
     * The Scene manager.
     */
    private SceneManager sceneManager;

    /**
     * The Player.
     */
    private Player player;

    /**
     * The game over label
     */
    @FXML
    private Label gameOver;

    /**
     * The details label
     */
    @FXML
    private Label details;

    private String status;

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Got to end credits screen.
     */
    @FXML
    private void endGame() {
        sceneManager.goToEndCredits(sceneManager, player);
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
     * Sets labels depending on game result
     * @param result win or lose
     */
    public void setLabels(String result) {
        if (result.equals("win")) {
            gameOver.setText("You win!");
            details.setText("You purchased the Winning Item");
        } else if (result.equals("lose")) {
            gameOver.setText("Game Over");
            details.setText("Your Ship Was Damaged Beyond Repair");
        }
    }

    public void setStatus(String status) {
        this.status = status;
        if (status.equals("win")) {
            gameOver.setText("You win!");
            details.setText("You purchased the Winning Item");
        } else if (status.equals("lose")) {
            gameOver.setText("Game Over");
            details.setText("Your Ship Was Damaged Beyond Repair");
        }
    }
}
