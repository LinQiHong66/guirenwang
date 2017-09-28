package com.inesv.digiccy.persistence.user;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.UserBasicInfoDto;

@Component
public class UserBasicInfoOperation {
	@Autowired
	QueryRunner queryRunner;
	
	public void addBasicInfo(UserBasicInfoDto dto) throws SQLException {
		String sql = "insert into t_inesv_user_basicinfo (user_no, nationality, job, sex, birthday, userName) values (?,?,?,?,?,?)";
		Object[] params = {
				dto.getUserNo(),dto.getNationality(),dto.getJob(),dto.getSex(),dto.getBirthday(),dto.getUserName()
		};
		queryRunner.update(sql, params);
	}
}
