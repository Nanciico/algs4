package exercise1;

import edu.princeton.cs.algs4.*;

import java.io.File;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public class Exercise1_3 {

    public void exercise1_3_1() {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(2);
        stack.push("a");
        StdOut.println(stack.isFull());
        stack.push("b");
        StdOut.println(stack.isFull());
    }

    public void exercise1_3_4() {
        char[] inputs = StdIn.readAll().toCharArray();
        StdOut.println(Parentheses(inputs));
    }

    private boolean Parentheses(char[] arr) {
        Stack<Character> stack = new Stack<>();
        for (char c : arr) {
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char pop = stack.pop();
                if (!((pop == '[' && c == ']') || (pop == '{' && c == '}') || (pop == '(' && c == ')'))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public void exercise1_3_9() {
        Stack<String> valStack = new Stack<>();
        Stack<String> opStack = new Stack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString().toLowerCase();
            switch (s) {
                case "(" -> {
                    continue;
                }
                case "+", "-", "*", "/", "sqrt" -> opStack.push(s);
                case ")" -> {
                    String right = valStack.pop();
                    String op = opStack.pop();
                    String left = valStack.pop();
                    valStack.push("( " + left + " " + op + " " + right + " )");
                }
                default -> valStack.push(s);
            }
        }
        StdOut.println(valStack.pop());
    }

    public void exercise1_3_10() {
        String[] infix = StdIn.readLine().toLowerCase().split("\\s+");
        StdOut.println(InfixToPostfix(infix));
    }

    private String InfixToPostfix(String[] infix) {
        Stack<String> valStack = new Stack<>();
        Stack<String> opStack = new Stack<>();
        for (String s : infix) {
            switch (s) {
                case "(" -> {
                    continue;
                }
                case "+", "-", "*", "/", "sqrt" -> opStack.push(s);
                case ")" -> {
                    String right = valStack.pop();
                    String op = opStack.pop();
                    String left = valStack.pop();
                    valStack.push(left + " " + right + " " + op);
                }
                default -> valStack.push(s);
            }
        }
        return valStack.pop();
    }

    public void exercise1_3_11() {
        String[] postfix = StdIn.readLine().toLowerCase().split("\\s+");
        StdOut.println(EvaluatePostfix(postfix));
    }

    private double EvaluatePostfix(String[] postfix) {
        Stack<Double> valStack = new Stack<>();
        for (String s : postfix) {
            switch (s) {
                case "+" -> {
                    double right = valStack.pop();
                    double left = valStack.pop();
                    valStack.push(left + right);
                }
                case "-" -> {
                    double right = valStack.pop();
                    double left = valStack.pop();
                    valStack.push(left - right);
                }
                case "*" -> {
                    double right = valStack.pop();
                    double left = valStack.pop();
                    valStack.push(left * right);
                }
                case "/" -> {
                    double right = valStack.pop();
                    double left = valStack.pop();
                    valStack.push(left / right);
                }
                case "sqrt" -> valStack.push(Math.sqrt(valStack.pop()));
                default -> valStack.push(Double.parseDouble(s));
            }
        }
        return valStack.pop();
    }

    public void exercise1_3_12() {
        Stack1_3_12 stack = new Stack1_3_12();
        stack.push("aaa");
        stack.push("bbb");
        stack.push("ccc");
        Stack1_3_12 copyStack = Stack1_3_12.copy(stack);
        Iterator<String> iterator = copyStack.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
    }

    public void exercise1_3_14() {
        ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(String.valueOf(i));
        }
        StdOut.println(queue);
        for (int i = 0; i < 9; i++) {
            queue.dequeue();
        }
        StdOut.println(queue);
        assert Objects.equals(queue.dequeue(), "9");
    }

    public void exercise1_3_15(int k) {
        Queue<String> queue = new Queue<>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        int dequeueCount = queue.size() - k;
        for (int i = 0; i < dequeueCount; i++) {
            queue.dequeue();
        }
        StdOut.println(queue.dequeue());
    }

    public void exercise1_3_26() {
        Node<String> head = new Node<>();
        head.item = String.valueOf(0);
        Node<String> p = head;
        for (int i = 1; i <= 10; i++) {
            Node<String> node = new Node<>();
            node.item = String.valueOf(i);
            p.next = node;
            p = p.next;
        }

        remove(head, "5");

        p = head;
        while (p != null) {
            StdOut.print(p.item + " ");
            p = p.next;
        }
    }

    private void remove(Node<String> node, String key) {
        if (node == null) return;
        if (node.next == null) {
            if (Objects.equals(node.item, key)) {
                node = null;
            }
            return;
        }
        Node<String> p = node.next;
        Node<String> q = node;

        while (p != null) {
            if (Objects.equals(p.item, key)) {
                q.next = p.next;
                p = p.next;
                continue;
            }
            q = q.next;
            p = p.next;
        }
    }

    public void exercise1_3_27() {
        Node<Integer> head = new Node<>();
        head.item = 0;
        Node<Integer> p = head;
        for (int i = 1; i <= 10; i++) {
            Node<Integer> node = new Node<>();
            node.item = i;
            p.next = node;
            p = p.next;
        }
        StdOut.println(max(head));
    }

    private int max(Node<Integer> head) {
        if (head == null) return 0;
        int max = 0;
        Node<Integer> p = head;
        while (p != null) {
            if (p.item > max) {
                max = p.item;
            }
            p = p.next;
        }
        return max;
    }

    public void exercise1_3_28() {
        Node<Integer> head = new Node<>();
        head.item = 0;
        Node<Integer> p = head;
        for (int i = 1; i <= 10; i++) {
            Node<Integer> node = new Node<>();
            node.item = i;
            p.next = node;
            p = p.next;
        }
        StdOut.println(max(head));
    }

    private int maxRecursion(Node<Integer> head) {
        if (head == null) return 0;
        return Math.max(head.item, maxRecursion(head.next));
    }

    public void exercise1_3_29() {
        CircleLinkedListQueue<Integer> queue = new CircleLinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        while (!queue.isEmpty()) {
            StdOut.print(queue.dequeue() + " ");
        }
    }

    public void exercise1_3_30() {
        Node<Integer> first = new Node<>();
        first.item = 0;
        Node<Integer> p = first;
        for (int i = 1; i < 10; i++) {
            p.next = new Node<>();
            p = p.next;
            p.item = i;
        }

        Node<Integer> reverse = reverse(first);
        p = reverse;
        while (p != null) {
            StdOut.print(p.item + " ");
            p = p.next;
        }

        StdOut.println();
        p = reverseRecursion(reverse);
        while (p != null) {
            StdOut.print(p.item + " ");
            p = p.next;
        }
    }

    private <Item> Node<Item> reverse(Node<Item> first) {
        Node<Item> reverse = null;
        while (first != null) {
            Node<Item> second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        return reverse;
    }

    private <Item> Node<Item> reverseRecursion(Node<Item> first) {
        if (first == null || first.next == null) return first;
        Node<Item> second = first.next;
        Node<Item> reverse = reverseRecursion(second);
        second.next = first;
        first.next = null;
        return reverse;
    }

    public void exercise1_3_31() {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();
        doubleLinkedList.insertHead(5);
        doubleLinkedList.insertHead(4);
        doubleLinkedList.insertTail(2);
        doubleLinkedList.insertAfter(2, 3);
        doubleLinkedList.insertBefore(1, 0);

        for (DoubleLinkedList.DoubleNode integerDoubleNode : doubleLinkedList) {
            StdOut.print(integerDoubleNode.item + " ");
        }

        doubleLinkedList.removeHead();
        doubleLinkedList.removeTail();

        StdOut.println();
        for (DoubleLinkedList.DoubleNode integerDoubleNode : doubleLinkedList) {
            StdOut.print(integerDoubleNode.item + " ");
        }
    }

    public void exercise1_3_32() {
        Steque<Integer> steque = new Steque<>();
        steque.push(1);
        steque.push(2);
        steque.enqueue(3);
        while (!steque.isEmpty()) {
            StdOut.println(steque.pop());
        }
    }

    public void exercise1_3_33() {
        // Deque
        Deque<Integer> deque = new Deque<>();
        deque.pushLeft(1);
        deque.pushRight(2);
        deque.pushRight(3);
        for (Integer i : deque) {
            StdOut.print(i + " ");
        }

        deque.popLeft();
        deque.popRight();
        StdOut.println();
        for (Integer i : deque) {
            StdOut.print(i + " ");
        }

        // ResizingArrayDeque
        ResizingArrayDeque<Integer> resizingArrayDeque = new ResizingArrayDeque<>();
        for (int i = 1; i <= 5; i++) {
            resizingArrayDeque.pushLeft(i);
        }
        StdOut.println();
        for (Integer i : resizingArrayDeque) {
            StdOut.print(i + " ");
        }

        for (int i = 6; i <= 15; i++) {
            resizingArrayDeque.pushRight(i);
        }
        StdOut.println();
        for (Integer i : resizingArrayDeque) {
            StdOut.print(i + " ");
        }

        for (int i = 6; i <= 15; i++) {
            resizingArrayDeque.popLeft();
        }
        StdOut.println();
        for (Integer i : resizingArrayDeque) {
            StdOut.print(i + " ");
        }

        for (int i = 1; i <= 4; i++) {
            resizingArrayDeque.popRight();
        }
        StdOut.println();
        for (Integer i : resizingArrayDeque) {
            StdOut.print(i + " ");
        }
    }

    public void exercise1_3_34() {
        RandomBag<Integer> randomBag = new RandomBag<>();
        for (int i = 0; i < 10; i++) {
            randomBag.add(i);
        }
        for (Integer i : randomBag) {
            StdOut.print(i + " ");
        }
    }

    public void exercise1_3_35() {
        RandomQueue<Card> randomQueue = new RandomQueue<>();
        String[] suits = {"♥", "♠", "♦", "♣"};
        String[] ranks = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        for (String suit : suits) {
            for (String rank : ranks) {
                randomQueue.enqueue(new Card(suit, rank));
            }
        }

        StdOut.println("Player 1:");
        for (int i = 0; i < 13; i++) {
            StdOut.print(randomQueue.dequeue() + " ");
        }

        StdOut.println();
        StdOut.println("Player 2:");
        for (int i = 0; i < 13; i++) {
            StdOut.print(randomQueue.dequeue() + " ");
        }

        StdOut.println();
        StdOut.println("Player 3:");
        for (int i = 0; i < 13; i++) {
            StdOut.print(randomQueue.dequeue() + " ");
        }

        StdOut.println();
        StdOut.println("Player 4:");
        for (int i = 0; i < 13; i++) {
            StdOut.print(randomQueue.dequeue() + " ");
        }
    }

    private record Card(String suit, String rank) {
        @Override
        public String toString() {
            return suit + rank;
        }
    }

    public void exercise1_3_36() {
        RandomQueue<Integer> randomQueue = new RandomQueue<>();
        for (int i = 0; i < 10; i++) {
            randomQueue.enqueue(i);
        }
        for (Integer i : randomQueue) {
            StdOut.print(i + " ");
        }
    }

    public void exercise1_3_37() {
        Josephus(7, 2);
    }

    private void Josephus(int N, int M) {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < N; i++) {
            queue.enqueue(i);
        }

        int m = 0;
        while (!queue.isEmpty()) {
            int num = queue.dequeue();
            if (++m == M) {
                StdOut.print(num + " ");
                m = 0;
            } else {
                queue.enqueue(num);
            }
        }
    }

    // TODO exercise 3.5.27
    public void exercise1_3_38() {

    }

    public void exercise1_3_39() {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(10);
        Thread writer1 = new Thread(() -> {
            for (int item = 0; item < Integer.MAX_VALUE; item++) {
                ringBuffer.write(item);
            }
        });
        writer1.start();
        while (true) {
            StdOut.println(ringBuffer.read());
        }
    }

    public void exercise1_3_40() {
        MoveToFront();
    }

    private void MoveToFront() {
        MoveToFrontQueue<Character> queue = new MoveToFrontQueue<>();
        while (StdIn.hasNextChar()) {
            char c = StdIn.readChar();
            if (Character.isWhitespace(c)) {
                continue;
            }
            queue.enqueue(c);
        }
        for (char c : queue) {
            StdOut.print("(" + c + ") ");
        }
    }

    public void exercise1_3_43() {
        listFiles("/", 0);
    }

    private void listFiles(String filePath, int hierarchy) {
        File file = new File(filePath);
        for (int i = 0; i < hierarchy; i++) {
            StdOut.print("\t");
        }
        StdOut.println(file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File subFile : files) {
                listFiles(subFile.getAbsolutePath(), hierarchy + 1);
            }
        }
    }

    public void exercise1_3_44() {
        Buffer buffer = new Buffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.insert('c');
        buffer.left(2);
        char del1 = buffer.delete();
        assert del1 == 'a';
        buffer.right(2);
        char del2 = buffer.delete();
        assert del2 == 'c';
    }

    public void exercise1_3_45() {
        int size = 0;
        while (StdIn.hasNextChar()) {
            char c = StdIn.readChar();
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (c == '-') {
                if (--size < 0) {
                    StdOut.println("栈向下溢出");
                    break;
                }
            } else {
                size++;
            }
        }
    }

    public void exercise1_3_47() {
        Steque<Integer> steque1 = new Steque<>();
        Steque<Integer> steque2 = new Steque<>();

        for (int i = 0; i < 10; i++) {
            steque1.enqueue(i);
        }
        for (int i = 10; i < 20; i++) {
            steque2.enqueue(i);
        }
        steque1.catenation(steque2);
        while (!steque1.isEmpty()) {
            StdOut.print(steque1.pop() + " ");
        }
    }

    public void exercise1_3_48() {
        DequeStack<Integer> dequeStack = new DequeStack<>();
        for (int i = 0; i < 10; i++) {
            dequeStack.pushLeft(i);
        }
        while (!dequeStack.isEmptyLeft()) {
            StdOut.print(dequeStack.popLeft() + " ");
        }

        for (int i = 10; i < 20; i++) {
            dequeStack.pushRight(i);
        }
        while (!dequeStack.isEmptyRight()) {
            StdOut.print(dequeStack.popRight() + " ");
        }
    }

    public void exercise1_3_50() {
        Stack1_3_50<Integer> stack1_3_50 = new Stack1_3_50<>();
        stack1_3_50.push(0);
        stack1_3_50.push(1);
        Iterator<Integer> iterator = stack1_3_50.iterator();
        Thread thread = new Thread(() -> stack1_3_50.push(2));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        iterator.next();
    }

    public static void main(String[] args) {
        Exercise1_3 runner = new Exercise1_3();

//        runner.exercise1_3_1();

//        runner.exercise1_3_4();

//        runner.exercise1_3_9();

//        runner.exercise1_3_10();

//        runner.exercise1_3_11();

//        runner.exercise1_3_12();

//        runner.exercise1_3_14();

//        runner.exercise1_3_15(3);

//        runner.exercise1_3_26();

//        runner.exercise1_3_27();

//        runner.exercise1_3_28();

//        runner.exercise1_3_29();

//        runner.exercise1_3_30();

//        runner.exercise1_3_31();

//        runner.exercise1_3_32();

//        runner.exercise1_3_33();

//        runner.exercise1_3_34();

//        runner.exercise1_3_35();

//        runner.exercise1_3_36();

//        runner.exercise1_3_37();

//        runner.exercise1_3_39();

//        runner.exercise1_3_40();

//        runner.exercise1_3_43();

//        runner.exercise1_3_44();

//        runner.exercise1_3_45();

//        runner.exercise1_3_47();

//        runner.exercise1_3_48();

        runner.exercise1_3_50();
    }
}


class FixedCapacityStackOfStrings {
    private final String[] a;
    private int N;

    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        a[N++] = item;
    }

    public String pop() {
        return a[--N];
    }

    public boolean isFull() {
        return this.size() == a.length;
    }
}


class Stack1_3_12 implements Iterable<String> {
    private int size;
    private final int length;
    private final String[] arr;

    public Stack1_3_12() {
        arr = new String[10];
        length = arr.length;
        size = 0;
    }

    public void push(String value) {
        if (isFull()) {
            throw new RuntimeException();
        }
        arr[size++] = value;
    }

    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        String value = arr[--size];
        arr[size] = null;
        return value;
    }

    public static Stack1_3_12 copy(Stack1_3_12 stack) {
        Iterator<String> iterator = stack.iterator();
        Stack1_3_12 copyStack = new Stack1_3_12();
        while (iterator.hasNext()) {
            copyStack.push(iterator.next());
        }
        return copyStack;
    }

    private boolean isFull() {
        return size >= length;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<String> iterator() {
        return new StackIterator(arr, size);
    }

    private static class StackIterator implements Iterator<String> {
        private final String[] arr;
        private final int size;
        private int index = 0;

        public StackIterator(String[] arr, int size) {
            this.arr = arr;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public String next() {
            return arr[index++];
        }
    }
}

class ResizingArrayQueueOfStrings {
    private int length;
    private String[] array;
    private int head;
    private int tail;
    private int size;

    public ResizingArrayQueueOfStrings() {
        length = 8;
        array = new String[length];
        head = -1;
        tail = 0;
        size = 0;
    }

    public void enqueue(String value) {
        if (size >= length) {
            resize(2 * length);
        }
        head = (head + 1) % length;
        array[head] = value;
        size++;
    }

    public String dequeue() {
        if (!isEmpty() && size <= length / 4) {
            resize(length / 2);
        }
        String value = array[tail];
        array[tail] = null;
        tail = (tail + 1) % length;
        size--;
        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int resizeLength) {
        String[] newArray = new String[resizeLength];
        for (int index = tail, newIndex = 0, count = size;
             count > 0;
             index = (index + 1) % length, newIndex++, count--) {
            newArray[newIndex] = array[index];
        }
        tail = 0;
        head = tail + size - 1;
        length = resizeLength;
        array = newArray;
    }

    @Override
    public String toString() {
        return "length=" + length + " size=" + size + " head=" + head + " tail=" + tail;
    }
}

class Node<Item> {
    Item item;
    Node<Item> next;
}

class CircleLinkedListQueue<Item> {
    private Node first;
    private Node last;

    CircleLinkedListQueue() {
        first = null;
        last = null;
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            first = new Node();
            last = first;
        } else {
            last.next = new Node();
            last = last.next;
        }
        last.next = first;
        last.item = item;
    }

    public Item dequeue() {
        assert !isEmpty();
        Item item = first.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
            last.next = first;
        }
        return item;
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    private class Node {
        Item item;
        Node next;
    }
}

class DoubleLinkedList<Item> implements Iterable<DoubleLinkedList.DoubleNode> {
    private DoubleNode first;
    private DoubleNode last;

    public void insertHead(Item item) {
        DoubleNode p = new DoubleNode(item);
        if (isEmpty()) {
            first = p;
            last = first;
        } else {
            first.prev = p;
            p.next = first;
            first = first.prev;
        }
    }

    public void insertTail(Item item) {
        DoubleNode p = new DoubleNode(item);
        if (isEmpty()) {
            first = p;
            last = first;
        } else {
            last.next = p;
            p.prev = last;
            last = last.next;
        }
    }

    public void removeHead() {
        if (first == last) {
            first = null;
            last = null;
            return;
        }
        DoubleNode second = first.next;
        first.next = null;
        second.prev = null;
        first = second;
    }

    public void removeTail() {
        if (first == last) {
            first = null;
            last = null;
            return;
        }
        DoubleNode secondToLast = last.prev;
        last.prev = null;
        secondToLast.next = null;
        last = secondToLast;
    }

    public void insertBefore(int index, Item item) {
        assert index > 0 && getSize() >= index;
        DoubleNode p = first;
        for (int i = 1; i < index; i++) {
            p = p.next;
        }
        DoubleNode itemNode = new DoubleNode(item);
        itemNode.next = p;
        itemNode.prev = p.prev;
        p.prev = itemNode;
        if (itemNode.prev != null) {
            itemNode.prev.next = itemNode;
        } else {
            first = first.prev;
        }
    }

    public void insertAfter(int index, Item item) {
        assert index > 0 && getSize() >= index;
        DoubleNode p = first;
        for (int i = 1; i < index; i++) {
            p = p.next;
        }
        DoubleNode itemNode = new DoubleNode(item);
        itemNode.prev = p;
        itemNode.next = p.next;
        p.next = itemNode;
        if (itemNode.next != null) {
            itemNode.next.prev = itemNode;
        } else {
            last = last.next;
        }
    }

    public void remove(int index) {
        assert index > 0 && getSize() >= index;
        DoubleNode p = first;
        for (int i = 1; i < index; i++) {
            p = p.next;
        }
        if (p.prev != null) {
            p.prev.next = p.next;
        }
        if (p.next != null) {
            p.next.prev = p.prev;
        }
        p.prev = null;
        p.next = null;
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    public int getSize() {
        if (isEmpty()) {
            return 0;
        }
        DoubleNode p = first;
        int count = 1;
        while (p != last) {
            p = p.next;
            count++;
        }
        return count;
    }

    @Override
    public Iterator<DoubleLinkedList.DoubleNode> iterator() {
        return new Iterator<>() {
            private DoubleNode node = null;

            @Override
            public boolean hasNext() {
                if (first == null) return false;
                if (node == null) return true;
                return node.next != null;
            }

            @Override
            public DoubleNode next() {
                if (node == null) {
                    node = first;
                } else {
                    node = node.next;
                }
                return node;
            }
        };
    }

    public class DoubleNode {
        DoubleNode prev;
        DoubleNode next;
        Item item;

        DoubleNode(Item item) {
            this.prev = null;
            this.next = null;
            this.item = item;
        }
    }
}

class Steque<Item> {
    private Node first;
    private Node last;

    Steque() {
        first = null;
        last = null;
    }

    public void push(Item item) {
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            last = node;
        }
        node.next = first;
        first = node;
    }

    public Item pop() {
        assert !isEmpty();
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return item;
    }

    public void enqueue(Item item) {
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = last.next;
        }
    }

    public void catenation(Steque<Item> steque) {
        while (!steque.isEmpty()) {
            this.enqueue(steque.pop());
        }
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    private class Node {
        Item item;
        Node next;
    }
}

class Deque<Item> implements Iterable<Item> {
    private DequeNode first;
    private DequeNode last;
    private int size;

    Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void pushLeft(Item item) {
        DequeNode node = new DequeNode(item);
        if (isEmpty()) {
            last = node;
        } else {
            node.next = first;
            first.prev = node;
        }
        first = node;
        size++;
    }

    public void pushRight(Item item) {
        DequeNode node = new DequeNode(item);
        if (isEmpty()) {
            first = node;
        } else {
            node.prev = last;
            last.next = node;
        }
        last = node;
        size++;
    }

    public Item popLeft() {
        assert !isEmpty();
        Item item = first.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            DequeNode second = first.next;
            first.next = null;
            second.prev = null;
            first = second;
        }
        size--;
        return item;
    }

    public Item popRight() {
        assert !isEmpty();
        Item item = last.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            DequeNode secondToLast = last.prev;
            last.prev = null;
            secondToLast.next = null;
            last = secondToLast;
        }
        size--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<>() {
            DequeNode node = null;

            @Override
            public boolean hasNext() {
                if (isEmpty()) return false;
                if (node == null) {
                    node = first;
                    return true;
                }
                node = node.next;
                return node != null;
            }

            @Override
            public Item next() {
                return node.item;
            }
        };
    }

    private class DequeNode {
        DequeNode prev;
        DequeNode next;
        Item item;

        DequeNode(Item item) {
            this.prev = null;
            this.next = null;
            this.item = item;
        }
    }
}

class ResizingArrayDeque<Item> implements Iterable<Item> {
    private Item[] array;
    private int left;
    private int right;
    private int size;
    private int length;

    ResizingArrayDeque() {
        this.length = 8;
        this.array = (Item[]) new Object[length];
        this.left = length / 2;
        this.right = length / 2 + 1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return left + 1 == right;
    }

    public int size() {
        return size;
    }

    public void pushLeft(Item item) {
        if (left == 0) {
            resizing(length * 2);
        }
        array[left] = item;
        left--;
        size++;
    }

    public void pushRight(Item item) {
        if (right == length - 1) {
            resizing(length * 2);
        }
        array[right] = item;
        right++;
        size++;
    }

    public Item popLeft() {
        if (isEmpty()) return null;
        if (size < length / 4) {
            resizing(length / 2);
        }
        Item item = array[++left];
        array[left] = null;
        size--;
        return item;
    }

    public Item popRight() {
        if (isEmpty()) return null;
        if (size < length / 4) {
            resizing(length / 2);
        }
        Item item = array[--right];
        array[right] = null;
        size--;
        return item;
    }

    private void resizing(int length) {
        Item[] newArray = (Item[]) new Object[length];
        for (int i = left + 1, index = length / 4; i < right; i++, index++) {
            newArray[index] = array[i];
        }
        left = length / 4 - 1;
        right = left + size + 1;
        this.array = newArray;
        this.length = length;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<>() {
            int index = left;

            @Override
            public boolean hasNext() {
                if (isEmpty()) return false;
                return array[++index] != null;
            }

            @Override
            public Item next() {
                return array[index];
            }
        };
    }
}

class RandomBag<Item> implements Iterable<Item> {
    private Item[] array;
    private int size;

    RandomBag() {
        this.array = (Item[]) new Object[8];
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return size;
    }

    public void add(Item item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[size] = item;
        size++;
    }

    private void resize(int length) {
        Item[] newArray = (Item[]) new Object[length];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }


    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int index;

        private RandomIterator() {
            this.index = 0;
            for (int i = 0; i < size; i++) {
                int j = StdRandom.uniformInt(i, size);
                Item temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return array[index] != null;
        }

        @Override
        public Item next() {
            Item item = array[index];
            index++;
            return item;
        }
    }
}

class RandomQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int size;

    RandomQueue() {
        this.array = (Item[]) new Object[8];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Item item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[size] = item;
        size++;
    }

    public Item dequeue() {
        assert size > 0;
        if (size < array.length / 4) {
            resize(array.length / 2);
        }
        int randomIndex = StdRandom.uniformInt(0, size--);
        Item item = array[randomIndex];
        array[randomIndex] = array[size];
        array[size] = null;
        return item;
    }

    private void resize(int length) {
        Item[] newArray = (Item[]) new Object[length];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int index;
        private final Item[] randomArray;

        RandomIterator() {
            this.index = 0;
            this.randomArray = array;
            for (int i = 0; i < size; i++) {
                int j = StdRandom.uniformInt(i, size);
                Item item = randomArray[j];
                randomArray[j] = randomArray[i];
                randomArray[i] = item;
            }
        }

        @Override
        public boolean hasNext() {
            return randomArray[index] != null;
        }

        @Override
        public Item next() {
            Item item = randomArray[index];
            index++;
            return item;
        }
    }
}

class RingBuffer<Item> {
    private final Item[] buffer;
    private volatile int readPos;
    private volatile int writePos;

    RingBuffer(int capacity) {
        this.buffer = (Item[]) new Object[capacity];
        this.readPos = 0;
        this.writePos = 0;
    }

    public boolean isEmpty() {
        return readPos == writePos;
    }

    public boolean isFull() {
        return ((writePos + 1) % buffer.length) == readPos;
    }

    public void write(Item item) {
        while (isFull())
            ;
        buffer[writePos] = item;
        writePos = (writePos + 1) % buffer.length;
    }

    public Item read() {
        while (isEmpty())
            ;
        Item item = buffer[readPos];
        readPos = (readPos + 1) % buffer.length;
        return item;
    }
}

class MoveToFrontQueue<Item> implements Iterable<Item> {
    Node first;

    MoveToFrontQueue() {
        first = null;
    }

    public void enqueue(Item item) {
        Node node = new Node();
        node.item = item;
        remove(item);
        node.next = first;
        first = node;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private void remove(Item item) {
        if (isEmpty()) return;
        if (first.item == item) {
            first = null;
            return;
        }
        Node p = first;
        while (p.next != null) {
            if (p.next.item == item) {
                p.next = p.next.next;
                return;
            }
            p = p.next;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<>() {
            Node pointer = first;

            @Override
            public boolean hasNext() {
                return pointer != null;
            }

            @Override
            public Item next() {
                Item item = pointer.item;
                pointer = pointer.next;
                return item;
            }
        };
    }

    private class Node {
        Item item;
        Node next;
    }
}

class Buffer {
    Stack<Character> stack1;
    Stack<Character> stack2;

    Buffer() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void insert(char c) {
        stack1.push(c);
    }

    public char delete() {
        return stack1.pop();
    }

    public void left(int k) {
        for (int i = 0; i < k; i++) {
            stack2.push(stack1.pop());
        }
    }

    public void right(int k) {
        for (int i = 0; i < k; i++) {
            stack1.push(stack2.pop());
        }
    }

    public int size() {
        return stack1.size() + stack2.size();
    }
}

class DequeStack<Item> {
    private final Deque<Item> deque;
    private int leftStackSize;
    private int rightStackSize;

    DequeStack() {
        deque = new Deque<>();
        leftStackSize = 0;
        rightStackSize = 0;
    }

    public void pushLeft(Item item) {
        deque.pushLeft(item);
        leftStackSize++;
    }

    public Item popLeft() {
        if (isEmptyLeft()) {
            return null;
        }
        Item item = deque.popLeft();
        leftStackSize--;
        return item;
    }

    public void pushRight(Item item) {
        deque.pushRight(item);
        rightStackSize++;
    }

    public Item popRight() {
        if (isEmptyRight()) {
            return null;
        }
        Item item = deque.popRight();
        rightStackSize--;
        return item;
    }

    public boolean isEmptyLeft() {
        return leftStackSize == 0;
    }

    public boolean isEmptyRight() {
        return rightStackSize == 0;
    }

}

class Stack1_3_50<Item> implements Iterable<Item> {
    private Node first;
    private int size;
    private int popCount;
    private int pushCount;

    Stack1_3_50() {
        first = null;
        size = 0;
        popCount = 0;
        pushCount = 0;
    }

    public void push(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = first;
        first = node;
        pushCount++;
        size++;
    }

    public Item pop() {
        if (isEmpty()) return null;
        Node node = first;
        first = first.next;
        size--;
        popCount++;
        return node.item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private class Node {
        Item item;
        Node next;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ExceptionIterator<>();
    }

    private class ExceptionIterator<E> implements Iterator<E> {
        private final int popCount;
        private final int pushCount;
        private Node pointer;

        ExceptionIterator() {
            this.popCount = Stack1_3_50.this.popCount;
            this.pushCount = Stack1_3_50.this.pushCount;
            this.pointer = first;
        }

        @Override
        public boolean hasNext() {
            check();
            return pointer != null;
        }

        @Override
        public E next() {
            check();
            Node node = pointer;
            pointer = pointer.next;
            return (E) node.item;
        }

        private void check() {
            if (popCount != Stack1_3_50.this.popCount || pushCount != Stack1_3_50.this.pushCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

/* Exercise linked list. */

class LinkedListExercise<Item> {
    private final Node first;
    private int N;

    public LinkedListExercise() {
        first = new Node();
        N = 0;
    }

    // exercise1_3_19
    public void removeTail() {
        if (isEmpty()) {
            return;
        }
        Node p = first;
        for (int i = 0; i < N - 1; i++) {
            p = p.next;
        }
        p.next = null;
        N--;
    }

    // exercise1_3_20
    public void delete(int k) {
        if (N < k) {
            return;
        }
        Node p = first;
        for (int i = 0; i < k - 1; i++) {
            p = p.next;
        }
        p.next = p.next.next;
        N--;
    }

    // exercise1_3_21
    public boolean find(Item item) {
        if (isEmpty()) {
            return false;
        }
        Node p = first;
        while (p.next != null) {
            p = p.next;
            if (p.item == item) {
                return true;
            }
        }
        return false;
    }

    // exercise1_3_24
    public void removeAfter(Node node) {
        while (node.next != null) {
            node.next = node.next.next;
        }
    }

    // exercise1_3_25
    public void insertAfter(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        node2.next = node1.next;
        node1.next = node2;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private class Node {
        Item item;
        Node next;
    }
}