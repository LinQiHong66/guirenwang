package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.HelpCenterDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 众筹项目查询 Created by JimJim on 2017/06/05 0017.
 */
@Component
public class QueryHelpCenterInfo {

	private static Logger logger = LoggerFactory.getLogger(QueryHelpCenterInfo.class);

	@Autowired
	QueryRunner queryRunner;

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<HelpCenterDto> queryAllHelpCenter() {
		List<HelpCenterDto> helpCenterDtoList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM t_help_center ";
			helpCenterDtoList = queryRunner.query(sql, new BeanListHandler<>(HelpCenterDto.class));
		} catch (SQLException e) {
			logger.error("查询帮助中心失败");
			e.printStackTrace();
		}
		return helpCenterDtoList;
	}

	/**
	 * 查询所有一级
	 * 
	 * @return
	 */
	public List<HelpCenterDto> queryAllHelpCenterByOne() {
		List<HelpCenterDto> helpCenterDtoList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM t_help_center WHERE help_grade = 1";
			helpCenterDtoList = queryRunner.query(sql, new BeanListHandler<>(HelpCenterDto.class));
		} catch (SQLException e) {
			logger.error("查询帮助中心失败");
			e.printStackTrace();
		}
		return helpCenterDtoList;
	}

	/**
	 * 查询所有对应父类的二级
	 * 
	 * @return
	 */
	public List<HelpCenterDto> queryAllHelpCenterByTwo(String parentId) {
		List<HelpCenterDto> helpCenterDtoList = new ArrayList<>();
		String sql = "SELECT id,help_name,help_grade,help_parent FROM t_help_center WHERE help_grade = 2 AND help_parent = ?";
		Object params[] = { parentId };
		try {
			helpCenterDtoList = queryRunner.query(sql, new BeanListHandler<>(HelpCenterDto.class), params);
		} catch (SQLException e) {
			logger.error("查询帮助中心失败");
			e.printStackTrace();
		}
		return helpCenterDtoList;
	}

	/**
	 * 查询指定
	 * 
	 * @return
	 */
	public HelpCenterDto queryHelpCenterInfo(String id) {
		HelpCenterDto helpCenterDto = new HelpCenterDto();
		String sql = "SELECT * FROM t_help_center WHERE id=?";
		Object params[] = { id };
		try {
			helpCenterDto = queryRunner.query(sql, new BeanHandler<>(HelpCenterDto.class), params);
		} catch (SQLException e) {
			logger.error("查询帮助中心失败");
			e.printStackTrace();
		}
		return helpCenterDto;
	}

	public Integer getIdsByTwoName(String name) {
		String sql = "select * from t_help_center WHERE help_name=?";
		try {
			name = new String(name.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Object params[] = { name };
		try {
			
			HelpCenterDto dto =  queryRunner.query(sql, new BeanHandler<HelpCenterDto>(HelpCenterDto.class),
					params);
			return Integer.parseInt(""+dto.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
