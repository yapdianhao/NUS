import java.util.ArrayList;

/**
 * Encapsulates a server which is able to serve a customer.
 */
public class Server {

    private int serveTime = 1;
    private double time;
    private boolean isIdle = true;

    /**
     * ArrayList of customers at each iteration.
     */
    public ArrayList<Customer> events;

    /**
     * Arraylist of customers remaining.
     */
    public ArrayList<Customer> customersLeft;

    public Server() {
        events = new ArrayList<Customer>();
        customersLeft = new ArrayList<Customer>();
    }

    /**
     * Serves customer based on server's state.
     */
    public void serveCustomer(Customer customer) {
        if (isIdle) {
            isIdle = false;
            time = customer.getTime() + serveTime;
            customer.changeState("served");
        } else if (!isIdle && customer.getTime() < time) {
            customer.changeState("leaves");
        } else if (!isIdle && customer.getTime() >= time) {
            isIdle = true;
            serveCustomer(customer);
        }
    }

    /** 
     * Shows the arriving time of each customer.
     */
    public void showArrivals() {
        System.out.println("# Adding arrivals");
        for (Customer customer : customersLeft) {
            System.out.println(customer.toString());
        }
    }

    /**
     * Gets the next customer's event and the remaining customes' arrivals.
     * Inserts the customer according to time
     * if the customer is waiting or done serving.
     */
    public void getNextEvent() {
        events.clear();
        Customer customer1 = customersLeft.get(0); // gets the first customer
        if (customer1.getState().equals("arrives")) {
            System.out.println("# Get next event: " + customer1.toString());
            serveCustomer(customer1);
            for (Customer customer : customersLeft) {
                events.add(customer);
            }
        } else if (customer1.getState().equals("served")) {
            System.out.println("# Get next event: " + customer1.toString());
            customer1.changeState("done");
            customer1.changeTime(time);
            for (int i = 1; i < customersLeft.size(); i++) {
                events.add(customersLeft.get(i));
            }
            int i = 0;
            for (Customer customer : events) {
                if (customer.getTime() > customer1.getTime()) {
                    break;
                } else if (customer.getTime() == customer1.getTime() 
                        && customer.getid() > customer1.getid()) {
                    break;
                } else {
                    i++;
                }
            }
            events.add(i,customer1);
            customersLeft.add(i + 1, customer1); // adds first customer 
            customersLeft.remove(0); // removes first customer
        } else if (customer1.getState().equals("done") 
                || customer1.getState().equals("leaves")) { 
            System.out.println("# Get next event: " + customer1.toString());
            customersLeft.remove(0);
            for (Customer customer : customersLeft) {
                events.add(customer);
            }
        }
    }
}

