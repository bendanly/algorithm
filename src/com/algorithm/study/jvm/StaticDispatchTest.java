package com.algorithm.study.jvm;

import java.io.Serializable;

/**
 * Created by liyang on 2017/12/6.
 * 目的是实践：静态分派确定调用方法，重载调用的判断依据
 */
public class StaticDispatchTest {
    static abstract class  Human{

    }
    static class  Man extends  Human{

    }
    static class  Women extends  Human{

    }
    public void sayHello(Human guy){
        System.out.println("hello guy");
    }
    public void sayHello(Man man){
        System.out.println("hello gentleman");
    }
    public void sayHello(Women women){
        System.out.println("hello lady");
    }

    /*public static void sayHello(Object arg){
        System.out.println("hello object");
    }*/
    /*public static void sayHello(int arg){
        System.out.println("hello int");
    }*/
    /*public static void sayHello(long arg){
        System.out.println("hello long");
    }*/
    /*public static void sayHello(Character arg){
        System.out.println("hello Character");
    }*/
    /*public static void sayHello(char arg){
        System.out.println("hello char");
    }*/
    public static void sayHello(char... arg){
        System.out.println("hello char...");
    }
    /*public static void sayHello(Serializable arg){
        System.out.println("hello Serializable");
    }*/

    public static void main(String[] args){
        ///////////////////示例1，演示静态分派/////////////////////////
        Human man = new Man();//Human为静态类型，Man为实际类型。 静态类型在编译期可知，实际类型变化的结果在运行期才确定
        Human women = new Women();
        StaticDispatchTest staticDispatchTest= new StaticDispatchTest();
        //重载是根据编译期的静态类型就确定的方法的重载版本，所以选择了sayHello(Human guy)
        staticDispatchTest.sayHello(man);    //输出 hello guy
        staticDispatchTest.sayHello(women);  //输出 hello guy
        /////////////////示例2,演示静态分派的模糊性
        staticDispatchTest.sayHello('a');//会输出hello char,但是如果把sayHello(char arg)重载去掉，会显示hello int。如果挨个注释掉，会按照int->long->Character->Serializable->Object->char...顺序依次调用
    }
}
