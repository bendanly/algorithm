package com.algorithm.study.jvm.classinit;

public class JVMInitializationTest {
	static {
		System.out.println("JVMInitializationTest init");
	}
	public static int value = 1;
	public static String string = "test";
	public static final String FS_STRING = "fstring";
}
