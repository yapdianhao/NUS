class SavingsAccount implements Comparable<SavingsAccount>{
	Employee employee;
	Bank bank;
	double raise;
	double savings;

	public SavingsAccount(Employee employee, Bank bank) {
		this.employee = employee;
		this.bank = bank;
		savings = 0;
		raise = employee.adjustment.adjust(employee.getIncrease()) / 100;
	}


	public double compute(int months) {
		double total = 0;
		if (months <=  12) {
			for (int i = 0; i < months; i++) {
				savings += employee.updatedSalary;
				savings = savings * (1 + bank.monthly);
			}
		}
		else {
			savings = compute(12);
			employee.updateSalary();
			savings = compute(months - 12);
		}
		return savings;
	}

	@Override
	public int compareTo(SavingsAccount other) {
		if (savings > other.savings) {
			return 1;
		}
		else if (savings < other.savings) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
