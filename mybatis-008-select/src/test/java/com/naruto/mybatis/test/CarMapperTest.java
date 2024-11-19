package com.naruto.mybatis.test;


import com.naruto.myabtis.mapper.CarMapper;
import com.naruto.myabtis.pojo.Car;
import com.naruto.myabtis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-18-23:42
 */
public class CarMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(5L);
        System.out.println(car);
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> carList = mapper.selectAll();
        System.out.println(carList);
    }

    @Test
    public void testSelectByBrandLike() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        // 抛异常：TooManyResultsException
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectByBrandLike("奔驰");
        System.out.println(car);
    }

    @Test
    public void testSelectReturnMap() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Map<String, Object> map = mapper.selectByIdRetrunMap(5L);
        System.out.println(map);
    }

    @Test
    public void testSelectReturnListMap() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Map<String, Object>> maps = mapper.selectAllRetrunListMap();
        maps.forEach(map -> System.out.println(map));
    }

    @Test
    public void testSelectAllRetrunMap() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Map<Long, Map<String, Object>> longMapMap = mapper.selectAllRetrunMap();
        System.out.println(longMapMap);
    }


    @Test
    public void testSelectReturnResultMap() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByResultMap();
        System.out.println(cars);
    }

    @Test
    public void testSelectAllMapUnderscoreToCamelCase() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllMapUnderscoreToCamelCase();
        System.out.println(cars);
    }
}
