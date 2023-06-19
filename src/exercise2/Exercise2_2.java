package exercise2;

import edu.princeton.cs.algs4.*;

public class Exercise2_2 {

    public void exercise2_2_9() {
        Double[] doubles = RandomGenerator.generateRandomDoubles(40);
        MergeAux.sort(doubles);
        StdOut.println(SortUtil.isSorted(doubles));
    }

    public void exercise2_2_10() {
        Double[] doubles = RandomGenerator.generateRandomDoubles(200);
        FasterMerge.sort(doubles);
        StdOut.println(SortUtil.isSorted(doubles));
    }

    public void exercise2_2_11() {

    }

    public void exercise2_2_12() {

    }

    public void exercise2_2_14() {
        Queue<Comparable> queue1 = new Queue<>();
        Queue<Comparable> queue2 = new Queue<>();
        for (int i = 0; i < 10; i += 2) {
            queue1.enqueue(Integer.valueOf(i));
        }
        for (int i = 1; i <= 10; i += 2) {
            queue2.enqueue(Integer.valueOf(i));
        }
        Queue<Comparable> queue = mergeQueue(queue1, queue2);
        while (!queue.isEmpty()) {
            StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println();

    }

    private Queue<Comparable> mergeQueue(Queue<Comparable> queue1, Queue<Comparable> queue2) {
        Queue<Comparable> queue = new Queue<>();
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            if (queue1.isEmpty()) queue.enqueue(queue2.dequeue());
            else if (queue2.isEmpty()) queue.enqueue(queue1.dequeue());
            else if (SortUtil.less(queue1.peek(), queue2.peek())) queue.enqueue(queue1.dequeue());
            else queue.enqueue(queue2.dequeue());
        }
        return queue;
    }

    public void exercise2_2_15() {

    }

    public void exercise2_2_16() {
        Double[] doubles = RandomGenerator.generateRandomDoubles(100);
        NaturalMerge.sort(doubles);
        StdOut.println(SortUtil.isSorted(doubles));
    }

    public void exercise2_2_17() {
        LinkedListNatureMerge.LinkedList<Comparable> linkedList = new LinkedListNatureMerge.LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            Integer integer = StdRandom.uniformInt(-200, 200);
            linkedList.insert(integer);
        }
        LinkedListNatureMerge.sort(linkedList);
        StdOut.println(LinkedListNatureMerge.isSorted(linkedList));
    }

    public void exercise2_2_18() {
        LinkedListNatureMerge.LinkedList<Comparable> linkedList = new LinkedListNatureMerge.LinkedList<>();
        for (int i = 1000; i >= 0; i--) {
            Integer integer = Integer.valueOf(i);
            linkedList.insert(integer);
        }
        StdOut.println(LinkedListNatureMerge.isSorted(linkedList));
        LinkedListNatureMerge.shuffle(linkedList);
        while (linkedList.size() != 0) {
            StdOut.print(linkedList.remove() + " ");
        }
        StdOut.println();
    }

    public void exercise2_2_19() {
        Double[] doubles = RandomGenerator.generateRandomDoubles(100);
        int inversions = MergeInversions.count(doubles);
        StdOut.println(SortUtil.isSorted(doubles));
        StdOut.println(inversions);
    }

    public void exercise2_2_20() {
        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++) {
            Integer integer = StdRandom.uniformInt(-99, 100);
            a[i] = integer;
        }

        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + "\t");
        }
        StdOut.println();

        int[] perms = IndirectMergeSort.indexSort(a);

        for (int i = 0; i < perms.length; i++) {
            StdOut.print(perms[i] + "\t");
        }
        StdOut.println();
    }

    public void exercise2_2_22() {
        Integer[] a = RandomGenerator.generateRandomInteger(100000);
        ThreeWayMergeSort.sort(a);
        StdOut.println(SortUtil.isSorted(a));
    }

    public static void main(String[] args) {
        Exercise2_2 runner = new Exercise2_2();

//        runner.exercise2_2_9();

//        runner.exercise2_2_10();

//        runner.exercise2_2_14();

//        runner.exercise2_2_16();

//        runner.exercise2_2_17();

//        runner.exercise2_2_18();

//        runner.exercise2_2_19();

//        runner.exercise2_2_20();

        runner.exercise2_2_22();
    }
}

class MergeAux {

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + ((hi - lo) >> 1);
        sort(a, aux, 0, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

class FasterMerge {

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + ((hi - lo) >> 1);
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }
        for (int k = mid + 1; k <= hi; k++) {
            aux[k] = a[hi - k + mid + 1];
        }
        int i = lo, j = hi;
        for (int k = lo; k <= hi; k++) {
            if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j--];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

class NaturalMerge {
    public static void sort(Comparable[] a) {
        if (a == null || a.length < 2) return;
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        while (true) {
            int l = lo;
            int m = findAscIndex(a, l, hi);
            if (m == hi) break;

            while (m != hi) {
                int r = findAscIndex(a, m + 1, hi);
                merge(a, aux, l, m, r);
                l = r + 1;
                m = findAscIndex(a, l, hi);
            }
        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }
        for (int k = mid + 1; k <= hi; k++) {
            aux[k] = a[hi - k + mid + 1];
        }
        int i = lo, j = hi;
        for (int k = lo; k <= hi; k++) {
            if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j--];
        }
    }

    private static int findAscIndex(Comparable[] a, int lo, int hi) {
        lo++;
        while (lo <= hi) {
            if (less(a[lo], a[lo - 1])) return lo - 1;
            lo++;
        }
        return hi;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

class LinkedListNatureMerge {

    public static void sort(LinkedList<Comparable> a) {
        if (a == null || a.size() < 2) return;

        LinkedList.Node sentinel = a.new Node(null, a.first);
        while (true) {
            LinkedList.Node prev = sentinel;
            LinkedList.Node lo = prev.next;
            LinkedList.Node mid = findAscIndex(lo, a.last);
            if (mid == a.last) break;

            while (mid != a.last) {
                LinkedList.Node hi = findAscIndex(mid.next, a.last);

                prev = merge(prev, lo, mid, hi);
                if (a.last.next != null) a.last = prev;     // 维护链表 last 指针

                lo = prev.next;
                mid = findAscIndex(lo, a.last);
            }
        }
        a.first = sentinel.next;        // 维护链表 first 指针
    }

    private static LinkedList.Node merge(LinkedList.Node prev, LinkedList.Node lo, LinkedList.Node mid, LinkedList.Node hi) {
        LinkedList.Node next = hi.next;

        LinkedList.Node p1 = lo;
        LinkedList.Node p2 = mid.next;

        mid.next = null;
        hi.next = null;

        while (p1 != null && p2 != null) {
            if (less(p1.item, p2.item)) {
                prev.next = p1;
                p1 = p1.next;
            } else {
                prev.next = p2;
                p2 = p2.next;
            }
            prev = prev.next;
        }

        if (p1 == null) {
            prev.next = p2;
        } else {
            prev.next = p1;
        }

        while (prev.next != null) {
            prev = prev.next;
        }
        prev.next = next;
        return prev;
    }

    private static LinkedList.Node findAscIndex(LinkedList.Node node, LinkedList.Node last) {
        if (node == null) return last;
        while (node.next != null) {
            if (less(node.next.item, node.item)) return node;
            node = node.next;
        }
        return last;
    }

    public static boolean isSorted(LinkedList<Comparable> a) {
        LinkedList.Node pointer = a.first;
        while (pointer.next != null) {
            if (less(pointer.next.item, pointer.item)) return false;
            pointer = pointer.next;
        }
        return true;
    }

    public static void shuffle(LinkedList<Comparable> a) {
        if (a == null || a.size() < 2) return;

        LinkedList.Node sentinel = a.new Node(null, a.first);
        int block = 1;
        while (block < a.size()) {
            LinkedList.Node prev = sentinel;
            LinkedList.Node lo = prev.next;
            LinkedList.Node mid = findShuffleIndex(prev, block, a.last);

            while (mid != a.last) {
                LinkedList.Node hi = findShuffleIndex(mid, block, a.last);

                prev = shuffleMerge(prev, lo, mid, hi);
                if (a.last.next != null) a.last = prev;

                lo = prev.next;
                mid = findShuffleIndex(prev, block, a.last);
            }
            a.first = sentinel.next;
            block *= 2;
        }
    }

    private static LinkedList.Node shuffleMerge(LinkedList.Node prev, LinkedList.Node lo, LinkedList.Node mid, LinkedList.Node hi) {
        LinkedList.Node next = hi.next;

        LinkedList.Node p1 = lo;
        LinkedList.Node p2 = mid.next;

        mid.next = null;
        hi.next = null;

        while (p1 != null && p2 != null) {
            double random = StdRandom.uniformDouble();
            if (random < 0.5) {
                prev.next = p1;
                p1 = p1.next;
            } else {
                prev.next = p2;
                p2 = p2.next;
            }
            prev = prev.next;
        }

        if (p1 == null) {
            prev.next = p2;
        } else {
            prev.next = p1;
        }

        while (prev.next != null) {
            prev = prev.next;
        }
        prev.next = next;
        return prev;
    }

    private static LinkedList.Node findShuffleIndex(LinkedList.Node node, int block, LinkedList.Node last) {
        if (node == null) return last;
        while (node.next != null && block != 0) {
            node = node.next;
            block--;
        }
        return node;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    static class LinkedList<Item extends Comparable> {
        Node first;
        Node last;
        private int size;

        LinkedList() {
            first = last = null;
            size = 0;
        }

        public void insert(Item item) {
            if (first == null) {
                first = last = new Node(item, null);
            } else {
                first = new Node(item, first);
            }
            size++;
        }

        public Item remove() {
            if (first == null) return null;
            Node temp = first;
            if (first == last) {
                first = last = null;
            } else {
                first = first.next;
            }
            size--;
            return temp.item;
        }

        public int size() {
            return size;
        }

        class Node {
            Item item;
            Node next;

            Node(Item item, Node next) {
                this.item = item;
                this.next = next;
            }
        }
    }
}

class MergeInversions {

    public static int count(Comparable[] a) {
        if (a == null || a.length < 2) return 0;
        Comparable[] aux = new Comparable[a.length];
        return count(a, aux, 0, a.length - 1);
    }

    private static int count(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return 0;
        int inversions = 0;
        int mid = lo + ((hi - lo) >> 1);
        inversions += count(a, aux, lo, mid);
        inversions += count(a, aux, mid + 1, hi);
        inversions += merge(a, aux, lo, mid, hi);
        return inversions;
    }

    private static int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int inversions = 0;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (SortUtil.less(aux[j], aux[i])) {
                a[k] = aux[j++];
                inversions++;
            } else a[k] = aux[i++];
        }
        return inversions;
    }
}

class IndirectMergeSort {

    public static int[] indexSort(Comparable[] a) {
        int[] index = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            index[i] = i;
        }

        int[] aux = new int[a.length];
        sort(a, aux, index, 0, a.length - 1);
        return index;
    }

    private static void sort(Comparable[] a, int[] aux, int[] index, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + ((hi - lo) >> 1);
        sort(a, aux, index, lo, mid);
        sort(a, aux, index, mid + 1, hi);
        merge(a, aux, index, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int[] aux, int[] index, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = index[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) index[k] = aux[j++];
            else if (j > hi) index[k] = aux[i++];
            else if (SortUtil.less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
            else index[k] = aux[i++];
        }
    }
}

class ThreeWayMergeSort {
    public static void sort(Comparable[] a) {
        if (a == null || a.length < 2) return;
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int midl = lo + (hi - lo) / 3;
        int midr = midl + (hi - lo) / 3;
        sort(a, aux, lo, midl);
        sort(a, aux, midl + 1, midr);
        sort(a, aux, midr + 1, hi);
        merge(a, aux, lo, midl, midr, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int midl, int midr, int hi) {
        for (int l = lo; l <= hi; l++) {
            aux[l] = a[l];
        }

        int i = lo, j = midl + 1, k = midr + 1;
        for (int l = lo; l <= hi; l++) {
            int flag = 0x111;
            if (i > midl) flag -= 0x100;
            if (j > midr) flag -= 0x010;
            if (k > hi) flag -= 0x001;

            switch (flag) {
                case 0x111 -> {
                    Comparable min = min(aux[i], aux[j], aux[k]);
                    if (min.equals(aux[i])) a[l] = aux[i++];
                    else if (min.equals(aux[j])) a[l] = aux[j++];
                    else a[l] = aux[k++];
                }
                case 0x110 -> {
                    if (SortUtil.less(aux[j], aux[i])) a[l] = aux[j++];
                    else a[l] = aux[i++];
                }
                case 0x101 -> {
                    if (SortUtil.less(aux[k], aux[i])) a[l] = aux[k++];
                    else a[l] = aux[i++];
                }
                case 0x011 -> {
                    if (SortUtil.less(aux[k], aux[j])) a[l] = aux[k++];
                    else a[l] = aux[j++];
                }
                case 0x100 -> a[l] = aux[i++];
                case 0x010 -> a[l] = aux[j++];
                case 0x001 -> a[l] = aux[k++];
            }
        }
    }

    private static Comparable min(Comparable a, Comparable b, Comparable c) {
        Comparable min = a;
        if (SortUtil.less(b, min)) min = b;
        if (SortUtil.less(c, min)) min = c;
        return min;
    }
}
