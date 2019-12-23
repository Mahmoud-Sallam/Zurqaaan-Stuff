package functionalExample;

public interface MyPredicate<T> {
    boolean apply(T t);

    default MyPredicate<T> and(MyPredicate<T> another){
        return (T x)->apply(x) && another.apply(x);
    }

    default MyPredicate<T> or(MyPredicate<T> another){
        return (T x)->apply(x) || another.apply(x);
    }
}
