package exercise1;

import edu.princeton.cs.algs4.*;

import java.util.Iterator;

public class Exercise1_5 {

    public void exercise1_5_1() {
        MyQuickFind uf = new MyQuickFind(10);
        uf.union(9, 0);
        uf.union(3, 4);
        uf.union(5, 8);
        uf.union(7, 2);
        uf.union(2, 1);
        uf.union(5, 7);
        uf.union(0, 3);
        uf.union(4, 2);
        StdOut.println(uf.count());
    }

    public void exercise1_5_2() {
        MyQuickUnion uf = new MyQuickUnion(10);
        uf.union(9, 0);
        uf.union(3, 4);
        uf.union(5, 8);
        uf.union(7, 2);
        uf.union(2, 1);
        uf.union(5, 7);
        uf.union(0, 3);
        uf.union(4, 2);
        StdOut.println(uf.count());
    }

    public void exercise1_5_3() {
        MyWeightedQuickUnion uf = new MyWeightedQuickUnion(10);
        uf.union(9, 0);
        uf.union(3, 4);
        uf.union(5, 8);
        uf.union(7, 2);
        uf.union(2, 1);
        uf.union(5, 7);
        uf.union(0, 3);
        uf.union(4, 2);
        StdOut.println(uf.count());
    }

    public void exercise1_5_4() {
        // TODO
    }

    public void exercise1_5_12() {
        In in = new In("algs4-data/tinyUF.txt");
        int n = in.readInt();
        QuickUnionWithPathCompression uf = new QuickUnionWithPathCompression(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

    public void exercise1_5_13() {
        In in = new In("algs4-data/tinyUF.txt");
        int n = in.readInt();
        WeightedQuickUnionWithPathCompression uf = new WeightedQuickUnionWithPathCompression(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

    public void exercise1_5_14() {
        In in = new In("algs4-data/tinyUF.txt");
        int n = in.readInt();
        WeightedQuickUnionHeight uf = new WeightedQuickUnionHeight(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

    public void exercise1_5_17() {
        ErdosRenyi();
    }

    private void ErdosRenyi() {
        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        int connections = 0;
        while (uf.count() != 1) {
            int p = StdRandom.uniformInt(N);
            int q = StdRandom.uniformInt(N);
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
            connections++;
        }
        StdOut.println(connections);
    }

    public void exercise1_5_18() {
        RandomGrid();
    }

    private void RandomGrid() {
        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        RandomBag<Connection> randomBag = new RandomBag<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Connection connection = new Connection(i, j);
                randomBag.add(connection);
            }
        }

        Iterator<Connection> iterator = randomBag.iterator();

        for (Connection connection = iterator.next(); uf.count() != 1; connection = iterator.next()) {
            if (uf.connected(connection.p, connection.q)) continue;
            uf.union(connection.p, connection.q);
            StdOut.println(connection);
        }
    }

    private record Connection(int p, int q) {
        @Override
        public String toString() {
            return p + " " + q;
        }
    }

    public static void main(String[] args) {
        Exercise1_5 runner = new Exercise1_5();

//        runner.exercise1_5_1();

//        runner.exercise1_5_2();

//        runner.exercise1_5_3();

//        runner.exercise1_5_12();

//        runner.exercise1_5_13();

//        runner.exercise1_5_14();

//        runner.exercise1_5_17();

        runner.exercise1_5_18();
    }
}

class MyQuickFind {
    private final int[] id;
    private int count;
    private int times;

    MyQuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
        count = N;
        times = 0;
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
            times++;
        }

        count--;

        printTimes();
        printId();
        StdOut.println();
    }

    public int find(int p) {
        times++;
        return id[p];
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public int count() {
        return count;
    }

    private void printTimes() {
        StdOut.println("times: " + times);
    }

    private void printId() {
        StdOut.println("id:");
        for (int i = 0; i < id.length; i++) {
            StdOut.print(id[i] + " ");
        }
        StdOut.println();
    }
}

class MyQuickUnion {
    private final int[] parent;
    private int count;
    private int times;

    MyQuickUnion(int N) {
        parent = new int[N];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        count = N;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        times++;
        count--;

        printTimes();
        printId();
        StdOut.println();
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
            times++;
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    private void printTimes() {
        StdOut.println("times: " + times);
    }

    private void printId() {
        StdOut.println("id:");
        for (int i = 0; i < parent.length; i++) {
            StdOut.print(parent[i] + " ");
        }
        StdOut.println();
    }
}

class MyWeightedQuickUnion {
    private final int[] parent;
    private final int[] size;
    private int count;
    private int times;

    MyWeightedQuickUnion(int N) {
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        count = N;
        times = 0;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        times++;
        count--;

        printTimes();
        printId();
        StdOut.println();
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
            times++;
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    private void printTimes() {
        StdOut.println("times: " + times);
    }

    private void printId() {
        StdOut.println("id:");
        for (int i = 0; i < parent.length; i++) {
            StdOut.print(parent[i] + " ");
        }
        StdOut.println();
    }
}

class QuickUnionWithPathCompression {
    private final int[] parent;
    private int count;

    QuickUnionWithPathCompression(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        count = N;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }

    public int find(int p) {
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }
        // Path Compression
        while (parent[p] != root) {
            int temp = parent[p];
            parent[p] = root;
            p = temp;
        }
        return root;
    }

    public int count() {
        return count;
    }
}

class WeightedQuickUnionWithPathCompression {
    private final int[] parent;
    private final int[] size;
    private int count;

    WeightedQuickUnionWithPathCompression(int N) {
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        count = N;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    public int find(int p) {
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }
        while (parent[p] != root) {
            int temp = parent[p];
            parent[p] = root;
            p = temp;
        }
        return root;
    }

    public int count() {
        return count;
    }
}

class WeightedQuickUnionHeight {
    private final int[] parent;
    private final int[] height;
    private int count;

    WeightedQuickUnionHeight(int N) {
        parent = new int[N];
        height = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            height[i] = 1;
        }
        count = N;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        if (height[rootP] < height[rootQ]) {
            parent[rootP] = rootQ;
            height[rootQ] = Math.max(height[rootQ], height[rootP] + 1);
        } else {
            parent[rootQ] = rootP;
            height[rootP] = Math.max(height[rootP], height[rootQ] + 1);
        }
        count--;
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public int count() {
        return count;
    }
}