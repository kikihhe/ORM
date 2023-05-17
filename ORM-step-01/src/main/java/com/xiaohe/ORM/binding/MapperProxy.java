package com.xiaohe.ORM.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author : 小何
 * @Description : 动态映射
 * @date : 2023-05-17 22:45
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    private static final long serialVersionUID = -6424540398559729838L;

    /**
     * SqlSession对象
     */
    private Map<String, String> sqlSession;

    private final Class<T> mapperInterface;

    /**
     * 指定此 MapperProxy 是什么类型的代理
     * @param mapperInterface 需要被代理的类型
     */
    public MapperProxy(Class<T> mapperInterface, Map<String, String> sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            // 在这里执行具体的代理逻辑
            return "被代理:  " + sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }
}
