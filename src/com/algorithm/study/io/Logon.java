package com.algorithm.study.io;

import java.io.*;
import java.util.Date;

import static java.io.File.separatorChar;

/**
 * Created by liyang on 2018/5/10.
 */
public class Logon implements Serializable {
    private Date date = new Date();
    private String name ;
    private transient  String password;
    public Logon(String name,String pwd){
        this.name= name;
        this.password= pwd;
    }
    public String toString(){
        return "Logon info:\n username->"+name+"\n password->"+password+"\n date->"+date;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Logon logon=new Logon("liyang","123456");
        System.out.println(logon);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:"+ separatorChar+"Logon.out"));
        objectOutputStream.writeObject(logon);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:"+ separatorChar+"Logon.out"));
        logon = (Logon)objectInputStream.readObject();
        System.out.println(logon);
    }
}
