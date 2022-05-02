import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    /**
     * Reads in customers from the input and inserts them into an arraylist.
     * Also reads the number of servers and inserts them into an arraylist.
     * Passes the arraylist into one of the servers.
     * Since the servers share the same arraylist, they can all assess it.
     */
    public static ArrayList<ArrayList> readCustomersAndServers() {
        ArrayList<ArrayList> items = new ArrayList<ArrayList>();
        ArrayList<Server> servers = new ArrayList<Server>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Scanner scn = new Scanner(System.in);
        int numOfServers = scn.nextInt();
        for (int i = 0; i < numOfServers; i++) {
            servers.add(new Server());
        }
        servers.get(0).servers = servers;
        while (scn.hasNext()) {
            Customer customer = new Customer(scn.nextDouble());
            customers.add(customer);
        }
        items.add(servers);
        items.add(customers);
        return items;
    }

    /**
     * Prints out the events and the servers' statistics.
     */
    public static void main(String[] args) {
        ArrayList<ArrayList> items = readCustomersAndServers();
        ArrayList servers = items.get(0);
        ArrayList customers = items.get(1);
        Server server = (Server)servers.get(0); // initiates the server
        for (Customer customer : (ArrayList<Customer>)customers) {
            server.customersLeft.add(customer);
        }
        while (!server.customersLeft.isEmpty()) {
            server.addNextEvent();
        }
        while (!(server.events.size() == 0)) {
            System.out.println(server.events.poll().toString());
        }
        System.out.println(String.format("[%.3f %d %d]",
               (server.getWaitTime() / server.getServed()),
               server.getServed(), server.getLeaves()));
    }
}
