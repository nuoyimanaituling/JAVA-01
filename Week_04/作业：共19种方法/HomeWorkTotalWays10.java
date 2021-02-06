package main.java.java0;

import java.util.Random;
import java.util.concurrent.Semaphore;
//methods11
// 可以允许释放的大于申请的信号量，这样总信号量就会由初始的-acquire 然后加上release的
//2-1+3=4 正好由主线程获取 主线程不断尝试获取锁
public class HomeWorkTotalWays10 {
    public static void main(String[] args) throws InterruptedException {
         final Semaphore s = new Semaphore(2);
         Mythreads10 mythreads10 =new Mythreads10(s);
         mythreads10.start();
         s.acquire(4);
         System.out.println("主线程执行完毕了");
         s.release(4);
    }
    static class Mythreads10 extends Thread{
        Semaphore s;
        public  Mythreads10(Semaphore s){
            this.s =s;
        }
        @Override
        public void run() {
            try{
                s.acquire(1);
                System.out.println(new Random().nextInt());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release(3);
            }
        }
    }
}
