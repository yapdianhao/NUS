import java.util.TreeMap;
import java.util.Collections;
import java.util.PriorityQueue;

class Kattis3 {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int cmds = kattio.getInt();
        TreeMap<Integer, PriorityQueue<Integer>> treeMap = new TreeMap<Integer, PriorityQueue<Integer>>();
        for (int i = 0; i < cmds; i++) {
            // scan the first word of each line..
            String word = kattio.getWord();
            if (word.equals("add")) {
                int energy = kattio.getInt();
                //key not in map, create a pq, store as key value, put both in map.
                if (treeMap.get(energy) == null) {
                    PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
                    queue.add(kattio.getInt());
                    treeMap.put(energy, queue);
                } else {
                    //key already in map, same energy but diff gold.
                    //push gold to the queue which share the same energy as key.
                    treeMap.get(energy).add(kattio.getInt());
                }
            } else {
                int energy = kattio.getInt();
                long gold = 0;
                // if empty tree or no lower value than given query, dont do it
                while (!treeMap.isEmpty() && treeMap.floorKey(energy) != null) {
                    int key = treeMap.floorKey(energy);
                    //access the pq associated to the highest lower energy value
                    PriorityQueue<Integer> temp = treeMap.get(key);
                    //get the money
                    gold += (long) temp.poll();
                    //deducts the energy by the highest lower energy value
                    energy -= key;
                    if (temp.isEmpty()) {
                        // if the pq has no gold left, delete the key
                        treeMap.remove(key);
                    }
                }
                kattio.println(gold);
            }
            kattio.flush();
        }
    }
}