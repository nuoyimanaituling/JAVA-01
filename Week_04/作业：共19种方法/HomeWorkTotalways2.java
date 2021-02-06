package main.java.java0;

import java.util.Random;

//method3
public class HomeWorkTotalways2 {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        MyThread1 myThread1 =new MyThread1(o);
        myThread1.start();
        synchronized (o){
            o.wait();
            System.out.println("主线程执行完退出");
        }
    }
}
class  MyThread1 extends Thread {
    private Object object;
    public MyThread1(Object o){
        this.object =o;
    }
    @Override
    public void run() {
        synchronized (object) {
            System.out.println(new Random().nextInt());
            object.notify();
        }
    }
}
