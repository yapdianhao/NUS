import java.util.Hashtable;

class Test {
    
    public static void main(String[] args ) {
        Hashtable<Pair, Integer> h = new Hashtable<Pair, Integer>();
        Pair p = new Pair(5, 6);
        h.put(p, 10);
        System.out.println(h.get(p));
        System.out.println(h.keySet());
        System.out.println(h.containsKey(new Pair(5, 6)));

    }

    static class Pair {

        int x;
        int y;

        public Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "" + x + " " + y;
        }
    }
}