import javafx.scene.image.Image;

public class Weapon implements Item {
    private String name;
    private String type;
    private int costPrice;
    private int sellPrice;
    private int power;
    private String charge;
    private Image image;

    public Weapon() {
        this(null, 0, 0, 0, null);
    }

    public Weapon(String name, int costPrice, int sellPrice, int power, String charge) {
        this.name = name;
        this.type = "Weapon";
        this.costPrice = costPrice;
        this.sellPrice = sellPrice;
        this.power = power;
        this.charge = charge;
        this.image = new Image("weapon.png");
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public Image getImage() {
        return this.image;
    }
}
