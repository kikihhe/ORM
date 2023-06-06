package com.xiaohe.ORM.transaction.jdbc;

import com.xiaohe.ORM.session.TransactionIsolationLevel;
import com.xiaohe.ORM.transaction.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @ClassName JdbcTransaction
 * @Description 基于JDBC的事务管理
 * @Author 何
 * @Date 2023-06-06 19:46
 * @Version 1.0
 */
public class JdbcTransaction implements Transaction {

    /**
     * 事务连接对象
     */
    protected Connection connection;
    /**
     * 数据源对象
     */
    protected DataSource dataSource;
    /**
     * 事务的隔离级别
     */
    protected TransactionIsolationLevel isolationLevel = TransactionIsolationLevel.NONE;

    protected boolean autoCommit;

    /**
     * 构造方法
     * 不给定连接，而是给定数据源，可以从数据源中获取连接。
     * @param dataSource 数据源
     * @param isolationLevel 事务的隔离级别
     * @param autoCommit 是否自动提交事务
     */
    public JdbcTransaction(DataSource dataSource, TransactionIsolationLevel isolationLevel, boolean autoCommit) {
        this.dataSource = dataSource;
        this.isolationLevel = isolationLevel;
        this.autoCommit = autoCommit;
    }

    public JdbcTransaction(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        connection = dataSource.getConnection();
        connection.setTransactionIsolation(isolationLevel.getLevel());
        connection.setAutoCommit(autoCommit);
        return connection;
    }

    @Override
    public void commit() throws SQLException {
        // 如果connection不为空且不是自动提交事务，那就执行事务
        if (!Objects.isNull(connection) && !connection.getAutoCommit()) {
            connection.commit();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (!Objects.isNull(connection) && !connection.getAutoCommit()) {
            connection.rollback();
        }

    }

    @Override
    public void close() throws SQLException {
        if (!Objects.isNull(connection) && !connection.getAutoCommit()) {
            connection.close();
        }
    }
}
