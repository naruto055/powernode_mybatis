package com.naruto.mybatis.test;


import com.naruto.mybatis.pojo.Car;
import com.naruto.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-03-18:30
 */
public class CarMapperTest {

    @Test
    public void testInsertCar() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();

        Map<String, Object> map = new HashMap<>();
        map.put("car_num", "1111");
        map.put("brand", "比亚迪汉");
        map.put("guide_price", 10.00);
        map.put("produce_time", "2022-11-11");
        map.put("car_type", "新能源");

        sqlSession.insert("insertCar", map);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCarByPOJO() {
        Car car = new Car(null, "3333", "比亚迪秦", 30.00, "2022-11-11", "新能源");
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        int count = sqlSession.insert("insertCar", car);
        System.out.println("插入条数：" + count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        int count = sqlSession.delete("deleteById", 11L);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateById() {
        Car car = new Car(4L, "9999", "比亚迪秦", 39.00, "2024-11-11", "新能源");
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        int count = sqlSession.update("updateById", car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        Car car = sqlSession.selectOne("selectById", 4L);
        System.out.println(car);
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        List<Object> cars = sqlSession.selectList("selectAll");
        cars.forEach(System.out::println);
    }

    @Test
    public void testNamespace() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        List<Object> selectAll = sqlSession.selectList("car.selectAll");
        selectAll.forEach(System.out::println);
        sqlSession.close();
    }
}
