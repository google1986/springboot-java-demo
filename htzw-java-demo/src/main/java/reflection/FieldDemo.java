package reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/13 15:39
 */
public class FieldDemo {
    @Test
    public void test001(){
        Class<Person> personClass = Person.class;
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("=================");

        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
    }

    @Test
    public void test002(){
        Class<Person> personClass = Person.class;
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.print(Modifier.toString(field.getModifiers())+"\t");
            Class<?> type = field.getType();
            System.out.print(type.getName()+"\t");
            System.out.print(field.getName()+"\t");
            System.out.println();
        }
    }
}
