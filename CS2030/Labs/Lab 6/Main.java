import cs2030.mystream.MyIntStream;

public class Main {
    public static void main(String[] args) {
        MyIntStream stream = MyIntStream.of(1,2,3,4,5,5,5,5,4,3,2,1,1);
        System.out.println(stream.distinct());
        System.out.println(stream.reduce(0, (x,y) -> x+y));
        stream.forEach(System.out::println);
    }
}
