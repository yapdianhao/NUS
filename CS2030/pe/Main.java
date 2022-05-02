import java.util.Scanner;
import java.util.ArrayList;

class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Burger> burgers = new ArrayList<Burger>();
		ArrayList<Snack> snacks = new ArrayList<Snack>();
		ArrayList<Drink> drinks = new ArrayList<Drink>();
		Menu menu = new Menu();

		while(sc.next().equals("add")) {
			String type = sc.next();
			if (! type.equals("Combo")) {
				String desc = sc.next();
				int price = sc.nextInt();
				menu.addToMenu(type, desc, price);
			}
			else {
				int burgerID = sc.nextInt();
				int snackID = sc.nextInt();
				int drinkID = sc.nextInt();
				menu.addComboToMenu(type, burgerID, snackID, drinkID);
			}
		}

		menu.showMenu();

		Order order = new Order(menu);
		while (sc.hasNext()) {
			order.orderIDs.add(sc.nextInt());
		}
		order.placeOrder();
		System.out.println(order.toString());
		order.showOrders();

	}
}

