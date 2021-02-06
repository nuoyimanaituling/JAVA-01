package main.java.java0;

import java.util.Random;
import java.util.concurrent.*;

public class HomeWorkTotalWays19 {
//methods19
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> bq =  new LinkedBlockingQueue<>();
        ExecutorService executor =  Executors.newFixedThreadPool(1);
        Future<Integer> f1 =   executor.submit(()->getNumber());
        executor.execute(()-> {
            try {
                bq.put(f1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println(bq.take());

    }

    static Integer getNumber() {

      return   new Random().nextInt();
    }
}
