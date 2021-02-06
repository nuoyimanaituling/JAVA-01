package main.java.java0;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
// method4
public class HomeWorkTotalWays3 {
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        final Condition notFull  = lock.newCondition();
        MyThread2 myThread2 =new MyThread2(notFull,lock);
        myThread2.start();
        lock.lock();
        try{
            notFull.await();
        }
        finally {
            lock.unlock();
        }
        System.out.println("主线程执行完");
    }
}
class  MyThread2 extends Thread {
    private Condition notFull;
    private Lock lock;
    public  MyThread2(Condition notFull,Lock lock){
        this.notFull =notFull;
        this.lock =lock;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
            try {
                System.out.println( new Random().nextInt());
                notFull.signalAll();
            }finally {
                lock.unlock();

            }
    }
}