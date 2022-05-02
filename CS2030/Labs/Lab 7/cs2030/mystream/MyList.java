package cs2030.mystream;

import java.util.function.Supplier;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

class MyList<T> implements InfiniteList<T> {
    private Supplier<? extends T> headSupplier;
    private Supplier<MyList<T>> tailSupplier;
    private boolean isEmpty;
    private Supplier<MyList<T>> lazySupplier;

    private MyList(Supplier<? extends T> headSupplier, Supplier<MyList<T>> tailSupplier) {
        this.headSupplier = headSupplier;
        this.tailSupplier = tailSupplier;
        this.isEmpty = false;
    }

    private MyList(Supplier<MyList<T>> lazySupplier) {
        this.isEmpty = false;
        this.lazySupplier = lazySupplier;
    }

    private MyList() {
        this.isEmpty = true;
    }

    // propagates the lazySupplier and stops when a head is found
    private void propagateLazy() {
        if (lazySupplier != null) {
            MyList<T> newList = lazySupplier.get();
            newList.propagateLazy();
            headSupplier = newList.headSupplier;
            tailSupplier = newList.tailSupplier;
            isEmpty = newList.isEmpty;
            lazySupplier = null;
        }
    }

    public static <T> MyList<T> generate(Supplier<? extends T> supplier) {
        return new MyList<T>(supplier, () -> MyList.generate(supplier));
    }

    public static <T> MyList<T> iterate(T seed, UnaryOperator<T> next) {
        return new MyList<T>(() -> seed, () -> iterate(next.apply(seed), next));
    }

    @Override
    public MyList<T> limit(long maxSize) {
        if (maxSize == 0) {
            return new MyList<T>();
        }
        if (lazySupplier != null) {
            return new MyList<T>(() -> lazySupplier.get().limit(maxSize));
        }
        if (isEmpty) {
            return new MyList<T>();
        }
        if (maxSize == 1) {
            return new MyList<T>(headSupplier,
                () -> new MyList<T>());
        }
        return new MyList<T>(headSupplier,
            () -> this.tailSupplier.get().limit(maxSize - 1));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        propagateLazy();
        if (isEmpty) {
            return;
        }
        T head = headSupplier.get();
        action.accept(head);
        tailSupplier.get().forEach(action);
    }

    @Override
    public long count() {
        propagateLazy();
        if (isEmpty) {
            return 0;
        }
        return 1 + tailSupplier.get().count();
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        propagateLazy();
        if (isEmpty) {
            return identity;
        }
        T head = headSupplier.get();
        return tailSupplier.get().reduce(accumulator.apply(identity, head), accumulator);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        propagateLazy();
        if (isEmpty) {
            return Optional.empty();
        }
        T head = headSupplier.get();
        return Optional.of(tailSupplier.get().reduce(head, accumulator));
    }

    private List<T> addToList(List<T> list) {
        propagateLazy();
        if (isEmpty) {
            return list;
        }
        T head = headSupplier.get();
        list.add(head);
        return tailSupplier.get().addToList(list);
    }

    @Override
    public Object[] toArray() {
        List<T> list = new ArrayList<>();
        return this.addToList(list).toArray();
    }

    @Override
    public MyList<T> filter(Predicate<? super T> predicate) {
        if (isEmpty) {
            return new MyList<T>();
        }
        if (lazySupplier != null) {
            return new MyList<T>(() -> lazySupplier.get().filter(predicate));
        }
        return new MyList<T>(() -> {
            T head = headSupplier.get();
            if (predicate.test(head)) {
                return new MyList<T>(() -> head, () -> tailSupplier.get().filter(predicate));
            }
            return tailSupplier.get().filter(predicate);
        });
    }

    @Override
    public <R> MyList<R> map(Function<? super T, ? extends R> mapper) {
        if (isEmpty) {
            return new MyList<R>();
        }
        if (lazySupplier != null) {
            return new MyList<R>(() -> lazySupplier.get().map(mapper));
        }
        return new MyList<R>(() -> {
            T head = headSupplier.get();
            return new MyList<R>(() -> mapper.apply(head), 
                () -> tailSupplier.get().map(mapper));
        });
    }

    @Override
    public MyList<T> takeWhile(Predicate<? super T> predicate) {
        if (isEmpty) {
            return new MyList<T>();
        }
        if (lazySupplier != null) {
            return new MyList<T>(() -> lazySupplier.get().takeWhile(predicate));
        }
        return new MyList<T>(() -> {
            T head = headSupplier.get();
            if (predicate.test(head)) {
                return new MyList<T>(() -> head, 
                    () -> tailSupplier.get().takeWhile(predicate));
            }
            return new MyList<T>();
        });
    }
}
