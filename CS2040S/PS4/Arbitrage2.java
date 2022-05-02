import java.util.Hashtable;
import java.util.Arrays;

class Arbitrage2 {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int currencies = io.getInt();
        while (currencies != 0) {
            solve(io, currencies);
            currencies = io.getInt();
        }
        io.close();
    }

    static void solve(Kattio io, int currencies) {
        Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
        for (int i = 0; i < currencies; i++) {
            ht.put(io.getWord(), i);
        }
        double[] dist = new double[currencies];
        int rates = io.getInt();
        Edge[] edgeList = new Edge[rates];
        for (int i = 0; i < rates; i++) {
            String from = io.getWord();
            String to = io.getWord();
            String[] ratio = io.getWord().split(":");
            double processed = -Math.log(Double.parseDouble(ratio[1]) / Double.parseDouble(ratio[0]));
            Edge edge = new Edge(ht.get(from), ht.get(to), processed);
            edgeList[i] = edge;
        }
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 1;
        for (int i = 0; i < currencies; i++) {
            //Arrays.fill(dist, Double.MAX_VALUE);
            //dist[0] = 0;
            for (Edge edge : edgeList) {
                int u = edge.from;
                int v = edge.to;
                double weight = edge.weight;
                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                }
            }
        }
        for (int i = 0; i < currencies; i++) {
            for (Edge edge : edgeList) {
                int u = edge.from;
                int v = edge.to;
                double weight = edge.weight;
                if (dist[v] > dist[u] + weight) {
                    io.println("Arbitrage");
                    return;
                }
            }
        }
        io.println("ok");
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