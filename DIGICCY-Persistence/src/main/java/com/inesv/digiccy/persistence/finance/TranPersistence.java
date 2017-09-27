package com.inesv.digiccy.persistence.finance;

import com.inesv.digiccy.dto.TranDto;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
@Component
public class TranPersistence {

    @Autowired
    QueryRunner queryRunner;

    /**
     *新增转账记录
     */
    public void addTranInfo(TranDto tranDto){
        String sql = "insert INTO t_inesv_tran(user_no,tran_user,coin_type,tran_num,poundage,state,date) VALUES(?,?,?,?,?,?,?)";
        Object parmas[] = {tranDto.getUser_no(),tranDto.getTran_user(),tranDto.getCoin_type(),
                tranDto.getTran_num(),tranDto.getPoundage(),tranDto.getState(),tranDto.getDate()};
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
