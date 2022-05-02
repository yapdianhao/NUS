import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

class Cats {

    static Kattio io = new Kattio(System.in);

    public static void main(String[] args) {
        int cases = io.getInt();
        for (int i = 0; i < cases; i++) {
            solve();
        }
        io.close();
    }

    static void solve() {
        int m = io.getInt();
        int c = io.getInt();
        int l = c * (c - 1) / 2;
        boolean[] visited = new boolean[c];
        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            adj.add(new ArrayList<Node>());
        }
        for (int i = 0; i < l; i++) {
            int src = io.getInt();
            int dest = io.getInt();
            int w = io.getInt();
            adj.get(src).add(new Node(src, dest, w));
            adj.get(dest).add(new Node(dest, src, w));
        }
        /*for (int i = 0; i < c; i++) {
            io.println(adj.get(i));
        }*/
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        visited[0] = true;
        int count = 0;
        for (Node n : adj.get(0)) {
            pq.add(n);
        }
        //io.println(pq);
        //io.println("+++++++++");
        while (!pq.isEmpty()) {
            //o.println(pq);
            Node curr = pq.poll();
            if (visited[curr.dest]) {
                continue;
            }
            count++;
            //io.println(curr);
            count += curr.w;
            visited[curr.dest] = true;
            for (Node neigh : adj.get(curr.dest)) {
                if (!visited[neigh.dest]) {
                    pq.add(neigh);
                }
            }
        }
        //io.println(Arrays.toString(visited));
        //io.println(count);
        io.println((count + 1) <= m ? "yes" : "no");
    }

    static class Node implements Comparable<Node> {

        int src;
        int dest;
        int w;

        public Node(int src, int dest, int w) {
            this.src = src;
            this.dest = dest;
            this.w = w;
        }

        public int compareTo(Node ot) {
            return this.w - ot.w;
        }

        public String toString() {
            return "(" + this.src + ", " + this.dest + ", " + this.w + ")";
        }
    }
}