import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

class Terraces {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int col = io.getInt();
        int row = io.getInt();
        int[][] garden = new int[row][col];
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(visited[i], false);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                garden[i][j] = io.getInt();
            }
        }
        int count = 0; 
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j]) {
                    io.println(i + " " + j);
                    Ans ans = BFS(garden, visited, i, j);
                    if (ans.flag) {
                        io.println("this is " + i + " " + j);
                        count += ans.total;
                    }
                    for (int r = 0; r < row; r++) {
                        io.println(Arrays.toString(visited[r]));
                    }
                }
            }
        }
        /*for (int i = 0; i < row; i++) {
            io.println(Arrays.toString(visited[i]));
        }*/
        io.println(count);
        io.close();
    }

    static Ans BFS(int[][] garden, boolean[][] visited, int i, int j) {
        int total = 0;
        boolean check = true;
        visited[i][j] = true;
        Pair start = new Pair(i, j);
        Queue<Pair> s = new LinkedList<Pair>();
        s.offer(start);
        while (!s.isEmpty()) {
            Pair curr = s.poll();
            total++;
            check = getNeighbour(curr, garden);
            if (!check) {
                return new Ans(total, check);
            }
            for (Pair neighbour : curr.neighbours) {
                if (!visited[neighbour.x][neighbour.y]) {
                    //total++;
                    System.out.println("neighbour " + neighbour.x + " " + neighbour.y + " : " + garden[neighbour.x][neighbour.y]);
                    visited[neighbour.x][neighbour.y] = true;
                    s.offer(neighbour);
                }
            }
        }
        return new Ans(total, check);
    }

    static boolean getNeighbour(Pair curr, int[][] garden) {
        boolean flag = true;
        try {
            int check = garden[curr.x + 1][curr.y];
            if (check == garden[curr.x][curr.y]) {
                curr.neighbours.add(new Pair(curr.x + 1, curr.y));
            } else if (check < garden[curr.x][curr.y]) {
                if (curr.x == 3 && curr.y == 2) {
                    System.out.println("down");
                }
               flag = false;
            }
        } catch (Exception e) {

        } try {
            int check = garden[curr.x - 1][curr.y];
            if (check == garden[curr.x][curr.y]) {
                curr.neighbours.add(new Pair(curr.x - 1, curr.y));
            } else if (check < garden[curr.x][curr.y]) {
                if (curr.x == 3 && curr.y == 2) {
                    System.out.println("up");
                }
                flag = false;
            }
        } catch (Exception e) {

        } try {
            int check = garden[curr.x][curr.y + 1];
            if (check == garden[curr.x][curr.y]) {
                curr.neighbours.add(new Pair(curr.x, curr.y + 1));
            } else if (check < garden[curr.x][curr.y]) {
                if (curr.x == 3 && curr.y == 2) {
                    System.out.println("right");
                }
                flag = false;
            }
        } catch (Exception e) {

        } try {
            int check = garden[curr.x][curr.y - 1];
            if (check == garden[curr.x][curr.y]) {
                curr.neighbours.add(new Pair(curr.x, curr.y - 1));
            } else if (check < garden[curr.x][curr.y]) {
                if (curr.x == 3 && curr.y == 2) {
                    System.out.println("left");
                }
                flag = false;
            }
        } catch (Exception e) {

        }
        return flag;
    }

    static class Ans {
        int total;
        boolean flag;
        public Ans(int total, boolean flag) {
            this.total = total;
            this.flag = flag;
        }
    }

    static class Pair {
        int x;
        int y;
        List<Pair> neighbours;
        public Pair(int x, int y) {
            this.x = x; 
            this.y = y;
            this.neighbours = new ArrayList<Pair>();
        }
    }
}