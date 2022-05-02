import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        String[] strings = new String[]{"one","two three"};
        String[] shortString = new String[] {"According to a research   at Cambridge  University, it doesn't matter in"};
        String[] longString = new String[] {"According   ", "to", "   a", "research","at","Cambridge  ", "University"};
        List<String> shortList = Arrays.asList(shortString);
        List<String> list = Arrays.asList(strings);
        List<String> longList = Arrays.asList(longString);
        /*System.out.println(Parser.parse(list));;
        System.out.println(Parser.parse(list).wordcount());
        System.out.println(Parser.parse(list).linecount().wordcount());
        System.out.println(Parser.parse(list).wordcount().linecount());
        System.out.println(Parser.parse(list).grab("e").wordcount());
        System.out.println(Parser.parse(list).grab("ee").wordcount());
        System.out.println(Parser.parse(list).grab("z").wordcount());
        System.out.println(Parser.parse(list).chop(2,5));
        System.out.println(Parser.parse(list).chop(2,2));
        System.out.println(Parser.parse(list).grab("e").chop(0,10));
        System.out.println(Parser.parse(list).grab("e").echo().chop(0,10))*/;
        System.out.println(Parser.parse(list).chop(30,200));
        System.out.println(Parser.parse(list).chop(30,200).linecount());
        System.out.println(Parser.parse(list).chop(30,200).wordcount());
        /*System.out.println(Parser.parse(shortList).echo());
        System.out.println(Parser.parse(longList).echo());*/
        System.out.println(Parser.parse(shortList).shuffle());
        System.out.println(Parser.parse(list).grab("z").linecount().grab("0").linecount().linecount().
                linecount().wordcount().grab("0").linecount());
    }

}
