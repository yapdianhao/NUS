import cs2030.mystream.MyIntStream;

class Main {
    public static void main(String[] args) {
        System.out.println(MyIntStream.of(1, 2, 3));
        System.out.println(MyIntStream.range(1, 10));
        System.out.println(MyIntStream.rangeClosed(1,10));
        System.out.println(MyIntStream.range(1, 1).count());
        System.out.println(MyIntStream.range(1, 10).count());
        System.out.println(MyIntStream.range(1, 10).max());
        System.out.println(MyIntStream.range(1, 1).max());
        System.out.println(MyIntStream.range(1, 1).min());
        System.out.println(MyIntStream.range(1, 1).average());
        System.out.println(MyIntStream.range(1, 10).limit(5));
        System.out.println(MyIntStream.range(1, 10).limit(0));
        System.out.println(MyIntStream.range(1, 9).distinct());
        System.out.println(MyIntStream.range(1, 10).filter(x -> x < 5));
        System.out.println(MyIntStream.range(1, 10).map(x -> x / 2));
        System.out.println(MyIntStream.range(1, 10).map(x -> x / 2).distinct());
        System.out.println(MyIntStream.range(1, 5).reduce(0, (x, y) -> x + y));
        System.out.println(MyIntStream.range(1, 1).reduce((x, y) -> x + y));
        System.out.println(MyIntStream.range(1, 10).reduce((x, y) -> x + y));
    }
}
