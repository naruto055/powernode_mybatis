package com.naruto.mybatis.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * mybatis工具类
 *
 * @Author: naruto
 * @CreateTime: 2024-11-03-18:08
 */
public class SqlSessionUtil {
    // 工具类的构造方法一般都是私有的，工具类中所有的方法都是静态的，直接采用类名调用即可
    // 为了防止工具类被实例化，所以构造方法私有化
    private SqlSessionUtil() {
    }

    private static final SqlSessionFactory sqlSessionFactory;

    // 静态代码块，在类加载的时候执行，只执行一次
    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取会话对象
     *
     * @return
     * @throws IOException
     */
    public static SqlSession openSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
