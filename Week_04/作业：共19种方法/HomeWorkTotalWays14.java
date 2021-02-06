package main.java.java0;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//methods14
//为什么Result不能用Integer？？？
public class HomeWorkTotalWays14 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Result result =new Result();
        ExecutorService executor   = Executors.newFixedThreadPool(1);
        Future<Result> future =   executor.submit(new Task(result),result);
        System.out.println(future.get().i);
        System.out.println(result.i);

    }
}
class Task implements Runnable{
    Result r;
    Task(Result r){
        this.r=r;
    }
    @Override
    public void run() {
        r.setI(new Random().nextInt());
    }
}

class Result{
    Integer i;
    public void setI(Integer i){
        this.i=i;
    }
}
