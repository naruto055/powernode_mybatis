package com.naruto.mybatis.mapper;


import com.naruto.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-16-22:48
 */
public interface StudentMapper {
    /**
     * 当接口中的方法参数只有一个，并且参数的数据类型都是简单类型
     * 根据id查询，name查询，birth查询，sex查询
     */
    List<Student> selectById(Long id);

    List<Student> selectByName(String name);

    List<Student> selectByBirth(Date birth);

    List<Student> selectBySex(Character sex);

    /**
     * 多个参数查询
     * 如果是多个参数的话，mybatis会自动创建一个map集合
     * map.put("arg0", name)
     * map.put("arg1", sex)
     * map.put("param1", name)
     * map.put("param2", sex)
     *
     * @param name
     * @param sex
     * @return
     */
    List<Student> selectByNameAndSex(String name, String sex);

    /**
     * 多个参数查询
     * 如果是多个参数的话，mybatis会自动创建一个map集合
     * map.put("name", name)
     * map.put("sex", sex)
     *
     * @param name
     * @param sex
     * @return
     */
    List<Student> selectByNameAndSex2(@Param("name") String name, @Param("sex") String sex);
}
