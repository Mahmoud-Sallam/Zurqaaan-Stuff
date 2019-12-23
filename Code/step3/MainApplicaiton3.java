package step3;

import java.util.function.Function;

public class MainApplicaiton3 {


    public static Function<Integer,Integer> add5(){

        int c = 5;

        Function<Integer,Integer> returnResult = x->x+c;
        return returnResult;

    }

    public static void main(String[] args) {
        Function<Integer, Integer> integerIntegerFunction = add5();
        System.out.println(integerIntegerFunction.apply(10));
        System.out.println(add5().apply(15));
    }
}
