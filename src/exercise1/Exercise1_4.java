package exercise1;

import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class Exercise1_4 {
    public static void exercise1_4_2() {
        In in = new In("algs4-data/1Kints.txt");
        int[] array = in.readAllInts();
        int n = array.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    long twoSum = array[i] + array[j];
                    if (twoSum == -array[k]) count++;
                }
            }
        }
        StdOut.println("Count=" + count);
    }

    public static void exercise1_4_8() {
        In in = new In("algs4-data/tinyT.txt");
        int[] array = in.readAllInts();
        Arrays.sort(array);
        int count = 0;
        for (int left = 0, right = 1; right < array.length; right++) {
            if (array[left] == array[right]) {
                count++;
            } else {
                left = right;
            }
        }
        StdOut.println("Count=" + count);
    }

    public static void exercise1_4_10() {
        int[] array = new int[]{1, 1, 2, 2, 3, 3, 3, 4, 5, 5, 6, 7, 7};
        StdOut.println(rank(array, 1));
    }

    private static int rank(int[] array, int key) {
        return rank(array, key, 0, array.length - 1);
    }

    private static int rank(int[] array, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key <= array[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        if (lo == array.length) return -1;
        return array[lo] == key ? lo : -1;
    }

    public static void exercise1_4_11() {
        int[] array = new int[]{1, 1, 2, 2, 3, 3, 3, 4, 5, 5, 6, 7, 7};
        StaticSETofInts set = new StaticSETofInts(array);
        StdOut.println(set.howMany(3));
    }

    private static class StaticSETofInts {
        private final int[] a;

        public StaticSETofInts(int[] keys) {
            a = new int[keys.length];
            // defensive copy
            System.arraycopy(keys, 0, a, 0, keys.length);
            Arrays.sort(a);
        }

        public boolean contains(int key) {
            return rank(key) != -1;
        }

        public int howMany(int key) {
            int low = rankLow(key);
            int high = rankHigh(key);
            if (low == -1 || high == -1) return -1;
            return high - low + 1;
        }

        private int rankLow(int key) {
            int lo = 0;
            int hi = a.length - 1;
            while (lo <= hi) {
                int mid = lo + ((hi - lo) >> 1);
                if (key <= a[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            if (lo == a.length) return -1;
            return a[lo] == key ? lo : -1;
        }

        private int rankHigh(int key) {
            int lo = 0;
            int hi = a.length - 1;
            while (lo <= hi) {
                int mid = lo + ((hi - lo) >> 1);
                if (key >= a[mid]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            if (hi == -1) return -1;
            return a[hi] == key ? hi : -1;
        }

        private int rank(int key) {  // Binary search.
            int lo = 0;
            int hi = a.length - 1;
            while (lo <= hi) {  // Key is in a[lo..hi] or not present.
                int mid = lo + (hi - lo) / 2;
                if (key < a[mid]) hi = mid - 1;
                else if (key > a[mid]) lo = mid + 1;
                else return mid;
            }
            return -1;
        }
    }

    private static void exercise1_4_12() {
        int[] array1 = new int[]{0, 2, 3, 5, 6, 7};
        int[] array2 = new int[]{2, 4, 5, 8, 8, 9};
        exercise1_4_12(array1, array2, 6);
    }

    private static void exercise1_4_12(int[] array1, int[] array2, int N) {
        assert array1 != null && array2 != null;
        int index1 = 0;
        int index2 = 0;
        while (index1 < N && index2 < N) {
            if (array1[index1] == array2[index2]) {
                StdOut.print(array1[index1] + " ");
                index1++;
                index2++;
            } else if (array1[index1] < array2[index2]) {
                index1++;
            } else {
                index2++;
            }
        }
    }

    public static void exercise1_4_14() {
        In in = new In("algs4-data/1Kints.txt");
        int[] array = in.readAllInts();
        Arrays.sort(array);
        int count = k_sum_count(array, 0, array.length, 4, 0);
        StdOut.println(count);
    }

    // 在 array 的 [lo, hi) 范围内，k 个数之和为 key 的总数量。 20866
    private static int k_sum_count(int[] array, int lo, int hi, int k, int key) {
        assert k >= 2;
        int count = 0;
        if (k == 2) {
            for (int i = lo; i < hi; i++) {
                count += rank_count(array, i + 1, hi - 1, key - array[i]);
            }
            return count;
        }
        for (int i = lo; i < hi; i++) {
            count += k_sum_count(array, i + 1, hi, k - 1, key - array[i]);
        }
        return count;
    }

    // 在 array 的 [lo, hi] 范围，key 的个数。
    private static int rank_count(int[] array, int lo, int hi, int key) {
        int left = rankLeft(array, lo, hi, key);
        int right = rankRight(array, lo, hi, key);
        if (left == -1 || right == -1) {
            return 0;
        }
        return right - left + 1;
    }

    private static int rankLeft(int[] array, int lo, int hi, int key) {
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key <= array[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        if (lo == array.length) return -1;
        return key == array[lo] ? lo : -1;
    }

    private static int rankRight(int[] array, int lo, int hi, int key) {
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key >= array[mid]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        if (hi == 0) return -1;
        return key == array[hi] ? hi : -1;
    }

    public static void exercise1_4_15() {
        In in = new In("algs4-data/1Kints.txt");
        int[] array = in.readAllInts();
        Arrays.sort(array);
        StdOut.println(TwoSumFaster(array));
        StdOut.println(ThreeSumOn2(array));
    }

    private static int TwoSumFaster(int[] array) {
        int lo = 0;
        int hi = array.length - 1;
        int count = 0;
        while (array[lo] < 0 || array[hi] > 0) {
            if (array[lo] + array[hi] < 0) {
                lo++;
            } else if (array[lo] + array[hi] > 0) {
                hi--;
            } else {
                int negative = 1;
                while (array[lo] == array[lo + 1]) {
                    lo++;
                    negative++;
                }
                int positive = 1;
                while (array[hi] == array[hi - 1]) {
                    hi--;
                    positive++;
                }
                lo++;
                hi--;
                count += negative * positive;
            }
        }
        if (lo < hi) {
            count += combination(hi - lo + 1, 2);
        }
        return count;
    }

    private static int combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combination(n - 1, k) + combination(n - 1, k - 1);
    }

    private static int ThreeSumOn2(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            int target = -array[i];

            int lo = i + 1;
            int hi = array.length - 1;
            while (lo <= hi) {
                if (array[lo] + array[hi] < target) {
                    lo++;
                } else if (array[lo] + array[hi] > target) {
                    hi--;
                } else {
                    if (array[lo] == array[hi]) break;
                    int lowCount = 1;
                    while (array[lo] == array[lo + 1]) {
                        lo++;
                        lowCount++;
                    }
                    int highCount = 1;
                    while (array[hi] == array[hi - 1]) {
                        hi--;
                        highCount++;
                    }
                    lo++;
                    hi--;
                    count += lowCount * highCount;
                }
            }
            if (lo < hi) {
                count += combination(hi - lo + 1, 2);
            }
        }
        return count;
    }

    public static void exercise1_4_16() {
        In in = new In("algs4-data/1Kints.txt");
        int[] array = in.readAllInts();
        Arrays.sort(array);
        int minDifference = Integer.MAX_VALUE;
        int pairIndex = -1;
        for (int i = 0; i < array.length - 1; i++) {
            int difference = array[i + 1] - array[i];
            if (difference < minDifference) {
                minDifference = difference;
                pairIndex = i;
            }
        }
        if (pairIndex == -1) {
            StdOut.println(-1);
        } else {
            StdOut.println(array[pairIndex] + " " + array[pairIndex + 1]);
        }
    }

    public static void exercise1_4_17() {
        In in = new In("algs4-data/1Kints.txt");
        int[] array = in.readAllInts();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            min = Math.min(min, array[i]);
            max = Math.max(max, array[i]);
        }
        StdOut.println(min + " " + max);
    }

    public static void exercise1_4_18() {
        In in = new In("algs4-data/1Kints.txt");
        int[] array = in.readAllInts();
        int index = partialMin(array);
        if (index == -1) StdOut.println("No partial min value.");
        StdOut.println(array[index - 1] + " " + array[index] + " " + array[index + 1]);
    }

    private static int partialMin(int[] array) {
        assert array != null && array.length > 0;
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (mid == 0 || mid == array.length - 1) break;
            if (array[mid] < array[mid - 1] && array[mid] < array[mid + 1]) {
                return mid;
            } else if (array[mid - 1] <= array[mid + 1]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    public static void exercise1_4_19() {
        // TODO
    }

    public static void exercise1_4_20() {
        int[] array = new int[]{1, 3, 5, 7, 9, 8, 6, 4, 2, 0};
        int key = StdIn.readInt();
        int index = bitonicSearch(array, key);
        StdOut.println(index);
    }

    private static int bitonicSearch(int[] array, int key) {
        assert array != null && array.length > 0;
        if (key < array[0] && key < array[array.length - 1]) return -1;
        int maxIndex = partialMax(array);
        if (maxIndex == -1 || key > array[maxIndex]) return -1;
        int l = rank(array, key, 0, maxIndex);
        int r = bottomRank(array, key, maxIndex, array.length - 1);
        if (l != -1) return l;
        if (r != -1) return r;
        return -1;
    }

    private static int partialMax(int[] array) {
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (mid == 0 || mid == array.length - 1) break;
            if (array[mid] > array[mid - 1] && array[mid] > array[mid + 1]) {
                return mid;
            } else if (array[mid - 1] > array[mid + 1]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    private static int bottomRank(int[] array, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key < array[mid]) {
                lo = mid + 1;
            } else if (key > array[mid]) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void exercise1_4_22() {
        In in = new In("algs4-data/1Kints.txt");
        int[] array = in.readAllInts();
        Arrays.sort(array);
        StdOut.println(BinarySearchFibonacci(array, 791849));
    }

    private static int BinarySearchFibonacci(int[] array, int key) {
        assert array != null && array.length > 0;
        int lo = 0;
        int hi = array.length - 1;

        int FibonacciLo = 0;
        int FibonacciHi = 1;
        while (FibonacciHi <= hi) {
            int temp = FibonacciLo;
            FibonacciLo = FibonacciHi;
            FibonacciHi += temp;
        }

        while (lo <= hi) {
            while (FibonacciLo > 0 && FibonacciHi >= hi - lo) {
                int temp = FibonacciLo;
                FibonacciLo = FibonacciHi - FibonacciLo;
                FibonacciHi = temp;
            }

            int mid = lo + FibonacciLo;
            if (key < array[mid]) {
                hi = mid - 1;
            } else if (key > array[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void exercise1_4_23() {
        // TODO
    }

    public static void exercise1_4_24() {
        int[] floors = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
        int F = throwEggLgN(floors);
        StdOut.println(F);

        F = throwEggLgF(floors);
        StdOut.println(F);
    }

    private static int throwEggLgN(int[] floors) {
        assert floors != null && floors.length > 0;
        int key = 1;
        int lo = 0;
        int hi = floors.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key != floors[mid]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        if (lo == floors.length) return -1;
        return key == floors[lo] ? lo : -1;
    }

    private static int throwEggLgF(int[] floors) {
        int key = 1;
        int lo = 0;
        int hi = 1;
        while (hi < floors.length && floors[hi] != key) {
            int temp = lo;
            lo = hi;
            hi = temp + hi;
        }

        if (hi >= floors.length) {
            hi = floors.length - 1;
        }

        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key != floors[mid]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        if (lo == floors.length) return -1;
        return key == floors[lo] ? lo : -1;
    }

    public static void exercise1_4_25() {
        int[] floors = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1};
        int F = throw2EggsN(floors);
        StdOut.println(F);

        F = throw2EggsF(floors);
        StdOut.println(F);
    }

    private static int throw2EggsN(int[] floors) {
        assert floors != null && floors.length > 0;
        int lo = 0;
        int hi = floors.length - 1;
        int mid = 0;
        int key = 1;

        while (lo <= hi) {
            mid = lo + ((hi - lo) >> 1);
            if (key == floors[mid]) {
                break;
            } else {
                lo = mid + 1;
            }
        }

        while (lo < mid && floors[lo] != key) {
            lo++;
        }

        if (lo == floors.length || floors[lo] != key) return -1;
        return lo;
    }

    private static int throw2EggsF(int[] floors) {
        assert floors != null && floors.length > 0;
        int firstEgg = (int) Math.sqrt(floors.length);
        int secondEgg = 0;
        int key = 1;

        while (firstEgg <= floors.length - 1) {
            if (floors[firstEgg] == key) {
                break;
            }
            secondEgg = firstEgg;
            firstEgg += firstEgg;
        }

        if (firstEgg >= floors.length) firstEgg = floors.length - 1;

        while (secondEgg < firstEgg) {
            if (floors[secondEgg] == key) break;
            secondEgg++;
        }

        if (secondEgg == floors.length || floors[secondEgg] != key) return -1;
        return secondEgg;
    }

    public static void exercise1_4_27() {
        QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i < 3; i++) {
            StdOut.print(queue.dequeue() + " ");
        }

        for (int i = 5; i < 10; i++) {
            queue.enqueue(i);
        }

        while (!queue.isEmpty()) {
            StdOut.print(queue.dequeue() + " ");
        }
    }

    private static class QueueWithTwoStacks<Item> {
        private final Stack<Item> stack1;
        private final Stack<Item> stack2;

        QueueWithTwoStacks() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void enqueue(Item item) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            stack1.push(item);
        }

        public Item dequeue() {
            if (isEmpty()) return null;
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        public boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

    public static void exercise1_4_28() {
        StackWithAQueue<Integer> stack = new StackWithAQueue<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }

        for (int i = 0; i < 3; i++) {
            StdOut.print(stack.pop() + " ");
        }

        for (int i = 5; i < 10; i++) {
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            StdOut.print(stack.pop() + " ");
        }
    }

    private static class StackWithAQueue<Item> {
        private final Queue<Item> queue = new Queue<>();

        public void push(Item item) {
            queue.enqueue(item);
        }

        public Item pop() {
            if (isEmpty()) return null;
            int size = queue.size();
            while (size > 1) {
                queue.enqueue(queue.dequeue());
                size--;
            }
            return queue.dequeue();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static void exercise1_4_29() {
        StequeWithTwoStacks<Integer> steque = new StequeWithTwoStacks<>();
        for (int i = 0; i < 5; i++) {
            steque.push(i);
        }

        for (int i = 5; i < 10; i++) {
            steque.enqueue(i);
        }

        while (!steque.isEmpty()) {
            StdOut.print(steque.pop() + " ");
        }
    }

    private static class StequeWithTwoStacks<Item> {
        private final Stack<Item> stack1 = new Stack<>();
        private final Stack<Item> stack2 = new Stack<>();

        public void push(Item item) {
            stack1.push(item);
        }

        public Item pop() {
            if (isEmpty()) return null;
            if (stack1.isEmpty()) {
                while (!stack2.isEmpty()) {
                    stack1.push(stack2.pop());
                }
            }
            return stack1.pop();
        }

        public void enqueue(Item item) {
            stack2.push(item);
        }

        public boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

    public static void exercise1_4_34() {
        int secret = 5;
        int N = 30;
        int answer = HotOrCold(secret, N);
        StdOut.println(answer == secret);
    }

    private static int HotOrCold(int secret, int N) {
        assert N > 0;
        int lo = 1;
        int hi = N;
        int loDistance = Math.abs(secret - lo);
        int hiDistance = Math.abs(secret - hi);
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (loDistance < hiDistance) {
                hi = mid - 1;
                hiDistance = Math.abs(secret - hi);
            } else if (loDistance > hiDistance) {
                lo = mid + 1;
                loDistance = Math.abs(secret - lo);
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void exercise1_4_38() {
        for (int N = 250; true; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%7d %5.1f\n", N, time);
        }
    }

    private static double timeTrial(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniformInt(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        int cnt = native3sum(a);
        return timer.elapsedTime();
    }

    private static int native3sum(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    if (i < j && j < k)
                        cnt++;
        return cnt;
    }

    public static void main(String[] args) {
//        exercise1_4_2();

//        exercise1_4_8();

//        exercise1_4_10();

//        exercise1_4_11();

//        exercise1_4_12();

//        exercise1_4_14();

//        exercise1_4_15();

//        exercise1_4_16();

//        exercise1_4_17();

//        exercise1_4_18();

//        exercise1_4_20();

//        exercise1_4_22();

//        exercise1_4_24();

//        exercise1_4_25();

//        exercise1_4_27();

//        exercise1_4_28();

//        exercise1_4_29();

//        exercise1_4_34();

        exercise1_4_38();
    }
}
