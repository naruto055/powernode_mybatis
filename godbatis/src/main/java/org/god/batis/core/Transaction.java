package org.god.batis.core;

import java.sql.Connection;

/**
 * 事务管理器接口
 * 所有的事务管理器都应该遵循该规范
 *
 * @Author: naruto
 * @CreateTime: 2024-11-06-23:49
 */
public interface Transaction {
    /**
     * 提交事务
     */
    void commit();

    /**
     * 回滚事务
     */
    void rollback();

    /**
     * 关闭事务
     */
    void close();

    /**
     * 开启数据库连接
     */
    void openConnection();

    Connection getConnection();
}
