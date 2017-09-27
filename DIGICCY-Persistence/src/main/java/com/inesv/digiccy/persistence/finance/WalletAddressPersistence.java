package com.inesv.digiccy.persistence.finance;

import com.inesv.digiccy.dto.WalletAddressDto;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by yc on 2016/12/19 0019.
 */
@Component
public class WalletAddressPersistence {

    private static Logger logger = LoggerFactory.getLogger(WalletAddressPersistence.class);

    @Autowired
    QueryRunner queryRunner;


    /**新增用户钱包地址*/
    public void addWalletAddress(int userNo, int coinNo, String idth, String address, Date date){
        String sql = "insert into t_inesv_wallet_address(user_no,coin_no,idtf,address,date)VALUES(?,?,?,?,?)";
        Object parmas[] = {userNo,coinNo,idth,address,date};
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            logger.error("新增钱包地址失败");
            e.printStackTrace();
        }
    }


    /**删除钱包地址*/
    public void deleteWalletAddress(Long id){
        String sql = "DELETE FROM t_inesv_wallet_address WHERE id = ?";
        Object parmas[] = {id};
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
