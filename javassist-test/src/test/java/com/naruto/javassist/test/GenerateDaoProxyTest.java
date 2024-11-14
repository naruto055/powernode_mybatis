package com.naruto.javassist.test;


import com.naruto.bank.dao.AccountDAO;
import com.naruto.bank.model.Account;
import com.naruto.bank.utils.GenerateDaoProxyUtil;
import com.naruto.bank.utils.SqlSessionUtil;
import org.junit.Test;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-14-21:30
 */
public class GenerateDaoProxyTest {

    // private AccountDAO accountDAO = (AccountDAO) GenerateDaoProxyUtil.generate(AccountDAO.class, SqlSessionUtil.openSqlSession());
    /*
    在mybatis中，mybatis提供了相关的机制，也可以动态为我们生成实现类，但是需要我们自己手动去配置，
    mybatis当中实际上采用了代理模式，在内存中生成dao接口的代理类，然后创建代理类的实例。
    使用mybatis的这种代理机制的前提，SqlMapper.xml 文件中的namespace必须是dao接口的全限定名称，id必须是dao接口中的方法
     */

    private AccountDAO accountDAO = SqlSessionUtil.openSqlSession().getMapper(AccountDAO.class);

    @Test
    public void testGenerate() throws Exception{
        // 测试查询
        Account account = accountDAO.selectByActno("act001");
        System.out.println(account);
    }
}
