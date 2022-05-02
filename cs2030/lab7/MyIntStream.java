package cs2030.mystream;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.*;
import java.util.Set;
import java.util.LinkedHashSet;

public interface MyIntStream {
    ArrayList<Integer> stream = new ArrayList<Integer>();

    public static MyIntStream of(int... values) {
       MyIntStreamImpl intstream = new MyIntStreamImpl();
        for (int i : values) {
            intstream.stream.add(i);
        }
        return intstream;
    }

    public static MyIntStream range(int start, int end) {
        MyIntStreamImpl intstream = new MyIntStreamImpl();
        for (int i = start; i < end; i++) {
            intstream.stream.add(i);
        }
        return intstream;
    }

    public static MyIntStream rangeClosed(int start, int end) {
        MyIntStreamImpl intstream = new MyIntStreamImpl();
        for (int i = start; i < end + 1; i++) {
            intstream.stream.add(i);
        }
        return intstream;
    }
    
    public MyIntStream limit(int maxSize); 
    
    public MyIntStream distinct();
    
    public MyIntStream filter(IntPredicate predicate);

    public MyIntStream map(IntUnaryOperator mapper);

    public long count();
   
    public int sum();

    public OptionalDouble average();
   
    public OptionalInt max();

    public OptionalInt min();

    public void forEach(IntConsumer action);

    public int reduce(int identity, IntBinaryOperator op);

    public OptionalInt reduce(IntBinaryOperator op);
}
