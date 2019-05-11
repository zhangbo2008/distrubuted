package com.hc.hcsql.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/exclusive_plug?characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
    private static String user = "root";
    private static String password = "admin";

    // 加载数据库驱动
    static {
        try {
            Class.forName(driver);
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 连接数据库
    public static Connection getCon() throws SQLException {
        Connection con;
        con = DriverManager.getConnection(url, user, password);
        System.out.println("数据库连接成功");
        return con;
    }


    public static void close(AutoCloseable... closeables){
        for (AutoCloseable c : closeables) {
            try {
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
