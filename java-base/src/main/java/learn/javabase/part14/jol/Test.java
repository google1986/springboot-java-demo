package learn.javabase.part14.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/7 11:14
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        B b = new B();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
        C c = new C();
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        int[] aa = new int[10];
        System.out.println(ClassLayout.parseInstance(aa).toPrintable());
    }

}
