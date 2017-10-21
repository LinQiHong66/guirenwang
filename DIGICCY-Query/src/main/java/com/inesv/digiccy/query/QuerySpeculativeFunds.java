package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.Property;
import com.inesv.digiccy.dto.SpeculativeFundsDto;

@Component
public class QuerySpeculativeFunds {

	private static Logger logger = LoggerFactory.getLogger(QuerySpeculativeFunds.class);

	@Autowired
	QueryRunner queryRunner;

	/**
	 * 获取所有异常用户
	 * 
	 * @paramuserAuthoritys
	 * @return
	 * @throws SQLException
	 */
	public List<SpeculativeFundsDto> getAllSpeculativeFunds() {
		// String querySql = "select * from t_inesv_speculativefunds ; ";
		List<SpeculativeFundsDto> users = new ArrayList<>();
		try {
			String querySql = "select * from t_inesv_speculativefunds ";
			users = queryRunner.query(querySql, new BeanListHandler<SpeculativeFundsDto>(SpeculativeFundsDto.class));
			// sers = queryRunner.query(querySql, new
			// BeanListHandler<>(SpeculativeFundsDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("获取所有资金异常账户失敗！");
		}
		return users;
	}

	public void checkAllSpeculativeFunds() {
		List<SpeculativeFundsDto> speculativeFundsDtos = new ArrayList<>();
		List<InesvUserDto> users = null;
		String querySql = "SELECT * FROM t_inesv_user where user_no=2162";
		String insertSql = "INSERT INTO t_inesv_speculativefunds (inProperty,outProperty,totalProperty,user_no) values(?,?,?,?);";
		try {
			users = queryRunner.query(querySql, new BeanListHandler<InesvUserDto>(InesvUserDto.class));
			for (InesvUserDto inesvUserDto : users) {
				double in = getPropertyById(inesvUserDto.getUser_no(), 0);
				double out = getPropertyById(inesvUserDto.getUser_no(), 1);
				SpeculativeFundsDto speculativeFundsDto = new SpeculativeFundsDto(inesvUserDto.getUser_no(), in, out,
						in - out, inesvUserDto.getUser_no());
				speculativeFundsDtos.add(speculativeFundsDto);
				Object[] params = { in, out, in - out, inesvUserDto.getUser_no() };
				queryRunner.update(insertSql, params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("获取所有资金异常账户！");
		}
	}

	/**
	 * 获取用户出入金记录
	 * 
	 * @param userNo
	 * @param dealType
	 * @return
	 */
	public double getPropertyById(int userNo, int dealType) {
		String querySql = null;
		if (dealType == 0) {// 入金
			querySql = "select (sum(t1.actual_price) +sum(t2.deal_price)) as  property from t_inesv_rmb_recharge t1,t_inesv_deal_detail t2 where t2.deal_type=? and t1.user_no=? and t2.user_no=? ;";
		} else if (dealType == 1) {// 出金
			querySql = "select (sum(t1.actual_price) +sum(t2.deal_price)) as  property from t_inesv_rmb_withdraw t1,t_inesv_deal_detail t2 where t2.deal_type=? and t1.user_no=? and t2.user_no=? ;";
		} else {

		}
		Property property = null;
		Object params[] = { dealType, userNo, userNo };
		try {
			property = queryRunner.query(querySql, new BeanHandler<Property>(Property.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return property.getProperty();
	}

	public SpeculativeFundsDto getSpeculativeFundsById(int id) {
		String querySql = "SELECT * FROM t_inesv_speculativefunds where id=?";
		SpeculativeFundsDto speculativeFundsDto = null;
		Object param[] = { id };
		try {
			speculativeFundsDto = queryRunner.query(querySql,
					new BeanHandler<SpeculativeFundsDto>(SpeculativeFundsDto.class), param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取" + id + "用户资金异常账户！");
		}
		return speculativeFundsDto;
	}
}
