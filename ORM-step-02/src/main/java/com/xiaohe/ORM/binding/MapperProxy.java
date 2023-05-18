package com.xiaohe.ORM.binding;

import com.xiaohe.ORM.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName MapperProxy
 * @Description MapperProxy实现了invoke方法，供MapperProxyFactory调用
 * @Author 何
 * @Date 2023-05-18 23:30
 * @Version 1.0
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    private final Class<T> mapperInterface;
    private SqlSession sqlSession;


    /**
     * @return
     * @description
     * @param    mapperInterface
     * @param    sqlSession
     */
    public MapperProxy(Class<T> mapperInterface, SqlSession sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 如果代理的是从Object继承过来的方法，直接执行就行了
        if (method.getDeclaringClass().equals(Object.class)) {
            method.invoke(proxy, args);
        }

        return sqlSession.selectOne(method.getName(), args);
    }
}