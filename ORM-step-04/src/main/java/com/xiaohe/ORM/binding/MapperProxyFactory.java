package com.xiaohe.ORM.binding;

import com.xiaohe.ORM.session.SqlSession;

/**
 * @ClassName MapperProxyFactory
 * @Description 映射工厂
 * @Author 何
 * @Date 2023-06-06 20:52
 * @Version 1.0
 */
public class MapperProxyFactory<T> {
    public MapperProxyFactory(T type) {


    }

    public T newInstance(SqlSession sqlSession) {

        return null;
    }
}
