package com.xiaohe.ORM;

import com.xiaohe.ORM.dao.IUserDao;
import com.xiaohe.ORM.io.Resources;
import com.xiaohe.ORM.session.SqlSession;
import com.xiaohe.ORM.session.SqlSessionFactory;
import com.xiaohe.ORM.session.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;


/**
 * @ClassName APITest
 * @Description 测试类
 * @Author 何
 * @Date 2023-05-20 22:18
 * @Version 1.0
 */
public class APITest {
    private Logger log = LoggerFactory.getLogger(APITest.class);

    @Test
    public void test_SqlSessionFactory() throws DocumentException, ClassNotFoundException {
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String result = userDao.queryUserInfoById("1001");
        log.info(result);
    }
}
