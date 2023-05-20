package com.xiaohe.ORM.session.defaults;

import com.xiaohe.ORM.mapping.MappedStatement;
import com.xiaohe.ORM.session.Configuration;
import com.xiaohe.ORM.session.SqlSession;

/**
 * @ClassName DefaultSqlSession
 * @Description TODO
 * @Author 何
 * @Date 2023-05-20 22:06
 * @Version 1.0
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);

        return (T) ("你被代理了!" + "\n方法: " + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameters) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        String sql = mappedStatement.getSql();
        return (T) ("你被代理了!" + "\n方法: " + statement + "\n入参: " + parameters + "\n待执行sql: " + sql);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
