package com.qa.restclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.qa.utils.fatjson.FastjsonUtils;

/*
 * 1.实现get请求方法及带请求头信息,POST请求方法，PUT方法，DELETE方法
 * 2.一个是带请求头信息的POST请求方法
 * 3.获取响应状态码
 * 4.json内容解析
 */
public class RestClient {

    private static final Logger log = LoggerFactory.getLogger(RestClient.class);

    // get请求方法
    public static CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
        return get(url, null);
    }

    // Get 请求方法（带请求头信息）
    public static CloseableHttpResponse get(String url, Map<String, String> headers)
            throws ClientProtocolException, IOException {
        // 创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建一个HttpGet的请求对象
        HttpGet httpget = new HttpGet(url);
        // 加载请求头到httpget对象
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpget.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // 执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
        CloseableHttpResponse httpResponse = httpclient.execute(httpget);

        return httpResponse;
    }


    //POST方法（如果不需要header可传入null），提交form表单（默认application/x-www-form-urlencoded）
    public static CloseableHttpResponse postForm(String url, Map<String, String> params, Map<String, String> headers)
            throws ClientProtocolException, IOException {
        // 设置请求头数据传输格式,使用表单提交的方式,postman中有写
        //headers.put("Content-Type","application/x-www-form-urlencoded");
        return post(url, null, params, headers);
    }

    //  POST方法,发送json格式（如果不需要header可传入null）
    public static CloseableHttpResponse postJson(String url, String jsonString, Map<String, String> headers)
            throws ClientProtocolException, IOException {
        //准备请求头信息
        headers.put("Content-Type", "application/json");//postman中有写
        return post(url, jsonString, null, headers);
    }


    // POST方法
   public static CloseableHttpResponse post(String url, String jsonString, Map<String, String> params, Map<String, String> headers)
            throws ClientProtocolException, IOException {
        // 创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建一个HttpPost的请求对象
        HttpPost httppost = new HttpPost(url);
        // 构造请求体，创建参数队列
        if(jsonString != null && !"".equals(jsonString)) {
            httppost.setEntity(new StringEntity(jsonString));
        } else {
            // 构造请求体，创建参数队列
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            httppost.setEntity(new UrlEncodedFormEntity(nvps));
        }

        // 加载请求头到httppost对象
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httppost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // 发送post请求
        CloseableHttpResponse httpResponse = httpclient.execute(httppost);
        return httpResponse;
    }

    //3. POST方法
            public CloseableHttpResponse post(String url, String entityString, HashMap<String,String> headermap) throws ClientProtocolException, IOException {
        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //创建一个HttpPost的请求对象
        HttpPost httppost = new HttpPost(url);
        //设置payload
        httppost.setEntity(new StringEntity(entityString));

        //加载请求头到httppost对象
        for(Map.Entry<String, String> entry : headermap.entrySet()) {
        httppost.addHeader(entry.getKey(), entry.getValue());
        }
        //发送post请求
        CloseableHttpResponse httpResponse = httpclient.execute(httppost);
        return httpResponse;
        }


    // 4. Put方法
    public static CloseableHttpResponse put(String url, Map<String, String> params, Map<String, String> headers)
            throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPut httpput = new HttpPut(url);
        // 构造请求体，创建参数队列
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        httpput.setEntity(new UrlEncodedFormEntity(nvps));

        // 加载请求头到httpput对象
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpput.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // 发送put请求
        CloseableHttpResponse httpResponse = httpclient.execute(httpput);
        return httpResponse;
    }

    // 5. Delete方法
    public static CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpDelete httpdel = new HttpDelete(url);

        // 发送put请求
        CloseableHttpResponse httpResponse = httpclient.execute(httpdel);
        return httpResponse;
    }

    /**
     * 获取响应状态码，常用来和TestBase中定义的状态码常量去测试断言使用
     * @param response
     * @return 返回int类型状态码
     */
    public static int getStatusCode(CloseableHttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        log.info("解析，得到响应状态码:" + statusCode);
        return statusCode;
    }

    /**把字符串转换为JSON对象，get取出【JSONObject.get("name")】
     * @param response,
     *            任何请求返回返回的响应对象 @return， 返回响应体的json格式对象，方便接下来对JSON对象内容解析
     *            接下来，一般会继续调用TestUtil类下的json解析方法得到某一个json对象的值
     * @throws ParseException
     * @throws IOException
     */
    public static JSONObject getResponseJson(CloseableHttpResponse response) throws ParseException, IOException {
        log.info("得到响应对象的String格式");
        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject responseJsonObj = FastjsonUtils.toJsonObject(responseString);
        log.info("返回响应内容的JSON对象");
        return responseJsonObj;
    }

    /**
     * 把json字符串转换成指定类型的实体bean
     * get取出【user.getName()】
     */
    public static <T> T getResponseJson2Bean(CloseableHttpResponse response, Class<T> clazz)
            throws ParseException, IOException {
        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        log.info("得到响应对象的String格式,数据为：" + responseString);
        T t = FastjsonUtils.toBean(responseString, clazz);
        log.info("返回响应的实体bean对象");
        return t;
    }


}
