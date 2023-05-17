package com.xiaohe.ORM.test;

import com.xiaohe.ORM.binding.MapperProxyFactory;
import com.xiaohe.ORM.test.dao.IUserDao;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-05-17 23:01
 */
public class ProxyTest {

    @Test
    public void testProxy() {
        MapperProxyFactory<IUserDao> mapperProxyFactory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("com.xiaohe.ORM.test.dao.IUserDao.queryName", "执行IUserDao中的queryName方法");
        sqlSession.put("com.xiaohe.ORM.test.dao.IUserDao.queryUserAge", "执行IUserDao中的queryUserAge方法");

        IUserDao iUserDao = mapperProxyFactory.newInstance(sqlSession);
        String name = iUserDao.queryName();
//        Integer age = iUserDao.queryUserAge();

        System.out.println("name: " + name);
//        System.out.println("age: " + age);


    }
}
