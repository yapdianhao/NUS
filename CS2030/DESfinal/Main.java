import java.util.Scanner;
import java.util.ArrayList;
import cs2030.simulator.Event;
import cs2030.simulator.Customer;
import cs2030.simulator.Server;
import cs2030.simulator.CreateCustomer;
import cs2030.simulator.Scheduler;

public class Main {
    /**
     * Prints out the events and the servers' statistics.
     */
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ArrayList<Server> servers = new ArrayList<Server>();
        int seedValue = scn.nextInt();
        int numOfServers = scn.nextInt();
        int maxCustomers = scn.nextInt();
        int numOfCustomers = scn.nextInt();
        double arrivalRate = scn.nextDouble();
        double serviceRate = scn.nextDouble();
        for (int i = 0; i < numOfServers; i++) {
            Server server = new Server(maxCustomers);
            servers.add(server);
        }
        ArrayList<Customer> customers = 
            CreateCustomer.createCustomer(numOfCustomers, seedValue, arrivalRate, serviceRate);
        if (servers.size() == 0) {
            for (Customer customer : customers) {
                customer.setLeaveTime(customer.getTime());
                customer.changeState("leaves");
            }
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        } else {
            Scheduler scheduler = new Scheduler(seedValue, arrivalRate, serviceRate);
            Server server = (Server)servers.get(0); // initiates the server
            server.servers = servers;
            for (Customer customer : (ArrayList<Customer>)customers) {
                Event event = new Event(customer);
                server.eventsRemaining.add(event);
            }
            while (!(server.eventsRemaining.size() == 0)) {
                scheduler.schedule(server);
            }
            while (!(server.events.size() == 0)) {
                Event event = server.events.poll();
                System.out.println(event.toString());
                //Customer customer = event.getCustomer();
                //Server temp = server.getIDByName(customer.getServerID());
               // System.out.println(temp.getID() + ": " +  temp.maxCustomers);
            }
            System.out.println(String.format("[%.3f %d %d]",
                        (server.getWaitTime() / server.getServed()),
                        server.getServed(), server.getLeaves()));
        }
    }
}
