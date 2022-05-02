import java.util.Arrays;
import java.util.Hashtable;

public class Arb {
    static void Floyd(int numCurrencies, int numRates, Kattio io, double[][] dist) {
        boolean arb = false;
        for (int i = 0; i < numCurrencies - 1; i++) {
            for (int j = 0; j < numCurrencies - 1; j++) {
                dist[i][j] = Math.max(0, dist[i][j]);
            }
        }

        for (int i = 0; i < numCurrencies; i++) {
            for (int j = 0; j < numCurrencies; j++) {
                for (int k = 0; k < numCurrencies; k++) {
                    //io.println(dist[j][k] + " " + dist[j][i] * dist[i][k]);
                    dist[k][i] = Math.max(dist[k][i], dist[k][j] * dist[j][i]);
                }
            }
        }

        for (int i = 0; i < numCurrencies; i++) {
            //io.println(Arrays.toString(dist[i]));
            if (dist[i][i] > 1) {
                io.println("Arbitrage");
                arb = true;
                break;
            }
        }

        if (!arb) {
            io.println("Ok");
        }
    }
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int numCurrencies = io.getInt();

        while (numCurrencies != 0) {
            Hashtable<String, Integer> hash = new Hashtable<>();
            for (int i = 0; i < numCurrencies; i++) {
                hash.put(io.getWord(), i);
            }

            int numRates = io.getInt();
            double[][] graph = new double[numCurrencies][numCurrencies];

            for (int i = 0; i < numRates; i++) {
                int curr1 = (int) hash.get(io.getWord());
                int curr2 = (int) hash.get(io.getWord());
                String rate = io.getWord();
                String[] arr = rate.split(":");
                graph[curr1][curr2] = Double.parseDouble(arr[1]) / Double.parseDouble(arr[0]);
            }

            Floyd(numCurrencies, numRates, io, graph);
            numCurrencies = io.getInt();
        }
        io.close();
    }
}