import java.util.TreeMap;
import java.util.Comparator;

class Kattis2 {

    public static void main(String[] args) {
        TreeMap<Quest, Integer> treeMap = new TreeMap<>(new Comparator<Quest>() {
            @Override
            public int compare(Quest quest1, Quest quest2) {
                if (quest1.energy > quest2.energy) {
                    return 1;
                } else if (quest1.energy < quest2.energy) {
                    return -1;
                } else {
                    if (quest1.gold > quest2.gold) {
                        return 1;
                    } else if (quest1.gold < quest2.gold) {
                        return -1;
                    } else {
                        return quest1.id - quest2.id;
                    }
                }
            }
        });
        Kattio kattio = new Kattio(System.in, System.out);
        int cmds = kattio.getInt();
        int id = 0;
        for (int i = 0; i < cmds; i++) {
            String action = kattio.getWord();
            if (action.equals("add")) {
                id++;
                Quest quest = new Quest(kattio.getInt(), kattio.getInt(), id);
                treeMap.put(quest, quest.gold);
            } else {
                int energy = kattio.getInt();
                int gold = 0;
                Quest temp = new Quest(energy, 100000, 100000);
                while (treeMap.floorKey(temp) != null && energy - treeMap.floorKey(temp).energy >= 0) {
                    energy -= treeMap.floorKey(temp).energy;
                    gold += treeMap.remove(treeMap.floorKey(temp));
                }
                kattio.println(gold);
                kattio.flush();
            }
            //kattio.println(treeMap);
        }
        kattio.flush();
    }
}
