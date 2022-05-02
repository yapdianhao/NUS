import java.util.ArrayList;

/**
 * Encapsulates a server which is able to serve a customer.
 */
public class Server {

    private int id;
    private static int count;
    private static int serveTime = 1;
    private double time;
    private boolean isIdle = true;
    private int currCustomers = 0;
    private static int wait = 0;
    private static int served = 0;
    private static int leaves = 0;
    private static double totalWaitTime = 0;

    /**
     * ArrayList of strings of each events..
     */
    public static ArrayList<String> events;

    /**
     * Arraylist of customers remaining.
     */
    public static ArrayList<Customer> customersLeft;
    
    /**
     * Arraylist of shared servers.
     */
    public static ArrayList<Server> servers;

    /**
     * Constructor.
     * Increments server's id by 1 for each server created.
     */
    public Server() {
        id = ++count;
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
     * Returns a server with that ID according to number input.
     */
    public Server getIDByName(int serverID) {
        for (Server server : servers) {
            if (serverID == server.id) {
                return server;
            }
        }
        return this;
    }

    /**
     * Serves customer based on server's availability.
     * Arrives customers could be served, waits, or leaves.
     * Checks if server is idle. If it is, serves the customer. 
     * If it is not, passes to the next server.
     * If all servers are not idle, customer waits.
     * If all servers has a customer waiting, customer leaves.
     */
    public void serveCustomer(Customer customer) {
        for (Server server : servers) {
            if (server.isIdle) {
                server.isIdle = false;
                server.time = customer.getTime() + serveTime;
                customer.setServerID(server.id);
                customer.setServedTime(customer.getTime());
                customer.changeState("served");
                server.currCustomers++;
                served++;
                break;
            }
        }
        if (customer.getState().equals("arrives")) {
            for (Server server : servers) {
                if (!server.isIdle &&
                        customer.getTime() < server.time && 
                        server.currCustomers < 2) {
                    customer.setServerID(server.id);
                    customer.setWaitTime(customer.getTime());
                    customer.changeState("waits");
                    server.currCustomers++;
                    wait++;
                    break;
                }
            }
        }
        if (customer.getState().equals("arrives")) {
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
            Server server = getIDByName(customer.getServerID());
            customer.setDoneTime(server.time);
            customer.changeState("done");
        } else if (customer.getState().equals("waits")) {
            Server server = getIDByName(customer.getServerID());
            customer.setServedTime(server.time);
            customer.changeState("served");
            server.time += serveTime;
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
            Server server = getIDByName(customer1.getServerID());
            server.currCustomers--;
            if (server.currCustomers == 0) {
                server.isIdle = true;
            }
            customersLeft.remove(0);
        } else if (customer1.getState().equals("leaves")) {
            events.add(customer1.toString());
            customersLeft.remove(0);
        }
    }
}

