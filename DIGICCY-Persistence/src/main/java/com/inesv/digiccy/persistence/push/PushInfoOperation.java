package com.inesv.digiccy.persistence.push;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.dto.PushInfoDto;

@Component
public class PushInfoOperation {
	@Autowired
	QueryRunner queryRunner;

	public void updateDriverToken(PushInfoDto dto) throws SQLException {
		String sql = "update t_inesv_pushInfo set driverToken=? where userNo=?";
		Object[] params = new Object[] { dto.getDriverToken(), dto.getUserNo() };
		queryRunner.update(sql, params);
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void setSectionOfPush(PushInfoDto dto) throws Exception {
		String sql = "select * from t_inesv_pushInfo where userNo=?";
		PushInfoDto pushInfo = queryRunner.query(sql, new BeanHandler<PushInfoDto>(PushInfoDto.class), dto.getUserNo());
		String updateSql = "";
		ArrayList<Object> arrParam = new ArrayList<>();
		if (pushInfo == null) {
			String selectUserName = "select username from t_inesv_user where user_no=?";
			List<String> cols = queryRunner.query(selectUserName, new ColumnListHandler<String>("username"),
					dto.getUserNo());
			if (cols != null && cols.size() > 0) {
				updateSql = "insert into t_inesv_pushInfo (userNo, maxPrice, minPrice, isPush,userName) values (?,?,?,?,?)";
				arrParam.add(dto.getUserNo());
				arrParam.add(dto.getMaxPrice());
				arrParam.add(dto.getMinPrice());
				arrParam.add(dto.isPush());
				arrParam.add(dto.getUserName());
			} else {
				throw new Exception("未查询到" + dto.getUserNo() + "改用户");
			}
		} else {
			updateSql = "update t_inesv_pushInfo set maxPrice=?, minPrice=?, isPush=? where userNo=?";

			arrParam.add(dto.getMaxPrice());
			arrParam.add(dto.getMinPrice());
			arrParam.add(dto.isPush());
			arrParam.add(dto.getUserNo());
		}
		queryRunner.update(updateSql, arrParam.toArray(new Object[] {}));
	}
}
