package com.algorithm.study;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

import javax.print.attribute.standard.PrinterLocation;

public class Test2 extends Test{
 public Test2() {
	super();
}


	public void doSome(){
	 System.out.println("this is test2");
 }
 
 private static volatile Boolean hasInit = new Boolean(false);
 
 public static void main(String[] args){
	 //因为父类Test中doSome没有声明为final导致方法被子类Test2覆盖，调用子类的构造函数时，父类的构造函数先被调用，而且逻辑已被子类的doSome覆盖
	 Test  t  = new Test2();	 
	 
	 String t1 = new String("");
	 String t2 = "abc";
	 if(t1!=""){
		 System.out.println("test");
	 }
	 LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
	 hashMap.forEach((k,v)-> {System.out.println(k);System.out.println(v);});
	 
 }
}
