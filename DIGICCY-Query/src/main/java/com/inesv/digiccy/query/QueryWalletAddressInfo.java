package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.WalletAddressDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yc on 2016/12/19 0019.
 * 查询用户钱包地址信息
 */
@Component
public class QueryWalletAddressInfo {
    private static Logger logger = LoggerFactory.getLogger(QueryWalletAddressInfo.class);

    @Autowired
    QueryRunner queryRunner;

    public List<WalletAddressDto> queryWalletAddressInfo(int userNo,int coinNo){
        List<WalletAddressDto> list = null;
        String sql = "SELECT a.*,b.coin_name coinName,b.coin_core coinCode FROM t_inesv_wallet_address a INNER JOIN  t_inesv_coin_type b ON a.coin_no=b.coin_no WHERE a.user_no = ? AND a.coin_no=? ";
        Object parmas[] = {userNo,coinNo};
        try {
            list = queryRunner.query(sql,new BeanListHandler<WalletAddressDto>(WalletAddressDto.class),parmas);
        } catch (SQLException e) {
            logger.error("查询用户钱包信息失败");
            e.printStackTrace();
        }
        return list;
    }
 
}
