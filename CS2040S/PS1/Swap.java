import java.util.*;

class Swap {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int indexes = kattio.getInt();
        long[] arr = new long[indexes];
        int count = 0;
        for (int i = 0; i < indexes; i++) {
            arr[i] = kattio.getLong();
        }
        long swaps = minSwap(arr);
        kattio.println(swaps);
        kattio.close();
    }


    static long minSwap(long[] arr) {
        int swap = 0;
        boolean[] visited = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            int cycle = 0;
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j] - 1;
                cycle++;
            }
            if (cycle != 0) {
                swap += cycle - 1;
            }
        }
        return swap;
    }
}