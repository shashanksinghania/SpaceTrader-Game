import javafx.animation.PathTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.paint.Color.WHITE;

/**
 * Universe Controller
 *
 * @author Team 12
 */
public class UniverseController {

    /**
     * The Scene manager.
     */
    private SceneManager sceneManager;
    /**
     * The Player.
     */
    private Player player;
    /**
     * The Ship.
     */
    private Ship ship;
    /**
     * The Regions.
     */
    private List<Region> regions;
    /**
     * The Names.
     */
    private String[] names = {"Roaring 20s", "Rocky Caves", "Hogwarts", "Yay Or Neigh",
        "DaVinci's Code", "Fun Factory", "Fission Chips", "Hello World", "Galactica", "Cybertron"};
    /**
     * The Descriptions.
     */
    private Map<String, String> descriptions = new HashMap<>();
    /**
     * The Tech levels.
     */
    private Map<String, Integer> techLevels = new HashMap<>();
    /**
     * The Current region.
     */
    private Region currentRegion;

    /**
     * The Visiting region.
     */
    private Region visitingRegion;
    /**
     * The Lbl regions.
     */
    private Map<Region, Circle> lblRegions;

    /**
     * The Current x.
     */
    private int currentX;
    /**
     * The Current y.
     */
    private int currentY;

    /**
     * The Current ship.
     */
    private Ship currentShip;

    /**
     * The Pane region.
     */
    @FXML
    private Pane paneRegion;

    /**
     * The Lbl name region.
     */
    @FXML
    private Label lblNameRegion;

    /**
     * The Lbl technology level.
     */
    @FXML
    private Label lblTechnologyLevel;

    /**
     * The Lbl description.
     */
    @FXML
    private Label lblDescription;

    /**
     * The Lbl distance.
     */
    @FXML
    private Label lblDistance;

    /**
     * The Btn next region.
     */
    @FXML
    private Button btnNextRegion;

    /**
     * The Btn previous region.
     */
    @FXML
    private Button btnPreviousRegion;

    /**
     * The Btn visit.
     */
    @FXML
    private Button btnVisit;

    /**
     * The Lbl name label.
     */
    @FXML
    private Label lblNameLabel;

    /**
     * The Btn Marketplace.
     */
    @FXML
    private Button btnMarketplace;

    /**
     * The Lbl tech level label.
     */
    @FXML
    private Label lblTechLevelLabel;

    /**
     * The Lbl coordinates label.
     */
    @FXML
    private Label lblCoordinates;

    /**
     * The Lbl distance label.
     */
    @FXML
    private Label lblDistanceLabel;

    /**
     * The Btn refuel ship button
     */
    @FXML
    private Button btnRefuelShip;

    /**
     * The Btn repair Ship button
     */
    @FXML
    private Button btnRepairShip;

    /**
     * The Setup.
     */
    private boolean setup;

    /**
     * The Travel success.
     */
    private boolean travelSuccess;

    /**
     * The X list.
     */
    private ArrayList<Integer> xList = new ArrayList<>();
    /**
     * The Y list.
     */
    private ArrayList<Integer> yList = new ArrayList<>();

    /**
     * The number of regions visited
     */
    private static int regionsVisited = 1;
    /**
     * Whther the win item has been initialized in a region
     */
    private static boolean winItemAllocated = false;

    /**
     * @return winItemAllocated
     */
    public static boolean isWinItemAllocated() {
        return winItemAllocated;
    }

    /**
     * @param winItemAllocated whether win item has been allocated to a region
     */
    public static void setWinItemAllocated(boolean winItemAllocated) {
        UniverseController.winItemAllocated = winItemAllocated;
    }

    /**
     * @return number of regions visited
     */

    public static int getRegionsVisited() {
        return regionsVisited;
    }

    /**
     * Initializes a list of Region objects that the player
     * can travel to. Maps the descriptions and Tech levels to that
     * specific region
     */
    public void initRegions() {
        initDescriptions();
        initTechLevels();
        regions = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Region region = new Region(names[i]);
            region.setDescription(descriptions.get(names[i]));
            region.setTechLevel(techLevels.get(names[i]));
            regions.add(region);
        }
    }

    /**
     * Init descriptions.
     */
    public void initDescriptions() {
        descriptions.put("Roaring 20s", "In this system, inhabitants are "
                + "dinosaurs. Technology "
                + "is underdeveloped and there are minimal resources here. They "
                + "have little interest "
                + "in buying things as they don't need "
                + "them, but you may find water and raw materials here to add to "
                + "your inventory.");
        descriptions.put("Rocky Caves", "In this system, inhabitants are cav"
                + "emen who know basic counting. You can "
                + "trade weapons here, although you can likely only find manual w"
                + "eapons such as sharp stones and "
                + "spears here. You can also get some fabrics and natural medicine here.");
        descriptions.put("Hogwarts", "In this system, inhabitants are wizard"
                + "s and witches. There is much to see that "
                + "you will be enchanted. They produce almost everything except m"
                + "achines and automated equipment. "
                + "Be warned, however; the battles that you may encounter here ar"
                + "e not easy to win.");
        descriptions.put("Yay Or Neigh", "In this medieval system, inhabitan"
                + "ts are knights on horses most of "

                + "the time. They are quick and aggressive when negotiating, espe"
                + "cially over firearms. With "
                + "much focus placed on agriculture, there is plenty of "
                + "food and fabric to go around.");
        descriptions.put("DaVinci's Code", "In this system, inhabitants are inventors focusing on"
                + " the discoveries of new science, art and religion. They would "
                + "most likely take anything you are "
                + "willing to sell, except robots which they lack the power to ru"
                + "n. Raw materials and firearms are "
                + "in abundance here.");
        descriptions.put("Fun Factory", "In this system, manual labor is not "
                + "common as people have learnt "
                + "to be more efficient by taking advantage of "
                + "factory production. Thus, this is a good "
                + "market for whatever goods you want to trade. However, some "
                + "advanced machinery are still "
                + "too costly to be produced.");
        descriptions.put("Fission Chips", "In this underwater system, inhabitants are sea "
                + "creatures who have "
                + "discovered nuclear power. Firearms are extremely powerful are more "
                + "expensive over here. However, "
                + "be careful not to upset anyone or you'll be blasted far away from the planet.");
        descriptions.put("Hello World", "In this system, inhabitants are always "
                + "hurrying from place to place or "
                + "frowning over their personal electronic devices. Computers, mobile"
                + " phones and other gadgets are "
                + "all over the planet and extremely affordable, but nothing fancy"
                + " can be found here.");
        descriptions.put("Galactica", "In this system, inhabitants are extraterrestrial "
                + "beings. They are highly "
                + "fascinated by any raw materials and man-made machinery you might bring. "
                + "Expect to also find unique "
                + "food and equipment here. However, "
                + "communicate properly and ensure you come in peace.");
        descriptions.put("Cybertron", "In this system, inhabitants are"
                + " extremely intelligent "
                + "robots. Don't expect "
                + "to find food, raw materials and medicine here, albeit they may "
                + "reap high profits. Make good use of "
                + "the advanced machinery and firearms available, which are eff"
                + "icient and cheap.");
    }

    /**
     * Init tech levels.
     */
    public void initTechLevels() {
        techLevels.put("Roaring 20s", 1);
        techLevels.put("Rocky Caves", 2);
        techLevels.put("Hogwarts", 3);
        techLevels.put("Yay Or Neigh", 4);
        techLevels.put("DaVinci's Code", 5);
        techLevels.put("Fun Factory", 6);
        techLevels.put("Fission Chips", 7);
        techLevels.put("Hello World", 8);
        techLevels.put("Galactica", 9);
        techLevels.put("Cybertron", 10);
    }

    /**
     * Gives all of the region objects random coordinates
     *
     * @param xmin the xmin
     * @param xmax the xmax
     * @param ymin the ymin
     * @param ymax the ymax
     */
    public void randomCoordinates(int xmin, int xmax, int ymin, int ymax) {
        int i = 0;
        outerLoop:
        while (i < regions.size()) {
            int randx = (int) (Math.random() * ((xmax - xmin) + 1)) + xmin;
            int randy = (int) (Math.random() * ((ymax - ymin) + 1)) + ymin;

            for (int j = 0; j < xList.size(); j++) {
                double distance = dist(randx, randy, xList.get(j), yList.get(j));
                if (distance < 70) {
                    continue outerLoop;
                }
            }
            xList.add(randx);
            yList.add(randy);
            i++;
        }

        for (i = 0; i < regions.size(); i++) {
            regions.get(i).setX(xList.get(i));
            regions.get(i).setY(yList.get(i));
        }
    }

    /**
     * Dist double.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     * @return the double
     */
    private double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    /**
     * Add the regions to the universe pane
     *
     * @param spawn the spawn
     */
    public void addRegions(boolean spawn) {
        lblRegions = new HashMap<>();
        for (int i = 0; i < regions.size(); i++) {
            Label lblRegion = new Label();
            lblRegion.setLayoutX(regions.get(i).getX());
            lblRegion.setLayoutY(regions.get(i).getY());
            lblRegion.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            lblRegion.setTextFill(Color.WHITE);
            Circle circle = new Circle();
            circle.setCenterX(lblRegion.getLayoutX() + 10);
            circle.setCenterY(lblRegion.getLayoutY() + 20);
            circle.setRadius(30);
            circle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
            RadialGradient gradient = new RadialGradient(0, .1, 100,
                    100, 20, false,
                    CycleMethod.REPEAT, new Stop(0, Color.color(Math.random(),
                    Math.random(), Math.random())),
                    new Stop(1, Color.color(Math.random(),
                            Math.random(), Math.random())));
            circle.setFill(gradient);
            int finalI = i;
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

                displayRegion();
                lblDistance.setText((int) dist(currentRegion.getX(), currentRegion.getY(),
                        regions.get(finalI).getX(), regions.get(finalI).getY()) + "");
                currentRegion = regions.get(finalI);
                lblRegions.get(currentRegion).setFill(Color.LIGHTGREEN);
                checkButton();
            });
            lblRegions.put(regions.get(i), circle);
            paneRegion.getChildren().add(circle);
            paneRegion.getChildren().add(lblRegion);
        }
        if (spawn) {
            currentRegion = regions.get((int) (Math.random() * (names.length - 1) + 1));
        } else {
            currentRegion = visitingRegion;
        }
        setup = true;
        travelSuccess = true;
        visit();
        displayRegion();
        setup = false;
    }

    /**
     * Updates the description, name, and tech level of the current region
     */
    public void displayRegion() {
        RadialGradient gradient = new RadialGradient(0, .1, 100, 100, 20, false,
                CycleMethod.REPEAT, new Stop(0,
                Color.color(Math.random(), Math.random(), Math.random())),
                new Stop(1, Color.color(Math.random(), Math.random(), Math.random())));
        lblRegions.get(currentRegion).setFill(gradient);
        lblCoordinates.setText("(" + currentRegion.getX() + ", " + currentRegion.getY() + ")");
        lblDistance.setText(Integer.toString(calculateDistance(currentRegion)));
        lblDistanceLabel.setVisible(true);
        lblDistance.setVisible(true);
        if (currentRegion.isVisited()) {
            lblNameRegion.setText(currentRegion.getName());
            lblDescription.setText(currentRegion.getDescription());
            lblDescription.setWrapText(true);
            lblTechnologyLevel.setText(Integer.toString(currentRegion.getTechLevel()));
            lblNameLabel.setVisible(true);
            lblTechLevelLabel.setVisible(true);
            lblDescription.setVisible(true);
            lblNameRegion.setVisible(true);
            lblTechnologyLevel.setVisible(true);
            btnMarketplace.setVisible(true);
        } else {
            lblNameLabel.setVisible(false);
            lblTechLevelLabel.setVisible(false);
            lblTechnologyLevel.setVisible(false);
            lblDescription.setVisible(false);
            lblNameRegion.setVisible(false);
            btnMarketplace.setVisible(false);
        }
        checkButton();
    }

    /**
     * Create player ship.
     */
    public void createPlayerShip() {
        currentShip = new Ship("speedie", 20, new ArrayList<Item>(), 1000, 200);
        player.setShip(currentShip);
        Image image = new Image("spaceship.png");
        currentShip.setImage(image);
        ImageView imageView = new ImageView(image);
        currentShip.setImageView(imageView);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        ship = currentShip;
        if (currentRegion != null) {
            imageView.setX(currentRegion.getX() - 15);
            imageView.setY(currentRegion.getY() - 5);
        }
        paneRegion.getChildren().add(imageView);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage shipDisplay = new Stage();
            shipDisplay.initModality(Modality.APPLICATION_MODAL);
            shipDisplay.initOwner(sceneManager.getStage());
            VBox vbox = createShipView(shipDisplay);
            Scene dialogScene = new Scene(vbox, 300, 200);
            shipDisplay.setScene(dialogScene);
            shipDisplay.setTitle("Your Ship");
            shipDisplay.getIcons().add(new Image("spaceship.png"));
            shipDisplay.setResizable(false);
            shipDisplay.show();
        });
    }

    /**
     * Button next region
     */
    public void nextRegion() {
        currentRegion = regions.get(regions.indexOf(currentRegion) + 1);
        lblRegions.get(currentRegion).setFill(Color.LIGHTGREEN);
        displayRegion();
        checkButton();
    }

    /**
     * Button previous region
     */
    public void previousRegion() {
        currentRegion = regions.get(regions.indexOf(currentRegion) - 1);
        lblRegions.get(currentRegion).setFill(Color.LIGHTGREEN);
        displayRegion();
        checkButton();
    }

    /**
     * Check button.
     */
    private void checkButton() {
        if (regions.indexOf(currentRegion) == 0) {
            btnPreviousRegion.setDisable(true);
        } else {
            btnPreviousRegion.setDisable(false);
        }
        if (regions.indexOf(currentRegion) == regions.size() - 1) {
            btnNextRegion.setDisable(true);
        } else {
            btnNextRegion.setDisable(false);
        }
        if (calculateDistance(currentRegion) == 0) {
            btnVisit.setDisable(true);
        } else {
            btnVisit.setDisable(false);
        }
    }

    /**
     * Go to market.
     */
    @FXML
    private void goToMarket() {
        sceneManager.goToMarketPlace(sceneManager, currentRegion, player, ship);
    }

    /**
     * Visit region
     */
    public void visit() {
        if (!setup && Math.random() < 0.5) { // 50% chance of encounter
            ArrayList<String> typesOfEncounters = new ArrayList<String>();

            // if difficulty is hard then more chances of encountering a bandit
            if (player.getDifficulty().equals("Hard")) {
                typesOfEncounters.add("Bandit");
            }
            typesOfEncounters.add("Bandit");
            typesOfEncounters.add("Trader");
            if (player.getShip().getItemInventory().size() > 0) {
                typesOfEncounters.add("Space Police");
            }
            goToEncounter(typesOfEncounters.get((int) (Math.random() * typesOfEncounters.size())),
                    player, this);
        }
        if (travelSuccess) {
            try {
                int distance = Integer.parseInt(lblDistance.getText())
                        / player.getSkillPointAllocations().get("pilot");
                if (player.getShip().getFuelCapacity() < distance) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR,
                            "You do not have enough fuel!");
                    errorAlert.setTitle("Not Enough Fuel");
                    errorAlert.showAndWait();
                    return;
                }
                player.getShip().setFuelCapacity(player.getShip().
                        getFuelCapacity() - (distance));
            } catch (Exception ex) {
                //first visit will cause an exception because lblDistance doesn't
                //have a value yet
            }
            if (!currentRegion.isVisited()) {
                regionsVisited++;
            }
            currentRegion.setVisited(true);
            visitingRegion = currentRegion;
            currentX = currentRegion.getX();
            currentY = currentRegion.getY();
            displayRegion();
            btnVisit.setDisable(true);
            if (currentShip != null) {
                Path path = new Path();
                path.getElements().add(new MoveTo(currentShip.getImageView().getX()
                        + currentShip.getImageView().getFitWidth() / 2,
                        currentShip.getImageView().getY()
                                + currentShip.getImageView().getFitHeight() / 2));
                path.getElements().add(new LineTo(currentX
                        + currentShip.getImageView().getFitWidth() / 4 - 3,
                        currentY
                                + currentShip.getImageView().getFitHeight() / 2 - 5));
                currentShip.getImageView().setX(currentX - 15);
                currentShip.getImageView().setY(currentY - 5);
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(1000));
                pathTransition.setCycleCount(1);
                pathTransition.setPath(path);
                pathTransition.setNode(currentShip.getImageView());
                pathTransition.setAutoReverse(true);
                pathTransition.play();
            }
        } else {
            travelSuccess = true;
        }
    }

    /**
     * Upgrade.
     */
    public void upgrade() {
        sceneManager.goToUpgradesView(sceneManager,
                player, visitingRegion, regions);
    }

    /**
     * Calculate distance int.
     *
     * @param next the next
     * @return the int
     */
    public int calculateDistance(Region next) {
        return (int) Math.sqrt(Math.pow(currentX - next.getX(), 2)
                + Math.pow(currentY - next.getY(), 2));
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
     * Sets visiting region.
     *
     * @param visitingRegion the visiting region
     */
    public void setVisitingRegion(Region visitingRegion) {
        this.visitingRegion = visitingRegion;
    }

    /**
     * Sets regions.
     *
     * @param regions the regions
     */
    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    /**
     * Go to encounter.
     *
     * @param typeOfEncounter    the type of encounter
     * @param player             the player
     * @param universeController the universe controller
     */
    public void goToEncounter(String typeOfEncounter, Player player,
                              UniverseController universeController) {
        Parent root = null;
        Stage stage = new Stage();
        stage.setTitle("Encounter");
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:images/appIcon.png"));
        stage.initStyle(StageStyle.DECORATED);
        stage.setOnCloseRequest(Event::consume);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("npcEncounter.fxml"));
            root = loader.load();
            NpcController controller = loader.getController();
            controller.setPlayer(player);
            controller.setTypeOfEncounter(typeOfEncounter);
            controller.setUniverseController(universeController);
            controller.init();
            controller.setSceneManager(sceneManager);
            controller.setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene encounter = new Scene(root, 500, 200);
        stage.setScene(encounter);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Sets travel success.
     *
     * @param travelSuccess the travel success
     */
    public void setTravelSuccess(boolean travelSuccess) {
        this.travelSuccess = travelSuccess;
    }

    /**
     * Create ship view v box.
     *
     * @param shipDisplay the ship display
     * @return the v box
     */
    public VBox createShipView(Stage shipDisplay) {
        VBox vbox = new VBox(20);
        Pane pane = new Pane();
        ImageView imgV = new ImageView(currentShip.getImage());
        imgV.setFitWidth(100);
        imgV.setFitHeight(100);
        imgV.setX(30);
        imgV.setY(50);
        Label lblName = new Label(currentShip.getName());
        lblName.setFont(new Font("Century Gothic Bold", 25.0));
        lblName.setTextFill(WHITE);
        lblName.setLayoutX(170);
        lblName.setLayoutY(20);
        Label lblCargoCapacity = new Label("Cargo Capacity: " + currentShip.getCargoCapacity());
        lblCargoCapacity.setFont(new Font("Century Gothic Bold", 12.0));
        lblCargoCapacity.setTextFill(WHITE);
        lblCargoCapacity.setLayoutX(165);
        lblCargoCapacity.setLayoutY(70);
        Label lblFuelCapacity = new Label("Fuel Capacity: " + currentShip.getFuelCapacity());
        lblFuelCapacity.setFont(new Font("Century Gothic Bold", 12.0));
        lblFuelCapacity.setTextFill(WHITE);
        lblFuelCapacity.setLayoutX(165);
        lblFuelCapacity.setLayoutY(100);
        Label lblItemInventory = new Label("Item Inventory: "
                + currentShip.getItemInventory().size());
        lblItemInventory.setFont(new Font("Century Gothic Bold", 12.0));
        lblItemInventory.setTextFill(WHITE);
        lblItemInventory.setLayoutX(175);
        lblItemInventory.setLayoutY(130);
        Label lblHealth = new Label("Health: " + currentShip.getHealth());
        lblHealth.setFont(new Font("Century Gothic Bold", 12.0));
        lblHealth.setTextFill(WHITE);
        lblHealth.setLayoutX(185);
        lblHealth.setLayoutY(160);
        ImageView background = new ImageView(new Image("playerMenuBackground.jpg"));
        background.setFitHeight(251);
        background.setFitWidth(300);
        pane.getChildren().addAll(background, imgV, lblName, lblCargoCapacity,
                lblFuelCapacity, lblItemInventory, lblHealth);
        vbox.getChildren().add(pane);
        return vbox;
    }

    /**
     * Refuel ship.
     */
    public void refuelShip() {
        Stage shipDisplay = new Stage();
        shipDisplay.initModality(Modality.APPLICATION_MODAL);
        shipDisplay.initOwner(sceneManager.getStage());
        VBox vbox = createShipView(shipDisplay);
        Pane pane = (Pane) vbox.getChildren().get(0);
        Label lblFuelCapacity = (Label) pane.getChildren().get(4);
        Button btnAddFuel = new Button();
        btnAddFuel.setText("Add Fuel");
        btnAddFuel.setLayoutX(110);
        btnAddFuel.setLayoutY(200);
        Label lblCredits = new Label();
        lblCredits.setFont(new Font("Century Gothic Bold", 12.0));
        lblCredits.setTextFill(WHITE);
        lblCredits.setLayoutX(20);
        lblCredits.setLayoutY(205);
        lblCredits.setText("Credits: " + player.getCredits());
        Label lblCost = new Label();
        lblCost.setFont(new Font("Century Gothic Bold", 12.0));
        lblCost.setTextFill(WHITE);
        lblCost.setLayoutX(180);
        lblCost.setLayoutY(205);
        int costOfRefuel = 15;      //refuel ship is constant
        lblCost.setText("Cost: " + costOfRefuel + " credits");
        btnAddFuel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (player.getCredits() < costOfRefuel) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,
                        "You do not have enough credits");
                errorAlert.showAndWait();
            } else {
                player.setCredits(player.getCredits() - costOfRefuel);
                player.getShip().setFuelCapacity(player.getShip().getFuelCapacity() + 100);
                lblFuelCapacity.setText("Fuel Capacity: "
                        + player.getShip().getFuelCapacity());
                lblCredits.setText("Credits: " + player.getCredits());
            }
        });
        pane.getChildren().addAll(btnAddFuel, lblCost, lblCredits);
        Scene dialogScene = new Scene(vbox, 300, 250);
        shipDisplay.setScene(dialogScene);
        shipDisplay.setTitle("Your Ship");
        shipDisplay.getIcons().add(new Image("spaceship.png"));
        shipDisplay.setResizable(false);
        shipDisplay.show();
    }

    /**
     * Repair Ship
     */
    public void repairShip() {
        Stage shipDisplay = new Stage();
        shipDisplay.initModality(Modality.APPLICATION_MODAL);
        shipDisplay.initOwner(sceneManager.getStage());
        VBox vbox = createShipView(shipDisplay);
        Pane pane = (Pane) vbox.getChildren().get(0);
        Label lblHealth = (Label) pane.getChildren().get(6);
        Button btnRepairShip = new Button();
        btnRepairShip.setText("Repair Ship");
        btnRepairShip.setLayoutX(110);
        btnRepairShip.setLayoutY(200);
        Label lblCredits = new Label();
        lblCredits.setFont(new Font("Century Gothic Bold", 12.0));
        lblCredits.setTextFill(WHITE);
        lblCredits.setLayoutX(20);
        lblCredits.setLayoutY(205);
        lblCredits.setText("Credits: " + player.getCredits());
        Label lblCost = new Label();
        lblCost.setFont(new Font("Century Gothic Bold", 12.0));
        lblCost.setTextFill(WHITE);
        lblCost.setLayoutX(185);
        lblCost.setLayoutY(205);

        //cost is dependent on engineering skill
        int costOfRepair = 50 - (int) (1.75 * player.getSkillPointAllocations().get("engineer"));
        lblCost.setText(" Cost: " + costOfRepair + " credits");
        String diff = player.getDifficulty();
        btnRepairShip.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (player.getCredits() < costOfRepair) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,
                        "You do not have enough credits");
                errorAlert.showAndWait();
            } else {
                player.setCredits(player.getCredits() - costOfRepair);
                player.getShip().setHealth(player.getShip().getHealth() + 50);
                lblHealth.setText("Health: "
                        + player.getShip().getHealth());
                lblCredits.setText("Credits: " + player.getCredits());
            }
        });
        pane.getChildren().addAll(btnRepairShip, lblCost, lblCredits);
        Scene dialogScene = new Scene(vbox, 300, 250);
        shipDisplay.setScene(dialogScene);
        shipDisplay.setTitle("Your Ship");
        shipDisplay.getIcons().add(new Image("spaceship.png"));
        shipDisplay.setResizable(false);
        shipDisplay.show();
    }

}
