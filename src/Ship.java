import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class Ship {
    private String name;
    private int cargoCapacity;
    private int fuelCapacity;
    private List<Item> itemInventory;
    private int health;
    private Image image;
    private ImageView imageView;

    public Ship(String name) {
        this.name = name;
    }

    public Ship(String name, int cargoCapacity, List<Item> itemInventory,
                int fuelCapacity, int health) {
        this.name = name;
        this.cargoCapacity = cargoCapacity;
        this.itemInventory = itemInventory;
        this.fuelCapacity = fuelCapacity;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public List<Item> getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(List<Item> itemInventory) {
        this.itemInventory = itemInventory;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void addItem(Item item) {
        itemInventory.add(item);
    }

    public Item removeItem(Item item) {
        itemInventory.remove(item);
        return item;
    }
}
