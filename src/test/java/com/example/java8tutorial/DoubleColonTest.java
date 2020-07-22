package com.example.java8tutorial;

import com.example.java8tutorial.entity.DoubleColon;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author: fulj
 * @created: 2020/07/19 21:48
 */
public class DoubleColonTest {

    //非静态方法的第一个参数为被调用的对象，后面是入参。静态方法因为jvm已有对象，直接接收入参。
    //用::提取的函数，最主要的区别在于静态与非静态方法，非静态方法比静态方法多一个参数，就是被调用的实例。
    //参数只能用包装类
    @Test
    void test1() {
        Consumer<String> consumer = DoubleColon::printStr;
        consumer.accept("sdsd");
    }
    @Test
    void test2() {
        Consumer<DoubleColon> consumer = DoubleColon::toUpper;
        consumer.accept(new DoubleColon());
        BiConsumer<DoubleColon, String> consumer1 = DoubleColon::toLower;
        consumer1.accept(new DoubleColon(), "sdsfdsfds");
        BiFunction<DoubleColon, String, Integer> function = DoubleColon::toInt;
        int x = function.apply(new DoubleColon(), "sssss");
        System.out.println(x);
    }




}
