package step5_genric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class OldJava {

    public static void main(String[] args) {

//        List strings = Arrays.asList("Ahmad","Mohammad","Esa");
//
//        for (Object string : strings) {
//            String temp = (String)string;
//            System.out.println(temp.toUpperCase());
//
//        }

//        List<String> strings = Arrays.asList("Ahmad","Mohammad","Esa");
//        for (String string : strings) {
//
//        }
//
//        List<String> listOfStrings = new ArrayList<>();
//
//        listOfStrings.add("Ahmad");
//
//        MyList<String> a= new MyList<>("str");
//
//        MyList<Integer> b= new MyList<>(3);
//
//
//
//        MyList<Date> d= new MyList<>(new Date());
//
//
//        MyList.clone(d);
//        MyList.<Integer>clone(b);
//        MyList<String> s= MyList.clone(b);


        ValueHolder<String> name= new ValueHolder<>("Mohammad");
        ValueHolder<Integer> intHolder= new ValueHolder<>(4);


//        System.out.println(intHolder.desc());
//        System.out.println(name.desc());

        ValueHolder<Integer> mappedValue = name.map(x -> x.length());
//        System.out.println("mappedValue.getValue() = " + mappedValue.getValue());
//        System.out.println("mappedValue.desc() = " + mappedValue.desc());

        Function<Object,String> strMapper = obj->"Value is : "+obj.toString();
//        Function<Integer,String> intStrMapper = obj->"Value is : "+obj.toString();
//        Function<Date,String> dateStrMapper = obj->"Value is : "+obj.toString();

        ValueHolder<String> mapStr = intHolder.map(strMapper);
        System.out.println(mapStr.getValue());

        ValueHolder<Date> dateHodler = new ValueHolder<>(new Date());
        ValueHolder<String> dateMapped = dateHodler.map(strMapper);
        System.out.println(dateMapped.getValue());


    }

    public static class ValueHolder<E>{
        private final E e;

        public ValueHolder(E e) {
            this.e = e;
        }

        public E getValue(){
            return e;
        }

        public <U> ValueHolder<U> map(Function<? super E,U> mapper){

            U returnedValue = mapper.apply(e);
            return new ValueHolder<>(returnedValue);
        }

        public String desc(){
            return "I am value holder of "+e.toString();
        }
    }

    public static class MyList<E> {

        private E e;

        public MyList(E e) {
            this.e = e;
        }

        public static <U> MyList<U> clone(MyList old){

            return null;
        }

        public void add(E e){
            //;
        }
        public E remove(E e){
            //;

            return null;
        }
    }

}
