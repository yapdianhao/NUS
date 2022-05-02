import java.util.*;

class SortString implements Comparator<String> {

    public int compare(String a, String b) {
        return a.substring(0, 2).compareTo(b.substring(0, 2));
    }
}