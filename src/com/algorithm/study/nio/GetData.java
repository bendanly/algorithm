package com.algorithm.study.nio;

import java.nio.ByteBuffer;

/**
 * Created by liyang on 2018/5/5.
 */
public class GetData {
    public static final int BSIZE=1024;
    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        int i =0;
        while(i++<buffer.limit()){
            if(buffer.get()!=0){
                System.out.print("nonzero");
            }
        }
        System.out.print("i="+i);
        System.out.println("");

        buffer.rewind();
        buffer.asCharBuffer().put("hello word!");
        char c;
        while((c=buffer.getChar())!=0){
            System.out.print(c);
        }

        System.out.println("");
        buffer.rewind();
        buffer.asShortBuffer().put((short)471142);
        System.out.println(buffer.getShort());

        buffer.rewind();
        buffer.asIntBuffer().put(99471142);
        System.out.println(buffer.getInt());

        buffer.rewind();
        buffer.asLongBuffer().put(99471142);
        System.out.println(buffer.getLong());

        buffer.rewind();
        buffer.asFloatBuffer().put(99471142);
        System.out.println(buffer.getFloat());

        buffer.rewind();
        buffer.asDoubleBuffer().put(99471142);
        System.out.println(buffer.getDouble());
    }
}
