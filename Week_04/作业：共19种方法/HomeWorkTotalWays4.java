package main.java.java0;


import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//methods5
public class HomeWorkTotalWays4 {
    static ReentrantReadWriteLock readWriteLock =new ReentrantReadWriteLock();
    static Lock w=readWriteLock.writeLock();

    public static void main(String[] args) throws InterruptedException {

        MyThread3 myThread3 =new MyThread3(w);
        myThread3.start();
        Thread.sleep(1);
        System.out.println("主线程已经执行完了");
    }
}


class  MyThread3 extends Thread {
   private Lock lock;
   public  MyThread3(Lock lock){
       this.lock =lock;
   }
    @Override
    public void run() {
       try {
           lock.lock();
           System.out.println(new Random().nextInt());
       }finally {
           lock.unlock();
       }
    }
}