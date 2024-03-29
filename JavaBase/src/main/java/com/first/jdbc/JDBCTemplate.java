package com.first.jdbc;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JdbcTemplateTest 操作数据库，需要连接池， 数据源
 *
 * @author luoxiaoqing
 */
public class JDBCTemplate {

    private JdbcTemplate jt;

    public JDBCTemplate() throws IOException {
        this.jt = new JdbcTemplate(getDataSource());
    }

    /**
     * 获取数据源
     * @return
     * @throws IOException
     */
    private DataSource getDataSource() throws IOException {
        //建立连接池资源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //读取配置信息
        Properties p = new Properties();
        InputStream is = JDBCTemplate.class.getClassLoader().getResourceAsStream("jdbc.properties");
        p.load(is);
        String driverClassName = p.getProperty("driverClassName");
        String url = p.getProperty("url");
        String username = p.getProperty("username");
        String password = p.getProperty("password");


        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }


    /**
     * 查询
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void query() throws SQLException, IOException {
        //  连接池, 数据源
        String name = jt.queryForObject("select name from t_user where id = ?", String.class, 1);
        System.out.println("name = " + name);
    }

    /**
     *
      */
    public void main1 () {

    }





}
