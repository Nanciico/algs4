package exercise2;


import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class Exercise2_1 {

    public void exercise2_1_11() {
        String[] a = {
                "E", "A", "S", "Y",
                "S", "H", "E", "L", "L",
                "S", "O", "R", "T",
                "Q", "U", "E", "S", "T", "I", "O", "N"
        };
        ShellSortKeepArray.sort(a);
        ShellSortKeepArray.show(a);
    }

    public void exercise2_1_12() {
        // TODO
    }

    public void exercise2_1_16() {
        Double[] doubles = RandomGenerator.generateRandomDoubles(100);
        StdOut.println(check(doubles));
    }

    private boolean check(Comparable[] a) {
        Comparable[] clone = new Comparable[a.length];
        System.arraycopy(a, 0, clone, 0, a.length);
        Shell.sort(a);
        Arrays.sort(clone);
        for (int i = 0; i < a.length; i++) {
            if (a[i] != clone[i]) return false;
        }
        return true;
    }

    public void exercise2_1_17() {
        Double[] a = RandomGenerator.generateRandomDoubles(100);
        SelectionSortAnimation.sort(a);
    }

    public void exercise2_1_19() {
        Integer[] a = new Integer[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = 100 - i;
        }
        ShellSortRecordCompare.sort(a);
    }

    public void exercise2_1_20() {
        Integer[] a = new Integer[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = i + 1;
        }
        ShellSortRecordCompare.sort(a);
    }

    public void exercise2_1_24() {
        String[] args = new String[]{"InsertionSentinel", "Insertion", "1000", "1000"};
        SortCompare.compare(args);
    }

    public void exercise2_1_25() {
        String[] args = new String[]{"InsertionWithoutExchange", "Insertion", "1000", "1000"};
        SortCompare.compare(args);
    }

    public void exercise2_1_26() {
        double timeInt = 0.0;
        for (int i = 0; i < 1000; i++) {
            int[] a = RandomGenerator.generateRandomPrimitiveInteger(1000);
            Stopwatch timer = new Stopwatch();
            InsertionSortPrimitive.sort(a);
            timeInt += timer.elapsedTime();
        }

        double timeInteger = 0.0;
        for (int i = 0; i < 1000; i++) {
            Integer[] a = RandomGenerator.generateRandomInteger(1000);
            Stopwatch timer = new Stopwatch();
            Insertion.sort(a);
            timeInteger += timer.elapsedTime();
        }

        StdOut.printf("time1 = %.1f\n", timeInt);
        StdOut.printf("time2 = %.1f\n", timeInteger);
    }

    public void exercise2_1_27() {
        for (int length = 128; length < 100000; length *= 2) {
            String[] args = new String[]{"Shell", "Insertion", String.valueOf(length), "10"};
            SortCompare.compare(args);
        }

        for (int length = 128; length < 100000; length *= 2) {
            String[] args = new String[]{"Shell", "Selection", String.valueOf(length), "10"};
            SortCompare.compare(args);
        }
    }

    public static void main(String[] args) {
        Exercise2_1 runner = new Exercise2_1();

//        runner.exercise2_1_11();

//        runner.exercise2_1_12();

//        runner.exercise2_1_16();

//        runner.exercise2_1_17();

//        runner.exercise2_1_19();

//        runner.exercise2_1_20();

//        runner.exercise2_1_24();

//        runner.exercise2_1_25();

//        runner.exercise2_1_26();

        runner.exercise2_1_27();
    }
}

class RandomGenerator {
    public static Double[] generateRandomDoubles(int n) {
        Double[] doubles = new Double[n];
        for (int i = 0; i < n; i++) {
            doubles[i] = StdRandom.uniformDouble();
        }
        return doubles;
    }

    public static int[] generateRandomPrimitiveInteger(int n) {
        int[] ints = new int[n];
        int max = 100000;
        for (int i = 0; i < n; i++) {
            ints[i] = StdRandom.uniformInt(-max, max);
        }
        return ints;
    }

    public static Integer[] generateRandomInteger(int n) {
        Integer[] integers = new Integer[n];
        int max = 100000;
        for (int i = 0; i < n; i++) {
            integers[i] = StdRandom.uniformInt(-max, max);
        }
        return integers;
    }
}

class SortCompare {
    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("InsertionSentinel")) InsertionSortWithSentinel.sort(a);
        if (alg.equals("InsertionWithoutExchange")) InsertionSortWithoutExchange.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {  // Use alg to sort T random arrays of length N.
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {  // Perform one experiment (generate and sort an array).
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    public static void compare(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T); // total for alg1
        double t2 = timeRandomInput(alg2, N, T); // total for alg2
        StdOut.printf("For %d random Doubles\n    %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }
}

class ShellSortKeepArray {
    public static void sort(Comparable[] a) {
        int n = a.length;
        int[] arrayH = getArrayH(n);
        for (int h : arrayH) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
        }
    }

    private static int[] getArrayH(int n) {
        int size = (int) (Math.log(2 * n + 1) / Math.log(3));
        int[] arrayH = new int[size];
        int h = 0;
        for (int i = size - 1; i >= 0; i--) {
            h = 3 * h + 1;
            arrayH[i] = h;
        }
        return arrayH;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
}

class ShellSortRecordCompare {
    public static void sort(Comparable[] a) {
        int compare = 0;
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    compare++;
                    exch(a, i, i - h);
                }
            }
            h = h / 3;
        }
        StdOut.println("Shell sort compared " + compare + " times.");
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

class SelectionSortAnimation {
    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable max = getMaxValue(a);
        initDraw(n, max);
        show(a, max);
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
            show(a, max);
        }
        show(a, max);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void show(Comparable[] a, Comparable max) {
        StdDraw.clear();
        for (int i = 0; i < a.length; i++) {
            double x = i + 0.5;
            double y = (double) a[i] / (double) max / 2;
            double halfWidth = 0.5;
            double halfHeight = y;
            StdDraw.rectangle(x, y, halfWidth, halfHeight);
            StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
        }
        StdDraw.pause(100);
    }

    private static Comparable getMaxValue(Comparable[] a) {
        if (a == null || a.length == 0) return -1;
        Comparable max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (less(max, a[i])) {
                max = a[i];
            }
        }
        return max;
    }

    private static void initDraw(int length, Comparable max) {
        StdDraw.setCanvasSize(1024, 256);
        StdDraw.setXscale(-1, length + 1);
        StdDraw.setYscale(0, (double) max);
    }
}

class InsertionSortAnimation {
    public static void sort(Comparable[] a) {

    }
}

class InsertionSortWithSentinel {
    public static void sort(Comparable[] a) {
        int min = 0;
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[min])) min = i;
        exch(a, 0, min);

        for (int i = 2; i < a.length; i++) {
            for (int j = i; less(j, j - 1); j--) {
                exch(a, j, j - 1);
            }
        }
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

class InsertionSortWithoutExchange {
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            Comparable value = a[i];
            int j = i;
            for (; j > 0 && less(value, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = value;
        }
        assert isSorted(a, 0, n - 1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            if (!less(a[i], a[i + 1])) return false;
        }
        return true;
    }
}

class InsertionSortPrimitive {
    public static void sort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}