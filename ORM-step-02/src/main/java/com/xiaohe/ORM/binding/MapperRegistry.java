package com.xiaohe.ORM.binding;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.ClassScanner;
import com.xiaohe.ORM.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-05-18 21:25
 */
public class MapperRegistry {
    /**
     *
     */
    private Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    /**
     * 扫描指定包，将指定包下的所有接口注册到Map中
     * @param packageName
     */
    public void addMappers(String packageName) {
        Set<Class<?>> classes = ClassScanner.scanPackage(packageName);
        classes.forEach(item -> {
            addMapper(item);
        });
    }

    /**
     * 将类型为type的接口放入Map
     * @param type 指定的类型
     */
    private void addMapper(Class<?> type) {
        Assert.isTrue(type.isInterface(), "Type " + type + " is not a interface!");
        Assert.isTrue(!hasMapper(type), "Type " + type + " is already known to the MapperRegistry!");
        // 放入Map
        knownMappers.put(type, new MapperProxyFactory<>(type));
    }

    /**
     * 判断Map中是否已经有这个类型
     * @param type 类型
     * @return 指定类型是否在Map中已经存在。
     */
    private boolean hasMapper(Class<?> type) {
        return knownMappers.containsKey(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        Assert.isNull(mapperProxyFactory, "Type: "+ type + " is not known to the MapperRegistry");
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch(Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

}
