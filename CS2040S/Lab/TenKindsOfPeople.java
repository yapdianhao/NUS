import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;


class TenKindsOfPeople {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int row = kattio.getInt();
        int col = kattio.getInt();
        int[][] map = new int[row][col];
        for (int i = 0; i < row; i++) {
            String line = kattio.getWord();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                map[i][j] = Character.getNumericValue(c);
            } 
        }
        int query = kattio.getInt();
        for (int i = 0; i < query; i++) {
            solve(kattio, map);
        }
        kattio.close();
    }

    public static void solve(Kattio kattio, int[][] map) {
        int r1 = kattio.getInt();
        int c1 = kattio.getInt();
        int r2 = kattio.getInt();
        int c2 = kattio.getInt();
        int row = map.length;
        int col = map[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(visited[i], false);
        }
        Stack<Pair> stack = new Stack<Pair>();
        Pair start = new Pair(r1 - 1, c1 - 1);
        Pair dest = new Pair(r2 - 1, c2 - 1);
        boolean res = depthFirstSearch(stack, visited, map, start, dest);
        if (res) {
            kattio.println(map[start.x][start.y] == 1 ? "decimal" : "binary");
        } else {
            kattio.println("neither");
        }
    }

    public static boolean depthFirstSearch(Stack<Pair> stack, boolean[][] visited, int[][] map, Pair start, Pair dest) {
        // use a stack to store all unvisited neighbours. 
        // if neighbour is same as self, push to stack.
        visited[start.x][start.y] = true;
        stack.push(start);
        while (!stack.isEmpty()) {
            Pair curr = (Pair) stack.pop();
            if (curr.x == dest.x && curr.y == dest.y) {
                return true;
            }
            curr.addNeighBours(map, visited);
            for (Pair neighbour : curr.neighbours) {
                if (!visited[neighbour.x][neighbour.y]) {
                    visited[neighbour.x][neighbour.y] = true;
                    stack.push(neighbour);
                }
            }
        }
        return false;
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

        public void addNeighBours(int[][] map, boolean[][] visited) {
            try {
                int x = this.x + 1;
                int y = this.y;
                Pair down = new Pair(x, y);
                if (!visited[x][y] && map[this.x][this.y] == map[x][y]) {
                    neighbours.add(down);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // do nothing if array out of index
            }
            try {
                int x = this.x - 1;
                int y = this.y;
                Pair up = new Pair(x, y);
                if (!visited[x][y] && map[this.x][this.y] == map[x][y]) {
                    neighbours.add(up);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // do nothing
            }
            try {
                int x = this.x;
                int y = this.y + 1;
                Pair right = new Pair(x, y);
                if (!visited[x][y] && map[this.x][this.y] == map[x][y]) {
                    neighbours.add(right);
                }
            } catch (ArrayIndexOutOfBoundsException e){
                // zzzz
            }
            try {
                int x = this.x;
                int y = this.y - 1;
                Pair left = new Pair(x, y);
                if (!visited[x][y] && map[this.x][this.y] == map[x][y]) {
                    neighbours.add(left);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // wtf
            }
        }
    }
}