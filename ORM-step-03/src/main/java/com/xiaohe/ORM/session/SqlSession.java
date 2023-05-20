package com.xiaohe.ORM.session;

/**
 * @ClassName SqlSession
 * @Description 会话
 * @Author 何
 * @Date 2023-05-19 22:51
 * @Version 1.0
 */
public interface SqlSession {
    Configuration getConfiguration();

    /**
     * 通过 执行对应的方法获取domain类
     * @param statement 对应方法的全限定名称
     * @return 返回domain类
     * @param <T>
     */
    public <T> T selectOne(String statement);


    /**
     * 通过执行对应的方法获取domain类，不同的是这个方法需要参数
     * @param statement 方法的全限定名称
     * @param parameters 参数，大多为 Map
     * @return 返回domain类
     * @param <T>
     */
    public <T> T selectOne(String statement, Object parameters);


    /**
     * 根据类型获取映射器
     * @param type 类型
     * @return
     * @param <T>
     */
    public <T> T getMapper(Class<T> type);
}
