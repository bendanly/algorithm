package com.algorithm.study.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by liyang on 2018/5/8.
 * 简单的算法：交换相邻字符实现对CharBuffer的编码和解码
 */
public class UsingBuffers {
    public static void symmetricScramble(CharBuffer charBuffer){
        while(charBuffer.hasRemaining()){
            //1调用mark()来设置mark标记（定位到postion位置）
            charBuffer.mark();
            //连续获取c1 c2后，position位置在第三个位置（值为2）
            char c1 = charBuffer.get();
            char c2 = charBuffer.get();
            //调用reset()将position的值设置为mark值
            charBuffer.reset();
            //调用两次put()将值写入，同时position移动到第三个位置（值为2），再次循环
            charBuffer.put(c2).put(c1);
        }
    }
    public static void main(String[] args){
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer buffer = ByteBuffer.allocate(data.length*2);
        CharBuffer charBuffer = buffer.asCharBuffer();
        charBuffer.put(data);
        System.out.println(charBuffer.rewind());
        symmetricScramble(charBuffer);
        System.out.println(charBuffer.rewind());
        symmetricScramble(charBuffer);
        System.out.println(charBuffer.rewind());
    }
}
