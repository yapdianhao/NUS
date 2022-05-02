import java.util.stream.IntStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalDouble;

class Main {
    static boolean isPerfect(int n) {
        return IntStream.rangeClosed(1, n-1)
            .filter(x -> n%x == 0)
            .sum() == n;
    }

    static boolean isSquare(int n) {
        return IntStream.rangeClosed(0, n)
            .filter(x -> x*x == n)
            .count() > 0;
    }

    static int reverse(int n){
        if (n == 0) return 0;
        return IntStream
            .iterate(n, x -> x > 0, x -> x /= 10)
            .map(x -> x%10)
            .reduce(0, (a,b) -> a * 10 + b);
    }

    static long countRepeats(int[] array) {
        return IntStream.range(0, array.length - 1)
            .filter(x -> array[x] == array[x+1] && 
                    (x == array.length - 2 || array[x] != array[x + 2]))
            .count();
    }

    static OptionalDouble variance(int[] array) {
        if (array.length < 2) return OptionalDouble.empty();
        double mu = ((double)Arrays.stream(array).sum()) / array.length;
        return OptionalDouble.of(Arrays.stream(array)
            .mapToDouble(x -> (x - mu) * (x - mu) / (array.length - 1))
            .sum());
    }

    static IntStream factors(int n) {
        return IntStream.rangeClosed(1, n)
            .filter(x -> n%x == 0);
    }

    static boolean isPrime(int n) {
        return factors(n).count() == 2;
    }

    static IntStream primeFactors(int n) {
        return factors(n)
            .filter(x -> isPrime(x));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(isSquare(n));
    }
}
