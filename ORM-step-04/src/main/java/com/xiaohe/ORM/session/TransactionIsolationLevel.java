package com.xiaohe.ORM.session;

import java.sql.Connection;

/**
 * @ClassName TransactionIsolationLevel
 * @Description 事务的隔离级别，包括JDBC支持的5个隔离级别
 * @Author 何
 * @Date 2023-06-06 19:49
 * @Version 1.0
 */
public enum TransactionIsolationLevel {

    //包括JDBC支持的5个级别
    NONE(Connection.TRANSACTION_NONE),
    /**
     * 读已提交
     */
    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
    /**
     * 读未提交
     */
    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
    /**
     * 可重复读，默认
     */
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
    /**
     * 串行化
     */
    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);
    /**
     * 隔离级别代表的数字
     */
    private final int level;

    TransactionIsolationLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
