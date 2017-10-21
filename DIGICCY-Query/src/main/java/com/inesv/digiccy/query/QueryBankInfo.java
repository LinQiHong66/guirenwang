package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InesvBankInfo;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Component
public class QueryBankInfo {

	private static Logger logBank = LoggerFactory.getLogger(QueryBankInfo.class);

	@Autowired
	QueryRunner queryRunner;

	/**
	 * 获取集合大小
	 */
	public long getSize(String userOrgCode, String bankName, String bankUserName, String phone, String userName,
			String startDate, String endDate) {
		long size = 0;

		String userWhere = "";
		String bankWhere = "";
		// 添加用户的where条件
		ArrayList<Object> paramArr = new ArrayList<>();
		if (userOrgCode != null && !"".equals(userOrgCode)) {
			userWhere += " and org_code like ?";
			paramArr.add("%" + userOrgCode + "%");
		}
		if (userName != null && !"".equals(userName)) {
			userWhere += " and real_name like ?";
			paramArr.add("%" + userName + "%");
		}
		if (phone != null && !"".equals(phone)) {
			userWhere += " and (phone like ? or username like ?)";
			paramArr.add("%" + phone + "%");
			paramArr.add("%" + phone + "%");
		}
		// 添加bank的where条件
		if (bankName != null && !"".equals(bankName)) {
			bankWhere += " and bank like ?";
			paramArr.add("%" + bankName + "%");
		}
		if (bankUserName != null && !"".equals(bankUserName)) {
			bankWhere += " and name like ?";
			paramArr.add("%" + bankUserName + "%");
		}
		if (startDate != null && endDate != null && !"".equals(startDate) && !"".equals(endDate)) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				Date startD = dateFormat.parse(startDate);
				Date endD = dateFormat.parse(endDate);

				bankWhere += " and date between ? and ?";
				paramArr.add(startD);
				paramArr.add(endD);
			} catch (Exception e) {

			}
		}

		String sql = "select count(*) as count from t_inesv_bankinfo where user_no in (select user_no from t_inesv_user where 1=1 "
				+ userWhere + ") " + bankWhere + " and delstate != 1 order by date desc";
		try {
			size = queryRunner.query(sql, new ColumnListHandler<Long>("count"), paramArr.toArray(new Object[] {}))
					.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	/**
	 * 查询所有银行卡信息
	 * 
	 * @return
	 */
	public List<InesvBankInfo> getAllBankInfo(int curPage, int pageItem, String userOrgCode, String bankName,
			String bankUserName, String phone, String userName, String startDate, String endDate) {

		String userWhere = "";
		String bankWhere = "";
		// 添加用户的where条件
		ArrayList<Object> paramArr = new ArrayList<>();
		if (userOrgCode != null && !"".equals(userOrgCode)) {
			userWhere += " and org_code like ?";
			paramArr.add("%" + userOrgCode + "%");
		}
		if (userName != null && !"".equals(userName)) {
			userWhere += " and real_name like ?";
			paramArr.add("%" + userName + "%");
		}
		if (phone != null && !"".equals(phone)) {
			userWhere += " and (phone like ? or username like ?)";
			paramArr.add("%" + phone + "%");
			paramArr.add("%" + phone + "%");
		}
		// 添加bank的where条件
		if (bankName != null && !"".equals(bankName)) {
			bankWhere += " and bank like ?";
			paramArr.add("%" + bankName + "%");
		}
		if (bankUserName != null && !"".equals(bankUserName)) {
			bankWhere += " and name like ?";
			paramArr.add("%" + bankUserName + "%");
		}
		if (startDate != null && endDate != null && !"".equals(startDate) && !"".equals(endDate)) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				Date startD = dateFormat.parse(startDate);
				Date endD = dateFormat.parse(endDate);

				bankWhere += " and date between ? and ?";
				paramArr.add(startD);
				paramArr.add(endD);
			} catch (Exception e) {

			}
		}

		String sql = "select * from t_inesv_bankinfo where user_no in (select user_no from t_inesv_user where 1=1 "
				+ userWhere + ") " + bankWhere + " and delstate != 1 order by date desc LIMIT ?,?";
		paramArr.add((curPage - 1) * pageItem);
		paramArr.add(pageItem);

		sql = "select u.username as remark_name,u.real_name as atte2,u.org_code as atte1,bank.bank,bank.province as city,bank.branch as branch,bank.name,bank.date,bank.bank_num from ("
				+ sql + ") bank LEFT JOIN t_inesv_user u on bank.user_no=u.user_no";

		List<InesvBankInfo> list = null;
		try {
			list = queryRunner.query(sql, new BeanListHandler<InesvBankInfo>(InesvBankInfo.class),
					paramArr.toArray(new Object[] {}));

		} catch (SQLException e) {
			e.printStackTrace();
			logBank.error("查询银行卡信息失败");
		}
		return list;
	}

	/**
	 * 查询银行卡信息
	 * 
	 * @param userNo
	 * @return
	 */
	public List<InesvBankInfo> getBankInfo(String userNo) {
		String sql = "select * from t_inesv_bankinfo where user_no = ? and  delstate!=1";
		Object params[] = { userNo };
		List<InesvBankInfo> list = null;
		try {
			list = queryRunner.query(sql, new BeanListHandler<InesvBankInfo>(InesvBankInfo.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
			logBank.error("查询银行卡信息失败");
		}
		return list;
	}

	/**
	 * 查询银行卡信息
	 * 
	 * @param userNo
	 * @return
	 */
	public InesvBankInfo getBankInfoById(Long id) {
		String sql = "select * from t_inesv_bankinfo where id = ? and  delstate!=1";
		Object params[] = { id };
		InesvBankInfo inesvBank = null;
		try {
			inesvBank = queryRunner.query(sql, new BeanHandler<InesvBankInfo>(InesvBankInfo.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
			logBank.error("根据id查询银行卡信息失败");
		}
		return inesvBank;
	}
}
