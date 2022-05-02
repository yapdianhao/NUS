import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private List<Server> servers = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private int numOfCustomers = 0;
    private double currentTime = 0;
    private double totalWaitingTime = 0;
    private int numCustomersServed = 0;
    private int numCustomersWhoLeft = 0;

    /**
     * Constructor.
     * @param numServers the number of servers
     * @param arrivalTimes the list of arrival times for all the customers
     */
    public Simulator(int numServers, List<Double> arrivalTimes) {
        for (int i = 0; i < numServers; i++) {
            servers.add(new Server(i + 1));
        }
        for (double time : arrivalTimes) {
            numOfCustomers++;
            events.add(new ArriveEvent(time, new Customer(numOfCustomers)));
        }
        startSimulation();
    }

    /**
     * Gets average waiting time for customers served.
     * @return the average waiting time
     */
    public double getAverageWaitingTime() {
        return totalWaitingTime / numCustomersServed;
    }

    /**
     * Gets number of customers served.
     * @return Number of customers served
     */
    public int getNumServed() {
        return numCustomersServed;
    }

    /**
     * Gets the number of customers who left.
     * @return Number of customers who left
     */
    public int getNumLeft() {
        return numCustomersWhoLeft;
    }

    private Event getEarliestEvent() {
        if (events.size() == 0) {
            return null;
        }
        Event earliestEvent = events.get(0);
        for (Event event : events) {
            if (event.compareTo(earliestEvent) < 0) {
                earliestEvent = event;
            }
        }
        events.remove(earliestEvent);
        return earliestEvent;
    }

    private void startSimulation() {
        Event event;
        while ((event  = getEarliestEvent()) != null) { 
            double time = event.getTime();
            if (event instanceof ArriveEvent) {
                Customer customer = ((ArriveEvent)event).getCustomer();
                int customerID = customer.getID();
                Server assignedServer = null;
                printMessage(time, " " + customerID + " arrives");
                customer.setWaitingTime(time);
                for (Server server : servers) {
                    if (server.getServerState() == ServerState.IDLE) {
                        assignedServer = server;
                        break;
                    }
                }
                if (assignedServer == null) {
                    // cannot find idle server
                    // assign to a server without waiting customers
                    for (Server server : servers) {
                        if (server.getWaitingCustomer() == null) {
                            assignedServer = server;
                            server.addCustomerToQueue(customer);
                            printMessage(time,
                                    " " + customerID + " waits to be served by " + server.getID());
                            break;
                        }
                    }
                    if (assignedServer == null) {
                        printMessage(time, " " + customerID + " leaves");
                        numCustomersWhoLeft++;
                    }
                } else {
                    printMessage(time, " " + customerID + " served by " + assignedServer.getID());
                    totalWaitingTime += time - customer.getWaitingTime();
                    assignedServer.setServerState(ServerState.SERVING);
                    events.add(new DoneEvent(time + Server.SERVICE_TIME, customer, assignedServer));
                }
            } else {
                Customer customer = ((DoneEvent)event).getCustomer();
                Server server = ((DoneEvent)event).getServer();
                int customerID = customer.getID();
                int serverID = server.getID();
                printMessage(time, " " + customerID + " done serving by " + serverID);
                numCustomersServed++;
                server.setServerState(ServerState.IDLE);
                if (server.getWaitingCustomer() != null) {
                    customer = server.getWaitingCustomer();
                    server.addCustomerToQueue(null);
                    printMessage(time, " " + customer.getID() + " served by " + serverID);
                    server.setServerState(ServerState.SERVING);
                    totalWaitingTime += time - customer.getWaitingTime();
                    events.add(new DoneEvent(time + Server.SERVICE_TIME, customer, server));
                }
            }
        }
    }

    private static void printMessage(double time, String str) {
        System.out.println(String.format("%.3f", time) + str);
    }
}
