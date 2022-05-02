import java.util.ArrayList;

public class Order {
	ArrayList<Integer> orderIDs = new ArrayList<Integer>();
	ArrayList<Item> orders = new ArrayList<Item>();
	Menu menu;

	public Order(Menu menu) {
		this.menu = menu;
	}

	public void placeOrder() {
		for (int i : orderIDs) {
			for (Item item : menu.items) {
				if (item.id == i) {
					orders.add(item);
				}
			}
		}	
	}

	@Override
	public String toString() {
		return String.format("--- Order ---");
	}

	public void showOrders() {
		int total = 0;
		for (Item item : orders) {
			total += item.price;
			System.out.println(item.toString());
		}
		System.out.println(String.format("Total: %d", total));
	}
}

