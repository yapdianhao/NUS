package cs2030.mystream;

import cs2030.mystream.InfiniteListImpl;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.BiFunction;

public interface InfiniteList<T> {
    static <T> InfiniteList<T> generate(Supplier<T> supplier) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                Optional<T> var = Optional.of(supplier.get());
                return var;
            }
        };
    }
    
    static <T> InfiniteList<T> iterate(T seed, Function<T, T> next) {
        return new InfiniteListImpl<T>() {
            T element = seed;
            Function<T, T> func = x -> {
                func = next;
                return element;
            };
            public Optional<T> get() {
                element = func.apply(element);
                return Optional.of(element);
            }
        };
    }

    public <R> InfiniteList<R> map(Function<T, R> mapper);

    public InfiniteList<T> limit(long maxSize);

    public InfiniteList<T> filter(Predicate<T> predicate);

    public InfiniteList<T> takeWhile(Predicate<T> predicate);

    public void forEach(Consumer<T> action);

    public long count();

    public Optional<T> reduce(BiFunction<T, T, T> accumulator);

    public T reduce(T identity, BiFunction<T, T, T> accumulator);

    public Object[] toArray();

}
