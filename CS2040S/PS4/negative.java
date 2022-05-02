import java.util.Arrays;

public class Negative {
    static Edge[] graph;
    static int nodes;
    static int edges;
    static int queries;
    static int start;
    
    public static long[] Bellman(int start) {
        long result[] = new long[nodes];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[start] = 0;

        for (int i = 0; i < nodes - 1; i++) {
            for (Edge e : graph) {
                int src = e.from;
                int dest = e.to;
                int weight = e.weight;
                if (result[dest] > weight + result[src] && result[src] != Integer.MAX_VALUE) {
                    result[dest] = (long) weight + result[src];
                }
            }
        }

        for (int i = 0; i < nodes; i++) {
            for (Edge e : graph) {
                int src = e.from;
                int dest = e.to;
                int weight = e.weight;
                if (result[dest] > weight + result[src] && result[src] != Integer.MAX_VALUE) {
                    result[dest] = Integer.MIN_VALUE;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        // first run
        nodes = io.getInt();
        edges = io.getInt();
        queries = io.getInt();
        start = io.getInt();
        graph = new Edge[edges];

        while (nodes != 0 || edges != 0 || queries != 0 || start != 0) {

            for (int i = 0; i < edges; i++) {
                int from = io.getInt();
                int to = io.getInt();
                int weight = io.getInt();
                graph[i] = new Edge(from, to, weight);
            }

            long[] result = Bellman(start);

            for (int i = 0; i < queries; i++) {
                int dest = io.getInt();

                if (result[dest] == Integer.MIN_VALUE) {
                    io.println("-Infinity");
                } else if (result[dest] == Integer.MAX_VALUE) {
                    io.println("Impossible");
                } else {
                    io.println(result[dest]);
                }
            }
            io.println();
            nodes = io.getInt();
            edges = io.getInt();
            queries = io.getInt();
            start = io.getInt();
        }
        io.close();
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