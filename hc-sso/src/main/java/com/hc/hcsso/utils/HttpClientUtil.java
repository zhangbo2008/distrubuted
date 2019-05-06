package com.hc.hcsso.utils;


import com.google.gson.Gson;
import com.hc.hccommon.utils.VerifyUtil;
import com.hc.hcsso.dtos.Data;
import com.hc.hcsso.dtos.RequestBean;
import com.hc.hcsso.dtos.ResultBean;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;


@Service("httpClientUtil")
public class HttpClientUtil {

    /**
     * 使用Json格式发送Post请求
     *
     * @param url         发送的URL
     * @param requestData 传给数据
     * @return
     * @throws IOException
     */
    public ResultBean<Data> postAction(String url, RequestBean requestData) throws IOException, IllegalAccessException, InstantiationException {
        // 将Json对象转换为字符串
        Gson gson = new Gson();
        String strJson = gson.toJson(requestData, requestData.getClass());
        //使用帮助类HttpClients创建CloseableHttpClient对象.
        CloseableHttpClient client = HttpClients.createDefault();
        //HTTP请求类型创建HttpPost实例
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

        //组织数据
        StringEntity se = new StringEntity(strJson);
        se.setContentType("application/json");

        //对于httpPost请求,把请求体填充进HttpPost实体.
        httpPost.setEntity(se);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = client.execute(httpPost);
//            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            String line;
//            while (null != (line = br.readLine())) {
//                System.out.println(line);
//            }
//
            HttpEntity entity = response.getEntity();
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                strJson = EntityUtils.toString(entity, "UTF-8").trim();
                ResultBean<Data> resultBean = VerifyUtil.cast(gson.fromJson(strJson, ResultBean.class));
                if (null != response.getFirstHeader("x-auth-token")) {
                    if (null == resultBean.getData()) {
                        resultBean.setData(new Data());
                    }
                    resultBean.getData().setXAuthToken((response.getFirstHeader("x-auth-token").toString()));
                }
                return resultBean;
            }
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
            client.close();
        }
    }
}
