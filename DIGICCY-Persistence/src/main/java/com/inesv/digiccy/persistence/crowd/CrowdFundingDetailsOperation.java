package com.inesv.digiccy.persistence.crowd;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.dto.CrowdFundingDetailsDto;
import com.inesv.digiccy.dto.TranDto;
import com.inesv.digiccy.dto.UserBalanceDto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by JimJim on 2016/12/9 0009.
 */
@Component
public class CrowdFundingDetailsOperation {

    @Autowired
    QueryRunner queryRunner;
    
    /*
     * 增加众筹信息
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void insertDetails(CrowdFundingDetailsDto crowdFundingDetailsDto) throws Exception{
        String sql = "INSERT INTO t_crowdfunding_details (user_id,ico_id,ico_user_number,ico_user_sumprice,date) VALUES (?,?,?,?,?)";
        Object params[] = {crowdFundingDetailsDto.getUser_id(),
        		crowdFundingDetailsDto.getIco_id(),
        		crowdFundingDetailsDto.getIco_user_number(),
        		crowdFundingDetailsDto.getIco_user_sumprice(),
        		crowdFundingDetailsDto.getDate()};
        queryRunner.update(sql,params);
    }
    
    /*
     * 参与众筹
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void updateCrowdDetailAndBalance(String icoNo, String userNo, Integer icoNumber, Double icoNumberConvert, String sumPrice, String icoCurrent, 
    		String icoStatus, String total_price, String enable_coin, String icoPriceType) throws Exception{
    	if(Double.valueOf(total_price) < 0 || Double.valueOf(enable_coin) < 0) {
    		int exception= 1/0;	//手动抛出异常
    	}
        String insertDetailSql = "INSERT INTO t_crowdfunding_details (user_id,ico_id,ico_user_number,ico_user_sumprice,date) VALUES (?,?,?,?,?)";
        Object insertDetailParams[] = {userNo,icoNo,icoNumberConvert,sumPrice,new Date()};
        	queryRunner.update(insertDetailSql,insertDetailParams);
        String updateCrowdSql = "UPDATE t_crowdfunding SET ico_current=? , ico_status=? , ico_state = ? WHERE ico_no = ?";
        if(Double.valueOf(icoStatus)==1) {
        	Object updateCrowdParams1[] = { icoCurrent, icoStatus, 1, icoNo };
        		queryRunner.update(updateCrowdSql, updateCrowdParams1);
        }else {
        	Object updateCrowdParams0[] = { icoCurrent, icoStatus, 0, icoNo };
        		queryRunner.update(updateCrowdSql, updateCrowdParams0);
        }
		String updateBalanceSql = "UPDATE t_inesv_user_balance SET enable_coin = ?,total_price = ? WHERE user_no = ? and coin_type = ?";
	    Object updateBalanceParmas[] = {enable_coin,total_price,userNo,icoPriceType};
	            queryRunner.update(updateBalanceSql,updateBalanceParmas);
	    String getBalanceSql = "select * from t_inesv_user_balance where user_no = ? and coin_type = ? for update";
	    Object getBalanceParmas[] = {userNo,"60"};
	    		UserBalanceDto balanceDto= (UserBalanceDto)queryRunner.query(getBalanceSql,new BeanHandler(UserBalanceDto.class),getBalanceParmas);
	    if(balanceDto == null) {
	    	String updateBalanceByCrowdSql = "INSERT INTO t_inesv_user_balance (user_no,coin_type,enable_coin,unable_coin,total_price,date) values (?,?,?,?,?,?)";
    	    Object updateBalanceByCrowdParmas[] = {userNo,"60","0",icoNumberConvert,icoNumberConvert,new Date()};
    	            queryRunner.update(updateBalanceByCrowdSql,updateBalanceByCrowdParmas);
	    }else {
	    	String updateBalanceByCrowdSql = "UPDATE t_inesv_user_balance SET unable_coin = unable_coin + ?,total_price = total_price + ? WHERE user_no = ? and coin_type = ?";
    	    Object updateBalanceByCrowdParmas[] = {icoNumberConvert,icoNumberConvert,userNo,"60"};
    	            queryRunner.update(updateBalanceByCrowdSql,updateBalanceByCrowdParmas);
	    }
    }
    
    /**
     * 修改所属的物流状态信息
     * @param id
     * @param statue
     * @return
     */
    public Boolean update_wl(String id,String statue){
    	
    	try {
    		String sql="update t_crowdfunding_details as tc  SET tc.logistics_status=? where id=?";
    		 Object params[] = {statue, id};
    		 int i=queryRunner.update(sql,params);
    		 if(i==1){
    			 return true;
    		 }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return false;
    }
    
    /**
     * 修改所属的物流状态信息
     * @param id
     * @param statue
     * @return
     */
    public Boolean update_number(String id,String number,String name,String code){
    	
    	try {
    		String sql="update t_crowdfunding_details as tc  SET tc.logistics_company=? ,logistics_number=? , logistics_code=? where id=?";
    		 Object params[] = {name,number,code, id};
    		 int i=queryRunner.update(sql,params);
    		 if(i==1){
    			 return true;
    		 }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return false;
    }
}
