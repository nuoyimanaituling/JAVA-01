package main.java.java0;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
//methods18
public class HomeWorkTotalWays18 {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool(1);
      Integer result =fjp.invoke(new ComputeNumber());
        System.out.println(result);
        System.out.println("我是主线程我结束了");
    }
    static class ComputeNumber extends RecursiveTask<Integer> {
        protected Integer compute() {
            return new Random().nextInt();
        }
    }
}