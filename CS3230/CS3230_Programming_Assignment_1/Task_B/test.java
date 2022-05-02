import java.util.*;

class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int B = sc.nextInt();
        int arr[] = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int W[] = new int[N];
        for (int i = 0; i < N; i++) {
            W[i] = sc.nextInt();
        }
        int C[] = new int[N];
        for (int i = 0; i < N; i++) {
            C[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            int[] copy = new int[N];
            for (int j = 0; j < N; j++) {
                copy[j] = arr[j];
            }
            if (mergesort(copy, W[i]) < B) {
                System.out.println(true);
            }
        }
    }

    static int merge(int[] arr, int[] leftArr, int[] rightArr, int key) {
        //System.out.println(Arrays.toString(arr));
        //System.out.println(cr);
        int i = 0;
        int j = 0;
        int k = 0;
        int inv = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] > rightArr[j]) {
                //System.out.println(true);
                inv += leftArr.length - i;
                j++;
            } else {
                i++;
            }
        }
        i = 0;
        j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] > key * rightArr[j]) {
                inv += leftArr.length - i;
                j++;
            } else {
                i++;
            }
        }
        i = 0;
        j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] > rightArr[j]) {
                arr[k++] = rightArr[j++];
            } else {
                arr[k++] = leftArr[i++];
            }
        }
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
        return inv;
    }

    static int mergesort(int[] arr, int cr) {
        if (arr.length <= 1) {
            return 0;
        }
        int[] leftArr = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] rightArr = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        return mergesort(leftArr, cr) + mergesort(rightArr, cr) + merge(arr, leftArr, rightArr, cr);
    }

}