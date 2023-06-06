package com.xiaohe.ORM.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName Transaction
 * @Description 事务接口
 * @Author 何
 * @Date 2023-06-06 19:43
 * @Version 1.0
 */
public interface Transaction {
    /**
     * 获取连接
     *
     * @return Connection对象
     */
    Connection getConnection() throws SQLException;


    /**
     * 提交事务
     */
    void commit() throws SQLException;

    /**
     * 回滚事务
     */
    void rollback() throws SQLException;


    /**
     * 关闭连接
     */
    void close() throws SQLException;
}
