package com.first.jdbc;

import org.junit.Test;
import com.mysql.jdbc.Driver;
import java.sql.*;

/**
 * Jdbc连接数据库
 *
 * @author luoxiaoqing
 * @date 2020-01-24__14:40
 */
public class JdbcTest {

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
