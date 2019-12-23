package step5_genric;

import java.util.function.Function;

@FunctionalInterface
public interface MyFunction<T,U> {

    public U apply(T t);


    default <V> Function<T,V> andThen(Function<? super U,? extends V> after){
        return null;
    }

}
