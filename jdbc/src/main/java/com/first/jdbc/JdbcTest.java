package com.first.jdbc;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Jdbc连接数据库
 *
 * @author luoxiaoqing
 * @date 2020-01-24__14:40
 */
public class JdbcTest {


    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static Connection conn = null;

    /**
     * 读取配置文件
     */
    public void getConfigInfo() {
        try {
            //  创建属性集和对象
            Properties p = new Properties();
            //  使用类加载器中的方法获取，io流的方式相对的就是src路径
            InputStream is = JdbcTemplateTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            p.load(is);
            //双引号中的url表示配置文件jdbc.properties中的key,即url
            url = p.getProperty("url");
            username = p.getProperty("username");
            password = p.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取连接
     */
    public Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 释放资源
     */
    public void release(Connection conn, Statement st, ResultSet rs) {
        try {
            //判断。防止空指针异常，有异常在关闭
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            //判断。防止空指针异常，有异常在关闭
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            //判断。防止空指针异常，有异常在关闭
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 最原始的创建
     * @throws Exception
     */
    @Test
    public void main1 () throws Exception {
        // 1.  注册数据库驱动
        // 2.  获取数据库连接
        // 3.  创建发送SQL对象
        // 4.  执行SQL语句，获取结果
        // 5.  遍历结果集
        // 6.  关闭资源
        String url = "jdbc:mysql://localhost:3306/first";
        String user = "root";
        String pwd = "123456";
        String sql = "select * from user";

        DriverManager.registerDriver(new Driver());
        Connection conn = DriverManager.getConnection(url, user, pwd);
        Statement stt = conn.createStatement();
        ResultSet result = stt.executeQuery(sql);

        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");

            System.out.println(id + " -> " + name );
        }

        result.close();
        stt.close();
        conn.close();
    }
}
