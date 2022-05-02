import java.util.Arrays;

class SSSP3 {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int nodes = io.getInt();
        int edges = io.getInt();
        int queries = io.getInt();
        int source = io.getInt();
        while (nodes != 0 || edges != 0 || queries != 0 || source != 0) {
            solve(io, nodes, edges, queries, source);
            io.println();
            nodes = io.getInt();
            edges = io.getInt();
            queries = io.getInt();
            source = io.getInt();
        }
        io.close();
    }

    static void solve(Kattio io, int nodes, int edges, int queries, int source) {
        long[] dist = new long[nodes];
        Edge[] edgeList = new Edge[edges];
        long MAX = Integer.MAX_VALUE;
        Arrays.fill(dist, MAX);
        dist[source] = 0;
        for (int i = 0; i < edges; i++) {
            Edge edge = new Edge(io.getInt(), io.getInt(), io.getInt());
            edgeList[i] = edge;
        }
        for (int i = 0; i < nodes; i++) {
            for (Edge edge : edgeList) {
                int u = edge.from;
                int v = edge.to;
                int weight = edge.weight;
                if ((dist[v] > dist[u] + weight) && dist[u] != MAX) {
                    dist[v] = dist[u] + (long) weight;
                }
            }
        }
        for (int i = -1; i < 1; i++) {
            for (Edge edge : edgeList) {
                int u = edge.from;
                int v = edge.to;
                int weight = edge.weight;
                if (dist[v] > dist[u] + weight && dist[u] != MAX) {
                    dist[v] = -MAX;
                }
            }
        }

        for (int i = 0; i < queries; i++) {
            int target = io.getInt();
            if (dist[target] == -MAX) {
                io.println("-Infinity");
            } else if (dist[target] == MAX) {
                io.println("Impossible");
            } else {
                io.println(dist[target]);
            }
        }
    }

    static class Edge {

        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

    }
}