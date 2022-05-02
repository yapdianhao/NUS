import cs2030.mystream.InfiniteList;
import cs2030.mystream.InfiniteListImpl;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
          System.out.println(InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).limit(10).toArray());
          System.out.println(InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 1).limit(10).count());
          System.out.println(InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 1).limit(10).count());
          System.out.println(InfiniteList.iterate(1, x -> x + 1).limit(10).filter(x -> x % 2 == 1).count());
          InfiniteList.iterate(1, x -> x + 1).limit(5).forEach(System.out::println);
          InfiniteList.generate(() -> "A").limit(5).forEach(System.out::println);
          InfiniteList.iterate(1, x -> x + 1).limit(5).takeWhile(x -> x < 3).forEach(System.out::println);
    }
}
