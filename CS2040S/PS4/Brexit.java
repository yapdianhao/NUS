import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;

class Brexit {
    
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int countries = io.getInt();
        int edges = io.getInt();
        int self = io.getInt();
        int leave = io.getInt();
        Hashtable<Integer, Pair> ht = new Hashtable<Integer, Pair>();
        List<List<Integer>> adj = new ArrayList<>();
        boolean[] visited = new boolean[countries];
        Arrays.fill(visited, false);
        for (int i = 0; i < countries; i++) {
            adj.add(new ArrayList<Integer>());
            ht.put(i + 1, new Pair(0, 0));
        }
        for (int i = 0; i < edges; i++) {
            int country = io.getInt();
            int neighbour = io.getInt();
            adj.get(country - 1).add(neighbour);
            adj.get(neighbour - 1).add(country);
            ht.get(country).start++;
            ht.get(country).end++;
            ht.get(neighbour).start++;
            ht.get(neighbour).end++;
        }
        if (leave == self) {
            io.println("leave");
            io.flush();
            return;
        }
        ht.get(leave).end = 0;
        decreaseNeighbour(leave, adj, ht, visited);
        if (ht.get(self).end < (double)ht.get(self).start / 2) {
            io.println("leave");
        } else {
            io.println("stay");
        }
        io.close();
    }

    static void decreaseNeighbour(int target, List<List<Integer>> adj, Hashtable<Integer, Pair> ht, boolean[] visited) {
        if (visited[target - 1] == true ||
         (ht.get(target).end) > (double) (ht.get(target).start / 2)) {
            return;
        } else {
            visited[target - 1] = true;
            for (int i : adj.get(target - 1)) {
                ht.get(i).end -= 1;
                decreaseNeighbour(i, adj, ht, visited);
            }
        }
    }

    static class Pair {

        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "{" + start + ", " + end + "}";
        }
    }
}