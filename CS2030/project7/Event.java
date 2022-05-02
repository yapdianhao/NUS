/**
 * Encapsulates an event of a customer's current status.
 */

public class Event {
    private double time;
    private int customerID;
    private String customerState;
    private String customerEvent;

    /**
     * Constructor of an event.
     * Takes in a customer and displays the customer's current time and state.
     */
    public Event(Customer customer) {
        this.time = customer.getTime();
        this.customerID = customer.getID();
        this.customerEvent = customer.toString();
        this.customerState = customer.getState();
    }

    public double getTime() {
        return time;
    }

    public String getState() {
        return customerState;
    }

    public int getID() {
        return customerID;
    }

    @Override
    public String toString() {
        return customerEvent;
    }
}
