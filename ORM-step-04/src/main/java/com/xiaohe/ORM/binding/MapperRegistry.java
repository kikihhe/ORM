package com.xiaohe.ORM.binding;

import cn.hutool.core.lang.ClassScanner;
import com.xiaohe.ORM.session.Configuration;
import com.xiaohe.ORM.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName MapperRegistry
 * @Description 映射器注册机
 * @Author 何
 * @Date 2023-06-06 20:49
 * @Version 1.0
 */
public class MapperRegistry {
    private Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 将所有已经注册的Mapper全部放在这里
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    /**
     * 将某个路径下的所有mapper全部注册
     *
     * @param packageName 指定包路径
     */
    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }



    /**
     * 将某个类型加入容器
     * @param type 类型
     */
    public void addMapper(Class<?> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new RuntimeException(type + "这个类型已经被注册");
            } else {
                knownMappers.put(type, new MapperProxyFactory<>(type));
            }
        } else {
            throw new RuntimeException("只能注册接口类型, 而非: " + type);
        }
    }
    /**
     * 查看容器中是否含有mapper
     * @param type mapper
     * @return 是否含有
     */
    public boolean hasMapper(Class<?> type) {
        return knownMappers.containsKey(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (Objects.isNull(mapperProxyFactory)) {
            throw new RuntimeException(type + "这个类还没注册过");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("获取映射失败! " + e, e);
        }

    }
}
