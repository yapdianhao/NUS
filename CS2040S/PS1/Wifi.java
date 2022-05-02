import java.util.*;

class Wifi {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int cases = kattio.getInt();
        for (int i = 0; i < cases; i++) {
            int distance = findDistance(kattio);
            if (distance % 2 == 0) {
                kattio.println(String.format("%d.0", (distance / 2)));
            } else {
                kattio.println(String.format("%d.5", (distance / 2)));
            }
        }
        kattio.close();
    }

    static int findDistance(Kattio kattio) {
        int routers = kattio.getInt();
        int houses = kattio.getInt();
        int[] arr = new int[houses];
        for (int i = 0; i < houses; i++) {
            arr[i] = kattio.getInt();
        }
        Arrays.sort(arr);
        int low = 0;
        int high = 1000000;
        while (low < high) {
            //System.out.println(low);
            //System.out.println(high);
            int mid = (low + high) / 2;
            int APs = findRouters(arr, mid);
            if (APs > routers) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    static int findRouters(int[] houses, int mid) {
        int APs = 0;
        int temp = 0; // this is set to 0 because 0 < any house number and at least one router is needed
        for (int i : houses) {
            if (temp < i) {
                // the house's distance is beyond coverage
                APs++;
                temp = i + mid; // this add function is because, add the distance to the house in coverage.
            } else {
                continue;
            }
        }
        return APs;
    }
}