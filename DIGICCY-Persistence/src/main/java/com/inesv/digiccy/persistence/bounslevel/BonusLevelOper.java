package com.inesv.digiccy.persistence.bounslevel;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.BonusLevelDto;
import com.inesv.digiccy.util.ObjectChangeUtil;

@Component
public class BonusLevelOper {
	
	private static Logger logger = LoggerFactory.getLogger(BonusLevelOper.class);
	
	@Autowired 
	QueryRunner queryRunner;
	
	public void insert(BonusLevelDto bonusLevelDto){
		String sql = "insert into t_inesv_bonus_level(bonus_source,bonus_coin,bonus_user,bonus_type,bonus) values(?,?,?,?,?)";
		Object params[] = new ObjectChangeUtil<BonusLevelDto>().objectToArray(bonusLevelDto);
		try {
			queryRunner.update(sql,params);
		} catch (SQLException e) {
			logger.error("新增三级分销分红记录失败");
			e.printStackTrace();
		}
	}

}
