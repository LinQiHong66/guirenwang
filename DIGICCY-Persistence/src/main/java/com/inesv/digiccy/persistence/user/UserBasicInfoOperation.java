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
	//添加基本信息
	public void addBasicInfo(UserBasicInfoDto dto) throws SQLException {
		String sql = "insert into t_inesv_user_basicinfo (user_no, nationality, job, sex, birthday, userName, province, districts, cities, addressInfo) values (?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {
				dto.getUserNo(),dto.getNationality(),dto.getJob(),dto.getSex(),dto.getBirthday(),dto.getUserName(),dto.getProvince(),dto.getDistricts(),dto.getCities(),dto.getAddressInfo()
		};
		System.out.println(sql);
		queryRunner.update(sql, params);
	}
	//修改真实姓名
	public void updateRealName(UserBasicInfoDto dto) throws SQLException {
		String sql = "update t_inesv_user_basicinfo set userName=? where user_no=?";
		Object[] params = {
				dto.getUserName(),dto.getUserNo()
		};
		queryRunner.update(sql, params);
	}
}
