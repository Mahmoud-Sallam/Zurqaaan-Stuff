package functionalExample;

public interface IntFunction {

    public int apply(int number);

    default IntFunction compose(IntFunction another){
        return x->another.apply(apply(x));
    }

}
