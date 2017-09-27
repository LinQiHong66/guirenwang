package com.inesv.digiccy.persistence.wallet;

import com.inesv.digiccy.dto.WalletLinkDto;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 钱包接口持久层
 * Created by Ben on 2016/12/20 0020.
 */

@Component
public class WalletLinkOperation {
        private static Logger logger = LoggerFactory.getLogger(WalletLinkOperation.class);

        @Autowired
        QueryRunner queryRunner;

    /**
     * 新增钱包接口地址
     * @param walletLinkDto
     * */
    public void addWalletLink(WalletLinkDto walletLinkDto){
        String sql = "insert into t_inesv_wallet_link(coin_no,host,post,wallet_name,wallet_pwd,wallet_lockpwd) values(?,?,?,?,?,?)";
        Object params[] = {walletLinkDto.getCoin_no(),walletLinkDto.getHost(),walletLinkDto.getPost(),walletLinkDto.getWallet_name(),walletLinkDto.getWallet_pwd(),walletLinkDto.getWallet_lockpwd()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("新增钱包地址错误");
            e.printStackTrace();
        }
    }

    /**
     * 修改钱包接口
     * @param walletLinkDto
     * */
    public void updateWalletLink(WalletLinkDto walletLinkDto){
        String sql = "UPDATE t_inesv_wallet_link " +
                "SET host = ?,post = ?,wallet_name = ?,wallet_pwd = ?, wallet_lockpwd = ?" +
                "WHERE coin_no = ?";
        Object params[] = {walletLinkDto.getHost(),walletLinkDto.getPost(),walletLinkDto.getWallet_name(),walletLinkDto.getWallet_pwd(),
                walletLinkDto.getWallet_lockpwd(),walletLinkDto.getCoin_no()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("修改钱包地址错误");
            e.printStackTrace();
        }

    }

    /**
     * 删除钱包接口
     * @param coin_no
     */
    public void deleWalletLink(Integer coin_no){
        String sql="DELETE FROM t_inesv_wallet_link WHERE coin_no = ? ";
        Object params[]={coin_no};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("删除钱包地址错误");
            e.printStackTrace();
        }
    }
}
