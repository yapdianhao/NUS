
/**
 * CS2040S Problem Set 3 - Speed Demon
 * 
 * Include the names of any collaborators here, and how they helped you.
 * Example: Tan Ah Kow - Discussed what Hash functions to use for the Problem
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class MySpeedDemon2 {
    private static int arr[];
    private static List<String> ls = new LinkedList<>();

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

    public static int speedMatch(String dbfilename) {
        /*
         * idea: count ascii for all strings - O(n*m) store value in arr/arrlist compare
         * values - O(n^2) (might have collision)
         * 
         * use xor of 2 strings - repeat for all strings - O(n^2) (might have collision
         * also)
         * 
         * ascii + xor ?
         */
        int n = 0;

        try {
            FileReader dataFile = new FileReader(dbfilename);
            BufferedReader bufferedDataFile = new BufferedReader(dataFile);
            String line = bufferedDataFile.readLine();
            n = Integer.valueOf(line);

            arr = new int[n];
            Arrays.fill(arr, 1);

            for (int i = 0; i < n; i++) { // O(n)
                ls.add(bufferedDataFile.readLine());
            }

            bufferedDataFile.close();
        } catch (Exception e) {
            System.out.println(e);
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
        return totalAnagrams;
    }

    public static void main(String args[]) {
        if (args != null && args.length > 0) {
            int result = MySpeedDemon.speedMatch(args[0]);
            System.out.println(result);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
