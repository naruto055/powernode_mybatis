package com.mybatis.test;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-04-23:49
 */
public class ParseXMLByDom4jTest {
    @Test
    public void testParseMybatisConfigXML() throws Exception {
        // 创建SAXReader对象
        SAXReader saxReader = new SAXReader();
        // 获取输入流
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        // 读xml文件，返回document对象
        Document document = saxReader.read(in);
        // 获取文档当中的根标签
        Element rootElement = document.getRootElement();
        String rootElementName = rootElement.getName();
        /* System.out.println(document);
        System.out.println(rootElementName); */

        // 获取default默认的环境id
        String xpath = "/configuration/environments";  // 从根标签开始，获取子标签
        Element environments = (Element) document.selectSingleNode(xpath);
        // System.out.println(environments);

        // 获取属性的值
        String defaultEnvironmentId = environments.attributeValue("default");
        // System.out.println(defaultEnvironmentId);

        // 获取具体的环境environment
        xpath = "/configuration/environments/environment[@id='" + defaultEnvironmentId + "']";
        Element environment = (Element) document.selectSingleNode(xpath);
        System.out.println(environment);

        // 获取environment节点下的transactionManager节点
        Element transactionManager = environment.element("transactionManager");
        String transactionType = transactionManager.attributeValue("type");
        System.out.println("事务管理器的类型：" + transactionType);

        // 获取dataSource节点
        Element dataSource = environment.element("dataSource");
        String type = dataSource.attributeValue("type");
        System.out.println("dataSource 的类型：" + type);

        // 获取dataSource节点下的子节点
        List<Element> propertyList = dataSource.elements();
        propertyList.forEach(element -> {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");

            System.out.println(name + "=" + value);
        });


        // 获取所有mapper标签
        xpath = "/configuration/mappers/mapper";
        List<Node> mappers = document.selectNodes(xpath);
        mappers.forEach(node -> {
            Element mapper = (Element) node;
            String resource = mapper.attributeValue("resource");
            System.out.println("mapper 的 resource 属性：" + resource);
        });
    }

    @Test
    public void testParseSqlMapperXML() throws Exception {
        SAXReader reader = new SAXReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("CarMapper.xml");
        Document document = reader.read(is);
        // 获取namespace
        String xpath = "/mapper";
        Element mapper = (Element) document.selectSingleNode(xpath);
        String namespace = mapper.attributeValue("namespace");
        System.out.println(namespace);

        // 获取mapper节点下的子节点
        List<Element> elements = mapper.elements();
        elements.forEach(element -> {
            // 获取sqlId
            String sqlId = element.attributeValue("id");
            System.out.println("sqlId：" + sqlId);
            // 获取resultType
            String resultType = element.attributeValue("resultType");
            System.out.println(resultType);  // 没有属性的话，自动返回null

            // 获取标签中的sql语句，忽略空格
            String sqlStatement = element.getTextTrim();
            System.out.println(sqlStatement);
            String s = sqlStatement.replaceAll("#\\{[0-9A-Za-z_$]*}", "?");
            System.out.println(s);
        });

    }
}
