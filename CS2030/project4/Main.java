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
     * Serves and prints each customer's string representation
     * before and after serving in the arraylist.
     * Prints out events in each iteration while 
     * there are still customers remaining.
     */
    public static void main(String[] args) {
        Server server = new Server(); // initiates the server
        ArrayList<Customer> customers = readCustomers();
        for (Customer customer : customers) {
            server.customersLeft.add(customer);
        }
        server.showArrivals();
        System.out.println();
        while (!server.customersLeft.isEmpty()) {
            server.getNextEvent();
            for (Customer customer : server.events) {
                System.out.println(customer);
            }
            System.out.println();
        }
        System.out.println("Number of customers: " + customers.size());
    }
}
