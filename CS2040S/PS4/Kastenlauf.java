import java.util.PriorityQueue;
import java.util.Hashtable;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Kastenlauf {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int cases = io.getInt();
        for (int i = 0; i < cases; i++) {
            solve(io);
        }
        io.close();
    }

    static void solve(Kattio io) {
        int shops = io.getInt();
        boolean[] visited = new boolean[shops + 2];
        Point[] points = new Point[shops + 2];
        Hashtable<Point, Integer> ht = new Hashtable<Point, Integer>();
        for (int i = 0; i < shops + 2; i++) {
            Point point = new Point(io.getInt(), io.getInt());
            points[i] = point;
            ht.put(point, i);
        }
        Queue<Point> q = new LinkedList<Point>();
        q.add(points[0]);
        while (!q.isEmpty()){
            Point curr = q.poll();
            visited[ht.get(curr)] = true;
            for (int i = 0; i < shops + 2; i++) {
                Point temp = points[i];
                if (temp != curr && !visited[i]) {
                    if ((Math.abs(temp.x - curr.x) + Math.abs(temp.y - curr.y)) <= 1000) {
                        q.add(temp);
                        visited[i] = true;
                    }
                }
            }
        }
        io.println(visited[shops + 1] ? "happy" : "sad");
    }

    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + this.x + " " + this.y + ")";
        }
    }
}