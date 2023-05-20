package com.xiaohe.ORM.mapping;

/**
 * @ClassName SqlCommandType
 * @Description 当前执行操作的类型
 * @Author 何
 * @Date 2023-05-20 00:14
 * @Version 1.0
 */
public enum SqlCommandType {

    /**
     * 未知
     */
    UNKNOWN,
    /**
     * 插入
     */
    INSERT,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 查找
     */
    SELECT;

}
