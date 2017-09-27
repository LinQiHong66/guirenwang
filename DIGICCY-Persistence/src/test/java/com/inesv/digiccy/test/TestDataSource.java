package com.inesv.digiccy.test;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by SKINK on 2016/10/6.
 */
public class TestDataSource{
    @Autowired
    QueryRunner queryRunner;


    @Test
    public void dataSourceTest(){
        String sql = "insert into inesv_user(username,password,nickname) values(?,?,?)";
        Map<Long, Map<String, Object>> key = null;
        try {
            key = queryRunner.insert(sql,new KeyedHandler<Long>(),"admin456","mima456","zhangsan");
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException("sql error");
        }
        System.out.println(key);
    }
}
