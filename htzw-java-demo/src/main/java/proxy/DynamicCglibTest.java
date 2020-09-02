package proxy;

import proxy.model.BookFacadeImpl1;

/**
 * 对于需要被代理的类，它只是动态生成一个子类以覆盖非 final 的方法，同时绑定钩子回调自定义的拦截器。
 *
 * 值得说的是，它比 JDK 动态代理还要快。
 *
 * 值得注意的是，我们传入目标类作为代理的父类。不同于 JDK 动态代理，我们不能使用目标对象来创建代理。目标对象只能被 CGLIB 创建。
 * 在例子中，默认的无参构造方法被使用来创建目标对象。
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/28 15:04
 */
public class DynamicCglibTest {
    public static void main(String[] args) {
        BookFacadeCglibProxy cglib = new BookFacadeCglibProxy();
        BookFacadeImpl1 bookCglib = (BookFacadeImpl1)cglib.getInstance(BookFacadeImpl1.class);
        bookCglib.addBook();
    }
}
