import java.util.ArrayList;

class Server {
    private int id;
    private static int count;
    static ArrayList<Server> servers;
    private static int serveTime = 1;
    private double time;
    private boolean isIdle = true;
    private int currCustomers = 0;
    static ArrayList<String> events;
    static ArrayList<Customer> customersLeft;
    private static  int Wait = 0;
    private static int Served = 0;
    private static int Leaves = 0;
    private static  double totalWaitTime = 0;

    public Server() {
        id = ++count;
        customersLeft = new ArrayList<Customer>();
        events = new ArrayList<String>();
    }

    public boolean getIdle() {
        return isIdle;
    }

    public double getWaitTime() {
        return totalWaitTime;
    }

    public int getWait() {
        return Wait;
    }

    public int getServed() {
        return Served;
    }

    public int getLeaves() {
        return Leaves;
    }

    public Server getIDByName(int serverID) {
        for (Server server : servers) {
            if (serverID == server.id) {
                return server;
            }
        }
        return this;
    }

    public boolean getIdleServer() {
        for (Server server : servers) {
            if (server.isIdle) {
                return true;
            }
        }
        return false;
    }

    public void serveCustomer(Customer customer) {
        for (Server server : servers) {
            if (server.isIdle) {
                server.isIdle = false;
                server.time = customer.getTime() + serveTime;
                customer.setServerID(server.id);
                customer.setServedTime(customer.getTime());
                customer.changeState("served");
                server.currCustomers++;
                Served++;
                break;
            }
        }
        if (customer.getState().equals("arrives")) {
            for (Server server : servers) {
                if (!server.isIdle &&
                        customer.getTime() < server.time 
                        && server.currCustomers < 2) {
                    customer.setServerID(server.id);
                    customer.setWaitTime(customer.getTime());
                    customer.changeState("waits");
                    server.currCustomers++;
                    Wait++;
                    break;
                }
            }
        }
        if (customer.getState().equals("arrives")) {
            customer.setLeaveTime(customer.getTime());
            customer.changeState("leaves");
            Leaves++;
        }
    }

    public void updateState(Customer customer) {
        if (customer.getState().equals("served")) {
            Server server = getIDByName(customer.getServerID());
            customer.setDoneTime(server.time);
            customer.changeState("done");
        } else if (customer.getState().equals("waits")) {
            Server server = getIDByName(customer.getServerID());
            customer.setServedTime(server.time);
            customer.changeState("served");
            server.time += serveTime;
            Served++;
            totalWaitTime += customer.getWaitTime();
        }
    }

    public void placeCustomer(Customer c) {
        int i = 0;
        for (Customer customer : customersLeft) {
            if (customer.getTime() > c.getTime()) {
                break;
            } else if (customer.getTime() == c.getTime() && customer.getid() > c.getid()) {
                break;
            } else {
                i++;
            }
        }
        customersLeft.add(i,c);
        customersLeft.remove(0);
    }

    public void addNextEvent() {
        Customer customer1 = customersLeft.get(0);
        if (customer1.getState().equals("arrives")) {
            events.add(customer1.toString());
            serveCustomer(customer1);
        } else if (customer1.getState().equals("served")) {
            events.add(customer1.toString());
            updateState(customer1);
            placeCustomer(customer1);
        } else if (customer1.getState().equals("waits")) {
            events.add(customer1.toString());
            updateState(customer1);
            placeCustomer(customer1);
        } else if (customer1.getState().equals("done")) {
            events.add(customer1.toString());
            Server server = getIDByName(customer1.getServerID());
            server.currCustomers--;
            if (server.currCustomers == 0) {
                server.isIdle = true;
            }
            customersLeft.remove(0);
        } else if (customer1.getState().equals("leaves")) {
            events.add(customer1.toString());
            customersLeft.remove(0);
        }
    }
}


