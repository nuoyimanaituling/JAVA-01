package main.java.java0;

import java.util.Random;
import java.util.concurrent.*;
//methpds17
public class HomeWorkTotalWays17 {
    public static void main(String[] args) {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        Future<Integer> future =executor.submit(new Temp());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
   static class Temp implements Callable {
        @Override
        public Object call() throws Exception {
            return new Random().nextInt();
        }
   }
}


