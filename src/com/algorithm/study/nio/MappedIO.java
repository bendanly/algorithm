package com.algorithm.study.nio;

import com.algorithm.study.Test;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static java.io.File.separatorChar;

/**
 * Created by liyang on 2018/5/8.
 * 比较新旧IO的性能
 */
public class MappedIO {
    private static int numOfInts = 4000000;
    private static int numOfUbuffInts = 200000;
    private abstract  static class Tester{
        private String name;
        public Tester(String name){this.name= name;}
        public void runTest(){
            System.out.print(name+":");
            try{
                long begin = System.currentTimeMillis();
                test();
                long end = System.currentTimeMillis();
                System.out.println((end-begin)+" ms");
            }catch (IOException ex){
                throw new RuntimeException(ex);
            }
        }
        public abstract  void test() throws IOException;
    }
    public static Tester[] testers = {
            new Tester("Stream write"){

                @Override
                public void test() throws IOException {
                    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("D:"+ separatorChar+"temp.tmp"))));
                    for(int i=0;i<numOfInts;i++){
                        dataOutputStream.writeInt(i);
                    }
                    dataOutputStream.close();
                }
            },
            new Tester("Mapped wirte") {
                @Override
                public void test() throws IOException {
                    FileChannel fileChannel = new RandomAccessFile("D:"+ separatorChar+"temp.tmp","rw").getChannel();
                    IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,fileChannel.size()).asIntBuffer();
                    for(int i=0;i<numOfInts;i++){
                        intBuffer.put(i);
                    }
                    fileChannel.close();
                }
            },
            new Tester("Stream read") {
                @Override
                public void test() throws IOException {
                    DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("D:"+ separatorChar+"temp.tmp")));
                    for(int i =0;i<numOfInts;i++){
                        dataInputStream.readInt();
                    }
                    dataInputStream.close();
                }
            },
            new Tester("Mapped read") {
                @Override
                public void test() throws IOException {
                    FileChannel fileChannel = new FileInputStream("D:"+ separatorChar+"temp.tmp").getChannel();
                    IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY,0,fileChannel.size()).asIntBuffer();
                   while (intBuffer.hasRemaining()){
                       intBuffer.get();
                   }
                    fileChannel.close();
                }
            },
            new Tester("Stream read/write") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(new File("D:"+ separatorChar+"temp.tmp"),"rw");
                    randomAccessFile.writeInt(1);
                    for (int i =0;i<numOfUbuffInts;i++){
                        randomAccessFile.seek(randomAccessFile.length()-4);
                        randomAccessFile.writeInt(randomAccessFile.readInt());
                    }
                    randomAccessFile.close();
                }
            },
            new Tester("Mapped read/write") {
                @Override
                public void test() throws IOException {
                    FileChannel fileChannel = new RandomAccessFile(new File("D:"+ separatorChar+"temp.tmp"),"rw").getChannel();
                    IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,fileChannel.size()).asIntBuffer();
                    intBuffer.put(0);
                    for(int i=1;i<numOfUbuffInts;i++){
                        intBuffer.put(intBuffer.get(i-1));
                    }
                    fileChannel.close();
                }
            }
    };
    public static  void main(String[] args){
        for (Tester t:testers) {
            t.runTest();
        }
    }
}
