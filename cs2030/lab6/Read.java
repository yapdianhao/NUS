import java.util.Scanner;
import java.util.ArrayList;

class Read{
    public static int[]  readInput() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Scanner scn = new Scanner(System.in);
        while (scn.hasNext()) {
            numbers.add(scn.nextInt());
        }
        int[] array = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            array[i] = numbers.get(i);
        }
        return array;
    }
}
