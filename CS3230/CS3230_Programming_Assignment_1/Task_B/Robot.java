/*
Name: Yap Dian Hao
Key idea: If a chosen robot cannot arrange the boxes under the battery limit, then a robot with larger weight is needed.
Algorithm: 
- first, sort the weight array.
- binary search the array to get the minimum possible weight of that robot.
- in the binary search step, count the middle robot with the merge sort algorithm. If the selected robot's inversions is larger than 
the battery limit, choose a robot with larger weight instead and recurse on the right half of the array. Else, the robot's weight can be 
further minimize by recursing on the left half of the array.
- in the mergesort algorithm, first count the inversions, arr[i] > arr[j] in the first pass. next, count the significant inversions 
in the second pass, and copy the array elements to sort the array in the third pass.
- after getting the minimum weight of the robots needed, loop through the robots that has the weight equal or larger than this but has
the minimum cost.

Time complexity: T(n/2) + O(n log n) = ⍬(n log n) by master theorem case 3.
*/
import java.util.*;

class Robot {

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
        CuteRobot robots[] = new CuteRobot[N];
        for (int i = 0; i < N; i++) {
            robots[i] = new CuteRobot(W[i], C[i]);
        }
        Arrays.sort(robots);
		int minw = binarySearch(robots, arr, B, 0, N - 1);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (W[i] < minw) {
                continue;
            }
            if (C[i] < ans) {
                ans = C[i];
            }
        }
        System.out.println(ans);
    }

    static int binarySearch(CuteRobot[] robots, int[] boxes, int ref, int lo, int hi) {
        if (hi > lo) {
            //System.out.println(true);
            int[] copy = Arrays.copyOf(boxes, boxes.length);
            int mid = (lo + hi) / 2;
            CuteRobot temp = robots[mid];
            int inv = mergesort(copy, temp);
            if (inv <= ref) { // if the charges required are more then the provided, search robots with more weight
                return binarySearch(robots, boxes, ref, lo, mid);
            } else {
                return binarySearch(robots, boxes, ref, mid + 1, hi);
            }
        }
        return robots[lo].w;
    }

    static int mergesort(int[] arr, CuteRobot cr) {
        if (arr.length <= 1) {
            return 0;
        }
        int[] leftArr = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] rightArr = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        return mergesort(leftArr, cr) + mergesort(rightArr, cr) + merge(arr, leftArr, rightArr, cr);
    }

    static int merge(int[] arr, int[] leftArr, int[] rightArr, CuteRobot cr) {
        int i = 0;
        int j = 0;
        int k = 0;
        int inv = 0;
        // first pass to count inversions
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] > rightArr[j]) {
                inv += leftArr.length - i;
                j++;
            } else {
                i++;
            }
        }
        i = 0;
        j = 0;
        // second pass to count significant inversions
        while (i < leftArr.length && j < rightArr.length) {
            // have to be careful of integer overflow for test case 3
            if (leftArr[i] / cr.w > rightArr[j]) {
                inv += leftArr.length - i;
                j++;
            } else {
                i++;
            }
        }
        i = 0;
        j = 0;
        // third pass to merge the arrays
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

    static class CuteRobot implements Comparable<CuteRobot> {
        int w;
        int c;

        public CuteRobot(int w, int c) {
            this.w = w;
            this.c = c;
        }

        public int compareTo(CuteRobot ot) {
            return this.w - ot.w;
        }

        public String toString() {
            return "(" + this.w + ", " + this.c + ")";
        }
    }
}
