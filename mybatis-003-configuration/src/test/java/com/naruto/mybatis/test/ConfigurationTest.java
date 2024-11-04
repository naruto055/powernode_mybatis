package com.naruto.mybatis.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-03-23:52
 */
public class ConfigurationTest {

    @Test
    public void testEnvironment() throws IOException {
        // 获取SqlSessionFactory对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sessionFactory.openSession();

        sqlSession.insert("car.insertCar");

        sqlSession.commit();
        sqlSession.close();

        SqlSessionFactory sessionFactory1 = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"), "mybatisDB");
        SqlSession sqlSession1 = sessionFactory1.openSession();
        sqlSession1.insert("car.insertCar");
        sqlSession1.commit();
        sqlSession1.close();
    }

    @Test
    public void testPooled() throws IOException {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sessionFactory.openSession();
        sqlSession.insert("car.insertCar");
        sqlSession.commit();
        sqlSession.close();

        SqlSession sqlSession2 = sessionFactory.openSession();
        sqlSession2.insert("car.insertCar");
        sqlSession2.commit();
        sqlSession2.close();
    }

    @Test
    public void testDataSource() throws IOException {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sessionFactory.openSession();
        sqlSession.insert("car.insertCar");
        sqlSession.commit();
        sqlSession.close();
    }
}
