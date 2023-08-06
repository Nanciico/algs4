package exercise2;

import com.sun.security.auth.callback.TextCallbackHandler;
import edu.princeton.cs.algs4.*;

public class Exercise2_4 {

    public void exercise2_4_19() {
        Integer[] a = RandomGenerator.generateRandomInteger(30);
        new MaxPQConstructor<>(a);
    }

    public void exercise2_4_24() {
        LinkedMaxPQ<Integer> pq = new LinkedMaxPQ<>();

        int size = 20;
        Integer[] a = RandomGenerator.generateRandomInteger(size);
        for (int i = 0; i < size; i++) {
            pq.insert(a[i]);
        }
        for (int i = 0; i < size; i++) {
            StdOut.print(pq.delMax() + " ");
        }
    }

    public void exercise2_4_25() {
        CubeSum.cubeSumMain(10000);
    }

    public void exercise2_4_26() {
        int capacity = 30;
        Integer[] a = RandomGenerator.generateRandomInteger(capacity);
        MaxPQInsertion<Integer> pq = new MaxPQInsertion<>(capacity);

        for (int i = 0; i < capacity; i++) {
            pq.insert(a[i]);
        }

        for (int i = 0; i < capacity; i++) {
            StdOut.print(pq.delMax() + " ");
        }
    }

    public void exercise2_4_27() {
        int capacity = 30;
        Integer[] a = RandomGenerator.generateRandomInteger(capacity);
        MaxPQWithMin<Integer> pq = new MaxPQWithMin<>(capacity);

        for (int i = 0; i < capacity; i++) {
            pq.insert(a[i]);
        }

        StdOut.println(pq.min());

        for (int i = 0; i < capacity; i++) {
            StdOut.print(pq.delMax() + " ");
        }
    }

    public void exercise2_4_28() {
        int M = 20;
        int N = 60;

        Stack<Point> points = topM(M, N);

        while (!points.isEmpty()) {
            StdOut.println(points.pop());
        }
    }

    private Stack<Point> topM(int M, int N) {
        MaxPQ<Point> pq = new MaxPQ<>();
        for (int i = 0; i < N; i++) {
            int x = StdRandom.uniformInt(-2000, 2000);
            int y = StdRandom.uniformInt(-2000, 2000);
            int z = StdRandom.uniformInt(-2000, 2000);
            Point point = new Point(x, y, z);

            pq.insert(point);
            if (pq.size() > M) {
                pq.delMax();
            }
        }

        Stack<Point> points = new Stack<>();
        for (int i = 0; i < M; i++) {
            points.push(pq.delMax());
        }
        return points;
    }

    public void exercise2_4_30() {
        int number = 10;
        MedianPQ<Integer> medianPQ = new MedianPQ<>();
        for (int i = 0; i < number; i++) {
            medianPQ.insert(i);
        }

        while (!medianPQ.isEmpty()) {
            StdOut.print(medianPQ.deleteMedian() + " ");
        }
    }

    public void exercise2_4_33() {

    }

    public static void main(String[] args) {
        Exercise2_4 runner = new Exercise2_4();

//        runner.exercise2_4_19();

//        runner.exercise2_4_24();

//        runner.exercise2_4_25();

//        runner.exercise2_4_26();

//        runner.exercise2_4_27();

//        runner.exercise2_4_28();

//        runner.exercise2_4_30();

        runner.exercise2_4_33();
    }
}

class PriorityQueueUtil {

    public static boolean isMaxHeap(Comparable[] a, int N) {
        for (int i = 1; i <= N; i++) {
            if (a[i] == null) return false;
        }
        for (int i = N + 1; i < a.length; i++) {
            if (a[i] != null) return false;
        }
        if (a[0] != null) return false;
        return isMaxHeapOrdered(a, 1, N);
    }

    private static boolean isMaxHeapOrdered(Comparable[] a, int k, int N) {
        if (k > N) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= N && SortUtil.less(a[k], a[left])) return false;
        if (right <= N && SortUtil.less(a[k], a[right])) return false;
        return isMaxHeapOrdered(a, left, N) && isMaxHeapOrdered(a, right, N);
    }
}

class SinkOptimizeMaxPQ<T extends Comparable<T>> {

    private T[] pq;
    private int N;

    SinkOptimizeMaxPQ(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException();

        pq = (T[]) new Object[capacity + 1];
        N = 0;
    }

    public T delMax() {
        T max = pq[1];
        if (max == null) throw new RuntimeException();

        SortUtil.exch(pq, 1, N--);
        pq[N + 1] = pq[1];  // temporary sentinel.
        sink(1);
        pq[N + 1] = null;

        return max;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (SortUtil.less(pq[j], pq[j + 1])) {  // optimize j < N
                j++;
            }

            if (!SortUtil.less(k, j)) {
                break;
            }

            SortUtil.exch(pq, k, j);
            k = j;
        }
    }
}

class MaxPQConstructor<T extends Comparable<T>> {

    private T[] pq;

    private int N;

    MaxPQConstructor(T[] a) {
        N = a.length;
        pq = (T[]) new Comparable[a.length + 1];
        for (int i = 0; i < N; i++) {
            pq[i + 1] = a[i];
        }
        for (int k = N / 2; k >= 1; k--) {
            sink(k);
        }

        SortUtil.show(pq);
        StdOut.println(PriorityQueueUtil.isMaxHeap(pq, N));
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && SortUtil.less(pq[j], pq[j + 1])) {
                j++;
            }

            if (!SortUtil.less(pq[k], pq[j])) {
                break;
            }

            SortUtil.exch(pq, k, j);
            k = j;
        }
    }
}

class TreeNode<T extends Comparable> {

    T value;

    TreeNode<T> father;
    TreeNode<T> left;
    TreeNode<T> right;

    TreeNode(T value) {
        this.value = value;
    }
}

class LinkedMaxPQ<T extends Comparable<T>> {

    private TreeNode<T> root;

    private TreeNode<T> last;

    private int size;

    public T delMax() {
        T result = root.value;
        exch(root, last);

        if (size == 1) {
            root = null;
            last = null;
            size--;
            return result;
        }

        if (size == 2) {
            root.left = null;
            last = root;
            size--;
            return result;
        }

        TreeNode<T> newLast = last;
        if (newLast == last.father.right) {
            newLast = last.father.left;
        } else {
            while (newLast != root) {
                if (newLast != newLast.father.left) {
                    break;
                }
                newLast = newLast.father;
            }

            if (newLast != root) {
                newLast = newLast.father.left;
            }
            while (newLast.right != null) {
                newLast = newLast.right;
            }
        }

        if (last.father.left == last) {
            last.father.left = null;
        } else {
            last.father.right = null;
        }

        sink(root);

        last = newLast;
        size--;

        return result;
    }

    public void insert(T value) {
        TreeNode<T> item = new TreeNode<>(value);

        if (size == 0) {
            root = last = item;
            size++;
            return;
        }

        if (size == 1) {
            root.left = item;
            item.father = root;
            last = item;
            size++;
            swim(item);
            return;
        }

        TreeNode<T> prev = last.father;
        if (prev.right == null) {
            prev.right = item;
        } else {
            while (prev != root) {
                if (prev.father.right != prev) {
                    break;
                }
                prev = prev.father;
            }

            if (prev != root) {
                prev = prev.father.right;
            }
            while (prev.left != null) {
                prev = prev.left;
            }

            prev.left = item;
        }
        item.father = prev;

        last = item;
        size++;
        swim(item);
    }

    private void swim(TreeNode<T> node) {
        while (node != root) {
            if (!less(node.father, node)) {
                break;
            }
            exch(node.father, node);
            node = node.father;
        }
    }

    private void sink(TreeNode<T> node) {
        while (node.left != null || node.right != null) {
            TreeNode<T> exchange;
            if (node.left != null && node.right != null) {
                exchange = less(node.right, node.left) ? node.left : node.right;
            } else {
                exchange = node.left;
            }

            if (!less(node, exchange)) {
                break;
            }

            exch(exchange, node);
            node = exchange;
        }
    }

    private boolean less(TreeNode<T> v, TreeNode<T> w) {
        return v.value.compareTo(w.value) < 0;
    }

    private void exch(TreeNode<T> v, TreeNode<T> w) {
        T temp = v.value;
        v.value = w.value;
        w.value = temp;
    }
}

class CubeSum implements Comparable<CubeSum> {

    private final long sum;
    private final long i;
    private final long j;

    CubeSum(long i, long j) {
        this.i = i;
        this.j = j;
        sum = i * i * i + j * j * j;
    }

    @Override
    public int compareTo(CubeSum o) {
        if (this.sum < o.sum) return -1;
        if (this.sum > o.sum) return +1;
        return 0;
    }

    public String toString() {
        return sum + " = " + i + "^3" + " + " + j + "^3";
    }

    public static void cubeSumMain(int N) {
        MinPQ<CubeSum> minPQ = new MinPQ<>();
        for (int i = 0; i < N; i++) {
            minPQ.insert(new CubeSum(i, i));
        }

        CubeSum prev = new CubeSum(-1, -1);
        while (!minPQ.isEmpty()) {
            CubeSum cubeSum = minPQ.delMin();

            if (cubeSum.compareTo(prev) == 0) {
                StdOut.println(prev);
                StdOut.println(cubeSum);
                StdOut.println();
            }

            if (cubeSum.j < N) {
                minPQ.insert(new CubeSum(cubeSum.i, cubeSum.j + 1));
            }

            prev = cubeSum;
        }
    }
}

class MaxPQInsertion<T extends Comparable<T>> {

    private final T[] pq;

    private int n;

    MaxPQInsertion(int capacity) {
        pq = (T[]) new Comparable[capacity + 1];
        int n = 0;
    }

    public void insert(T value) {
        pq[++n] = value;
        swim(n);
    }

    public T delMax() {
        T value = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        return value;
    }

    private void swim(int k) {
        T value = pq[k];

        while (k > 1 && less(pq[k / 2], value)) {
            pq[k] = pq[k / 2];
            k /= 2;
        }

        pq[k] = value;
    }

    private void sink(int k) {
        T value = pq[k];

        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq[j], pq[j + 1])) j++;
            if (!less(value, pq[j])) break;
            pq[k] = pq[j];
            k = j;
        }

        pq[k] = value;
    }

    private boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private void exch(int v, int w) {
        T temp = pq[v];
        pq[v] = pq[w];
        pq[w] = temp;
    }
}

class MaxPQWithMin<T extends Comparable<T>> {

    private final T[] pq;
    private int n;
    private T min;

    MaxPQWithMin(int capacity) {
        pq = (T[]) new Comparable[capacity + 1];
        n = 0;
        min = null;
    }

    public void insert(T value) {
        if (min == null) {
            min = value;
        } else if (value.compareTo(min) < 0) {
            min = value;
        }

        pq[++n] = value;
        swim(n);
    }

    public T delMax() {
        T value = pq[1];

        exch(1, n--);
        sink(1);
        pq[n + 1] = null;

        if (n == 0) {
            min = null;
        }

        return value;
    }

    public T min() {
        return min;
    }

    public void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private void exch(int v, int w) {
        T temp = pq[v];
        pq[v] = pq[w];
        pq[w] = temp;
    }

    private boolean less(int v, int w) {
        return pq[v].compareTo(pq[w]) < 0;
    }
}

class Point implements Comparable<Point> {
    private final int x;
    private final int y;
    private final int z;

    private Double euclideanDistance;

    Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

        euclideanDistance = Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public int compareTo(Point o) {
        return euclideanDistance.compareTo(o.euclideanDistance);
    }

    @Override
    public String toString() {
        return "( " + x + ", " + y + ", " + z + " ) euclideanDistance: " + euclideanDistance;
    }
}

class MedianPQ<T extends Comparable<T>> {
    private final MaxPQ<T> maxPQ;
    private final MinPQ<T> minPQ;

    T median;
    int size;

    MedianPQ() {
        maxPQ = new MaxPQ<>();
        minPQ = new MinPQ<>();
        median = null;
        size = 0;
    }

    public void insert(T value) {
        if (isEmpty()) {
            median = value;
            size++;
            return;
        }

        if (SortUtil.less(value, median)) {
            maxPQ.insert(value);
        } else {
            minPQ.insert(value);
        }

        if (maxPQ.size() - minPQ.size() >= 2) {
            T temp = median;
            median = maxPQ.delMax();
            minPQ.insert(temp);
        } else if (minPQ.size() - maxPQ.size() >= 2) {
            T temp = median;
            median = minPQ.delMin();
            maxPQ.insert(temp);
        }

        size++;
    }

    public T findMedian() {
        return median;
    }

    public T deleteMedian() {
        if (isEmpty()) {
            throw new RuntimeException();
        }

        if (size == 1) {
            T value = median;
            median = null;
            size--;
            return value;
        }

        T value = median;
        if (maxPQ.size() < minPQ.size()) {
            median = minPQ.delMin();
        } else {
            median = maxPQ.delMax();
        }
        size--;
        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

class IndexMinPQ<Key extends Comparable<Key>> {
    private int N;          // PQ中的元素数量

    /// 所有元素存储在 keys 数组中，并与其索引绑定
    /// 二叉堆 pq 根据元素的大小关系，生成索引的二叉堆
    /// qp 存储元素索引的索引，qp[i] 表示 keys[i] 元素对应的索引在二叉堆 pq 的位置，即 pq[qp[i]] 是 keys[i] 的索引
    /// 为什么需要记录元素索引的索引？ 在 delete / change 方法中变更任意元素 i ，可以直接通过 qp[i] 获取到其索引位置
    private Key[] keys;     // 元素数组
    private int[] pq;       // 元素索引二叉堆
    private int[] qp;       // 元素索引的索引，没有元素该值为 -1


    public IndexMinPQ(int maxN) {
        N = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void insert(int k, Key key) {
        keys[k] = key;

        N++;
        pq[N] = k;
        qp[k] = N;

        swim(N);
    }

    public Key min() {
        return keys[pq[1]];
    }

    public int delMin() {
        int indexOfMin = pq[1];
        exch(1, N--);
        sink(1);
        keys[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;
        return indexOfMin;
    }

    public int minIndex() {
        return pq[1];
    }

    public void change(int k, Key key) {
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public void delete(int k) {
        exch(k, N--);
        swim(qp[k]);
        sink(qp[k]);
        keys[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j + 1, j)) j++;
            if (!less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int v, int w) {
        return keys[pq[v]].compareTo(keys[pq[w]]) < 0;
    }

    private void exch(int v, int w) {
        int temp = pq[v];
        pq[v] = pq[w];
        pq[w] = temp;

        // 索引 pq[v] 与 pq[w] 交换，此时 pq[v] 指向 keys[w], pq[w] 指向 keys[v]
        qp[pq[v]] = v;
        qp[pq[w]] = w;
    }
}