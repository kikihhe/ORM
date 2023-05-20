package com.xiaohe.ORM.session;

import com.xiaohe.ORM.builder.xml.XMLConfigBuilder;
import com.xiaohe.ORM.session.defaults.DefaultSqlSessionFactory;
import org.dom4j.DocumentException;

import java.io.Reader;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description 建造者模式
 * @Author 何
 * @Date 2023-05-20 22:12
 * @Version 1.0
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(Reader reader) throws DocumentException, ClassNotFoundException {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
