/**
 * CS2040S Problem Set 3 - Speed Demon
 * 
 * Include the names of any collaborators here, and how they helped you.
 * Jel Lim - discussed about mapping characters to primes as the hashmap's key
 * 
 */

import java.util.Arrays;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MySpeedDemon {
    
    public static int speedMatch(String dbfilename) {
        try {
            FileReader fr = new FileReader(dbfilename);
            BufferedReader br = new BufferedReader(fr);
            int inputs = Integer.parseInt(br.readLine());
            int output = 0;
            HashMap<Long, Integer> hm = new HashMap<Long, Integer>();
            boolean[] bool = sieveOfErasthothenes();
            int[] primes = generatePrimes(bool);
            for (int i = 0; i < inputs; i++) {
                String line = br.readLine();
                long product = 1;
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    product *= primes[(int) c];
                }
                if (hm.containsKey(product)) {
                    hm.put(product, hm.get(product) + 1);
                } else {
                    hm.put(product, 1);
                }
            }
            for (long i : hm.keySet()) {
                int value = hm.get(i);
                if (value == 2) {
                    output += 1;
                } else if (value > 2) {
                    output += value * (value - 1) / 2;
                }
            }
            return output;
        } catch (IOException e) {
            System.out.println(e);
            return 0;
        }
    }

    public static boolean[] sieveOfErasthothenes() {
        boolean[] primes = new boolean[720 + 1];
        Arrays.fill(primes, true);
        primes[1] = false;
        for (int i = 2; i * i <= 720; i++) {
            if (primes[i]) {
                for (int j = 2 * i; j <= 720; j += i) {
                    primes[j] = false;
                }
            }
        }
        return primes;
    }

    public static int[] generatePrimes(boolean[] seived) {
        int[] primeNumbers = new int[128];
        int count = 0;
        int num = 1;
        while (count < 128) {
            if (seived[num]) {
                primeNumbers[count] = num;
                count++;
            }
            num++;
        }
        return primeNumbers;
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
