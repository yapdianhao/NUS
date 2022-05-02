import java.util.*;

class Wifi2 {

    static int MAX = 1000000;
    static int MIN = 0;

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int cases = kattio.getInt();
        int[] accessPoints = new int[cases];
        int[] houses = new int[cases];  
        ArrayList<int[]> houseNumbers = new ArrayList<int[]>();
        for (int i = 0; i < cases; i++) {
            accessPoints[i] = kattio.getInt();
            houses[i] = kattio.getInt();
            int[] houseNumber = new int[houses[i]];
            for (int j = 0; j < houses[i]; j++) {
                houseNumber[j] = kattio.getInt();
            }
            Arrays.sort(houseNumber);
            houseNumbers.add(houseNumber);
        }
        for (int i = 0; i < cases; i++) {
            int distance = findDistance(MAX, MIN, accessPoints[i], houseNumbers.get(i));
            //kattio.println(distance);
            if (distance % 2 == 0) {
                kattio.println(String.format("%d.0", distance / 2));
            } else {
                kattio.println(String.format("%d.5", distance / 2));
            }
        }
        kattio.close();
    }

    static int findDistance(int MAX, int MIN, int maxAPs, int[] houseNumber) {
        while (MIN < MAX) {
            int mid = (MAX + MIN) / 2;
            int routersNeeded = findRouters(mid, houseNumber);
            // to decide whether increase or decrease the distance between houses.
            // if the needed routers exceed the limited routers, increase the distance between houses.
            if (routersNeeded > maxAPs) {
                MIN = mid + 1;
            } else {
                MAX = mid;
            }
            //System.out.println("MIN: " + MIN);
            //System.out.println("MAX: " + MAX);
            //System.out.println("ROUTERS: " + routersNeeded);
        }
        return MIN;
    }

    static int findRouters(int dist, int[] houseNumber) {
        int routers = 0;
        int coverage = 0; 
        for (int number : houseNumber) {
            if (number > coverage) {
                // most difficult part of the program.
                // if the current house is in coverage, continue
                // else set the current coverage to be the house number + distance, with a new AP.
                // previous coverages could be ignored because the usage of a new AP.
                coverage = dist + number;
                routers++;
            }
        }
        return routers;
    }
}