import java.util.*;

class Camels {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int length = kattio.getInt();
        int[] jaap = new int[length];
        int[] jan = new int[length];
        int[] thij = new int[length];
        boolean[] booleanJaap = new boolean[length];
        for (int i = 0; i < length; i++) {
            jaap[i] = kattio.getInt();
        }
        for (int i = 0; i < length; i++) {
            jan[i] = kattio.getInt();
        }
        for (int i = 0; i < length; i++) {
            thij[i] = kattio.getInt();
        }
        subset(jaap, 2, 0, 0, booleanJaap);
        kattio.close();
    }

    static void subset(int[] arr, int k, int start, int currLen, boolean[] used) {
        if (currLen == k) {
            for (int i = 0; i < arr.length; i++) {
                if (used[i] == true) {
                    System.out.println(arr[i] + " ");
                }
            }
            System.out.println();
            return;
        }
        used[start] = true;
        subset(arr, k, start + 1, currLen + 1, used);
        used[start] = false;
        subset(arr, k, start + 1, currLen, used);
    }
}