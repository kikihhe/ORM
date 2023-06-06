package com.xiaohe.ORM.session;

/**
 * @ClassName SqlSession
 * @Description 会话
 * @Author 何
 * @Date 2023-06-06 21:04
 * @Version 1.0
 */
public interface SqlSession {
    /**
     * 获取映射
     * @param type 类型
     * @return 返回该类型对应的映射
     * @param <T>
     */
    <T> T getMapper(Class<T> type);

    /**
     * 获取该会话对应的配置信息
     * @return 配置信息
     */
    Configuration getConfiguration();
}
