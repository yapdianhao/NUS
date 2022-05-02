import java.util.*;

class Guessing {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int min = 1;
        int max = 10;
        while (kattio.hasMoreTokens()) {
            int input = kattio.getInt();
            if (input == 0) {
                kattio.close();
                break;
            }
            String statement = kattio.getWord() + " " + kattio.getWord();
            if (statement.equals("too low")) {
                min = Math.max(min, input + 1);
            } else if (statement.equals("too high")){
                max = Math.min(max, input - 1);
            } else if (statement.equals("right on")) {
                if (min <= input && input <= max) {
                    kattio.println("Stan may be honest");
                    min = 1;
                    max = 10;
                } else {
                    kattio.println("Stan is dishonest");
                    min = 1;
                    max = 10;
                }
            }
        }
    }
}