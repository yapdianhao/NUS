/**
 * Encapsulates a customer.
 */
public class Customer {
    private static int count;
    private int id;
    private double arriveTime;
    private double servedTime;
    private double waitTime;
    private double doneTime;
    private double leaveTime;

    /**
     * Sets customer's initial state to arrives.
     */
    private String state = "arrives";

    /**
     * Constructs a new Customer with the given time.
     */
    public Customer(double time) {
        id = ++count; // increments customer id for each customer created.
        this.arriveTime = time;
    }

    public int getid() {
        return id;
    }

    /**
     * Returns the time according to customer's current state.
     */
    public double getTime() {
        if (state.equals("arrives")) {
            return arriveTime;
        } else if (state.equals("served")) {
            return servedTime;
        } else if (state.equals("waits")) {
            return waitTime;
        } else if (state.equals("done")) {
            return doneTime;
        } else {
            return leaveTime;
        }
    }

    public String getState() {
        return state;
    }

    public void changeState(String state) {
        this.state = state;
    }

    public void setServedTime(double time) {
        servedTime = time;
    }

    public void setWaitTime(double time) {
        waitTime = time;
    }

    public void setLeaveTime(double time) {
        leaveTime = time;
    }

    public void setDoneTime(double time) {
        doneTime = time;
    }

    /**
     * Calculates and returns the time spent waiting.
     */
    public double getWaitTime() {
        return servedTime - waitTime;
    }

    /** 
    * Returns a string representation of the customer.
    */   
    @Override
    public String toString() {
        return String.format("%.3f %d %s", getTime(), id, state);
    }
}
