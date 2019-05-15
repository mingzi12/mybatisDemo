package com.hmx.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author TODO
 * @description: TODO
 * @date 2019/5/15 10:16
 */
public class SqlSessionUtil {

    private static final String resource = "conf/mybatis.xml";

    /*private static final SqlSessionUtil INSTANCE = new SqlSessionUtil();

    public static SqlSessionUtil getInstance() {
        return INSTANCE;
    }*/

    public static SqlSession getSqlSession() {
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = UserDaoImplTest.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        SqlSession session = sessionFactory.openSession();

        return session;
    }

    public static void close(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }

}
