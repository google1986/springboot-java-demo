package reflection;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/13 15:53
 */
public class MethodDemo {
    @Test
    public void test001(){
        Class<Person> aClass = Person.class;
        //getMethods():获取当前运行时类及其所有父类中声明为public权限的方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("--------------");

        //getDeclaredMethods():获取当前运行时类中声明的所有方法。（不包含父类中声明的方法）
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }

    @Test
    public void test002(){
        Class<Person> aclass = Person.class;
        Method[] declaredMethods = aclass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            Annotation[] annotations = declaredMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
            System.out.print(Modifier.toString(declaredMethod.getModifiers())+"\t");
            System.out.print(declaredMethod.getReturnType().getName() + "\t"+declaredMethod.getName()+" (");
            Parameter[] parameters = declaredMethod.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                if (i == parameters.length-1){
                    System.out.print(parameters[i].getName()+"args_"+i);
                    break;
                }
                System.out.print(parameters[i].getName()+"args_"+i+",");
            }
            System.out.print(")");
            Class<?>[] exceptionTypes = declaredMethod.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.print("throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if (i == exceptionTypes.length - 1) {
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }

                    System.out.print(exceptionTypes[i].getName() + ",");
                }
            }
            System.out.println();
        }
    }
}
