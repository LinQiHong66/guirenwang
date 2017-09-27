package com.inesv.digiccy.query.walletaddress;

import com.inesv.digiccy.dto.UserAndWalletAndCoinDto;
import com.inesv.digiccy.dto.WalletAddressDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JimJim on 2016/12/27 0027.
 */
@Component
public class QueryWalletAddress {
    private static Logger logger = LoggerFactory.getLogger(QueryWalletAddress.class);

    @Autowired
    QueryRunner queryRunner;//t_inesv_wallet_address

    /**
     *
     * */
    /**
     * 根据用户名查询用户钱包信息
     * @return
     */
    public WalletAddressDto queryWalletAddressOfUser(Integer user_no){
        WalletAddressDto walletAddressDto = null;
        String sql = "SELECT * FROM t_inesv_wallet_address where user_no=?";
        Object params[] = {user_no};
        try {
            walletAddressDto = (WalletAddressDto) queryRunner.query(sql, new BeanHandler(WalletAddressDto.class), params);
            return walletAddressDto;
        }catch (SQLException e){
            logger.error("查询钱包信息失败");
            e.printStackTrace();
        }
        return walletAddressDto;
    }
    /**
     * 查询所有用户钱包信息
     * */
    public List<UserAndWalletAndCoinDto> queryAllWalletAddress(){
        List<UserAndWalletAndCoinDto> WalletAddressList = new ArrayList<>();
        try {
            String sql = "select username,coin_name,idtf,wa.address,wa.date from  t_inesv_wallet_address wa,t_inesv_user us,t_inesv_coin_type ct where wa.user_no = us.user_no and wa.coin_no = ct.coin_no;";
            WalletAddressList = queryRunner.query(sql,new BeanListHandler<>(UserAndWalletAndCoinDto.class));
        } catch (SQLException e) {
            logger.error("查询钱包信息失败");
            e.printStackTrace();
        }
        return WalletAddressList;
    }
    /**
     * 根据币种查询用户钱包信息
     * */
    public WalletAddressDto queryWalletAddressOfCoin(Integer coin_no){
        WalletAddressDto walletAddressDto = null;
        String sql = "SELECT * FROM t_inesv_wallet_address where coin_no=?";
        Object params[] = {coin_no};
        try {
            walletAddressDto = (WalletAddressDto) queryRunner.query(sql, new BeanHandler(WalletAddressDto.class), params);
            return walletAddressDto;
        }catch (SQLException e){
            logger.error("查询钱包信息失败");
            e.printStackTrace();
        }
        return walletAddressDto;
    }

}
