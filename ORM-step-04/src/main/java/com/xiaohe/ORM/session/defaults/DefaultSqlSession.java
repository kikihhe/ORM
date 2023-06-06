package com.xiaohe.ORM.session.defaults;

import com.xiaohe.ORM.session.Configuration;
import com.xiaohe.ORM.session.SqlSession;

/**
 * @ClassName DefaultSqlSession
 * @Description TODO
 * @Author 何
 * @Date 2023-06-06 21:07
 * @Version 1.0
 */
public class DefaultSqlSession implements SqlSession {
    /**
     * 会话的配置
     * configuration提供一些基本操作: 注册mapper、拿到mapper、注册别名...
     */
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }


}
