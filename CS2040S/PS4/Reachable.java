import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.util.ArrayList;

class Reachable {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int cities = io.getInt();
        for (int i = 0; i < cities; i++) {
            solve(io);
        }
    }

    static void solve(Kattio io) {
        int nodes = io.getInt();
        int edges = io.getInt();
        Node[] nodeArr = new Node[nodes];
        for (int i = 0; i < nodes; i++) {
            nodeArr[i] = new Node(i);
        }
        boolean[] connected = new boolean[nodes];
        Arrays.fill(connected, false);
        for (int i = 0; i < edges; i++) {
            int from = io.getInt();
            int to = io.getInt();
            nodeArr[from].neighbours.add(new Node(to));
            nodeArr[to].neighbours.add(new Node(from));
        }
        /*for (int i = 0; i < nodes; i++) {
            for (Node node : nodeArr[i].neighbours) {
                io.println("Neighbour of " + i + " : " + node);
            }
        }*/
        int count = 0;
        for (int i = 0; i < nodes; i++) {
            if (!connected[i]) {
                //io.println("fuck: " + i);
                BFS(nodeArr, connected, i);
                count++;
            }
        }
        io.println(count -1);
        io.flush();
    }

    static void BFS(Node[] nodeArr, boolean[] connected, int i) {
        Stack<Node> s = new Stack<Node>();
        connected[i] = true;
        s.push(nodeArr[i]);
        while (!s.isEmpty()) {
            Node curr = s.pop();
            for (Node neighbour : curr.neighbours) {
                if (!connected[neighbour.key]) {
                    connected[neighbour.key] = true;
                    s.push(nodeArr[neighbour.key]);
                }
            }
        }
    }

    static class Node {

        int key;
        List<Node> neighbours;

        public Node(int key) {
            this.key = key;
            this.neighbours = new ArrayList<Node>();
        }

        public String toString() {
            return "" + this.key;
        }
    }
}