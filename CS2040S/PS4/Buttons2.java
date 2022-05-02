import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Buttons2 {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int cases = io.getInt();
        for (int i = 0; i < cases; i++) {
            solve(io);
        }
        io.close();
    }

    public static void solve(Kattio io) {
        int MAX = (int) Math.pow(10, 9);
        int buttons = io.getInt();
        int target = io.getInt();
        int[] buttonArr = new int[buttons];
        for (int i = 0; i < buttons; i++) {
            buttonArr[i] = io.getInt();
        }
        int[] dist = new int[3601];
        Queue<Integer> q = new LinkedList<Integer>();
        Arrays.fill(dist, MAX);
        dist[0] = 0;
        q.offer(0);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int button : buttonArr) {
                int temp = curr + button;
                temp = temp > 0 ? temp : 0;
                temp = temp < 3600 ? temp : 3600;
                if (dist[temp] > dist[curr] + 1) {
                    dist[temp] = dist[curr] + 1;
                    q.offer(temp);
                }
            }
        }
        for (int i = target; i < 3601; i++) {
            if (dist[i] != MAX) {
                io.println(dist[i] + " " + (i - target));
                break;
            }
        }
    }
}