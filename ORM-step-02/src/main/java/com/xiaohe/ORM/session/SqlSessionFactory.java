package com.xiaohe.ORM.session;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-05-18 21:35
 */
public interface SqlSessionFactory {
    /**
     * 开启会话
     * @return DefaultSqlSession
     */
    public SqlSession openSqlSession();
}
