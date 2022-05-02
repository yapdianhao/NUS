import java.util.Scanner;
import java.util.ArrayList;

class Main {

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

    public static void main(String[] args) {
        ArrayList<ArrayList> items = readCustomersAndServers();
        ArrayList servers = items.get(0);
        ArrayList customers = items.get(1);
        Server server = (Server)servers.get(0);
        for (Customer customer : (ArrayList<Customer>)customers) {
            server.customersLeft.add(customer);
        }
        while (!server.customersLeft.isEmpty()) {
            server.addNextEvent();
        }
        for (String s : server.events) {
            System.out.println(s);
        }
        System.out.println(String.format("[%.3f %d %d]",
                 (server.getWaitTime() / server.getServed()), 
                 server.getServed(), server.getLeaves()));
    }
}
