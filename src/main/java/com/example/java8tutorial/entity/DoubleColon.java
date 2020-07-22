package com.example.java8tutorial.entity;

/**
 * @author: fulj
 * @created: 2020/07/19 21:50
 */
public class DoubleColon {
    public static void printStr(String str) {
        System.out.println("printStr : " + str);
    }

    public void toUpper(){
        System.out.println("toUpper : " + this.toString());
    }

    public void toLower(String str){
        System.out.println("toLower : " + str);
    }

    public int toInt(String str){
        System.out.println("toInt : " + str);
        return 1;
    }
}
