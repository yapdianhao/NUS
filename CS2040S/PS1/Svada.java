import java.util.*;

class Svada {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int time = kattio.getInt();
        int numPickingMonkeys = kattio.getInt();
        int[] Ak = new int[numPickingMonkeys];
        int[] Bk = new int[numPickingMonkeys];
        for (int i = 0; i < numPickingMonkeys; i++) {
            Ak[i] = kattio.getInt();
            Bk[i] = kattio.getInt();
        }
        int numBreakingMonkeys = kattio.getInt();
        int[] Ck = new int[numBreakingMonkeys];
        int[] Dk = new int[numBreakingMonkeys];
        for (int i = 0; i < numBreakingMonkeys; i++) {
            Ck[i] = kattio.getInt();
            Dk[i] = kattio.getInt();
        }
        int res = binarySearch(time, Ak, Bk, Ck, Dk);
        kattio.println(res);
        kattio.close();
    }

    static int pickCoconut(int pickTime, int[] Ak, int[] Bk) {
        int coconuts = 0;
        for (int i = 0; i < Ak.length; i++) {
            int timeAfterPicking = pickTime - Ak[i];
            if (timeAfterPicking >= 0) {
                coconuts++;
                coconuts += timeAfterPicking / Bk[i];
            }
        }
        return coconuts;
    }

    static int breakCoconut(int pickTime, int[] Ck, int[] Dk) {
        int coconuts = 0;
        for (int i = 0; i < Ck.length; i++) {
            int timeAfterFinding = pickTime - Ck[i];
            if (timeAfterFinding >= 0) {
                coconuts++;
                coconuts += timeAfterFinding / Dk[i];
            }
        }
        return coconuts;
    }

    static int binarySearch(int time, int[] Ak, int[] Bk, int[] Ck, int[] Dk) {
        int low = 1; 
        int high = time;
        while (high > low + 1) {
            int mid = (high + low) / 2;
            if (pickCoconut(mid, Ak, Bk) > breakCoconut(time - mid, Ck, Dk)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return low;
    }

}