/**
 * Encapsulates a server which is able to serve a customer.
 */
public class Server {

    /**
     * The time for a server to serve a customer.
     * Constant.
     */
    private int serveTime = 1;

    private double time;

    /** 
     * A boolean value to determine if the server
     * is serving a customer.
     */
    private boolean isIdle = true;

    public Server() {
    }
    
    /**
     * Serves customer based on server's state.
     */
    public void serveCustomer(Customer customer) {
        if (isIdle) {
            isIdle = false;
            time = customer.getTime() + serveTime;
            System.out.println(String.format("Customer served, " + 
                   "next service @ %.3f", time));
        } else if (!isIdle && customer.getTime() < time) {
            System.out.println("Customer leaves");
        } else if (!isIdle && customer.getTime() >= time) {
            isIdle = true;
            serveCustomer(customer);
        }
    }
}

