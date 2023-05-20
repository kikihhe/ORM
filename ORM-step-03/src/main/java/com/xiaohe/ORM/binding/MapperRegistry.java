package com.xiaohe.ORM.binding;

import cn.hutool.core.lang.ClassScanner;
import com.xiaohe.ORM.session.Configuration;
import com.xiaohe.ORM.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName MapperRegistry
 * @Description TODO
 * @Author 何
 * @Date 2023-05-19 23:20
 * @Version 1.0
 */
public class MapperRegistry {
    /**
     * 所有已经被加载的Mapper
     */
    private Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    private Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 将指定的包下所有Mapper都注册为 MapperProxyFactory
     * @param packageName
     */
    public void addMappers(String packageName) {
        Set<Class<?>> mapperClasses = ClassScanner.scanPackage(packageName);
        mapperClasses.forEach(mapperClass -> {
            addMapper(mapperClass);
        });
    }

    /**
     * 根据指定的类型加载某一Mapper
     * @param mapperClass 指定的类型
     * @param <T>
     */
    public <T> void addMapper(Class<T> mapperClass) {
        if (!mapperClass.isInterface()) {
            throw new RuntimeException("被加载的类: " + mapperClass + " 不是接口");
        }
        if (knownMappers.containsKey(mapperClass)) {
            throw new RuntimeException("该接口" + mapperClass + "已被加载!");
        }
        knownMappers.put(mapperClass, new MapperProxyFactory<>(mapperClass));
    }
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        if (!knownMappers.containsKey(type)) {
            throw new RuntimeException("该类型未被加载!");
        }
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        return mapperProxyFactory.getMapper(sqlSession);
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }
}
