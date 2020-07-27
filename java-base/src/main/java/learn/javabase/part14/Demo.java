package learn.javabase.part14;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/6/23 9:09
 */
class B {

}
public class Demo {
    static  B b = new B();
    public static void main(String[] args) {
        //jvm的信息
        System.out.println(VM.current().details());
        System.out.println("========================");
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
    }
}
