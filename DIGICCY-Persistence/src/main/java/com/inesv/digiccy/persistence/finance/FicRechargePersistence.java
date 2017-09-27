package com.inesv.digiccy.persistence.finance;

import com.inesv.digiccy.dto.FicRechargeDto;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

@Component
public class FicRechargePersistence {

    private static Logger logger = LoggerFactory.getLogger(FicRechargePersistence.class);

    @Autowired
    QueryRunner queryRunner;


    /**
     *处理虚拟币充值--事务处理
     * @throws SQLException 
     *
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void ficRechargeTransac(Integer userNo,Integer coinNo,String fromaddress,BigDecimal bigPrice,Integer state,String txid,BigDecimal nowEnble, BigDecimal nowTotalprice) throws SQLException{
    	//添加虚拟币记录
    	 String sql = "insert into t_inesv_fic_recharge(user_no,coin_no,address,actual_price,give_price,sum_price,state,date,txid)values(?,?,?,?,?,?,?,?,?)";
    	 Object pasram[] = {userNo,coinNo,fromaddress,bigPrice,0,bigPrice,state,new Date(),txid};
    	 
    	 //修改资产
    	 String sql2 = "UPDATE t_inesv_user_balance SET enable_coin = ?,total_price = ? WHERE user_no = ? and coin_type = ?";
    	 Object pasram2[] = {nowEnble , nowTotalprice ,userNo,coinNo};
    	 
    	 if(queryRunner.update(sql2,pasram2)==1)
    	 {
    		  queryRunner.update(sql,pasram);
    	 }
    }
    
    
    /**
     *新增交易记录
     *
     */
    public void addFicFecharge(FicRechargeDto ficRechargeDto){
        String sql = "insert into t_inesv_fic_recharge(user_no,coin_no,address,actual_price,give_price,sum_price,state,date,txid)values(?,?,?,?,?,?,?,?,?)";
        Object pasram[] = {ficRechargeDto.getUser_no(),ficRechargeDto.getCoin_no(),ficRechargeDto.getAddress(),ficRechargeDto.getActual_price(),
                ficRechargeDto.getGive_price(),ficRechargeDto.getSum_price(),ficRechargeDto.getState(),ficRechargeDto.getDate(),ficRechargeDto.getTixid()};
        try {
            queryRunner.update(sql,pasram);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("新增虚拟币充值记录失败");
        }
    }

    /**
     * 确认虚拟币充值到账
     */
    @Transactional
    public void confirmToAccount(int recordId,int user,int coin,BigDecimal price) {
        try {
            String updateState = "UPDATE t_inesv_fic_recharge SET state = 0 WHRER id = ? ";
            Object stateParam[] = {recordId};
            queryRunner.update(updateState,stateParam);
            String updateBalance = "UPDATE t_inesv_user_balance SET enable_coin = enable_coin+?,total_price = total_price+? " +
                    "WHRER user_no = ? and coin_type = ? ";
            Object balanceParam[] = {price,price,user,coin};
            queryRunner.update(updateBalance,balanceParam);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *确认充值修改状态
     */
    @Transactional
    public void updateState(int userNo,int coinNo,int state,String txid){
        String sql = "update t_inesv_fic_recharge set state = ? where user_no = ? and coin_no = ? and txid = ?";
        Object parmas[] = {state,userNo,coinNo,txid};
        try {
            queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
