package com.hmx.mybatis;

import com.hmx.mybatis.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public void getUsersTest(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * getAllUsers是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.getUsers";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql

        SqlSession session = SqlSessionUtil.getSqlSession();
        Map<String, Object> param = new HashMap<>(10);
        //如果有多个条件参数，则xml文件中第一个条件才会拼接
        //param.put("name", "user");
        param.put("age", 30);
        List<User> userList = session.selectList(statement, param);
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
        SqlSession session = null;
        for (int i = 0; i < 10; i++) {
            String userName = GeneratorUserNameUtil.generatorUserNmae(i);
            User user = new User(userName, 34);
            session = SqlSessionUtil.getSqlSession();
            session.insert(statement, user);
            //非查询操作，需要手动调用commit()函数，提交sql
            session.commit();
        }

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

    @Test
    public void updateUserByIdTwoTest(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * updateUserById是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.updateUserByIdTwo";//映射sql的标识字符串
        User user = new User(26);
        user.setName("Peter");
        user.setAge(24);;
        SqlSession session = SqlSessionUtil.getSqlSession();
        int result = session.update(statement, user);
        //非查询操作，需要手动调用commit()函数，提交sql
        session.commit();
        System.out.println(result);
        getAllUsersTest();
        SqlSessionUtil.close(session);
    }

    /**
     * @description: 根据用户名进行模糊查询
     */

    @Test
    public void getUserByNameTest(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUserByName是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.getUserByName";//映射sql的标识字符串
        User user = new User();
        user.setName("user");
        SqlSession session = SqlSessionUtil.getSqlSession();
        List<User> userList = session.selectList(statement, user);
        printList(userList);
        //getAllUsersTest();
        SqlSessionUtil.close(session);
    }

    /**
     * @description: 根据用户iD和用户名进行模糊查询
     */

    @Test
    public void getUserByIDAndNameTest(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUserByName是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.getUserByIdAndName";//映射sql的标识字符串
        Map<String,Object> param = new HashMap<>(10);
        param.put("id", 22);
        param.put("name", "User");
        SqlSession session = SqlSessionUtil.getSqlSession();
        List<User> userList = session.selectList(statement, param);
        printList(userList);
        //getAllUsersTest();
        SqlSessionUtil.close(session);
    }
    /**
     * @description: 动态Sql查询用户
     */
    @Test
    public void getAllUsersTwoTest(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * getAllUsers是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.getAllUsersTwo";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql

        SqlSession session = SqlSessionUtil.getSqlSession();
        //List<User> userList = session.selectList(statement);
        Map<String, Object> param = new HashMap<>(10);
        param.put("name", "user4");
        List<User> userList = session.selectList(statement, param);
        SqlSessionUtil.close(session);
        for (User user : userList) {

            System.out.println(user);

        }
    }

    /**
     * @description: where标签拼接动态Sql查询用户
     */
    @Test
    public void getAllUsers3Test(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * getAllUsers是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.getAllUsers3";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql

        SqlSession session = SqlSessionUtil.getSqlSession();
        //List<User> userList = session.selectList(statement);
        Map<String, Object> param = new HashMap<>(10);
        param.put("name", "user");
        param.put("age", 20);
        List<User> userList = session.selectList(statement, param);
        SqlSessionUtil.close(session);
        for (User user : userList) {

            System.out.println(user);

        }
    }

    /**
     * @description: 根据用户名进行模糊查询
     */

    @Test
    public void getUsersInScopeTest(){
        /**
         * 映射sql的标识字符串，
         * com.hmx.mybatis.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUserByName是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.hmx.mybatis.mapping.UserMapping.getUsersInScope";//映射sql的标识字符串
        List<Integer> userIDs = new ArrayList<>(10);
        userIDs.add(27);
        userIDs.add(28);
        SqlSession session = SqlSessionUtil.getSqlSession();
        List<User> userList = session.selectList(statement, userIDs);
        printList(userList);
        //getAllUsersTest();
        SqlSessionUtil.close(session);
    }

    /**
     * @description: 泛型方法，打印查询结果
     * @param  list
     *         查询结果
     */
    public <T> void printList(List<T> list) {
        if (list == null) {
            return;
        }
        for (T obj : list) {
            System.out.println(obj);
        }
    }


}
