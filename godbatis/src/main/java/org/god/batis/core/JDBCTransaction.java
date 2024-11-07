package org.god.batis.core;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC 事务管理器
 *
 * @Author: naruto
 * @CreateTime: 2024-11-06-23:58
 */
public class JDBCTransaction implements Transaction {
    /**
     * 数据源属性
     */
    private DataSource dataSource;

    /**
     * 自动提交标志
     */
    private boolean autocommit;

    private Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    public JDBCTransaction(DataSource dataSource, boolean autocommit) {
        this.dataSource = dataSource;
        this.autocommit = autocommit;
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openConnection() {
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
