import javafx.scene.image.Image;

/**
 * The type Gadget.
 */
public class Gadget implements Item {
    /**
     * The Name.
     */
    private String name;
    /**
     * The Type.
     */
    private String type;
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
     * The Image.
     */
    private Image image;

    /**
     * Instantiates a new Gadget.
     */
    public Gadget() {
        this(null, 0, 0, 0, null);
    }

    /**
     * Instantiates a new Gadget.
     *
     * @param name      the name
     * @param costPrice the cost price
     * @param sellPrice the sell price
     * @param power     the power
     * @param charge    the charge
     */
    public Gadget(String name, int costPrice, int sellPrice, int power, String charge) {
        this.name = name;
        this.type = "Gadget";
        this.costPrice = costPrice;
        this.sellPrice = sellPrice;
        this.power = power;
        this.charge = charge;
        this.image = new Image("gadget.png");
    }

    /**
     * get Name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * get cost
     * @return cost
     */
    public int getCostPrice() {
        return costPrice;
    }

    /**
     * get sell price
     * @return sell price
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * set Name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set Type
     * @param type of gadget
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * set cost
     * @param costPrice cost price
     */
    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * sell Price
     * @param sellPrice price
     */
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * set power
     * @param power of the gadget
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * set charge
     * @param charge of the gadget
     */
    public void setCharge(String charge) {
        this.charge = charge;
    }

    /**
     * get image
     * @return image
     */
    public Image getImage() {
        return this.image;
    }
}
