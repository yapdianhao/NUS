import java.util.*;

class Flowery2 {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int nodes = io.getInt();
        int edges = io.getInt();
        List<List<Edge>> adj = new ArrayList<>();
        List<List<Edge>> parents = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<Edge>());
            parents.add(new ArrayList<>());
        }
        HashMap<Integer, HashMap<Integer, Integer>> hm = new HashMap<>();
        for (int i = 0; i < edges; i++) {
            int node = io.getInt();
            int neigh = io.getInt();
            int weight = io.getInt();
            if (node == neigh) continue;
            adj.get(node).add(new Edge(neigh, weight));
            adj.get(neigh).add(new Edge(node, weight));
        }
        /*for (int i = 0; i < nodes; i++) {
            io.println(adj.get(i));
        }*/
        //HashMap<Integer, HashMap<Integer, Integer>> hm = new HashMap<>();
        djikstra(io, nodes, edges, adj, parents);
        io.close();
    }

    static void djikstra(Kattio io, int nodes, int edges, List<List<Edge>> adj, List<List<Edge>> parents) {
        Edge source = new Edge(0, 0);
        int[] dist = new int[nodes];
        int MAX = (int) Math.pow(10, 9);
        Arrays.fill(dist, MAX);
        dist[0] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.add(source);
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (dist[curr.key] < curr.weight) continue;
            for (Edge neighbour : adj.get(curr.key)) {
                if (dist[neighbour.key] > dist[curr.key] + neighbour.weight) {
                    dist[neighbour.key] = dist[curr.key] + neighbour.weight;
                    parents.get(neighbour.key).clear();
                    //if (neighbour.key == 9 && curr.key == 7) io.println("check: " + neighbour);
                    parents.get(neighbour.key).add(new Edge(curr.key, neighbour.weight));
                    pq.add(neighbour);
                } else if (dist[neighbour.key] == dist[curr.key] + neighbour.weight) {
                    parents.get(neighbour.key).add(new Edge(curr.key, neighbour.weight));
                }
            }
        }
        //io.println("dist: " + Arrays.toString(dist));
        long total = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        boolean[] visited = new boolean[nodes];
        Arrays.fill(visited, false);
        q.offer(nodes - 1);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (visited[curr]) continue;
            visited[curr] = true;
            //io.println("curr" + curr);
            //io.println(parents.get(curr));
            for (Edge parent : parents.get(curr)) {
                //io.println(parent.weight);
                total += parent.weight;
                q.offer(parent.key);
            }
        }
        io.println(total * 2);
    }
    static class Edge implements Comparable<Edge> {

        int key;
        int weight;

        public Edge(int key, int weight) {
            this.key = key; 
            this.weight = weight;
        }

        public String toString() {
            return "(" + this.key + ", " + this.weight + ")";
        }

        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

}