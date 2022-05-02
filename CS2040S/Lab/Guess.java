import java.util.*;

class Guess {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        //String res = kattio.getWord();
        int up = 1000;
        int low = 1;
        while (true) {
            int randint = (up + low) / 2;
            kattio.println(randint);
            kattio.flush();
            String res = kattio.getWord();
            //String res = kattio.getWord();
            if (res.equals("higher")) {
                low = randint + 1;
            } else if (res.equals("lower")) {
                up = randint - 1;
            } else {
                break;
            }
        }
    }
}