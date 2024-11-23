package com.naruto.myabtis.mapper;


import com.naruto.myabtis.pojo.Student;

import java.util.List;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-20-22:02
 */
public interface StudentMapper {

    /**
     * 根据id获取学生信息，同时获取学生关联的班级信息
     *
     * @param sid 学生的id
     * @return
     */
    Student selectById(Integer sid);

    Student selectByIdAssociation(Integer sid);

    Student selectByIdStep1(Integer sid);

    List<Student> selectByCidStep2(Integer cid);
}
