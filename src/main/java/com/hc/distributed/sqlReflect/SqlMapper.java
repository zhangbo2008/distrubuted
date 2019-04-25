package com.hc.distributed.sqlReflect;

import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class SqlMapper {

    List<Class<?>> mapper = new ArrayList<>();

    public Object getInstance(Class<?> cls){
        DynamicProxy invocationHandler = new DynamicProxy();
        Object newProxyInstance = Proxy.newProxyInstance(
                cls.getClassLoader(),
                new Class[] { cls },
                invocationHandler);
        return (Object)newProxyInstance;
    }

}
