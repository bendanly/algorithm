package com.algorithm.study;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class HasStatic {
	private static int x = 50;
	static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return  n+1  ;
    }
	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
	public static void main(String args[]) {
		HasStatic hs1 = new HasStatic();
		hs1.x++;
		final HasStatic hs2 = new HasStatic();
		hs2.x++;
		hs1 = new HasStatic();
		hs1.x++;
		HasStatic.x--;
		System.out.println("x=" + x);
		ThreadLocal<String> threadLocal =new ThreadLocal<String>();
		int n =13;
		 n=n>>1;
	       
		System.out.println(("test").hashCode());
		
	}
}
