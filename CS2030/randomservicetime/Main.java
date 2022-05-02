import java.util.Scanner;
import java.util.ArrayList;
import cs2030.simulator.Event;
import cs2030.simulator.Customer;
import cs2030.simulator.Server;
import cs2030.simulator.CreateCustomer;

public class Main {
    /**
     * Prints out the events and the servers' statistics.
     */
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ArrayList<Server> servers = new ArrayList<Server>();
        int seedValue = scn.nextInt();
        int numOfServers = scn.nextInt();
        int numOfCustomers = scn.nextInt();
        double arrivalRate = scn.nextDouble();
        double serviceRate = scn.nextDouble();
        for (int i = 0; i < numOfServers; i++) {
            servers.add(new Server(seedValue, arrivalRate, serviceRate));
        }
        ArrayList<Customer> customers = 
            CreateCustomer.createCustomer(numOfCustomers, seedValue, arrivalRate, serviceRate);
        Server server = (Server)servers.get(0); // initiates the server
        server.servers = servers;
        for (Customer customer : (ArrayList<Customer>)customers) {
            Event event = new Event(customer);
            server.eventsRemaining.add(event);
        }
        while (!(server.eventsRemaining.size() == 0)) {
            server.addNextEvent(seedValue, arrivalRate, serviceRate);
        }
        while (!(server.events.size() == 0)) {
            System.out.println(server.events.poll().toString());
        }
        System.out.println(String.format("[%.3f %d %d]",
               (server.getWaitTime() / server.getServed()),
               server.getServed(), server.getLeaves()));
    }
}
