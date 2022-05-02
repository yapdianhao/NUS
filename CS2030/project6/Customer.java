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
    private int serverID;
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

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public int getServerID() {
        return serverID;
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
        if (state.equals("arrives")) {
            return String.format("%.3f %d arrives", getTime(), id, getServerID());
        } else if (state.equals("served")) {
            return String.format("%.3f %d served by %d", getTime(), id, getServerID());
        } else if (state.equals("waits")) {
            return String.format("%.3f %d waits to be served by %d", getTime(), id, getServerID());
        } else if (state.equals("done")) {
            return String.format("%.3f %d done serving by %d", getTime(), id, getServerID());
        } else { 
            return String.format("%.3f %d leaves", getTime(), id);
        }
    }

}
