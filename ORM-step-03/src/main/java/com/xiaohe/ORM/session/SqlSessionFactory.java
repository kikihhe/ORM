package com.xiaohe.ORM.session;

/**
 * @ClassName SqlSessionFactory
 * @Description SqlSession工厂
 * @Author 何
 * @Date 2023-05-20 22:10
 * @Version 1.0
 */
public interface SqlSessionFactory {
    /**
     * 获取SqlSession
     * @return
     */
    SqlSession openSqlSession();
}
