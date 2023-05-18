package com.xiaohe.ORM.session.defaults;

import com.xiaohe.ORM.binding.MapperRegistry;
import com.xiaohe.ORM.session.SqlSession;
import com.xiaohe.ORM.session.SqlSessionFactory;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-05-18 21:48
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
