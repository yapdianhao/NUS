import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Buttons {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int cases = io.getInt();
        for (int i = 0; i < cases; i++) {
            solve(io);
        }
        io.close();
    }

    static void solve(Kattio io) {
        int buttons = io.getInt();
        int target = io.getInt();
        int MAX = (int) Math.pow(10, 9);
        int[] dist = new int[3601];
        Arrays.fill(dist, MAX);
        dist[0] = 0;
        int[] buttonArr = new int[buttons];
        for (int i = 0; i < buttons; i++) {
            buttonArr[i] = io.getInt();
        }
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(0);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int i : buttonArr) {
                int next = curr + i;
                next = next > 0 ? next : 0;// max of next and 0
                next = next < 3600 ? next : 3600;// min of 3600 and next
                if (dist[next] > dist[curr] + 1) {
                    dist[next] = dist[curr] + 1;
                    q.offer(next);
                }
            }
        }
        for (int i = target; i < 3601; i++) {
            //System.out.println(dist[i]);
            if (dist[i] != MAX) {
                io.println(dist[i] + " " + (i - target));
                return;
            }
        }
        io.flush();
    }
}