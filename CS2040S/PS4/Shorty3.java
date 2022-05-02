import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;


class Shorty3 {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int nodes = io.getInt();
        int edges = io.getInt();
        while (edges != 0 || nodes != 0) {
            solve(io, nodes, edges);
            nodes = io.getInt();
            edges = io.getInt();
        }
        io.close();
    }

    static void solve(Kattio io, int nodes, int edges) {
        double[] dist = new double[nodes];
        boolean[] visited = new boolean[nodes];
        Arrays.fill(visited, false);
        Arrays.fill(dist, 0);
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<Pair>());
        }
        for (int i = 0; i < edges; i++) {
            int node1 = io.getInt();
            int node2 = io.getInt();
            double weight = io.getDouble();
            adj.get(node1).add(new Pair(node2, weight));
            adj.get(node2).add(new Pair(node1, weight));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        pq.offer(new Pair(0, 1));
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int currNode = curr.node;
            double weight = curr.weight;
            if (visited[currNode]) {
                continue;
            }
            visited[currNode] = true;
            dist[currNode] = weight;
            for (Pair pair : adj.get(currNode)) {
                pq.offer(new Pair(pair.node, weight * pair.weight));
            }
        }
        io.printf("%.4f\n", dist[nodes - 1]);
    }

    static class Pair implements Comparable<Pair> {

        int node;
        double weight;

        public Pair(int node, double weight) {
            this.node = node;
            this.weight = weight;
        }

        public int compareTo(Pair other) {
            return this.weight > other.weight ? -1 :  1;
        }
    }
}