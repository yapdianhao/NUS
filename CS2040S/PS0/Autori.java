import java.util.*; 

class Autori{

    public static void main(String[] args) {
        Scanner scn = new Scanner (System.in);
        String s = scn.next();
        String toPrint = "";
        for (int i = 0; i < s.length() - 1; i ++) {
            if (i == 0) {
                toPrint += s.substring(0,1);
            } else if (s.substring(i, i + 1).equals("-")) {
                toPrint += s.substring(i + 1, i + 2);
            }
        }
        System.out.println(toPrint);
    }
}