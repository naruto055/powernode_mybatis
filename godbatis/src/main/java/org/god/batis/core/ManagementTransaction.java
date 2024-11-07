package org.god.batis.core;


import java.sql.Connection;

/**
 * ManagementTransaction事务管理器
 *
 * @Author: naruto
 * @CreateTime: 2024-11-06-23:59
 */
public class ManagementTransaction implements Transaction{
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void openConnection() {

    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }
}
