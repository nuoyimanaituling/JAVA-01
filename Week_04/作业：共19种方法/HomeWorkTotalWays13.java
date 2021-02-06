package main.java.java0;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Random;
import java.util.concurrent.*;
//method13
public class HomeWorkTotalWays13 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor =   Executors.newFixedThreadPool(1);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        cs.submit(()->getRandomNumber());
        Integer number = cs.take().get();
        System.out.println("子线程拿到的数据是："+number);
        System.out.println("主线程执行结束");

    }

  static   Integer getRandomNumber(){
        return  new Random().nextInt();
    }


}
