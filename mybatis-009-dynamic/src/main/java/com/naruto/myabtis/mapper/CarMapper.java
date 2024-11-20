package com.naruto.myabtis.mapper;


import com.naruto.myabtis.pojo.Car;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-18-23:40
 */
public interface CarMapper {
    List<Car> selectByMultipleParams(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    List<Car> selectByMultiConditionWithWhere(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    List<Car> selectByMultiConditionWithTrim(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    Integer updateById(Car car);

    /**
     * 根据choose when otherwise
     *
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByChoose(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    int deleteByIds(@Param("ids") List<Long> ids);

    int insertBatch(@Param("cars") List<Car> cars);
}
