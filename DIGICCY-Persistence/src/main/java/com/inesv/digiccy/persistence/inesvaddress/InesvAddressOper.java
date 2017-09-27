package com.inesv.digiccy.persistence.inesvaddress;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InesvAddressOper {

	private static Logger logger = LoggerFactory.getLogger(InesvAddressOper.class);
	
	@Autowired
	QueryRunner queryRunner;
	
	/**
	 * 根据Id修改接口权限等级
	 * */
	public void updateLlevel(int address_level,Long address_id){
		String sql = "update t_inesv_address set address_level = ? where address_id = ?";
		Object params[] = {address_level,address_id};
		try {
			queryRunner.update(sql,params);
		} catch (SQLException e) {
			logger.error("修改权限等级失败");
			e.printStackTrace();
		}
	}

}
