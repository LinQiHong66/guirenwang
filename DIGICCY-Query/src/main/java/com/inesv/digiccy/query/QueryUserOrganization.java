package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InesvUserOrganizationDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class QueryUserOrganization {

	private static Logger log = LoggerFactory.getLogger(QueryUserOrganization.class);

	@Autowired
	private QueryRunner queryRunner;

	public List<InesvUserOrganizationDto> getAllUserOrganization() {
		String sql = "SELECT t1.id AS id , t1.user_no AS user_no , t2.username AS attr1 , t1.state AS state , t1.org_type AS org_type , "
				+ "t1.date AS date FROM t_inesv_user_organization t1 , t_inesv_user t2 WHERE t1.user_no = t2.user_no ORDER BY DATE DESC ";
		try {
			List<InesvUserOrganizationDto> uidList = queryRunner.query(sql, new BeanListHandler<InesvUserOrganizationDto>(InesvUserOrganizationDto.class));
			return uidList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public InesvUserOrganizationDto getAllUserOrganization(Integer userNo,Integer org_type) {
		String sql = "SELECT * FROM t_inesv_user_organization WHERE user_no = ? and org_type = ?";
		Object params[] = {userNo,org_type};
		try {
			InesvUserOrganizationDto uidList = queryRunner.query(sql, new BeanHandler<InesvUserOrganizationDto>(InesvUserOrganizationDto.class),params);
			return uidList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
 
}
