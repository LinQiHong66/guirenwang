package com.inesv.digiccy.persistence.finance;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by yc on 2016/12/20 0020.
 */
@Component
public class RecUserPersistence {

    @Autowired
    QueryRunner queryRunner;


    /**
     *新增用户推荐码
     */
    public void updateInvite(int userNo,String invite){
        String sql = "update t_inesv_user set invite_num = ? where user_no = ?";
        Object parmas[] = {invite,userNo};
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
