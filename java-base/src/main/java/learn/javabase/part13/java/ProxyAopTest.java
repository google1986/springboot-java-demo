package learn.javabase.part13.java;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/21 15:16
 */

interface Dog {
    void info();
    void  run();
}
class HuntingDog implements Dog {

    @Override
    public void info() {
        System.out.println("我是一只猎狗");
    }

    @Override
    public void run() {
        System.out.println("我奔跑迅速");
    }
}
class DogUtil {
    public void method1() {
        System.out.println("======模拟通用方法一=====");
    }
    public void method2() {
        System.out.println("======模拟通用方法二========");
    }
}
class MyAopInvocationHandler implements InvocationHandler {

    private Object obj;
    public void bind(Object obj){
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DogUtil dogUtil = new DogUtil();
        dogUtil.method1();
        Object invoke = method.invoke(obj, args);
        dogUtil.method2();
        return invoke;
    }
}
class MyProxyAopFactory {
    public static Object getProxy(Object obj){
        //创建一个MyInvokationHandler对象
        MyAopInvocationHandler handler = new MyAopInvocationHandler();
        handler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }
}
public class ProxyAopTest {
    public static void main(String[] args) {
        Dog target = new  HuntingDog();
        Dog dog = (Dog) MyProxyAopFactory.getProxy(target);
        dog.info();
        dog.run();
    }
}
