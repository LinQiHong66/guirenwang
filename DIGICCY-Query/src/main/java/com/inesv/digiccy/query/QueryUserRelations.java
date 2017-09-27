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

import com.inesv.digiccy.dto.UserRelations;


@Component
public class QueryUserRelations {
	
	private static Logger logger = LoggerFactory.getLogger(QueryUserRelations.class);
	
	@Autowired
	QueryRunner queryRunner;
	
	public List<UserRelations> queryAll(){
		String sql = "select * from t_user_relations";
		List<UserRelations> list = new ArrayList<UserRelations>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<UserRelations>(UserRelations.class));
		} catch (SQLException e) {
			logger.error("查询用户关联表出错");
			e.printStackTrace();
		}
		return list;
	}
	
	public UserRelations queryRelationByUserNo(Long user_no){
		String sql = "select * from t_user_relations where user_no = ?";
		UserRelations Relations = new UserRelations();
		
		try {
			Relations = queryRunner.query(sql, new BeanHandler<UserRelations>(UserRelations.class),user_no);
		} catch (SQLException e) {
			logger.error("查询用户关联表出错");
			e.printStackTrace();
		}
		return Relations;
	}
}
