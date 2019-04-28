package com.hc.distributed.sqlReflect;

import com.hc.distributed.exceptions.ErrorException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class SqlMapper {

    static List<Class<?>> mapper = new ArrayList<>();

    public Object getInstance(){
        DynamicProxy invocationHandler = new DynamicProxy();
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                mapper.toArray(new Class[]{}),
                invocationHandler);
    }

    public Set<Class<?>> getClasses(String packageName) throws UnsupportedEncodingException, ClassNotFoundException {
        // 第一个Class类的集合
        Set<Class<?>> classes = new HashSet<>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字，并进行替换
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合，并进行循环来处理这个目录下的things
        Enumeration<URL> dirs = null;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (null != dirs && dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                System.out.println("filePath : " + filePath);
                findAndClassesInPackageByFile(packageName, filePath, recursive, classes);
            }
            System.out.println("protocol : " + url.getProtocol());
        }
        return classes;
    }

    public void findAndClassesInPackageByFile(String packageName,
                                              String packagePath, final boolean recursive, Set<Class<?>> classes) throws ClassNotFoundException {
        // 获取此包的目录， 建立一个File
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new ErrorException("file is error");
        }

        File[] dirfiles = dir.listFiles(file -> (recursive && file.isDirectory()) || (file.getName().endsWith(".class")));
        assert dirfiles != null;
        for (File file : dirfiles) {
            if (file.isDirectory()) {
                findAndClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                // 如果是java 类文件，去掉后面的.class
                String className = file.getName().substring(0, file.getName().length() - 6);
                classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                Class<?> clazz = Class.forName(packageName + "." + className);
                Annotation[] annotations = clazz.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof DaoMapper) {
                        mapper.add(clazz);
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException, ClassNotFoundException {
        SqlMapper sqlMapper = new SqlMapper();
        sqlMapper.getClasses("com.hc.distributed");
    }

}
