package cs2030.mystream;

import java.util.function.Supplier;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.Optional;

public interface InfiniteList<T> {
    public static <T> InfiniteList<T> generate(Supplier<? extends T> supplier) {
        return MyList.generate(supplier);
    }

    public static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> next) {
        return MyList.iterate(seed, next);
    }

    public InfiniteList<T> limit(long maxSize);

    public void forEach(Consumer<? super T> action);

    public long count();

    public T reduce(T identity, BinaryOperator<T> accumulator);

    public Optional<T> reduce(BinaryOperator<T> accumulator);

    public Object[] toArray();

    public InfiniteList<T> filter(Predicate<? super T> predicate);

    public <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper);

    public InfiniteList<T> takeWhile(Predicate<? super T> predicate);
}
