package com.inesv.digiccy.persistence.userlevel;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.UserLevelDto;
import com.inesv.digiccy.util.ObjectChangeUtil;

@Component
public class UserLevelOper {
	private static Logger logger = LoggerFactory.getLogger(UserLevelOper.class);
	
	@Autowired
	private QueryRunner queryRunner;
	
	public void updateLevel(Boolean status,Long user_id,Integer level){
		String sql = "update t_user_address_level set status = ? where user_id = ? and level = ?";
		Object params[] = {status,user_id,level};
		try {
			queryRunner.update(sql,params);
		} catch (SQLException e) {
			logger.error("修改用户权限等级失败");
			e.printStackTrace();
		}
	}
	
	public void addLevel(UserLevelDto userLevelDto){
		String sql = "insert into t_user_address_level(user_id,level,status) values(?,?.?)";
		Object params[] = new ObjectChangeUtil<UserLevelDto>().objectToArray(userLevelDto);
		try {
			queryRunner.update(sql,params);
		} catch (SQLException e) {
			logger.error("设置用户权限失败");
			e.printStackTrace();
		}
	}
}
