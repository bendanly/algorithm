package com.algorithm.study.jvm.classinit;

public class JVMInitializationTest3 {
	public static void main(String[] args){

		//测试时，下面两个方法分开执行，一个不会打印static语句块中的输出，一个会
		System.out.println(JVMInitializationTest2.FS_STRING);//这个属性是static+final的会在class的ConstantValue属性中，类的准备阶段即会被赋值。不会在初始化clinit方法中赋值，不属于要求类强制初始化四种的情况之一
		System.out.println(JVMInitializationTest2.string);//这个属性时static属性，不会再constantVa属性表中，静态语句块调用属于强制类初始化的四种情况之一，会在clinit方法中赋值，会导致类进行初始化动作

	}
}
