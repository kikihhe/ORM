package com.xiaohe.ORM.transaction.jdbc;

import com.xiaohe.ORM.session.TransactionIsolationLevel;
import com.xiaohe.ORM.transaction.Transaction;
import com.xiaohe.ORM.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @ClassName JdbcTransactionFactory
 * @Description JDBC事务 工厂
 * @Author 何
 * @Date 2023-06-06 20:04
 * @Version 1.0
 */
public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection connection) {
        return new JdbcTransaction(connection);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel isolationLevel, boolean autoCommit) {
        return new JdbcTransaction(dataSource, isolationLevel, autoCommit);
    }
}
