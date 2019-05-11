package com.hc.hcsql.utils;

import com.hc.hcsql.sqlReflect.SqlMapper;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Map;

/**
 * 对.yml配置文件的操作工具类，单例模式
 *
 * @author HC
 * @create 2019-05-09 16:45
 */
@Slf4j
public class YmlConfigUtils {

    private YmlConfigUtils() {

    }

    private static Map<String, Object> ymlConfigMap;

    private static final Object lock = new Object();

    public static Map<String, Object> getYmlConfigMap() {
        if (null == ymlConfigMap) {
            synchronized (lock) {
                if (null == ymlConfigMap) {
                    ymlConfigMap = readYml();
                }
            }
        }
        return ymlConfigMap;
    }

    private static Map<String, Object> readYml() {
        Map map = null;
        try {
            Yaml yaml = new Yaml();
            URL url = SqlMapper.class.getClassLoader().getResource("sql.yml");
            if (url != null) {
                //获取test.yaml文件中的配置数据，然后转换为Map
                map = (Map) yaml.load(new FileInputStream(url.getFile()));
                //通过map我们取值就可以了.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("读取yml配置文件完成");
        return cast(map);
    }

    @SuppressWarnings("unchecked")
    private static <T> T cast(Object object) {
        return (T)object;
    }
}
