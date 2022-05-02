import java.util.Arrays;
import java.util.PriorityQueue;


class Blockcrusher {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int row = io.getInt();
        int col = io.getInt();
        while (row != 0 && col != 0) {
            solve(io, row, col);
            row = io.getInt();
            col = io.getInt();
            io.println();
        }
        io.close();
    }

    static boolean inBoundsX(int x, int row) {
        return -1 < x && x < row;
    }

    static boolean inBoundsY(int y, int col) {
        return -1 < y && y < col;
    }

    static void solve(Kattio io , int row, int col) {
        char[][] dummy = new char[row][col];
        int[][] dist = new int[row][col];
        Point[][] parents = new Point[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(dist[i], (int) Math.pow(10, 9));
        }
        int[][] block = new int[row][col];
        for (int i = 0; i < row; i++) {
            String word = io.getWord();
            char[] charArr = word.toCharArray();
            for (int j = 0; j < col; j++) {
                block[i][j] = Character.getNumericValue(charArr[j]);
            }
        }
        PriorityQueue<Point> pq = new PriorityQueue<Point>();
        for (int i = 0; i < col; i++) {
            dist[0][i] = block[0][i];
            pq.add(new Point(0, i, dist[0][i]));
        }
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            int x = curr.x;
            int y = curr.y;
            if (dist[x][y] < curr.distance) {
                continue;
            }
            for (int k = -1; k < 2; ++k) {
                for (int l = -1; l < 2; ++l) {
                    if (k == 0 && l == 0) {
                        continue;
                    }
                    int newx = x + k;
                    int newy = y + l;
                    if (inBoundsX(newx, row) && inBoundsY(newy, col)) {
                        if (dist[newx][newy] > curr.distance + block[newx][newy]) {
                            dist[newx][newy] = curr.distance + block[newx][newy];
                            parents[newx][newy] = curr;
                            pq.add(new Point(newx, newy, dist[newx][newy]));
                        }
                    }
                }
            }
        }
        //start getting the smallest distance from below the block
        int min = 0;
        for (int i = 0; i < col; i++) {
            if (dist[row - 1][i] < dist[row - 1][min]) {
                min = i;
            }
        }
        Point btm = new Point(row - 1, min, 0);
        while (btm.x != 0) {
            dummy[btm.x][btm.y] = ' ';
            Point parent = parents[btm.x][btm.y];
            btm = parent;
        }
        dummy[btm.x][btm.y] = ' ';
        for (int i = 0; i < row; i++) {
            String toPrint = "";
            for (int j = 0; j < col; j++) {
                if (dummy[i][j] == ' ') {
                    toPrint += ' ';
                } else {
                    toPrint += block[i][j];
                }
            }
            System.out.println(toPrint);
        }
        System.out.println();
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        public int compareTo(Point other) {
            return this.distance - other.distance;
        }

        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    }
}