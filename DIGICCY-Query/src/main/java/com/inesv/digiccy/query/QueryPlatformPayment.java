package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InesvBankInfo;
import com.inesv.digiccy.dto.PlatformPaymentDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Component
public class QueryPlatformPayment {

    private static Logger logBank= LoggerFactory.getLogger(QueryPlatformPayment.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     * 查询平台收款银行账户
     * @return
     */
    public PlatformPaymentDto queryBankAccount(){
        String sql = "SELECT *FROM t_inesv_platform_payment WHERE id=1"; //1为银行卡
        PlatformPaymentDto platf=null;
        try {
        	platf = queryRunner.query(sql, new BeanHandler<PlatformPaymentDto>(PlatformPaymentDto.class));
         
        }catch (SQLException e){
            e.printStackTrace();
            logBank.error("查询平台收款银行账户");
        }
        return platf;
    }

     
}
