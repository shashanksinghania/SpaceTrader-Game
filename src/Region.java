import java.util.List;

/**
 * Create a Region with randomly generated coordinates
 * @author David
 */
public class Region {
    private boolean isVisited;
    private int size;
    private int techLevel;
    private String name;
    private int x;
    private int y;
    private String description;

    private List<Item> weapon;
    private List<Item> shield;
    private List<Item> gadgets;

    public List<Item> getWeapon() {
        return weapon;
    }

    public void setWeapon(List<Item> weapon) {
        this.weapon = weapon;
    }

    public List<Item> getShield() {
        return shield;
    }

    public void setShield(List<Item> shield) {
        this.shield = shield;
    }

    public List<Item> getGadgets() {
        return gadgets;
    }

    public void setGadgets(List<Item> gadgets) {
        this.gadgets = gadgets;
    }

    public Region(String name) {
        this.isVisited = false;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
