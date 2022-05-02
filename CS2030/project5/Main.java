import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    /**
     * Reads in customers from the input and inserts them into an arraylist.
     */
    public static ArrayList<Customer> readCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Scanner scn = new Scanner(System.in);
        while (scn.hasNext()) {
            Customer customer = new Customer(scn.nextDouble());
            customers.add(customer);
        }
        return customers;
    }

    /**
     * Prints out the events and the server's statistics.
     */
    public static void main(String[] args) {
        Server server = new Server(); // initiates the server
        ArrayList<Customer> customers = readCustomers();
        for (Customer customer : customers) {
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
