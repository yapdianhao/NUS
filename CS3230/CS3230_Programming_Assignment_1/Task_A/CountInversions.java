/**
    PLEASE GRADE THIS.
    Name: Yap Dian Hao  
    Idea: enhanced merge sort. In a normal merge sort, in the merge function, if leftarr[i] > rightarr[j], 
    then all the remaining elements in the left array after leftarr[i] is larger than leftarr[j], so we have 
    leftarray's length - i inversions. However, since this question requests for a multiple of P, we cannot 
    make sure that all the inverted elements are a multiple of n. So, a storing of the visited elements in a data 
    structure is required. We are going to use a data structure with O(1) accessing time, the remainder array.

    Idea: before the merging, first iterate through the left and right array. If the rightarr[j] < leftarr[i],
    we will "record" the remainder of the smaller element divided by P, rightarr[j] % P into the remainder array. If rightarr[j] is 
    a multiple of P, we will add P instead. If leftarr[i] < rightarr[j], we will find the complement of leftarr[i] % P which adds up to P,
    and add the value of the complement to the inversions. An example is shown below.

    example problem:
    leftarr = [4,5,6,7], rightarr = [1,2,3,9], and find inversions that sum to a multiple of 2.

    [4,5,6,7]  [1,2,3,9]  [1, 0]
    ^           ^
    after first iteration,
    [4,5,6,7]  [1,2,3,9]  [1, 1]
     ^            ^
    [4,5,6,7] [1,2,3,9]  [2, 1]
     ^             ^  
    [4,5,6,7] [1,2,3,9] [2, 1]
     ^               ^
    [4,5,6,7] [1,2,3,9] [2, 1]   inv = 1
       ^             ^
    [4,5,6,7] [1,2,3,9] [2, 1]   inv = 1 + 2 = 3
         ^           ^
    [4,5,6,7] [1,2,3,9] [2, 1]   inv = 3 + 1 = 4
           ^         ^
    [4,5,6,7] [1,2,3,9] [2, 1]   inv = 4 + 2 = 6 //done. 

    Time complexity: 2T(n/2) + O(n) = ⍬(n lg n)
*/

import java.util.*;

class CountInversions {

    // A list of added remainders to add into the remainder array
    static List<Integer> added = new ArrayList<Integer>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int P = sc.nextInt();
        int arr[] = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }		
        int[] remainders = new int[P + 1]; // an array of 0s, tracking the remainder frequencies. [0, 0, ..., 0] at each initialization
		int answer = mergesort(arr, P, remainders);
        //System.out.println(Arrays.toString(arr));
        System.out.println(answer);
    }

    static int mergesort(int[] arr, int key, int[] remainders) {
        if (arr.length <= 1) {
            return 0;
        }
        //Arrays.fill(remainders, 0);
        int inv = 0;
        int[] leftArr = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] rightArr = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        inv += mergesort(leftArr, key, remainders); 
        // need to clear all the counted remainders in the remainder array, or else will get double counted. 
        // need to clear after each merge function.
        clearArr(remainders, added);
        added.clear();
        inv += mergesort(rightArr, key, remainders);
        clearArr(remainders, added);
        added.clear();
        return inv + merge(arr, leftArr, rightArr, key, remainders);
    }

    static void clearArr(int[] remainders, List<Integer> added) {
        for (int i : added) {
            remainders[i] = 0;
        }
    }

    static int merge(int[] arr, int[] leftArr, int[] rightArr, int key, int[] remainders) {
        //Arrays.fill(remainders, 0); this one will lead to TLE 
        int i = 0;
        int j = 0;
        int k = 0;
        int inv = 0;
        while ((i < leftArr.length) && (j < rightArr.length)) {
            if (leftArr[i] > rightArr[j]) {
                int remainder = rightArr[j] % key;
                remainder = remainder == 0 ? key : remainder; //a multiple of key, add key instead. 
                remainders[remainder]++;
                added.add(remainder);
                j++;
            } else {
                // find the complement of the larger element in leftarray module the key. If they both adds up to 
                // the key, we have an inversion that is a multiple of the key.
                int complement = leftArr[i] % key;
                inv += remainders[key - complement];
                i++;
            }
        }
        // the remaining uncopied elements in the array. Since the remaining elements are definitely larger,
        // we only account for the larger elements in the left array. The right array, although larger,
        // is not an inversion.
        while (i < leftArr.length) {
            int complement = leftArr[i] % key;
            inv += remainders[key - complement];
            i++;
        }
        i = 0;
        j = 0;
        while ((i < leftArr.length) && (j < rightArr.length)) {
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
        //System.out.println(Arrays.toString(arr));
        //System.out.println(Arrays.toString(remainders));
        return inv;
    }
    
}
