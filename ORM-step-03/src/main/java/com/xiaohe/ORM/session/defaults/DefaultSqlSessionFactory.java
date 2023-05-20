package com.xiaohe.ORM.session.defaults;

import com.xiaohe.ORM.session.Configuration;
import com.xiaohe.ORM.session.SqlSession;
import com.xiaohe.ORM.session.SqlSessionFactory;

/**
 * @ClassName DefaultSqlSessionFactory
 * @Description TODO
 * @Author 何
 * @Date 2023-05-20 22:10
 * @Version 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }
}
