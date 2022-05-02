import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

class SSSP2 {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int nodes = io.getInt();    
        int edges = io.getInt();
        int queries = io.getInt();
        int source = io.getInt();
        while (true) {
            if (nodes == 0 && edges == 0 && queries == 0 && source == 0) {
                break;
            }
            solve(io, nodes, edges, queries, source);
            nodes = io.getInt();
            edges = io.getInt();
            queries = io.getInt();
            source = io.getInt();
        }
        io.close();
    }

    static void solve(Kattio io, int nodes, int edges, int queries, int source) {
        int[] dist = new int[nodes];
        int MAX = (int) Math.pow(10, 9);
        Arrays.fill(dist, MAX);
        PriorityQueue<Node> q = new PriorityQueue<Node>(nodes, new NodeComparator());
        dist[source] = 0;
        Node start = new Node(source, 0, 0);
        q.add(start);
        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<Node>());
        }
        for (int i = 0; i < edges; i++) {
            int node = io.getInt();
            Node neighbour = new Node(io.getInt(), io.getInt(), MAX);
            adj.get(node).add(neighbour);
            q.add(neighbour);
        }
        while (!q.isEmpty()) {
            Node toProcess = q.poll();
            if (dist[toProcess.key] < toProcess.relaxed) {
                continue;
            }
            for (Node node : adj.get(toProcess.key)) {
                if (dist[node.key] > dist[toProcess.key] + node.weight) {
                    dist[node.key] = dist[toProcess.key] + node.weight;
                    q.add(new Node(node.key, node.weight, dist[node.key]));
                }
            }
        }
        for(int i = 0; i < queries; i++) {
            int target = io.getInt();
            io.println(dist[target] == MAX ? "Impossible" : dist[target]);
        }
    }

    static class Node {

        int key;
        int weight;
        int relaxed;

        public Node(int key, int weight, int relaxed) {
            this.key = key; 
            this.weight = weight;
            this.relaxed = relaxed;
        }

        public String toString() {
            return "" + this.key;
        }
    }

    static class NodeComparator implements Comparator<Node> {

        public int compare(Node one, Node two) {
            return one.relaxed - two.relaxed;
        }
    }
}