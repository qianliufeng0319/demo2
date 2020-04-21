

package com.qa.tests;

        import com.alibaba.fastjson.JSON;
        import com.alibaba.fastjson.JSONObject;
        import com.qa.base.BaseApi;
        import com.qa.base.TestBase;
        import com.qa.parameters.postParameters;
        import com.qa.restclient.HttpUtil;
        import com.qa.restclient.RestClient;
        import com.qa.utils.TestUtil;
        import com.qa.utils.TestUtil2;
        import org.apache.http.client.methods.CloseableHttpResponse;
        import org.apache.http.util.EntityUtils;
        import org.testng.Assert;
        import org.testng.annotations.AfterClass;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;

        import java.io.IOException;
        import java.util.HashMap;
        import java.util.Map;

        import static com.qa.utils.TestUtil2.dtt;

public class testCase1 extends BaseApi {
    BaseApi baseApi;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    //host根url
    String host;
    //Excel路径
    String testCaseExcel;
    //header
    HashMap<String ,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        baseApi = new BaseApi();
        restClient = new RestClient();
        postHeader.put("Content-Type","application/json");
        //载入配置文件，接口endpoint
        host = prop.getProperty("Host");
        //载入配置文件，post接口参数
        testCaseExcel=prop.getProperty("testCase1data");

    }

    @DataProvider(name = "postData")
    public Object[][] post() throws IOException {
        return dtt(testCaseExcel,0);

    }

    @Test(dataProvider = "postData")
    public void login(String loginUrl,String mobile, String pwd) throws Exception {
        //使用构造函数将传入的用户名密码初始化成登录请求参数
        postParameters loginParameters = new postParameters(mobile,pwd);
        /*Map<String, String> m = new HashMap<String, String>();
        m.put("mobile",mobile);
        m.put("pwd",pwd);*/

        //将登录请求对象序列化成json对象
        String userJsonString = JSON.toJSONString(loginParameters);

        //发送登录请求
        closeableHttpResponse = restClient.post(host+loginUrl,userJsonString,postHeader);

       // closeableHttpResponse = HttpUtil.http(host+loginUrl,m);
        //从返回结果中获取状态码
        //System.out.println(userJsonString);


        int statusCode = TestUtil2.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode,200);

        //把响应内容存储在字符串对象
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        //创建Json对象，把上面字符串序列化成Json对象
        JSONObject responseJson = JSON.parseObject(responseString);
        System.out.println("respon json from API-->" + responseJson);

        //json内容解析
         String s = TestUtil.getValueByJPath(responseJson,"code");
         Assert.assertEquals(s,200);


    }
    @AfterClass
    public void endTest(){
        System.out.print("测试结束");
    }

}
