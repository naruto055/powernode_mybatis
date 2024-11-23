package com.naruto.mybatis.test;


import com.naruto.myabtis.mapper.ClazzMapper;
import com.naruto.myabtis.pojo.Clazz;
import com.naruto.myabtis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-20-22:03
 */
public class ClazzMapperTest {
    @Test
    public void testSelectByCollection() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByCollection(1000);
        System.out.println(clazz);
        sqlSession.close();
    }

        @Test
    public void testSelectByStep() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByStep1(1000);
        System.out.println(clazz);
        sqlSession.close();
    }
}
