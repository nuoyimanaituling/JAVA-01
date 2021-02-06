package main.java.java0;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;

//methods8
public class HomeWorkTotalWays7 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier =new CyclicBarrier(2);
        MyThread5 myThread5 =new MyThread5(cyclicBarrier);
        myThread5.start();
        cyclicBarrier.await();
        Thread.sleep(1);
        System.out.println("我是主线程我执行完了");

    }

}
class  MyThread5 extends Thread {
    CyclicBarrier cyclicBarrier;
    public MyThread5(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier =cyclicBarrier;
    }
    @Override
    public void run() {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(new Random().nextInt());

    }
}