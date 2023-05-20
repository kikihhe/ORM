package com.xiaohe.ORM.binding;

import com.xiaohe.ORM.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ClassName MapperProxy
 * @Description MapperProxy
 * @Author 何
 * @Date 2023-05-19 22:49
 * @Version 1.0
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    private static final long serialVersionUID = -6424540398559729838L;
    private final Map<Method, MapperMethod> methodCache;

    private final Class<T> type;
    private SqlSession sqlSession;

    public MapperProxy(Map<Method, MapperMethod> methodCache, Class<T> type, SqlSession sqlSession) {
        this.methodCache = methodCache;
        this.type = type;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().equals(Object.class)) {
            return method.invoke(proxy, args);
        } else {
            final MapperMethod mapperMethod = cacheMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }

    }

    public MapperMethod cacheMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            mapperMethod = new MapperMethod(type, method, sqlSession.getConfiguration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
