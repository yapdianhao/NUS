public class Combo implements Buyable {
    private Item burger;
    private Item snack;
    private Item drink;
    private int id;

    /**
     * Constructor.
     * @param burger burger
     * @param snack snack
     * @param drink drink
     * @param id identifition number
     */
    public Combo(Item burger, Item snack, Item drink, int id) {
        this.burger = burger;
        this.snack = snack;
        this.drink = drink;
        this.id = id;
    }

    @Override
    public int getCost() {
        return burger.getCost() + snack.getCost() + drink.getCost() - 50;
    }
    
    @Override
    public String toString() {
        return "#" + id + " Combo (" + getCost() + ")\n"
            + "   " + burger + "\n"
            + "   " + snack + "\n"
            + "   " + drink;
    }
}
