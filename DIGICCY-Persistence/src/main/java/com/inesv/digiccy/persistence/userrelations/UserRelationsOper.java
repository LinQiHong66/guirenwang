package com.inesv.digiccy.persistence.userrelations;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRelationsOper {
	
	private static Logger logger = LoggerFactory.getLogger(UserRelationsOper.class);
	
	@Autowired
	QueryRunner queryRunner;
	
	public void insert(Long user_no,Long relations_no){
		String sql = "insert into t_user_relations(user_no,relations_no) values(?,?)";
		Object params[] = {user_no,relations_no};
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("创建用户关系失败");
			e.printStackTrace();
		}
	}
}
