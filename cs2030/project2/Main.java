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
     * Serves and prints each customer's string representation in the arraylist.
     */
    public static void main(String[] args) {
        Server server = new Server(); // initiates the server
        ArrayList<Customer> customers = readCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.toString());
            server.serveCustomer(customer);
        }
        System.out.println("Number of customers: " + customers.size());
    }
}
