import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

class MST {

    static Kattio io = new Kattio(System.in);

    public static void main(String[] args) {
        int nodes = io.getInt();
        int edges = io.getInt();
        while (nodes != 0 || edges != 0) {
            solve(nodes, edges);
            nodes = io.getInt();
            edges = io.getInt();
        }
        io.close();
    }

    static void solve(int nodes, int edges) {
        if (edges == 0) {
            io.println("Impossible");
            return;
        }
        int total = 0;
        boolean[] visited = new boolean[nodes]; // an array to check if vertex is in MST
        List<List<Node>> adj = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        PriorityQueue<Node> path = new PriorityQueue<Node>();
        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<Node>());
        }
        for (int i = 0; i < edges; i++) {
            int node = io.getInt();
            int neighbour = io.getInt();
            int weight = io.getInt();
            adj.get(node).add(new Node(node, neighbour, weight));
            adj.get(neighbour).add(new Node(neighbour, node, weight));
        }
        visited[0] = true;
        for (Node start : adj.get(0)) {
            pq.add(start);
        }
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (visited[curr.dest]) {
                continue;
            }
            path.add(new Node(Math.min(curr.source, curr.dest), Math.max(curr.source, curr.dest),0));
            total += curr.weight;
            visited[curr.dest] = true;
            for (Node neighbour : adj.get(curr.dest)) {
                if (!visited[neighbour.dest]) {
                    pq.add(new Node(neighbour.source, neighbour.dest, neighbour.weight));
                }
            }
        }
        if (hasFalse(visited)) {
            io.println("Impossible");
        } else {
            io.println(total);
            while (! path.isEmpty()) {
                io.println(path.poll());
            }
        }
    }

    static boolean hasFalse(boolean[] arr) {
        for (boolean b : arr) {
            if (!b) {
                return true;
            }
        }
        return false;
    }

    static class Node implements Comparable<Node> {

        int source;
        int dest;
        int weight;

        public Node (int source, int dest, int weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }

        public int compareTo(Node other) {
            if (this.weight == other.weight) {
                if (this.source == other.source) {
                    return this.dest - other.dest;
                } else {
                    return this.source - other.source;
                }
            }
            return this.weight - other.weight;
        }

        public String toString() {
            return this.source + " " + this.dest;
        }
    }
}