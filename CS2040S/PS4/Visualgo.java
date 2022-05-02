import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayList;
//import java.util.Comparable;

class Visualgo {

    public static void main(String[] args) {
        int MAX = (int) Math.pow(10, 9);
        Kattio io = new Kattio(System.in);
        int nodes = io.getInt();
        int edges = io.getInt();
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<Edge>());
        }
        for (int i = 0; i < edges; i++) {
            int node = io.getInt();
            adj.get(node).add(new Edge(io.getInt(), io.getInt(), MAX));
        }
        int[] dist = new int[nodes];
        Arrays.fill(dist, MAX);
        int[] paths = new int[nodes];
        int start = io.getInt();
        int end = io.getInt();
        djikstra(adj, dist, paths, start, end);
        io.close();
    }

    static void djikstra(List<List<Edge>> adj, int[] dist, int[] paths, int start, int end) {
        dist[start] = 0;
        paths[start] = 1;
        Edge source = new Edge(start, 0, 0);
        Queue<Edge> q = new LinkedList<Edge>();
        q.offer(source);
        while (!q.isEmpty()) {
            Edge curr = q.poll();
            if (dist[curr.to] < curr.weight) {
                continue;
            }
            for (Edge neighbour : adj.get(curr.to)) {
                if (dist[neighbour.to] > dist[curr.to] + neighbour.weight) {
                    paths[neighbour.to] = paths[curr.to];
                    dist[neighbour.to] = dist[curr.to] + neighbour.weight;
                    q.add(new Edge(neighbour.to, dist[neighbour.to], dist[neighbour.to]));
                } else if (dist[neighbour.to] == dist[curr.to] + neighbour.weight) {
                    paths[neighbour.to] += paths[curr.to];
                }
            }
        }
        System.out.println(paths[end]);
    }

    static class Edge implements Comparable<Edge>{
        int to;
        int weight;
        int relaxed;

        public Edge(int to, int weight, int relaxed) {
            this.to = to;
            this.weight = weight;
            this.relaxed = relaxed;
        }

        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
}