package com.xiaohe.ORM.session;

import cn.hutool.core.map.MapUtil;
import com.xiaohe.ORM.binding.MapperRegistry;
import com.xiaohe.ORM.mapping.Environment;
import com.xiaohe.ORM.mapping.MappedStatement;
import com.xiaohe.ORM.type.TypeAlisaRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Configuration
 * @Description 配置项
 * @Author 何
 * @Date 2023-06-06 20:45
 * @Version 1.0
 */
public class Configuration {
    /**
     * 环境
     * 包含 数据源、事务工厂
     */
    private Environment environment;

    /**
     * 注册工厂
     */
    protected MapperRegistry mapperRegistry;

    protected final Map<String, MappedStatement> mappedStatements = MapUtil.newHashMap();

    protected final TypeAlisaRegistry typeAlisaRegistry = new TypeAlisaRegistry();


    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }
    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }
    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }
    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }
    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }
    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }
    public TypeAlisaRegistry getTypeAlisaRegistry() {
        return typeAlisaRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
