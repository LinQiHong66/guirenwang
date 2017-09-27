package com.inesv.digiccy.persistence.finance;

import com.inesv.digiccy.dto.RmbWithdrawDto;
import com.inesv.digiccy.dto.UserBalanceDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

/**
 * 用户提现记录增删改
 */
@Component
public class RmbWithdrawPersistence {


    @Autowired
    QueryRunner queryRunner;

    /**
     *新增提现记录
     *
     */
    @Transactional
    public void addWithdrawInfo(RmbWithdrawDto rmbWithdrawDto){

        String sql = "INSERT INTO t_inesv_rmb_withdraw(user_no,bank,price,poundage,actual_price,date,state)VALUES(?,?,?,?,?,?,?)";
        
        /*  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        /*Object parmas[] = {rmbWithdrawDto.getUser_no(),rmbWithdrawDto.getBank(),rmbWithdrawDto.getPrice(),rmbWithdrawDto.getPoundage(),
                rmbWithdrawDto.getActual_price(),rmbWithdrawDto.getDate(),rmbWithdrawDto.getState()};
        */
        Object parmas[] = {rmbWithdrawDto.getUser_no(),rmbWithdrawDto.getBank(),rmbWithdrawDto.getPrice(),rmbWithdrawDto.getPoundage(),
                rmbWithdrawDto.getActual_price(),rmbWithdrawDto.getDate(),0};  //状态为未处理
        /*  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * 申请提现--事务控制
     * @throws SQLException 
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void applayToAccount(int user_no, int bank, BigDecimal price, BigDecimal poundage, BigDecimal actual_price) throws Exception{
           //添加提现记录
    	    String sql = "INSERT INTO t_inesv_rmb_withdraw(user_no,bank,price,poundage,actual_price,date,state)VALUES(?,?,?,?,?,?,?)";
            Object  Param[] = {user_no,bank,price,poundage,actual_price,new Date(),0};   //状态为未处理
            queryRunner.update(sql, Param);
            
            //手续费记录 
            String sql2 = "INSERT INTO t_inesv_poundage(user_no,optype,type,money,date) VALUES(?,?,?,?,?)";
            Object parmas2[] = {user_no,3,0,poundage,new Date()};
            queryRunner.update(sql2, parmas2);
            
            //资产--将资产从可用转到冻结
            String sql3 = "SELECT * FROM t_inesv_user_balance WHERE user_no = ? AND coin_type = 0 for update";
            Object parmas3[] = {user_no};
            UserBalanceDto userBalanceDto = queryRunner.query(sql3,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),parmas3);
        	if(userBalanceDto.getEnable_coin().doubleValue() - price.doubleValue() < 0 
        			|| userBalanceDto.getTotal_price().doubleValue() - price.doubleValue() < 0) {
        		int exception= 1/0;	//手动抛出异常
        	}
        	
            String sql4 = "UPDATE t_inesv_user_balance SET enable_coin =enable_coin - ?,unable_coin= unable_coin + ? WHERE user_no = ? and coin_type = 0";
            Object parmas4[] = {price,price,user_no};
            queryRunner.update(sql4, parmas4);
    }
    
    /**
     * 确认人民币提现到账
     * @throws SQLException 
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void confirmToAccount(int recordId,int user,String price) throws Exception{
            String getBalanceSql = "SELECT * FROM t_inesv_user_balance WHERE user_no = ? AND coin_type = 0";
            Object params[] = {user};
        		UserBalanceDto userBalanceDto = queryRunner.query(getBalanceSql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),params);
        	if(userBalanceDto.getUnable_coin().doubleValue() - Double.valueOf(price) < 0 
        			|| userBalanceDto.getTotal_price().doubleValue() - Double.valueOf(price) < 0) {
        		int exception= 1/0;	//手动抛出异常
        	}
        	
        	String updateBalance = "UPDATE t_inesv_user_balance SET unable_coin = unable_coin-?,total_price = total_price-? " +
                    "WHERE user_no = ? and coin_type = 0 ";
            Object balanceParam[] = {price, price, user};
            	queryRunner.update(updateBalance, balanceParam);
        	
        	String updateState = "UPDATE t_inesv_rmb_withdraw SET state = 1 ,actual_price=price-poundage WHERE  id=? ";
            Object stateParam[] = {recordId};
            	queryRunner.update(updateState, stateParam);
        	
    }

}
