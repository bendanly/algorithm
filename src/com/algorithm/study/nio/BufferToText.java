package com.algorithm.study.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by liyang on 2018/5/4.
 * ByteBuffer缓冲器里是普通的字节，为了能转化为字符。要么在输入它们的时候对其编码，要么在将其从缓冲区输出时对它进行解码。
 */
public class BufferToText {
    public static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = new FileOutputStream("D:" + File.separatorChar + "data2.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("some text ".getBytes()));
        fileChannel.close();

        fileChannel = new FileInputStream("D:" + File.separatorChar + "data2.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fileChannel.read(buffer);
        buffer.flip();
        //因为字节流没有编码，输出的将是乱码；
        System.out.println(buffer.asCharBuffer());

        //方法一：移到传冲区开头，用Charset对buffer进行解码，也可以正确得到输出
        buffer.rewind();
        String enCoding = System.getProperty("file.encoding");
        System.out.println("Decode  using" + enCoding + ":" + Charset.forName(enCoding).decode(buffer));

        //方法二：重新写入文件，写入时对缓冲区进行UTF-16BE编码
        fileChannel = new FileOutputStream("D:" + File.separatorChar + "data2.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("more text".getBytes("UTF-16BE")));
        fileChannel.close();
        //再次正常读取，能正确输出
        fileChannel = new FileInputStream("D:" + File.separatorChar + "data2.txt").getChannel();
        buffer.clear();
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());

        //如果通过CharBuffer向ByteBuffer写入
        fileChannel = new FileOutputStream("D:" + File.separatorChar + "data2.txt").getChannel();
        buffer = ByteBuffer.allocate(24);//申请24个字节（12个字符）
        buffer.asCharBuffer().put("some text");//写入9个字符
        fileChannel.write(buffer);
        fileChannel.close();

        fileChannel = new FileInputStream("D:" + File.separatorChar + "data2.txt").getChannel();
        //buffer = ByteBuffer.allocate(BSIZE);
        buffer.clear();
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
    }
}
