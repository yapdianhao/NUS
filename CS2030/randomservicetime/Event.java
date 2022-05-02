package cs2030.simulator;

/**
 * Encapsulates an event of a customer's current status.
 */

public class Event {
    private final double time;
    private final int customerID;
    private final String customerEvent;
    private final String customerState;
    private Customer customer;

    /**
     * Constructor. 
     * Takes in a customer and displays information 
     * based on the current customer's state.
     */
    public Event(Customer customer) {
        this.time = customer.getTime();
        this.customerID = customer.getID();
        this.customer = customer;
        this.customerState = customer.getState();
        this.customerEvent = customer.toString();
    }

    public double getTime() {
        return time;
    }

    public int getID() {
        return customerID;
    }

    public String getState() {
        return customerState;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return customerEvent;
    }
}
