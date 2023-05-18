package com.xiaohe.ORM.binding;

import com.xiaohe.ORM.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @author 23825
 *
 * MapperProxy的工厂
 */
public class MapperProxyFactory<T> {
    private final Class<T> mapperInstance;

    public MapperProxyFactory(Class<T> mapperInstance) {
        this.mapperInstance = mapperInstance;
    }

    /**
     * 使用动态代理获取代理类
     * @param sqlSession
     * @return
     */
    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInstance, sqlSession);
        return (T) Proxy.newProxyInstance(
                mapperInstance.getClassLoader(),
                new Class[]{mapperInstance},
                mapperProxy
        );
    }
}
