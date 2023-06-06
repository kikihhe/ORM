package com.xiaohe.ORM.mapping;

import com.xiaohe.ORM.session.TransactionIsolationLevel;
import com.xiaohe.ORM.transaction.Transaction;
import com.xiaohe.ORM.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @ClassName Environment
 * @Description 环境
 * @Author 何
 * @Date 2023-06-06 20:34
 * @Version 1.0
 */
public class Environment {
    /**
     * 环境id
     */
    private final String id;

    /**
     * 事务工厂
     */
    private final TransactionFactory transactionFactory;

    /**
     * 数据源
     */
    private final DataSource dataSource;

    public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
        this.id = id;
        this.transactionFactory = transactionFactory;
        this.dataSource = dataSource;
    }

    /**
     * 建造者模式
     */
    public static class Builder {
        private String id;
        private TransactionFactory transactionFactory;
        private DataSource dataSource;

        public Builder(String id) {
            this.id = id;
        }
        public Builder transactionFactory(TransactionFactory transactionFactory) {
            this.transactionFactory = transactionFactory;
            return this;
        }
        public Builder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }
        public String id() {
            return this.id;
        }
        public Environment build() {
            return new Environment(id, transactionFactory, dataSource);
        }

    }


    public String getId() {
        return id;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

}
