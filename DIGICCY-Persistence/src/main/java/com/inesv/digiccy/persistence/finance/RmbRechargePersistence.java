package com.inesv.digiccy.persistence.finance;

import com.inesv.digiccy.dto.RmbRechargeDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
@Component
public class RmbRechargePersistence {

	@Autowired
	QueryRunner queryRunner;

	/** 人民币充值信息 */
	public void addRecharge(RmbRechargeDto rmbRechargeDto) {
		String sql = "insert into t_inesv_rmb_recharge(user_no,recharge_type,recharge_price,recharge_order,actual_price,state,date) values(?,?,?,?,?,?,?)";
		Object parmas[] = { rmbRechargeDto.getUser_no(), rmbRechargeDto.getRecharge_type(),
				rmbRechargeDto.getRecharge_price(), rmbRechargeDto.getRecharge_order(),
				rmbRechargeDto.getActual_price(), rmbRechargeDto.getState(), rmbRechargeDto.getDate() };
		try {
			queryRunner.update(sql, parmas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 修改充值状态 */
	public void updateState(RmbRechargeDto rmbRechargeDto) {
		String sql = "update t_inesv_rmb_recharge set state = ?,actual_price = ? where recharge_order = ?";
		Object parmars[] = { rmbRechargeDto.getState(), rmbRechargeDto.getActual_price(),
				rmbRechargeDto.getRecharge_order() };
		try {
			queryRunner.update(sql, parmars);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 确认充值到账
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public synchronized void confirmToOrder(String order,int state) throws Exception {

		RmbRechargeDto rmbselect = new RmbRechargeDto();
		String sqlselect = "SELECT * FROM t_inesv_rmb_recharge WHERE recharge_order = ? ";
		Object parmas1[] = { order };
		rmbselect = (RmbRechargeDto) queryRunner.query(sqlselect, new BeanHandler(RmbRechargeDto.class), parmas1);
		
		if (rmbselect != null && rmbselect.getState() == 0) {
			String sql = "SELECT * FROM t_inesv_rmb_recharge WHERE recharge_order = ? ";
			Object parmas[] = { order };
			RmbRechargeDto rmbOrder = (RmbRechargeDto) queryRunner.query(sql, new BeanHandler(RmbRechargeDto.class),
					parmas);
			String updateEntrust = "UPDATE t_inesv_rmb_recharge SET state = "+state+",actual_price = ? WHERE recharge_order = ? ";
			Object params[] = { rmbOrder.getRecharge_price(), order };
			queryRunner.update(updateEntrust, params);
			String updateBalance = "UPDATE t_inesv_user_balance SET enable_coin = enable_coin+?,total_price = total_price+? "
					+ "WHERE user_no = ? and coin_type = 0 ";
			Object balanceParam[] = { rmbOrder.getRecharge_price(), rmbOrder.getRecharge_price(),
					rmbOrder.getUser_no() };
			queryRunner.update(updateBalance, balanceParam);
		}
	}

}
