import java.util.*;

class Gnome {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int total = kattio.getInt();
        int remaining = kattio.getInt();
        boolean[] searched = new boolean[total];
        int[] gnomes = new int[remaining];
        for (int i = 0; i < total; i++) {
            searched[i] = false;
        }
        for (int i = 0; i < remaining; i++) {
            int temp = kattio.getInt();
            gnomes[i] = temp;
            searched[temp] = true;
        }
        int max = getMax(gnomes);
        for (boolean b : searched) {
            kattio.println(b);
        }

        kattio.close();
    }

    static int getMax(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}