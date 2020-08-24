package learn.javabase.part14.jol;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/13 16:29
 */
public class MackWordTest {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        String print = ClassLayout.parseInstance(obj).toPrintable();
        System.out.println(print);
        System.out.println("===========================================================");
        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}