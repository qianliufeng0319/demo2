package com.qa.utils;

import java.io.IOException;

import java.io.InputStream;

import java.util.Properties;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

 // 读取系统参数
public class PropertiesUtils {
         private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);
         private static String configPath = "config.properties";
         private static Properties p = null;
         static {
             p = new Properties();
             InputStream is = null;
             try {
                 is = PropertiesUtils.class.getClassLoader().getResourceAsStream(configPath);
                 p.load(is);
             }
             catch(Exception ex) {
                 log.warn("Failed to load " + configPath + "(ingore this file)" + ex.getMessage());
             } finally {
                 if(is != null) {
                     try {
                         is.close();
                     }
                     catch (IOException e) {
                         // ingore
                         	}
                         	}
                          	}
                          }
                         /**	 * 	通过指定的key来获取配置文件中对应的value,支持使用默认值
                          *
                          */
                         public static String getConfigValue(String key, String defValue) {
                             String value = null;
                             try {
                                 value = p.getProperty(key);
                                 if(value == null || "".equals(value)) {
                                     value = defValue;
                                 }
                             } catch (Exception e) {
                                 log.error("读取配置文件出错，请检查配置文件的路径是否正确！错误信息为：" + e.getMessage());
                                 value = defValue;		}
                                 return value;
                         }
                                 /**	 * 	通过指定的key来获取配置文件中对应的value,支持使用默认值	 * @param key	 * @return	 */
                                 public static String getConfigValue(String key) {
                                     return getConfigValue(key, null);	}

}