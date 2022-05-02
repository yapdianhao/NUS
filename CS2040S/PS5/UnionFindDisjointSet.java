public class UnionFindDisjointSet {   

    int[] parents;
    int[] size;

    /**
     * Constructs a new Union-Find Disjoint Set with the specified number of vertices, 
     * indexed from 0 to (numVertices - 1).
     * 
     * @param numVertices The number of vertices in this Union-Find Disjoint Set
     */
    public UnionFindDisjointSet(int numVertices) {
        this.parents = new int[numVertices];
        this.size = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Unions the set containing vertex u with the set containing vertex v. If both 
     * vertices u and v are in the same set to begin with, the sets should remain 
     * unchanged.
     */
    public void union(int u, int v) {
        //while (parents[u] != u) u = parents[u];
        //while (parents[v] != v) v = parents[v];
        u = findRoot(u);
        v = findRoot(v);
        if (size[u] > size[v]) {
            parents[v] = u;
            size[u] += size[v];
        } else {
            parents[u] = v;
            size[v] += size[u];
        }
    }

    public int findRoot(int p) {
        int root = p;
        while (parents[root] != root) root = parents[root];
        while (parents[p] != p) {
            int temp = parents[p];
            parents[p] = root;
            p = temp;
        }
        return root;
    }

    /**
     * Checks if vertices u and v belong to the same set.
     * 
     * @return True if vertices u and v belong to the same set, false otherwise. 
     */
    public boolean isSameSet(int u, int v) {
        return findRoot(u) == findRoot(v);
    }
}