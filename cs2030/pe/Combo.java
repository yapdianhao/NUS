import java.util.ArrayList;

public class Combo extends Item {
	Burger burger;
	Snack snack;
	Drink drink;

	public Combo(String type, Burger burger, Snack snack, Drink drink) {
		super(type);
		this.burger = burger;
		this.snack = snack;
		this.drink = drink;
		this.price = this.burger.price + this.snack.price + this.drink.price - 50;
	//	foods = new ArrayList<Integer>();
	}

	@Override
	public String toString() {
		return String.format("#%d %s (%d)", id, type, price) +
		       	"\n   " + burger.toString() +
			"\n   " + snack.toString() + 
			"\n   " + drink.toString();
	}	
}	
