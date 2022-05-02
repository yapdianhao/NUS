import java.util.Arrays;

class Test {

    public static void main(String[] args) {
        boolean[] arr = sieveOfErasthothenes();
        int[] primes = generatePrimes(arr);
        System.out.println(Arrays.toString(primes));
    }

    public static boolean[] sieveOfErasthothenes() {
        boolean[] primes = new boolean[10000 + 1];
        Arrays.fill(primes, true);
        primes[1] = false;
        for (int i = 2; i * i <= 10000; i++) {
            if (primes[i]) {
                for (int j = 2 * i; j <= 10000; j += i) {
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
}