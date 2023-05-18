package com.xiaohe.ORM.session;

/***
 * @description: TODO
 * @author 何
 * @date 21:20 2023/5/18
 * @version 1.0
 */
public interface SqlSession {

    /**
     * statement指的是sql id, 方法名全限定类名
     * @param statement
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statement);

    /**
     * 通过方法的全限定类名与参数查询一个对象
     * @param statement 方法的权限定类名
     * @param parameter 参数, 通常是一个Map
     * @param <T>
     * @return 返回目标对象
     */
    public <T> T selectOne(String statement, Object parameter);


    /**
     * 获取代理对象
     * @param mapperInstance 类型
     * @param <T> 它的代理对象
     * @return
     */
    public <T> T getMapper(Class<T> mapperInstance);
}
