import java.util.*;

class Sorting {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        String input = kattio.getWord();
        while (!input.equals("0")) {
            int length = Integer.parseInt(input);
            String[] array = new String[length];
            for (int i = 0; i < length; i++) {
                array[i] = kattio.getWord();
            }
            List<String> list = Arrays.asList(array);
            Collections.sort(list, new SortString());
            for (String s : list) {
                kattio.println(s);
            }
            input = kattio.getWord();
            kattio.println();
        }
        kattio.close();
    }
}