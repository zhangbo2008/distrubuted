package com.hc.hcbasic.sqlConnectionPool;

import java.util.Map;

/**
 * 连接池工具类,使用连接池
 *
 * @author HC
 * @create 2019-05-09 12:56
 */
public class ConnectionPoolUtils {

    private static Map<String, Object> ymlConfigMap = YmlConfigUtils.getYmlConfigMap();

    private final static Object lock = new Object();

    private ConnectionPoolUtils(){}   //私有静态方法
    private static ConnectionPool poolInstance = null;
    public static ConnectionPool getPoolInstance(){
        if(null == poolInstance) {
            synchronized (lock) {
                if (null == poolInstance) {

                    String driver = (String) ((Map) ymlConfigMap.get("datasource")).get("driver-class-name");
                    String url = (String) ((Map) ymlConfigMap.get("datasource")).get("url");
                    String user = (String) ((Map) ymlConfigMap.get("datasource")).get("username");
                    String password = (String) ((Map) ymlConfigMap.get("datasource")).get("password");

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
