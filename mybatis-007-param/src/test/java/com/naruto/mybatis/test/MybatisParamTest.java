package com.naruto.mybatis.test;


import com.naruto.mybatis.mapper.StudentMapper;
import com.naruto.mybatis.pojo.Student;
import com.naruto.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-16-22:49
 */
public class MybatisParamTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectById(1L);
        students.forEach(System.out::println);
    }

    @Test
    public void testSelectByName() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByName("张三");
        students.forEach(System.out::println);
    }

    @Test
    public void testByBirth() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = sdf.parse("1980-10-11");
        List<Student> students = mapper.selectByBirth(birth);
        students.forEach(System.out::println);
    }

    @Test
    public void testBySex() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Character sex = Character.valueOf('男');
        List<Student> students = mapper.selectBySex(sex);
        students.forEach(System.out::println);
    }

    @Test
    public void testByNameAndSex() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Character sex = Character.valueOf('男');
        List<Student> students = mapper.selectByNameAndSex("张三", "男");
        students.forEach(System.out::println);
    }

    /**
     * 代理模式：
     * 1.代理对象   链家、贝壳
     * 2.代理方法   找房子
     * 3.目标对象   我
     * 4.目标方法   找房子
     * @throws ParseException
     */
    @Test
    public void testByNameAndSex2() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        // mapper就是一个代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Character sex = Character.valueOf('男');
        // selectByNameAndSex2就是一个代理方法
        List<Student> students = mapper.selectByNameAndSex2("张三", "男");
        students.forEach(System.out::println);
    }
}
