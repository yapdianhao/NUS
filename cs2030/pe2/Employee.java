class Employee {
    String firstName;
    String lastName;
    double salary;
    double Increase;
    SalaryAdjust adjustment;
    double updatedSalary;

    public Employee (String firstName, String lastName, double salary, SalaryAdjust adjustment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.adjustment = adjustment;
	this.updatedSalary = salary;
    }

    public void setSalaryIncrease(double Increase) {
        this.Increase = Increase;
    }
    
    public double getSalary() {
        double d = salary/1000;
        return d;
    }

    public double getIncrease() {
        double d =( Increase - 1) * 100;
        return d;
    }

    public void updateSalary() {
	    updatedSalary = updatedSalary * (1 + adjustment.adjust(getIncrease()) / 100);
	    //System.out.println(updatedSalary);
    }

    @Override
    public String toString() {
        String s = String.format("%s %s: salary is %.0fK, annual raise is %.0f", firstName, lastName, getSalary(), adjustment.adjust(getIncrease()));
        return s + "%";
    }

}
