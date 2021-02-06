package main.java.java0;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

//methods7
public class HomeWorkTotalWays6 {
    public static void main(String[] args) {
        CountDownLatch count =new CountDownLatch(1);
        task task =new task(count);
        Thread thread =new Thread(task);
        thread.start();
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我是主线程我已经执行完了");
    }
}
class task implements Runnable{
    CountDownLatch countDownLatch;
    public  task(CountDownLatch countDownLatch){
        this.countDownLatch =countDownLatch;
    }
    @Override
    public void run() {

        System.out .println(new Random().nextInt());
        countDownLatch.countDown();
    }
}