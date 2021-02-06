package main.java.java0;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
//methods6
public class HomeWorkTotalWays5 {
    public static void main(String[] args) {
        MyThread4 myThread4 =new MyThread4(Thread.currentThread());
        myThread4.start();
        LockSupport.park(Thread.currentThread());
        System.out.println("我是主线程，我已经执行完了");
    }
}
class  MyThread4 extends Thread {
    private Thread thread;
    public  MyThread4(Thread thread){
        this.thread =thread;
    }
    @Override
    public void run() {
            System.out.println(new Random().nextInt());
            LockSupport.unpark(thread);
    }
}