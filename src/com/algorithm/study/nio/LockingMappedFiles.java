package com.algorithm.study.nio;

import sun.applet.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.ThreadPoolExecutor;

import static java.io.File.separatorChar;

/**
 * Created by liyang on 2018/5/8.
 * 两个线程，分别加锁文件不同的位置
 */
public class LockingMappedFiles {
    static final int LENGTH = 0x8000000;//128M
    static FileChannel fileChannel;

    public static void main(String[] args) throws IOException {
        fileChannel = new RandomAccessFile("D:"+ separatorChar+"temp.tmp","rw").getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,LENGTH);
        for(int i =0;i<LENGTH;i++){
            mappedByteBuffer.put((byte)'x');
        }
        new LockAndModify(mappedByteBuffer,0,0+LENGTH/3);
        new LockAndModify(mappedByteBuffer,LENGTH/2,LENGTH/2+LENGTH/4);
    }
    private static class LockAndModify extends Thread{
        private ByteBuffer byteBuffer ;
        private int start,end;
        LockAndModify(ByteBuffer byteBuffer,int start,int end){
            this.start = start;
            this.end = end;
            byteBuffer.limit(end);
            byteBuffer.position(start);
            this.byteBuffer = byteBuffer.slice();
            start();
        }
        public void run(){
            try{
                FileLock fileLock = fileChannel.lock(start,end,false);
                System.out.println("Locked: "+start+" to "+end);
                while(byteBuffer.position()<byteBuffer.limit()-1){
                    byteBuffer.put((byte)(byteBuffer.get()+1));
                }
                fileLock.release();
                System.out.println("Released: "+start+" to "+end);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
