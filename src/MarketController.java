import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * The type Market controller.
 *
 * @author Team 12
 * @version 1.0
 */
public class MarketController {

    /**
     * The Random generator.
     */
    private RandomGenerator randomGenerator = new RandomGenerator();

    /**
     * The Current weapon list.
     */
    @FXML
    private ListView<String> currentWeaponList;
    /**
     * The Current shield list.
     */
    @FXML
    private ListView<String> currentShieldList;
    /**
     * The Current gadgets list.
     */
    @FXML
    private ListView<String> currentGadgetsList;
    /**
     * The Current equippable items list.
     */
    @FXML
    private ListView<String> currentEquipItemListView;
    /**
     * The Sale weapon list.
     */
    @FXML
    private ListView<String> saleWeaponList;
    /**
     * The Sale shield list.
     */
    @FXML
    private ListView<String> saleShieldList;
    /**
     * The Sale gadgets list.
     */
    @FXML
    private ListView<String> saleGadgetsList;
    /**
     * The Sale equippable item list.
     */
    @FXML
    private ListView<String> saleEquipItemListView;
    /**
     * The description.
     */
    @FXML
    private Label description;
    /**
     * The credits.
     */
    @FXML
    private Label credits;
    /**
     * The Region.
     */
    @FXML
    private Label cargoCapacity;

    /**
     * The Item image view.
     */
    @FXML
    private ImageView itemImageView;

    /**
     * The Player.
     */
    private Player player;

    /**
     * The Map.
     */
    private TreeMap<String, List<List<Item>>> map;
    /**
     * The Weapons.
     */
    private List<Item> weapons;
    /**
     * The Shields.
     */
    private List<Item> shields;
    /**
     * The Gadgets.
     */
    private List<Item> gadgets;
    /**
     * The Equip items.
     */
    private List<Item> equipItems;

    /**
     * The Ship.
     */
    private Ship ship;

    /**
     * Sets region.
     *
     * @param region the region
     * @param map    the map
     */
    public void setRegion(Region region, TreeMap<String, List<List<Item>>> map) {
        this.map = map;
        if (map.get(region.getName()) == null) {
            weapons = randomGenerator.randomize("Weapon", region, player);
            shields = randomGenerator.randomize("Shield", region, player);
            gadgets = randomGenerator.randomize("Gadgets", region, player);
            equipItems = randomGenerator.randomize("Equip", region, player);
            if (!UniverseController.isWinItemAllocated()) {
                Item winItem = randomGenerator.getWinItem();
                if (winItem != null) {
                    weapons.add(winItem);
                }
            }
            map.put(region.getName(), Arrays.asList(weapons, shields, gadgets, equipItems));
        } else {
            weapons = map.get(region.getName()).get(0);
            shields = map.get(region.getName()).get(1);
            gadgets = map.get(region.getName()).get(2);
            equipItems = map.get(region.getName()).get(3);
        }
        updateSaleItems();
        updateCurrentItems();
        credits.setText("Credits: " + player.getCredits());
        cargoCapacity.setText("Cargo Capacity: "
                + (player.getShip().getCargoCapacity() - player.getTotalNumItems()));
        // Sets handlers for all list views to show selected item's price
        ListView[] listViews = {currentWeaponList, saleWeaponList,
            currentShieldList, saleShieldList, currentGadgetsList,
            saleGadgetsList, currentEquipItemListView, saleEquipItemListView};
        List[] lists = {player.getWeapon(), weapons,
            player.getShield(), shields, player.getGadgets(),
            gadgets, player.getEquippableItems(), equipItems};
        for (int i = 0; i < listViews.length; i++) {
            int finalI = i;
            listViews[i].setOnMouseClicked(mouseEvent -> {
                Item item = null;
                for (Object y : lists[finalI]) {
                    Item x = (Item) y;
                    String itemName = x.getName();
                    if (itemName.equals(listViews[finalI].getSelectionModel().getSelectedItem())) {
                        item = x;
                    }
                }
                if (!(item == null)) {
                    String effect = "";
                    if (item.getType().equals("Equip")) {
                        effect = "Effect: " + ((EquippableItem) item).getSkill() + " + "
                                + ((EquippableItem) item).getEffect();
                    }
                    if (finalI % 2 == 0) {
                        description.setText(item.getName() + "\n" + effect + "\n"
                                + " Selling Price: " + calculateSellPrice(item.getSellPrice()));
                    } else {
                        description.setText(item.getName() + "\n" + effect + "\n"
                                + "  Cost Price: " + calculateCostPrice(item.getCostPrice()));
                    }
                    itemImageView.setImage(item.getImage());
                }
            });

        }

    }

    /**
     * Calculates cost price based on the player's trading skill
     * (the more trader skill points, the lower the cost price)
     * Should range between 0.6 and 1.0 of item's original cost price
     *
     * @param costPrice of base item
     * @return actual costPrice
     */
    private int calculateCostPrice(int costPrice) {
        return (int) (costPrice -  (double) player.getSkillPointAllocations().get("trader")
                / 25 * 0.4 * costPrice);
    }

    /**
     * Calculates sell price based on the player's trading skill
     * (the more trader skill points, the higher the sell price)
     * Should range between 1 and 2.5 of item's original cost price
     *
     * @param sellPrice of base item
     * @return actual sellPrice
     */
    private int calculateSellPrice(int sellPrice) {
        return (int) (sellPrice +  (double) player.getSkillPointAllocations().get("trader")
                / 25 * 1.5 * sellPrice);
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
     * Sets ship.
     *
     * @param ship the ship
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Buy shield.
     */
    @FXML
    private void buyShield() {
        if (saleShieldList.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (!checkCargoCapacity()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Ship is full!");
            errorAlert.showAndWait();
            return;
        }
        Item item = new Shield();
        for (Item x : shields) {
            if (x.getName().equals(saleShieldList.getSelectionModel().getSelectedItem())) {
                item = x;
            }
        }
        if (player.getCredits() >= item.getCostPrice()) {
            player.setShield(item);
            shields.remove(item);
            ship.addItem(item);
            updateCurrentItems();
            updateSaleItems();
            player.setCredits((player.getCredits() - calculateCostPrice(item.getCostPrice())));
            credits.setText("Credits: " + player.getCredits());
            cargoCapacity.setText("Cargo Capacity: "
                    + (player.getShip().getCargoCapacity() - player.getTotalNumItems()));
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "You are broke!");
            errorAlert.showAndWait();
        }
    }

    /**
     * Buy weapon.
     */
    @FXML
    private void buyWeapon() {
        if (saleWeaponList.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (!checkCargoCapacity()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Ship is full!");
            errorAlert.showAndWait();
            return;
        }
        Item item = new Weapon();
        for (Item x : weapons) {
            if (x.getName().equals(saleWeaponList.getSelectionModel().getSelectedItem())) {
                item = x;
            }
        }
        if (player.getCredits() >= item.getCostPrice()) {
            if (item.getName().equals("WINNING ITEM")) {
                sceneManager.goToGameOverScreen(sceneManager, player, "win");
            }
            player.setWeapon(item);
            weapons.remove(item);
            ship.addItem(item);
            updateCurrentItems();
            updateSaleItems();
            player.setCredits((player.getCredits() - calculateCostPrice(item.getCostPrice())));
            credits.setText("Credits: " + player.getCredits());
            cargoCapacity.setText("Cargo Capacity: "
                    + (player.getShip().getCargoCapacity() - player.getTotalNumItems()));
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "You are broke!");
            errorAlert.showAndWait();
        }
    }

    /**
     * Buy gadget.
     */
    @FXML
    private void buyGadget() {
        if (saleGadgetsList.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (!checkCargoCapacity()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Ship is full!");
            errorAlert.showAndWait();
            return;
        }
        Item item = new Gadget();
        for (Item x : gadgets) {
            if (x.getName().equals(saleGadgetsList.getSelectionModel().getSelectedItem())) {
                item = x;
            }
        }
        if (player.getCredits() >= item.getCostPrice()) {
            player.setGadgets(item);
            gadgets.remove(item);
            ship.addItem(item);
            updateCurrentItems();
            updateSaleItems();
            player.setCredits((player.getCredits() - calculateCostPrice(item.getCostPrice())));
            credits.setText("Credits: " + player.getCredits());
            cargoCapacity.setText("Cargo Capacity: "
                    + (player.getShip().getCargoCapacity() - player.getTotalNumItems()));
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "You are broke!");
            errorAlert.showAndWait();
        }
    }

    /**
     * Buy equippable item.
     */
    @FXML
    private void buyEquipItem() {
        Item item = null;
        for (Item x : equipItems) {
            if (x.getName().equals(saleEquipItemListView.getSelectionModel().getSelectedItem())) {
                item = x;
            }
        }
        if (item == null) {
            return;
        }
        if (player.getCredits() >= item.getCostPrice()) {
            EquippableItem equipItem = (EquippableItem) item;
            player.addEquippableItem(equipItem);
            equipItems.remove(item);
            updateCurrentItems();
            updateSaleItems();
            player.setCredits((player.getCredits() - calculateCostPrice(item.getCostPrice())));
            credits.setText("Credits: " + player.getCredits());
            equipItem.setEquipped(true);
            HashMap<String, Integer> oldAllocations = player.getSkillPointAllocations();
            int oldPoints = oldAllocations.get(equipItem.getSkill());
            oldAllocations.replace(equipItem.getSkill(), oldPoints + equipItem.getEffect());
            player.setSkillPointAllocations(oldAllocations);
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "You are broke!");
            errorAlert.showAndWait();
        }
    }

    /**
     * Update current items.
     */
    private void updateCurrentItems() {
        List<Item> currentWeapons = player.getWeapon();
        List<Item> currentShields = player.getShield();
        List<Item> currentGadgets = player.getGadgets();
        List<EquippableItem> currentEquipItems = player.getEquippableItems();
        currentWeaponList.getItems().clear();
        for (Item x : currentWeapons) {
            currentWeaponList.getItems().add(x.getName());
        }
        currentShieldList.getItems().clear();
        for (Item x : currentShields) {
            currentShieldList.getItems().add(x.getName());
        }
        currentGadgetsList.getItems().clear();
        for (Item x : currentGadgets) {
            currentGadgetsList.getItems().add(x.getName());
        }
        currentEquipItemListView.getItems().clear();
        for (EquippableItem x : currentEquipItems) {
            currentEquipItemListView.getItems().add(x.getName());
        }

    }

    /**
     * Update sale items.
     */
    private void updateSaleItems() {
        saleWeaponList.getItems().clear();
        for (Item x : weapons) {
            saleWeaponList.getItems().add(x.getName());
        }
        saleShieldList.getItems().clear();
        for (Item x : shields) {
            saleShieldList.getItems().add(x.getName());
        }
        saleGadgetsList.getItems().clear();
        for (Item x : gadgets) {
            saleGadgetsList.getItems().add(x.getName());
        }
        saleEquipItemListView.getItems().clear();
        for (Item x : equipItems) {
            if (x != null && !player.getEquippableItems().contains(x)) {
                saleEquipItemListView.getItems().add(x.getName());
            }
        }
    }

    /**
     * Check cargo capacity boolean.
     *
     * @return the boolean
     */
    private boolean checkCargoCapacity() {
        int numItems = player.getWeapon().size() + player.getShield().size()
                + player.getGadgets().size();
        if (player.getShip().getCargoCapacity() < numItems + 1) {
            return false;
        }
        return true;
    }

    /**
     * Sell weapon.
     */
    @FXML
    private void sellWeapon() {
        if (currentWeaponList.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Item item = new Weapon();
        for (Item x : player.getWeapon()) {
            if (x.getName().equals(currentWeaponList.getSelectionModel().getSelectedItem())) {
                item = x;
            }
        }
        player.removeWeapon(item);
        weapons.add(item);
        ship.removeItem(item);
        updateCurrentItems();
        updateSaleItems();
        player.setCredits((player.getCredits() + calculateSellPrice(item.getSellPrice())));
        credits.setText("Credits: " + player.getCredits());
        cargoCapacity.setText("Cargo Capacity: "
                + (player.getShip().getCargoCapacity() - player.getTotalNumItems()));
    }

    /**
     * Sell shield.
     */
    @FXML
    private void sellShield() {
        if (currentShieldList.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Item item = new Shield();
        for (Item x : player.getShield()) {
            if (x.getName().equals(currentShieldList.getSelectionModel().getSelectedItem())) {
                item = x;
            }
        }
        player.removeShield(item);
        shields.add(item);
        ship.removeItem(item);
        updateCurrentItems();
        updateSaleItems();
        player.setCredits((player.getCredits() + calculateSellPrice(item.getSellPrice())));
        credits.setText("Credits: " + player.getCredits());
        cargoCapacity.setText("Cargo Capacity: "
                + (player.getShip().getCargoCapacity() - player.getTotalNumItems()));
    }

    /**
     * Sell gadget.
     */
    @FXML
    private void sellGadget() {
        if (currentGadgetsList.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Item item = new Gadget();
        for (Item x : player.getGadgets()) {
            if (x.getName().equals(currentGadgetsList.getSelectionModel().getSelectedItem())) {
                item = x;
            }
        }
        player.removeGadgets(item);
        gadgets.add(item);
        ship.removeItem(item);
        updateCurrentItems();
        updateSaleItems();
        player.setCredits((player.getCredits() + calculateSellPrice(item.getSellPrice())));
        credits.setText("Credits: " + player.getCredits());
        cargoCapacity.setText("Cargo Capacity: "
                + (player.getShip().getCargoCapacity() - player.getTotalNumItems()));
    }

    /**
     * Sell equippable item.
     */
    @FXML
    private void sellEquipItem() {
        Item item = null;
        for (Item x : player.getEquippableItems()) {
            String itemName = x.getName();
            if (itemName.equals(currentEquipItemListView.getSelectionModel().getSelectedItem())) {
                item = x;
            }
        }
        if (item == null) {
            return;
        }
        EquippableItem equipItem = (EquippableItem) item;
        player.removeEquippableItem(equipItem);
        equipItems.add(item);
        updateCurrentItems();
        updateSaleItems();
        player.setCredits((player.getCredits() + calculateSellPrice(item.getSellPrice())));
        credits.setText("Credits: " + player.getCredits());
        if (equipItem.isEquipped()) {
            equipItem.setEquipped(false);
            HashMap<String, Integer> oldAllocations = player.getSkillPointAllocations();
            int oldPoints = oldAllocations.get(equipItem.getSkill());
            oldAllocations.replace(equipItem.getSkill(), oldPoints - (equipItem.getEffect()));
            player.setSkillPointAllocations(oldAllocations);
        }
    }


    /**
     * Go back.
     */
    @FXML
    private void goBack() {
        sceneManager.goBackToUniverseView(sceneManager, map);
    }


    /**
     * The Scene manager.
     */
    private SceneManager sceneManager;

    /**
     * Sets scene manager.
     *
     * @param sceneManager the scene manager
     */
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
