package com.xiaohe.ORM.binding;

import com.xiaohe.ORM.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-05-18 20:59
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    private final Class<T> mapperInterface;
    private SqlSession sqlSession;

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
