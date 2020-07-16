package com.example.java8tutorial;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

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



    public interface People {
        void come(Person person);
    }
    @Data
    class Person {
        private String name;
        private Integer age;

        public Integer getAge() {
            return age;
        }

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public Person(Integer integer) {
            this.age = integer;
        }


        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public void print(Person person) {
        System.out.println("person = " + person);
    }
    @Test
    public void testConsumerAndInterfaceFunction() {
        Function<Integer, Person> personCreator1 = Person::new;
        Person persons1 = personCreator1.apply(5);
        System.out.println(persons1.getAge());

        BiFunction<String,Integer, Person> bi = Person::new;//(x,y) -> new Person(x,y);
        Function<Integer, Person[]> personCreator = Person[]::new; //t -> new Person[t];
        Person[] persons = personCreator.apply(5);
        System.out.println(persons.length);

        Consumer<People> consumer = people -> {
            people.come(new Person("李四", 23));
            people.come(new Person("找钱", 34));
            people.come(new Person("孙俪", 45));
            people.come(new Person("孙俪", 45));
        };

                consumer.accept(this::print);
    }
    @Test
    void ttt() {
        List<String> a1 = Arrays.asList("a", "b", "c");

        for (String a : a1) {
            printValur(a);
        };

        a1.forEach(x -> LambadaTest.printValur(x));

        Consumer<String> consumer = LambadaTest::printValur;
        a1.forEach(x -> consumer.accept(x));

        a1.forEach(LambadaTest::printValur);



    }

    public static void printValur(String str) {
        System.out.println("print value : " + str);
    }
}
