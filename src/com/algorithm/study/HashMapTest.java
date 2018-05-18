package com.algorithm.study;

import java.util.HashMap;

public class HashMapTest {
	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
	 static final int tableSizeFor(int cap) {
	        int n = cap - 1;
	        n |= n >>> 1;
	        n |= n >>> 2;
	        n |= n >>> 4;
	        n |= n >>> 8;
	        n |= n >>> 16;
	        return (n < 0) ? 1 :  n + 1;
	    }
	public static void main(String[] args){
		HashMap<String, String> hashMap= new HashMap<>();
		String tString = "1000";
		int i = HashMapTest.hash(tString);
		int j = tString.hashCode();
		int k = j>>>16;
		int l = j^k;
		for(int s=0;s<18;s++){
			hashMap.put("test"+s, "test"+s);
		}
		
		System.out.println("i->"+Integer.toBinaryString(i)+"   j->"+Integer.toBinaryString(j)+"   k->"+Integer.toBinaryString(k)+"   l->"+Integer.toBinaryString(l));
		System.out.println(tableSizeFor(1025)+""+Integer.toBinaryString(18));		
		
	}
}
