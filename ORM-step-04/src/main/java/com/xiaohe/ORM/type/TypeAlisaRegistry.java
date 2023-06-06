package com.xiaohe.ORM.type;

import cn.hutool.core.map.MapUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName TypeAlisaRegistry
 * @Description 别名注册机
 * @Author 何
 * @Date 2023-06-06 20:09
 * @Version 1.0
 */
public class TypeAlisaRegistry {
    /**
     * 别名注册机
     */
    private final Map<String, Class<?>> TYPE_ALISA = MapUtil.newHashMap();

    public TypeAlisaRegistry() {
        // 构造函数将8种基本数据类型和String注册上
        registerAlias("string", String.class);

        registerAlias("byte", Byte.class);
        registerAlias("boolean", Boolean.class);
        registerAlias("short", Short.class);
        registerAlias("char", Character.class);
        registerAlias("int", Integer.class);
        registerAlias("long", Long.class);
        registerAlias("float", Float.class);
        registerAlias("double", Double.class);
    }

    /**
     * 注册别名
     * @param alias 别名
     * @param value 类型
     */
    public void registerAlias(String alias, Class<?> value) {
        // 将所有别名设置为小写
        String key = alias.toLowerCase(Locale.ENGLISH);
        TYPE_ALISA.put(alias, value);
    }

    /**
     * 根据别名获取对象类型
     * @param string 别名
     * @return 对象类型
     * @param <T>
     */
    public <T> Class<T> resolveAlias(String string) {
        string = string.toLowerCase(Locale.ENGLISH);
        return (Class<T>) TYPE_ALISA.get(string);
    }
}
