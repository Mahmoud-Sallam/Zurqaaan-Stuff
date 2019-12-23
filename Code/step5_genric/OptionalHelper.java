package step5_genric;

import java.util.Optional;
import java.util.function.Function;

public class OptionalHelper {


    public static <U,T> Optional<U> map(Optional<T> opt, Function<? super T,? extends U> mapper){
        if(opt.isPresent()) {
            U u = mapper.apply(opt.get());
            return  Optional.of(u);
        }

        return Optional.empty();
    }

    public static <U,T> Optional<U> map2(Optional<T> opt, Function<? super T,? extends U> mapper){
        return opt.map(mapper);
    }

    public static <U,T> Optional<U> flatMap(Optional<T> opt, Function<T,Optional<U>> flatMapFunc){

        return null;
    }

    public static <U,T> Optional<U> flatMap2(Optional<T> opt, Function<T,Optional<U>> flatMapFunc){

        return null;
    }

}
