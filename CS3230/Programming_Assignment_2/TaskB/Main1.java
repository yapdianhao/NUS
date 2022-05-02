import java.util.*;

/*
Name: Yap Dian Hao
ID: A0184679H
Algorithm: Dynamic Programming
For every time Alice blows x air into a balloon, the balloon either bursts or not burst. If the balloon bursts, it means every volume
bigger than x will burst balloon. So, Alice will need to continue with n - 1 balloons, and x - 1 volumes of air. Or the balloon doesnt
burst, so Alice will need to continue with n balloons and m - x volume of air. In both cases, Alice will need to consider the worst case
.

We will build a 2d array with n * m rows. when on the first row, which means that alice has one ballon left, she will need x trials
for each column <= m. when there are <= 1 options of air volume left, one balloon is enough. Then, we fill in the table. for each
iteration at i, j, we take the value at [i][j] to be the maximum of [i - 1][k - 1], which the ballon burst, and [i][j - k], which
the balloon doesnt burst, for all k up to j. An internal loop is needed to find the worst case, and the final answer will be at [n][m].
Time Complexity: ⍬(NM^2)
*/
public class Main {

    public static int solve(int n, int m) {
        int[][] grid = new int[n + 1][m + 1];
        // fill the first 2 columns. If <= 1 options, only 1 balloon is enough.
        for (int i = 0; i <= n; i++) {
            grid[i][0] = 0;
            grid[i][1] = 1;
        }
        // fill the first row. If one balloon left, then it will be used for all trials until it bursts.
        for (int i = 1; i <= m; i++) {
            //grid[0][i] = 0;
            grid[1][i] = i;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                grid[i][j] = (int) Math.pow(10, 9);
                int temp;
                for (int k = 1; k <= j; k++) {
                    // get the worst case.
                    temp = 1 + Math.max(grid[i - 1][k - 1], grid[i][j - k]);
                    // the value at grid[i][j] for the minimum trials.
                    grid[i][j] = Math.min(temp, grid[i][j]);
                }
            }
        }
        return grid[n][m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.print(solve(n, m));
        scanner.close();
    }
}
