package main.java.java0;

import java.util.Random;
import java.util.concurrent.*;

//methods10 由线程池异步执行
public class HomeWorkTotalWays9 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        Executor executor =   Executors.newFixedThreadPool(1);
        CyclicBarrier cyclicBarrier =new CyclicBarrier(1, ()-> {
            executor.execute(() -> System.out.println(new Random().nextInt()));
        });
        cyclicBarrier.await();
        Thread.sleep(1);
        System.out.println("我是主线程我执行完了");
    }
    }