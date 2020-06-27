import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.HashMap;


/**
 * The type Config controller.
 *
 * @author Rochan
 * @version 1.2.1
 */
public class ConfigController {

    /**
     * The Scene manager.
     */
    private SceneManager sceneManager;

    /**
     * The Name text field.
     */
    @FXML
    private TextField nameTextField;
    /**
     * The Skill points label.
     */
    @FXML
    private Label skillPointsLabel;
    /**
     * The Pilot pts label.
     */
    @FXML
    private Label pilotPtsLabel;
    /**
     * The Fighter pts label.
     */
    @FXML
    private Label fighterPtsLabel;
    /**
     * The Trader pts label.
     */
    @FXML
    private Label traderPtsLabel;
    /**
     * The Engineer pts label.
     */
    @FXML
    private Label engineerPtsLabel;

    /**
     * The Difficulty menu.
     */
    @FXML
    private ComboBox difficultyMenu;

    /**
     * The Pilot plus btn.
     */
    @FXML
    private Button pilotPlusBtn;
    /**
     * The Pilot minus btn.
     */
    @FXML
    private Button pilotMinusBtn;
    /**
     * The Fighter plus btn.
     */
    @FXML
    private Button fighterPlusBtn;
    /**
     * The Fighter minus btn.
     */
    @FXML
    private Button fighterMinusBtn;
    /**
     * The Trader plus btn.
     */
    @FXML
    private Button traderPlusBtn;
    /**
     * The Trader minus btn.
     */
    @FXML
    private Button traderMinusBtn;
    /**
     * The Engineer plus btn.
     */
    @FXML
    private Button engineerPlusBtn;
    /**
     * The Engineer minus btn.
     */
    @FXML
    private Button engineerMinusBtn;

    /**
     * The Default points.
     */
    private int defaultPoints = 0;
    /**
     * The Easy points.
     */
    private int easyPoints = 30;
    /**
     * The Normal points.
     */
    private int normalPoints = 20;
    /**
     * The Hard points.
     */
    private int hardPoints = 10;

    /**
     * Method to set the initial points when the difficulty is chosen.
     */
    @FXML
    private void changeDifficulty() {
        Button[] plusBtns = {pilotPlusBtn, fighterPlusBtn, traderPlusBtn, engineerPlusBtn};
        for (Button b : plusBtns) {
            b.setDisable(false);
        }

        Button[] minusBtns = {pilotMinusBtn, fighterMinusBtn, traderMinusBtn, engineerMinusBtn};
        for (Button b : minusBtns) {
            b.setDisable(true);
        }

        if (difficultyMenu.getValue().equals("Easy")) {
            skillPointsLabel.setText(Integer.toString(easyPoints));
        } else if (difficultyMenu.getValue().equals("Normal")) {
            skillPointsLabel.setText(Integer.toString(normalPoints));
        } else {
            skillPointsLabel.setText(Integer.toString(hardPoints));
        }
        pilotPtsLabel.setText(Integer.toString(defaultPoints));
        fighterPtsLabel.setText(Integer.toString(defaultPoints));
        traderPtsLabel.setText(Integer.toString(defaultPoints));
        engineerPtsLabel.setText(Integer.toString(defaultPoints));
    }

    /**
     * Method to check and disable or enable the plus and minus buttons according to the values
     */
    private void checkButtons() {
        if (skillPointsLabel.getText().equals("0")) {
            pilotPlusBtn.setDisable(true);
            fighterPlusBtn.setDisable(true);
            traderPlusBtn.setDisable(true);
            engineerPlusBtn.setDisable(true);
        } else {
            pilotPlusBtn.setDisable(false);
            fighterPlusBtn.setDisable(false);
            traderPlusBtn.setDisable(false);
            engineerPlusBtn.setDisable(false);
        }

        if (pilotPtsLabel.getText().equals("0")) {
            pilotMinusBtn.setDisable(true);
        } else {
            pilotMinusBtn.setDisable(false);
        }
        if (fighterPtsLabel.getText().equals("0")) {
            fighterMinusBtn.setDisable(true);
        } else {
            fighterMinusBtn.setDisable(false);
        }
        if (traderPtsLabel.getText().equals("0")) {
            traderMinusBtn.setDisable(true);
        } else {
            traderMinusBtn.setDisable(false);
        }
        if (engineerPtsLabel.getText().equals("0")) {
            engineerMinusBtn.setDisable(true);
        } else {
            engineerMinusBtn.setDisable(false);
        }
    }

    /**
     * Method when pilot plus button is pressed.
     */
    @FXML
    private void pilotPlus() {
        if (Integer.parseInt(skillPointsLabel.getText()) > 0) {
            pilotPtsLabel.setText("" + (Integer.parseInt(pilotPtsLabel.getText()) + 1));
            skillPointsLabel.setText("" + (Integer.parseInt(skillPointsLabel.getText()) - 1));
        }
        checkButtons();
    }

    /**
     * Method when pilot minus button is pressed.
     */
    @FXML
    private void pilotMinus() {
        if (Integer.parseInt(pilotPtsLabel.getText()) > 0 && Integer.parseInt(
                skillPointsLabel.getText()) >= 0) {
            pilotPtsLabel.setText("" + (Integer.parseInt(pilotPtsLabel.getText()) - 1));
            skillPointsLabel.setText("" + (Integer.parseInt(skillPointsLabel.getText()) + 1));
        }
        checkButtons();
    }

    /**
     * Method when fighter plus button is pressed.
     */
    @FXML
    private void fighterPlus() {
        if (Integer.parseInt(skillPointsLabel.getText()) > 0) {
            fighterPtsLabel.setText("" + (Integer.parseInt(fighterPtsLabel.getText()) + 1));
            skillPointsLabel.setText("" + (Integer.parseInt(skillPointsLabel.getText()) - 1));
        }
        checkButtons();
    }

    /**
     * Method when fighter minus button is pressed.
     */
    @FXML
    private void fighterMinus() {
        if (Integer.parseInt(fighterPtsLabel.getText()) > 0 && Integer.parseInt(
                skillPointsLabel.getText()) >= 0) {
            fighterPtsLabel.setText("" + (Integer.parseInt(fighterPtsLabel.getText()) - 1));
            skillPointsLabel.setText("" + (Integer.parseInt(skillPointsLabel.getText()) + 1));
        }
        checkButtons();
    }

    /**
     * Method when trader plus button is pressed.
     */
    @FXML
    private void traderPlus() {
        if (Integer.parseInt(skillPointsLabel.getText()) > 0) {
            traderPtsLabel.setText("" + (Integer.parseInt(traderPtsLabel.getText()) + 1));
            skillPointsLabel.setText("" + (Integer.parseInt(skillPointsLabel.getText()) - 1));
        }
        checkButtons();
    }

    /**
     * Method when trader minus button is pressed.
     */
    @FXML
    private void traderMinus() {
        if (Integer.parseInt(traderPtsLabel.getText()) > 0 && Integer.parseInt(
                skillPointsLabel.getText()) >= 0) {
            traderPtsLabel.setText("" + (Integer.parseInt(traderPtsLabel.getText()) - 1));
            skillPointsLabel.setText("" + (Integer.parseInt(skillPointsLabel.getText()) + 1));
        }
        checkButtons();
    }

    /**
     * Method when engineer plus button is pressed.
     */
    @FXML
    private void engineerPlus() {
        if (Integer.parseInt(skillPointsLabel.getText()) > 0) {
            engineerPtsLabel.setText("" + (Integer.parseInt(engineerPtsLabel.getText()) + 1));
            skillPointsLabel.setText("" + (Integer.parseInt(skillPointsLabel.getText()) - 1));
        }
        checkButtons();
    }

    /**
     * Method when engineer minus button is pressed.
     */
    @FXML
    private void engineerMinus() {
        if (Integer.parseInt(engineerPtsLabel.getText()) > 0 && Integer.parseInt(
                skillPointsLabel.getText()) >= 0) {
            engineerPtsLabel.setText("" + (Integer.parseInt(engineerPtsLabel.getText()) - 1));
            skillPointsLabel.setText("" + (Integer.parseInt(skillPointsLabel.getText()) + 1));
        }
        checkButtons();
    }

    /**
     * Method to check the name and points
     *
     * @return if game can proceed ahead
     */
    @FXML
    private boolean checkOKBtn() {
        if (nameTextField.getText() == null || nameTextField.getText().strip().equals("")) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Name cannot be blank!");
            errorAlert.showAndWait();
            nameTextField.setText("");
            return false;
        }
        if (pilotPtsLabel.getText().equals(Integer.toString(defaultPoints))
                && traderPtsLabel.getText().equals(Integer.toString(defaultPoints))
                && fighterPtsLabel.getText().equals(Integer.toString(defaultPoints))
                && engineerPtsLabel.getText().equals(Integer.toString(defaultPoints))) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "All points cannot be 0!");
            errorAlert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Method to relay data to main character screen.
     */
    @FXML
    private void goToCharScreen() {
        if (checkOKBtn()) {
            HashMap<String, Integer> skillPointAllocations = new HashMap<>();
            skillPointAllocations.put("pilot", Integer.parseInt(pilotPtsLabel.getText()));
            skillPointAllocations.put("fighter", Integer.parseInt(fighterPtsLabel.getText()));
            skillPointAllocations.put("trader", Integer.parseInt(traderPtsLabel.getText()));
            skillPointAllocations.put("engineer", Integer.parseInt(engineerPtsLabel.getText()));
            String difficulty = difficultyMenu.getValue().toString();
            int credits = difficulty.equals("Easy") ? easyPoints
                    : difficulty.equals("Normal") ? normalPoints : hardPoints;
            credits *= 10;
            Player player = new Player(nameTextField.getText(), credits,
                    difficultyMenu.getValue().toString(), skillPointAllocations);
            sceneManager.goToCharSheet(sceneManager, player);
        }
    }


    /**
     * Sets scene manager.
     *
     * @param sceneManager the scene manager
     */
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
