package functionalExample;

public interface MyFunction<T,U> {

    public  U apply(T t);

    default<V> MyFunction<T,V> composite
            (MyFunction<U,V> another){
        return x->another.apply(apply(x));
    }
}
