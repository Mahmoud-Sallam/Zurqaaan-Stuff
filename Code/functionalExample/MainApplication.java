package functionalExample;

public class MainApplication {


    public static void main(String[] args) {





        MyFunction<String,Integer> lengthOfString = x-> x.length();
        MyFunction<Integer,Boolean> isEven = x -> x % 2 == 0;
        MyFunction<Boolean,Integer> intBoolean= x->x?1:0;

        MyFunction<String, Boolean> funciton1 = lengthOfString.composite(isEven);

        MyFunction<String, Integer> function2 = funciton1.composite(intBoolean);


        MyFunction<Integer, Integer> function3 = isEven.composite(intBoolean);
        System.out.println(funciton1.apply("Test2"));
        System.out.println(function2.apply("Test2"));
        System.out.println(function3.apply(10));

        //String -> String To uppercase
        MyFunction<String,String> toUpper = x->x.toUpperCase();

        //String -> length
        MyFunction<String,Integer> length  = x->x.length();

        // Integer -> Boolean
        MyFunction<Integer,Boolean> isEvenLatest = x->x%2==0;

        // String -> boolean is number of cha is even
        MyFunction<String, Boolean> composite = toUpper.composite(length).composite(isEven);

        MyPredicate<Integer> isEven2 = x->x%2==0;
        MyPredicate<Integer> isGT100 = x->x>100;

        MyPredicate<Integer> togatherAnd = isEven2.and(isGT100);
        MyPredicate<Integer> togatherOr = isEven2.or(isGT100);
//        System.out.println(togatherAnd.apply(16));
//        System.out.println(togatherAnd.apply(15));
//        System.out.println(togatherAnd.apply(160));
        System.out.println(togatherOr.apply(16));
        System.out.println(togatherOr.apply(153));
        System.out.println(togatherOr.apply(15));
        System.out.println(togatherOr.apply(160));


        MyFunction<Integer,MyFunction<Integer,Integer>> f=x->y->x+y;

        MyFunction<String,MyFunction<String,String>> f2= x->y->x+y;

        System.out.println(f2.apply("A").apply("B"));

        MyFunction<Boolean,MyFunction<String,String>> f3= y->x->y?(x+" are allowed to access the system"):("Access Denied for "+x);

        System.out.println(f3.apply(true).apply("Mohamamd"));

        MyFunction<String, String> messageForAcccessDenied = f3.apply(false);

        System.out.println(messageForAcccessDenied.apply("Mosa"));



    }
}
