/**
 * Encapsulates a customer.
 */
public class Customer {
    private static int count;
    private int id;
    private double time;

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

    /** 
    * Returns a string representation of the customer.
    */   
    @Override
    public String toString() {
        return String.format("%d arrives at %.3f", id, time);
    }
}
