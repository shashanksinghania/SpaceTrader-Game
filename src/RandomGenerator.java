import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
    private Random random = new Random();

    public List<Item> randomize(String type, Region region, Player player) {
        String[] names = {"Cool", "Hybrid", "Blue", "Laser", "Red", "Blaster", "Sword",
            "Birdie", "Arrow", "Flash", "Master", "Tension", "Buzz", "Doodle"};
        ArrayList<EquippableItem> allEquippableItems = new ArrayList<EquippableItem>();
        allEquippableItems.add(new EquippableItem("Vibranium Helmet", false, "fighter", 3));
        allEquippableItems.add(new EquippableItem("Hypnosis Scroll", false, "trader", 5));
        allEquippableItems.add(new EquippableItem("Sharingan", false, "pilot", 4));
        allEquippableItems.add(new EquippableItem("Yellow Jacket", false, "engineer", 99));
        allEquippableItems.add(new EquippableItem("Magic Armor", false, "fighter", 7));
        allEquippableItems.add(new EquippableItem("Bargaining Amulet", false, "trader", 3));
        allEquippableItems.add(new EquippableItem("GPS", false, "pilot", 2));
        allEquippableItems.add(new EquippableItem("Shiny Wrench", false, "engineer", 1));
        allEquippableItems.add(new EquippableItem("Hercules' Muscles", false, "fighter", 11));
        allEquippableItems.add(new EquippableItem("Bewitching Spell", false, "trader", 2));
        allEquippableItems.add(new EquippableItem("Auto-pilot", false, "pilot", 6));
        allEquippableItems.add(new EquippableItem("Mjolnir", false, "engineer", 8));
        allEquippableItems.add(new EquippableItem("Boxing Glove", false, "fighter", 1));
        allEquippableItems.add(new EquippableItem("One Ring To Rule Them All", false, "trader", 8));
        //allEquippableItems.add(new EquippableItem("Wings", false,"pilot",1));
        //allEquippableItems.add(new EquippableItem("Phineas' Brain", false,"engineer",4));

        int num = random.nextInt(2) + 3;

        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            String name = names[random.nextInt(names.length)] + names[random.nextInt(names.length)];
            int costPrice = (random.nextInt(10) + 5) * region.getTechLevel() + random.nextInt(10);
            int power = random.nextInt(10);

            String charge = Integer.toString(random.nextInt(50));

            Item object;

            if (type.equals("Weapon")) {
                object = new Weapon();
                name += "Gun";
            } else if (type.equals("Shield")) {
                object = new Shield();
                name += "Shield";
            } else if (type.equals("Gadgets")) {
                object = new Gadget();
                name += "Gadget";
            } else {
                object = allEquippableItems.remove(random.nextInt(allEquippableItems.size()));
            }
            if (!type.equals("Equip")) {
                object.setName(name);
            }
            object.setCostPrice(costPrice);
            object.setSellPrice(costPrice / 5);
            object.setCharge(charge);
            object.setPower(power);

            items.add(object);
        }

        return items;
    }

    public Item getARandomItem(int costPrice) {
        String[] names = {"Cool", "Hybrid", "Blue", "Laser", "Red", "Blaster", "Sword", "Birdie",
            "Arrow", "Flash", "Master", "Tension", "Buzz", "Doodle"};
        ArrayList<EquippableItem> allEquippableItems = new ArrayList<EquippableItem>();
        allEquippableItems.add(new EquippableItem("Vibranium Helmet", false, "fighter", 3));
        allEquippableItems.add(new EquippableItem("Hypnosis Scroll", false, "trader", 5));
        allEquippableItems.add(new EquippableItem("Sharingan", false, "pilot", 4));
        allEquippableItems.add(new EquippableItem("Yellow Jacket", false, "engineer", 99));
        allEquippableItems.add(new EquippableItem("Magic Armor", false, "fighter", 7));
        allEquippableItems.add(new EquippableItem("Bargaining Amulet", false, "trader", 3));
        allEquippableItems.add(new EquippableItem("GPS", false, "pilot", 2));
        allEquippableItems.add(new EquippableItem("Shiny Wrench", false, "engineer", 1));
        allEquippableItems.add(new EquippableItem("Hercules' Muscles", false, "fighter", 11));
        allEquippableItems.add(new EquippableItem("Bewitching Spell", false, "trader", 2));
        allEquippableItems.add(new EquippableItem("Auto-pilot", false, "pilot", 6));
        allEquippableItems.add(new EquippableItem("Mjolnir", false, "engineer", 8));
        allEquippableItems.add(new EquippableItem("Boxing Glove", false, "fighter", 1));
        allEquippableItems.add(new EquippableItem("One Ring To Rule Them All", false, "trader", 8));

        Random random = new Random();
        String name = names[random.nextInt(names.length)] + names[random.nextInt(names.length)];
        int power = random.nextInt(10);

        int randomType = random.nextInt(3);
        Item object;
        if (randomType == 0) {
            object = new Weapon();
            name += "Gun";
        } else if (randomType == 1) {
            object = new Shield();
            name += "Shield";
        } else {
            object = new Gadget();
            name += "Gadget";
        }

        String charge = Integer.toString(random.nextInt(50));
        object.setName(name);
        object.setCostPrice(costPrice);
        object.setCharge(charge);
        object.setPower(power);
        object.setSellPrice(costPrice / 5);

        return object;

    }

    public Item getWinItem() {
        Weapon item = null;
        if (UniverseController.getRegionsVisited() == 10 || random.nextDouble() < 0.8) {
            item = new Weapon();
            item.setCharge(Integer.toString(Integer.MAX_VALUE));
            item.setPower(Integer.MAX_VALUE);
            item.setName("WINNING ITEM");
            int costPrice = 150 + random.nextInt(10) * 10 + random.nextInt(10);
            item.setCostPrice(costPrice);
            item.setSellPrice(costPrice / 2);
            UniverseController.setWinItemAllocated(true);
        }
        return item;
    }
}
