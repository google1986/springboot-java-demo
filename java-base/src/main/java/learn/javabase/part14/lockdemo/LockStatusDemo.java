package learn.javabase.part14.lockdemo;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author J.L.G
 * @version 1.0
 * @date 2020/9/9 9:43
 */
public class LockStatusDemo {

    static class A {

    }

    /**
     * 可以看到最后 00000001 basied_lock = 0, lock =01  表示无锁
     */
    @Test
    public void lockDemo01(){
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }

    /**
     * 当没有竞争出现时，默认使用偏斜锁。JVM 会利用CAS操作在对象头上的Mark Word部分设置线程ID，
     * 以表示对象偏向当前线程。所以并不涉及真正的互斥锁，这样做的假设是基于在很多应用场景中，
     * 大部分对象生命周期中最多会被一个线程锁定，使用偏斜锁可以降低无竟争开销。
     *
     * 00000101 中 basied_lock = 1, lock =01  表示偏斜锁
     *
     * @throws InterruptedException
     */
    @Test
    public void lockDemo02() throws InterruptedException {
        Thread.sleep(5000);
        A a = new A();
        synchronized (a) {
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
    }
    /**
     *thread1中依旧输出偏向锁，主线程获取对象A时，
     * thread1虽然已经退出同步代码块，但主线程和thread1仍然为锁的**交替竞争**关系。故此时主线程输出结果为轻量级锁
     *
     * 00000101  依然是偏向锁，00111000 是轻量级锁
     */
    @Test
    public void lockDemo03() throws InterruptedException {
        Thread.sleep(5000);
        A a = new A();

        Thread thread1= new Thread(() -> {
            synchronized (a){
                System.out.println("thread1 locking");
                System.out.println(ClassLayout.parseInstance(a).toPrintable()); //偏向锁
            }
        });
        thread1.start();
        thread1.join();
        Thread.sleep(10000);

        synchronized (a){
            System.out.println("main locking");
            // 轻量锁
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
    }

    /**
     * thread1 和 thread2 同时竞争对象a，此时输出结果为重量级锁
     */
    @Test
    public void lockDemo04() throws InterruptedException {
        Thread.sleep(5000);
        A a = new A();
        Thread thread1 = new Thread(() -> {
            synchronized (a){
                System.out.println("thread1 locking");
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
                try {
                    //让线程晚点儿死亡，造成锁的竞争
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (a){
                System.out.println("thread2 locking");
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
