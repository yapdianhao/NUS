import java.util.Scanner;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Builddeps {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int fileNum = Integer.parseInt(sc.nextLine());
        String[] fileNames = new String[fileNum];
        Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
        int[] adj = new int[fileNum];
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < fileNum; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < fileNum; i++) {
            String line = sc.nextLine();
            String[] lineArr = line.split(" ");
            lineArr[0] = lineArr[0].substring(0, lineArr[0].length() - 1);
            //System.out.println(Arrays.toString(lineArr));
            for (String file : lineArr) {
                if (!ht.containsKey(file)) {
                    ht.put(file, ht.size());
                    fileNames[ht.get(file)] = file;
                }
            }
            int key = ht.get(lineArr[0]);
            for (int j = 1; j < lineArr.length; j++) {
                int toPush = ht.get(lineArr[j]);
                adjList.get(toPush).add(key);
                adj[key] += 1;
            }
            //System.out.println(ht);
        }
        //System.out.println(Arrays.toString(adj));
        String start = sc.nextLine();
        int startKey = ht.get(start);
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < fileNum; i++) {
            if (adj[i] == 0 && i != startKey) {
                queue.offer(i);
            }
        }
        //System.out.println("queue " + queue);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int i : adjList.get(curr)) {
                adj[i] -= 1;
                if (adj[i] == 0 && i != startKey) {
                    queue.offer(i);
                }
            }
        }
        queue.offer(startKey);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.println(fileNames[curr]);
            for (int i : adjList.get(curr)) {
                adj[i] -= 1;
                if (adj[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        /*for (int i = 0; i < fileNum; i++) {
            System.out.println(adjList.get(i));
        }*/

        //System.out.println(ht);
        //System.out.println(Arrays.toString(adj));
        //System.out.println(Arrays.toString(fileNames));
    }

}