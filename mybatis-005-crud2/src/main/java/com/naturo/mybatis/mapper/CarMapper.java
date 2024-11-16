package com.naturo.mybatis.mapper;


import com.naturo.mybatis.pojo.Car;

import java.util.List;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-15-23:40
 */
public interface CarMapper {

    /**
     * 新增car
     *
     * @param car
     * @return
     */
    int insert(Car car);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 修改汽车信息
     * @param car
     * @return
     */
    int update(Car car);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Car selectById(Long id);

    /**
     * 查询所有
     * @return
     */
    List<Car> selectAll();
}
