package io.github.rainblooding.pool.datasource;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

public class SkyDragonDataSourceTest {

    private SkyDragonDataSource dataSource;



    @Before
    public void init() {
        dataSource = new SkyDragonDataSource();

        dataSource.setUrl("jdbc:mysql://192.168.0.64:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&autoReconnect=true&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull");
        dataSource.setUser("root");
        dataSource.setPassword("test");
        dataSource.setMinSize(1);
        // 初始化
        dataSource.init();
    }

    @Test
    public void testQuery() throws SQLException, ParseException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("show databases");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        resultSet.close();
        statement.close();
        dataSource.destroy();
    }
}
