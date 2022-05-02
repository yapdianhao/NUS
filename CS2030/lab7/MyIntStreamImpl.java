package cs2030.mystream;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.*;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;

class MyIntStreamImpl implements MyIntStream{
    ArrayList<Integer> stream;

    public MyIntStreamImpl() {
        stream = new ArrayList<Integer>();
    }

    @Override
    public long count() {
        return stream.size();
    }

    public int sum() {
        int total = 0;
        for (int i : stream) {
            total += i;
        }
        return total;
    }

    @Override
    public OptionalInt max() {
        if (stream.size() == 0) {
            return OptionalInt.empty();
        } else {
            int max = stream.get(0);
            for (int i : stream) {
                if (i >= max) {
                    max = i;
                }
            }
            return OptionalInt.of(max);
        }
    }
    
    @Override
    public OptionalInt min() {
        if (stream.size() == 0) {
            return OptionalInt.empty();
        } else {
            int min = stream.get(0);
            for (int i : stream) {
                if (i <= min) {
                    min = i;
                }
            }
            return OptionalInt.of(min);
        }
    }

    @Override
    public OptionalDouble average() {
        if (stream.size() == 0) {
            return OptionalDouble.empty();
        } else {
            return OptionalDouble.of(sum() / count());
        }
    }

    public OptionalInt reduce(IntBinaryOperator op) {
        if (stream.size() == 0) {
            return OptionalInt.empty();
        } else {
            int start = 0;
            for (int i : stream) {
                start = op.applyAsInt(start, i);
            }
            return OptionalInt.of(start);

        }
    }

    public void forEach(IntConsumer action) {
        for (int i : stream) {
            action.accept(i);
        }
    }

    public int reduce(int identity, IntBinaryOperator op) {
        for(int i : stream) {
            identity = op.applyAsInt(identity, i);
        }
        return identity;
    }

    public MyIntStream limit(int maxSize) {
        MyIntStreamImpl intstream = new MyIntStreamImpl();
        int count = 0;
        while (count < maxSize) {
            intstream.stream.add(stream.get(count));
            count++;
        }
        return intstream;
    }

    public MyIntStream distinct() {
        MyIntStreamImpl intstream = new MyIntStreamImpl();
        Set<Integer> hashSet = new LinkedHashSet<Integer>();
        for (int i : stream) {
            hashSet.add(i);
        }
        for (int i : hashSet) {
            intstream.stream.add(i);
        }
        return intstream;
    }

    public MyIntStream filter(IntPredicate predicate) {
        MyIntStreamImpl intstream = new MyIntStreamImpl();
        for (int i : stream) {
            if (predicate.test(i)) {
                intstream.stream.add(i);
            }
        }
        return intstream;
    }

    public MyIntStream map(IntUnaryOperator mapper) {
        MyIntStreamImpl intstream = new MyIntStreamImpl();
        for (int i : stream) {
            intstream.stream.add(mapper.applyAsInt(i));
        }
        return intstream;
    }

    public String toString() {
        return stream.toString();
    }
}
