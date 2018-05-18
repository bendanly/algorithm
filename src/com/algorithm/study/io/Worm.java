package com.algorithm.study.io;

import java.io.*;
import java.util.Random;

import static java.io.File.separatorChar;

/**
 * Created by liyang on 2018/5/9.
 * 序列化和反序列化
 */
class Data implements Serializable{
    private int n;
    public Data(int n){this.n=n;}
    public String toString(){
        return Integer.toString(n);
    }
}
public class Worm implements  Serializable{
    private static Random random = new Random(47);
    private Data[] data = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10))
    };
    private Worm next;
    private char c;
    public Worm(int i,char c){
        System.out.println("Worm constructor: "+i);
        this.c = c;
        if(--i>0){
            next = new Worm(i,(char)(c+1));
        }
    }
    public Worm(){
        System.out.println("Default constructor");
    }
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder(":");
        stringBuilder.append(c);
        stringBuilder.append("(");
        for(Data d:data){
            stringBuilder.append(d);
        }
        stringBuilder.append(")");
        if(next!=null){
            stringBuilder.append(next);
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Worm worm = new Worm(6,'a');
        System.out.println("worm = "+worm);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:"+ separatorChar+"worm.out"));
        objectOutputStream.writeObject("worm storage\n");
        objectOutputStream.writeObject(worm);
        objectOutputStream.close();

        ObjectInputStream objectInputStream =new ObjectInputStream(new FileInputStream("D:"+ separatorChar+"worm.out"));
        String s = (String)objectInputStream.readObject();
        Worm worm1 = (Worm)objectInputStream.readObject();
        System.out.println(s+" worm1 = "+worm1);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream1.writeObject("worm  storage\n");
        objectOutputStream1.writeObject(worm1);
        objectOutputStream1.flush();

        ObjectInputStream objectInputStream1 = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        s = (String)objectInputStream1.readObject();
        Worm worm2 = (Worm)objectInputStream1.readObject();
        System.out.println(s+" worm2 = "+worm2);
    }
}
