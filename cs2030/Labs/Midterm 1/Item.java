public class Item implements Comparable<Item>, Buyable {
    private int id;
    private int cost;
    private String type;
    private String name;

    /**
     * Constructor.
     * @param type Type of the item
     * @param name Name or description of the item
     * @param cost The cost/price
     * @param id The identification number
     */
    public Item(String type, String name, int cost, int id) {
        this.cost = cost;
        this.name = name;
        this.type = type;
        this.id = id;
    }

    @Override
    public int getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    private int getRank() {
        int rank;
        if (type.equals("Burger")) {
            rank = 1;
        } else if (type.equals("Snack")) {
            rank = 2;
        } else {
            rank = 3;
        }
        return rank;
    }

    @Override
    public int compareTo(Item other) {
        if (getRank() != other.getRank()) {
            return getRank() - other.getRank();
        }
        return id - other.id;
    }

    @Override
    public String toString() {
        return "#" + id + " " + type + ": " + name + " (" + cost + ")";
    }
}
