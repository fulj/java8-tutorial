package com.example.java8tutorial;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * @author: fulj
 * @created: 2020/07/14 20:31
 */
public class LambadaTest {
//    https://juejin.im/post/5b7fc5aaf265da4339672c76
    @Test
    void testCosumer() {
        System.out.println("sss");
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Hello " + s);
            }
        };
        consumer.accept("李磊");
    }
}
