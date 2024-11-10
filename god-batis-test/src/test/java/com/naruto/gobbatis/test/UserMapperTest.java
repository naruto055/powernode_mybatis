package com.naruto.gobbatis.test;


import com.naruto.godbatis.pojo.User;
import org.god.batis.core.SqlSession;
import org.god.batis.core.SqlSessionFactory;
import org.god.batis.core.SqlSessionFactoryBuilder;
import org.god.batis.utils.Resources;
import org.junit.Test;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-10-19:31
 */
public class UserMapperTest {
    @Test
    public void testInsertUser() {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourcesAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User("1", "naruto", "18");
        int insert = sqlSession.insert("user.insertUser", user);
        System.out.println(insert);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectOne() {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourcesAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = (User) sqlSession.selectOne("user.selectOne", "1");
        System.out.println(user);
        sqlSession.close();
    }
}
