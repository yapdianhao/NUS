import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

class Driving {

    static Kattio io = new Kattio(System.in);
    public static void main(String[] args) {
        int nodes = io.getInt();
        int edges = io.getInt();
        int[] parent = new int[nodes];
        int[] size = new int[nodes];
        Arrays.fill(size, 1);
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(); // the sorted edges.
        List<Edge> el = new ArrayList<Edge>();
        for (int i = 0; i < edges; i++) {
            pq.add(new Edge(io.getInt(), io.getInt(), io.getInt()));
        }
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (!find(curr.src, curr.dest, parent)) {
                el.add(curr);
                union(curr.src, curr.dest, parent, size);
            }
        }
        if (el.size() != nodes - 1) {
            io.println("IMPOSSIBLE");
        } else {
            io.println(el.get(el.size() - 1).w);
        }
        io.close();
    }

    static boolean find(int src, int dest, int[] parent) {
        return findRoot(src, parent) == findRoot(dest, parent);
    }

    static void union(int src, int dest, int[] parent, int[] size) {
        while (parent[src] != src) src = parent[src];
        while (parent[dest] != dest) dest = parent[dest];
        if (size[src] < size[dest]) {
            parent[src] = parent[dest];
            size[dest] += size[src];
        } else {
            parent[dest] = parent[src];
            size[src] += size[dest];
        }
    }

    static int findRoot(int i, int[] parent) {
        int root = i;
        while (parent[root] != root) {
            parent[root] = parent[parent[root]];
            root = parent[root];
        }
        return root;
    }

    static class Edge implements Comparable<Edge> {

        int src;
        int dest;
        int w;

        public Edge(int src, int dest, int w) {
            this.src = src;
            this.dest = dest;
            this.w = w;
        }

        public int compareTo(Edge ot) {
            return this.w - ot.w;
        }
    }

}