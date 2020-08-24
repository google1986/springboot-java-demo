package reflection;

import org.junit.Test;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/13 15:12
 */
public class ClassLoaderTest {
    @Test
    public void test01(){
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);
    }
}
