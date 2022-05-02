import java.util.*;

class TreasureHunt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        char[][] map = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = sc.next();
            for (int j = 0; j < cols; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        int count = 0;
        boolean[][] seen = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seen[i][j] = false;
            }
        }
        boolean completed = false;
        int row = 0;
        int col = 0;
        char start = map[row][col];
        while (!completed) {
            try{
                if (start == 'E') {
                    start = map[row][++col];
                    if (seen[row][col - 1] == true) {
                        System.out.println("Lost");
                        break;
                    } else {
                        seen[row][col - 1] = true;
                    } 
                    count++;
                } else if (start == 'S') {
                    start = map[++row][col];
                    if (seen[row - 1][col] == true) {
                        System.out.println("Lost");
                        break;
                    } else {
                        seen[row - 1][col] = true;
                    }
                    count++;
                } else if (start == 'W') {
                    start = map[row][--col];
                    if (seen[row][col + 1] == true) {
                        System.out.println("Lost");
                        break;
                    } else {
                        seen[row][col + 1] = true;
                    }
                    count++;
                } else if (start == 'N') {
                    start = map[--row][col];
                    if (seen[row + 1][col] == true) {
                        System.out.println("Lost");
                        break;
                    } else {
                        seen[row + 1][col] = true;
                    }
                    count++;
                } else if (start == 'T') {
                    System.out.println(count);
                    completed = true;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Out");
                break;
            }
        }
    }
}