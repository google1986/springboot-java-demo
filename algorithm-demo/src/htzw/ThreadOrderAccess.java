package htzw;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/16 6:51
 */

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class ShareResource
{
    private int number = 1;//1:A 2:B 3:C
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(int totalLoopNumber)
    {
        lock.lock();
        try
        {
            //1 判断
            while(number != 1)
            {
                //A 就要停止
                c1.await();
            }
            //2 干活
            for (int i = 1; i <=5; i++)
            {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoopNumber: "+totalLoopNumber);
            }
            //3 通知
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print10(int totalLoopNumber)
    {
        lock.lock();
        try
        {
            //1 判断
            while(number != 2)
            {
                //A 就要停止
                c2.await();
            }
            //2 干活
            for (int i = 1; i <=10; i++)
            {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoopNumber: "+totalLoopNumber);
            }
            //3 通知
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int totalLoopNumber)
    {
        lock.lock();
        try
        {
            //1 判断
            while(number != 3)
            {
                //A 就要停止
                c3.await();
            }
            //2 干活
            for (int i = 1; i <=15; i++)
            {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoopNumber: "+totalLoopNumber);
            }
            //3 通知
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


/**
 *
 * @Description:
 * 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 *
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......来10轮
 *
 */
public class ThreadOrderAccess
{
    public static void main(String[] args)
    {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位

        for (int i = 1; i <=6; i++) //模拟6部汽车
        {
            new Thread(() -> {
                try
                {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到了车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+"\t------- 离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}













