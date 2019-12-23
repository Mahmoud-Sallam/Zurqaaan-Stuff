package collections_session;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.System.arraycopy;

public class MyArrayList<E> {


    private Object[] data;

    public static final int DEFAULT_INITIAL_SIZE = 10;

    private int size;

    public MyArrayList() {

        data = new Object[DEFAULT_INITIAL_SIZE];
    }

    public MyArrayList(int initialArraySize) {
        data = new Object[initialArraySize];
    }

    private MyArrayList(Object[] newData, int size) {
        this.data = newData;
        this.size = size;

    }


    public static <U> MyArrayList<U> of(U... uData) {
        return new MyArrayList<U>(uData, uData.length);
    }

    public <U> U reduceRight(U seed,
                             Function<? super E,Function<? super U,? extends U>> reducer){
        U acc = seed;
        for (int i = size - 1; i >= 0; i--) {
            E element = (E)data[i];
            acc = reducer.apply(element).apply(acc);
        }

        return acc;

    }

    public <U> U
    reduceLeft(U seed,
               Function<? super U, Function<? super E, ? extends U>> reducer) {
        U accum = seed;
        Iterator<E> iterator = iterate();
        while (iterator.hasNext()) {
            E element = iterator.next();
            accum = reducer.apply(accum).apply(element);
        }
        return accum;

    }

    public <U> U reduceLeft(U seed, BiFunction<? super U, ? super E, ? extends U> reducer) {

        return reduceLeft(seed, acc -> elem -> reducer.apply(acc, elem));
    }

    public <U> MyArrayList<U>
    flatMap(Function<? super E, MyArrayList<? extends U>> flatMap) {

        MyArrayList<U> uMyArrayList = new MyArrayList<>();

        Iterator<E> iterate = iterate();
        while (iterate.hasNext()) {
            E element = iterate.next();
            MyArrayList<? extends U> subList = flatMap.apply(element);
            uMyArrayList.addAll(subList);
        }

        return uMyArrayList;
    }


    public <U> MyArrayList<U> map(Function<? super E, ? extends U> mapper) {

        MyArrayList<U> uMyArrayList = new MyArrayList<>(data.length);

        for (int i = 0; i < size; i++) {

            Object datum = data[i];
            U mappedData = mapper.apply((E) datum);
            uMyArrayList.set(i, mappedData, true);

        }

        return uMyArrayList;
    }

    public void addAll(MyArrayList<? extends E> additionalData) {
        checkIfWeNeedToExpandTheArray(this.size + additionalData.size());
        Iterator<? extends E> iterate = additionalData.iterate();
        while (iterate.hasNext()) {
            this.add(iterate.next());
        }
    }

    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {

            if (data[i].equals(element)) {
                return true;
            }

        }
        return false;

    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    public void removeIf(Predicate<E> predicate) {
        int index = 0;
        int s = size;
        for (int i = 0; i < s; i++) {
            Object datum = data[index];
            boolean shouldBeRemoved = predicate.test((E) datum);
            if (shouldBeRemoved) {
                remove(index);
            } else {
                index++;

            }
        }


    }

    public void removeAllOcc(E element) {
        removeIf(x -> x.equals(element));
    }

    public void removeExcept(E element) {
        removeIf(x -> !x.equals(x));
    }

    public Optional<E> removeByValue(E element) {

        for (int i = 0; i < size; i++) {
            Object datum = data[i];
            if (datum.equals(element)) {
                return remove(i);
            }
        }

        return Optional.empty();
    }

    public Optional<E> removeLast() {

        if (size == 0) return Optional.empty();

        Object lastElement = data[--size];
        data[size] = null;

        return Optional.of((E) lastElement);

    }

    public Optional<E> remove(int index) {
        if (index < 0 || index >= size) return Optional.empty();

        Object datum = data[index];

        arraycopy(
                data, index + 1, data, index, --size - index);
        data[size] = null;
        return Optional.of((E) datum);

    }

    public Optional<E> removeFirst() {
        return remove(0);
    }

    public void set(int index, E element) {
        set(index, element, false);
    }

    public void set(int index, E element, boolean expandIfNeeded) {
        if (index >= size && expandIfNeeded == false) {
            throw new IllegalArgumentException("Index out of bound");
        }
        checkIfWeNeedToExpandTheArray(index);
        data[index] = element;
        size = size > index + 1 ? size : index + 1;
    }

    public Optional<E> get(int index) {

        if (index < 0 || index >= size) return Optional.empty();

        return Optional.of((E) data[index]);

    }

    public void add(E element) {
        addLast(element);
    }

    public void addFirst(E element) {
        checkIfWeNeedToExpandTheArray(size);
        arraycopy(data, 0, data, 1, size++);
        data[0] = element;
    }

    public Iterator<E> iterate() {
        return new MyArrayListIterator(size, data);
    }

    public void addLast(E element) {
        checkIfWeNeedToExpandTheArray(size);

        data[size++] = element;
    }

    public int size() {
        return size;
    }

    //TODO remove the next method in production
    public void debug() {
        System.out.println("==========================");
        for (int i = 0; i < data.length; i++) {
            System.out.println(" Element at pos: " + i + " : " + data[i]);
        }
    }

    private class MyArrayListIterator implements Iterator<E> {

        private int index = 0;
        private int size = 0;
        private Object[] data;

        public MyArrayListIterator(int size, Object[] data) {
            this(0, size, data);
        }

        public MyArrayListIterator(int index, int size, Object[] data) {
            this.index = index;
            this.size = size;
            this.data = data;
        }

        @Override
        public boolean hasNext() {

            return index < size;
        }

        @Override
        public E next() {
            if (index >= size) throw new ArrayIndexOutOfBoundsException();

            return (E) data[index++];
        }
    }

    private void checkIfWeNeedToExpandTheArray(int targetSize) {
        if (targetSize < data.length) return;

        int newArrayLength = data.length << 1;
        newArrayLength = newArrayLength > targetSize ? newArrayLength : targetSize;

        data = Arrays.copyOf(this.data, newArrayLength);

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


    public static void main(String[] args) {


        MyArrayList<String> strListFromOf = MyArrayList.of("AB", "ABC",
                "CDE", "BD", "Z", "ZA");

        MyArrayList<Integer> numbers = MyArrayList.of(10, 20, 30, 40);
        Integer sum = numbers.reduceLeft(0, acc -> elem -> acc + elem);
        System.out.println("sum = " + sum);
        Integer sum2 = numbers.reduceRight(0, elem -> acc -> acc + elem);
        System.out.println("sum2 = " + sum2);

        Integer sub1 = numbers.reduceLeft(0, acc -> ele -> acc - ele);
        Integer sub2 = numbers.reduceRight(0, ele -> acc -> ele - acc);
        System.out.println("sub1 = " + sub1);
        System.out.println("sub2 = " + sub2);

        HashMap<Character, MyArrayList<String>> characterMyArrayListHashMap =

                strListFromOf.reduceLeft(
                        new HashMap<Character, MyArrayList<String>>(),
                        acc -> element -> {
                            char key = element.charAt(0);
                            MyArrayList<String> orDefault =
                                    acc.computeIfAbsent(key, k -> new MyArrayList<String>());
                            orDefault.add(element);

                            return acc;
                        });

        System.out.println("characterMyArrayListHashMap = " + characterMyArrayListHashMap);
//
//        MyArrayList<Character> myArrayList = strListFromOf
//                .flatMap(x -> {
//                    char[] chars = x.toCharArray();
//                    MyArrayList<Character> charsList = new MyArrayList<>();
//                    for (char aChar : chars) {
//                        charsList.add(aChar);
//                    }
//                    return charsList;
//                });
//
//        myArrayList.debug();
//
//        MyArrayList<MyArrayList<Character>> map = strListFromOf
//                .map(x -> {
//                    char[] chars = x.toCharArray();
//                    MyArrayList<Character> charsList = new MyArrayList<>();
//                    for (char aChar : chars) {
//                        charsList.add(aChar);
//                    }
//                    return charsList;
//                });

//        strListFromOf.debug();
//        System.out.println("strListFromOf.size() = " + strListFromOf.size());
//
//        strListFromOf.addAll(MyArrayList.of("D","E","F"));
//        strListFromOf.addAll(MyArrayList.of("D","E","F"));
//        strListFromOf.debug();
//        System.out.println("strListFromOf.size() = " + strListFromOf.size());
//        strListFromOf.removeAllOcc("D");
//        strListFromOf.debug();
//        System.out.println("strListFromOf.size() = " + strListFromOf.size());

//        MyArrayList<String> strList = new MyArrayList<>(4);
//        strList.addLast("BC");
//        strList.addLast("C");
//        strList.addLast("DE");
//        strList.addLast("E");
//        strList.debug();
//
//        MyArrayList<Integer> map = strList.map(x -> x.length());
//        map.debug();
//
//        Iterator<String> iterate = strList.iterate();
//        while (iterate.hasNext()){
//            System.out.println("iterate.next() = " + iterate.next());
//        }

//        iterate.next();//will throw exception

//        System.out.println("Before strList.size() = " + strList.size());
//
////        strList.removeIf(x->x.equalsIgnoreCase("d"));
////        strList.removeByValue("D");
////        strList.debug();
//        System.out.println("After strList.size() = " + strList.size());
//        strList.removeIf(x->x.length()==1);
//        strList.debug();
//        System.out.println("After strList.size() = " + strList.size());

//        strList.removeFirst();
//        strList.debug();
//        System.out.println("After strList.size() = " + strList.size());

//        strList.debug();
//        strList.set(0,"Z");
//        strList.debug();
//        int size = strList.size();
//        System.out.println("size = " + size);
//        strList.debug();
//        strList.set(3,"W");
//        strList.debug();
//        size = strList.size();
//        System.out.println("size = " + size);

//        boolean contC = strList.contains("C");
//        boolean contF = strList.contains("F");
//        System.out.println("contC = " + contC);
//        System.out.println("contF = " + contF);

//        strList.debug();
//        strList.addFirst("A");
//        strList.addLast("E");
//        strList.debug();
//        System.out.println("Remove last");
//        strList.removeLast();
//        strList.debug();
//        strList.addLast("E");
//        strList.debug();
//
//        Optional<String> s0 = strList.get(0);
//        System.out.println("s0 = " + s0);
//        Optional<String> s3 = strList.get(3);
//        System.out.println("s3 = " + s3);
//        Optional<String> s4 = strList.get(4);
//        System.out.println("s4 = " + s4);
//        Optional<String> s30 = strList.get(30);
//        System.out.println("s30 = " + s30);

//        String _d =
//                s30.
//                        map(str -> str.startsWith("A")?"Start with A":"Not Start with A")
//                        .orElse("Not Founded");
//        System.out.println("lengthOfStr = " + _d);
//        MyArrayList<Integer> list = new MyArrayList<>();
//        list.addLast(10);
//        list.addLast(20);
//        list.addLast(30);
//        list.addLast(10);
//        list.addLast(20);
//        list.addLast(30);
//        list.addLast(10);
//        list.addLast(20);
//        list.addLast(30);
//        list.addLast(10);
//        list.addLast(20);
//        list.addLast(30);
//        list.debug();
//
//        System.out.println("list.size = " + list.size);
//        MyArrayList<String> stringMyArrayList = new MyArrayList<>(2);
//        stringMyArrayList.addLast("A");
//        stringMyArrayList.addLast("B");
//        stringMyArrayList.debug();
//        System.out.println("=========================");
//        stringMyArrayList.addLast("C");
//        stringMyArrayList.debug();


    }

}
