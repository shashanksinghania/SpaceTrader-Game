import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javafx.scene.paint.Color.WHITE;

/**
 * The type Npc controller.
 */
public class NpcController {

    /**
     * The Universe controller.
     */
    private UniverseController universeController;
    /**
     * The Left option.
     */
    private String leftOption;
    /**
     * The Middle option.
     */
    private String middleOption;
    /**
     * The Right option.
     */
    private String rightOption;
    /**
     * The Extra.
     */
    private String extra;
    /**
     * The Scene manager.
     */
    private SceneManager sceneManager;
    /**
     * The Custom display.
     */
    private HBox customDisplay = new HBox();
    /**
     * The Type of encounter.
     */
    private String typeOfEncounter;
    /**
     * The Player.
     */
    private Player player;
    /**
     * The Travel success.
     */
    private boolean travelSuccess;
    /**
     * The Stage.
     */
    private Stage stage;

    /**
     * The Bandit demand.
     */
    private int banditDemand = (int) (Math.random() * 100);
    /**
     * The Damage ship health.
     */
    private int damageShipHealth = (int) (Math.random() * 200);
    /**
     * The Trader price.
     */
    private int traderPrice = (int) (Math.random() * 100);
    /**
     * The Random item.
     */
    private Item randomItem;

    /**
     * The Stolen items.
     */
    private List<Item> stolenItems = new ArrayList<Item>();

    /**
     * The Left.
     */
    @FXML
    private Button left;

    /**
     * The Middle.
     */
    @FXML
    private Button middle;

    /**
     * The Right.
     */
    @FXML
    private Button right;

    /**
     * The Extra b.
     */
    @FXML
    private Button extraB;

    /**
     * The Display.
     */
    @FXML
    private HBox display;

    /**
     * The Done.
     */
    @FXML
    private Button done;

    /**
     * The Encounter type info.
     */
    @FXML
    private Label encounterTypeInfo;

    /**
     * The Information.
     */
    @FXML
    private Label information;

    // This is the health below which the ship gets damaged and game ends.
    // For testing, set it to 900.
    // Ideally, it should be 0.
    private int shipDamageThreshHold = 0;

    /**
     * Init.
     */
    void init() {
        if (typeOfEncounter.equals("Space Police")) {
            List<Item> playerItems = player.getShip().getItemInventory();
            Random rand = new Random();
            int noOfItemsStolen;
            if (playerItems.size() > 1) {
                noOfItemsStolen = rand.nextInt((playerItems.size() - 1)) + 1;
            } else {
                noOfItemsStolen = 1;
            }
            for (int i = 0; i < noOfItemsStolen; i++) {
                int indexToRemove = (int) (Math.random() * playerItems.size());
                stolenItems.add(playerItems.remove(indexToRemove));
            }
            String listOfStolenItemNames = "";
            for (Item x : stolenItems) {
                listOfStolenItemNames += x.getName();
                listOfStolenItemNames += ", ";
            }
            listOfStolenItemNames = listOfStolenItemNames.substring(0,
                    listOfStolenItemNames.length() - 1);
            Label label = new Label("Return the following: " + listOfStolenItemNames);
            label.setFont(new Font("Century Gothic", 12.0));
            label.setTextFill(WHITE);
            customDisplay.getChildren().add(label);
            setOptions("Forfeit", "Flee", "Fight", null);
        } else if (typeOfEncounter.equals("Bandit")) {
            setOptions("Pay", "Flee", "Fight", null);
            Label label = new Label("Demand: " + String.valueOf(banditDemand));
            label.setFont(new Font("Century Gothic", 15.0));
            label.setTextFill(WHITE);
            customDisplay.getChildren().add(label);
        } else if (typeOfEncounter.equals("Trader")) {
            setOptions("Buy", "Ignore", "Rob", "Negotiate");
            // choose a new random item with trader price
            RandomGenerator randomGenerator = new RandomGenerator();
            randomItem = randomGenerator.getARandomItem(traderPrice);
            Label label = new Label("Would you like to buy a "
                    + randomItem.getName() + " for " + traderPrice + "?");
            label.setFont(new Font("Century Gothic", 12.0));
            label.setTextFill(WHITE);
            customDisplay.getChildren().add(label);
        }
        encounterTypeInfo.setText("You have encountered a " + typeOfEncounter + "!");
        left.setText(leftOption);
        middle.setText(middleOption);
        right.setText(rightOption);
        if (extra != null) {
            extraB.setText(extra);
        } else {
            extraB.setVisible(false);
        }
        display.getChildren().addAll(customDisplay.getChildren());
    }

    /**
     * Sets scene manager.
     *
     * @param sceneManager the scene manager
     */
    void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Left button clicked.
     */
    public void leftButtonClicked() {
        if (typeOfEncounter.equals("Bandit")) {
            //pay
            if (player.getCredits() >= banditDemand) {
                player.setCredits(player.getCredits() - banditDemand);
                information.setText("You have paid the demand.");
                travelSuccess = true;
            } else if (player.getShip().getItemInventory().size() != 0) {
                player.getShip().setItemInventory(new ArrayList<Item>());
                information.setText("You don't have enough credits. "
                        + "All your items were stolen");
                travelSuccess = false;
            } else {
                if ((player.getShip().getHealth() - damageShipHealth) <= shipDamageThreshHold) {
                    information.setText("Your ship was damaged beyond repair! Game Over!");
                    sceneManager.goToGameOverScreen(sceneManager, player, "lose");
                }
                player.getShip().setHealth(player.getShip().getHealth() - damageShipHealth);
                information.setText("You don't have enough credits. Your ship was damaged.");
                travelSuccess = false;
            }
        } else if (typeOfEncounter.equals("Space Police")) {
            //forfeit
            removeStolenItems();
            travelSuccess = true;
            information.setText("The stolen items have been forfeited.");
        } else if (typeOfEncounter.equals("Trader")) {
            //buy
            extraB.setDisable(true);
            if (player.getCredits() >= traderPrice) {
                player.setCredits(player.getCredits() - traderPrice);
                //check cargo and add randomItem to the inventory
                player.addItem(randomItem);
                player.getShip().setItemInventory(player.getItems());
                information.setText("You have purchased the item.");
                travelSuccess = true;
            } else {
                information.setText("You do not have enough credits!");
                travelSuccess = true;
            }
        }
        disableOptions();
        done.setVisible(true);
    }

    /**
     * Middle button clicked.
     */
    public void middleButtonClicked() {
        Random random = new Random();
        if (typeOfEncounter.equals("Bandit")) {
            //flee
            int probability = random.nextInt(11);
            if (player.getSkillPointAllocations().get("pilot") >= probability) {
                information.setText("You managed to flee!");
                travelSuccess = true;
            } else {
                player.setCredits(0);
                player.getShip().setItemInventory(new ArrayList<Item>());
                information.setText("You failed to flee. "
                        + "All your credits and items have been stolen.");
                travelSuccess = false;
            }
        } else if (typeOfEncounter.equals("Space Police")) {
            //flee depending upon pilot skill
            int probability = random.nextInt(11);
            if (player.getSkillPointAllocations().get("pilot") >= probability - 1) {
                information.setText("You managed to flee!");
                travelSuccess = true;
            } else {
                removeStolenItems();
                if ((player.getShip().getHealth() - damageShipHealth) <= shipDamageThreshHold) {
                    information.setText("Your ship was damaged beyond repair! Game Over!");
                    sceneManager.goToGameOverScreen(sceneManager, player, "lose");
                }
                player.getShip().setHealth(player.getShip().getHealth() - damageShipHealth);
                fine();
                information.setText("You failed to flee. Your items have been forfeited, "
                        + "your ship is damaged and you have been charged a fine.");
                travelSuccess = false;
            }
        } else if (typeOfEncounter.equals("Trader")) {
            //ignore
            information.setText("You ignored the Trader and managed to flee!");
            travelSuccess = true;
        }
        disableOptions();
        done.setVisible(true);
    }

    /**
     * Right button clicked.
     */
    public void rightButtonClicked() {
        Random random = new Random();
        int probability = random.nextInt(11);
        if (typeOfEncounter.equals("Bandit")) {
            //fight
            if (player.getSkillPointAllocations().get("fighter") >= probability - 1) {
                int banditCredits = (int) (Math.random() * 50);
                player.setCredits(player.getCredits() + banditCredits);
                information.setText("You won the fight! You have gained " + banditCredits
                        + " credits.");
                travelSuccess = true;
            } else {
                if ((player.getShip().getHealth() - damageShipHealth) <= shipDamageThreshHold) {
                    information.setText("Your ship was damaged beyond repair! Game Over!");
                    sceneManager.goToGameOverScreen(sceneManager, player, "lose");
                }
                player.getShip().setHealth(player.getShip().getHealth() - damageShipHealth);
                player.setCredits(0);
                information.setText("You lost. Your ship was damaged "
                        + "and all your credits were stolen.");
                travelSuccess = false;
            }
        } else if (typeOfEncounter.equals("Space Police")) {
            if (player.getSkillPointAllocations().get("fighter")
                    >= probability - 1) {
                information.setText("You won the fight!");
                travelSuccess = true;
            } else {
                removeStolenItems();
                fine();
                information.setText("You lost. Your stolen items have been forfeited "
                        + "and you have been fined.");
                travelSuccess = false;
            }
        } else if (typeOfEncounter.equals("Trader")) {
            //rob
            if (player.getSkillPointAllocations().get("fighter") >= probability - 1) {
                player.addItem(randomItem);
                player.getShip().setItemInventory(player.getItems());
                information.setText("You robbed the Trader! You have obtained "
                        + randomItem.getName() + ".");
                travelSuccess = true;
            } else {
                if ((player.getShip().getHealth() - damageShipHealth) <= shipDamageThreshHold) {
                    information.setText("Your ship was damaged beyond repair!Game Over!");
                    sceneManager.goToGameOverScreen(sceneManager, player, "lose");
                }
                player.getShip().setHealth(player.getShip().getHealth() - damageShipHealth);
                information.setText("You failed to rob the Trader. Your ship was damaged.");
                travelSuccess = false;
            }
        }
        disableOptions();
        done.setVisible(true);
    }

    /**
     * Extra button clicked.
     */
    public void extraButtonClicked() {
        Random random = new Random();
        int probability = random.nextInt(11);
        if (typeOfEncounter.equals("Trader")) {
            //negotiate
            if (player.getSkillPointAllocations().get("trader")
                    >= probability) { //arbitrary threshold
                traderPrice = traderPrice - (traderPrice / 2);
                information.setText("Your negotiation is successful! The trader's price is now "
                        + traderPrice + " credits.");
            } else {
                traderPrice = traderPrice + (traderPrice / 2);
                information.setText("You negotiation failed and the trader is upset. "
                        + "The trader now demands a higher price: " + traderPrice + " credits.");
            }
        }
        extraB.setDisable(true);
    }

    /**
     * Done.
     */
    public void done() {
        universeController.setTravelSuccess(travelSuccess);
        stage.close();
    }

    /**
     * Disable options.
     */
    public void disableOptions() {
        left.setDisable(true);
        middle.setDisable(true);
        right.setDisable(true);
        extraB.setDisable(true);
    }

    /**
     * Sets options.
     *
     * @param leftOption   the left option
     * @param middleOption the middle option
     * @param rightOption  the right option
     * @param extra        the extra
     */
    public void setOptions(String leftOption, String middleOption,
                           String rightOption, String extra) {
        this.leftOption = leftOption;
        this.middleOption = middleOption;
        this.rightOption = rightOption;
        this.extra = extra;
    }

    /**
     * Remove stolen items.
     */
    public void removeStolenItems() {
        List<Item> playerItems = player.getShip().getItemInventory();
        for (Item x : stolenItems) {
            playerItems.remove(x);
            if (x.getType().equals("Weapon")) {
                player.removeWeapon(x);
            } else if (x.getType().equals("Shield")) {
                player.removeShield(x);
            } else if (x.getType().equals("Gadget")) {
                player.removeGadgets(x);
            }
        }
        player.getShip().setItemInventory(playerItems);
    }

    /**
     * Fine int.
     *
     * @return the int
     */
    public int fine() {
        int fine = (int) (Math.random() * player.getCredits());
        player.setCredits(player.getCredits() - fine);
        return fine;
    }

    /**
     * Sets display h box.
     *
     * @param hbox the hbox
     */
    public void setDisplayHBox(HBox hbox) {
        this.customDisplay = hbox;
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
     * Sets type of encounter.
     *
     * @param typeOfEncounter the type of encounter
     */
    public void setTypeOfEncounter(String typeOfEncounter) {
        this.typeOfEncounter = typeOfEncounter;
    }

    /**
     * Is travel success boolean.
     *
     * @return the boolean
     */
    public boolean isTravelSuccess() {
        return travelSuccess;
    }

    /**
     * Sets stage.
     *
     * @param stage the stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setHeight(480);
        stage.setWidth(800);
        display.prefWidthProperty().bind(stage.widthProperty());
        display.prefHeightProperty().bind(stage.heightProperty());
    }

    /**
     * Sets universe controller.
     *
     * @param universeController the universe controller
     */
    public void setUniverseController(UniverseController universeController) {
        this.universeController = universeController;
    }
}

