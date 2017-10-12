package com.inesv.digiccy.persistence.reg;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.dto.InesvPhoneDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
@Transactional
@Component
public class RegUserPersistence {

	@Autowired
	QueryRunner queryRunner;

	
	//修改用户登陆密码
	public int modifyPassword(String phone, String password) throws SQLException {
		String sql = "update t_inesv_user set password=? where username=?";
		Object[] params = new Object[] {
				password, phone
		};
		int k = queryRunner.update(sql,params);
		return k;
	}
	
	/**
	 * 新增 用户记录
	 *
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public synchronized long addUser(InesvUserDto inesvUserDto, String recCode, String inviteNum) throws Exception {
		// 新增用户信息表
		String sql = "insert into t_inesv_user(username,password,real_name,phone,state,invite_num,date,phone_state,org_type,org_code,org_parent_code) values(?,?,?,?,?,?,?,?,?,?,?)";
		Map<Long, Map<String, Object>> key = null;
		Object params[] = { inesvUserDto.getUsername(), inesvUserDto.getPassword(), inesvUserDto.getReal_name(),
				inesvUserDto.getPhone(), inesvUserDto.getState(), inesvUserDto.getInvite_num(), inesvUserDto.getDate(),
				1, inesvUserDto.getOrg_type(), inesvUserDto.getOrg_code(), inesvUserDto.getOrg_parent_code() };
		key = queryRunner.insert(sql, new KeyedHandler<Long>(), params);

		String sqlss = "insert into t_inesv_user_role(user_id,role_id) values(?,1)";

		Iterator<Long> keyIt = key.keySet().iterator();
		long user_No = 0;
		if (keyIt.hasNext()) {
			user_No = keyIt.next();
		}
		System.out.println("user_No:" + user_No);
		Object paramsss[] = { user_No };
		queryRunner.update(sqlss, paramsss);

		// 新增用户，初始化用户财务表(添加用户人民币资产信息)
		String insertRmbBalanceSql = "insert into t_inesv_user_balance(user_no,coin_type,enable_coin,unable_coin,total_price,date) values(?,?,0,0,0,?)";
		Object[] insertRmbBalanceParams = { key.keySet().toArray()[0], 0, new Date() };
		queryRunner.update(insertRmbBalanceSql, insertRmbBalanceParams);

		// 查询所有的货币类型
		List<CoinDto> coinNoList = queryRunner.query("select coin_no from t_inesv_coin_type where coin_no!=0",
				new BeanListHandler<CoinDto>(CoinDto.class));

		// 循环添加用户财务信息
		for (CoinDto coinNo : coinNoList) {
			String insertBalanceSql = "insert into t_inesv_user_balance(user_no,coin_type,enable_coin,unable_coin,total_price,date) values(?,?,0,0,0,?)";
			Object[] insertBalanceParams = { key.keySet().toArray()[0], coinNo.getCoin_no(), new Date() };
			queryRunner.update(insertBalanceSql, insertBalanceParams);
		}

		// 修改用户表ID
		String userSql = "update t_inesv_user set id=user_no where username= ? ";
		Object userParams[] = { inesvUserDto.getPhone() };
		queryRunner.update(userSql, userParams);

		// 新增邀请码序列表
		if (recCode != null && !recCode.equals("")) {
			String sqlSequence = "insert into t_sequence(seq_name,current_val,step) values(?,?,?)";// 新增对应邀请码的序列
			Object sequenceParams[] = { recCode, 0, 1 };
			queryRunner.update(sqlSequence, sequenceParams);
		}

		if (!"".equals(inviteNum) && inviteNum != null) {
			String userInfoSql1 = "select * from t_inesv_user where invite_num = ?";
			Object parmas1[] = { inviteNum };
			UserInfoDto userInfoDto1 = (UserInfoDto) queryRunner.query(userInfoSql1, new BeanHandler(UserInfoDto.class),
					parmas1);

			if (userInfoDto1 != null) {
				int userNo = userInfoDto1.getUser_no(); // 获取推荐码用户的编号
				String userInfoSql2 = "select * from t_inesv_user where phone = ?";
				Object parmas2[] = { inesvUserDto.getPhone() };
				UserInfoDto userInfoDto2 = (UserInfoDto) queryRunner.query(userInfoSql2,
						new BeanHandler(UserInfoDto.class), parmas2);
				int ReguserNo = userInfoDto2.getUser_no();// 获取注册用户编号

				String sql1 = "insert into t_inesv_rec(user_no,rec_user,rec_type,auth,date)VALUES(?,?,?,?,?)";
				String sql2 = "insert into t_user_relations (user_no, relations_no) values (?,?)";
				Object recParmas1[] = { userNo, ReguserNo, 1, 0, new Date() };
				Object recParmas2[] = { ReguserNo, userNo };
				queryRunner.update(sql1, recParmas1);
				queryRunner.update(sql2, recParmas2);
			}
		}
		return user_No;
	}
	/*
	 * @Transactional(rollbackFor={Exception.class, RuntimeException.class}) public
	 * void addUser(InesvUserDto inesvUserDto) throws Exception { String sql =
	 * "insert into t_inesv_user(username,password,region,real_name,certificate_num,deal_pwd,mail,phone,state,invite_num,date,phone_state,org_type,org_code,org_parent_code) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ; Map<Long, Map<String, Object>> key = null; Object params[] =
	 * {inesvUserDto.getUsername(),inesvUserDto.getPassword(),inesvUserDto.getRegion
	 * (),
	 * inesvUserDto.getReal_name(),inesvUserDto.getCertificate_num(),inesvUserDto.
	 * getDeal_pwd(),inesvUserDto.getMail(),inesvUserDto.getPhone(),inesvUserDto.
	 * getState(),
	 * inesvUserDto.getInvite_num(),inesvUserDto.getDate(),1,inesvUserDto.
	 * getOrg_type(),inesvUserDto.getOrg_code(),inesvUserDto.getOrg_parent_code()};
	 * SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd"); key =
	 * queryRunner.insert(sql,new KeyedHandler<Long>(),params);
	 * 
	 * String sqlss = "insert into t_inesv_user_role(user_id,role_id) values(?,1)";
	 * Object paramsss[] = {key.keySet().toArray()[0]}; queryRunner.update(sqlss,
	 * paramsss); //新增用户，初始化用户财务表(添加用户人民币资产信息) String
	 * insertRmbBalanceSql="insert into t_inesv_user_balance(user_no,coin_type,enable_coin,unable_coin,total_price,date) values(?,?,0,0,0,now())"
	 * ; Object[] insertRmbBalanceParams={key.keySet().toArray()[0],0};
	 * queryRunner.update(insertRmbBalanceSql, insertRmbBalanceParams); //查询所有的货币类型
	 * List<CoinDto> coinNoList=queryRunner.
	 * query("select coin_no from t_inesv_coin_type where coin_no!=0", new
	 * BeanListHandler<CoinDto>(CoinDto.class)); //循环添加用户财务信息 for (CoinDto coinNo :
	 * coinNoList) { String
	 * insertBalanceSql="insert into t_inesv_user_balance(user_no,coin_type,enable_coin,unable_coin,total_price,date) values(?,?,0,0,0,now())"
	 * ; Object[]
	 * insertBalanceParams={key.keySet().toArray()[0],coinNo.getCoin_no()};
	 * queryRunner.update(insertBalanceSql, insertBalanceParams); } }
	 */

	/**
	 * 新增 发送短信记录
	 *
	 * @return
	 */
	public void addPhone(InesvPhoneDto inesvPhoneDto) throws Exception {
		String sql = "insert into t_inesv_phone(user_no,phone,state,code) values(?,?,?,?)";
		Object params[] = { inesvPhoneDto.getUser_no(), inesvPhoneDto.getPhone(), inesvPhoneDto.getState(),
				inesvPhoneDto.getCode() };
		queryRunner.update(sql, params);

	}

	/**
	 * 忘记密码
	 * 
	 * @return
	 */
	public void forgetPwd(InesvUserDto inesvUserDto) throws Exception {
		String sql = "UPDATE t_inesv_user SET password = ? WHERE username = ? and phone = ? ";
		Object params[] = { inesvUserDto.getPassword(), inesvUserDto.getUsername(), inesvUserDto.getPhone() };
		queryRunner.update(sql, params);
	}

	/**
	 * 更新id
	 */
	public void updateId(String phone) {
		String sql = "update t_inesv_user set id=user_no where username= ? ";
		Object params[] = { phone };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 经济人手动修改父级关系
	 */
	public void updateParentCode(InesvUserDto inesvUserDto) throws Exception {
		String sql = "";
		Object params[] = new String[] {};
		if (!StringUtils.isBlank(inesvUserDto.getInvite_num()) && !StringUtils.isBlank(inesvUserDto.getOrg_code())) {
			sql = "update t_inesv_user set invite_num = ? , org_code = ? , org_parent_code = ? where user_no = ? ";
			params = new String[] { inesvUserDto.getInvite_num(), inesvUserDto.getOrg_code(),
					inesvUserDto.getOrg_parent_code(), inesvUserDto.getUser_no() + "" };
		}
		if (!StringUtils.isBlank(inesvUserDto.getInvite_num()) && StringUtils.isBlank(inesvUserDto.getOrg_code())) {
			sql = "update t_inesv_user set invite_num = ? , org_parent_code = ? where user_no = ? ";
			params = new String[] { inesvUserDto.getInvite_num(), inesvUserDto.getOrg_parent_code(),
					inesvUserDto.getUser_no() + "" };
		}
		if (StringUtils.isBlank(inesvUserDto.getInvite_num()) && !StringUtils.isBlank(inesvUserDto.getOrg_code())) {
			sql = "update t_inesv_user set org_code = ? , org_parent_code = ? where user_no = ? ";
			params = new String[] { inesvUserDto.getOrg_code(), inesvUserDto.getOrg_parent_code(),
					inesvUserDto.getUser_no() + "" };
		}
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改 委托管理记录 状态
	 *
	 * @return
	 */

	public void updateEntrustState(EntrustDto entrustModel) throws Exception {
		String updateEntrust = "UPDATE t_inesv_entrust SET state = ? WHERE id = ?";
		Object params[] = { entrustModel.getState(), entrustModel.getId() };
		queryRunner.update(updateEntrust, params);
	}

	public void updateEntrustState2(long id) throws Exception {
		String updateEntrust = "UPDATE t_inesv_entrust SET state = ? WHERE id = ?";
		Object params[] = { 2, id };
		queryRunner.update(updateEntrust, params);
	}

	public void updateBalance(Integer user_no, Integer coin_type, Double sum) throws Exception {
		String sql = "UPDATE t_inesv_user_balance SET unable_coin=unable_coin-?,enable_coin=enable_coin+?"
				+ " WHERE user_no=? AND coin_type = ?";
		Object params[] = { sum, sum, user_no, coin_type };
		queryRunner.update(sql, params);
	}

	public void updateBalance(EntrustDto entrustDto) throws Exception {
		String sql = null;
		if (entrustDto.getEntrust_type() == 0) {
			sql = "select * from t_inesv_user_balance where user_no = ? and coin_type = 0";
			Object params[] = { entrustDto.getUser_no() };
			UserBalanceDto userBalanceDto = queryRunner.query(sql,
					new BeanHandler<UserBalanceDto>(UserBalanceDto.class), params);
			sql = "UPDATE t_inesv_user_balance SET unable_coin=?,enable_coin=?" + " WHERE user_no=? AND coin_type = 0";
			if (userBalanceDto.getUnable_coin().doubleValue()
					- (entrustDto.getEntrust_num().doubleValue() - entrustDto.getDeal_num().doubleValue())
							* entrustDto.getEntrust_price().doubleValue() < 0) {
				int exception = 1 / 0;
			}
			Object params1[] = {
					userBalanceDto.getUnable_coin().doubleValue()
							- (entrustDto.getEntrust_num().doubleValue() - entrustDto.getDeal_num().doubleValue())
									* entrustDto.getEntrust_price().doubleValue(),
					userBalanceDto.getEnable_coin().doubleValue() + (entrustDto.getEntrust_num().doubleValue()
							* entrustDto.getEntrust_price().doubleValue()
							- entrustDto.getDeal_num().doubleValue() * entrustDto.getEntrust_price().doubleValue()),
					entrustDto.getUser_no() };
			queryRunner.update(sql, params1);
		} else if (entrustDto.getEntrust_type() == 1) {
			sql = "select * from t_inesv_user_balance where user_no = ? and coin_type = ?";
			Object params[] = { entrustDto.getUser_no(), entrustDto.getEntrust_coin() };
			UserBalanceDto userBalanceDto = queryRunner.query(sql,
					new BeanHandler<UserBalanceDto>(UserBalanceDto.class), params);
			if ((userBalanceDto.getUnable_coin().doubleValue()
					- (entrustDto.getEntrust_num().doubleValue() - entrustDto.getDeal_num().doubleValue())) < 0) {
				int exception = 1 / 0;
			}
			sql = "UPDATE t_inesv_user_balance SET unable_coin=?,enable_coin=?" + " WHERE user_no=? AND coin_type = ?";
			Object params2[] = {
					userBalanceDto.getUnable_coin().doubleValue()
							- (entrustDto.getEntrust_num().doubleValue() - entrustDto.getDeal_num().doubleValue()),
					userBalanceDto.getEnable_coin().doubleValue()
							+ (entrustDto.getEntrust_num().doubleValue() - entrustDto.getDeal_num().doubleValue()),
					entrustDto.getUser_no(), entrustDto.getEntrust_coin() };
			queryRunner.update(sql, params2);
		}
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void updateBalanceEntrust(EntrustDto entrustDto) throws Exception {
		// 修改委托状态
		String updateEntrustSql = "UPDATE t_inesv_entrust SET state = 2 WHERE id = ?";
		Object updateEntrustParams[] = { entrustDto.getId() };
		queryRunner.update(updateEntrustSql, updateEntrustParams);
		// 回滚用户资产
		String sql = null;
		if (entrustDto.getEntrust_type() == 0) {
			sql = "select * from t_inesv_user_balance where user_no = ? and coin_type = 0 for update";
			Object params[] = { entrustDto.getUser_no() };
			UserBalanceDto userBalanceDto = queryRunner.query(sql,
					new BeanHandler<UserBalanceDto>(UserBalanceDto.class), params);
			if (userBalanceDto.getUnable_coin().doubleValue()
					- (entrustDto.getEntrust_num().doubleValue() - entrustDto.getDeal_num().doubleValue())
							* entrustDto.getEntrust_price().doubleValue() < 0) {
				int exception = 1 / 0;
			}
			sql = "UPDATE t_inesv_user_balance SET unable_coin=?,enable_coin=?" + " WHERE user_no=? AND coin_type = 0";
			Object params1[] = {
					userBalanceDto.getUnable_coin().doubleValue()
							- (entrustDto.getEntrust_num().doubleValue() - entrustDto.getDeal_num().doubleValue())
									* entrustDto.getEntrust_price().doubleValue(),
					userBalanceDto.getEnable_coin().doubleValue() + (entrustDto.getEntrust_num().doubleValue()
							* entrustDto.getEntrust_price().doubleValue()
							- entrustDto.getDeal_num().doubleValue() * entrustDto.getEntrust_price().doubleValue()),
					entrustDto.getUser_no() };
			queryRunner.update(sql, params1);
		} else if (entrustDto.getEntrust_type() == 1) {
			sql = "select * from t_inesv_user_balance where user_no = ? and coin_type = ? for update";
			Object params[] = { entrustDto.getUser_no(), entrustDto.getEntrust_coin() };
			UserBalanceDto userBalanceDto = queryRunner.query(sql,
					new BeanHandler<UserBalanceDto>(UserBalanceDto.class), params);
			if ((userBalanceDto.getUnable_coin().doubleValue()
					- (entrustDto.getEntrust_num().doubleValue() - entrustDto.getDeal_num().doubleValue())) < 0) {
				int exception = 1 / 0;
			}
			sql = "UPDATE t_inesv_user_balance SET unable_coin=?,enable_coin=?" + " WHERE user_no=? AND coin_type = ?";
			Object params2[] = {
					userBalanceDto.getUnable_coin().doubleValue()
							- (entrustDto.getEntrust_num().doubleValue() - entrustDto.getDeal_num().doubleValue()),
					userBalanceDto.getEnable_coin().doubleValue()
							+ (entrustDto.getEntrust_num().doubleValue() - entrustDto.getDeal_num().doubleValue()),
					entrustDto.getUser_no(), entrustDto.getEntrust_coin() };
			queryRunner.update(sql, params2);
		}
	}

	public void updateEntrustStateByAttr1(long attr1) throws Exception {
		String updateEntrust = "UPDATE t_inesv_entrust SET state = ? WHERE attr1 = ?";
		Object params[] = { 2, attr1 };
		queryRunner.update(updateEntrust, params);
	}

}
