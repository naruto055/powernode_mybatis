package com.naruto.mybatis.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-03-17:09
 */
public class CarMapperTest {
    @Test
    public void testInsert(){
        SqlSessionFactory sessionFactory = null;
        SqlSession sqlSession = null;
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            // 开启会话，底层会开启事务
            sqlSession = sessionFactory.openSession();

            // 执行SQL语句
            sqlSession.insert("insertCar");

            // 提交事务
            sqlSession.commit();
        } catch (IOException e) {
            // 回滚事务
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            // 关闭会话，释放资源
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
