package main.java.java0;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//methods9
public class HomeWorkTotalWays8 {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier =new CyclicBarrier(1,new Temp());
        cyclicBarrier.await();
        System.out.println("我是主线程我执行完了");
    }

    static class Temp implements Runnable{
        @Override
        public void run() {
            System.out.println(new Random().nextInt());
        }
    }
}
