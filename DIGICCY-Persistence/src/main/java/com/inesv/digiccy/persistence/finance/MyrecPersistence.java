package com.inesv.digiccy.persistence.finance;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by yc on 2016/12/21 0021.
 */
@Component
public class MyrecPersistence {


    @Autowired
    QueryRunner queryRunner;

    public void updateRec(int userNo,int recUser,int recType,int auth,Date date){
        String sql = "insert into t_inesv_rec(user_no,rec_user,rec_type,auth,date)VALUES(?,?,?,?,?)";
        String sql2 = "insert into t_user_relations (user_no, relations_no) values (?,?)";
        Object parmas[] = {userNo,recUser,recType,auth,date};
        Object parmas2[]={recUser, userNo};
        try {
            queryRunner.update(sql,parmas);
            queryRunner.update(sql2, parmas2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
