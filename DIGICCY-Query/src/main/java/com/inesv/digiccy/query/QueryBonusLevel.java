package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.BonusLevelDto;

@Component
public class QueryBonusLevel {

	@Autowired
	QueryRunner queryRunner;

	private static Logger logger = LoggerFactory.getLogger(QueryBonusLevel.class);

	public long getSize(String userName, String userPhone, String userCode, String startDate, String endDate) {
		long size = 0;
		ArrayList<Object> paramArr = new ArrayList<>();
		String userWhere = "";
		String bonusWhere = "";
		// 用户姓名
		if (userName != null && !"".equals(userName)) {
			userWhere += " and username like ?";
			paramArr.add("%" + userName + "%");
		}
		// 用户电话查询
		if (userPhone != null && !"".equals(userPhone)) {
			userWhere += " and (phone like ? or username like ?)";
			paramArr.add("%" + userPhone + "%");
			paramArr.add("%" + userPhone + "%");
		}
		// 用户编号查询
		if (userCode != null && !"".equals(userCode)) {
			userWhere += " and org_code like ?";
			paramArr.add("%" + userCode + "%");
		}
		// 时间
		if (startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date startD = dateFormat.parse(startDate);
				Date endD = dateFormat.parse(endDate);
				bonusWhere += " and date between ? and ?";
				paramArr.add(startD);
				paramArr.add(endD);
			} catch (Exception e) {

			}
		}
		String sql = "select count(*) as count from t_inesv_bonus_level where bonus_user in (select user_no from t_inesv_user where 1=1 "
				+ userWhere + ") " + bonusWhere + " order by date desc";

		try {
			size = queryRunner.query(sql, new ColumnListHandler<Long>("count"), paramArr.toArray(new Object[] {}))
					.get(0);
		} catch (SQLException e) {
			logger.error("查询分润条目数量出错");
			e.printStackTrace();
		}
		return size;
	}

	public List<BonusLevelDto> queryAll(int curPage, int pageItem, String userName, String userPhone, String userCode,
			String startDate, String endDate) {
		ArrayList<Object> paramArr = new ArrayList<>();
		String userWhere = "";
		String bonusWhere = "";
		// 用户姓名
		if (userName != null && !"".equals(userName)) {
			userWhere += " and (username like ? or real_name like ?)";
			paramArr.add("%" + userName + "%");
			paramArr.add("%" + userName + "%");
		}
		// 用户电话查询
		if (userPhone != null && !"".equals(userPhone)) {
			userWhere += " and (phone like ? or username like ?)";
			paramArr.add("%" + userPhone + "%");
			paramArr.add("%" + userPhone + "%");
		}
		// 用户编号查询
		if (userCode != null && !"".equals(userCode)) {
			userWhere += " and org_code like ?";
			paramArr.add("%" + userCode + "%");
		}
		// 时间
		if (startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date startD = dateFormat.parse(startDate);
				Date endD = dateFormat.parse(endDate);
				bonusWhere += " and date between ? and ?";
				paramArr.add(startD);
				paramArr.add(endD);
			} catch (Exception e) {

			}
		}
		String sql = "select * from t_inesv_bonus_level where bonus_user in (select user_no from t_inesv_user where 1=1 "
				+ userWhere + ") " + bonusWhere + " order by date desc LIMIT ?,?";
		paramArr.add((curPage - 1) * pageItem);
		paramArr.add(pageItem);
		sql = "SELECT bnus.*, ua.username as attr1,ua.real_name as attr2,u.real_name as attr3, tcoin.coin_name as attr4 from ("
				+ sql
				+ ")  bnus left join t_inesv_user u on bnus.bonus_user=u.user_no left join t_inesv_user ua on bnus.bonus_rel=ua.user_no left join t_inesv_coin_type tcoin on bnus.bonus_coin=tcoin.coin_no;";
		List<BonusLevelDto> list = new ArrayList<BonusLevelDto>();
		System.out.println("sql------------");
		System.out.println(sql);
		System.out.println(paramArr.toString());
		try {
			list = queryRunner.query(sql, new BeanListHandler<BonusLevelDto>(BonusLevelDto.class),
					paramArr.toArray(new Object[] {}));
		} catch (SQLException e) {
			logger.error("查询分润列表出错");
			e.printStackTrace();
		}
		System.out.println("result---------------");
		System.out.println(list.toString());
		return list;
	}

}
