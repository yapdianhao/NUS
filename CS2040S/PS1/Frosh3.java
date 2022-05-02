import java.util.*;

class Frosh3 {

    public static void main(String[] args) {
        int[] arr = {5, 1, 3, 4, 2, 7, 6};
        System.out.println(mergeSort(arr));
        System.out.println("sorted " + Arrays.toString(arr));
        //io.println(Arrays.toString(arr));
    }

    static long mergeSort(int[] arr) {
        if (arr.length > 1) {
            int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
            int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
            long ansLeft = mergeSort(left);
            long ansRight = mergeSort(right);
            return ansLeft + ansRight + merge(left, right, arr);
        }
        return 0;
    }

    static long merge(int[] left, int[] right, int[] arr) {
        long inv = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        System.out.println(Arrays.toString(arr));
        while (i < left.length || j < right.length) {
            if (i >= left.length) {
                arr[k++] = right[j++];
                //System.out.println(Arrays.toString(arr));
            } else if (j >= right.length) {
                arr[k++] = left[i++];
                //System.out.println(Arrays.toString(arr));
            } else {
                if (left[i] >= right[j]) {
                    arr[k++] = left[i++];
                } else {
                    inv += (j - i);
                    System.out.println(left.length + " " + i);
                    arr[k++] = right[j++];
                }
            }
            //System.out.println(Arrays.toString(arr));
        }
        //System.out.println(inv);
        return inv;
    }

}