import java.util.Arrays;

class Shorty {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int intersections = io.getInt();
        int corridors = io.getInt();
        while (intersections != 0 || corridors != 0) {
            solve(io, intersections, corridors);
            intersections = io.getInt();
            corridors = io.getInt();
        }
        io.close();
    }

    static void solve(Kattio io, int intersections, int corridors) {
        double[] dist = new double[intersections];
        Edge[] edgeList = new Edge[corridors];
        double MIN = (double) Math.pow(10, -9);
        Arrays.fill(dist, MIN);
        dist[0] = 1;
        for (int i = 0; i < corridors; i++) {
            Edge edge = new Edge(io.getInt(), io.getInt(), io.getDouble());
            edgeList[i] = edge;
        }
        for (int i = 0; i < intersections; i++) {
            for (Edge edge : edgeList) {
                int u = edge.from;
                int v = edge.to;
                double weight = edge.weight;
                if (dist[u] != dist[v] && dist[u] == MIN) {
                    dist[u] = dist[v];
                }
                if (dist[v] < dist[u] * weight) {
                    dist[v] = dist[u] * weight;
                    dist[u] = dist[u] * weight;
                }
            }
        }
        //io.println(Arrays.toString(dist));
        System.out.printf("%.4f\n", dist[intersections - 1]);
    }

    static class Edge {
        int from;
        int to; 
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}