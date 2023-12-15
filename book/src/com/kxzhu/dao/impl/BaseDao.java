package com.kxzhu.dao.impl;

import com.kxzhu.pojo.User;
import com.kxzhu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 用来给别人复用代码，不需要对象实例
 * 所以声明为静态的，只用于继承
 *
 * 封装了针对于数据表的通用的操作（CRUD)
 *
 * 这里不能关闭连接，后面还要用同一个连接，要在事务提交/回滚时，才能关闭
 *
 * @author kxzhu
 * @create 2022-09-21 22:54
 */
public abstract class BaseDao {
    //使用DBUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update()方法用来执行:Insert\Update\Delete 语句
     * @return 如果返回-1，说明执行失败；返回其他，表示影响的行数
     */
    public int update(String sql, Object... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e); //throw之后 就不用再return -1了
        }
    }

    /**
     * 查询返回一个javaBean的sql语句
     * 即查询一条记录
     * @param type  返回的对象类型，用反射
     * @param sql   执行的sql语句
     * @param args  sql的参数值（？占位符）
     * @param <T>   返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object...args){
        Connection conn = JDBCUtils.getConnection();
        BeanHandler<T> handler = new BeanHandler<>(type); //type: xx.class
        try {
            return queryRunner.query(conn, sql, handler, args); //返回查到的数据条数（1）
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个javaBean的sql语句
     * @param type  返回的对象类型，用反射
     * @param sql   执行的sql语句
     * @param args  sql的参数值（？占位符）
     * @param <T>   返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object...args){
        Connection conn = JDBCUtils.getConnection();
        BeanListHandler<T> handler = new BeanListHandler<>(type);
        try {
            return queryRunner.query(conn, sql, handler, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql   执行的sql语句
     * @param args  sql的参数值（？占位符）
     * @return
     */
    public Object queryForSingleValue(String sql, Object...args){
        Connection conn = JDBCUtils.getConnection();
        ScalarHandler handler = new ScalarHandler();
        try {
            return queryRunner.query(conn, sql, handler, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
