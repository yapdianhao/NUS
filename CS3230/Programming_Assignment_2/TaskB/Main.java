import java.util.*;

/**
 * Name: Yap Dian Hao
 * 
 * Student Number: A0184679H
 * 
 * Algorithm: Idea is to work backwards. For a number of x trials and m differnt
 * volumes of air, the result will be a number of bursted balloons and ballons
 * that didnt burst. Whenever a balloon bursted, we know that the volume of air
 * is not the elastic score. For x trials, the worst number of balloons bursted
 * is at most x. Therefore, we can determine (x choose 1) + (x choose 2) + (x
 * choose 3) + ... + (x choose m) different volumes of air. To find the number
 * x, we binary search between the air Alice has to blow.
 * 
 * Time complexity: ⍬(n log m)
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.print(solve(n, m));
        scanner.close();
    }

    public static long solve(long n, long m) {
        long low = 1;
        long high = m;
        while (low < high) {
            long mid = (low + high) / 2;
            if (nck(mid, n, m) < m) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /**
     * Dynamic programming. The goal is to find ∑ x choose i for i from 1 to n.
     * Transform the formula (x choose i ) = (x choose (i - 1)) * (x - i) / (i + 1).
     */
    public static long nck(long mid, long n, long m) {
        long total = 0;
        long curr = 1;
        long[] arr = new long[(int) (n + 1)];
        arr[0] = 1;
        for (int i = 1; i <= n; i++) {
            curr = arr[i - 1] * (mid - (i - 1)) / i;
            total += curr;
            arr[i] = curr;
            if (total >= m)
                return total;
        }
        return total;
    }
}