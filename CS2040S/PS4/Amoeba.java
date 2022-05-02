import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Amoeba {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int row = io.getInt();
        int col = io.getInt();
        char[][] grid = new char[row][col];
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(visited[i], false);
        }
        for (int i = 0; i < row; i++) {
            String line = io.getWord();
            char[] arr = line.toCharArray();
            for (int j = 0; j < col; j++) {
                grid[i][j] = arr[j];
            }
        }
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '#' && !visited[i][j]) {
                    count++;
                    BFS(grid, visited, i, j);
                }
            }
        }
        io.println(count);
        io.close();
    }

    static void BFS(char[][] grid, boolean[][] visited, int i, int j) {
        Pair start = new Pair(i, j);
        Queue<Pair> q = new LinkedList<Pair>();
        q.offer(start);
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            visited[curr.x][curr.y] = true;
            addNeighbour(curr, grid);
            for (Pair neighbour : curr.neighbours) {
                if (!visited[neighbour.x][neighbour.y]) {
                    visited[neighbour.x][neighbour.y] = true;
                    q.add(neighbour);
                }
            }
        }
    }

    static void addNeighbour(Pair curr, char[][] grid) {
        try {
            char check = grid[curr.x + 1][curr.y];
            if (check == '#') {
                Pair down = new Pair(curr.x + 1, curr.y);
                curr.neighbours.add(down);
            }
        } catch (Exception e) {

        } try {
            char check = grid[curr.x - 1][curr.y];
            if (check == '#') {
                Pair up = new Pair(curr.x - 1, curr.y);
                curr.neighbours.add(up);
            }
        } catch (Exception e) {

        } try {
            char check = grid[curr.x][curr.y + 1];
            if (check == '#') {
                Pair right = new Pair(curr.x, curr.y + 1);
                curr.neighbours.add(right);
            }
        } catch (Exception e) {

        } try {
            char check = grid[curr.x][curr.y - 1];
            if (check == '#') {
                Pair left = new Pair(curr.x, curr.y - 1);
                curr.neighbours.add(left);
            }
        } catch (Exception e) {

        } try {
            char check = grid[curr.x - 1][curr.y + 1];
            if (check == '#') {
                Pair upright = new Pair(curr.x - 1, curr.y + 1);
                curr.neighbours.add(upright);
            }
        } catch (Exception e) {

        } try {
            char check = grid[curr.x - 1][curr.y - 1];
            if (check == '#') {
                Pair upleft = new Pair(curr.x - 1, curr.y - 1);
                curr.neighbours.add(upleft);
            }
        } catch (Exception e) {

        } try {
            char check = grid[curr.x + 1][curr.y + 1];
            if (check == '#') {
                Pair downright = new Pair(curr.x + 1, curr.y + 1);
                curr.neighbours.add(downright);
            } 
        } catch (Exception e) {

        } try {
            char check = grid[curr.x + 1][curr.y - 1];
            if (check == '#') {
                Pair downleft = new Pair(curr.x + 1, curr.y - 1);
                curr.neighbours.add(downleft);
            }
        } catch (Exception e) {

        }
    }

    static class Pair {

        int x;
        int y;
        ArrayList<Pair> neighbours;

        public Pair(int x, int y) {
            this.neighbours = new ArrayList<Pair>();
            this.x = x;
            this.y = y;
        }
    }
}