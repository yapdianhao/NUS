import java.util.stream.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.Arrays;

class Main {
    static boolean isSquare(int n) {
        int result = IntStream.rangeClosed(0, n)
                     .filter(x -> x * x == n)
                     .sum();
        return (result * result == n);
    }

    public static long countRepeats(int[] array) {
        return IntStream.range(1, array.length)
               .filter(i -> i - 2 < 0 || array[i - 2] != array[i])
               .filter(i -> array[i] == array[i - 1])
               .count();
    }

    public static boolean isPrime(int i) {
        return IntStream.range(2, i)
               .filter(x -> i % x == 0) 
               .count() == 0;
    }

    public static IntStream factors(int x) {
        return IntStream.rangeClosed(1, x)
               .filter(i -> x % i == 0);
    }

    public static OptionalDouble variance(int[] array) {
        if (array.length < 2) {
            return OptionalDouble.empty();
        } else {
            IntStream intstream = Arrays.stream(array);
            double sum = intstream.sum();
            //double mean = intstream.sum() / array.length;
            double mean = sum / array.length;
            IntStream myIntStream = Arrays.stream(array);
            double variance = (myIntStream.mapToDouble(x -> x)
                                          .map(x -> (x - mean) * (x - mean))
                                          .sum()) / (array.length - 1);
            return OptionalDouble.of(variance);
        }
    }

    public static IntStream primeFactors(int x) {
        return IntStream.rangeClosed(2, x)
               .filter(i -> x % i == 0)
               .filter(i -> isPrime(i));
               //.forEach(System.out::println);
    }

    public static void printStream(IntStream intStream) {
        intStream.forEach(x -> System.out.print(" " + x));
    }

    public static void main(String[] args) {
      /* ArrayList<Integer> numbers = new ArrayList<Integer>();
        Scanner scn = new Scanner(System.in);
            numbers.add(scn.nextInt());
        }
        IntStream streamOfNumbers = numbers.stream().mapToInt(Integer::intValue);
        int[] array = streamOfNumbers.toArray();
        System.out.println(countRepeats(array));
        */
        int[] array = Read.readInput();
        //System.out.println(variance(IntStream.rangeClosed(1,6).toArray()));
        System.out.println(variance(array));
    }
}    
