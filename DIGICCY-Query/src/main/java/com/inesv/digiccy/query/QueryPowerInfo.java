package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.PowerInfoDto;
import com.inesv.digiccy.dto.ProwerParamDto;

@Component
public class QueryPowerInfo {

	@Autowired
	QueryRunner queryRunner;

	// 获取详情
	public PowerInfoDto getBasicInfo(int powerInfoId) throws SQLException {
		PowerInfoDto dto = new PowerInfoDto();
		String sql1 = "select id,url,info,time,userName from t_inesv_powerinfo where id=?";
		dto = queryRunner
				.query(sql1, new BeanListHandler<PowerInfoDto>(PowerInfoDto.class), new Object[] { powerInfoId })
				.get(0);
		String sql2 = "select paramInfo as paramInfo, paramName as paramName, powerId as powerId, id, paramValue as paramValue from t_inesv_power_params where powerId=?";
		List<ProwerParamDto> params = queryRunner.query(sql2, new BeanListHandler<ProwerParamDto>(ProwerParamDto.class),
				new Object[] { powerInfoId });
		ArrayList<ProwerParamDto> paramsArr = new ArrayList<>();
		paramsArr.addAll(params);
		dto.setParams(paramsArr);
		return dto;
	}

	// 获取权限记录的总数
	public long getSize(String url, String info, String userName, Date startDate, Date endDate) throws SQLException {
		String sql = "select count(*) as count from t_inesv_powerinfo ";
		ArrayList<Object> paramArr = new ArrayList<>();
		if (url != null && !"".equals(url)) {
			sql += sql.contains("where") ? " and url like ?" : " where url like ?";
			paramArr.add("%" + url + "%");
		}
		if (info != null && !"".equals(info)) {
			sql += sql.contains("where") ? " and info like ?" : " where info like ?";
			paramArr.add("%" + info + "%");
		}
		if (userName != null && !"".equals(userName)) {
			sql += sql.contains("where") ? " and userName like ?" : " where userName like ?";
			paramArr.add("%" + userName + "%");
		}
		if (startDate != null && endDate != null) {
			sql += sql.contains("where") ? " and time between ? and ?" : " where time between ? and ?";
			paramArr.add(startDate);
			paramArr.add(endDate);
		}
		List<Long> lengs = queryRunner.query(sql, new ColumnListHandler<Long>("count"),
				paramArr.toArray(new Object[] {}));
		return lengs.get(0);
	}

	// 获取权限访问记录
	public List<PowerInfoDto> getPowerInfo(String url, String info, String userName, Date startDate, Date endDate,
			int curPage, int pageItem, String orderName, String orderType) throws SQLException {
		String sql = "select id,url,info,time,userName from t_inesv_powerinfo ";
		ArrayList<Object> paramArr = new ArrayList<>();
		int startItem = 0;
		startItem = pageItem * (curPage - 1);
		if (url != null && !"".equals(url)) {
			sql += sql.contains("where") ? " and url like ?" : " where url like ?";
			paramArr.add("%" + url + "%");
		}
		if (info != null && !"".equals(info)) {
			sql += sql.contains("where") ? " and info like ?" : " where info like ?";
			paramArr.add("%" + info + "%");
		}
		if (userName != null && !"".equals(userName)) {
			sql += sql.contains("where") ? " and userName like ?" : " where userName like ?";
			paramArr.add("%" + userName + "%");
		}
		if (startDate != null && endDate != null) {
			sql += sql.contains("where") ? " and time between ? and ?" : " where time between ? and ?";
			paramArr.add(startDate);
			paramArr.add(endDate);
		}
		if("id".equals(orderName) || orderName == null || "".equals(orderName)) {
			sql += " order by time desc";
		}else {
			sql += " order by " + orderName + " " + ("up".equals(orderType) ? "desc" : "asc");
		}
		sql += " limit ?,?";
		paramArr.add(startItem);
		paramArr.add(pageItem);
		return queryRunner.query(sql, new BeanListHandler<PowerInfoDto>(PowerInfoDto.class),
				paramArr.toArray(new Object[] {}));
	}
}
