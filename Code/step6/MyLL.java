package step6;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MyLL<E> {

    private int maxCapicity = Integer.MAX_VALUE;

    private Node first, last;

    private int size;

    public MyLL(int maxCapicity) {
        this.maxCapicity = maxCapicity;
    }

    public MyLL() {
    }

    public void add(E e) {

        addLast(e);

    }

    public <U> MyLL<U> map(Function<? super E, ? extends U> mapper) {

        MyLL<U> mappedList = new MyLL<>();

        Node<E> x = first;
        while (x != null) {
            E data = x.data;
            U mapped = mapper.apply(data);
            mappedList.add(mapped);
            x = x.next;
        }

        return mappedList;
    }

    public <U> MyLL<U> flatMap(Function<? super E, MyLL<? extends U>> mapper) {

        MyLL<U> mappedList = new MyLL<>();

        Node<E> x = first;
        while (x != null) {
            E data = x.data;
            MyLL<? extends U> mapped = mapper.apply(data);


            mappedList.addAll(mapped);
            x = x.next;
        }

        return mappedList;
    }

    public Iterator<E> iterator() {

        return new MYIterator<>(first);

    }

    private class MYIterator<E> implements Iterator<E> {

        private Node<E> node;
        private Node<E> lastReturned;

        public MYIterator(Node<E> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            lastReturned = node;
            node = node.next;
            return lastReturned.data;
        }
    }

    private void addAll(MyLL<? extends E> externalList) {

        Iterator<? extends E> iterator = externalList.iterator();
        while (iterator.hasNext()) {
            this.add(iterator.next());
        }
    }

    private Node<E> nodeOf(int index) {

        Node x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;

    }

    public E removeFirst() {

        Node<E> f = first;
        if (f == null) throw new IllegalStateException("List i empty");

        Node next = first.next;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.previous = null;
        }
        size--;
        f.next = null;
        E e = f.data;
        f.data = null;

        return e;
    }

    public E removeLast() {

        Node<E> l = this.last;
        if (l == null) throw new IllegalStateException("List is empty");
        Node previous = l.previous;
        last = previous;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        E data = l.data;
        l.data = null;
        l.previous = null;
        return data;

    }

    public <T> T reduce(T seed, BiFunction<? super T, ? super E, ? extends T> reducer) {

        return reduce(seed, x -> y -> reducer.apply(x, y));

    }

    public <T> T reduce(T seed, Function<? super T, Function<? super E, ? extends T>> reducer) {
        T accum = seed;

        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E next = iterator.next();
            accum = reducer.apply(accum).apply(next);
        }

        return accum;
    }

    public void addLast(E e) {

        Node l = this.last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }

        size++;

    }

    public void addFirst(E e) {

        Node f = this.first;
        Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.previous = newNode;
        }
        size++;

    }

    private class Node<E> {

        private E data;
        private Node<E> next;
        private Node<E> previous;

        public Node(Node<E> previous, E data, Node<E> next) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }

        public E getData() {
            return data;
        }


        public Node<E> getNext() {
            return next;
        }


        public Node<E> getPrevious() {
            return previous;
        }

    }

    @Override
    public String toString() {
        Node<E> x = first;
        Iterator<E> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    public static void main(String[] args) {
        MyLL<Integer> integerMyLL = new MyLL<>();
        integerMyLL.add(1);
        integerMyLL.add(2);
        integerMyLL.add(3);

        System.out.println(integerMyLL);

        MyLL<String> map = integerMyLL.map(x -> "As String" + x);
        System.out.println("map = " + map);

        MyLL<Integer> integerMyLL1 = integerMyLL.flatMap(x -> {
            MyLL<Integer> subList = new MyLL<>();
            subList.add(x);
            subList.add(x * 100);
            return subList;
        });

        System.out.println(integerMyLL1);

        Integer reduce = integerMyLL.reduce(0, acc -> item -> acc + item);
        System.out.println("reduce = " + reduce);

        Integer reduce2 = integerMyLL1.reduce(0, acc -> item -> acc + item);
        System.out.println("reduce2 = " + reduce2);

        Integer reduce3 = integerMyLL.reduce(1, acc -> item -> acc * item);
        System.out.println("reduce3 = " + reduce3);

    }
}
