package com.xiaohe.ORM.builder.xml;

import com.xiaohe.ORM.builder.BaseBuilder;
import cn.hutool.core.map.MapUtil;
import com.xiaohe.ORM.io.Resources;
import com.xiaohe.ORM.mapping.MappedStatement;
import com.xiaohe.ORM.mapping.SqlCommandType;
import com.xiaohe.ORM.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;


import java.io.Reader;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName XMLConfigBuilder
 * @Description XML配置构建器
 * @Author 何
 * @Date 2023-05-20 18:53
 * @Version 1.0
 */
public class XMLConfigBuilder extends BaseBuilder {
    private Element root;

    /**
     * 构造方法，传入Reader, 将父类的Configuration创建后，根据Reader获取Document再获取root节点
     * @param reader
     * @throws DocumentException
     */
    public XMLConfigBuilder(Reader reader) throws DocumentException {
        super(new Configuration());
        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(new InputSource(reader));
        root = document.getRootElement();
    }

    public Configuration parse() throws DocumentException, ClassNotFoundException {
        // 获取根节点下的 mappers 标签
        Element mappers = root.element("mappers");
        // 解析mappers标签中的所有mapper标签(与之对应的Mapper文件)
        mapperElement(mappers);
        return getConfiguration();
    }
    public void mapperElement(Element mappers) throws DocumentException, ClassNotFoundException {
        // 获取所有Mapper标签
        List<Element> mapperList = mappers.elements("mapper");
        for (Element mapper : mapperList) {
            // 获取该mapper的全限定类名
            String resource = mapper.attributeValue("resource");
            Reader resourceReader = Resources.getResourceAsReader(resource);
            // 获取该Reader对应的Mapper的Document
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(resourceReader));
            // 获取根节点: <mapper>标签
            Element rootElement = document.getRootElement();
            String namespace = rootElement.attributeValue("namespace");

            // 开始解析具体的<select>标签
            List<Element> selectNodes = rootElement.elements("select");
            for (Element selectNode : selectNodes) {
                // 该标签的名称(select、insert、delete、update)
                String nodeName = selectNode.getName();
                // 方法的名称
                String id = selectNode.attributeValue("id");
                // 全限定方法名
                String mappedId = namespace + "." + id;
                String parameterType = selectNode.attributeValue("parameterType");
                String resultType = selectNode.attributeValue("resultType");
                String sql = selectNode.getText();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ROOT));
                // 替换sql语句中的 #{username}、${password}...参数
                Map<Integer, String> parameter = MapUtil.newHashMap();
                Pattern compile = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = compile.matcher(sql);
                for (int i = 1; matcher.find(); i++) {
                    // g1: username
                    // g2: username具体的值
                    String g1 = matcher.group(1);
                    String g2 = matcher.group(2);
                    sql = sql.replace(g1, "?");
                    parameter.put(i, g2);
                }

                MappedStatement mappedStatement = new MappedStatement.Builder(getConfiguration(), mappedId, sqlCommandType, parameterType, resultType, sql, parameter).build();
                getConfiguration().addMappedStatement(mappedStatement);
            }
            getConfiguration().addMapper(Resources.classForName(namespace));
        }


    }
}
