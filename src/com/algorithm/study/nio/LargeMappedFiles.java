package com.algorithm.study.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static java.io.File.separatorChar;

/**
 * Created by liyang on 2018/5/8.
 * 大文件读写
 */
public class LargeMappedFiles {
    //static int length=0x8FFFFFF;//128m
    static int length=0x8000000;//128m
    public static  void main(String[] args) throws IOException {
        MappedByteBuffer mappedByteBuffer=new RandomAccessFile("D:"+ separatorChar+"data3.txt","rw").getChannel().map(FileChannel.MapMode.READ_WRITE,0,length);
        for(int i=0;i<length;i++){
            mappedByteBuffer.put((byte)'X');
        }
        System.out.println("finish writing");
        for (int i=length/2;i<length/2+6;i++){
            System.out.println((char)mappedByteBuffer.get(i));
        }
    }
}
