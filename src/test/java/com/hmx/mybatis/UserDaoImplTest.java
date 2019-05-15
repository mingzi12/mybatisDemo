package com.hmx.mybatis;

import com.hmx.mybatis.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;


public class UserDaoImplTest {

    @Test
    public void getUserByIdTest(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUserById是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.getUserById";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql

        SqlSession session = SqlSessionUtil.getSqlSession();
        User user = session.selectOne(statement, 1);
        System.out.println(user.toString());
        SqlSessionUtil.close(session);
    }

    @Test
    public void getAllUsersTest(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * getAllUsers是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.getAllUsers";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql

        SqlSession session = SqlSessionUtil.getSqlSession();
        List<User> userList = session.selectList(statement);
        SqlSessionUtil.close(session);
        for (User user : userList) {

            System.out.println(user);

        }
    }

    @Test
    public void insertUserTest(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * addUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.addUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = new User("Lebron James", 34);
        SqlSession session = SqlSessionUtil.getSqlSession();
        int result = session.insert(statement, user);
        //非查询操作，需要手动调用commit()函数，提交sql
        session.commit();
        System.out.println(result);
        getAllUsersTest();
        SqlSessionUtil.close(session);
    }

    @Test
    public void deleteUserByIdTest(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * deleteUserById是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.deleteUserById";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql

        SqlSession session = SqlSessionUtil.getSqlSession();
        User user = new User(3);
        int  result = session.delete(statement, user);
        //非查询操作，需要手动调用commit()函数，提交sql
        session.commit();
        System.out.println(result);
        getAllUsersTest();
        SqlSessionUtil.close(session);
    }

    @Test
    public void updateUserByIdTest(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * updateUserById是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.updateUserById";//映射sql的标识字符串
        User user = new User(4);
        user.setName("Peter");
        SqlSession session = SqlSessionUtil.getSqlSession();
        int result = session.update(statement, user);
        //非查询操作，需要手动调用commit()函数，提交sql
        session.commit();
        System.out.println(result);
        getAllUsersTest();
        SqlSessionUtil.close(session);
    }


}
