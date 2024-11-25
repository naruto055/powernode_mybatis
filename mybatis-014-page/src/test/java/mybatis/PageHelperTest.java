package mybatis;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.CarMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.util.List;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-25-22:37
 */
public class PageHelperTest {
    @Test
    public void testPageHelper() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        PageHelper.startPage(2, 3);
        List<Car> cars = mapper.selectAll();
        PageInfo<Car> pageInfo = new PageInfo<>(cars);
        System.out.println(pageInfo);
        /*
        PageInfo{pageNum=2, pageSize=3, size=3, startRow=4, endRow=6, total=22, pages=8,
        list=Page{count=true, pageNum=2, pageSize=3, startRow=3, endRow=6, total=22, pages=8, reasonable=false, pageSizeZero=false}
        [Car(id=5, carNum=京A88888, brand=奔驰, guidePrice=50000.0, produceTime=2023-08-08, carType=SUV),
        Car(id=6, carNum=京A88888, brand=奔驰, guidePrice=50000.0, produceTime=2023-08-08, carType=SUV),
        Car(id=7, carNum=京A88888, brand=别克, guidePrice=50000.0, produceTime=2023-08-08, carType=SUV)],
        prePage=1, nextPage=3, isFirstPage=false, isLastPage=false, hasPreviousPage=true, hasNextPage=true,
        navigatePages=8, navigateFirstPage=1, navigateLastPage=8, navigatepageNums=[1, 2, 3, 4, 5, 6, 7, 8]}

         */
    }
}
