import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Test2 {
    static int arr[];
    static List<String> ls = new LinkedList<>();

    public static int getXor(String s1, String s2) {
        int val = 0;
        for (int i = 0; i < s1.length(); i++) {
            val = val ^ s1.charAt(i);
            val = val ^ s2.charAt(i);
        }
        return val;
    }

    public static int calcAscii(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += s.charAt(i);
        }
        return res;
    }

    public static boolean areAnagrams(String s1, String s2) {
        int len = s1.length();
        int ascii;
        int val;

        if (len != s2.length()) {
            return false;
        } else {
            ascii = calcAscii(s1) - calcAscii(s2);
            val = getXor(s1, s2);
            return ascii == 0 && val == 0;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        arr = new int[n];
        Arrays.fill(arr, 1);

        for (int i = 0; i < n; i++) {
            ls.add(sc.next());
        }

        for (int j = 0; j < n - 1; j++) { // O(n)
            for (int k = j + 1; k < ls.size(); k++) {
                String item = ls.get(j);
                if (areAnagrams(item, ls.get(k))) {
                    ls.remove(k);
                    k--;
                    arr[j]++;
                }
            }
        }

        int totalAnagrams = 0;

        for (int i : arr) {
            if (i != 1) {
                totalAnagrams += i * (i - 1) / 2;
            }
        }

        System.out.println(totalAnagrams);
    }
}