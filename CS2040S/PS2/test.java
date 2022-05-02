import java.util.Comparator;
import java.util.TreeMap;

class Test {

    public static void main(String[] args) {
        TreeMap<Integer,String> treeMap = new TreeMap<Integer, String>();
        //treeMap.put(1, "one");
        treeMap.put(9, "nine");
        treeMap.put(4, "four");
        treeMap.put(2, "two");
        System.out.println(treeMap.remove(treeMap.floorKey(1)));
        //System.out.println(treeMap.floorKey("z"));
        System.out.println(treeMap);

    }
}