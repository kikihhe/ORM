package binding;

import session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @ClassName MapperProxyFactory
 * @Description MapperProxy的工厂
 * @Author 何
 * @Date 2023-05-19 23:00
 * @Version 1.0
 */
public class MapperProxyFactory<T> {
    private final Class<T> type;

    public MapperProxyFactory(Class<T> type) {
        this.type = type;
    }
    public T getMapper(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(type, sqlSession);
        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class[]{type},
                mapperProxy
        );
    }
}
