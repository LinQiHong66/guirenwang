package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.UserBasicInfoDto;

@Component
public class QueryUserBasicInfo {
	private static Logger log = LoggerFactory.getLogger(QueryAssessInfo.class);

	@Autowired
	private QueryRunner queryRunner;

	public UserBasicInfoDto getUserBasicInfo(int userNo) {
		String sql = "select user_no as userNo, nationality, job, sex, birthday,userName from t_inesv_user_basicinfo where user_no=?";
		try {
			List<UserBasicInfoDto> dtos = queryRunner.query(sql,
					new BeanListHandler<UserBasicInfoDto>(UserBasicInfoDto.class), new Object[] { userNo });
			if (dtos != null && dtos.size() > 0) {
				return dtos.get(0);
			}
		} catch (SQLException e) {
			log.debug(e.getMessage());
		}
		return null;
	}

}
