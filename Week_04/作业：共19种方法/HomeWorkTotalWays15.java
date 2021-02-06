package main.java.java0;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

//methods15
public class HomeWorkTotalWays15 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask  = new FutureTask<>(()-> new Random().nextInt());
        ExecutorService es =   Executors.newCachedThreadPool();
         es.submit(futureTask);
         Integer result = futureTask.get();
         System.out.println(result);
         System.out.println("我是主线程我结束了");
    }


}
