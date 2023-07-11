package exercise2;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Stack;

public class Exercise2_3 {

    public void exercise2_3_5() {
        Integer[] a = new Integer[]{2, 2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 2};
        sortTwoDistinct(a);
        SortUtil.show(a);
    }

    private void sortTwoDistinct(Comparable[] a) {
        int l = 0, r = a.length - 1;
        int i = 0;
        while (i < r) {
            if (SortUtil.less(a[++i], a[l])) {
                SortUtil.exch(a, i, l);
                l++;
            }
        }
    }

    public void exercise2_3_6() {
        StdOut.println("N\t准确值\t估计值\t比值");
        int[] n = new int[]{100, 1000, 10000};
        for (int i = 0; i < n.length; i++) {
            QuickComputeCompare comparator = new QuickComputeCompare();
            Integer[] a = RandomGenerator.generateRandomInteger(n[i]);

            comparator.sort(a);
            int compareCount = comparator.getCompareCount();

            double estimatedCount = 2 * n[i] * Math.log(n[i]);

            StdOut.println(n[i] + "\t" + compareCount + "\t" + (int) estimatedCount + "\t" + compareCount / estimatedCount);
        }
    }

    public void exercise2_3_15() {
        Nut<Integer>[] nuts = new Nut[10];
        Bolt<Integer>[] bolts = new Bolt[10];

        for (int i = 0; i < 10; i++) {
            nuts[i] = new Nut<>(i);
            bolts[i] = new Bolt<>(i);
        }

        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);

        SortUtil.show(nuts);
        SortUtil.show(bolts);

        NutsAndBolts.sort(nuts, bolts);

        SortUtil.show(nuts);
        SortUtil.show(bolts);
    }

    public void exercise2_3_16() {
        Integer[] best = QuickBest.best(20);
        SortUtil.show(best);

        Quick.sort(best);
        SortUtil.show(best);
    }

    public void exercise2_3_17() {
        Integer[] a = RandomGenerator.generateRandomInteger(20);
        QuickSentinel.sort(a);

        SortUtil.show(a);
    }

    public void exercise2_3_18() {
        Integer[] a = RandomGenerator.generateRandomInteger(20);
        Quick3Way.sort(a);

        SortUtil.show(a);
    }

    public void exercise2_3_20() {
        Integer[] a = RandomGenerator.generateRandomInteger(20);
        QuickNoRecursive.sort(a);

        SortUtil.show(a);
    }

    public void exercise2_3_21() {
        Integer[] a = RandomGenerator.generateRandomInteger(100);
        QuickBentlyAndMcIlroy.sort(a);

        SortUtil.show(a);
        System.out.println(SortUtil.isSorted(a));
    }

    public static void main(String[] args) {
        Exercise2_3 runner = new Exercise2_3();

//        runner.exercise2_3_5();

//        runner.exercise2_3_6();

//        runner.exercise2_3_15();

//        runner.exercise2_3_16();

//        runner.exercise2_3_17();

//        runner.exercise2_3_18();

//        runner.exercise2_3_20();

        runner.exercise2_3_21();
    }
}

class QuickComputeCompare {

    private int compareCount;

    public void sort(Comparable[] a) {
        compareCount = 0;
        sort(a, 0, a.length - 1);
    }

    public int getCompareCount() {
        return compareCount;
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) if (i == hi) break;

            while (less(v, a[--j])) if (j == lo) break;

            if (i >= j) break;

            SortUtil.exch(a, i, j);
        }

        SortUtil.exch(a, lo, j);

        return j;
    }

    private boolean less(Comparable v, Comparable w) {
        compareCount++;
        return v.compareTo(w) < 0;
    }
}

class Nut<T extends Comparable> implements Comparable<Bolt<T>> {

    public T value;

    public Nut(T value) {
        this.value = value;
    }

    @Override
    public int compareTo(Bolt<T> o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

class Bolt<T extends Comparable> implements Comparable<Nut<T>> {

    public T value;

    Bolt(T value) {
        this.value = value;
    }

    @Override
    public int compareTo(Nut<T> o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

class NutsAndBolts {

    public static <T extends Comparable> void sort(Nut<T>[] nuts, Bolt<T>[] bolts) {
        if (nuts.length != bolts.length) {
            throw new RuntimeException();
        }
        sort(nuts, bolts, 0, nuts.length - 1);
    }

    private static <T extends Comparable> void sort(Nut<T>[] nuts, Bolt<T>[] bolts, int lo, int hi) {
        if (hi <= lo) return;
        int mid = partition(nuts, bolts, lo, hi);
        sort(nuts, bolts, lo, mid - 1);
        sort(nuts, bolts, mid + 1, hi);
    }

    private static <T extends Comparable> int partition(Nut<T>[] nuts, Bolt<T>[] bolts, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        Bolt<T> pivotBolt = bolts[lo];
        // 找到对应螺丝
        for (int k = lo; k <= hi; k++) {
            if (nuts[k].compareTo(pivotBolt) == 0) {
                SortUtil.exch(nuts, lo, k);
                break;
            }
        }
        // 用螺母套螺丝
        while (true) {

            while (nuts[++i].compareTo(pivotBolt) < 0) {
                if (i == hi) {
                    break;
                }
            }

            while (pivotBolt.compareTo(nuts[--j]) < 0) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            SortUtil.exch(nuts, i, j);
        }
        SortUtil.exch(nuts, lo, j);

        // 用螺丝比较螺母
        Nut<T> pivotNut = nuts[j];
        i = lo;
        j = hi + 1;

        while (true) {

            while (bolts[++i].compareTo(pivotNut) < 0) {
                if (i == hi) {
                    break;
                }
            }

            while (pivotNut.compareTo(bolts[--j]) < 0) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            SortUtil.exch(bolts, i, j);
        }
        SortUtil.exch(bolts, lo, j);

        return j;
    }
}

class QuickBest {

    public static Integer[] best(int size) {
        Integer[] a = new Integer[size];

        for (int i = 0; i < size; i++) {
            a[i] = i;
        }

        best(a, 0, size - 1);
        return a;
    }

    private static void best(Integer[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + ((hi - lo) >> 1);
        best(a, lo, mid - 1);
        best(a, mid + 1, hi);
        SortUtil.exch(a, lo, mid);
    }
}

class QuickSentinel {

    public static void sort(Comparable[] a) {
        if (a == null || a.length < 2) return;

        int maxIndex = 0;
        for (int i = 1; i < a.length; i++) {
            if (SortUtil.less(a[maxIndex], a[i])) {
                maxIndex = i;
            }
        }
        SortUtil.exch(a, maxIndex, a.length - 1);

        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = partition(a, lo, hi);
        sort(a, lo, mid - 1);
        sort(a, mid + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable pivot = a[lo];

        while (true) {

            while (SortUtil.less(a[++i], pivot)) {
            }

            while (SortUtil.less(pivot, a[--j])) {
            }

            if (i >= j) break;

            SortUtil.exch(a, i, j);
        }
        SortUtil.exch(a, lo, j);

        return j;
    }
}

class Quick3Way {

    public static void sort(Comparable[] a) {
        if (a == null || a.length < 2) return;

        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) SortUtil.exch(a, lt++, i++);
            else if (cmp > 0) SortUtil.exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}

class QuickNoRecursive {

    public static void sort(Comparable[] a) {
        if (a == null || a.length < 2) return;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(a.length - 1);
        sort(a, stack);
    }

    private static void sort(Comparable[] a, Stack<Integer> stack) {
        while (!stack.isEmpty()) {
            int hi = stack.pop();
            int lo = stack.pop();

            if (lo >= hi) continue;

            int mid = partition(a, lo, hi);

            if (hi - mid > mid - lo) {
                stack.push(mid + 1);
                stack.push(hi);
                stack.push(lo);
                stack.push(mid - 1);
            } else {
                stack.push(lo);
                stack.push(mid - 1);
                stack.push(mid + 1);
                stack.push(hi);
            }
        }
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];

        while (true) {
            while (SortUtil.less(a[++i], v)) {
                if (i == hi) break;
            }

            while (SortUtil.less(v, a[--j])) {
                if (j == lo) break;
            }

            if (i >= j) break;

            SortUtil.exch(a, i, j);
        }
        SortUtil.exch(a, lo, j);

        return j;
    }
}

class QuickBentlyAndMcIlroy {

    private static final int INSERTION_SORT_CUTOFF = 8;

    private static final int MEDIAN_OF_3_CUTOFF = 40;

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        int n = hi - lo + 1;

        if (n <= INSERTION_SORT_CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        } else if (n <= MEDIAN_OF_3_CUTOFF) {
            int m = median3(a, lo, lo + n / 2, hi);
            exch(a, m, lo);
        } else {
            int eps = n / 8;
            int mid = lo + n / 2;
            int m1 = median3(a, lo, lo + eps, lo + eps + eps);
            int m2 = median3(a, mid - eps, mid, mid + eps);
            int m3 = median3(a, hi - eps - eps, hi - eps, hi);
            int ninther = median3(a, m1, m2, m3);
            exch(a, ninther, lo);
        }

        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[--j]))
                if (j == lo) break;

            if (i == j && eq(a[i], v))
                exch(a, ++p, i);
            if (i >= j) break;

            exch(a, i, j);
            if (eq(a[i], v)) exch(a, ++p, i);
            if (eq(a[j], v)) exch(a, --q, j);
        }


        i = j + 1;
        for (int k = lo; k <= p; k++)
            exch(a, k, j--);
        for (int k = hi; k >= q; k--)
            exch(a, k, i++);

        sort(a, lo, j);
        sort(a, i, hi);
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
                (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
                (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;
        return v.compareTo(w) < 0;
    }

    private static boolean eq(Comparable v, Comparable w) {
        if (v == w) return true;
        return v.compareTo(w) == 0;
    }
}
