package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.BonusLevelDto;

@Component
public class QueryBonusLevel {

	@Autowired
	QueryRunner queryRunner;
	
	private static Logger logger =  LoggerFactory.getLogger(QueryBonusLevel.class);
	
	public List<BonusLevelDto> queryAll(){
		String sql = "select * from t_inesv_bonus_level";
		List<BonusLevelDto> list = new ArrayList<BonusLevelDto>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<BonusLevelDto>(BonusLevelDto.class));
		} catch (SQLException e) {
			logger.error("查询分红列表出错");
			e.printStackTrace();
		}
		return list;
	}
	
}
