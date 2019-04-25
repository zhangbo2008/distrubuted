package com.hc.distributed.sqlReflect;

import com.hc.distributed.service.FileService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Set;

public class JDKProxyTest extends AbstractProcessor {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        proxyAnnotation1();
        noImpl();
    }

    public static void proxy1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> proxyClass= Proxy.getProxyClass(JDKProxyTest.class.getClassLoader(), HelloWord.class);
        final Constructor<?> cons = proxyClass.getConstructor(InvocationHandler.class);
//        final InvocationHandler ih = new DynamicProxy(new HelloWordImpl());
//        HelloWord helloWord= (HelloWord)cons.newInstance(ih);
    }

    /**
     * loader：定义了代理类的ClassLoder;
     * interfaces：代理类实现的接口列表
     * h：调用处理器，也就是我们上面定义的实现了InvocationHandler接口的类实例
     */
    public static void proxy2() {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        //下面是更简单的一种写法，本质上和上面是一样的
//        DynamicProxy inter = new DynamicProxy(new HelloWordImpl());
//
//        HelloWord helloWord = (HelloWord) Proxy.newProxyInstance(HelloWord.class.getClassLoader(), new Class[] {HelloWord.class, FileService.class}, inter);

//        FileService fileService = (FileService) Proxy.newProxyInstance(FileService.class.getClassLoader(), new Class[] {HelloWord.class, FileService.class}, inter);
    }

    public static void proxyAnnotation1() {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
//        DynamicProxy inter = new DynamicProxy(new HelloWordImpl());
//        HelloWord helloWord = (HelloWord) Proxy.newProxyInstance(HelloWord.class.getClassLoader(), new Class[] {HelloWord.class, FileService.class}, inter);
//        System.out.println(helloWord.sayHello("3"));
//        System.out.println(helloWord.update("3", 4));
//        System.out.println(helloWord.delete(3));
//        System.out.println(helloWord.insert(3));
    }

    public static void proxyAnnotation2() {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
//        DynamicProxy inter = new DynamicProxy(new HelloWordImpl());
//        HelloWord helloWord = (HelloWord) Proxy.newProxyInstance(HelloWord.class.getClassLoader(), new Class[] {HelloWord.class, FileService.class}, inter);
//        helloWord.sayHello(4);
    }

    public static void noImpl() {
        HelloWord helloWord = (HelloWord) new SqlMapper().getInstance(HelloWord.class);
        System.out.println(helloWord.sayHello("3"));
    }

    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
