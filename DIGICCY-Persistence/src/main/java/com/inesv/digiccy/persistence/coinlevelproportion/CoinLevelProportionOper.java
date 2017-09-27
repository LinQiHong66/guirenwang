package com.inesv.digiccy.persistence.coinlevelproportion;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.persistence.finance.FicRechargePersistence;

@Component
public class CoinLevelProportionOper {
	
	private static Logger logger = LoggerFactory.getLogger(FicRechargePersistence.class);
	
	@Autowired
	QueryRunner queryRunner;
	
	public void insert(Long coin_no,BigDecimal level_one,BigDecimal level_two,BigDecimal level_three){
		String sql = "insert into t_coin_level_proportion(coin_no,level_one,level_two,level_three) values(?,?,?,?)";
		Object params[]={coin_no,level_one,level_two,level_three};
		try {
			queryRunner.update(sql,params);
		} catch (SQLException e) {
			logger.error("新增货币等级分红比例失败");
			e.printStackTrace();
		}
	}
	
	public void updateLevelByCoinNo(BigDecimal level_one,BigDecimal level_two,BigDecimal level_three,Long id){
		String sql = "update t_coin_level_proportion set level_one = ?,level_two = ?,level_three = ? where id = ?";
		Object params[] = {level_one,level_two,level_three,id}; 
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("修改货币等级分红比例失败");
			e.printStackTrace();
		}
	}
}
