package proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用cglib动态代理
 *
 *  * cglib是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，
 *  * 但因为采用的是继承，所以不能对final修饰的类进行代理。
 *
 *
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/28 14:58
 */
public class BookFacadeCglibProxy implements MethodInterceptor {
    /**
     * CGLIB 增强类对象，代理类对象是由 Enhancer 类创建的，
     * Enhancer 是 CGLIB 的字节码增强器，可以很方便的对类进行拓展
     */
    private Enhancer enhancer = new Enhancer();

    /**
     * 使用动态代理来 创建一个代理对象
     *
     * @param c
     * @return
     */
    public Object getInstance(Class<?> c){

        /**
         * 设置产生的代理对象的父类，增强类型
         */
        enhancer.setSuperclass(c);
        /**
         * 回调方法
         * 定义代理逻辑对象为当前对象，要求当前对象实现MethodInterceptor接口
         */
        enhancer.setCallback(this);
        /**
         * 使用默认无参数的构造函数创建目标对象，这是一个前提，被代理的类要提供无参构造方法
         */
        return enhancer.create();
    }

    /**
     *
     * @param o  被代理的对象
     * @param method 代理的方法
     * @param args 方法的参数
     * @param proxy CGLIB方法代理对象
     * @return  cglib生成用来代替Method对象的一个对象，使用MethodProxy比调用JDK自身的Method直接执行方法效率会有提升
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("TX start......");
        Object obj = proxy.invokeSuper(o, args);
        System.out.println("Tx  end......");
        return obj;
    }
}
