package com.hc.hcbasic.sqlConnectionPool;

/**
 * 连接池工具类,使用连接池
 *
 * @author HC
 * @create 2019-05-09 12:56
 */
public class ConnectionPoolUtils {

    private final static Object lock = new Object();

    private ConnectionPoolUtils(){}   //私有静态方法
    private static ConnectionPool poolInstance = null;
    public static ConnectionPool getPoolInstance(){
        if(null == poolInstance) {
            synchronized (lock) {
                if (null == poolInstance) {

                    String driver = "com.mysql.cj.jdbc.Driver";
                    String url = "jdbc:mysql://localhost:3306/exclusive_plug?characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
                    String user = "root";
                    String password = "admin";

                    poolInstance = new ConnectionPool(driver, url, user, password);
                    try {
                        poolInstance.createPool();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        return poolInstance;
    }
}
