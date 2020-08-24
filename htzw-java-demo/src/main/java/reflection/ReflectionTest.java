package reflection;

import org.junit.Test;
import sun.plugin2.message.ShowDocumentMessage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/13 15:25
 */
public class ReflectionTest {
    @Test
    public void test001(){
        Person person = new Person("Jack",54);
        person.age=33;
        System.out.println(person.toString());
        person.show();
    }
    @Test
    public void test002() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<Person> personClass = Person.class;
        Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
        Person person = constructor.newInstance("MM", 44);
        System.out.println(person.toString());
        Field age = personClass.getDeclaredField("age");
        age.set(person,23);
        System.out.println(person.toString());

        Method show = personClass.getDeclaredMethod("show");
        show.invoke(person);

        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Person person1 = declaredConstructor.newInstance("DDD");
        System.out.println(person1.toString());

        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person1, "BB");
        System.out.println(person1.toString());

        Method showNation = personClass.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String cccc = (String)showNation.invoke(person1, "CCCC");
        System.out.println(cccc);
    }

    @Test
    public void  test003() throws ClassNotFoundException {
        Class<Person> personClass = Person.class;
        System.out.println(personClass);

        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        System.out.println(aClass);

        Class<?> p = Class.forName("reflection.Person");
        System.out.println(p);
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> aClass1 = classLoader.loadClass("reflection.Person");
        System.out.println(aClass1);
        if ((personClass ==aClass) &&(personClass ==p) && (p==aClass1)) {
            System.out.println("------------");
        }
    }
}
