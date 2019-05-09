package com.hc.hcbasic.sqlReflect;

import com.hc.hcbasic.exceptions.ErrorException;
import com.hc.hcbasic.sqlConnectionPool.ConnectionPool;
import com.hc.hcbasic.sqlConnectionPool.ConnectionPoolUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DynamicProxy implements InvocationHandler {

    private ConnectionPool connectionPool = ConnectionPoolUtils.getPoolInstance();

    DynamicProxy() {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation[] annotations = method.getAnnotations();
        String sql = "";

        for (Annotation annotation :
                annotations) {
            if (annotation instanceof SqlAnnotation) {
                sql = ((SqlAnnotation) annotation).value();
            }
        }

        //TODO 该方法Java原生不做任何设置只能获取arg0这类名字
        Parameter[] parameters = method.getParameters();
        Class<?> returnType = method.getReturnType();

        Connection con = connectionPool.getConnection();
        PreparedStatement ps = SqlUtil.sqlHandel(sql, parameters, args, con);

        //TODO void类型会出事
        Object o;

        try {
            switch (getType(sql)) {
                case "SELECT":
                    o = selectMethod(method, returnType, ps);
                    break;
                case "UPDATE":
                    o = updateMethod(returnType, ps);
                    break;
                case "INSERT":
                    o = insertMethod(returnType, ps);
                    break;
                case "DELETE":
                    o = deleteMethod(returnType, ps);
                    break;
                default:
                    throw new ErrorException("Sql is error");
            }
        } finally {
            connectionPool.returnConnection(con);
        }

        return o;
    }

    /**
     * 如是自定义实体类，则必须要有默认构造器，不然会抛出异常
     *
     * @param returnType
     * @param defaultValue
     * @return
     */
    public Object getObject(Class<?> returnType, Object defaultValue) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Object o;

        if (returnType.isPrimitive()) {
            // 基本类型,使用了两个Map来存储基本类型对应的class与引用类型
            /*String primitiveName = returnType.getName();
            Constructor<?> constructor = SqlUtil.quoteClazz.get(primitiveName).getConstructor(SqlUtil.primitiveClazz.get(primitiveName));
            o = constructor.newInstance(defaultValue);
            return o;*/

            // 我在想什么...基本类型直接返回就好啦...
            return defaultValue;
        }

        if (returnType.isInterface()) {
            o = SqlMapper.getInstance();
            return o;
        }

        try {
            // 拥有默认构造器的对象
            o = returnType.newInstance();
        } catch (UndeclaredThrowableException | InstantiationException e) {
//            e.printStackTrace();
            // 引用类型，适用于Integer等,
            Constructor<?> constructor = returnType.getConstructor(SqlUtil.primitiveClazz.get((String) returnType.getField("TYPE").get(null)));
            o = constructor.newInstance(defaultValue);
        }
        return o;
    }

    public String getType(String sql) {
        return sql.split(" ")[0].toUpperCase();
    }

    public Object selectMethod(Method method, Class<?> returnType, PreparedStatement ps) throws SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        boolean isList = false;

        Object list = null;
        if (returnType == List.class) {
            isList = true;
            list = getObject(ArrayList.class, 0);
//            list = new ArrayList();
            returnType = GenericsUtils.getMethodGenericReturnType(method);
        }

        // 执行sql语句
        ResultSet rs;
        rs = ps.executeQuery();
        // 得到数据行数
        int count = rs.getMetaData().getColumnCount();

        Object o = null;
        while (rs.next()) {
            // 实例化对象o
            o = getObject(returnType, 0);
            // 数据库表中的列从1开始计数
            for (int i = 1; i <= count; i++) {
                // 得到数据表当前列的名称
                String colunmLabel = rs.getMetaData().getColumnLabel(i);

                // 将数据库下划线风格的名称转化为驼峰
                String humpName = SqlUtil.underLineToHump(colunmLabel);
                // 根据驼峰命名得到相应的set方法
                String methodName = SqlUtil.getMethodName(humpName);

                // 得到数据表中当前行的值
                Object result = rs.getObject(i);
                // 得到o中名为humpName的字段的class类型
                Class<?> fieldClass = SqlUtil.getFieldClass(o, humpName);

                // 得到字段相应的set方法并将结果放入。
                Method setMethod = o.getClass().getDeclaredMethod(methodName, fieldClass);
                setMethod.invoke(o, result);
            }
            if (isList) {
                Method addMethod = list.getClass().getDeclaredMethod("add", Object.class);
                addMethod.invoke(list, o);
            }
        }

        if (isList) {
            return list;
        }
        return o;
    }

    /**
     * 获取泛型类Class对象，不是泛型类则返回null
     * 只能拿到类的泛型而不能拿到实例的泛型
     *
     * @param clazz 类对象
     * @return 类对象的泛型
     */
    public static Class<?> getActualTypeArgument(Class<?> clazz) {
        Class<?> entitiClass = null;
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                System.out.println(actualTypeArguments[0]);
                entitiClass = (Class<?>) actualTypeArguments[0];
            }
        }
        return entitiClass;
    }

    public Object updateMethod(Class<?> returnType, PreparedStatement ps) throws SQLException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return getObject(returnType, ps.executeUpdate());
    }

    public Object deleteMethod(Class<?> returnType, PreparedStatement ps) throws SQLException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return getObject(returnType, ps.executeUpdate());
    }

    public Object insertMethod(Class<?> returnType, PreparedStatement ps) throws SQLException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return getObject(returnType, ps.executeUpdate());
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        System.out.println(List.class.newInstance());


//        System.out.println(((ParameterizedType) strings.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0]);
    }
}
