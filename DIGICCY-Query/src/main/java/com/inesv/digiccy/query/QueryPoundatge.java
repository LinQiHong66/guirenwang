package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.BonusLevelDto;
import com.inesv.digiccy.dto.PoundatgeDto;

@Component
public class QueryPoundatge {

	@Autowired
	QueryRunner queryRunner;

	private static Logger logger = LoggerFactory.getLogger(QueryPoundatge.class);

	public List<PoundatgeDto> queryAll(int curPage, int pageItem, String userOrgCode, String phone, String username,
			String startDate, String endDate) {
		String userWhere = "";
		String pounWhere = "";

		ArrayList<Object> paramArr = new ArrayList<>();
		if (userOrgCode != null && !"".equals(userOrgCode)) {
			userWhere += " and org_code like ?";
			paramArr.add("%" + userOrgCode + "%");
		}

		if (phone != null && !"".equals(phone)) {
			userWhere += " and phone like ?";
			paramArr.add("%" + phone + "%");
		}

		if (username != null && !"".equals(username)) {
			userWhere += " and (username like ? or real_name like ?)";
			paramArr.add("%" + username + "%");
			paramArr.add("%" + username + "%");
		}

		if (startDate != null && endDate != null && !"".equals(startDate) && !"".equals(endDate)) {
			try {

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date startD = format.parse(startDate);
				Date endD = format.parse(endDate);
				pounWhere += " and date between ? and ?";
				paramArr.add(startD);
				paramArr.add(endD);
			} catch (Exception e) {

			}
		}

		String sql = "select p.id, p.user_no, p.optype,p.type as coinNo,p.money,p.date,p.sum_money,p.attr1 as coinpoun from t_inesv_poundage as p where user_no in (select user_no from t_inesv_user where 1=1 "
				+ userWhere + ") " + pounWhere + " order by date desc LIMIT ?,?";
		paramArr.add((curPage - 1) * pageItem);
		paramArr.add(pageItem);

		sql = "select  poun.*,u.username as attr2, u.org_code as user_code,u.real_name as user_name, cointype.coin_name as attr3,pcointype.coin_name as attr1 from ("
				+ sql
				+ ") poun left JOIN t_inesv_user u on poun.user_no=u.user_no LEFT JOIN t_inesv_coin_type cointype on poun.coinNo=cointype.coin_no LEFT JOIN t_inesv_coin_type pcointype on poun.coinpoun=pcointype.coin_no";

		List<PoundatgeDto> list = new ArrayList<PoundatgeDto>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<PoundatgeDto>(PoundatgeDto.class),
					paramArr.toArray(new Object[] {}));
		} catch (SQLException e) {
			logger.error("查询手续费列表出错");
			e.printStackTrace();
		}
		return list;
	}

}
