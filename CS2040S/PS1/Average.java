import java.util.*;

class Average {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int cases = kattio.getInt();
        for (int i = 0; i < cases; i++) {
            solve(kattio);
        }
        kattio.close();
    }

    static void solve(Kattio kattio) {
        int students = kattio.getInt();
        int[] arr = new int[students];
        for (int i = 0; i < students; i++) {
            arr[i] = kattio.getInt();
        }
        for (int i : arr) {
            kattio.println(i);
        }
    }
}