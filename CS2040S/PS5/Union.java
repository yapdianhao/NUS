class Union {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int nums = io.getInt();
        int queries = io.getInt();
        int[] arr = new int[nums];
        int[] parent = new int[nums];
        int[] size = new int[nums];
        for (int i = 0; i < nums; i++) {
            arr[i] = i;
            parent[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < queries; i++) {
            String word = io.getWord();
            int first = io.getInt();
            int sec = io.getInt();
            if (word.equals("?")) {
                find(io, first, sec, parent);
            } else {
                union(io, first, sec, size, parent);
            }
        }
        io.close();
    }

    static int getRoot(int i, int[] parent) {
        /*while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;*/
        return i == parent[i] ? i : getRoot(parent[i], parent);
    }

    static void find(Kattio io, int first, int sec, int[] parent) {
        io.println(getRoot(first, parent) == getRoot(sec, parent) ? "yes" : "no");
    }

    static void union(Kattio io, int first, int sec, int[] size, int[] parent) {
        first = getRoot(first, parent);
        sec = getRoot(sec, parent);
        if (first == sec) return;
        if (size[first] < size[sec]) {
            parent[first] = sec;
            size[sec] += size[first];
        } else {
            parent[sec] = first;
            size[first] += size[sec];
        }
    }
}