package java8.lambda;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: fulj
 * @created: 2020/07/14 16:05
 */
public class FunctionExample {


    public static void main(String[] args) {
        Function<String, Integer> ss = s -> s.length();
        Function<Integer, Double> sss = dd -> dd / 2.0;
//        ss.compose(null);
//        System.out.println(Function.identity());
//
//        System.out.println(ss.apply("ssss"));
//        System.out.println(ss.andThen(sss).apply("3333"));
        identity();
    }

    private static void identity() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);
    }
}
