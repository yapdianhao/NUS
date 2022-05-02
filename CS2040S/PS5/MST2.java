import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

class MST2 {

    static Kattio io = new Kattio(System.in);

    public static void main(String[] args) {
        int nodes = io.getInt();
        int edges = io.getInt();
        while (nodes != 0 || edges != 0) {
            solve(nodes, edges);
            nodes = io.getInt();
            edges = io.getInt();
        }
        io.close();
    }

    static void solve(int nodes, int edges) {
        boolean[] visited = new boolean[nodes];
        int total = 0;
        if (edges == 0) {
            io.println("Impossible");
            return;
        }
        List<List<Pair>> adj = new ArrayList<>();
        PriorityQueue<Pair> path = new PriorityQueue<Pair>();
        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<Pair>());
        }
        for (int i = 0; i < edges; i++) {
            int node = io.getInt();
            int neigh = io.getInt();
            int weight = io.getInt();
            adj.get(node).add(new Pair(neigh, weight));
            adj.get(neigh).add(new Pair(node, weight));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        visited[0] = true;
        for (Pair n : adj.get(0)) {
            pq.add(new Edge(0, n.x, n.y));
        }
        while (!pq.isEmpty()) {
            //io.println(true);
            Edge curr = pq.poll();
            if (visited[curr.dest]) {
                //io.println("pass");
                continue;
            }
            visited[curr.dest] = true;
            total += curr.weight;
            path.add(new Pair(curr.src > curr.dest ? curr.dest : curr.src, curr.src > curr.dest ? curr.src : curr.dest));
            for (Pair n : adj.get(curr.dest)) {
                //io.println(n);
                if (!visited[n.x]) {
                    //io.println(n);
                    pq.add(new Edge(curr.dest, n.x, n.y));
                }
            }
        }
        if (hasFalse(visited)) {
            //io.println(path);
            io.println("Impossible");
        } else {
            io.println(total);
            while (!path.isEmpty()) {
                io.println(path.poll());
            }
            //io.println(Arrays.toString(visited));
        }
        //io.println(path);
    }

    static boolean hasFalse(boolean[] visited) {
        for (boolean b : visited) {
            if (!b) return true;
        }
        return false;
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair other) {
            return this.x == other.x ? this.y - other.y : this.x - other.x;
        }

        /*public String toString() {
            return "{" + this.x + " " + this.y + "}";
        }*/

        public String toString() {
            return this.x + " " + this.y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int weight;
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
}