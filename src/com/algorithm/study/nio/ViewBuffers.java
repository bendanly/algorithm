package com.algorithm.study.nio;

import java.nio.*;

/**
 * Created by liyang on 2018/5/7.
 */
public class ViewBuffers {
    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.wrap(new byte[]{0,0,0,0,0,0,0,'a'});
        buffer.rewind();
        System.out.println("Byte Buffer");
        while(buffer.hasRemaining()){
            System.out.print(buffer.position()+"->"+buffer.get()+",");
        }
        System.out.println("");

        CharBuffer charBuffer = ((ByteBuffer)buffer.rewind()).asCharBuffer();
        System.out.println("Char Buffer");
        while(charBuffer.hasRemaining()){
            System.out.print(charBuffer.position()+"->"+charBuffer.get()+",");
        }
        System.out.println("");

        FloatBuffer floatBuffer= ((ByteBuffer)buffer.rewind()).asFloatBuffer();
        System.out.println("Float Buffer");
        while(floatBuffer.hasRemaining()){
            System.out.print(floatBuffer.position()+"->"+floatBuffer.get()+",");
        }
        System.out.println("");

        IntBuffer intBuffer = ((ByteBuffer)buffer.rewind()).asIntBuffer();
        System.out.println("Int Buffer");
        while(intBuffer.hasRemaining()){
            System.out.print(intBuffer.position()+"->"+intBuffer.get()+",");
        }
        System.out.println("");

        LongBuffer longBuffer = ((ByteBuffer)buffer.rewind()).asLongBuffer();
        System.out.println("Long Buffer");
        while(longBuffer.hasRemaining()){
            System.out.print(longBuffer.position()+"->"+longBuffer.get()+",");
        }
        System.out.println("");

        ShortBuffer shortBuffer = ((ByteBuffer)buffer.rewind()).asShortBuffer();
        System.out.println("Short Buffer");
        while(shortBuffer.hasRemaining()){
            System.out.print(shortBuffer.position()+"->"+shortBuffer.get()+",");
        }
        System.out.println();

        DoubleBuffer doubleBuffer = ((ByteBuffer)buffer.rewind()).asDoubleBuffer();
        System.out.println("Double buffer");
        while(doubleBuffer.hasRemaining()){
            System.out.print(doubleBuffer.position()+"->"+doubleBuffer.get()+",");
        }
    }
}
