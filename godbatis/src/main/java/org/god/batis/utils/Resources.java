package org.god.batis.utils;


import java.io.InputStream;

/**
 * godbatis提供的工具类
 * 这个工具类专门完成“类路径”中资源的加载
 *
 * @Author: naruto
 * @CreateTime: 2024-11-06-23:31
 */
public class Resources {
    private Resources() {}

    /**
     * 从类路径中加载资源
     * @return
     */
    public static InputStream getResourcesAsStream(String resources) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(resources);
    }
}
