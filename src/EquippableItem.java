import javafx.scene.image.Image;

/**
 * The type Equippable item.
 */
public class EquippableItem implements Item {
    /**
     * The Name.
     */
    private String name;
    /**
     * The Equipped.
     */
    private boolean equipped;
    /**
     * The Skill.
     */
    private String skill;
    /**
     * The Effect.
     */
    private int effect;
    /**
     * The Cost price.
     */
    private int costPrice;
    /**
     * The Sell price.
     */
    private int sellPrice;
    /**
     * The Power.
     */
    private int power;
    /**
     * The Charge.
     */
    private String charge;
    /**
     * The Type.
     */
    private String type = "Equip";
    private Image image;

    /**
     * Instantiates a new Equippable item.
     *
     * @param name     the name
     * @param equipped the equipped
     * @param skill    the skill
     * @param effect   the effect
     */
    public EquippableItem(String name, boolean equipped, String skill, int effect) {
        this.name = name;
        this.equipped = equipped;
        this.skill = skill;
        this.effect = effect;
        this.image = new Image("equipable.png");
    }

    /**
     * get name
     * @return name
     */
    public String getName() {
        return name;
    }

    @Override
    public int getCostPrice() {
        return costPrice;
    }

    @Override
    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    @Override
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public void setCharge(String charge) {
        this.charge = charge;
    }

    @Override
    public int getSellPrice() {
        return sellPrice;
    }

    public int getPower() {
        return power;
    }

    public String getCharge() {
        return charge;
    }

    @Override
    public void setType(String s) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    /**
     * set name
     * @param name set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Is equipped boolean.
     *
     * @return the boolean
     */
    public boolean isEquipped() {
        return equipped;
    }

    /**
     * Sets equipped.
     *
     * @param equipped the equipped
     */
    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    /**
     * Gets skill.
     *
     * @return the skill
     */
    public String getSkill() {
        return skill;
    }

    /**
     * Sets skill.
     *
     * @param skill the skill
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }

    /**
     * Gets effect.
     *
     * @return the effect
     */
    public int getEffect() {
        return effect;
    }

    /**
     * Sets effect.
     *
     * @param effect the effect
     */
    public void setEffect(int effect) {
        this.effect = effect;
    }

    /**
     * get Image
     * @return image
     */

    public Image getImage() {
        return this.image;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EquippableItem)) {
            return false;
        } else {
            return ((EquippableItem) o).getName().equals(name);
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
