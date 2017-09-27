package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.WalletLinkDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by yc on 2016/12/15 0015.
 */

@Component
public class QueryWalletLinkInfo {

    private static Logger logger = LoggerFactory.getLogger(QueryWalletLinkInfo.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     *查询钱包连接信息
     */
    public WalletLinkDto queryLinkInfo(int coinType){
        WalletLinkDto walletLinkDto = null;
        String sql = "select * from t_inesv_wallet_link where coin_no = ?";
        Object parmas[] = {coinType};
        try {
            walletLinkDto = (WalletLinkDto) queryRunner.query(sql,new BeanHandler(WalletLinkDto.class),parmas);
            return walletLinkDto;
        } catch (SQLException e) {
            logger.error("查询钱包链接信息失败");
            e.printStackTrace();
        }
        return walletLinkDto;
    }
 

}
