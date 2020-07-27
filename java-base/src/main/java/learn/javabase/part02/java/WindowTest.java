package learn.javabase.part02.java;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 例子：创建三个窗口卖票，总票数为100张.使用继承Thread类的方式
 *
 * 存在线程的安全问题，待解决。
 *
 * @author shkstart
 * @create 2019-02-13 下午 4:20
 */
class Window extends Thread{



    private static int ticket = 100;
    private  Lock lock;

    public Window(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        while(true){

            lock.lock();
            try {
                if(ticket > 0){
                    System.out.println(getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                }else{
                    break;
                }

            }finally {
                lock.unlock();
            }


        }

    }
}


public class WindowTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Window t1 = new Window(lock);
        Window t2 = new Window(lock);
        Window t3 = new Window(lock);


        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
