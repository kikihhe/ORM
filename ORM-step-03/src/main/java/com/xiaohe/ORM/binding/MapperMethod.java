package com.xiaohe.ORM.binding;

import com.xiaohe.ORM.mapping.MappedStatement;
import com.xiaohe.ORM.mapping.SqlCommandType;
import com.xiaohe.ORM.session.Configuration;
import com.xiaohe.ORM.session.SqlSession;

import java.lang.reflect.Method;


import static com.xiaohe.ORM.mapping.SqlCommandType.*;

/**
 * @ClassName MapperMethod
 * @Description TODO
 * @Author 何
 * @Date 2023-05-20 23:44
 * @Version 1.0
 */
public class MapperMethod {

    private final SqlCommand command;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        this.command = new SqlCommand(configuration, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (command.getType()) {
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.getName(), args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());
        }
        return result;
    }

    /**
     * SQL 指令
     */
    public static class SqlCommand {

        private final String name;
        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }

}

