import java.util.Hashtable;

class Hardwood {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
        String s = io.getLine();
        while (!s.equals("")) {
            if (!ht.containsKey(s)) {
                ht.put(s, 0);
            } else {
                ht.put(s, ht.get(s) + 1);
            }
            s = io.getLine();
        }
        io.println(ht);
        io.close();
    }
}