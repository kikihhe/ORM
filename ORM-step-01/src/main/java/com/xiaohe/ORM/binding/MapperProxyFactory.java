package com.xiaohe.ORM.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author : 小何
 * @Description : 代理类工厂
 * @date : 2023-05-17 22:51
 */
public class MapperProxyFactory<T> {
    private Class<T> mapperInterface;

    /**
     * 指定这个工厂生产的类是什么类型的
     *
     * @param mapperInterface 类型
     */
    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }


    /**
     * 创建代理类的实例
     * @param sqlSession
     * @return
     */
    public T newInstance(Map<String, String> sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface, sqlSession);
        return (T) Proxy.newProxyInstance(mapperProxy.getClass().getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
