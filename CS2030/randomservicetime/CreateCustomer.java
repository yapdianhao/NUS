package cs2030.simulator;

import java.util.ArrayList;
import cs2030.simulator.RandomGenerator;

public class CreateCustomer {
    static double time = 0;

    /**
     * Creates customers based on the input from the scanner. 
     * Stores the customers created in an ArrayList and returns it.
     */
    public static ArrayList<Customer> createCustomer(
            int numOfCustomers, int seedValue, double arrivalRate, double serviceRate) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        RandomGenerator r = new RandomGenerator(seedValue, arrivalRate, serviceRate);
        for (int i = 0; i < numOfCustomers; i++) {
            Customer customer = new Customer(time);
            double generatedTime = r.genInterArrivalTime();
            customers.add(customer);
            time += generatedTime;
        }
        return customers;
    }
}
