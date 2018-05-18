package com.algorithm.study.jvm;

/**
 * Created by liyang on 2017/12/11.
 */
public class DynamicDispatchTest {
    static abstract  class  Human{
        protected  abstract  void sayhello();
    }
    static class  Man extends  Human{

        @Override
        protected void sayhello() {
            System.out.println("man say hello");
        }
    }
    static  class  Women extends  Human{

        @Override
        protected void sayhello() {
            System.out.println("women say hello");
        }
    }
    public  static  void  main(String[] args){
        Human man = new Man();
        Human women = new Women();
        man.sayhello();
        women.sayhello();

        man=new Women();
        man.sayhello();
    }
}
