import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Upgrades controller.
 */
public class UpgradesController {
    /**
     * The Scene manager.
     */
    private SceneManager sceneManager;
    /**
     * The Player.
     */
    private Player player;
    /**
     * The Visiting region.
     */
    private Region visitingRegion;
    /**
     * The Regions.
     */
    private List<Region> regions;
    /**
     * The Map.
     */
    private TreeMap<String, List<List<Item>>> map;

    /**
     * Gets map.
     *
     * @return the map
     */
    public TreeMap<String, List<List<Item>>> getMap() {
        return map;
    }

    /**
     * Sets map.
     *
     * @param map the map
     */
    public void setMap(TreeMap<String, List<List<Item>>> map) {
        this.map = map;
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
     * The List.
     */
    @FXML
    private ListView list;

    /**
     * The Skill label.
     */
    @FXML
    private Label skillLabel;

    /**
     * The Equip image.
     */
    @FXML
    private ImageView equipImage;

    /**
     * Sets visiting region.
     *
     * @param visitingRegion the visiting region
     */
    public void setVisitingRegion(Region visitingRegion) {
        this.visitingRegion = visitingRegion;
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
     * Sets scene manager.
     *
     * @param sceneManager the scene manager
     */
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Init.
     */
    public void init() {
        updatePoints();
        ObservableList<EquippableItem> equippedItemList = FXCollections.observableArrayList();
        for (EquippableItem item : player.getEquippableItems()) {
            equippedItemList.add(item);
        }
        list.setItems(equippedItemList);
        list.setSelectionModel(new NoSelectionModel<EquippableItemCell>());
        list.setStyle("-fx-background-color: transparent;");
        list.setCellFactory(equippedItemListView -> new EquippableItemCell());
        Image image = new Image("equipable.png");
        equipImage.setImage(image);
    }

    /**
     * Back.
     */
    @FXML
    public void back() {
        sceneManager.goBackToUniverseView(sceneManager, map);
    }

    /**
     * Update points.
     */
    private void updatePoints() {
        String s = "";
        for (Map.Entry entry : player.getSkillPointAllocations().entrySet()) {
            s += entry.getKey() + ":" + entry.getValue() + "|";
        }
        skillLabel.setText(s);
    }

    /**
     * The type Equippable item cell.
     */
    private class EquippableItemCell extends ListCell<EquippableItem> {
        /**
         * The Pane.
         */
        private HBox pane = new HBox(10);
        /**
         * The Item name.
         */
        private Label itemName;
        /**
         * The Equipped.
         */
        private Label equipped;
        /**
         * The Effect.
         */
        private Label effect;
        /**
         * The Equip.
         */
        private Button equip;

        @Override
        protected void updateItem(EquippableItem equippableItem, boolean b) {
            super.updateItem(equippableItem, b);
            setStyle("-fx-background-color: transparent;");
            updateSelected(false);
            pane.setMinWidth(700);
            pane.setMaxWidth(700);
            if (b || equippableItem == null) {
                setText(null);
                setGraphic(null);
            } else {
                itemName = new Label(equippableItem.getName());
                itemName.setMinWidth(275);
                itemName.setTextFill(Color.WHITE);
                itemName.setFont(Font.font("Century Gothic", FontWeight.BOLD, 12.0));
                equipped = new Label("Equipped");
                equipped.setMinWidth(100);
                equipped.setFont(Font.font("Century Gothic", 12.0));
                effect = new Label(equippableItem.getSkill() + "+" + equippableItem.getEffect());
                effect.setMinWidth(200);
                effect.setTextFill(Color.GREEN);
                effect.setFont(Font.font("Century Gothic", FontWeight.BOLD, 12.0));
                equip = new Button("Equip");
                equip.setFont(Font.font("Century Gothic", 12.0));
                equip.setMinWidth(125);
                pane.getChildren().addAll(itemName, effect, equipped, equip);
                if (!equippableItem.isEquipped()) {
                    equipped.setText("Not Equipped");
                    equipped.setTextFill(Color.RED);
                    equip.setText("Equip");
                } else {
                    equipped.setText("Equipped");
                    equipped.setTextFill(Color.GREEN);
                    equip.setText("Unequip");
                }
                equip.setOnAction(e -> {
                    if (equip.getText().equals("Equip")) {
                        Platform.runLater(() -> {
                            equipped.setText("Equipped");
                            equip.setText("Unequip");
                            equipped.setTextFill(Color.GREEN);
                        });
                        equippableItem.setEquipped(true);
                        HashMap<String, Integer> oldAllocations = player.getSkillPointAllocations();
                        int oldPoints = oldAllocations.get(equippableItem.getSkill());
                        oldAllocations.replace(equippableItem.getSkill(),
                                oldPoints + equippableItem.getEffect());
                        player.setSkillPointAllocations(oldAllocations);
                    } else {
                        Platform.runLater(() -> {
                            equipped.setText("Not Equipped");
                            equipped.setTextFill(Color.RED);
                            equip.setText("Equip");
                        });
                        equippableItem.setEquipped(false);
                        HashMap<String, Integer> oldAllocations = player.getSkillPointAllocations();
                        int oldPoints = oldAllocations.get(equippableItem.getSkill());
                        oldAllocations.replace(equippableItem.getSkill(),
                                oldPoints - equippableItem.getEffect());
                        player.setSkillPointAllocations(oldAllocations);
                    }
                    updatePoints();
                });

                setText(null);
                setGraphic(pane);
            }
        }
    }

    /**
     * The type No selection model.
     *
     * @param <T> the type parameter
     */
    private class NoSelectionModel<T> extends MultipleSelectionModel<T> {

        @Override
        public ObservableList<Integer> getSelectedIndices() {
            return FXCollections.emptyObservableList();
        }

        @Override
        public ObservableList<T> getSelectedItems() {
            return FXCollections.emptyObservableList();
        }

        @Override
        public void selectIndices(int index, int... indices) {
        }

        @Override
        public void selectAll() {
        }

        @Override
        public void selectFirst() {
        }

        @Override
        public void selectLast() {
        }

        @Override
        public void clearAndSelect(int index) {
        }

        @Override
        public void select(int index) {
        }

        @Override
        public void select(T obj) {
        }

        @Override
        public void clearSelection(int index) {
        }

        @Override
        public void clearSelection() {
        }

        @Override
        public boolean isSelected(int index) {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public void selectPrevious() {
        }

        @Override
        public void selectNext() {
        }
    }
}
