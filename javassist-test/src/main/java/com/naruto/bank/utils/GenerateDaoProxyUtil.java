package com.naruto.bank.utils;


import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * 工具类：动态生成DAO的实现类
 *
 * @Author: naruto
 * @CreateTime: 2024-11-14-20:16
 */
public class GenerateDaoProxyUtil {

    /**
     * 生成dao接口实现类，并且将实现类的对象创建出来并返回
     *
     * @param daoInterface dao接口
     * @return dao接口的实现类
     */
    public static Object generate(Class daoInterface, SqlSession sqlSession) {
        // 类池
        ClassPool classPool = ClassPool.getDefault();
        // 制造类
        CtClass ctClass = classPool.makeClass(daoInterface.getName() + "Proxy");
        // 制造接口
        CtClass ctInterface = classPool.makeInterface(daoInterface.getName());
        // 实现接口
        ctClass.addInterface(ctInterface);
        // 实现接口中所有的方法
        Method[] methods = daoInterface.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            // method 是抽象方法
            // 将method这个抽象方法进行实现
            try {
                StringBuilder methodCode = new StringBuilder();
                methodCode.append("public ");
                methodCode.append(method.getReturnType().getName());
                methodCode.append(" ");
                methodCode.append(method.getName());
                methodCode.append("(");
                // 方法的形参列表
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> parameterType = parameterTypes[i];
                    methodCode.append(parameterType.getName());
                    methodCode.append(" arg" + i);
                    if (i != parameterTypes.length - 1) {
                        methodCode.append(", ");
                    }
                }
                methodCode.append(")");
                methodCode.append("{");
                // 方法体
                methodCode.append("org.apache.ibatis.session.SqlSession sqlSession = com.naruto.bank.utils.SqlSessionUtil.openSqlSession();");
                String sqlId = daoInterface.getName() + "." + method.getName();
                SqlCommandType sqlCommandType = sqlSession.getConfiguration().getMappedStatement(sqlId).getSqlCommandType();
                if (sqlCommandType == SqlCommandType.SELECT) {
                    String returnTypeName = method.getReturnType().getName();
                    methodCode.append("return (" + returnTypeName + ")  sqlSession.selectOne(\"" + sqlId + "\", arg0);");
                } else if (sqlCommandType == SqlCommandType.INSERT) {
                    methodCode.append("return sqlSession.insert(\"" + sqlId + "\", arg0);");
                } else if (sqlCommandType == SqlCommandType.UPDATE) {
                    methodCode.append("return sqlSession.update(\"" + sqlId + "\", arg0);");
                } else if (sqlCommandType == SqlCommandType.DELETE) {
                    methodCode.append("return sqlSession.delete(\"" + sqlId + "\", arg0);");
                }
                methodCode.append("}");

                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 创建对象
        Object object = null;
        try {
            Class<?> clazz = ctClass.toClass();
            object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
