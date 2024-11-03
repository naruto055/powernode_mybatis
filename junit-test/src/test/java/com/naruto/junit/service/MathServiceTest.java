package com.naruto.junit.service;


import org.junit.Assert;
import org.junit.Test;

/**
 * 单元测试类
 *
 * @Author: naruto
 * @CreateTime: 2024-11-03-17:00
 */
public class MathServiceTest {
    @Test
    public void testSum() {
        /**
         * 在单元测试中有两个重要的概念：
         * 一个是：实际值，也就是被测试方法返回的结果
         * 另一个是：期望值，也就是我们期望得到的结果
         */
        MathService mathService = new MathService();
        int actual = mathService.sum(1, 2);
        int expected = 3;
        // 加断言进行测试
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSub() {
        MathService mathService = new MathService();
        int actual = mathService.sub(5, 2);
        int expected = 3;
        Assert.assertEquals(expected, actual);
    }
}
