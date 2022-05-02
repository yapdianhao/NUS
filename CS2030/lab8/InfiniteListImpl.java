package cs2030.mystream;

import cs2030.mystream.InfiniteList;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.BiFunction;
import java.util.ArrayList;

public abstract class InfiniteListImpl<T> implements InfiniteList<T> {

    public abstract Optional<T> get();
    
    public <R> InfiniteList<R> map(Function<T,R> mapper) {
        return new InfiniteListImpl<R>() {
            public Optional<R> get() {
                return InfiniteListImpl.this.get().map(mapper);
            }
        };
    }

    public InfiniteList<T> limit(long maxSize) {
        return new InfiniteListImpl<T>() {
            long remaining = maxSize;
            public Optional<T> get() {
                if (remaining == 0) {
                    return Optional.empty();
                } else {
                    --remaining;
                    return InfiniteListImpl.this.get();
                }
            }
        };
    }

    public InfiniteList<T> filter(Predicate<T> predicate) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                Optional<T> var = InfiniteListImpl.this.get();
                if (var.equals(Optional.empty())) {
                   return var;
                } else if (var.filter(predicate).equals(Optional.empty())) {
                   return get();
                } else {
                   return var;
                }
            }
        };
    }


    public InfiniteList<T> takeWhile(Predicate<T> predicate) {
        return new InfiniteListImpl<T>() {
            boolean flag = true;
            public Optional<T> get() {
                Optional<T> var = InfiniteListImpl.this.get();
                if (flag) {
                    if (predicate.test(var.get())) {
                        return var;
                    } else {
                        flag = false;
                        return Optional.empty();
                    }
                } else {
                    return Optional.empty();
                }
            }
        };
    }

    public void forEach(Consumer<T> action) {
        Optional<T> curr;
        boolean flag = true;
        while (flag) {
            curr = get();
            if (!curr.equals(Optional.empty())) {
                action.accept(curr.get());
            } else {
                flag = false;
            }
        }
    }

    public long count() {
        long count = 0;
        Optional<T> curr;
        boolean flag = true;
        while (flag) {
            curr = get();
            if (!curr.equals(Optional.empty())) {
                //System.out.println(curr.get());
                count++;
            } else {
                flag = false;
            }
        }
        return count;
    }

    public Optional<T> reduce(BiFunction<T, T, T> accumulator) {
        Optional<T> firstArgument = InfiniteListImpl.this.get();
        Optional<T> secondArgument = InfiniteListImpl.this.get();
        if (firstArgument.equals(Optional.empty())) {
            return Optional.empty();
        } else if (secondArgument.equals(Optional.empty())) {
            return firstArgument;
        } else {
            while (!secondArgument.equals(Optional.empty())) {
                firstArgument = Optional.of(accumulator.apply(firstArgument.get(), secondArgument.get()));
                secondArgument = InfiniteListImpl.this.get();
            }
            return firstArgument;
        }
    }

    public T reduce(T identity, BiFunction<T, T, T> accumulator) {
        Optional<T> firstArgument = InfiniteListImpl.this.get();
        Optional<T> secondArgument = InfiniteListImpl.this.get();
        if (firstArgument.equals(Optional.empty())) {
            return identity;
        } else if (secondArgument.equals(Optional.empty())) {
            T finalArgument = accumulator.apply(firstArgument.get(), identity);
            return finalArgument;
        } else {
            T finalArgument = accumulator.apply(firstArgument.get(), identity);
            while (!secondArgument.equals(Optional.empty())) {
                finalArgument = accumulator.apply(finalArgument, secondArgument.get());
                secondArgument = InfiniteListImpl.this.get();
            }
            return finalArgument;
        }
    }

    public Object[] toArray() {
        ArrayList<Object> objects = new ArrayList<Object>();
        Optional<T> object = InfiniteListImpl.this.get();
        while (!object.equals(Optional.empty())) {
            objects.add(object.get());
            object = InfiniteListImpl.this.get();
        }
        return objects.toArray();
    }

}
