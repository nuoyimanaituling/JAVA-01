package main.java.java0;


import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//methods16
public class HomeWorkTotalWays16 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask  = new FutureTask<>(()-> new Random().nextInt());
         Thread T1 = new Thread(futureTask);
         T1.start();
         Integer result = futureTask.get();
         System.out.println(result);
         System.out.println("我是主线程我结束了");
    }
}
