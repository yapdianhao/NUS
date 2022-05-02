import java.util.*;

class Gnomes {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int total = kattio.getInt();
        int remaining = kattio.getInt();
        int[] remainingGnomes = new int[remaining];
        int[] missingGnomes = new int[total - remaining];
        boolean[] searched = new boolean[total + 1];
        boolean[] remainingPrinted = new boolean[remaining];
        for (int i = 0; i < remaining; i++) {
            int temp = kattio.getInt();
            remainingGnomes[i] = temp;
            searched[temp] = true;
            remainingPrinted[i] = false;
        }
        for (int i = 1, j = 0; i <= total; i++) {
            if (!searched[i]) {
                missingGnomes[j] = i;
                j++;
            }
        }
        if (total == remaining) {
            for (int i = 0; i < remainingGnomes.length; i++) {
                remainingPrinted[i] = true;
                kattio.println(remainingGnomes[i]);
            }
        } 
        int j = 0;
        for (int i : missingGnomes) {
            for (; j < remaining;) {
                if (i < remainingGnomes[j]) {
                    kattio.println(i);
                    searched[i] = true;
                    break;
                } else {
                    kattio.println(remainingGnomes[j]);
                    remainingPrinted[j] = true;
                    j++;
                }
            }
            if (!searched[i]) {
                kattio.println(i);
            }
        }
        for (int i = 0; i < remaining; i++) {
            if (!remainingPrinted[i]) {
                kattio.println(remainingGnomes[i]);
            }
        }
        kattio.close();
    }
}