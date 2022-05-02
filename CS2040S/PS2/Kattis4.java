import java.util.TreeSet;

class Kattis4 {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        TreeSet<Quest> ts = new TreeSet<Quest>();
        int cmds = kattio.getInt();
        int id = 0;
        for (int i = 0; i < cmds; i++) {
            String word = kattio.getWord();
            if (word.equals("add")) {
                int energy = kattio.getInt();
                int gold = kattio.getInt();
                Quest quest = new Quest(id++, energy, gold);
                ts.add(quest);
            } else {
                int energy = kattio.getInt();
                long gold = 0;
                Quest quest = new Quest(id, energy, 100000);
                while (!ts.isEmpty() && ts.floor(quest) != null) {
                    Quest largest = ts.floor(quest);
                    quest.energy -= largest.energy;
                    gold += (int)largest.gold;
                    ts.remove(largest);
                }
                kattio.println(gold);
            }
            //kattio.println(ts);
        }
        kattio.close();
    }

    static class Quest implements Comparable<Quest> {

        int energy;
        int id;
        int gold;

        public Quest(int id, int energy, int gold) {
            this.id = id;
            this.energy = energy;
            this.gold = gold;
        }

        @Override
        public int compareTo(Quest other) {
            if (energy != other.energy) {
                return (energy - other.energy);
            }
            return gold != other.gold ? gold - other.gold : id - other.id;
        }

        @Override
        public String toString() {
            return "" + energy;
        }
    }

}