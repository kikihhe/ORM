package binding;

import session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName MapperProxy
 * @Description MapperProxy
 * @Author 何
 * @Date 2023-05-19 22:49
 * @Version 1.0
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    private final Class<T> type;
    private SqlSession sqlSession;

    public MapperProxy(Class<T> type, SqlSession sqlSession) {
        this.type = type;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().equals(Object.class)) {
            method.invoke(proxy, args);
        }
        return sqlSession.selectOne(method.getName(), args);
    }
}
