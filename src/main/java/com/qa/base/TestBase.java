
package com.qa.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

    public class TestBase {
        //这个类作为所有接口请求的父类，加载读取properties文件
        public Properties prop;
        //构造函数
        public TestBase(){
            try{
                prop = new Properties();
                FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
                        "/src/main/java/com/qa/config/config.properties");
                prop.load(fis);
            }catch(FileNotFoundException f){
                f.printStackTrace();
            }catch (IOException i){
                i.printStackTrace();
            }
        }
    }

      /*   1. test 包：主要放测试用例

            2. base包：

            1）baseapi类，所有测试类的模板基类，其他需要测试的类都需要继承该类；

            主要功能：读取配置文件，获取session，token等功能可以全局使用，若测试需要整片登录，可把登录功能放在该类；

            2）constants类，定义所有的常量status状态码，用常量写出来，方便每一个TestNG测试用例去调用去断言

            3. parameters包：post请求需要传递的参数对象，相当于javabena，添加有参构造和无参构造，添加set和get方法

            4. restclient包：提供各种请求方法，如post，get，put，delete，获取响应码状态，json内容解析

            5. utils包：工具包

            1）PropertiesUtils类：主要是各种方式读取配置文件

            2）EntityToMap类：实体对象转换map

            3）ReadExcel类：读取excel及sheet页

            6. fatjson包：json 解析工具类，
            */


