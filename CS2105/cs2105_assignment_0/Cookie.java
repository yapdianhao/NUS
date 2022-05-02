import java.util.*;

class Cookie {
    
    public static void main(String[] args) {
        Heap heap = new Heap(5);
        heap.insert(9);
        heap.insert(3);
        heap.insert(1);
        heap.insert(10);
        heap.minHeap();
        heap.toString();
        /*
        Kattio kattio = new Kattio(System.in, System.out);
        while (kattio.hasMoreTokens()) {
            String input = kattio.getWord();
            try {
                int diameter = Integer.parseInt(input);
                binaryTree.add(diameter);
                Collections.sort(binaryTree);
            } catch (Exception e) {
                int median;
                if (binaryTree.size() % 2 == 0) {

                }
            }
        }*/
    }
}


class Heap {

    int maxSize;
    int[] Heap;
    int size;
    static final int FRONT = 1;

    public Heap(int maxSize) {
        this.size = 0;
        this.maxSize = maxSize;
        this.heap = new int[maxSize + 1];
        heap[0] = 0;
    }

    public int parent(int pos) {
        return pos / 2;
    }

    public int leftChild(int pos) {
        return 2 * pos;
    }

    public int rightChild(int pos) {
        return 2 * pos + 1;
    }

    public boolean isLeaf(int pos) {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    public void swap(int firstPosition, int secPosition) {
        int temp = heap[firstPosition];
        heap[firstPosition] = heap[secPosition];
        heap[secPosition] = temp;
    }

    public void minHeapify(int pos) {
        if (!isLeaf(pos)) {
            if (heap[pos] > heap[leftChild(pos)] || heap[pos] > heap[rightChild(pos)]) {
                if (heap[leftChild(pos)] > heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }

    public void insert(int element) {
        heap[++size] = element;
        int curr = size;
        while (heap[curr] < heap[parent(curr)]) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
    }

    public void minHeap() {
        for (int pos = (size / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }

    public int remove() {
        int removed = heap[FRONT];
        heap[FRONT] = heap[size--];
        minHeapify(FRONT);
        return removed;
    }

    public void toString() {
        for (int i = 0; i < size / 2; i++) {
            System.out.println("PARENT: " + heap[i] + "LEFT CHILD: " + heap[2 * i] + 
                " RIGHT CHILD: " + heap[2 * i + 1]);
            System.out.println();
        }
    }
}