class Bank {
	static Bank[] BANKS;
	String name;
	double annual;
	double monthly;

	public Bank(String name, double annual) {
		this.name = name;
		this.annual = annual;
		monthly = annual / 12;
	}

	public static Bank getBankByName(String name) {
		int i = 0; 
		while (i < BANKS.length) {
			if (BANKS[i].name.equals(name)) {
				break;
			}
			else {
				i++;
			}
		}
		return BANKS[i];
	}
}
