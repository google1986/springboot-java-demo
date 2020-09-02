package learn.javabase.part13.java;

import org.junit.Test;
import sun.misc.Launcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;

/**
 * 动态代理
 *
 *
 * JDK的动态代理依靠接口实现，如果有些类并没有实现接口，则不能使用JDK代理，这就要使用cglib动态代理
 *
 * JDK的动态代理机制只能代理实现了接口的类，而不能实现接口的类就不能实现JDK的动态代理，cglib是针对类来实现代理的，
 * 他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。
 *
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/27 9:54
 */

interface Animal {
    String getBelief();
    void eat(String food);
}
class  Cat implements Animal {

    @Override
    public String getBelief() {
        return "I am a Cat!";
    }

    @Override
    public void eat(String food) {
        System.out.println("I eat "+food);
    }
}
class  AnimalUtil {
    public void method1(){
        System.out.println("============方法一========");
    }
    public void method2(){
        System.out.println("=============方法二===========");
    }
}

class AnimalProxyFactory{
    public static Object getProxyInstance(Object obj){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new MyAnimalInvocationHandler(obj));
    }
}
class MyAnimalInvocationHandler implements InvocationHandler {

    private Object object;
    public MyAnimalInvocationHandler(Object o){
        this.object = o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        AnimalUtil animalUtil = new AnimalUtil();
        animalUtil.method1();
        Object returnValue = method.invoke(object, args);
        animalUtil.method2();
        return returnValue;
    }
}

public class DynamicProxyTest {



    @Test
    public void  dddd(){
        System.out.println("======启动类加载器======");
        //获取BootstrapClassLoader能够加载的api的路径
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }

    }
    public static void main(String[] args) {
        Cat cat = new Cat();
        Animal animal = (Animal) AnimalProxyFactory.getProxyInstance(cat);
        System.out.println(animal.getBelief());
        animal.eat("鱼头");
    }
    
}
