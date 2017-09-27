package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.CoinAndCoinProportion;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CoinLevelProportionDto;


@Component
public class QueryCoinLevelProportion {
	
	private static Logger logger = LoggerFactory.getLogger(QueryCoinLevelProportion.class);
	
	@Autowired
	QueryRunner queryRunner;
	
	public List<CoinLevelProportionDto> queryAll(){
		String sql = "select * from t_coin_level_proportion";
		List<CoinLevelProportionDto> list = new ArrayList<CoinLevelProportionDto>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<CoinLevelProportionDto>(CoinLevelProportionDto.class));
		} catch (SQLException e) {
			logger.error("查询项目列表出错");
			e.printStackTrace();
		}
		return list;
	}
	
	public CoinLevelProportionDto queryByCoinNo(Long coin_no){
		String sql = "select * from t_coin_level_proportion where coin_no = ?";
		CoinLevelProportionDto coinLevelProportionDto = new CoinLevelProportionDto();
		try {
			coinLevelProportionDto = queryRunner.query(sql, new BeanHandler<CoinLevelProportionDto>(CoinLevelProportionDto.class),coin_no);
		} catch (SQLException e) {
			logger.error("根据币种编号查询分级比例");
			e.printStackTrace();
		}
		return coinLevelProportionDto;
	}
	//
	public List<CoinAndCoinProportion> queryCoinLevel(){
		String sql = "select l.id,c.coin_no,c.coin_name,l.level_one,l.level_two FROM t_inesv_coin_type c INNER JOIN t_coin_level_proportion l on c.coin_no = l.coin_no where c.coin_no != 0";
		List<CoinAndCoinProportion> coinAndCoinProportion = new ArrayList<CoinAndCoinProportion>();
		try {
			coinAndCoinProportion = queryRunner.query(sql, new BeanListHandler<CoinAndCoinProportion>(CoinAndCoinProportion.class));
		} catch (SQLException e) {
			logger.error("根据币种编号查询分级比例");
			e.printStackTrace();
		}
		return coinAndCoinProportion;
	}
	public CoinDto queryCoinDto(String coinNo){
		CoinDto coinDto = new CoinDto();
		String sql = "select * from t_inesv_coin_type where coin_no = ?";
		Object params[] = {coinNo};
		try {
			coinDto = queryRunner.query(sql, new BeanHandler<CoinDto>(CoinDto.class),params);
		} catch (SQLException e) {
			logger.error("根据币种编号查询信息");
			e.printStackTrace();
		}
		return coinDto;
	}
}
