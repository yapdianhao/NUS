import java.util.HashSet;

class Nodup {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        HashSet<String> hs = new HashSet<String>();
        while (io.hasMoreTokens()) {
            String s = io.getWord();
            if (hs.contains(s)) {
                io.println("no");
                io.flush();
                return;
            }
            hs.add(s);
        }
        io.println("yes");
        io.close();
    }
}