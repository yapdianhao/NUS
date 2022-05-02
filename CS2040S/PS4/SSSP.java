import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Comparator;

class SSSP {

    public static void main(String[] args) {

        Kattio kattio = new Kattio(System.in);
        int nodes = kattio.getInt();
        int edges = kattio.getInt();
        int queries = kattio.getInt();
        int start = kattio.getInt();
        while (true) {
            if (nodes == 0 && edges == 0 && queries == 0 && start == 0) {
                break;
            }
            solve(kattio, nodes, edges, queries, start);
            nodes = kattio.getInt();
            edges = kattio.getInt();
            queries = kattio.getInt();
            start = kattio.getInt();
        }
        kattio.close();
    }

    static void solve(Kattio kattio, int nodes, int edges, int queries, int start) {
        int[] dist = new int[nodes];
        // every node that has not been relaxed is infinity.
        Arrays.fill(dist, Integer.MAX_VALUE);
        HashSet<Integer> done = new HashSet<Integer>();
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>(nodes, new NodeComparator());
        pq.add(new Node(start, 0));
        ArrayList<ArrayList<Node>> adjacencyMatrix = new ArrayList<ArrayList<Node>>();
        //initialize neighbour list for each node
        for (int i = 0; i < nodes; i++) {
            adjacencyMatrix.add(new ArrayList<Node>());
        }

        for (int i = 0; i < edges; i++) {
            int node = kattio.getInt();
            Node neighbour = new Node(kattio.getInt(), kattio.getInt());
            adjacencyMatrix.get(node).add(neighbour);
        }
        while (!pq.isEmpty()) {
            int toProcess = pq.poll().key;
            done.add(toProcess);
            dijkstra(adjacencyMatrix, pq, done, dist, toProcess);
        }
        for (int i = 0; i < queries; i++) {
            int target = kattio.getInt();
            kattio.println(dist[target] == Integer.MAX_VALUE ? "Impossible" : dist[target]);
        }
        kattio.flush();    
    }

    static void dijkstra(ArrayList<ArrayList<Node>> adjacencyMatrix, PriorityQueue<Node> pq, 
        HashSet<Integer> done, int[] dist, int toProcess) {
        int oldDistance;
        int newDistance;
        for (int i = 0; i < adjacencyMatrix.get(toProcess).size(); i++) {
            Node node = adjacencyMatrix.get(toProcess).get(i);
            //System.out.println("I: " + i + " NODE: " + node.key);
            // relaxation algorithm
            if (!done.contains(node.key)) {
                oldDistance = node.weight;
                newDistance = dist[toProcess] + oldDistance;
                if (newDistance < dist[node.key]) {
                    dist[node.key] = newDistance;
                }
                pq.add(new Node(node.key, dist[node.key]));
            }
        }
        //System.out.println(pq.size());
        //System.out.println(Arrays.toString(dist));
        //System.out.println(done);
    }

    static class Node {

        int key;
        int weight;

        public Node(int key, int weight) {
            this.key = key;
            this.weight = weight;
        }

        public String toString() {
            return "" + this.key;
        }
    }

    static class NodeComparator implements Comparator<Node> {

        public int compare(Node one, Node two) {
            return one.weight - two.weight;
    }
}
}