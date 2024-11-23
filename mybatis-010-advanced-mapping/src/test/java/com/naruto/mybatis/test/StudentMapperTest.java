package com.naruto.mybatis.test;


import com.naruto.myabtis.mapper.StudentMapper;
import com.naruto.myabtis.pojo.Student;
import com.naruto.myabtis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-20-22:03
 */
public class StudentMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectById(1);
        System.out.println(student.getSid());
        System.out.println(student.getSname());
        System.out.println(student.getClazz().getCid());
        System.out.println(student.getClazz().getCname());
        sqlSession.close();
    }

    @Test
    public void testSelectByAssociation() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdAssociation(4);
        System.out.println(student.getSid());
        System.out.println(student.getSname());
        System.out.println(student.getClazz().getCid());
        System.out.println(student.getClazz().getCname());
        sqlSession.close();
    }

        @Test
    public void testSelectByStep() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdStep1(2);
        System.out.println(student.getSid());
        System.out.println(student.getSname());
        // System.out.println(student.getClazz().getCid());
        // System.out.println(student.getClazz().getCname());
        sqlSession.close();
    }
}
