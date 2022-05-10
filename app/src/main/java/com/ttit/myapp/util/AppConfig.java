package com.ttit.myapp.util;


public class AppConfig {
    public static final String BASE_URl = "http://192.168.31.32:8080/renren-fast";
    // API 27以上,非https会报错,
    // 如果要http,必须在res/xml中创建一个忽略安全检查的文件network_security_config.xml
    // 也要到AndroidManiFest.xml中进行注册:
    // `android:networkSecurityConfig="@xml/network_security_config"`
    // 在doc/api.txt中查看
}

