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
		String sql = "select * from t_coin_level_proportion where state != 2";
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
		String sql = "select * from t_coin_level_proportion where coin_no = ? and state != 2";
		CoinLevelProportionDto coinLevelProportionDto = new CoinLevelProportionDto();
		try {
			coinLevelProportionDto = queryRunner.query(sql, new BeanHandler<CoinLevelProportionDto>(CoinLevelProportionDto.class),coin_no);
		} catch (SQLException e) {
			logger.error("根据币种编号查询分级比例");
			e.printStackTrace();
		}
		return coinLevelProportionDto;
	}
	
	public CoinLevelProportionDto queryById(Long id){
		String sql = "select * from t_coin_level_proportion where id = ?";
		CoinLevelProportionDto coinLevelProportionDto = new CoinLevelProportionDto();
		try {
			coinLevelProportionDto = queryRunner.query(sql, new BeanHandler<CoinLevelProportionDto>(CoinLevelProportionDto.class),id);
		} catch (SQLException e) {
			logger.error("根据币种编号查询分级比例");
			e.printStackTrace();
		}
		return coinLevelProportionDto;
	}

	public List<CoinLevelProportionDto> queryCoinLevel(){
		String sql = "SELECT t1.id,t2.coin_name AS attr1,t1.level_one,t1.level_two,t1.level_three,t1.level_four,t1.level_five,t3.coin_name AS attr2,t1.state" + 
				" FROM t_coin_level_proportion t1 INNER JOIN t_inesv_coin_type t2 ON t1.coin_no = t2.coin_no  " + 
				" INNER JOIN t_inesv_coin_type t3 ON t1.level_type = t3.coin_no  WHERE t1.coin_no != 0 AND t1.state != 2";
		List<CoinLevelProportionDto> dtoList = new ArrayList<CoinLevelProportionDto>();
		try {
			dtoList = queryRunner.query(sql, new BeanListHandler<CoinLevelProportionDto>(CoinLevelProportionDto.class));
		} catch (SQLException e) {
			logger.error("根据币种编号查询分级比例");
			e.printStackTrace();
		}
		return dtoList;
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
