import java.util.*;

class Cookie {
    
    static int MAX = 600000;

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(MAX); // this has the larger numbers
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(MAX, Collections.reverseOrder()); // this has smaller numbers
        while (kattio.hasMoreTokens()) {
            String input = kattio.getWord();
            try {
                // input is an integer
                int diameter = Integer.parseInt(input);
                minHeap.add(diameter);
                if (minHeap.size() - maxHeap.size() > 1) { //limits the larger heap size.
                    int minInMax = minHeap.poll(); // if diff >= 2; move top ele to smal heap
                    maxHeap.add(minInMax);
                }
                if (!maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
                    int temp = minHeap.poll();
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(temp);
                }
                kattio.flush();
            } catch (Exception e) {
                kattio.println(minHeap.poll());
                if (maxHeap.size() > minHeap.size()) {
                    int maxInMin = maxHeap.poll();
                    minHeap.add(maxInMin);
                }

                kattio.flush();
            }
        }   
        kattio.close();
    }
}


