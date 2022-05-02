import java.util.*;
//import java.io.*;

class Kattis {

    public static void main(String[] args) {
        //long start = System.currentTimeMillis();
        Kattio kattio = new Kattio(System.in, System.out);
        int commands = kattio.getInt();
        //long start = System.currentTimeMillis();
        PriorityQueue<Quest> q1 = new PriorityQueue<Quest>(commands, new QuestComparator()); 
        PriorityQueue<Quest> q2 = new PriorityQueue<Quest>(commands, new QuestComparator()); 
        for (int i = 0; i < commands; i++) {
            String action = kattio.getWord();
            if (action.equals("add")) {
                Quest quest = new Quest(kattio.getInt(), kattio.getInt());
                q1.add(quest);
            } else {
                int gold = 0;
                int energy = kattio.getInt();
                /*while (!q1.isEmpty()) {
                    Quest popped = q1.poll();
                    if (popped.energy <= energy) {
                        energy -= popped.energy;
                        gold += popped.gold;
                    } else {
                        q2.add(popped);
                        if (energy <= 0) {
                            break;
                        }
                    }
                }*/
                while (energy > 0 && q1.peek() != null) {
                    Quest popped = q1.poll();
                    if (popped.energy <= energy) {
                        energy -= popped.energy;
                        gold += popped.gold;
                    } else {
                        q2.add(popped);
                    }
                }
                while (!q2.isEmpty()) {
                    q1.add(q2.poll());
                }
                kattio.println(gold);
            }
        }
        /*q1.add(new Quest(3, 5));
        q1.add(new Quest(2, 4));
        q1.add(new Quest(3, 6));
        while (!q1.isEmpty()) {
            kattio.println(q1.poll().gold);
        }*/
        //long end = System.currentTimeMillis();
        //kattio.println("TIME: " + (end - start));
        kattio.close();
    }
}