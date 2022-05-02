public class Burger extends Item{
	String desc;

	public Burger(String type, String desc, int price) {
		super(type);
		this.desc = desc;
		this.price = price;
	}

	@Override 
	public String toString() {
		return String.format("#%d %s: %s (%d)", id, type, desc, price);
	}
}
