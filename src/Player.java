import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The type Player.
 */
public class Player {
    /**
     * The Name.
     */
    private String name;
    /**
     * The Credits.
     */
    private double credits;
    /**
     * The Skill points.
     */
    private int skillPoints;
    /**
     * The Difficulty.
     */
    private String difficulty;
    /**
     * The Skill point allocations.
     */
    private HashMap<String, Integer> skillPointAllocations;

    private Ship ship;

    private ArrayList<EquippableItem> equippableItems = new ArrayList<EquippableItem>();

    private ArrayList<Item> items;

    private List<Item> weapon = new ArrayList<>();

    private List<Item> shield = new ArrayList<>();

    private List<Item> gadgets = new ArrayList<>();

    public List<Item> getWeapon() {
        return weapon;
    }

    public void setWeapon(Item item) {
        this.weapon.add(item);
    }

    public void removeWeapon(Item item) {
        this.weapon.remove(item);
    }

    public List<Item> getShield() {
        return shield;
    }

    public void setShield(Item item) {
        this.shield.add(item);
    }

    public void removeShield(Item item) {
        this.shield.remove(item);
    }

    public List<Item> getGadgets() {
        return gadgets;
    }

    public void setGadgets(Item item) {
        this.gadgets.add(item);
    }

    public void removeGadgets(Item item) {
        this.gadgets.remove(item);
    }

    public int getTotalNumItems() {
        return weapon.size() + gadgets.size() + shield.size();
    }



    /**
     * Instantiates a new Player.
     *
     * @param name                  the name
     * @param credits               the credits
     * @param difficulty            the difficulty
     * @param skillPointAllocations the skill point allocations
     */
    public Player(String name, double credits, String difficulty, HashMap<String, Integer>
            skillPointAllocations) {
        this.name = name;
        this.credits = credits;
        this.difficulty = difficulty;
        this.skillPointAllocations = skillPointAllocations;
        skillPoints = 0;
        for (Integer points : skillPointAllocations.values()) {
            skillPoints += points;
        }
        items = new ArrayList<>();
    }

    public void addItem(Item... item) {
        items.addAll(Arrays.asList(item));
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    /**
     * Gets skill point allocations.
     *
     * @return the skill point allocations
     */
    public HashMap<String, Integer> getSkillPointAllocations() {
        return skillPointAllocations;
    }

    /**
     * Sets skill point allocations.
     *
     * @param skillPointAllocations the skill point allocations
     */
    public void setSkillPointAllocations(HashMap<String, Integer> skillPointAllocations) {
        this.skillPointAllocations = skillPointAllocations;
    }

    /**
     * Gets difficulty.
     *
     * @return the difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets difficulty.
     *
     * @param difficulty the difficulty
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Instantiates a new Player.
     */
    public Player() {

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets credits.
     *
     * @return the credits
     */
    public double getCredits() {
        return credits;
    }

    /**
     * Gets skill points.
     *
     * @return the skill points
     */
    public int getSkillPoints() {
        return skillPoints;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets credits.
     *
     * @param credits the credits
     */
    public void setCredits(double credits) {
        this.credits = credits;
    }

    /**
     * Sets skill points.
     *
     * @param skillPoints the skill points
     */
    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }


    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public void addEquippableItem(String item, String skill, int change) {
        addEquippableItem(new EquippableItem(item, false, skill, change));
    }

    public void addEquippableItem(EquippableItem item) {
        equippableItems.add(item);
    }

    public void removeEquippableItem(EquippableItem item) {
        equippableItems.remove(item);
    }

    public ArrayList<EquippableItem> getEquippableItems() {
        return equippableItems;
    }

    public void setEquippableItems(ArrayList<EquippableItem> equippableItems) {
        this.equippableItems = equippableItems;
    }
}
