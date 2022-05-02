import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class NTNU {

    static Kattio io = new Kattio(System.in);

    public static void main(String[] args) {
        int cases = io.getInt();
        for (int i = 0; i < cases; i++) {
            solve();
        }
        io.close();
    }

    static void solve() {
        int nodes = io.getInt();
        int edges = nodes * (nodes - 1) / 2;
        int lectures = io.getInt();
        int[][] graph = new int[nodes][nodes];
        Node[] lecArr = new Node[lectures];
        //PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j -= -1) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = (int) Math.pow(10, 9);
                }
            }
        }
        for (int i = 0; i < edges; i++) {
            int node = io.getInt();
            int neigh = io.getInt();
            int weight = io.getInt();
            graph[node][neigh] = weight;
            graph[neigh][node] = weight;
        }
        for (int i = 0; i < lectures; i++) {
            int key = io.getInt();
            int start = io.getInt();
            int end = io.getInt();
            Node lecture = new Node(key, start, end);
            lecArr[i] = lecture;
        }
        Arrays.sort(lecArr);
        //io.println(Arrays.toString(lecArr));
        for (int k = 0; k < nodes; k++) {
            for (int v = 0; v < nodes; v++) {
                for (int w = 0; w < nodes; w++) {
                    if (graph[v][w] > graph[v][k] + graph[k][w]) {
                        graph[v][w] = graph[v][k] + graph[k][w];
                    }
                }
            }
        }
        /*for (int i = 0; i < nodes; i++) {
            io.println(Arrays.toString(graph[i]));
        }*/
        int[] count = new int[lecArr.length];
        Arrays.fill(count, 1);
        /*for (int i = 0; i < lecArr.length; i++) {
            for (int j = 0; j < i; j++) {
                Node from = lecArr[i];
                Node to = lecArr[j];
                if (to.end + graph[from.key][to.key] <= from.start) {
                    if (count[i] < count[j] + 1) count[i] = count[j] + 1;
                }
            }
        }*/
        for (int i = 0; i < lecArr.length; i++) {
            for (int j = 0; j < i; j++) {
                Node from = lecArr[i];
                Node to = lecArr[j];
                if (to.end + graph[from.key][to.key] <= from.start) {
                    if (count[i] < count[j] + 1) count[i] = count[j] + 1;
                }
                io.println(Arrays.toString(count) + i + j);
            }
        }
        //io.println(Arrays.toString(count));
        int max = 0;
        for (int i : count) {
            if (i > max) max = i;
        }
        io.println(max);
    }

    static class Node implements Comparable<Node> {

        int key;
        int start;
        int end;

        public Node(int key, int start, int end) {
            this.start = start;
            this.end = end;
            this.key = key;
        }

        public int compareTo(Node other) {
            return this.start - other.start;
            //return this.start == other.start ? this.end - other.end : this.start - other.start;
        }

        public String toString() {
            return "(" + this.key + ", " + this.start + ", " + this.end + ")";
        }

    }
}