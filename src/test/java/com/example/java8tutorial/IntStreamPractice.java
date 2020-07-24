package com.example.java8tutorial;

import org.junit.jupiter.api.Test;

import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author: fulj
 * @created: 2020/07/23 14:53
 * https://blog.csdn.net/qq_31865983/article/details/106443244
 */
public class IntStreamPractice {
    @Test
    public void test() throws Exception {
        //包含指定的元素
//        IntStream intStream=IntStream.of(1);
        //返回的int流中的元素是已经排序好的
        IntStream intStream=IntStream.of(1,3,2,5,4,6);
        print("of",intStream);

        //从11到16,不包含16
        intStream=IntStream.range(11,16);
        //从11到16,包含16
//        intStream=IntStream.rangeClosed(11,16);
        print("range",intStream);

        //包含指定的元素,add方法底层也是调用accept方法，然后返回this
        //返回的int流中的元素顺序与添加顺序一致
        intStream=IntStream.builder().add(23).add(22).add(21).build();
        print("builder", intStream);

        //指定一个int生成函数
        //返回的int流中的元素不排序
        intStream=IntStream.generate(()->{
            Random random=new Random();
            return random.nextInt(100);
        }).limit(6);
        print("generate", intStream);

        //指定一个int生成函数，前一次执行函数的结果会作为下一次调用函数的入参
        //第一个参数seed就是第一次调用生成函数的入参
        //返回的int流中的元素不排序
        intStream=IntStream.iterate(1,x->{
            int a=2*x;
            if(a>16){
                return a-20;
            }else{
                return a;
            }
        }).limit(6);
        print("iterate", intStream);
    }

    @Test
    public void test2() throws Exception {
        IntStream streamA=IntStream.range(11,15);
        IntStream streamB=IntStream.range(6,10);
        //将两个IntStream 合并起来
        //返回的int流的元素顺序与添加的流的元素顺序一致，不排序
        IntStream streamC=IntStream.concat(streamA,streamB);
        print("concat", streamC);
    }

    private void print(String start, IntStream intStream){
        System.out.println("print for->"+start);
        intStream.forEach(x->{
            System.out.println(x);
        });
    }

    @Test
    public void test3() throws Exception {
        IntStream intStream=IntStream.rangeClosed(1, 10);
        //会保留过滤函数返回true的元素，此处是保留偶数
        intStream=intStream.filter(x->{
            return x%2==0;
        }).peek(x->{ //peek方法指定的函数，以流中的元素为入参，无返回值，即不会修改元素本身
            System.out.println("filter->"+x);
        });
        //对流中的所有元素执行某个修改动作，此处是将所有值加1
//        intStream=intStream.map(x->{
//            return x+1;
//        }).peek(x->{
//            System.out.println("map->"+x);
//        });
//
//        //flatMap同map，区别在于flatMap指定的函数其返回值是一个IntStream，而非一个int值，最终flatMap返回的
//        //IntStream是将每次调用flatMap返回的子IntStream合并后的结果
//        intStream=intStream.flatMap(x->{
//            //返回IntStream时可以返回多个元素
//            return IntStream.of(x+3,x+2,x+1);
//        }).peek(x->{
//            System.out.println("flatMap->"+x);
//        });
//
        print("after flatMap", intStream);
    }


    @Test
    public void test7() throws Exception {
        IntStream intStream=IntStream.of(6,1,3,2,5,4);
        OptionalInt optionalInt=intStream.reduce((x, y)->{
            System.out.println("x->"+x+",y->"+y);
            return x+y;
        });
        System.out.println("result->"+ ((OptionalInt) optionalInt).getAsInt());

        System.out.println("");

        intStream=IntStream.of(6,1,3,2,5,4);
        //同第一个reduce方法，区别在于可以指定起始的left，第一个reduce方法使用第一个元素作为起始的left
        int result=intStream.reduce(2,(x, y)->{
            System.out.println("x->"+x+",y->"+y);
            return x+y;
        });
        System.out.println("result->"+result+"\n");

        intStream=IntStream.of(6,1,3,2,5,4);
        //同forEach方法，首先调用supplier函数生成一个值，将该值作为accumulator函数的第一个参数，accumulator函数的第二个
        //参数就是流中的元素，注意第三个参数combiner无意义，可置为null
        result=intStream.collect(()->{
            Random random=new Random();
            return random.nextInt(10);
        },(x,y)->{
            System.out.println("ObjIntConsumer x->"+x+",y->"+y);
        },null);
        //返回值是supplier函数生成的值
        System.out.println("collect result->"+result+"\n");

    }
    /**
     * https://geek-docs.com/java/java-examples/java-uses-flatmap-and-map-method.html
     * https://segmentfault.com/q/1010000004681887
     * https://www.cnblogs.com/zgq7/p/11125419.html
     * https://segmentfault.com/q/1010000004681887
     * 简单来比喻的话，peek就是管道的其中一节，后面还可以继续接管道、过滤器、阀门等等，forEach就是水龙头，是最终消费了流了，用了forEach就不能接上其他东西了。
     * 总结：peek接收一个没有返回值的λ表达式，可以做一些输出，外部处理等。map接收一个有返回值的λ表达式，之后Stream的泛型类型将转换为map参数λ表达式返回的类型
     * java1.8--OptionalInt,OptionalDouble,OptionalLong类 https://blog.csdn.net/u011794238/article/details/49532717
     */

    @Test
    public void test14() throws Exception {
        IntStream intStream = IntStream.of(6, 1, 1, 2, 5, 2, 3, 4);

        //onClose方法的返回值是一个新的流，可以连续调用onClose，注册多个回调方法
        intStream.onClose(()->{
            System.out.println("intStream isClosed one ");
        }).onClose(()->{
            System.out.println("intStream isClosed two");
        }).onClose(()->{
            System.out.println("intStream isClosed three");
        });

        //触发onClose方法注册的多个回调方法的执行,并关闭流
        intStream.close();
        //流已关闭，不能执行流处理动作，forEach执行完成也会关闭流但是不会触发onClose方法的执行
//        intStream.forEach(x->{
//            System.out.println(x);
//        });
        System.out.println("main end");
    }


}
