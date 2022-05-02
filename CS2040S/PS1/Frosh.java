import java.util.*;

class Frosh {

    static long inv = 0;

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int nums = kattio.getInt();
        int[] arr = new int[nums];
        for (int i = 0; i < nums; i++) {
            arr[i] = kattio.getInt();
        }
        mergeSort(arr, 0, nums - 1);
        kattio.println(inv);
        //kattio.println(Arrays.toString(arr));
        kattio.close();
    }

    static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    static void merge(int[] arr, int low, int mid, int high) {
        //copy original array into a temporary array first
        // the number of swaps
        int[] temp = new int[arr.length];
        for (int i = low; i <= high; i++) {
            temp[i] = arr[i];
        }
        int idxLeft = low; // to be increased when filling the leftarray
        int idxArr = low; // to be increased when filling the array
        int idxRight = mid + 1; // to be increased when filling the rightarray
        while (idxLeft <= mid && idxRight <= high) {
            if (temp[idxLeft] <= temp[idxRight]) {
                arr[idxArr++] = temp[idxLeft++];
            } else {
                //System.out.println("Im here");
                inv += (mid - idxArr + 1);
                arr[idxArr++] = temp[idxRight++];
            }
        }
        while (idxLeft <= mid) {
            arr[idxArr++] = temp[idxLeft++];
        }
    }
}