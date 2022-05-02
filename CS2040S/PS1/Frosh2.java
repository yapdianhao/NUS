    import java.util.*;

class Frosh2 {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int nums = kattio.getInt();
        int[] arr = new int[nums];
        for (int i = 0; i < nums; i++) {
            arr[i] = kattio.getInt();
        }
        long inv = countInversions(arr);
        kattio.println(inv);
        kattio.close();
    }

    static long countInversions(int[] arr) {
        if (arr.length <= 1) {
            return 0;
        }
        int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        long ansLeft = countInversions(left);
        long ansRight = countInversions(right);
        return ansLeft + ansRight + merge(left, right, arr);
    }

    static long merge(int[] left, int[] right, int[] arr) {
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
                    inv += (left.length - i);
                    arr[k++] = right[j++];
                }
            }
            //System.out.println(Arrays.toString(arr));
        }
        return inv;
    }
}