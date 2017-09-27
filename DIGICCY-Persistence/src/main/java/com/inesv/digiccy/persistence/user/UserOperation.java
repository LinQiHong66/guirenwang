package com.inesv.digiccy.persistence.user;

import com.inesv.digiccy.dto.LoginLogDto;
import com.inesv.digiccy.util.ObjectChangeUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

import static com.inesv.digiccy.util.TableName.T_INESV_LOGIN_LOG;

/**
 * Created by JimJim on 2016/12/9 0009.
 */
@Component
public class UserOperation {

	@Autowired
	QueryRunner queryRunner;

	/**
	 * 测试 修改用户资料
	 */
	public void updateUsers1(long id, String username, int user_no, String password, String real_name, String mail,
			String phone, String certificate_num, String deal_pwd, String alipay, int state, String operation) {
		String sql = "UPDATE t_inesv_user SET real_name = ? WHERE user_no = ?";
		Object params[] = { real_name, user_no };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试 增加用户资料
	 */
	public void addUsers(long id, String username, int user_no, String password, String real_name, String mail,
			String phone, String certificate_num, String deal_pwd, String alipay, int state, String operation) {
		String sql = "INSERT INTO t_inesv_user (id,username,real_name,mail,phone,certificate_num,alipay,deal_pwdstate)"
				+ " VALUES (?,?,?,?,?,?,?,0) ";
		Object params[] = { id, username, real_name, mail, phone, certificate_num, alipay };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试 修改用户资料
	 */
	public void updateUsers(long id, String username, int user_no, String password, String real_name, String mail,
			String phone, String certificate_num, String deal_pwd, String alipay, int state, String operation) {
		String sql = "UPDATE t_inesv_user SET username = ?,real_name = ?,mail = ?,phone = ?,"
				+ "certificate_num = ?,alipay = ? WHERE user_no = ?";
		Object params[] = { username, real_name, mail, phone, certificate_num, alipay, user_no };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试 删除用户资料
	 */
	public void deleteUsers(int user_no, String operation) {
		String sql = "DELETE FROM t_inesv_user WHERE user_no=?";
		Object params[] = { user_no };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改用户资料
	 */
	public void updateUserInfo(String username, int user_no, String real_name, String mail, String phone,
			String certificate_num, String alipay) {
		String sql = "UPDATE t_inesv_user SET username = ?,real_name = ?,mail = ?,phone = ?,"
				+ "certificate_num = ?,alipay = ? WHERE user_no = ?";
		if (username != null && phone == null) {
			phone = username;
		}
		if (phone != null && username == null) {
			username = phone;
		}
		Object params[] = { username, real_name, mail, phone, certificate_num, alipay, user_no };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改状态
	 */
	public void updateUserState(int user_no, int state) {
		String sql = "UPDATE t_inesv_user SET state = ? WHERE user_no = ?";
		Object params[] = { state, user_no };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重置密码
	 */
	public void updateUserPass(int user_no, String password, String deal_pwd) {
		String sql = "UPDATE t_inesv_user SET password = ?,deal_pwd = ? WHERE user_no = ?";
		Object params[] = { password, deal_pwd, user_no };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增登录ip地址
	 */
	public void addIpAddRess(LoginLogDto loginLogDto) {
		String sql = new ObjectChangeUtil<LoginLogDto>().objectToSql(loginLogDto, T_INESV_LOGIN_LOG);
		Object params[] = new ObjectChangeUtil<LoginLogDto>().objectToArray(loginLogDto);
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
