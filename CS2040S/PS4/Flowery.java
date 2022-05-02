import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;


class Flowery {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int nodes = io.getInt();
        int edges = io.getInt();
        List<List<Edge>> adj = new ArrayList<>();
        List<List<Edge>> parent = new ArrayList<>();
        int[] dist = new int[nodes];
        int[] paths = new int[nodes];
        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<Edge>());
            parent.add(new ArrayList<Edge>());
        }
        for (int i = 0; i < edges; i++) {
            int node = io.getInt();
            adj.get(node).add(new Edge(io.getInt(), io.getInt()));
        }
        /*for (int i = 0; i < nodes; i++) {
            System.out.println(adj.get(i));
        }*/
        djikstra(io, adj, dist, paths, parent);
        io.close();
    }

    static void djikstra(Kattio io, List<List<Edge>> adj, int[] dist, int[] paths, List<List<Edge>> parent) {
        //Edge[] prev = new Edge[dist.length];
        //boolean[] visited = new boolean[dist.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        //paths[0] = 1;
        dist[0] = 0;
        Edge source = new Edge(0, 0);
        Queue<Edge> q = new LinkedList<Edge>();
        q.offer(source);
        while (!q.isEmpty()) {
            Edge curr = q.poll();
            if (dist[curr.x] < curr.weight) {
                System.out.println("skip");
                continue;
            }
            for (Edge neighbour : adj.get(curr.x)) {
                if (dist[neighbour.x] > dist[curr.x] + neighbour.weight) {
                    //visited[neighbour.x] = true;
                    //prev[neighbour.x] = curr;
                    dist[neighbour.x] = dist[curr.x] + neighbour.weight;
                    //paths[neighbour.x] = paths[curr.x];
                    parent.get(neighbour.x).clear();
                    parent.get(neighbour.x).add(new Edge(curr.x, curr.weight));
                    q.offer(new Edge(neighbour.x, dist[neighbour.x]));
                } else if (dist[neighbour.x] == dist[curr.x] + neighbour.weight) {
                    parent.get(neighbour.x).add(new Edge(curr.x, curr.weight));
                    if (neighbour.x == 4) {
                       // System.out.println(parent.get(neighbour.x));
                    }
                    //paths[neighbour.x] += paths[curr.x];
                }
            }
        }
        long total = 0;
        Queue<Integer> intq = new LinkedList<Integer>();
        intq.offer(dist.length - 1);
        while (!intq.isEmpty()) {
            int curr = intq.poll();
            for (Edge par : parent.get(curr)) {
                //System.out.println(par.weight);
                //System.out.println(par.x);
                //System.out.println("parent: " + par.x + " neighbour: " + curr);
                //System.out.println(total);
                total += par.weight;
                intq.offer(par.x);
            }
        }
        /*for (int i = 0; i < dist.length; i++) {
            io.println(parent.get(i));
        }*/
        System.out.println(Arrays.toString(dist));
        io.println(total);
        //io.println(paths[dist.length - 1]);
    }

    static class Edge {

        int x;
        int weight;

        public Edge(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }

        public String toString() {
            return (this.x  + " " + this.weight);
        }
    }
}