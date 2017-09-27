package com.inesv.digiccy.persistence.user;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/3 0003.
 */
public class DeleteTUser {
    @Autowired
    private QueryRunner queryRunner;

    public void delete(){
        String sql = "delete from t_user where id = ?";
        try {
            queryRunner.update(sql,21);
        }catch (SQLException e){
            throw new RuntimeException("sql error");
        }

    }
}
