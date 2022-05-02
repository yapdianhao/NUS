import java.util.ArrayList;

/**
 * Encapsulates a server which is able to serve a customer.
 */
public class Server {

    private int serveTime = 1;
    private double time;
    private boolean isIdle = true;

    /**
     * ArrayList of events to record customer's state.
     */
    ArrayList<String> events;

    public Server() {
        events = new ArrayList<String>();
    }
    
    /**
     * Serves customer based on server's state.
     */
    public void serveCustomer(Customer customer) {
        if (isIdle) {
            isIdle = false;
            time = customer.getTime() + serveTime;
            customer.changeState("served");
        } else if (!isIdle && customer.getTime() < time) {
            customer.changeState("leaves");
        } else if (!isIdle && customer.getTime() >= time) {
            isIdle = true;
            serveCustomer(customer);
        }
    }
}

