package com.algorithm.study.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

import static java.io.File.separatorChar;

/**
 * Created by liyang on 2018/5/8.
 * 文件锁住
 */
public class FileLocking {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:"+ separatorChar+"data4.txt");
        FileLock fileLock = fileOutputStream.getChannel().tryLock();
        if(fileLock!=null){
            System.out.println("file is locked");
            TimeUnit.MILLISECONDS.sleep(10000);
            fileLock.release();
            System.out.println("file is unLocked");
        }
        fileLock.close();
    }
}
