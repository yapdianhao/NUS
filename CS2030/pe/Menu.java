import java.util.ArrayList;

public class Menu {
	ArrayList<Item> items;
	ArrayList<Burger> burgers;
	ArrayList<Snack> snacks;
	ArrayList<Drink> drinks;
	ArrayList<Integer> foods;
	ArrayList<Combo> combos;

	public Menu() {
		items = new ArrayList<Item>();
		burgers = new ArrayList<Burger>();
		snacks = new ArrayList<Snack>();
		drinks = new ArrayList<Drink>();
		combos = new ArrayList<Combo>();
	}

	public void addToMenu(String type, String desc, int price) {
		if (type.equals("Burger")) {
			Burger burger = new Burger(type, desc, price);
			burgers.add(burger);
			items.add(burger);
		}
		else if (type.equals("Snack")) {
			Snack snack = new Snack(type, desc, price);
			snacks.add(snack);
			items.add(snack);
		}
		else if (type.equals("Drink")) {
			Drink drink = new Drink(type, desc, price);
			drinks.add(drink);
			items.add(drink);
		}
	}

	public  void addComboToMenu(String type, int burgerID, int snackID, int drinkID) {
		try {
			Combo combo = new Combo(type, (Burger)items.get(burgerID), (Snack)items.get(snackID), (Drink)items.get(drinkID));
			combos.add(combo);
			items.add(combo);
		}
	       	catch (IndexOutOfBoundsException | ClassCastException exception) {
			System.err.println("Error: Invalid combo input " + burgerID + " " + snackID + " " + drinkID);
		}
	}




	public void showMenu(){
		for (Burger burger : burgers) {
			System.out.println(burger.toString());
		}
		for (Snack snack : snacks) {
			System.out.println(snack.toString());
		}
		for (Drink drink : drinks) {
			System.out.println(drink.toString());
		}
		for (Combo combo : combos) {
			System.out.println(combo.toString());
		}
	}
}
