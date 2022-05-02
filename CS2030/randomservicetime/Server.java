package cs2030.simulator;

import cs2030.simulator.RandomGenerator;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Encapsulates a server which is able to serve a customer.
 */
public class Server {

    private int id;
    private static int count;
    private static int serveTime = 1;
    private double time;
    private boolean isIdle = true;
    public int currCustomers = 0;
    private static int wait = 0;
    private static int served = 0;
    private static int leaves = 0;
    private static double totalWaitTime = 0;
    private RandomGenerator r;

    /**
     * Priority queue  of events that have been processed.
     * Shared by all the servers.
     */
    public static PriorityQueue<Event> events;

    /**
     * Priority queue of events remaining to be processed.
     * Shared by all the servers. 
     */
    public static PriorityQueue<Event> eventsRemaining;
    
    /**
     * Arraylist of shared servers.
     */
    public static ArrayList<Server> servers;

    /**
     * Constructor.
     * Increments server's id by 1 for each server created.
     */
    public Server(int seedValue, double arrivalRate, double serviceRate) {
        id = ++count;
        Comparator<Event> eventComparator = new EventComparator();
        events = new PriorityQueue<Event>(eventComparator);
        eventsRemaining = new PriorityQueue<Event>(eventComparator);
        r = new RandomGenerator(seedValue, arrivalRate, serviceRate);
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
                server.time = customer.getTime();
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
    public void updateState(
            Customer customer, int seedValue, double arrivalRate, double serviceRate) {
        if (customer.getState().equals("served")) {
            Server server = getIDByName(customer.getServerID());
            double serviceTime = r.genServiceTime();
            customer.setDoneTime(server.time + serviceTime);
            customer.changeState("done");
            server.time += serviceTime;
        } else if (customer.getState().equals("waits")) {
            Server server = getIDByName(customer.getServerID());
            customer.setServedTime(server.time);
            customer.changeState("served");
            served++;
            totalWaitTime += customer.getWaitTime();
        }
    }

    /**
     * Gets next event and add them into the event arraylist.
     * Updates the customer according to their state.
     * Customers who arrives will be determined their next state,
     * customers who are served or is waiting
     * gets updated and placed back into the remaining customers,
     * customers who are done or leaves gets removed from the remaining customers.
     */
    public void addNextEvent(int seedValue, double arrivalRate, double serviceRate) {
        Event event;
        Event event2;
        Customer customer1 = eventsRemaining.poll().getCustomer();
        if (customer1.getState().equals("arrives")) {
            event = new Event(customer1);
            events.add(event);
            serveCustomer(customer1);
            event2 = new Event(customer1);
            eventsRemaining.add(event2);
        } else if (customer1.getState().equals("served")) {
            event = new Event(customer1);
            events.add(event);
            updateState(customer1, seedValue, arrivalRate, serviceRate);
            event2 = new Event(customer1);
            eventsRemaining.add(event2);
        } else if (customer1.getState().equals("waits")) {
            event = new Event(customer1);
            events.add(event);
            updateState(customer1, seedValue, arrivalRate, serviceRate);
            event2 = new Event(customer1);
            eventsRemaining.add(event2);
        } else if (customer1.getState().equals("done")) {
            event = new Event(customer1);
            events.add(event);
            Server server = getIDByName(customer1.getServerID());
            server.currCustomers--;
            if (server.currCustomers == 0) {
                server.isIdle = true;
            }
        } else if (customer1.getState().equals("leaves")) {
            event = new Event(customer1);
            events.add(event);
        }
    }
}

