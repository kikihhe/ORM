package com.xiaohe.ORM.mapping;

/**
 * @ClassName SqlCommandType
 * @Description Sql指令类型
 * @Author 何
 * @Date 2023-06-06 20:32
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
