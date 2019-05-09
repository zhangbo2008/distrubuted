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
                    o = selectMethod(returnType, ps);
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
                    default:throw new ErrorException("Sql is error");
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

    public Object selectMethod(Class<?> returnType, PreparedStatement ps) throws SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Object o = getObject(returnType, 0);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            int count = rs.getMetaData().getColumnCount();
            if (rs.next()) {
                for (int i = 1; i <= count; i++) {
                    String colunmLabel = rs.getMetaData().getColumnLabel(i);

                    String humpName = SqlUtil.underLineToHump(colunmLabel);
                    String methodName = SqlUtil.getMethodName(humpName);

                    Object result = rs.getObject(i);
                    Class<?> fieldClass = SqlUtil.getFieldClass(o, humpName);

                    Method setMethod = o.getClass().getDeclaredMethod(methodName, fieldClass);
                    setMethod.invoke(o, result);
                }
            }
        } finally {
            JdbcUtils.close(rs);
        }
        return o;
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
        List.class.newInstance();
    }
}
