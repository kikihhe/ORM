package com.xiaohe.ORM.mapping;

import com.xiaohe.ORM.session.Configuration;

import java.util.Map;

/**
 * @ClassName MappedStatement
 * @Description 存储每一条SQL的信息
 * @Author 何
 * @Date 2023-05-19 23:54
 * @Version 1.0
 */
public class MappedStatement {
    /**
     * 本条MappedStatement对应的配置，例如数据源、别名...
     */
    private Configuration configuration;
    /**
     * 本条sql的id，namespace+id
     */
    private String id;

    /**
     * sql语句的类型
     */
    private SqlCommandType sqlCommandType;

    /**
     * 参数的类型
     */
    private String parameterType;

    /**
     * 结果类型
     */
    private String resultType;

    /**
     * 本条MappedStatement关联的SQL语句
     */
    private String sql;

    /**
     * 参数名字
     * key: 第几个
     * value: 名字
     */
    private Map<Integer, String> parameter;

    /**
     * 空参构造
     */
    public MappedStatement() {
    }

    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();
        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, String parameterType, String resultType, String sql, Map<Integer, String> parameter) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.parameterType = parameterType;
            mappedStatement.resultType = resultType;
            mappedStatement.sql = sql;
            mappedStatement.parameter = parameter;
        }
        public MappedStatement build() {
            assert mappedStatement != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<Integer, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<Integer, String> parameter) {
        this.parameter = parameter;
    }
}
