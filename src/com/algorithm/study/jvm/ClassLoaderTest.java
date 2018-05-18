package com.algorithm.study.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jj-ly on 2017/12/4.自定义一个类加载器，加载类
 * 目的是实践：同一个类经过不同的类加载器加载，这两个类会变得不相等
 */
public class ClassLoaderTest {
    public static  void main(String[] args) throws Exception{
        ClassLoader myLoader  = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try{
                    String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is==null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (IOException e){
                    throw  new ClassNotFoundException(name);
                }
            }
        };

        Object obj=myLoader.loadClass("com.algorithm.study.jvm.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.algorithm.study.jvm.ClassLoaderTest);
    }
}
