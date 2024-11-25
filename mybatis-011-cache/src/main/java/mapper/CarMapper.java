package mapper;


import pojo.Car;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-23-14:44
 */
public interface CarMapper {
    /**
     * 测试二级缓存
     * @param id
     * @return
     */
    Car selectById2(Long id);

    Car selectById(Long id);
}
