package pojo;


import lombok.Data;

import java.io.Serializable;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-23-14:43
 */
@Data
public class Car implements Serializable {
    private Long id;

    /**
     * 汽车编号
     */
    private String carNum;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 指导价
     */
    private Double guidePrice;

    /**
     * 出厂时间
     */
    private String produceTime;

    /**
     * 汽车类型
     */
    private String carType;
}
