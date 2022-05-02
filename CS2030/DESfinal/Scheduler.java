package cs2030.simulator;

import cs2030.simulator.RandomGenerator;

public class Scheduler {

    private RandomGenerator r;

    public Scheduler(int seedValue, double arrivalRate, double serviceRate) {
        r = new RandomGenerator(seedValue, arrivalRate, serviceRate);
    }

    public void schedule(Server server) {
        Event event1;
        Event event2;
        Customer customer1 = server.eventsRemaining.poll().getCustomer();
        if (customer1.getState().equals("arrives")) {
            event1 = new Event(customer1);
            server.events.add(event1);
            serveCustomer(customer1, server);
            event2 = new Event(customer1);   
            server.eventsRemaining.add(event2);
        } else if (customer1.getState().equals("served")) {
            event1 = new Event(customer1);
            server.events.add(event1);
            updateState(customer1, server);
            event2 = new Event(customer1);
            server.eventsRemaining.add(event2);
        } else if (customer1.getState().equals("waits")) {
            event1 = new Event(customer1);
            server.events.add(event1);
            updateState(customer1, server);
            event2 = new Event(customer1);
            server.eventsRemaining.add(event2);
        } else if (customer1.getState().equals("done")) {
            event1 = new Event(customer1);
            server.events.add(event1);
            Server server1 = server.getIDByName(customer1.getServerID());
            //System.out.println(customer1.getID() + " " + server1.id + " " + server1.currCustomers);
            server1.currCustomers--;
            if (server1.currCustomers == 0) {
                server1.isIdle = true;
            }
        } else {
            event1 = new Event(customer1);
            server.events.add(event1);
        }
        Server fuckingserver = server.getIDByName(customer1.getServerID());
        System.out.println(customer1.getID() + " " + customer1.getState() + " SHIT " + fuckingserver.id + " " + fuckingserver.lineOfCustomers.size());
    }

    private void serveCustomer(Customer customer, Server server) {
        for (Server server1 : server.servers) {
            if (server1.isIdle) {
                server1.isIdle = false;
                server1.time = customer.getTime();
                customer.setServerID(server1.id);
                customer.setServedTime(customer.getTime());
                customer.changeState("served");
                server1.currCustomers++;
                server.served++;
                break;
            }
        }
        if (customer.getState().equals("arrives")) {
            for (Server server1: server.servers) {
                if (server1.lineOfCustomers.size() < server1.maxCustomers) {
                    customer.setServerID(server1.id);
                    customer.setWaitTime(customer.getTime());
                    customer.changeState("waits");
                    server1.currCustomers++;
                    server1.lineOfCustomers.add(customer);
                    server.wait++;
                    break;
                }
            }
        }
        if (customer.getState().equals("arrives")) {
            customer.setLeaveTime(customer.getTime());
            customer.changeState("leaves");
            server.leaves++;
        }
    }

    public void updateState(Customer customer, Server server) {
        if (customer.getState().equals("served")) {
            Server server1 = server.getIDByName(customer.getServerID());
            double serviceTime = r.genServiceTime();
            customer.setDoneTime(customer.getTime() + serviceTime);
            server1.time += serviceTime;
            customer.changeState("done");
            server1.lineOfCustomers.remove(customer);
            if (!server1.lineOfCustomers.isEmpty()) {
                Customer c = server1.lineOfCustomers.get(0);
                System.out.println("check " + c.getID());
                server.totalWaitTime -= c.getWaitTime();
                c.setServedTime(server1.time);
                server.totalWaitTime += c.getWaitTime();
                /*for (Customer c : server1.lineOfCustomers) {
                    server.totalWaitTime -= c.getWaitTime();
                    c.setServedTime(server1.time);
                    server.totalWaitTime += c.getWaitTime();
                }*/
            }
        } else if (customer.getState().equals("waits")) {
            Server server1 = server.getIDByName(customer.getServerID());
            customer.setServedTime(server1.time);
            customer.changeState("served");
            server.served++;
            server.totalWaitTime += customer.getWaitTime();
        }
    }
}
