package mybatis.test;


import mapper.CarMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.io.IOException;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-23-14:45
 */
public class CarMapperTest {

    @Test
    public void testSelectById3() throws IOException {
        // 二级缓存对应的就是SqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sessionFactory.openSession();
        SqlSession sqlSession2 = sessionFactory.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);

        // 这行代码执行结束后，数据会缓存到一级缓存中（sqlSession是一级缓存）
        Car car = mapper.selectById2(13L);
        System.out.println(car);

        // 如果执行了这行代码，sqlSession1的缓存中的数据会放到二级缓存中
        sqlSession.close();

        // 这行代码执行结束后，数据会缓存到一级缓存中（sqlSession2是一级缓存）
        Car car1 = mapper2.selectById2(13L);

        // 如果程序执行到这里，会将sqlSession1这个一级缓存中的数据写入二级缓存中
        // sqlSession.close();

        sqlSession2.close();
    }

    @Test
    public void testSelectById2() throws IOException {
        // 如果要获取不同的sqlSession要自己来创建，不能使用这个封装的工具类了
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sessionFactory.openSession();
        SqlSession sqlSession1 = sessionFactory.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(2L);
        System.out.println(car);

        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        Car car1 = mapper1.selectById(2L);
        System.out.println(car1);
        // 这时候，两个不同的sqlSession，就会不走缓存了
    }

    @Test
    public void testSelectById() {
        // 如果要获取不同的sqlSession要自己来创建，不能使用这个封装的工具类了
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(2L);
        System.out.println(car);

        Car car1 = mapper.selectById(2L);
        System.out.println(car1);
        // 我们发现，两次查询，只执行了一条SQL语句
    }
}
