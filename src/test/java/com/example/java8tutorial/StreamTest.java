package com.example.java8tutorial;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: fulj
 * @created: 2020/07/19 21:26
 */
public class StreamTest {

//    https://juejin.im/post/59c7d39b6fb9a00a3d136291
    @Test
    void streamTest() {
        List<String> alpha = Arrays.asList("a", "b", "c", "d");

        //Before Java8
        List<String> alphaUpper = new ArrayList<>();
        for (String s : alpha) {
            alphaUpper.add(s.toUpperCase());
        }

        System.out.println(alpha); //[a, b, c, d]
        System.out.println(alphaUpper); //[A, B, C, D]

        // Java 8
        List<String> collect = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect); //[A, B, C, D]
        Function<String, String> function = String::toUpperCase;
        List<String> collect11 = alpha.stream().map(it -> it.toUpperCase()).collect(Collectors.toList());


        // Extra, streams apply to any data type.
        List<Integer> num = Arrays.asList(1,2,3,4,5);
        List<Integer> collect1 = num.stream().map(n -> n * 2).collect(Collectors.toList());
        System.out.println(collect1); //[2, 4, 6, 8, 10]
    }

    //https://www.baeldung.com/java-8-streams
    void streamTest1() {
        Stream<String> streamEmpty = Stream.empty();
        String concat = streamEmpty.collect(StringBuilder::new, StringBuilder::append,StringBuilder::append).toString();
    }
//    public Stream<String>
}
