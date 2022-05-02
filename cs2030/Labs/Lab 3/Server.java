import java.util.ArrayList;
import java.util.List;

public class Server {
    
    public static final double SERVICE_TIME = 1;
    private ServerState serverState = ServerState.IDLE;
    private Customer currentWaitingCustomer = null;
    private int serverID;

    /**
     * Constructor.
     */
    public Server(int id) {
        this.serverID = id;
    }

    public int getID() {
        return serverID;
    }

    public Customer getWaitingCustomer() {
        return currentWaitingCustomer;
    }

    public ServerState getServerState() {
        return serverState;
    }

    public void setServerState(ServerState serverState) {
        this.serverState = serverState;
    }

    public void addCustomerToQueue(Customer customer) {
        this.currentWaitingCustomer = customer;
    }
}
