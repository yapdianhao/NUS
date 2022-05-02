import java.util.Arrays;
import java.util.Scanner;

class CountInversions {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nums = sc.nextInt();
        int key = sc.nextInt();
        int[] arr = new int[nums];
        for (int i = 0; i < nums; i++) {
            arr[i] = sc.nextInt();
        }
        long inv = countInversions(arr, key);
        System.out.println(inv);
        sc.close();
    }

    static long countInversions(int[] arr, int key) {
        if (arr.length <= 1) {
            return 0;
        }
        int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        long ansLeft = countInversions(left, key);
        long ansRight = countInversions(right, key);
        return ansLeft + ansRight + merge(left, right, arr, key);
    }

    static long merge(int[] left, int[] right, int[] arr, int key) {
        long inv = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length || j < right.length) {
            if (i >= left.length) {
                arr[k++] = right[j++];
                //System.out.println(Arrays.toString(arr));
            } else if (j >= right.length) {
                arr[k++] = left[i++];
                //System.out.println(Arrays.toString(arr));
            } else {
                if (left[i] <= right[j]) {
                    arr[k++] = left[i++];
                } else {
                    if ((left[i] + right[j]) % key == 0) {
                        inv += (left.length - i);
                    }
                     arr[k++] = right[j++];
                }
             }
         }
         return inv;
     }
}