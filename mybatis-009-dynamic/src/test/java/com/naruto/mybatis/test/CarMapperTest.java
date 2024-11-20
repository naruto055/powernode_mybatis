package com.naruto.mybatis.test;


import com.naruto.myabtis.mapper.CarMapper;
import com.naruto.myabtis.pojo.Car;
import com.naruto.myabtis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-19-20:31
 */
public class CarMapperTest {
    @Test
    public void testSelectByMultiCondition() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultipleParams(null, 20000.00, "新能源");
        System.out.println(cars);
    }

    @Test
    public void testSelectByMultiConditionWithWhere() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWithWhere(null, null, null);
        System.out.println(cars);
    }

    @Test
    public void testSelectByMultiConditionWithTrim() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWithTrim(null, null, null);
        System.out.println(cars);
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(18L, "粤B82288", "宝骏", null, null, "新能源");
        mapper.updateById(car);
        sqlSession.commit();
    }

    @Test
    public void testSelectByChoose() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByChoose(null, 12.00, "新能源");
        System.out.println(cars);
    }

    @Test
    public void testDeleteByIds() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Long> list = Arrays.asList(18L, 19L);
        int i = mapper.deleteByIds(list);
        System.out.println(i);
        sqlSession.commit();
    }

    @Test
    public void testInsertBatch() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        ArrayList<Car> carList = new ArrayList<>();
        carList.add(new Car(null, "粤B88888", "宝骏12", 12.00, "2024-01-01", "新能源"));
        carList.add(new Car(null, "粤A88888", "宝骏99", 12.00, "2024-01-01", "新能源"));
        mapper.insertBatch(carList);
        sqlSession.commit();
    }
}
