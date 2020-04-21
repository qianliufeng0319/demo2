package com.qa.base;

import org.testng.annotations.BeforeClass;
import com.qa.utils.PropertiesUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseApi {

    //TestBase和BaseApi合并成BaseApi

    protected String hostManager;

    public Properties prop;
    public int RESPNSE_STATUS_CODE_200 = 200;
    public int RESPNSE_STATUS_CODE_201 = 201;
    public int RESPNSE_STATUS_CODE_404 = 404;
    public int RESPNSE_STATUS_CODE_500 = 500;

        @BeforeClass
        public void setUp() {
            String host1 = PropertiesUtils.getConfigValue("HOST");
            hostManager=PropertiesUtils.getConfigValue("HOSTMANAGER");    }


    //写一个构造函数
    public BaseApi() {

        try{
            prop= new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
                    "/src/main/resources/config.properties");
            prop.load(fis);


        }catch (FileNotFoundException e) {
            e.printStackTrace();

        }catch (IOException e) {
            e.printStackTrace();

        }

    }

}