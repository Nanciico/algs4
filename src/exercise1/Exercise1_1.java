package exercise1;

import edu.princeton.cs.algs4.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Exercise1_1 {
    public static void exercise1_1_3() {
        int a = StdIn.readInt();
        int b = StdIn.readInt();
        int c = StdIn.readInt();
        if (a == b && b == c) {
            StdOut.print("equal");
        } else {
            StdOut.print("not equal");
        }
    }

    public static void exercise1_1_5(double x, double y) {
        StdOut.print(0.0 <= x && x <= 1.0 && 0.0 <= y && y <= 1.0);
    }

    public static String exercise1_1_9(int N) {
        String s = "";
        for (int n = N; n > 0; n /= 2) {
            s = (n % 2) + s;
        }
        return s;
    }

    public static void exercise1_1_11(boolean[][] metric) {
        if (metric == null || metric.length == 0 || metric[0].length == 0) return;
        StdOut.print("\t");
        for (int i = 0; i < metric[0].length; i++) {
            StdOut.print(i + "\t");
        }
        StdOut.println();
        for (int i = 0; i < metric.length; i++) {
            StdOut.print(i + "\t");
            for (int j = 0; j < metric[0].length; j++) {
                String s = " ";
                if (metric[i][j]) {
                    s = "*";
                }
                StdOut.print(s + "\t");
            }
            StdOut.println();
        }
    }

    public static void exercise1_1_13(int[][] metric) {
        if (metric == null) return;
        int N = metric[0].length;
        for (int i = 0; i < N; i++) {
            for (int[] ints : metric) {
                StdOut.print(ints[i] + "\t");
            }
            StdOut.println();
        }
    }

    public static int exercise1_1_14(int N) {
        if (N < 1) {
            throw new RuntimeException();
        }
        int x = 0;
        int y = 1;
        while (y <= N) {
            y *= 2;
            x++;
        }
        return x - 1;
    }

    public static int[] exercise1_1_15(int[] a, int M) {
        int[] result = new int[M];
        for (int i = 0; i < M; i++) {
            int count = 0;
            for (int k : a) {
                if (k == i) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    public static int exercise1_1_19(int N) {
        if (N == 0 || N == 1) {
            return N;
        }
        int[] fibonacci = new int[N + 1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for (int i = 2; i <= N; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci[N];
    }

    public static double exercise1_1_20(int N) {
        return Math.log(factorial(N));
    }

    private static int factorial(int N) {
        if (N == 0 || N == 1) {
            return 1;
        }
        return factorial(N - 1) * N;
    }

    private static void exercise1_1_21() {
        String[] strings = StdIn.readAllLines();
        for (String s : strings) {
            String[] record = s.split(" ");
            int x = Integer.parseInt(record[1]);
            int y = Integer.parseInt(record[2]);
            StdOut.printf("%s\t%d\t%d\t%.3f\n", record[0], x, y, 1.0 * x / y);
        }
    }

    public static void exercise1_1_22() {
        int[] a = {10, 11, 12, 16, 18, 23, 29, 33, 48, 54, 57, 68, 77, 84, 98};
        StdOut.println(rank1_1_22(23, a));
        StdOut.println(rank1_1_22(50, a));
    }

    public static int rank1_1_22(int key, int[] a) {
        return rank1_1_22(key, a, 0, a.length - 1, 1);
    }

    public static int rank1_1_22(int key, int[] a, int lo, int hi, int depth) {
        for (int i = 0; i < depth; i++) {
            StdOut.print("\t");
        }
        StdOut.println("lo: " + lo + " hi: " + hi);
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank1_1_22(key, a, lo, mid - 1, depth + 1);
        else if (key > a[mid]) return rank1_1_22(key, a, mid + 1, hi, depth + 1);
        else return mid;
    }

    public static void exercise1_1_23(String[] args) {
        int[] whitelist = new In(args[0]).readAllInts();
        In in = new In(args[2]);
        Arrays.sort(whitelist);
        while (!in.isEmpty()) {
            int key = in.readInt();
            if ("+".equals(args[1]) && rank1_1_23(key, whitelist) < 0) {
                StdOut.println(key);
            } else if ("-".equals(args[1]) && rank1_1_23(key, whitelist) >= 0) {
                StdOut.println(key);
            }
        }
    }

    public static int rank1_1_23(int key, int[] a) {
        return rank1_1_23(key, a, 0, a.length - 1);
    }

    public static int rank1_1_23(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] < key) return rank1_1_23(key, a, mid + 1, hi);
        else if (a[mid] > key) return rank1_1_23(key, a, lo, mid - 1);
        else return mid;
    }

    public static void exercise1_1_24() {
        StdOut.println(Euclid(1111111, 1234567));
    }

    public static int Euclid(int p, int q) {
        StdOut.println("p=" + p + " q=" + q);
        if (q == 0) return p;
        int r = p % q;
        return Euclid(q, r);
    }

    public static void exercise1_1_27() {
        StdOut.println(binomial(100, 50, 0.25));
    }

    public static double binomial(int N, int k, double p) {
        double[][] arrP = new double[N + 1][k + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                arrP[i][j] = -1.0;
            }
        }
        arrP[0][0] = 1.0;
        return binomial(N, k, p, arrP);
    }

    private static double binomial(int N, int k, double p, double[][] arrP) {
        if (N < 0 || k < 0) return 0d;
        if (arrP[N][k] == -1.0) {
            arrP[N][k] = (1.0 - p) * binomial(N - 1, k, p, arrP) + p * binomial(N - 1, k - 1, p, arrP);
        }
        return arrP[N][k];
    }

    public static void exercise1_1_28(String[] args) {
        int[] whitelist = new In(args[0]).readAllInts();
        Arrays.sort(whitelist);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < whitelist.length - 1; i++) {
            if (whitelist[i] != whitelist[i + 1]) {
                list.add(whitelist[i]);
            }
        }
        list.add(whitelist[whitelist.length - 1]);
        for (int i : list) {
            StdOut.println(i);
        }
    }

    public static void exercise1_1_29() {
        String path = "exercise1/tinyT.txt";
        int[] whitelist = new In(path).readAllInts();
        Arrays.sort(whitelist);
        StdOut.println(rank1_1_29(77, whitelist));
        StdOut.println(count1_1_29(77, whitelist));
    }

    public static int rank1_1_29(int key, int[] a) {
        int index = rank1_1_23(key, a);
        if (index == -1) return index;
        while (index >= 0 && key == a[index]) {
            index--;
        }
        return index + 1;
    }

    public static int count1_1_29(int key, int[] a) {
        int index = rank1_1_29(key, a);
        int count = 0;
        if (index == -1) return count;
        while (index < a.length && key == a[index]) {
            index++;
            count++;
        }
        return count;
    }

    public static void exercise1_1_30(int N) {
        boolean[][] arr = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = Euclid(i, j) == 1;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                StdOut.print(arr[i][j] + "\t");
            }
            StdOut.println();
        }
    }

    public static void exercise1_1_31(int N, double p) {
        StdDraw.circle(0.5, 0.5, 0.5);
        Point1_1_31[] points = new Point1_1_31[N];
        double angle = 2 * Math.PI / N;
        StdDraw.setPenRadius(0.05);
        for (int i = 0; i < N; i++) {
            double x = 0.5 + 0.5 * Math.cos(angle * i);
            double y = 0.5 + 0.5 * Math.sin(angle * i);
            StdDraw.point(x, y);
            points[i] = new Point1_1_31(x, y);
        }
        StdDraw.setPenColor(Color.gray);
        StdDraw.setPenRadius(0.01);
        for (Point1_1_31 point1 : points) {
            for (Point1_1_31 point2 : points) {
                if (StdRandom.bernoulli(p)) {
                    StdDraw.line(point1.x, point1.y, point2.x, point2.y);
                }
            }
        }
    }

    static class Point1_1_31 {
        double x;
        double y;

        public Point1_1_31(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void exercise1_1_32(int N, double l, double r) {
        double[] values = StdIn.readAllDoubles();
        int[] count = new int[N];

        for (double v : values) {
            if (l <= v && v <= r) {
                int i = (int) (N * (v - l) / (r - l));
                count[i]++;
            }
        }
        int maxCount = StdStats.max(count);

        StdDraw.setCanvasSize(1024, 512);
        StdDraw.setXscale(l, r);
        StdDraw.setYscale(0, maxCount);

        double w = (r - l) / N;

        for (int i = 0; i < N; i++) {
            double x = l + (i + 0.5) * w;
            double y = count[i] / 2.0;
            double rw = 0.5 * w;
            double rh = count[i] / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }

    public static void exercise1_1_33() {
        StdOut.println("double dot(double[] x, double[] y):");
        double[] x = new double[]{1.2, 2.4, 4.8};
        double[] y = new double[]{3.2, 2.1, 1.0};
        double dotResult = Matrix.dot(x, y);
        StdOut.println(dotResult);

        StdOut.println("double[][] mult(double[][] a, double[][] b):");
        double[][] a = new double[][]{{1.2, 2.3, 3.4}, {4.5, 5.6, 6.7}};
        double[][] b = new double[][]{{4.6, 6.8}, {2.1, 4.3}, {5.2, 6.3}};
        double[][] multResult1 = Matrix.mult(a, b);
        for (double[] arr : multResult1) {
            for (double d : arr) {
                StdOut.printf("%.2f\t", d);
            }
            StdOut.println();
        }

        StdOut.println("double[][] transpose(double[][] a)");
        double[][] transposeResult = Matrix.transpose(a);
        for (double[] arr : transposeResult) {
            for (double d : arr) {
                StdOut.print(d + "\t");
            }
            StdOut.println();
        }

        StdOut.println("double[] mult(double[][] a, double[] x)");
        double[] multResult2 = Matrix.mult(a, x);
        for (double d : multResult2) {
            StdOut.print(d + "\t");
        }
        StdOut.println();

        StdOut.println("double[] mult(double[] y, double[][] a)");
        y = new double[]{1.2, 2.4};
        double[] multResult3 = Matrix.mult(y, a);
        for (double d : multResult3) {
            StdOut.printf("%.2f\t", d);
        }
        StdOut.println();
    }

    static class Matrix {
        static double dot(double[] x, double[] y) {
            if (x == null || y == null || x.length != y.length) {
                throw new IllegalArgumentException();
            }
            double result = 0d;
            for (int i = 0; i < x.length; i++) {
                result += x[i] * y[i];
            }
            return result;
        }

        static double[][] mult(double[][] a, double[][] b) {
            if (a == null || b == null || a.length == 0 || b.length == 0 || a[0].length != b.length) {
                throw new IllegalArgumentException();
            }

            double[][] result = new double[a.length][b[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        result[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
            return result;
        }

        static double[][] transpose(double[][] a) {
            if (a == null || a.length == 0 || a[0].length == 0) {
                throw new IllegalArgumentException();
            }

            double[][] result = new double[a[0].length][a.length];
            for (int i = 0; i < a[0].length; i++) {
                for (int j = 0; j < a.length; j++) {
                    result[i][j] = a[j][i];
                }
            }
            return result;
        }

        static double[] mult(double[][] a, double[] x) {
            if (a == null || x == null || a.length == 0 || a[0].length != x.length) {
                throw new IllegalArgumentException();
            }

            double[] result = new double[a.length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < x.length; j++) {
                    result[i] += a[i][j] * x[j];
                }
            }
            return result;
        }

        static double[] mult(double[] y, double[][] a) {
            if (y == null || a == null || y.length != a.length) {
                throw new IllegalArgumentException();
            }

            double[] result = new double[y.length];
            for (int i = 0; i < y.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    result[i] += y[i] * a[i][j];
                }
            }
            return result;
        }
    }

    public static void exercise1_1_36() {
        int M = StdIn.readInt();
        int N = StdIn.readInt();

        int[][] positions = new int[M][M];

        double[] a = new double[M];
        for (int i = 0; i < N; i++) {
            initializeArray(a);
            StdRandom.shuffle(a);
            for (int j = 0; j < a.length; j++) {
                positions[(int) a[j]][j]++;
            }
        }

        for (int[] arr : positions) {
            for (int i : arr) {
                StdOut.printf("%4d ", i);
            }
            StdOut.println();
        }
    }

    public static void initializeArray(double[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
    }

    public static void exercise1_1_37() {
        int M = StdIn.readInt();
        int N = StdIn.readInt();

        int[][] positions = new int[M][M];

        double[] a = new double[M];
        for (int i = 0; i < N; i++) {
            initializeArray(a);
            shuffle1_1_37(a);
            for (int j = 0; j < a.length; j++) {
                positions[(int) a[j]][j]++;
            }
        }

        for (int[] arr : positions) {
            for (int i : arr) {
                StdOut.printf("%4d ", i);
            }
            StdOut.println();
        }
    }

    public static void shuffle1_1_37(double[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniformInt(n - 1);
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void exercise1_1_38() {
        int key = 54467079;
        int[] arr = new In("exercise1/largeT.txt").readAllInts();

        StdOut.println("BruteForceSearch:");
        Stopwatch stopwatch = new Stopwatch();
        int forceSearchResult = BruteForceSearch(key, arr);
        double forceSearchTime = stopwatch.elapsedTime();
        StdOut.println("result: " + forceSearchResult);
        StdOut.println("time:" + forceSearchTime + "(ms)");

        StdOut.println("BruteForceSearch:");
        stopwatch = new Stopwatch();
        Arrays.sort(arr);
        int binarySearchResult = rank1_1_23(key, arr);
        double binarySearchTime = stopwatch.elapsedTime();
        StdOut.println("result: " + binarySearchResult);
        StdOut.println("time:" + binarySearchTime + "(ms)");
    }

    public static int BruteForceSearch(int key, int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void exercise1_1_39() {
        int T = StdIn.readInt();
        int[] N = {1000, 10000, 100000, 1000000};
        int[][] totalCount = new int[T][4];

        for (int t = 1; t <= T; t++) {
            int[] count = new int[4];
            for (int n = 0; n < N.length; n++) {
                int[] arr1 = randomArray(N[n]);
                int[] arr2 = randomArray(N[n]);

                for (int i = 0; i < arr1.length; i++) {
                    if (rank1_1_23(arr1[i], arr2) >= 0) {
                        count[n]++;
                    }
                    while (i < arr1.length - 1 && arr1[i] == arr1[i + 1]) {
                        i++;
                    }
                    if (i == arr1.length - 2 && arr1[i] == arr1[arr1.length - 1]) {
                        i++;
                    }
                }
                totalCount[t - 1][n] += count[n];
                StdOut.println("t = " + t + ";\tn = 10**" + (int) Math.log10(N[n]) + ";\tsame = " + count[n]);
            }
        }

        StdOut.println("\t\t\t10**3\t\t10**4\t\t10**5\t\t10**6");
        for (int t = 1; t < T; t++) {
            StdOut.print("t = " + t);
            for (int n = 0; n < 4; n++) {
                StdOut.printf("\t\t%.2f", 1.0 * totalCount[t - 1][n] / t);
            }
            StdOut.println();
        }

    }

    public static int[] randomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = StdRandom.uniformInt(100000, 1000000);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
//        exercise1_1_3();

//        exercise1_1_5(0.2, 0.3);

//        StdOut.print(exercise1_1_9(20));

//        boolean[][] b = {
//                {true, true, false, false},
//                {false, false, true, true},
//                {true, false, true, false},
//                {false, true, false, true}
//        };
//        exercise1_1_11(b);

//        int[][] metric = {
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12}
//        };
//        exercise1_1_13(metric);

//        StdOut.print(exercise1_1_14(10));

//        int[] a = {1, 2, 3, 1, 2};
//        int[] result = exercise1_1_15(a, 5);
//        for (int i = 0; i < result.length; i++) {
//            StdOut.print(result[i] + " ");
//        }

//        StdOut.print(exercise1_1_19(10));

//        StdOut.print(exercise1_1_20(10));

//        exercise1_1_21();

//        exercise1_1_22();

//        String[] args1_1_23 = {"exercise1/tinyW.txt", "+", "exercise1/tinyT.txt"};
//        exercise1_1_23(args1_1_23);
//        StdOut.println();
//        args1_1_23 = new String[]{"exercise1/tinyW.txt", "-", "exercise1/tinyT.txt"};
//        exercise1_1_23(args1_1_23);

//        exercise1_1_24();

//        exercise1_1_27();

//        String[] args1_1_28 = {"exercise1/tinyW.txt"};
//        exercise1_1_28(args1_1_28);

//        exercise1_1_29();

//        exercise1_1_30(5);

//        exercise1_1_31(5, 0.5);

//        exercise1_1_32(5, 0.5, 1.8);

//        exercise1_1_33();

//        exercise1_1_36();

//        exercise1_1_37();

//        exercise1_1_38();

        exercise1_1_39();
    }
}
