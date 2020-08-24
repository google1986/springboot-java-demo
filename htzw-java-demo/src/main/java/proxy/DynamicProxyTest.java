package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/13 16:10
 */
interface Animal {
    String getBelief();
    void eat(String food);
}
class Cat implements Animal{

    @Override
    public String getBelief() {
        return "I am a Cat";
    }

    @Override
    public void eat(String food) {
        System.out.println("I eat "+food);
    }
}
class AnimalUtil{
    public void before(){
        System.out.println("==========before=====");
    }
    public void after(){
        System.out.println("============after====");
    }
}
class AnimalProxyFactory{
    public static Object getProxyInstance(Object object){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                AnimalUtil animalUtil = new AnimalUtil();
                animalUtil.before();
                Object invoke = method.invoke(object, args);
                animalUtil.after();
                return invoke;
            }
        });
    }
}
public class DynamicProxyTest {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Animal animal = (Animal) AnimalProxyFactory.getProxyInstance(cat);
        System.out.println(animal.getBelief());
        animal.eat("鱼头");

    }
}
