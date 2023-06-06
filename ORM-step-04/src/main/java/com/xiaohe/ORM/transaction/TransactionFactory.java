package com.xiaohe.ORM.transaction;

import com.xiaohe.ORM.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @ClassName TransactionFactory
 * @Description 事务对象工厂
 * @Author 何
 * @Date 2023-06-06 20:00
 * @Version 1.0
 */
public interface TransactionFactory {
    /**
     * 根据连接池对象获取事务类
     * @param connection 连接对象
     * @return 事务对象
     */
    Transaction newTransaction(Connection connection);

    /**
     * 指定获取事务类
     * @param connection 连接
     * @param isolationLevel 事务隔离级别
     * @param autoCommit 自动提交
     * @return 事务对象
     */
    Transaction newTransaction(DataSource connection, TransactionIsolationLevel isolationLevel, boolean autoCommit);
}
