package org.god.batis.core;


import java.lang.reflect.Method;
import java.sql.*;

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
            while (true) {
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
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 执行select语句，返回一条记录
     *
     * @param sqlId
     * @param param
     * @return
     */
    public Object selectOne(String sqlId, Object param) {
        Object obj = null;
        try {
            Connection connection = factory.getTransaction().getConnection();
            String godbatisSql = factory.getMapperedStatement().get(sqlId).getSql();
            // 要封装的结果类型
            String resultType = factory.getMapperedStatement().get(sqlId).getResultType();
            String sql = godbatisSql.replaceAll("#\\{[a-zA-z0-9_$]*}", "?");
            PreparedStatement ps = connection.prepareStatement(sql);
            // 给占位符传值
            ps.setString(1, param.toString());
            ResultSet rs = ps.executeQuery();
            // 从结果集中取出数据，封装java对象
            if (rs.next()) {
                Class<?> resultTypeClass = Class.forName(resultType);
                obj = resultTypeClass.newInstance();
                // 给User类的id，name，age属性赋值
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String propertyName = rsmd.getColumnName(i);
                    // 拼接方法名
                    String setMethodName = "set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                    // 获取set方法
                    Method setMethods = resultTypeClass.getDeclaredMethod(setMethodName, String.class);
                    // 调用set方法
                    setMethods.invoke(obj, rs.getString(propertyName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        String sql = "insert into t_user values(#{id}, #{name}, #{age})";
        int fromIndex = 0;
        int index = 1;  // 问号的下标
        while (true) {
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
