package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.InesvUserAddressLevelDto;
import com.inesv.digiccy.dto.UserLevelDto;

@Component
public class QueryUserLevel {
	
	private static Logger logger = LoggerFactory.getLogger(QueryUserLevel.class);
	
	@Autowired
	QueryRunner queryRunner;
	
	public List<UserLevelDto> queryAll(){
		String sql = "select * from t_user_address_level";
		List<UserLevelDto> list = new ArrayList<UserLevelDto>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<UserLevelDto>(UserLevelDto.class));
		} catch (SQLException e) {
			logger.error("查询用户权限列表出错！");
			e.printStackTrace();
		}
		return list;
	}
	
	public List<UserLevelDto> queryByOff(){
		String sql = "select * from t_user_address_level where status = 0";
		List<UserLevelDto> list = new ArrayList<UserLevelDto>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<UserLevelDto>(UserLevelDto.class));
		} catch (SQLException e) {
			logger.error("查询用户权限列表出错！");
			e.printStackTrace();
		}
		return list;
	}
	
	public UserLevelDto queryByLevelId(Long level_id){
		String sql = "select * from t_user_address_level where level_id = ?";
		UserLevelDto userLevelDto = new UserLevelDto();
		try {
			userLevelDto = queryRunner.query(sql, new BeanHandler<UserLevelDto>(UserLevelDto.class),level_id);
		} catch (SQLException e) {
			logger.error("根据权限主键查询列表出错！");
			e.printStackTrace();
		}
		return userLevelDto;
	}
	
	public Integer queryByUserId1(Long user_id){
		String sql = "select * from t_user_address_level where user_id = ? and level = 1 ";
		Long countL = 0L;
		try {
			countL = (Long)queryRunner.query(sql, new ScalarHandler<Long>(),user_id);
		} catch (SQLException e) {
			logger.error("根据权限主键查询列表出错！");
			e.printStackTrace();
		}
		Integer count = countL.intValue();
		return count;
	}
	
	public Integer queryByUserId2(Long user_id){
		String sql = "select * from t_user_address_level where user_id = ? and level = 2 ";
		Long countL = 0L;
		try {
			countL = (Long)queryRunner.query(sql, new ScalarHandler<Long>(),user_id);
		} catch (SQLException e) {
			logger.error("根据权限主键查询列表出错！");
			e.printStackTrace();
		}
		Integer count = countL.intValue();
		return count;
	}
	
	public Integer queryByUserId3(Long user_id){
		String sql = "select * from t_user_address_level where user_id = ? and level = 3 ";
		Long countL = 0L;
		try {
			countL = (Long)queryRunner.query(sql, new ScalarHandler<Long>(),user_id);
		} catch (SQLException e) {
			logger.error("根据权限主键查询列表出错！");
			e.printStackTrace();
		}
		Integer count = countL.intValue();
		return count;
	}
	
	public Integer queryByUserId4(Long user_id){
		String sql = "select * from t_user_address_level where user_id = ? and level = 4";
		Long countL = 0L;
		try {
			countL = (Long)queryRunner.query(sql, new ScalarHandler<Long>(),user_id);
		} catch (SQLException e) {
			logger.error("根据权限主键查询列表出错！");
			e.printStackTrace();
		}
		Integer count = countL.intValue();
		return count;
	}
	
	public Integer queryByUserId5(Long user_id){
		String sql = "select * from t_user_address_level where user_id = ? and level = 5";
		Long countL = 0L;
		try {
			countL = (Long)queryRunner.query(sql, new ScalarHandler<Long>(),user_id);
		} catch (SQLException e) {
			logger.error("根据权限主键查询列表出错！");
			e.printStackTrace();
		}
		Integer count = countL.intValue();
		return count;
	}
	
	public Integer queryByUserId6(Long user_id){
		String sql = "select count(*) from t_user_address_level where user_id = ? and level = 6";
		Long countL = 0L;
		try {
			countL = (Long)queryRunner.query(sql, new ScalarHandler<Long>(),user_id);
		} catch (SQLException e) {
			logger.error("根据权限主键查询列表出错！");
			e.printStackTrace();
		}
		Integer count = countL.intValue();
		return count;
	}
	
	public Integer queryByUserId(Long user_id,Integer level){
		String sql = "select count(*) from t_user_address_level where user_id = ? and level = ?";
		Long countL = 0L;
		Object params[] = {user_id,level};
		try {
			countL = (Long)queryRunner.query(sql, new ScalarHandler<Long>(),params);
		} catch (SQLException e) {
			logger.error("根据权限主键查询列表出错！");
			e.printStackTrace();
		}
		Integer count = countL.intValue();
		return count;
	}
	
	public List<InesvUserAddressLevelDto> queryByStatus(){
		System.out.println("+++++++++++++++++1111111111111111");
		String sql = "select user_id,level,address_code,status from t_inesv_address t INNER JOIN t_user_address_level t2 ON t.address_level=t2.level where status = 0";
		List<InesvUserAddressLevelDto> list = new ArrayList<InesvUserAddressLevelDto>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<InesvUserAddressLevelDto>(InesvUserAddressLevelDto.class));
		} catch (SQLException e) {
			logger.error("查询api接口列表失败");
			e.printStackTrace();
		}
		return list;
	}
}
