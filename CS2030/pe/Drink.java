public class Drink extends Item {
	String desc;

	public Drink(String type, String desc, int price) {
		super(type);
		this.desc = desc;
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("#%d %s: %s (%d)", id, type, desc, price);
	}
}
