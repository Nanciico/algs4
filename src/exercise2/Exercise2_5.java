package exercise2;

import edu.princeton.cs.algs4.*;

import java.util.*;
import java.util.Queue;

public class Exercise2_5 {

    public void exercise2_5_2() {
        String[] input = {"black", "highway", "board", "high", "way", "left", "blackboard", "right", "grandson", "grand"};
        compoundWord(input);
    }

    private void compoundWord(String[] a) {
        Arrays.sort(a, Comparator.comparingInt(String::length));

        int compoundThreshold = a[0].length() * 2;
        int mayCompoundIndex = 0;
        while (a[mayCompoundIndex].length() < compoundThreshold && mayCompoundIndex < a.length) {
            mayCompoundIndex++;
        }

        for (int i = mayCompoundIndex; i < a.length; i++) {
            int lengthSum = a[i].length();
            for (int j = 0; j < i; j++) {
                int lengthA = a[j].length();
                int lengthB = lengthSum - lengthA;

                int start = binarySearchMinIndex(a, lengthB, j, i - 1);
                if (start != -1) {
                    while (a[start].length() == lengthB) {
                        if ((a[start] + a[j]).equals(a[i]) || (a[j] + a[start]).equals(a[i])) {
                            StdOut.println(a[i]);
                        }
                        start++;
                    }
                }
            }
        }
    }

    // 二分查找 a[] 在 [lo, hi] 区间内长度为 length 的最小索引
    private int binarySearchMinIndex(String[] a, int length, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (length <= a[mid].length()) hi = mid - 1;
            else lo = mid + 1;
        }

        if (lo == a.length) return -1;
        return a[lo].length() == length ? lo : -1;
    }

    public void exercise2_5_4() {
        String[] input = {"aa", "cc", "ff", "aa", "bb", "cc"};
        String[] distinct = dedup(input);

        for (int i = 0; i < distinct.length; i++) {
            if (distinct[i] != null)
                StdOut.print(distinct[i] + " ");
        }
    }

    private String[] dedup(String[] a) {
        String[] copy = Arrays.copyOf(a, a.length);

        Arrays.sort(copy);

        String[] distinct = new String[a.length];
        distinct[0] = copy[0];
        for (int i = 1; i < copy.length; i++) {
            if (distinct[i - 1] != copy[i]) {
                distinct[i] = copy[i];
            }
        }

        return distinct;
    }

    public void exercise2_5_6() {
        Integer[] a = new Integer[]{9, 6, 3, 2, 1, 5, 4, 7, 8, 0};
        StdOut.println(QuickSelectRecur.select(a, 4));
    }

    public void exercise2_5_12() {
        Task[] a = new Task[]{
                new Task("task8", 8),
                new Task("task1", 1),
                new Task("task5", 5),
                new Task("task7", 7),
                new Task("task6", 6),
                new Task("task3", 3),
                new Task("task2", 2),
                new Task("task4", 4)
        };
        a = SPT.spt(a);

        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public void exercise2_5_13() {
        Task[] a = new Task[]{
                new Task("task8", 8),
                new Task("task1", 1),
                new Task("task5", 5),
                new Task("task7", 7),
                new Task("task6", 6),
                new Task("task3", 3),
                new Task("task2", 2),
                new Task("task4", 4)
        };
        LPT.lpt(a, 3);
    }

    public void exercise2_5_14() {
        Domain[] domains = new Domain[]{
                new Domain("baidu.com"),
                new Domain("google.com"),
                new Domain("edu.princeton.cs"),
                new Domain("edu.princeton.ee"),
                new Domain("apple.com")
        };
        Arrays.sort(domains);

        for (int i = 0; i < domains.length; i++) {
            StdOut.println(domains[i]);
        }
    }

    public void exercise2_5_17() {
        Integer[] integers = RandomGenerator.generateRandomInteger(99999);
        StdOut.println(checkStability_2_5_17(integers));
    }

    private boolean checkStability_2_5_17(Comparable[] a) {
        KeyWrapper[] wrapper = new KeyWrapper[a.length];
        for (int i = 0; i < a.length; i++) {
            wrapper[i] = new KeyWrapper(i, a[i]);
        }

        Shell.sort(wrapper);

        for (int i = 1; i < wrapper.length; i++) {
            if (wrapper[i - 1].key() == wrapper[i].key()
                    && wrapper[i - 1].index() >= wrapper[i].index()) {
                return false;
            }
        }
        return true;
    }

    public void exercise2_5_18() {
        Integer[] integers = RandomGenerator.generateRandomInteger(99999);
        StdOut.println(checkStability_2_5_18(integers));
    }

    private boolean checkStability_2_5_18(Comparable[] a) {
        ForceStableKeyWrapper[] wrapper = new ForceStableKeyWrapper[a.length];
        for (int i = 0; i < a.length; i++) {
            wrapper[i] = new ForceStableKeyWrapper(i, a[i]);
        }

        Shell.sort(wrapper);

        for (int i = 1; i < wrapper.length; i++) {
            if (wrapper[i - 1].key() == wrapper[i].key()
                    && wrapper[i - 1].index() >= wrapper[i].index()) {
                return false;
            }
        }
        return true;
    }

    public void exercise2_5_19() {
        int[] a = KendallTau.permutation(10);
        int[] b = KendallTau.permutation(10);

        StdOut.println(KendallTau.distance(a, b));
    }

    public static void main(String[] args) {
        Exercise2_5 runner = new Exercise2_5();

//        runner.exercise2_5_2();

//        runner.exercise2_5_4();

//        runner.exercise2_5_6();

//        runner.exercise2_5_12();

//        runner.exercise2_5_13();

//        runner.exercise2_5_14();

//        runner.exercise2_5_17();

//        runner.exercise2_5_18();

        runner.exercise2_5_19();
    }
}

class QuickSelectRecur {

    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length)
            throw new IllegalArgumentException();

        return select(a, k, 0, a.length - 1);
    }

    private static Comparable select(Comparable[] a, int k, int lo, int hi) {
        int j = partition(a, lo, hi);
        if (k < j) return select(a, k, lo, j - 1);
        else if (k > j) return select(a, k, j + 1, hi);
        else return a[j];
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            while (less(v, a[--j])) {
                if (j == lo) break;
            }

            if (i >= j) break;

            exch(a, i, j);
        }

        exch(a, lo, j);

        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

record Task(String name, int time) implements Comparable<Task> {

    @Override
    public int compareTo(Task o) {
        return time - o.time;
    }

    @Override
    public String toString() {
        return name + " " + time;
    }
}

class SPT {
    public static Task[] spt(Task[] tasks) {
        Arrays.sort(tasks);
        return tasks;
    }
}

class LPT {
    public static void lpt(Task[] tasks, int M) {
        MaxPQ<Task> taskMaxPQ = new MaxPQ<>();

        for (int i = 0; i < tasks.length; i++) {
            taskMaxPQ.insert(tasks[i]);
        }

        MinPQ<Processor> processors = new MinPQ<>();
        for (int i = 0; i < M; i++) {
            Processor processor = new Processor(i);
            processors.insert(processor);
        }

        while (!taskMaxPQ.isEmpty()) {
            Processor processor = processors.delMin();
            processor.process(taskMaxPQ.delMax());
            processors.insert(processor);
        }

        while (!processors.isEmpty()) {
            StdOut.println(processors.delMin());
        }
    }
}

class Processor implements Comparable<Processor> {

    private final int processorNumber;
    private final Queue<Task> tasks = new LinkedList<>();
    private int processTime = 0;

    public Processor(int processorNumber) {
        this.processorNumber = processorNumber;
    }

    public void process(Task task) {
        tasks.offer(task);
        processTime += task.time();
    }

    @Override
    public int compareTo(Processor o) {
        return processTime - o.processTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Processor-").append(processorNumber).append(": ");

        while (!tasks.isEmpty()) {
            sb.append(tasks.poll().name()).append(" ");
        }

        return sb.toString();
    }
}

class Domain implements Comparable<Domain> {

    private final String[] fields;
    private final int length;

    Domain(String url) {
        fields = url.split("\\.");
        length = fields.length;
    }

    @Override
    public int compareTo(Domain o) {
        int minLength = Math.min(length, o.length);

        for (int i = minLength - 1; i >= 0; i--) {
            int compare = fields[i].compareTo(o.fields[i]);
            if (compare != 0) {
                return compare;
            }
        }

        return length - o.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            sb.append(fields[i]);
            if (i != 0) {
                sb.append(".");
            }
        }
        return sb.toString();
    }
}

record KeyWrapper<Key extends Comparable<Key>>(int index, Key key) implements Comparable<KeyWrapper<Key>> {
    @Override
    public int compareTo(KeyWrapper<Key> o) {
        return key.compareTo(o.key);
    }
}

record ForceStableKeyWrapper<Key extends Comparable<Key>>(int index,
                                                          Key key) implements Comparable<ForceStableKeyWrapper<Key>> {
    @Override
    public int compareTo(ForceStableKeyWrapper<Key> o) {
        int compare = key.compareTo(o.key);
        return compare == 0 ? index - o.index : compare;
    }
}

class KendallTau {
    public static long distance(int[] a, int[] b) {
        if (a.length != b.length) throw new IllegalArgumentException();

        int n = a.length;

        int[] ainv = new int[n];
        for (int i = 0; i < n; i++) {
            ainv[a[i]] = i;
        }

        int[] bnew = new int[n];
        for (int i = 0; i < n; i++) {
            bnew[i] = ainv[b[i]];
        }

        return Inversions.count(bnew);
    }

    public static int[] permutation(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        StdRandom.shuffle(a);
        return a;
    }
}

class StableMinQueue<T extends Comparable<T>> {

    int capacity;
    int n;
    T[] pq;
    long[] time;
    long timestamp = 1;

    StableMinQueue(int capacity) {
        this.capacity = capacity;
        n = 0;
        pq = (T[]) new Object[capacity + 1];
        time = new long[capacity + 1];
    }

    public void insert(T value) {
        if (n >= capacity) throw new RuntimeException();

        pq[++n] = value;
        time[n] = timestamp++;
        swim(n);
    }

    public T delMin() {
        if (n == 0) throw new RuntimeException();

        T value = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        time[n + 1] = 0;
        return value;
    }

    private void swim(int k) {
        while (k >= 1) {
            if (!less(k / 2, k)) break;
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) j++;
            if (!less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int v, int w) {
        int compare = pq[v].compareTo(pq[w]);
        if (compare == 0) {
            return time[v] - time[w] < 0;
        }
        return compare < 0;
    }

    private void exch(int v, int w) {
        T temp = pq[v];
        pq[v] = pq[w];
        pq[w] = temp;

        long tempTime = time[v];
        time[v] = time[w];
        time[w] = tempTime;
    }
}
