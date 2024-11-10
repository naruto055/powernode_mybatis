package org.god.batis.core;


import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 专门负责执行SQL语句的会话对象
 *
 * @Author: naruto
 * @CreateTime: 2024-11-10-16:55
 */
public class SqlSession {
    private SqlSessionFactory factory;

    public SqlSession(SqlSessionFactory factory) {
        this.factory = factory;
    }

    /**
     * 执行insert语句，向数据库中插入记
     *
     * @param sqlId SQL语句的id
     * @param pojo  插入的数据
     * @return
     */
    public int insert(String sqlId, Object pojo) {
        int count = 0;
        try {
            // JDBC代码，执行insert语句，完成插入操作
            Connection connection = factory.getTransaction().getConnection();
            String godbatisSql = factory.getMapperedStatement().get(sqlId).getSql();
            String sql = godbatisSql.replaceAll("#\\{[a-zA-z0-9_$]*}", "?");
            PreparedStatement ps = connection.prepareStatement(sql);
            // 给?号传值
            int fromIndex = 0;
            int index = 1;  // 问号的下标
            while(true) {
                int jingIndex = godbatisSql.indexOf("#", fromIndex);
                if (jingIndex < 0) {
                    break;
                }
                int youKuoHaoIndex = godbatisSql.indexOf("}", fromIndex);
                String propertyName = godbatisSql.substring(jingIndex + 2, youKuoHaoIndex).trim();
                fromIndex = youKuoHaoIndex + 1;
                String getMethodName = "get" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                Method getMethod = pojo.getClass().getDeclaredMethod(getMethodName);
                Object propertyValue = getMethod.invoke(pojo);
                ps.setString(index, propertyValue.toString());
                index++;
            }
            // 执行SQL语句
            count = connection.prepareStatement(sql).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        String sql = "insert into t_user values(#{id}, #{name}, #{age})";
        int fromIndex = 0;
        int index = 1;  // 问号的下标
        while(true) {
            int jingIndex = sql.indexOf("#", fromIndex);
            if (jingIndex < 0) {
                break;
            }
            System.out.println(index);
            index++;
            int youKuoHaoIndex = sql.indexOf("}", fromIndex);
            String propertyName = sql.substring(jingIndex + 2, youKuoHaoIndex).trim();
            System.out.println(propertyName);
            fromIndex = youKuoHaoIndex + 1;
        }
    }

    /**
     * 提交事务
     */
    public void commit() {
        factory.getTransaction().commit();
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        factory.getTransaction().rollback();
    }

    /**
     * 关闭会话
     */
    public void close() {
        factory.getTransaction().close();
    }
}
