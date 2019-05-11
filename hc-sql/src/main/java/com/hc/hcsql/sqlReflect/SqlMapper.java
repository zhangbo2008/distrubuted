package com.hc.hcsql.sqlReflect;

import com.hc.hccommon.exceptions.ErrorException;
import com.hc.hcsql.annotation.AutoDao;
import com.hc.hcsql.annotation.DaoMapper;
import org.apache.hadoop.fs.Path;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * Sql注解注册器
 * @author HC
 */
public class SqlMapper {

    /**
     * 存放目录下所有被@DaoMapper注释的类类型
     */
    private static List<Class<?>> mapper = new ArrayList<>();

    /**
     * 存放目录下所有的类类型
     */
    private static Set<Class<?>> classes;

    static {
        try {
            // 主目录s
            classes = getClasses("com.hc.hcsql");
        } catch (UnsupportedEncodingException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 初始化与sql有关的配置
    public static void initSql() throws IllegalAccessException {
        initMapperDao();
        initAutoDao();
    }

    /**
     * 将
     */
    private static void initMapperDao() {
        for (Class<?> clazz : classes) {
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof DaoMapper) {
                    mapper.add(clazz);
                    break;
                }
            }
        }
    }

    private static void initAutoDao() throws IllegalAccessException {
        for (Class<?> clazz : classes) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                AutoDao annotation = field.getAnnotation(AutoDao.class);
                if (null != annotation) {
                    field.setAccessible(true);
                    field.set(null, getInstance());
                }
            }
        }
    }

    /**
     * 得到实例
     * @return Object对象
     */
    public static Object getInstance() {
        return Proxy.newProxyInstance(
                SqlMapper.class.getClassLoader(),
                mapper.toArray(new Class[]{}),
                new DynamicProxy());
    }

    /**
     * 得到指定包下的所有类的类型
     * @param packageName 报名
     * @return 包含指定包下的所有类的类型
     * @throws UnsupportedEncodingException
     * @throws ClassNotFoundException
     */
    public static Set<Class<?>> getClasses(String packageName) throws UnsupportedEncodingException, ClassNotFoundException {
        // 第一个Class类的集合
        Set<Class<?>> classes = new HashSet<>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字，并进行替换
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合，并进行循环来处理这个目录下的things
        Enumeration<URL> dirs = null;
        try {
            // 加载指定路径下的资源
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 遍历文件资源
        while (null != dirs && dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            // 得到类型协议
            String protocol = url.getProtocol();

            if ("file".equals(protocol)) {
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                findAndClassesInPackageByFile(packageName, filePath, recursive, classes);
            }
        }
        return classes;
    }

    /**
     * 递归查找
     * @param packageName 相对目录，包名
     * @param packagePath 绝对目录
     * @param recursive 是否支持递归遍历，true为支持
     * @param classes set容器
     * @throws ClassNotFoundException
     */
    public static void findAndClassesInPackageByFile(String packageName, String packagePath, final boolean recursive,
                                                     Set<Class<?>> classes) throws ClassNotFoundException {
        // 获取此包的目录， 建立一个File
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new ErrorException("file is error");
        }

        // 得到所有以.class结尾或者类型为目录且支持递归遍历的File
        File[] dirfiles = dir.listFiles(file -> (recursive && file.isDirectory()) || (file.getName().endsWith(".class")));
        if (null != dirfiles) {
            for (File file : dirfiles) {
                // 若为目录则递归遍历
                if (file.isDirectory()) {
                    findAndClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
                } else {
                    // 如果是java 类文件，去掉后面的.class
                    String className = file.getName().substring(0, file.getName().length() - 6);
                    // 将Class类型实例放入set容器
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                }
            }
        }
    }
}
