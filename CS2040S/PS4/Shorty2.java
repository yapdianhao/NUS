import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

class Shorty2 {
    
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int intersections = io.getInt();
        int corridors = io.getInt();
        while (corridors != 0 || intersections != 0) {
            solve(io, intersections, corridors);
            intersections = io.getInt();
            corridors = io.getInt();
        }
        io.close();
    }

    static void solve(Kattio io, int intersections, int corridors) {
        double[] dist = new double[intersections];
        double MIN = (double) Math.pow(10, -9);
        Arrays.fill(dist, MIN);
        PriorityQueue<Node> q = new PriorityQueue<Node>(intersections, new NodeComparator());
        Node start = new Node(0, 1, 1);
        q.add(start);
        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < intersections; i++) {
            List<Node> neighbours = new ArrayList<Node>();
            adj.add(neighbours);
        }
        for (int i = 0; i < corridors; i++) {
            int node = io.getInt();
            Node neighbour = new Node(io.getInt(), io.getDouble(), MIN);
            Node clone = new Node(node, neighbour.weight, MIN);
            adj.get(node).add(neighbour);
            adj.get(neighbour.key).add(clone);
            q.add(neighbour);
            q.add(clone);
        }
        dist[0] = 1;
        while (!q.isEmpty()) {
            Node toProcess = q.poll();
            //io.println(toProcess.key);
            if (dist[toProcess.key] > toProcess.relaxed) {
                continue;
            }
            for (Node node : adj.get(toProcess.key)) {
                //io.println(true);
                /*if (dist[toProcess.key] != dist[node.key] && dist[toProcess.key] == MIN) {
                    dist[node.key] = dist[toProcess.key];
                }*/
                if (dist[node.key] < dist[toProcess.key] * node.weight) {
                    dist[node.key] = dist[toProcess.key] * node.weight;
                    q.add(new Node(node.key, node.weight, dist[node.key]));
                }
            }
        }
        //io.println(Arrays.toString(dist));
        io.printf("%.4f\n", dist[intersections - 1]);
    }

    static class Node {

        int key;
        double weight;
        double relaxed;

        public Node(int key, double weight, double relaxed) {
            this.key = key;
            this.weight = weight;
            this.relaxed = relaxed;
        }
    }

    static class NodeComparator implements Comparator<Node> {

        public int compare(Node one, Node two) {
            return one.relaxed > two.relaxed ? 1 : -1;
        }
    }
}