import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

class Greeting2 {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int points = io.getInt();
        List<Pair> lst = new ArrayList<Pair>();
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < points; i++) {
            Pair pair = new Pair(io.getInt(), io.getInt());
            lst.add(pair);
            set.add("" + pair.x + pair.y);
        }
        int count = 0;
        for (Pair pair : lst) {
            List<String> temp = new ArrayList<String>();
            temp.add((pair.x + 0) + "" +  (pair.y + 2018));
            temp.add((pair.x + 2018) + "" + (pair.y + 0));
            temp.add((pair.x - 2018) + "" +  (pair.y + 0));
            temp.add((pair.x + 0) + "" + (pair.y - 2018));
            temp.add((pair.x + 1118) + "" +  (pair.y + 1680));
            temp.add((pair.x + 1680) + "" + (pair.y + 1118));
            temp.add((pair.x + 1118) + "" + (pair.y - 1680));
            temp.add((pair.x + 1680) + "" + (pair.y - 1118));
            temp.add((pair.x - 1118) + "" + (pair.y - 1680));
            temp.add((pair.x - 1680) + "" + (pair.y - 1118));
            temp.add((pair.x - 1118) + "" + (pair.y + 1680));
            temp.add((pair.x - 1680) + "" + (pair.y + 1118));
            //io.println(temp);
            for (String neighbours: temp) {
                if (set.contains(neighbours)) {
                    //System.out.println(true);
                    count++;
                }
            }
            set.remove(pair);
        }
        //io.println(set);
        io.println(count / 2);
        io.close();
    }

    static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}