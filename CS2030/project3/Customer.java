/**
 * Encapsulates a customer.
 */
public class Customer {
    private static int count;
    private int id;
    private double time;
    /**
     * Sets customer's initial state to arrives.
     */
    private String state = "arrives";

    /**
     * Constructs a new Customer with the given time.
     */
    public Customer(double time) {
        id = ++count; // increments customer id for each customer created.
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public String getState() {
        return state;
    }

    public void changeState(String state) {
        this.state = state;
    }

    public void changeTime(double time) {
        this.time = time;
    }

    /** 
    * Returns a string representation of the customer.
    */   
    @Override
    public String toString() {
        return String.format("%.3f %d %s", time, id, state);
    }
}
