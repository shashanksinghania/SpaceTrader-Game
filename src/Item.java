import javafx.scene.image.Image;

/**
 * The interface Item.
 */
public interface Item {
    /**
     * Sets name.
     *
     * @param s the s
     */
    void setName(String s);

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets cost price.
     *
     * @return the cost price
     */
    int getCostPrice();

    /**
     * Sets cost price.
     *
     * @param costPrice the cost price
     */
    void setCostPrice(int costPrice);

    /**
     * Sets sell price.
     *
     * @param sellPrice the sell price
     */
    void setSellPrice(int sellPrice);

    /**
     * Sets power.
     *
     * @param power the power
     */
    void setPower(int power);

    /**
     * Sets charge.
     *
     * @param charge the charge
     */
    void setCharge(String charge);

    /**
     * Gets sell price.
     *
     * @return the sell price
     */
    int getSellPrice();

    /**
     * Sets type.
     *
     * @param s the s
     */
    void setType(String s);

    /**
     * Gets type.
     *
     * @return the type
     */
    String getType();

    /**
     * Gets image.
     *
     * @return the image
     */
    Image getImage();
}
