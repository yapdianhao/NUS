import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final double SERVICE_TIME = 1;
    private int numOfCustomers = 0;
    private List<Customer> listOfCustomers = new ArrayList<>();
    private double currentTime = 0;
    private double totalWaitingTime = 0;
    private int numOfServed = 0;
    private int numOfLeft = 0;
    private ServerState serverState = ServerState.IDLE;

    public enum ServerState {
        IDLE, SERVING
    }

    /**
     * Constructor.
     * @param arrivalTimings the list of customer arrival timings
     */
    public Server(List<Double> arrivalTimings) {
        for (Double timing : arrivalTimings) {
            numOfCustomers++;
            listOfCustomers.add(new Customer(numOfCustomers, (double)timing));
        }
    }

    /**
     * Gets average waiting time for customers served.
     * @return the average waiting time
     */
    public double getAverageWaitingTime() {
        return totalWaitingTime / numOfServed;
    }

    /**
     * Gets number of customers served.
     * @return Number of customers served
     */
    public int getNumServed() {
        return numOfServed;
    }

    /**
     * Gets the number of customers who left.
     * @return Number of customers who left
     */
    public int getNumLeft() {
        return numOfLeft;
    }
    /**
     * Gets the next customer with the next earliest event time.
     * @return the earliest customer, or null if no such customer
     */

    public Customer getNextEvent() {
        Customer nextCustomer = null;
        for (Customer customer : listOfCustomers) {
            if (customer.stateChangeTime < currentTime) {
                continue;
            }
            // Note that listOfCustomer is already in the order if the id, so there is no need
            // to tiebreak by id
            if (nextCustomer == null || nextCustomer.stateChangeTime > customer.stateChangeTime) {
                nextCustomer = customer;
            }
        }
        return nextCustomer;
    }

    private Customer getWaitingCustomer() {
        for (Customer customer : listOfCustomers) {
            if (customer.state == CustomerState.WAITING) {
                return customer;
            }
        }
        return null;
    }

    private void serveNextWaitingCustomer() {
        Customer customer = getWaitingCustomer();
        if (customer == null) {
            return;
        }
        totalWaitingTime += customer.serve(currentTime + SERVICE_TIME, currentTime);
        numOfServed++;
        serverState = ServerState.SERVING;
    }

    /**
     * Starts simulating customers.
     *
     */
    public void startServing() {
        Customer customer;
        while ((customer = getNextEvent()) != null) { 
            if (customer.stateChangeTime > currentTime) {
                currentTime = customer.stateChangeTime;
            }
            CustomerState state = customer.state;
            if (state == CustomerState.BEFOREARRIVAL) {
                customer.arrive(currentTime);
            } else if (state == CustomerState.ARRIVED) {
                if (serverState == ServerState.IDLE) {
                    customer.serve(currentTime + SERVICE_TIME, currentTime);
                    numOfServed++;
                    serverState = ServerState.SERVING;
                } else {
                    if (getWaitingCustomer() == null) {
                        customer.wait(currentTime);
                    } else {
                        customer.leave(currentTime);
                        numOfLeft++;
                    }
                }
            } else if (state == CustomerState.SERVED) {
                customer.done(currentTime);
                serverState = ServerState.IDLE;
                serveNextWaitingCustomer();
            } else if (state == CustomerState.WAITING) {
                numOfServed++;
                serverState = ServerState.SERVING;
                totalWaitingTime += customer.serve(currentTime + SERVICE_TIME, currentTime); 
            }
        }
    }
}
