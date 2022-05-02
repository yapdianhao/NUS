import java.util.HashSet;

class Everywhere {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int cases = io.getInt();
        while (cases-- > 0) {
            solve(io);
        }
        io.close();
    }

    static void solve(Kattio io) {
        int strings = io.getInt();
        HashSet<String> hs = new HashSet<String>();
        for (int i = 0; i < strings; i++) {
            hs.add(io.getWord());
        }
        io.println(hs.size());
    }
}