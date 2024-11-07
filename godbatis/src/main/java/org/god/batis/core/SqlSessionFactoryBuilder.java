package org.god.batis.core;


import java.io.InputStream;

/**
 * SqlSessionFactory构造器对象
 * 通过SqlSessionFactoryBuilder的build方法来解析godbatis-config文件，然后创建SqlSessionFactory对象
 *
 * @Author: naruto
 * @CreateTime: 2024-11-06-23:33
 */
public class SqlSessionFactoryBuilder {

    /**
     * 无参数构造方法
     */
    public SqlSessionFactoryBuilder() {}

    /**
     * 解析godbatis-config.xml文件，来构建SqlSessionFactory对象
     * @param in 指向 godbatis-config.xml文件的输入流
     * @return SqlSessionFactory对象
     */
    public SqlSessionFactory build(InputStream in) {
        SqlSessionFactory factory = new SqlSessionFactory();
        return null;
    }
}
