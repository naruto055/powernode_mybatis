package org.god.batis.core;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.god.batis.utils.Resources;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SqlSessionFactory构造器对象
 * 通过SqlSessionFactoryBuilder的build方法来解析godbatis-config文件，然后创建SqlSessionFactory对象
 *
 * @Author: naruto
 * @CreateTime: 2024-11-06-23:33
 */
public class SqlSessionFactoryBuilder {

    /**
     * 无参数构造方法
     */
    public SqlSessionFactoryBuilder() {
    }

    /**
     * 解析godbatis-config.xml文件，来构建SqlSessionFactory对象
     *
     * @param in 指向 godbatis-config.xml文件的输入流
     * @return SqlSessionFactory对象
     */
    public SqlSessionFactory build(InputStream in) {
        SqlSessionFactory factory = null;
        // 解析godbatis-config.xml文件
        SAXReader reader = new SAXReader();
        try {
            // 解析配置文件
            Document document = reader.read(in);
            Element environments = (Element) document.selectSingleNode("/configuration/environments");
            String defaultId = environments.attributeValue("default");
            Element environment = (Element) document.selectSingleNode("/configuration/environments/environment[@id='" + defaultId + "']");
            Element transactionElt = environment.element("transactionManager");
            Element dataSourceElt = environment.element("dataSource");

            // 获取SQL映射文件
            List<String> sqlMapperXMLPathList = new ArrayList<>();
            List<Node> nodes = document.selectNodes("//mapper");
            nodes.forEach(node -> {
                Element mapper = (Element) node;
                String resource = mapper.attributeValue("resource");
                sqlMapperXMLPathList.add(resource);
            });

            // 获取数据源对象
            DataSource dataSource = getDataSource(dataSourceElt);

            // 获取事务管理器
            Transaction transaction = getTransaction(transactionElt, dataSource);
            // 获取mapperedStatement
            Map<String, MapperedStatement> mapperedStatement = getMapperStatements(sqlMapperXMLPathList);
            factory = new SqlSessionFactory(transaction, mapperedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }

    /**
     * 解析所有的sqlMapper文件，然后构建map集合
     *
     * @param sqlMapperXMLPathList
     * @return
     */
    private Map<String, MapperedStatement> getMapperStatements(List<String> sqlMapperXMLPathList) {
        Map<String, MapperedStatement> mapperedStatements = new HashMap<>();
        sqlMapperXMLPathList.forEach(sqlMapperPath -> {
            // 解析XXXMapper.xml 文件
            try {
                SAXReader reader = new SAXReader();
                Document document = reader.read(Resources.getResourcesAsStream(sqlMapperPath));
                Element element = (Element) document.selectSingleNode("mapper");
                String namespace = element.attributeValue("namespace");
                List<Element> elements = element.elements();
                elements.forEach(e -> {
                    String id = e.attributeValue("id");
                    String sqlId = namespace + "." + id;
                    String resultType = e.attributeValue("resultType");
                    String sql = e.getText().trim();

                    MapperedStatement mapperedStatement = new MapperedStatement(sql, resultType);
                    mapperedStatements.put(sqlId, mapperedStatement);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return mapperedStatements;
    }

    /**
     * 获取事务管理器
     *
     * @param transactionElt 事务管理器标签元素
     * @param dataSource     数据源对象
     * @return
     */
    private Transaction getTransaction(Element transactionElt, DataSource dataSource) {
        Transaction transaction = null;

        String type = transactionElt.attributeValue("type").trim().toUpperCase();
        if (Constant.JDBC_TRANSACTION.equals(type)) {
            transaction = new JDBCTransaction(dataSource, false);
        }
        if (Constant.MANAGEMENT_TRANSACTION.equals(type)) {
            transaction = new ManagementTransaction();
        }
        return transaction;
    }

    /**
     * 获取数据源对象
     *
     * @param dataSourceElt 数据源标签元素
     * @return
     */
    private DataSource getDataSource(Element dataSourceElt) {
        Map<String, String> map = new HashMap<>();

        // 获取dataSource下的所有子节点
        List<Element> propertyElts = dataSourceElt.elements("property");
        propertyElts.forEach(propertyElt -> {
            // 获取属性值
            String name = propertyElt.attributeValue("name").trim();
            String value = propertyElt.attributeValue("value").trim();
            map.put(name, value);
        });

        DataSource dataSource = null;
        String type = dataSourceElt.attributeValue("type").trim().toUpperCase();
        if (Constant.UNPOOLED_DATASOURCE.equals(type)) {
            dataSource = new UnPooledDatasource(map.get("driver"), map.get("url"), map.get("username"), map.get("password"));
        }
        if (Constant.POOLED_DATASOURCE.equals(type)) {
            dataSource = new PooledDatasource();
        }
        if (Constant.JNDI_DATASOURCE.equals(type)) {
            dataSource = new JNDIDatasource();
        }
        return dataSource;
    }
}
