package cs2030.mystream;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.IntBinaryOperator;
import cs2030.mystream.IntStreamImple;

public interface MyIntStream {
    public static MyIntStream of(int... values) {
        return new IntStreamImple(values);
    }

    /**
     * Returns a MyIntStream containing numbers in [start, end).
     * @param start starting number
     * @param end ending number
     */
    public static MyIntStream range(int start, int end) {
        int[] values = new int[end - start];
        for (int i = start; i < end; i++) {
            values[i - start] = i;
        }
        return new IntStreamImple(values);
    }

    /**
     * Returns a MyIntStream containing numbers in [start, end].
     * @param start starting number
     * @param end ending number
     */
    public static MyIntStream rangeClosed(int start, int end) {
        int[] values = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            values[i - start] = i;
        }
        return new IntStreamImple(values);
    }

    public int count();

    public int sum();

    public OptionalDouble average();

    public OptionalInt min();

    public OptionalInt max();

    public void forEach(IntConsumer action);

    public MyIntStream limit(int maxSize);

    public MyIntStream distinct();

    public MyIntStream filter(IntPredicate predicate);

    public MyIntStream map(IntUnaryOperator mapper);

    public int reduce(int identity, IntBinaryOperator op);

    public OptionalInt reduce(IntBinaryOperator op);
}
