import java.util.*;

class Prize {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        long items = kattio.getLong();
        long price = kattio.getLong();
        int intItems = (int) items;
        long[] arr = new long[intItems];
        for (int i = 0; i < intItems; i++) {
            arr[i] = kattio.getLong();
        }
        long count = 1;
        if (items == 1) {
            kattio.println(items);
            kattio.close();
            return;
        } 
        Arrays.sort(arr);
        for (int i = 0; i < intItems - 1; i++) {
            if (arr[i] + arr[i + 1] <= price) {
                count++;
            }
        }
        kattio.println(count);
        kattio.close();
    }
}