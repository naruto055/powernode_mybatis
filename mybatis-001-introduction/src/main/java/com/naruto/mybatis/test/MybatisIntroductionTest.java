package com.naruto.mybatis.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-02
 */
public class MybatisIntroductionTest {
    public static void main(String[] args) throws IOException {
        // 获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 获取SqlSessionFactory对象
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");  // Resources.getResourceAsStream默认是从类的根路径下开始查找资源
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(is);

        // 获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();
        int count = sqlSession.insert("insertCar");  // 返回值是影响的行数

        System.out.println("插入记录行数：" + count);

        // 手动提交
        sqlSession.commit();
    }
}
