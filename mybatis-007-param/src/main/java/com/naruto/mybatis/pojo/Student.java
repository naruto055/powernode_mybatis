package com.naruto.mybatis.pojo;


import java.util.Date;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-16-22:46
 */
public class Student {
    private Long id;
    private String name;
    private String age;
    private String sex;
    private Double height;
    private Date birth;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", height=" + height +
                ", birth=" + birth +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Student() {
    }

    public Student(Long id, String name, String age, String sex, Double height, Date birth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.birth = birth;
    }
}
