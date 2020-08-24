package reflection;

import org.junit.Test;

import java.util.Random;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/13 15:15
 */
public class NewInstanceTest {
    @Test
    public  void test001() throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();
        System.out.println(person);
    }
    @Test
    public void  test002() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        for (int i = 0; i < 100; i++) {
            int num = new Random().nextInt(3);
            String classPath = "";
            switch (num){
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "reflection.Person";
                    break;
                default:
            }
            Object instance = getInstance(classPath);
            System.out.println(instance);
        }
    }
    public Object getInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName(classPath);
        return aClass.newInstance();
    }
}
