package com.inesv.digiccy.persistence.excel;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.StaticParamsDto;
import com.inesv.digiccy.util.excel.contentutils.ExcelBatch;

@Component
public class BatchAddBalance implements ExcelBatch {

	@Autowired
	QueryRunner queryRunner;

	/**
	 * 
	 * @param phone
	 *            手机号
	 * @param money
	 *            添加金额
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public synchronized boolean addBalance(String phone, int money) throws Exception {
		int userNo = getUserNo(phone);
		String sql = "update t_inesv_user_balance set unable_coin=unable_coin+" + (money / 1000) * 333
				+ " where coin_type=60 and user_no = " + userNo;
		String sql1 = "update t_inesv_user_balance set total_price=unable_coin+enable_coin where coin_type=60 and user_no = "
				+ userNo;
		int a = queryRunner.update(sql);
		int b = queryRunner.update(sql1);
		int c = 0;

		int forCount = 0;
		String ico_id = "";
		int ico_user_number = 0;
		int ico_user_sumprice = 0;

		if ((money / 1000) % 6 == 0) {
			// 六瓶
			forCount = money / 6000;
			ico_id = "201708312017587566";
			ico_user_number = 1998;
			ico_user_sumprice = 6000;
		} else if ((money / 1000) % 300 == 0) {
			// 三百瓶
			forCount = money / (1000 * 300);
			ico_id = "201708130000000001";
			ico_user_number = 100000;
			ico_user_sumprice = 300000;
		} else {
			forCount = money / 1000;
			ico_id = "201708130000000002";
			ico_user_number = 333;
			ico_user_sumprice = 1000;
		}

		for (int k = 0; k < forCount; k++) {
			String sql2 = "insert into t_crowdfunding_details (user_id,ico_id,ico_user_number,ico_user_sumprice,date,attr1, attr2) VALUES ("
					+ userNo + ", " + ico_id + ", " + ico_user_number + "," + ico_user_sumprice + ", now(),\"批量入金\", \"1\")";
			c += queryRunner.update(sql2);
		}
		if (a >= 1 && b >= 1 && c >= forCount) {
			return true;
		}
		return false;
	}

	private int getUserNo(String phone) throws SQLException {
		int userNo = 0;
		List<Integer> nos = queryRunner.query("select user_no as userNo from t_inesv_user where phone='" + phone + "'",
				new ColumnListHandler<Integer>("userNo"));
		if (nos != null && nos.size() > 0) {
			userNo = Integer.parseInt("" + nos.get(0));
		}
		return userNo;
	}

	@Override
	public boolean isReg(String phone) throws SQLException {
		List<InesvUserDto> dtos = queryRunner.query("select * from t_inesv_user where username=" + phone,
				new BeanListHandler<>(InesvUserDto.class));
		if (dtos != null && dtos.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public StaticParamsDto getParam(String key) throws SQLException {
		String sql = "select * from t_inesv_static_param where param='" + key + "'";
		List<StaticParamsDto> dtos = queryRunner.query(sql,
				new BeanListHandler<StaticParamsDto>(StaticParamsDto.class));
		if (dtos != null && dtos.size() > 0) {
			return dtos.get(0);
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void updateBatchGload(int money) throws Exception {
		StaticParamsDto dto = getParam("batchgold");
		if (dto != null) {
			BigDecimal total = dto.getValue();
			total = total.subtract(new BigDecimal(money));
			String sql = "update t_inesv_static_param set value=" + total + " where param='batchgold'";
			queryRunner.update(sql);
		}
	}

	@Override
	public InesvUserDto getCode(String phone) throws Exception {
		String sql = "select org_parent_code ,org_code from t_inesv_user where username='" + phone + "'";
		List<InesvUserDto> codes = queryRunner.query(sql, new BeanListHandler<InesvUserDto>(InesvUserDto.class));
		if (codes != null && codes.size() > 0) {
			return codes.get(0);
		}
		return null;
	}
}
