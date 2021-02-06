package main.java.java0;

import java.util.Random;

public class HomeWorkTotalWays {

    public static Integer call() {
        return (new Random().nextInt());
    }

    private static volatile Integer result;
    public static void method1(){

        new Thread(()->result=call()).start();
        while (result==null){
            System.out.println("子线程现在还没有执行完结果");
        }
        System.out.println(result);
        System.out.println("主线程执行结束");
    }

    public static void method2() throws InterruptedException {
      Thread thread1 = new Thread(()->result=call());
      thread1.start();
      thread1.join();
    }


    public static void main(String[] args) throws InterruptedException {
        method1();
        method2();
//        MyThread thread1 =new MyThread();
//        thread1.start();
//        thread1.join();
    }

}
class  MyThread extends Thread{
    @Override
    public void run(){

        System.out .println(new Random().nextInt());
    }
}
