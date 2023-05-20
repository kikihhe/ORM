package com.xiaohe.ORM.builder;

import com.xiaohe.ORM.session.Configuration;

/**
 * @ClassName BaseBuilder
 * @Description 建造者模式
 * @Author 何
 * @Date 2023-05-20 18:51
 * @Version 1.0
 */
public abstract class BaseBuilder {
    private final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
