package Goodnight;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.BaseApi;
import com.qa.parameters.Manager;
import com.qa.restclient.RestClient;
import com.qa.utils.TestUtil;
import com.qa.utils.fatjson.FastjsonUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class UserSendSms extends BaseApi {

     private final static   Logger Log = Logger.getLogger(String.valueOf(UserSendSms.class));
   // private final static   Logger Log = Logger.getLogger(UserSendSms.class.getSimpleName());

    @Test
    private void loginManagerAppTest1() throws ClientProtocolException, IOException {

        String url = hostManager + "/mobile/user/sendSms";
        Manager manager = new Manager("15026619241");


        Map<String, String> map = FastjsonUtils.toMap(FastjsonUtils.toJson(manager));

        System.out.println( map);

        CloseableHttpResponse closeableHttpResponse = RestClient.postForm(url, map, null);

       //断言代码状态是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,this.RESPNSE_STATUS_CODE_200,"response status code is not 200");

        //把响应内容存储在字符串对象
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        //创建Json对象，把上面字符串序列化成Json对象
        JSONObject responseJson = JSON.parseObject(responseString);
        System.out.println("respon json from API-->" + responseJson);

        //json内容解析
        int s = Integer.valueOf(TestUtil.getValueByJPath(responseJson,"code"));
       // String s = TestUtil.getValueByJPath(responseJson,"code");

         Assert.assertEquals(s,200);

    }
}
