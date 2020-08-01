package com.demo.mybatis.io;

import java.io.InputStream;

/**
 * description：
    使用类加载器读取Resource下的XML配置文件读入流的类
 * @author Hartley
 * date： 2020/7/26
 */
public class Resources {
    public static InputStream getResourceAsStream(String filePath){
        //
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
