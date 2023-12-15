package com.kxzhu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取连接和关闭连接
 * @author kxzhu
 * @create 2022-09-21 16:17
 */
public class JDBCUtils {

    private static DruidDataSource dataSource;//不管哪种技术，都是DataSource的实现类。此处使用德鲁伊连接池
    private static ThreadLocal<Connection> connection = new ThreadLocal<Connection>();//ThreadLocal对象

    static {
        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
//            System.out.println(dataSource.getConnection());//测试是否成功连接。结果：com.mysql.cj.jdbc.ConnectionImpl@35cabb2a
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null，则获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection()  {

        Connection conn = connection.get();

        if(conn == null){
            //第一次取，没有用connction.set(conn)保存之前，从数据库连接池里取
            try {
                conn = dataSource.getConnection();

                //获取后，保存进ThreadLocal对象中，以后就有了，供后面的jdbc操作使用
                connection.set(conn);

                conn.setAutoCommit(false);//设置 手动管理事务

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务，并关闭释放连接，放回数据库连接池
     */
    public static void commitAndClose(){
        Connection conn = JDBCUtils.connection.get();
        if(conn != null){
            try {
                //说明以前用过连接，操作过数据库
                conn.commit();//提交事务

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();//关闭释放连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。(因为Tomcat服务器底层使用了线程池技术)
        connection.remove();
    }

    /**
     * 回滚事务，并关闭释放连接，放回数据库连接池
     */
    public static void rollbackAndClose(){
        Connection conn = JDBCUtils.connection.get();
        if(conn != null){
            try {
                //说明以前用过连接，操作过数据库
                conn.rollback();//回滚事务

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();//关闭释放连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。(因为Tomcat服务器底层使用了线程池技术)
        connection.remove();
    }



    /**
     * 关闭连接，放回数据库连接池
     * @param conn
     */
/*
    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    */


}
