package cs2030.mystream;

import java.util.List;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.Set;
import java.util.HashSet;
import cs2030.mystream.MyIntStream;

public class IntStreamImple implements MyIntStream {
    List<Integer> list = new ArrayList<>();

    protected IntStreamImple() {
    
    }

    protected IntStreamImple(int... values) {
        for (int i = 0; i < values.length; i++) {
            this.list.add(values[i]);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i : list) {
            str += i + " ";
        }
        return str;
    }

    @Override
    public int count() {
        return list.size();
    }

    @Override
    public int sum() {
        int s = 0;
        for (int i : list) {
            s += i;
        }
        return s;
    }

    @Override
    public OptionalDouble average() {
        if (list.isEmpty()) {
            return OptionalDouble.empty();
        }
        return OptionalDouble.of((double) sum() / count());
    }

    @Override
    public OptionalInt min() {
        if (list.isEmpty()) {
            return OptionalInt.empty();
        }
        int mini = list.get(0);
        for (int i : list) {
            if (i < mini) {
                mini = i;
            }
        }
        return OptionalInt.of(mini);
    }

    @Override
    public OptionalInt max() {
        if (list.isEmpty()) {
            return OptionalInt.empty();
        }
        int maxi = list.get(0);
        for (int i : list) {
            if (i > maxi) {
                maxi = i;
            }
        }
        return OptionalInt.of(maxi);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int i : list) {
            action.accept(i);
        }
    }

    @Override
    public MyIntStream limit(int maxSize) {
        IntStreamImple stream = new IntStreamImple();
        for (int i = 0; i < list.size() && i < maxSize; i++) {
            stream.list.add(this.list.get(i));
        }
        return stream;
    }

    @Override
    public MyIntStream distinct() {
        IntStreamImple stream = new IntStreamImple();
        Set<Integer> set = new HashSet<Integer>();
        for (int i : this.list) {
            if (!set.contains(i)) {
                set.add(i);
                stream.list.add(i);
            }
        }
        return stream;
    }

    @Override
    public MyIntStream filter(IntPredicate predicate) {
        IntStreamImple stream = new IntStreamImple();
        for (int i : this.list) {
            if (predicate.test(i)) {
                stream.list.add(i);
            }
        }
        return stream;
    }

    @Override
    public MyIntStream map(IntUnaryOperator mapper) {
        IntStreamImple stream = new IntStreamImple();
        for (int i : this.list) {
            stream.list.add(mapper.applyAsInt(i));
        }
        return stream;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int i : list) {
            result = op.applyAsInt(result, i);
        }
        return result;
    }

    @Override
    public OptionalInt reduce(IntBinaryOperator op) {
        int result = 0;
        boolean foundAny = false;
        for (int i : list) {
            if (!foundAny) {
                foundAny = true;
                result = i;
            } else {
                result = op.applyAsInt(result, i);
            }
           
        }
        return foundAny ? OptionalInt.of(result) : OptionalInt.empty();
    }
}
