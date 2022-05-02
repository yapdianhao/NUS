import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class Grid {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int row = io.getInt();
        int col = io.getInt();
        int[][] grid = new int[row][col];
        for (int i = 0; i < row; i++) {
            String s = io.getWord();
            char[] arr = s.toCharArray();
            for (int j = 0; j < col; j++) {
                grid[i][j] = Character.getNumericValue(arr[j]);
            }
        }
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(visited[i], false);
        }
        //io.println("?");
        Queue<Pair> q = new LinkedList<Pair>();
        Pair start = new Pair(0, 0, 0);
        //int count = 0;
        q.add(start);
        visited[start.x][start.y] = true;
        //io.println("?");
        while (!q.isEmpty()) {
            Pair temp = q.poll();
            if ((temp.x == row - 1) && (temp.y == col - 1)) {
                io.println(temp.count);
                io.flush();
                return;
            }
            addNeighbours(temp, grid);
            for (Pair neighbour : temp.neighbours) {
                if (!visited[neighbour.x][neighbour.y]) {
                    visited[neighbour.x][neighbour.y] = true;
                    q.add(neighbour);
                }
            }
        }
        io.println(-1);
        io.close();
    }

    static void addNeighbours(Pair temp, int[][] grid) {
        int step = grid[temp.x][temp.y];
        try {
            int check = grid[temp.x + step][temp.y];
            Pair down = new Pair(temp.x + step, temp.y, temp.count + 1);
            temp.neighbours.add(down);
        } catch (Exception e) {

        } try {
            int check = grid[temp.x - step][temp.y];
            Pair up = new Pair(temp.x - step, temp.y, temp.count + 1);
            temp.neighbours.add(up); 
        } catch (Exception e) {

        } try {
            int check = grid[temp.x][temp.y + step];
            Pair right = new Pair(temp.x, temp.y + step, temp.count + 1);
            temp.neighbours.add(right);
        } catch (Exception e) {

        } try {
            int check = grid[temp.x][temp.y - step];
            Pair left = new Pair(temp.x, temp.y - step, temp.count + 1);
            temp.neighbours.add(left);
        } catch (Exception e) {

        }
    }

    static class Pair {

        int x;
        int y;
        ArrayList<Pair> neighbours;
        int count;

        public Pair(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.neighbours = new ArrayList<Pair>();
        }
    }

}