class Esej {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int min = io.getInt();
        int max = io.getInt();
        int med = Math.max(min, max / 2);
        HashSet<String> hs = new HashSet<String>();
        String s = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
        String toPrint = "";
        String[] alphabets = s.split(" ");
        int count = 0;
        for (String i : alphabets) {
            for (String j : alphabets) {
                for (String k : alphabets) {
                    toPrint += i + j + k + " ";
                    count++;
                    if (count == med) {
                        io.println(toPrint);
                        io.close();
                        return;
                    }
                }
            }
        }
        io.close();
    }
}