package com.hc.hcbasic.sqlReflect;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * asdas dsadasdas
 */

public class SqlUtil {

    public static final Map<String, Class<?>> primitiveClazz; // 基本类型的class

    public static final Map<String, Class<?>> quoteClazz; // 基本类型对应的引用类型

    private static final String INTEGER = "int";
    private static final String BYTE = "byte";
    private static final String CHARACTOR = "char";
    private static final String SHORT = "short";
    private static final String LONG = "long";
    private static final String FLOAT = "float";
    private static final String DOUBLE = "double";
    private static final String BOOLEAN = "boolean";

    static
    {
        primitiveClazz = new HashMap<>(8, 1);
        primitiveClazz.put(INTEGER, int.class);
        primitiveClazz.put(BYTE, byte.class);
        primitiveClazz.put(CHARACTOR, char.class);
        primitiveClazz.put(SHORT, short.class);
        primitiveClazz.put(LONG, long.class);
        primitiveClazz.put(FLOAT, float.class);
        primitiveClazz.put(DOUBLE, double.class);
        primitiveClazz.put(BOOLEAN, boolean.class);
    }

    static
    {
        quoteClazz = new HashMap<>(8, 1);
        quoteClazz.put(INTEGER, Integer.class);
        quoteClazz.put(BYTE, Byte.class);
        quoteClazz.put(CHARACTOR, Character.class);
        quoteClazz.put(SHORT, Short.class);
        quoteClazz.put(LONG, Long.class);
        quoteClazz.put(FLOAT, Float.class);
        quoteClazz.put(DOUBLE, Double.class);
        quoteClazz.put(BOOLEAN, Boolean.class);
    }

    /**
     * 下划线转驼峰
     * @param message 原字段
     * @return 转驼峰后的字段
     */
    static String underLineToHump(String message) {
        while(true) {
            int index = message.indexOf("_");
            if (index == -1) {
                return message;
            }
            message = message.replaceFirst("_\\w", String.valueOf(message.charAt(index + 1)).toUpperCase());
        }
    }

    static String getMethodName(String message) {
        return "set" + message.replaceFirst("\\b\\w", String.valueOf(message.charAt(0)).toUpperCase());
    }

    static Class<?> getFieldClass(Object o, String message) throws NoSuchFieldException {
        Field field = o.getClass().getDeclaredField(message);
        String typeName = field.getType().getTypeName();
        try {
            return Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            return primitiveClazz.get(typeName);
        }
    }

    static PreparedStatement sqlHandel(String sql, Parameter[] parameters, Object[] args, Connection con) throws SQLException {
        Object[] injectArgs = new Object[args.length + 1];
        int indexOfInjectArgs = 1;
        int indexOfArgs = -1;
        for (Parameter p :
                parameters) {
            // 注解的值
            String filedName = p.getAnnotation(SqlParam.class).value();
            Object arg = args[++indexOfArgs];
            String WellCharacterRegex = "[#]{1}\\{" + filedName + "}";
            String TheDollarSignRegex = "[$]{1}\\{" + filedName + "}";
            if (arg instanceof String) {
                // 使用了#{}标识符，需要注入
                if (Pattern.compile(WellCharacterRegex).matcher(sql).find()) {
                    injectArgs[indexOfInjectArgs++] = args[indexOfArgs];
                    sql = sql.replaceAll(WellCharacterRegex, "?");
                } else {
                    sql = sql.replaceAll(TheDollarSignRegex, "'" + arg + "'");
                }
            } else {
                // 使用了#{}标识符，需要注入
                if (Pattern.compile(WellCharacterRegex).matcher(sql).find()) {
                    injectArgs[indexOfInjectArgs++] = args[indexOfArgs];
                    sql = sql.replaceAll(WellCharacterRegex, "?");
                } else {
                    sql = sql.replaceAll(TheDollarSignRegex, arg.toString());
                }
            }
        }

        PreparedStatement ps = con.prepareStatement(sql);

        for (int i = 1; i < indexOfInjectArgs; i++) {
            ps.setObject(i, injectArgs[i]);
        }

        return ps;
    }

    private Method findMethod(Object o, String name , boolean declared, Object... args) {
        Class<?> clazz = o.getClass();
        try {
            Method method = declared ? clazz.getDeclaredMethod(name, toClasses(args)) : clazz.getMethod(name, toClasses(args));
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Method[] methods = declared ? clazz.getDeclaredMethods() : clazz.getMethods();
            return findMethod(methods, name, args);
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {
//        int.class.getConstructor(int.class).newInstance(0);
//        Class<?> clazz = Class.forName("java.lang.Double").isPrimitive();
//        Constructor<?> constructor = clazz.getConstructor(double.class);
//        Object o = constructor.newInstance();
//        System.out.println(o);
//        System.out.println(int.class.);
        System.out.println(Integer.class.getField("TYPE").get(null));
    }


    public Method findMethod(Method[] methods, String name, Object... args) {
        for (Method method : methods) {
            if (method.getName().equals(name) && equals(method.getParameterTypes(), args)) {
                return method;
            }
        }
        return null;
    }

    private Class<?>[] toClasses(Object... args) {
        return args.getClass().getClasses();
    }

    public boolean equals(Class[] clazz, Object... args) {
        if (clazz.length != args.length) {
            return false;
        }

        for (int i = 0; i < clazz.length; i++) {

        }
        return false;
    }

}
