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
import com.inesv.digiccy.dto.PoundatgeDto;

@Component
public class QueryPoundatge {

	@Autowired
	QueryRunner queryRunner;
	
	private static Logger logger =  LoggerFactory.getLogger(QueryPoundatge.class);
	
	public List<PoundatgeDto> queryAll(){
		String sql = "select * from t_inesv_poundage";
		List<PoundatgeDto> list = new ArrayList<PoundatgeDto>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<PoundatgeDto>(PoundatgeDto.class));
		} catch (SQLException e) {
			logger.error("查询手续费列表出错");
			e.printStackTrace();
		}
		return list;
	}
	
}
