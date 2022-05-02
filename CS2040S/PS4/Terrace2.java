import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Terrace2 {
    static int row;
    static int col;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        col = io.getInt();
        row = io.getInt();
        int[] X = {-1, 0, 1, 0};
        int[] Y = {0, -1, 0, 1};
        int total = 0;
        int[][] garden = new int[row][col];
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                garden[i][j] = io.getInt();
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j]) {
                    continue;
                }
                Queue<Pair> q = new LinkedList<Pair>();
                int count = 0;
                boolean flag = false;
                q.add(new Pair(i, j));
                visited[i][j] = true;
                while (!q.isEmpty()) {
                    Pair curr = q.poll();
                    count++;
                    for (int k = 0; k < 4; k++) {
                        try {
                            Pair neighbour = new Pair(curr.x + X[k], curr.y + Y[k]);
                            if (!visited[neighbour.x][neighbour.y] && garden[neighbour.x][neighbour.y] == garden[curr.x][curr.y]) {
                                visited[neighbour.x][neighbour.y] = true;
                                q.offer(neighbour);
                            }
                            if (garden[neighbour.x][neighbour.y] < garden[curr.x][curr.y]) {
                                flag = true;
                            }
                        } catch (Exception e) {

                        }
                    }
                }
                if (!flag) {
                    total += count;
                }
            }
        }
        io.println(total);
        io.close();
    }

    static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}