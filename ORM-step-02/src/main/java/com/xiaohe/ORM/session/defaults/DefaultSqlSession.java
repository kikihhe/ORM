package com.xiaohe.ORM.session.defaults;

import com.xiaohe.ORM.binding.MapperRegistry;
import com.xiaohe.ORM.session.SqlSession;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-05-18 21:38
 */
public class DefaultSqlSession implements SqlSession {
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + statement + "parameter: " + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
