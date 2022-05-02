import java.util.ArrayList;

/**
 * Encapsulates a server which is able to serve a customer.
 */
public class Server {

    private int serveTime = 1;
    private double time;
    private boolean isIdle = true;
    private int currCustomers = 0;
    private int wait = 0;
    private int served = 0;
    private int leaves = 0;
    private double totalWaitTime = 0;

    /**
     * ArrayList of strings of each events..
     */
    public ArrayList<String> events;

    /**
     * Arraylist of customers remaining.
     */
    public ArrayList<Customer> customersLeft;

    public Server() {
        events = new ArrayList<String>();
        customersLeft = new ArrayList<Customer>();
    }

    public double getWaitTime() {
        return totalWaitTime;
    }

    /**
     * Gets the number of customers waited.
     */
    public int getWait() {
        return wait;
    }

    /**
     * Gets the number of customers served.
     */
    public int getServed() {
        return served;
    }

    /**
     * Gets the number of customers who leaves.
     */
    public int getLeaves() {
        return leaves;
    }

    /**
     * Serves customer based on server's availability.
     * Arrives customers could be served, waits, or leaves.
     */
    public void serveCustomer(Customer customer) {
        if (isIdle) {
            isIdle = false;
            time = customer.getTime() + serveTime;
            customer.setServedTime(customer.getTime());
            customer.changeState("served");
            currCustomers++;
            served++;
        } else if (!isIdle && customer.getTime() < time
               && currCustomers < 2) {
            customer.setWaitTime(customer.getTime());
            customer.changeState("waits");
            currCustomers++;
            wait++;
        } else if (!isIdle && customer.getTime() <  time
                && currCustomers == 2) {
            customer.setLeaveTime(customer.getTime());
            customer.changeState("leaves");
            leaves++;
        }
    }

    /**
     * Updates the state of customers who are waiting or done serving.
     * Customers who are waiting gets served,
     * and customers who are served gets updated to done.
     */
    public void updateState(Customer customer) {
        if (customer.getState().equals("served")) {
            customer.setDoneTime(time);
            customer.changeState("done");
        } else if (customer.getState().equals("waits")) {
            customer.setServedTime(time);
            customer.changeState("served");
            time += serveTime;
            served++;
            totalWaitTime += customer.getWaitTime();
        }
    }

    /**
     * places the customer into the arraylist of customers remaining
     * according to the customer's current time.
     */
    public void placeCustomer(Customer c) {
        int i = 0;
        for (Customer customer : customersLeft) {
            if (customer.getTime() > c.getTime()) {
                break;
            } else if (customer.getTime() == c.getTime()
                    && customer.getid() > c.getid()) {
                break;
            } else {
                i++;
            }
        }
        customersLeft.add(i,c);
        customersLeft.remove(0);
    }

    /**
     * Gets next event and add them into the event arraylist.
     * Updates the customer according to their state.
     * Customers who arrives will be determined their next state,
     * customers who are served or is waiting
     * gets updated and placed back into the remaining customers,
     * customers who are done or leaves gets removed from the remaining customers.
     */
    public void addNextEvent() {
        Customer customer1 = customersLeft.get(0);
        if (customer1.getState().equals("arrives")) {
            events.add(customer1.toString());
            serveCustomer(customer1);
        } else if (customer1.getState().equals("served")) {
            events.add(customer1.toString());
            updateState(customer1);
            placeCustomer(customer1);
        } else if (customer1.getState().equals("waits")) {
            events.add(customer1.toString());
            updateState(customer1);
            placeCustomer(customer1);
        } else if (customer1.getState().equals("done")) {
            events.add(customer1.toString());
            currCustomers--;
            if (currCustomers == 0) {
                isIdle = true;
            }
            customersLeft.remove(0);
        } else if (customer1.getState().equals("leaves")) {
            events.add(customer1.toString());
            customersLeft.remove(0);
        }
    }
}

