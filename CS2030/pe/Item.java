public abstract class Item {
	int id;
	static int count = 0;
	String type;
	int price;
	
	public Item(String type) {
		this.type = type;
		id = count;
		count ++;
	}

	@Override
	public abstract String toString();
}

