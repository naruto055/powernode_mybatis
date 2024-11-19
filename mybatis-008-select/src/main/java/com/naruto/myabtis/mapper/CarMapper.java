package com.naruto.myabtis.mapper;


import com.naruto.myabtis.pojo.Car;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-18-23:40
 */
public interface CarMapper {
    /**
     * 根据id查询Car信息
     *
     * @param id
     * @return
     */
    Car selectById(Long id);

    /**
     * 获取所有Car
     *
     * @return
     */
    List<Car> selectAll();

    Car selectByBrandLike(String brand);

    Map<String, Object> selectByIdRetrunMap(Long id);

    List<Map<String, Object>> selectAllRetrunListMap();

    @MapKey("id")  // 不加这个注解报错，意思是：将查询结果的id值作为整个大Map集合的key
    Map<Long, Map<String, Object>> selectAllRetrunMap();

    /**
     * 查询所有的Car信息，使用resultMap标签
     * @return
     */
    List<Car> selectAllByResultMap();

    List<Car> selectAllMapUnderscoreToCamelCase();
}
