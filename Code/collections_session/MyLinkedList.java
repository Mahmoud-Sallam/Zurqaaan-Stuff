package collections_session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyLinkedList<E> {

    private int size;

    private Node<E> first;
    private Node<E> last;

    public MyLinkedList() {

    }

    public static <E> MyLinkedList<E> of(E... data){
        MyLinkedList<E> eMyLinkedList = new MyLinkedList<>();
        for (E datum : data) {
            eMyLinkedList.add(datum);
        }
        return eMyLinkedList;
    }

    public <U> HashMap<U, MyLinkedList<E>> groupingBy(
            Function<? super E, ? extends U> keyFunction) {

        return reduceLeft(new HashMap<U, MyLinkedList<E>>(),
                acc -> data -> {
                    U key = keyFunction.apply(data);
                    MyLinkedList<E> orDefault =
                            acc.getOrDefault(key, new MyLinkedList<>());
                    orDefault.add(data);
                    acc.put(key,orDefault);
                    return acc;
                });

    }

    public boolean noneMatch(Predicate<? super E> predicate) {

        return allMatch(predicate.negate());
    }

    public boolean allMatch(Predicate<? super E> predicate) {
        Iterator<? extends E> iterate = iterate();
        boolean passed = false;
        while (iterate.hasNext()) {
            E data = iterate.next();
            passed = predicate.test(data);
            if (passed == false) return false;
        }

        return passed;
    }

    public boolean anyMatch(Predicate<? super E> predicate) {

        Iterator<? extends E> iterate = iterate();
        while (iterate.hasNext()) {
            E data = iterate.next();
            boolean passed = predicate.test(data);
            if (passed) return true;
        }

        return false;

    }

    public MyLinkedList<E> revers() {

        return reduceLeft(new MyLinkedList<E>(), acc -> data -> {

            acc.addFirst(data);
            return acc;
        });
    }

    public MyLinkedList<E> filter(Predicate<? super E> predicate) {

        MyLinkedList<E> eMyLinkedList =
                reduceLeft(new MyLinkedList<E>(), acc -> data -> {
                    boolean passed = predicate.test(data);
                    if (passed) {
                        acc.addLast(data);
                    }
                    return acc;
                });
        return eMyLinkedList;
    }

    public <U> U reduceLeft(U seed, BiFunction<? super U, ? super E, ? extends U> reducer) {

        return reduceLeft(seed, x -> y -> reducer.apply(x, y));

    }

    public <U> U
    reduceLeft(U seed, Function<? super U, Function<? super E, ? extends U>> reducer) {

        U accum = seed;
        Iterator<? extends E> elements = iterate();
        while (elements.hasNext()) {
            E data = elements.next();
            accum = reducer.apply(accum).apply(data);
        }


        return accum;

    }

    public <U> MyLinkedList<U>
    flatMap(Function<? super E, MyLinkedList<? extends U>> flatMapFunction) {

        MyLinkedList<U> uMyLinkedList = new MyLinkedList<>();
        Iterator<? extends E> iterate = iterate();
        while (iterate.hasNext()) {
            E data = iterate.next();
            MyLinkedList<? extends U> mappedData = flatMapFunction.apply(data);
            uMyLinkedList.addAll(mappedData);
        }
        return uMyLinkedList;

    }

    public <U> MyLinkedList<U> map(Function<? super E, ? extends U> mapper) {
        MyLinkedList<U> uMyLinkedList = new MyLinkedList<>();
        Iterator<? extends E> iterate = iterate();
        while (iterate.hasNext()) {
            E data = iterate.next();
            U mappedData = mapper.apply(data);
            uMyLinkedList.addLast(mappedData);
        }
        return uMyLinkedList;

    }

    public Optional<E> get(int index) {

        if (index < 0 || index >= size) return Optional.empty();

        Node<E> node = node(index);

        return Optional.of(node.data);

    }

    public Optional<E> remove() {
        return removeFirst();
    }

    public Optional<E> removeFirst() {
        return removeNode(first);
    }

    public Optional<E> removeLast() {
        return removeNode(last);
    }

    public Optional<E> remove(int index) {

        if (index < 0 || index >= size) return Optional.empty();

        Node<E> node = node(index);

        return removeNode(node);


    }

    public Optional<E> removeByValue(E data) {

        for (int i = 0; i < size; i++) {
            Node<E> node = node(i);
            if (node.data.equals(data)) {
                return removeNode(node);
            }
        }

        return Optional.empty();
    }

    private Optional<E> removeNode(Node<E> nodeToBeRemoved) {
        if (nodeToBeRemoved == null) return Optional.empty();

        Node<E> previousNode = nodeToBeRemoved.previous;
        Node<E> nextNode = nodeToBeRemoved.next;

        if (previousNode == null) {
            first = nextNode;
            nodeToBeRemoved.next = null;
        } else {
            previousNode.next = nextNode;
        }

        if (nextNode == null) {
            last = previousNode;
            nodeToBeRemoved.previous = null;
        } else {
            nextNode.previous = previousNode;
        }
        E data = nodeToBeRemoved.data;
        size--;
        nodeToBeRemoved.previous = null;
        nodeToBeRemoved.next = null;
        nodeToBeRemoved.data = null;

        return Optional.of(data);
    }

    public Optional<E> first() {
        return first == null ? Optional.empty() : Optional.of(first.data);
    }

    public Optional<E> last() {

        return last == null ? Optional.empty() : Optional.of(last.data);
    }

    private Node<E> node(int index) {

        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public void addAll(MyLinkedList<? extends E> listToBeAdded) {
        //TODO check if listToBeAdded is null ..
        Iterator<? extends E> iterate = listToBeAdded.iterate();
        while (iterate.hasNext()) {
            this.addLast(iterate.next());
        }
    }

    public void add(E data) {

        addFirst(data);
    }

    public void addFirst(E data) {
        Node<E> oldFirst = first;
        Node<E> newFirst = new Node<>(null, data, oldFirst);
        first = newFirst;
        if (oldFirst == null) {
            last = newFirst;
        } else {
            oldFirst.previous = newFirst;
        }

        size++;

    }

    public void addLast(E data) {
        Node<E> oldLast = this.last;
        Node<E> newNode = new Node<>(oldLast, data, null);
        last = newNode;
        if (oldLast == null) {
            first = newNode;
        } else {
            oldLast.next = newNode;
        }
        size++;

    }


    public Iterator<? extends E> iterate() {
        return new LinkedIterator(first);
    }

    public int size() {

        return size;
    }

    @Override
    public String toString() {
        Iterator<? extends E> iterate = iterate();
        if (iterate.hasNext() == false) return "[]";

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        while (iterate.hasNext()) {
            E next = iterate.next();
            stringBuffer.append(next);
            if (iterate.hasNext()) {
                stringBuffer.append(", ");
            }
        }
        return stringBuffer.append("]").toString();

    }

    private class LinkedIterator implements Iterator<E> {

        private Node<E> currentNode;
        private Node<E> lastAccessed;

        public LinkedIterator(Node<E> currentNode) {
            this.currentNode = currentNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            lastAccessed = currentNode;
            currentNode = currentNode.next;
            return lastAccessed.data;
        }
    }

    private class Node<E> {

        private Node<E> previous;
        private E data;
        private Node<E> next;

        public Node(Node<E> previous, E data, Node<E> next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        MyLinkedList<Integer> numbers = new MyLinkedList<>();
        numbers.addFirst(1);
        numbers.addFirst(2);
        numbers.addFirst(9);
        numbers.addFirst(10);
        numbers.addFirst(30);
        numbers.addLast(50);
        numbers.addFirst(3);
        numbers.addFirst(323);
        numbers.addLast(55);

        HashMap<String, MyLinkedList<Integer>> groupedByType
                = numbers.groupingBy(x ->
                x % 2 == 0 ? "Even" : "Odd");

        System.out.println("groupedByType = " + groupedByType);

        HashMap<String, MyLinkedList<Integer>> groupedByType2
                = numbers.groupingBy(x ->
                x <=5 ? "<=5" : x
                        <10?"5<x<10" :"<=10");

        System.out.println("groupedByType = " + groupedByType2);


        boolean noneIsEven = numbers.noneMatch(x -> x % 2 == 0);
        boolean noneIsOdd = numbers.noneMatch(x -> x % 2 != 0);

        System.out.println("noneIsEven = " + noneIsEven);
        System.out.println("noneIsOdd = " + noneIsOdd);
        boolean isEven = numbers.allMatch(x -> x % 2 == 0);
        System.out.println("isEven = " + isEven);

        MyLinkedList<Integer> integerMyLinkedList = new MyLinkedList<Integer>();
        boolean b = integerMyLinkedList.allMatch(x -> x > 1);
        System.out.println("b = " + b);

        boolean founded = numbers.anyMatch(x -> x < 200);
        System.out.println("founded = " + founded);

        System.out.println("numbers = " + numbers);
        MyLinkedList<Integer> revers = numbers.revers();
        System.out.println("revers = " + revers);
        MyLinkedList<Integer> filteredData = numbers
                .filter(x -> x % 5 == 0);

        System.out.println("filteredData = " + filteredData);
        HashMap<Integer, Integer> integerIntegerHashMap =

                numbers.reduceLeft(new HashMap<Integer, Integer>(),
                        acc -> data -> {
                            Integer count = acc.getOrDefault(data, 0);
                            acc.put(data, count + 1);
                            return acc;
                        });

        System.out.println("integerIntegerHashMap = " + integerIntegerHashMap);


        ArrayList<Integer> integers =
                numbers.reduceLeft(new ArrayList<Integer>(),
                        acc -> data -> {
                            acc.add(data);
                            return acc;
                        });

        System.out.println("integers = " + integers);
        MyLinkedList<String> stringMyLinkedList =
                numbers.reduceLeft(new MyLinkedList<String>(),
                        acc -> data -> {
                            acc.addLast("Item Value: " + data + "\n");
                            return acc;
                        });


        System.out.println("stringMyLinkedList = "
                + stringMyLinkedList);


        Integer sum =
                numbers.reduceLeft(0, acc -> data -> acc + data);

        System.out.println("sum = " + sum);

        Integer mult = numbers.reduceLeft(1, acc -> data -> acc * data);
        System.out.println("mult = " + mult);


        Integer sum2 =
                numbers.reduceLeft(0, (acc, data) -> acc + data);
        System.out.println("sum2 = " + sum2);

//        MyLinkedList<Integer> anotherList = new MyLinkedList<>();
//        anotherList.addFirst(4);
//        anotherList.addFirst(5);
//        anotherList.addFirst(6);
//
//        System.out.println("numbers = " + numbers);
//        System.out.println("anotherList = " + anotherList);
//        numbers.addAll(anotherList);
//
//        System.out.println("numbers = " + numbers);
//
//        MyLinkedList<Integer> map = numbers.map(x -> x * x);
//        System.out.println("map = " + map);
//
//        MyLinkedList<String> map1 = numbers.map(x -> "The item value is " + x+"\n");
//        System.out.println("map1 = " + map1);
//
//        Function<Object,String> f = x->x.toString();
//        MyLinkedList<String> map2 = numbers.map(f);
//        System.out.println("map2 = " + map2);

        System.out.println("numbers = " + numbers);
        MyLinkedList<Integer> flatLinkedList = numbers.flatMap(x -> {
            MyLinkedList<Integer> subList = new MyLinkedList<>();
            subList.add(x - 1);
            subList.add(x);
            subList.add(x + 1);

            return subList;
        });
//
//        MyLinkedList<MyLinkedList<Integer>> map = numbers.map(x -> {
//            MyLinkedList<Integer> subList = new MyLinkedList<>();
//            subList.add(x - 1);
//            subList.add(x);
//            subList.add(x + 1);
//
//            return subList;
//        });
        System.out.println("flatLinkedList = " + flatLinkedList);
//        System.out.println("map = " + map);


    }
}
