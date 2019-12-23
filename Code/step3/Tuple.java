package step3;

public class Tuple<T,U> {

    public final T _1;
    public final U _2;

    public Tuple(T _1, U _2) {
        this._1 = _1;
        this._2 = _2;
    }

    public T get_1() {
        return _1;
    }

    public U get_2() {
        return _2;
    }
}
