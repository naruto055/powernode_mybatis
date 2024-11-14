package com.naruto.javassist.test;


import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-12-21:34
 */
public class JavassistTest {

    @Test
    public void testGenerateFirstClass() throws Exception {
        // 获取类池，这个类就是用来生成class的
        ClassPool pool = ClassPool.getDefault();

        // 制造类
        CtClass ctClass = pool.makeClass("com.naruto.bank.dao.impl.AccountDaoImpl");
        // 制造方法
        String methodCode = "public void insert() {System.out.println(123);}";
        CtMethod ctMethod = CtMethod.make(methodCode, ctClass);
        // 将方法加入到类中
        ctClass.addMethod(ctMethod);
        // 生成类
        ctClass.toClass();
        // 类加载
        Class<?> clazz = Class.forName("com.naruto.bank.dao.impl.AccountDaoImpl");
        // 创建对象
        Object obj = clazz.newInstance();
        Method declaredMethod = clazz.getDeclaredMethod("insert");
        declaredMethod.invoke(obj);
    }

}
