public class Customer {
    
    private int customerID;
    private double startWaitingTime;
    
    /**
     * Constructor for Customer.
     * @param customerID The ID
     */
    public Customer(int customerID) {
        this.customerID = customerID;
    }

    public int getID() {
        return customerID;
    }

    public double getWaitingTime() {
        return startWaitingTime;
    }

    public void setWaitingTime(double time) {
        this.startWaitingTime = time;
    }
}
