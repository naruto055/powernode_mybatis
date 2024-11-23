package com.naruto.myabtis.pojo;


import lombok.Data;

import java.util.List;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-03-18:38
 */
@Data
public class Clazz {
    private Integer cid;
    private String cname;
    private List<Student> studentList;
}
