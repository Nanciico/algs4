package exercise1;

import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class Exercise1_2 {
    public static void exercise1_2_1() {
        int N = StdIn.readInt();
        Point2D[] point2Ds = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x = StdRandom.uniformDouble(0.0, 1.0);
            double y = StdRandom.uniformDouble(0.0, 1.0);
            point2Ds[i] = new Point2D(x, y);
        }

        StdDraw.setPenRadius(0.01);
        for (Point2D point : point2Ds) {
            point.draw();
        }

        double min = Double.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                double distance = point2Ds[i].distanceTo(point2Ds[j]);
                min = Math.min(distance, min);
            }
        }
        StdOut.println(min);
    }

    public static void exercise1_2_2() {
        int N = StdIn.readInt();
        Interval1D[] interval1Ds = new Interval1D[N];
        for (int i = 0; i < N; i++) {
            double lo = StdRandom.uniformDouble();
            double hi = StdRandom.uniformDouble();
            if (lo > hi) {
                double temp = lo;
                lo = hi;
                hi = temp;
            }
            interval1Ds[i] = new Interval1D(lo, hi);
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (interval1Ds[i].intersects(interval1Ds[j])) {
                    StdOut.println("Interval" + i + ": min = " + interval1Ds[i].min() + " max = " + interval1Ds[j].max() +
                            " Interval" + j + ": min = " + interval1Ds[j].min() + " max = " + interval1Ds[j].max());
                }
            }
        }
    }

    public static void exercise1_2_3() {
        String[] all = StdIn.readAll().split("\\s+");
        assert all.length == 3;
        int N = Integer.parseInt(all[0]);
        double min = Double.parseDouble(all[1]);
        double max = Double.parseDouble(all[2]);
        assert min < max;

        Interval2D[] interval2Ds = new Interval2D[N];
        Point2D[] point1 = new Point2D[N];
        Point2D[] point2 = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double xlo = StdRandom.uniformDouble(min, max);
            double xhi = StdRandom.uniformDouble(min, max);
            if (xlo > xhi) {
                double temp = xlo;
                xlo = xhi;
                xhi = temp;
            }
            Interval1D xIntervals = new Interval1D(xlo, xhi);

            double ylo = StdRandom.uniformDouble(min, max);
            double yhi = StdRandom.uniformDouble(min, max);
            if (ylo > yhi) {
                double temp = ylo;
                ylo = yhi;
                yhi = temp;
            }
            Interval1D yIntervals = new Interval1D(ylo, yhi);

            point1[i] = new Point2D(xlo, ylo);
            point2[i] = new Point2D(xhi, yhi);
            interval2Ds[i] = new Interval2D(xIntervals, yIntervals);
        }

        for (Interval2D interval : interval2Ds) {
            interval.draw();
        }

        int intersectCount = 0;
        int containsCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (interval2Ds[i].intersects(interval2Ds[j])) {
                    intersectCount++;
                }
                if ((interval2Ds[i].contains(point1[j]) && interval2Ds[i].contains(point2[j]))
                        || (interval2Ds[j].contains(point1[i]) && interval2Ds[j].contains(point2[i]))) {
                    containsCount++;
                }
            }
        }
        StdOut.println("intersect count:  " + intersectCount);
        StdOut.println("contains  count:  " + containsCount);
    }

    public static void exercise1_2_6() {
        String s = StdIn.readString();
        String t = StdIn.readString();
        String doubleT = t + t;
        StdOut.println(s.length() == t.length() && doubleT.contains(s));
    }

    public static void exercise1_2_9() {
        int key = 23;
        int[] arr = new In("/exercise1/tinyT.txt").readAllInts();
        Arrays.sort(arr);
        Counter counter = new Counter("counter");
        int index = rank1_2_9(key, arr, counter);
        int step = counter.tally();
        StdOut.println("Index: " + index + " Step: " + step);
    }

    private static int rank1_2_9(int key, int[] a, Counter counter) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            counter.increment();
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    private static void exercise1_2_10() {
        int N = 100;
        int max = 50;
        VisualCounter counter = new VisualCounter(N, max);
        for (int i = 0; i < N; i++) {
            if (StdRandom.uniformInt(2) == 0) counter.increment();
            else counter.decrement();
        }
    }

    private static class VisualCounter {
        private final int N;
        private final int max;
        private int count;
        private int step;

        VisualCounter(int N, int max) {
            this.N = N;
            this.max = max;
            this.count = 0;
            this.step = 0;
            StdDraw.setXscale(0, N);
            StdDraw.setYscale(-max, max);
            StdDraw.setPenRadius(.005);
        }

        public void increment() {
            if (step == N || count == max) {
                throw new RuntimeException();
            }
            step++;
            count++;
            StdDraw.point(step, count);
        }

        public void decrement() {
            if (step == N || count == -max) {
                throw new RuntimeException();
            }
            step++;
            count--;
            StdDraw.point(step, count);
        }

    }

    public static void exercise1_2_11() {
        try {
            SmartDate smartDate = new SmartDate(2, 29, 2023);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }

    private static class SmartDate implements Comparable<SmartDate> {
        private static final int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        private static final int[] DAYS_NORMAL = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        private static final int[] DAYS_LEAP = {0, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
        private static final String[] WEEKDAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        private final int month;
        private final int day;
        private final int year;

        public SmartDate(int month, int day, int year) {
            if (!isValidDate(month, day, year)) {
                throw new IllegalArgumentException();
            }
            this.month = month;
            this.day = day;
            this.year = year;
        }

        public SmartDate(String date) {
            String[] dateInfo = date.split("/");
            if (dateInfo.length != 3) {
                throw new IllegalArgumentException();
            }
            int monthTemp = Integer.parseInt(dateInfo[0]);
            int dayTemp = Integer.parseInt(dateInfo[1]);
            int yearTemp = Integer.parseInt(dateInfo[2]);
            if (!isValidDate(monthTemp, dayTemp, yearTemp)) {
                throw new IllegalArgumentException();
            }
            this.month = monthTemp;
            this.day = dayTemp;
            this.year = yearTemp;
        }

        public int month() {
            return this.month;
        }

        public int day() {
            return this.day;
        }

        public int year() {
            return this.year;
        }

        public String dayOfTheWeek() {
            int totalDays = 0;

            for (int y = 2000; y < year; y++) {
                if (isLeapYear(y)) {
                    totalDays += 366;
                } else {
                    totalDays += 365;
                }
            }

            if (isLeapYear(year)) {
                totalDays = totalDays + DAYS_LEAP[month] + day;
            } else {
                totalDays = totalDays + DAYS_NORMAL[month] + day;
            }

            return WEEKDAYS[((totalDays - 1) % 7 + 6) % 7];
        }

        public String toString() {
            return this.month + "/" + this.day + "/" + this.year;
        }

        public boolean equals(Object that) {
            if (this == that) return true;
            if (that == null) return false;
            if (this.getClass() != that.getClass()) return false;
            if (this.month != ((SmartDate) that).month) return false;
            if (this.day != ((SmartDate) that).day) return false;
            if (this.year != ((SmartDate) that).year) return false;
            return true;
        }

        public int compareTo(SmartDate that) {
            if (this.year < that.year) return -1;
            if (this.year > that.year) return 1;
            if (this.month < that.month) return -1;
            if (this.month > that.month) return 1;
            if (this.day < that.day) return -1;
            if (this.day > that.day) return 1;
            return 0;
        }

        public int hashCode() {
            // TODO 3.4 hash code
            return super.hashCode();
        }

        private boolean isValidDate(int month, int day, int year) {
            if (month < 1 || month > 12) return false;
            if (day < 1 || day > DAYS[month]) return false;
            if (month == 2 && day == 29 && !isLeapYear(year)) return false;
            return true;
        }

        private boolean isLeapYear(int year) {
            if (year % 400 == 0) return true;
            if (year % 100 == 0) return false;
            return year % 4 == 0;
        }
    }

    public static void exercise1_2_12() {
        SmartDate smartDate = new SmartDate(2, 25, 2023);
        StdOut.println(smartDate.dayOfTheWeek());
    }

    public static void exercise1_2_13() {
        Transaction transaction = new Transaction("Alice 1/1/2023 23.45");
        StdOut.println(transaction);
    }

    private static class Transaction implements Comparable<Transaction> {
        private final String who;
        private final Date when;
        private final double amount;

        public Transaction(String who, Date when, double amount) {
            this.who = who;
            this.when = when;
            this.amount = amount;
        }

        public Transaction(String transaction) {
            String[] transactionInfo = transaction.split("\\s+");
            this.who = transactionInfo[0];
            this.when = new Date(transactionInfo[1]);
            this.amount = Double.parseDouble(transactionInfo[2]);
        }

        public String who() {
            return this.who;
        }

        public Date when() {
            return this.when;
        }

        public double amount() {
            return this.amount;
        }

        @Override
        public String toString() {
            return String.format("%-10s %10s %8.2f", who, when, amount);
        }

        public boolean equals(Object that) {
            if (that == this) return true;
            if (that == null) return false;
            if (that.getClass() != this.getClass()) return false;
            return (this.amount == ((Transaction) that).amount)
                    && (this.who.equals(((Transaction) that).who))
                    && (this.when.equals(((Transaction) that).when));
        }

        public int compareTo(Transaction that) {
            if (this.amount < that.amount) return -1;
            else if (this.amount > that.amount) return +1;
            else return 0;
        }

        public int hashCode() {
            // TODO 3.4 hash code
            return super.hashCode();
        }
    }

    public static void exercise1_2_14() {
        Transaction t1 = new Transaction("Alice 1/1/2023 23.56");
        Transaction t2 = new Transaction("Alice 1/1/2023 23.56");
        Transaction t3 = new Transaction("David 3/3/2023 11.11");
        StdOut.println(t1.equals(t2));
        StdOut.println(t1.equals(t3));
    }

    public static void exercise1_2_15() {
        String name = "/exercise1/tinyT.txt";
        int[] ints = readInts(name);
        for (int i = 0; i < ints.length; i++) {
            StdOut.print(ints[i] + " ");
        }
    }

    private static int[] readInts(String name) {
        In in = new In(name);
        String input = in.readAll();
        String[] words = input.split("\\s+");
        int[] ints = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            ints[i] = Integer.parseInt(words[i]);
        }
        return ints;
    }

    public static void exercise1_2_16() {
        Rational a = new Rational(2, 3);
        Rational b = new Rational(5, 6);
        Rational plus = a.plus(b);
        Rational minus = a.minus(b);
        Rational times = a.times(b);
        Rational divides = a.divides(b);
        StdOut.println("plus:    " + plus);
        StdOut.println("minus:   " + minus);
        StdOut.println("times:   " + times);
        StdOut.println("divides: " + divides);
    }

    private static class Rational {
        private final long numerator;
        private final long denominator;

        public Rational(int numerator, int denominator) {
            if (denominator == 0) throw new IllegalArgumentException();
            int g = Euclid(numerator, denominator);
            numerator /= g;
            denominator /= g;
            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Rational plus(Rational b) {
            long numerator = this.numerator * b.denominator + this.denominator * b.numerator;
            long denominator = this.denominator * b.denominator;
            assert Integer.MIN_VALUE < numerator && numerator < Integer.MAX_VALUE;
            assert Integer.MIN_VALUE < denominator && denominator < Integer.MAX_VALUE;
            return new Rational((int) numerator, (int) denominator);
        }

        public Rational minus(Rational b) {
            long numerator = this.numerator * b.denominator - this.denominator * b.numerator;
            long denominator = this.denominator * b.denominator;
            assert Integer.MIN_VALUE < numerator && numerator < Integer.MAX_VALUE;
            assert Integer.MIN_VALUE < denominator && denominator < Integer.MAX_VALUE;
            return new Rational((int) numerator, (int) denominator);
        }

        public Rational times(Rational b) {
            long numerator = this.numerator * b.numerator;
            long denominator = this.denominator * b.denominator;
            assert Integer.MIN_VALUE < numerator && numerator < Integer.MAX_VALUE;
            assert Integer.MIN_VALUE < denominator && denominator < Integer.MAX_VALUE;
            return new Rational((int) numerator, (int) denominator);
        }

        public Rational divides(Rational b) {
            return this.times(b.reciprocal());
        }

        public boolean equals(Rational that) {
            if (that == null) return false;
            return this.numerator == that.numerator && this.denominator == that.denominator;
        }

        public String toString() {
            if (denominator == 1) return String.valueOf(numerator);
            else return numerator + "/" + denominator;
        }

        private Rational reciprocal() {
            return new Rational((int) this.denominator, (int) this.numerator);
        }

        private int Euclid(int p, int q) {
            if (q == 0) return p;
            int r = p % q;
            return Euclid(q, r);
        }
    }

    public static void main(String[] args) {
//        exercise1_2_1();

//        exercise1_2_2();

//        exercise1_2_3();

//        exercise1_2_6();

//        exercise1_2_9();

//        exercise1_2_10();

//        exercise1_2_11();

//        exercise1_2_12();

//        exercise1_2_13();

//        exercise1_2_14();

//        exercise1_2_15();

        exercise1_2_16();
        
    }
}
