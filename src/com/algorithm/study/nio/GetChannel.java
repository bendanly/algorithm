package com.algorithm.study.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by liyang on 2018/5/4.
 */
public class GetChannel {
    private static final int BSIZE=1024;
    public static void main(String[] args) throws IOException {
        FileChannel fileChannel =new  FileOutputStream("D:"+File.separatorChar+"data.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("some txt ".getBytes()));
        fileChannel.close();

        fileChannel = new RandomAccessFile("D:"+File.separatorChar+"data.txt","rw").getChannel();
        fileChannel.position(fileChannel.size());
        fileChannel.write(ByteBuffer.wrap("more txt".getBytes()));
        fileChannel.close();

        fileChannel = new FileInputStream("D:"+File.separatorChar+"data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fileChannel.read(buffer);
        buffer.flip();
        while(buffer.hasRemaining()){
            System.out.print((char)buffer.get());
        }
        fileChannel.close();
    }
}
