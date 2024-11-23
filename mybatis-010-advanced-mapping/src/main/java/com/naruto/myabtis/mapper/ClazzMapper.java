package com.naruto.myabtis.mapper;


import com.naruto.myabtis.pojo.Clazz;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-18-23:40
 */
public interface ClazzMapper {

    /**
     * 根据cid查询班级
     *
     * @param cid
     * @return
     */
    Clazz selectByIdStep2(Integer cid);

    /**
     * 根据班级编号查询班级信息
     *
     * @param cid
     * @return
     */
    Clazz selectByCollection(Integer cid);

    /**
     * 分步查询，根据cid查询班级
     *
     * @param cid
     * @return
     */
    Clazz selectByStep1(Integer cid);
}
