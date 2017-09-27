package com.inesv.digiccy.persistence.user;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public class UpdateTUser {

    @Autowired
    QueryRunner queryRunner;

    public void updateUser(){
        String sql = "update t_inesv_user";
    }
}
