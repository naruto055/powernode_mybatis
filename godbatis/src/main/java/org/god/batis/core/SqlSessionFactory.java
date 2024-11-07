package org.god.batis.core;


import java.util.Map;

/**
 * SqlSessionFactory对象
 * 一个数据库对应一个SqlSessionFactory对象
 * 通过SqlSessionFactory对象可以获取SqlSession对象
 * 一个SqlSessionFactory对象可以开启多个SqlSession会话
 *
 * @Author: naruto
 * @CreateTime: 2024-11-06-23:35
 */
public class SqlSessionFactory {
    /**
     * 事务管理器属性
     */
    private Transaction transaction;

    /**
     * 存放SQL语句的Map集合
     * key是sqlId
     * value是对应的SQL标签信息对象
     */
    private Map<String , MapperedStatement> mapperedStatement;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Map<String, MapperedStatement> getMapperedStatement() {
        return mapperedStatement;
    }

    public void setMapperedStatement(Map<String, MapperedStatement> mapperedStatement) {
        this.mapperedStatement = mapperedStatement;
    }

    public SqlSessionFactory(Transaction transaction, Map<String, MapperedStatement> mapperedStatement) {
        this.transaction = transaction;
        this.mapperedStatement = mapperedStatement;
    }

    public SqlSessionFactory() {
    }
}
