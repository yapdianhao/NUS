package cs2030.simulator;

//import cs2030.simulator.CreateTime;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Encapsulates a server which is able to serve customers.
 */
public class Server {
    /**
     * Server's ID.
     */
    public int id;
    /**
     * Counter to increment server's ID.
     */
    private static int count;
    /**
     * The maximum number of waiting customers.
     */
    public static int maxCustomers;
    /**
     * Server's time.
     */
    public double time;
    /**
     * A state to determine whether a Server is idle.
     * An idle server has no serving and waiting customers.
     */
    public boolean isIdle = true;
    /**
     * An integer of customers of a server.
     * Includes the waiting and serving customers.
     */
    public int currCustomers = 0;
    /**
     * Number of total waiting customers of all servers.
     */
    public static int wait = 0;
    /**
     * Number of total served customers of all servers.
     */
    public static int served = 0;
    /**
     * Number of total left customers of all servers.
     */
    public static int leaves = 0;
    /**
     * Number of total waiting for all customers.
     */
    public static double totalWaitTime = 0;
    /**
     * Random generator to generate service time.
     */
    //private static RandomGenerator r;
    /**
     * Arraylist of current waiting customers of the server.
     */
    public ArrayList<Customer> lineOfCustomers;
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

    int seedValue; 
    double arrivalRate;
    double serviceRate;

    /**
     * Constructor.
     * Increments server's id by 1 for each server created.
     * @param seedValue The seedvalue for the random generator.
     * @param maxCustomers The maximum number of waiting customers.
     * @param arrivalRate The arrival rate for the random generator.
     * @param serviceRate The service rate for the random generator.
     */
    public Server(int maxCustomers) {
        id = ++count;
        currCustomers = 0;
        this.maxCustomers = maxCustomers;
        lineOfCustomers = new ArrayList<Customer>();
        Comparator<Event> eventComparator = new EventComparator();
        events = new PriorityQueue<Event>(eventComparator);
        eventsRemaining = new PriorityQueue<Event>(eventComparator);
        //r = new RandomGenerator(seedValue, arrivalRate, serviceRate);// this is static
    }

    /**
     * A method to retrieve the total waiting time for customers on all servers.
     * @return Total waiting time.
     */
    public double getWaitTime() {
        return totalWaitTime;
    }

    /**
     * Gets the number of customers waited.
     * @return Total number of customers waited.
     */
    public int getWait() {
        return wait;
    }

    /**
     * Gets the number of customers served.
     * @return Total number of customers served.
     */
    public int getServed() {
        return served;
    }

    /**
     * Gets the number of customers who leaves.
     * @return Total number of customers left without being served.
     */
    public int getLeaves() {
        return leaves;
    }

    /**
     * Returns a server with that ID according to number input.
     * @param serverID Integer of the server's ID
     * @return Server with that ID.
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
     * @param customer that is to be served.
     */
    /*public void serveCustomer(Customer customer) {
        for (Server server : servers) {
            if (server.isIdle) {
                // Server is not idle after serving one customer.
                server.isIdle = false;
                // Sets the server's next available time.
                // Trivial, since the next customer is the current one.
                server.time = customer.getTime();
                // Assigns the server to serve the customer.
                customer.setServerID(server.id);
                // Sets the serving time of the customer.
                // Also trivial, since the customer gets served immediately.
                customer.setServedTime(customer.getTime());
                customer.changeState("served");
                // Increments the # of current customers served by the server.
                server.currCustomers++;
                // Increments total number of customers served. Recall that this is a static field.
                served++;
                // Needs to break here after serving. One customer can only be served once.
                break;
            }
        }
        // The case where no server is idle. 
        // Every server has a serving customer.
        if (customer.getState().equals("arrives")) {
            for (Server server : servers) {
                // Checks if server is able to take in one more waiting customer.
                if (server.lineOfCustomers.size() < maxCustomers) {
                    customer.setServerID(server.id);
                    // Sets the waiting time of the customer.
                    // Also trivial, since customer starts waiting immediately.
                    customer.setWaitTime(customer.getTime());
                    customer.changeState("waits");
                    // Increments # of current customers of the server here.
                    server.currCustomers++;
                    // Adds the waiting customer into the waiting queue.
                    server.lineOfCustomers.add(customer);
                    // Increments total number of customers waited. Also a static field.
                    wait++;
                    // Breaks here also. A customer belongs to a server.
                    break;
                }
            }
        }
        // The case where no server is idle, and every server has a max
        // number of customers waiting.
        if (customer.getState().equals("arrives")) {
            // Sets the customer leaving time.
            // Again trivial, customer leaves immediately.
            customer.setLeaveTime(customer.getTime());
            customer.changeState("leaves");
            // Increments total number of customers left. Static field.
            leaves++;
        }
    }

    /**
     * Updates the state of customers who are waiting or done serving.
     * Customers who are waiting gets served,
     * and customers who are served gets updated to done.
     * @param customer to update
     */
    /*ublic void updateState(Customer customer) {
        if (customer.getState().equals("served")) {
            // Gets the server serving the customer.
            Server server = getIDByName(customer.getServerID());
            // Generates the serving time here only. 
            // Should be correct.
            double serviceTime = CreateTime.createServiceTime();
            // Sets the customer's time after serving to be current time + serviceTime
            customer.setDoneTime(customer.getTime() + serviceTime);
            // Increments server's next available time.
            server.time += serviceTime;
            customer.changeState("done");
            // Removes the customer from the waiting queue
            server.lineOfCustomers.remove(customer);
            // Fragment of code below:
            // Modification because of the change in server's time in line
            // What happens if server's next available time > customer in queue's serving time?
            // Loops through every customer in the queue, updates their serving time.
            // Also recomputes the total waiting time. 
            // If hard to visualise, try echo "1 2 2 10 1 1" | java Main
            // and observe customer 10's waiting and serving time
            // because of the modification of serving customer 9.
            if (!server.lineOfCustomers.isEmpty()) {
                for (Customer c : server.lineOfCustomers) {
                    server.totalWaitTime -= c.getWaitTime();
                    c.setServedTime(server.time);
                    server.totalWaitTime += c.getWaitTime();
                }
            }
        } else if (customer.getState().equals("waits")) {
            // Gets the server serving the customer.
            Server server = getIDByName(customer.getServerID());
            // Sets the customer's serving time to server's next available time.  
            customer.setServedTime(server.time);
            customer.changeState("served");
            served++;
            //computes the total waiting time. 
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
    /*public void addNextEvent() {
        Event event;
        Event event2;
        Customer customer1 = eventsRemaining.poll().getCustomer();
        if (customer1.getState().equals("arrives")) {
            // Generates the event of the customer before processing.
            event = new Event(customer1);
            // Adds the event into the events queue.
            // Note that this event queue has all the events to be printed out.
            // Each event will be polled out.
            events.add(event);
            // The processing of the customer starts. 
            // ONLY customer with state "arrives" will be served.
            // See function serveCustomer for more details.
            serveCustomer(customer1);
            // Generates the event of the customer after processing. 
            event2 = new Event(customer1);
            // Adds the event processed into the eventsRemaining queue.
            // Note that eventsRemaining queue has all the events that has to be processed.
            // See function updateStatus for more details.
            eventsRemaining.add(event2);
        } else if (customer1.getState().equals("served")) {
            event = new Event(customer1);
            events.add(event);
            // Process customer starts.
            // Note that ONLY customers with "served" or "waits" will be updated.
            // See function updateStatus for more details.
            updateState(customer1);
            event2 = new Event(customer1);
            eventsRemaining.add(event2);
        } else if (customer1.getState().equals("waits")) {
            event = new Event(customer1);
            events.add(event);
            updateState(customer1);
            event2 = new Event(customer1);
            eventsRemaining.add(event2);
        } else if (customer1.getState().equals("done")) {
            event = new Event(customer1);
            events.add(event);
            // Get the server serving the customer.
            Server server = getIDByName(customer1.getServerID());
            // Decreases the # of customers of the server by 1.
            // Note that currCustomers INCLUDES the customer being served.
            // Different from lineOfCustomers.size().
            server.currCustomers--;
            if (server.currCustomers == 0) {
                // The only way for a server to go back to idle is here.
                server.isIdle = true;
            }
        } else if (customer1.getState().equals("leaves")) {
            event = new Event(customer1);
            events.add(event);
            // Notice how event2 is not here.
            // The only way for the eventsRemaining queue to be shorteR.
        } 
        System.out.println("CHECK :" + getIDByName(customer1.getServerID()).lineOfCustomers.size());
    }*/ 
}

