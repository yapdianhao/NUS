public class Customer {
    
    private int customerID;
    public CustomerState state = CustomerState.BEFOREARRIVAL;
    private CustomerState nextState;
    public double stateChangeTime;
    private double startWaitingTime = -1;
    
    /**
     * Constructor for Customer.
     * @param customerID The ID
     * @param arrivalTime The arrivial time of that customer
     */
    public Customer(int customerID, double arrivalTime) {
        this.customerID = customerID;
        this.stateChangeTime = arrivalTime;
    }
    
    /**
     * Customer arrives.
     */
    public void arrive(double currentTime) {
        if (state == CustomerState.BEFOREARRIVAL) {
            state = CustomerState.ARRIVED;
            System.out.println(String.format("%.3f", currentTime) + " " + customerID + " arrives");
            return;
        }
        throw new RuntimeException("Customer cannot arrive.");
    }

    /**
     * Customer being served.
     * @param servingEndTime when customer is done serving
     * @param currentTime the current time
     */
    public double serve(double servingEndTime, double currentTime) {
        state = CustomerState.SERVED;
        System.out.println(String.format("%.3f", currentTime) + " " 
                + customerID + " served");
        stateChangeTime = servingEndTime;
        return currentTime - startWaitingTime;
    }

    /**
     * Customer leaving.
     * @param currentTime the current time
     */
    public void leave(double currentTime) {
        if (state != CustomerState.ARRIVED) {
            throw new RuntimeException("Customer cannot leave");
        }
        state = CustomerState.LEFT; // ARRIVED -> LEFT
        System.out.println(String.format("%.3f", currentTime) + " " 
                + customerID + " leaves");
        stateChangeTime = -1;
    }

    /**
     * Customer is done being served.
     * @param currentTime the current time
     */
    public void done(double currentTime) {
        if (state != CustomerState.SERVED) {
            throw new RuntimeException("Customer is not done");
        }
        state = CustomerState.DONE;
        System.out.println(String.format("%.3f", currentTime) + " " 
                + customerID + " done");
        stateChangeTime = -1;
    }

    /**
     * Customer is waiting.
     * @param currentTime the current time
     */
    public void wait(double currentTime) {
        if (state != CustomerState.ARRIVED) {
            throw new RuntimeException("Customer cannot wait");
        }
        state = CustomerState.WAITING;
        System.out.println(String.format("%.3f", currentTime) + " " 
                + customerID + " waits");
        stateChangeTime = -1;
        startWaitingTime = currentTime;
    }

}
