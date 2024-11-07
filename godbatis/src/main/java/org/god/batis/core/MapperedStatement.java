package org.god.batis.core;


/**
 * 普通的java类，封装了一个sql标签
 * 一个MapperedStatement对应一个标签
 * 一个SQL标签的所有信息封装到MapperedStatement对象当中
 *
 * @Author: naruto
 * @CreateTime: 2024-11-06-23:49
 */
public class MapperedStatement {
    /**
     * sql语句
     */
    private String sql;

    /**
     * 要封装的结果集类型
     */
    private String resultType;

    public MapperedStatement() {
    }

    public MapperedStatement(String sql, String resultType) {
        this.sql = sql;
        this.resultType = resultType;
    }

    @Override
    public String toString() {
        return "MapperedStatement{" +
                "sql='" + sql + '\'' +
                ", resultType='" + resultType + '\'' +
                '}';
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
