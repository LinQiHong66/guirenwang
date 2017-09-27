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

import com.inesv.digiccy.dto.ProvinceAbbreviationDto;

/**
 * 查询省份简写数据
 * @author Administrator
 *
 */
@Component
public class QueryProvinceAbbreviation {
	
	private static Logger log = LoggerFactory.getLogger(QueryProvinceAbbreviation.class);

	@Autowired
	private QueryRunner queryRunner;
	
	public List<String> getAllProvinceAbbreviation(){
		String sql ="select * from t_province_abbreviation";
		List<ProvinceAbbreviationDto> list=null;
		List<String> result=new ArrayList<String>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<ProvinceAbbreviationDto>(ProvinceAbbreviationDto.class));
			if(list==null||list.isEmpty()){
				return result;
			}
			for (ProvinceAbbreviationDto provinceAbbreviationDto : list) {
				result.add(provinceAbbreviationDto.getAbbreviation());
			}
		} catch (SQLException e) {
			log.error("查询帮助中心失败",e);
		}
		return result;
	}

}
