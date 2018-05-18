package com.algorithm.study.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by liyang on 2018/5/4.
 */
public class TransferTo {
    public static void main(String[] args) throws IOException {
        String sourceFile = "H:" + File.separatorChar + "software" + File.separatorChar + "jboss-5.1.0.GA-jdk6.zip";
        String destFile = "D:" + File.separatorChar + "jboss-5.1.0.GA-jdk6.zip";

        FileChannel in = new FileInputStream(sourceFile).getChannel(),
                out = new FileOutputStream(destFile).getChannel();
        long begin = System.currentTimeMillis();
        in.transferTo(0, in.size(), out);
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-begin)+"毫秒");
    }
}
