package com.inesv.digiccy.persistence.finance;

import com.inesv.digiccy.dto.RecSubProfitDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
@Component
public class UserBalancePersistence {

	private static Logger logger = LoggerFactory.getLogger(UserBalancePersistence.class); 
	
    @Autowired
    QueryRunner queryRunner;

    

    /**
     *转账给用户之后更新自己的可用金额
     */
    @Transactional
    public void updateUserenableCoinxn(UserBalanceDto balanceDto){
        String sql = "UPDATE t_inesv_user_balance SET enable_coin = ?,total_price = ? WHERE user_no = ? and coin_type = ?";
        Object parmas[] = {balanceDto.getEnable_coin(),balanceDto.getTotal_price(),balanceDto.getUser_no(),balanceDto.getCoin_type()};
        System.out.println("转账之前:"+sql);
        System.out.println("<<<<<<<<<<<<<<<<< nable:"+balanceDto.getEnable_coin());
        System.out.println("<<<<<<<<<<<<<<<<< balanceDto.getTotal_price():"+balanceDto.getTotal_price());
        System.out.println("<<<<<<<<<<<<<<<<< balanceDto.getUser_no():"+balanceDto.getUser_no());
        System.out.println("<<<<<<<<<<<<<<<<< balanceDto.getCoin_type():"+balanceDto.getCoin_type());
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 根据用户编号和币种类型新增用户钱包
     */
    public int updateWallteAddress(UserBalanceDto userBalanceDto){
        String sql = "update t_inesv_user_balance set wallet_address=? where user_no=? and coin_type=?";
        Object parmas[] = {userBalanceDto.getWallet_address(),userBalanceDto.getUser_no(),userBalanceDto.getCoin_type()};
        try {
            int result = queryRunner.update(sql,parmas);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *转账给用户之后更新用户的可用金额
     */
    @Transactional
    public void updateTranUserenableCoin(UserBalanceDto balanceDto){
        String sql = "UPDATE t_inesv_user_balance SET enable_coin = ?,total_price = ? WHERE user_no = ? and coin_type = ?";
        Object parmas[] = {balanceDto.getEnable_coin(),balanceDto.getTotal_price(),balanceDto.getUser_no(),balanceDto.getCoin_type()};
        System.out.println("转账之后:"+sql);
        System.out.println("<<<<<<<<<<<<<<<<< nable:"+balanceDto.getEnable_coin());
        System.out.println("<<<<<<<<<<<<<<<<< balanceDto.getTotal_price():"+balanceDto.getTotal_price());
        System.out.println("<<<<<<<<<<<<<<<<< balanceDto.getUser_no():"+balanceDto.getUser_no());
        System.out.println("<<<<<<<<<<<<<<<<< balanceDto.getCoin_type():"+balanceDto.getCoin_type());
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    /**
     *众筹后，更改个人可用资金
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void updateTranUserenableCoins(UserBalanceDto balanceDto) throws Exception{
        String sql = "UPDATE t_inesv_user_balance SET enable_coin = ?,total_price = ? WHERE user_no = ? and coin_type = ?";
        Object parmas[] = {balanceDto.getEnable_coin(),balanceDto.getTotal_price(),balanceDto.getUser_no(),balanceDto.getCoin_type()};
            queryRunner.update(sql,parmas);
    }



    /**
     *转账给用户之后更新自己的可用金额
     */
    @Transactional
    public void updateUserenableCoin(UserBalanceDto balanceDto){
        String sql = "UPDATE t_inesv_user_balance SET enable_coin = ?,unable_coin = ? WHERE user_no = ? and coin_type = ?";
        Object parmas[] = {balanceDto.getEnable_coin(),balanceDto.getTotal_price(),balanceDto.getUser_no(),balanceDto.getCoin_type()};
        System.out.println("转账之前:"+sql);
        System.out.println("<<<<<<<<<<<<<<<<< nable:"+balanceDto.getEnable_coin());
        System.out.println("<<<<<<<<<<<<<<<<< balanceDto.getTotal_price():"+balanceDto.getTotal_price());
        System.out.println("<<<<<<<<<<<<<<<<< balanceDto.getUser_no():"+balanceDto.getUser_no());
        System.out.println("<<<<<<<<<<<<<<<<< balanceDto.getCoin_type():"+balanceDto.getCoin_type());
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *用户没有币种则新增用户相应币种
     */
    public void insertTranCoinType(UserBalanceDto balanceDto){
        String sql = "INSERT into t_inesv_user_balance(user_no,coin_type,enable_coin,unable_coin,total_price,wallet_address,date)VALUES(?,?,?,?,?,?,?)";
        Object parmas[] = {balanceDto.getUser_no(),balanceDto.getCoin_type(),balanceDto.getEnable_coin(),balanceDto.getUnable_coin(),
                balanceDto.getTotal_price(),balanceDto.getWallet_address(),balanceDto.getDate()};
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * 添加用户资产
     * @param userBalanceDto
     */
	public void addUserBalance(UserBalanceDto userBalanceDto) {
		String sql = new ObjectChangeUtil<UserBalanceDto>().objectToSql(userBalanceDto, TableName.T_INESV_USER_BALANCE);
		
        Object params[] = new ObjectChangeUtil<UserBalanceDto>().objectToArray(userBalanceDto);
      
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("新增用户资产错误");
            e.printStackTrace();
        }
	}


	/**
     *修改冻结金额
     */
    @Transactional
    public void updateUserUnableCoin(UserBalanceDto balanceDto){
        String sql = "UPDATE t_inesv_user_balance SET enable_coin = ?,unable_coin=?,total_price = ? WHERE user_no = ? and coin_type = ?";
        BigDecimal enble = balanceDto.getEnable_coin();
        BigDecimal unableCoin = balanceDto.getUnable_coin();
        BigDecimal totalPrice = balanceDto.getTotal_price();
        int userNo = balanceDto.getUser_no();
        int coinType = balanceDto.getCoin_type();
        System.out.println("----------------------------------------------------"+unableCoin);
        Object parmas[] = {enble,unableCoin,totalPrice,userNo,coinType};
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

	/**
     *修改冻结金额
     */
    @Transactional
    public void updateEnbleByfirwith(UserBalanceDto balanceDto){
        String sql = "UPDATE t_inesv_user_balance SET  unable_coin=?,total_price = ? WHERE user_no = ? and coin_type = ?";
        BigDecimal enble = balanceDto.getEnable_coin();
        BigDecimal unableCoin = balanceDto.getUnable_coin();
        BigDecimal totalPrice = balanceDto.getTotal_price();
        int userNo = balanceDto.getUser_no();
        int coinType = balanceDto.getCoin_type();
        System.out.println("----------------------------------------------------"+unableCoin);
        Object parmas[] = {unableCoin,totalPrice,userNo,coinType};
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
