import java.util.Hashtable;
import java.util.Arrays;

class Arbitrage {

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
        double[][] graph = new double[currencies][currencies];
        int rates = io.getInt();
        for (int i = 0; i < currencies; i++) {
            for (int j = 0; j < currencies; j++) {
                if (i == j ) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < rates; i++) {
            int row = ht.get(io.getWord());
            int col = ht.get(io.getWord());
            String ratio = io.getWord();
            String[] splitted = ratio.split(":");
            //io.println(Arrays.toString(splitted));
            graph[row][col] = Math.max(graph[row][col],
                (double)Integer.parseInt(splitted[1]) / (double)Integer.parseInt(splitted[0]));
        }
        for (int k = 0; k < currencies; k++) {
            for (int v = 0; v < currencies; v++) {
                for (int w = 0; w < currencies; w++) {
                    if (graph[k][v] < graph[k][w] * graph[w][v]) {
                        graph[k][v] = graph[k][w] * graph[w][v];
                    }
                }
            }
        }
        for (int i = 0; i < currencies; i++) {
            if (graph[i][i] > 1) {
                io.println("Arbitrage");
                return;
            }
        }
        io.println("ok");
    }
}