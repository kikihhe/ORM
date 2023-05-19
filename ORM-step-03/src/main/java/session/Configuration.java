package session;

import binding.MapperRegistry;
import cn.hutool.core.lang.hash.Hash;
import mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Configuration
 * @Description TODO
 * @Author 何
 * @Date 2023-05-19 23:52
 * @Version 1.0
 */
public class Configuration {
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }
    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }
    public <T> boolean hasMapper(Class<T> type) {
        return mapperRegistry.hasMapper(type);
    }
    public void addMappedStatement(MappedStatement mappedStatement) {
        mappedStatements.put(mappedStatement.getId(), mappedStatement);
    }
    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

}
