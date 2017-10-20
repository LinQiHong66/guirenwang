package com.inesv.digiccy.persistence.coinlevelproportion;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.CoinLevelProportionDto;
import com.inesv.digiccy.persistence.finance.FicRechargePersistence;

@Component
public class CoinLevelProportionOper {
	
	private static Logger logger = LoggerFactory.getLogger(FicRechargePersistence.class);
	
	@Autowired
	QueryRunner queryRunner;
	
	public void insert(CoinLevelProportionDto levelDto){
		String sql = "insert into t_coin_level_proportion(coin_no,level_one,level_two,level_three,level_four,level_five,level_type,state) values(?,?,?,?,?,?,?,?)";
		Object params[]={levelDto.getCoin_no(),levelDto.getLevel_one(),levelDto.getLevel_two(),levelDto.getLevel_three(),levelDto.getLevel_four(),levelDto.getLevel_five(),
				levelDto.getLevel_type(),levelDto.getState()};
		try {
			queryRunner.update(sql,params);
		} catch (SQLException e) {
			logger.error("新增货币等级分润比例失败");
			e.printStackTrace();
		}
	}
	
	public void updateById(CoinLevelProportionDto levelDto){
		String sql = "update t_coin_level_proportion set coin_no = ?, level_one = ?, level_two = ?, level_three = ?, level_four = ?, level_five = ?, level_type = ?, state = ? where id = ?";
		Object params[] = {levelDto.getCoin_no(),levelDto.getLevel_one(),levelDto.getLevel_two(),levelDto.getLevel_three(),levelDto.getLevel_four(),levelDto.getLevel_five(),
				levelDto.getLevel_type(),levelDto.getState(),levelDto.getId()};
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("修改货币等级分润比例失败");
			e.printStackTrace();
		}
	}
	
	public void delete(CoinLevelProportionDto levelDto){
		String sql = "update t_coin_level_proportion set state = 2 where id = ?";
		Object params[] = {levelDto.getId()}; 
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("修改货币等级分润比例失败");
			e.printStackTrace();
		}
	}
}
