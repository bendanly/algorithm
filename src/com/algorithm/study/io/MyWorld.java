package com.algorithm.study.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyang on 2018/5/10.
 * 序列化实验深度拷贝
 */
class House implements Serializable{}
class Animal implements Serializable{
    private  String name;
    private House preferredHouse;
    Animal(String name,House house){
        this.name=name;
        this.preferredHouse=house;
    }
    public String toString(){
        return name+" ["+super.toString()+"] "+preferredHouse+"\n";
    }
}
public class MyWorld {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> list = new ArrayList<>();
        list.add(new Animal("first one",house));
        list.add(new Animal("second one",house));
        list.add(new Animal("third one",house));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(list);
        //连续再写一次
        objectOutputStream.writeObject(list);
        objectOutputStream.close();

        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(byteArrayOutputStream1);
        objectOutputStream1.writeObject(list);
        objectOutputStream1.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        ObjectInputStream objectInputStream1 = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream1.toByteArray()));
        List<Animal>
                list1 = (List<Animal>) objectInputStream.readObject(),
                list2 = (List<Animal>)objectInputStream.readObject(),//相同流输出的对象引用地址将会和第一次相同，没有深度拷贝
                list3 = (List<Animal>)objectInputStream1.readObject();//不同流输出的对象引用地址会变化，实现了深度拷贝
        System.out.println("list1->"+list1);
        System.out.println("list2->"+list2);
        System.out.println("list3->"+list3);

    }
}
