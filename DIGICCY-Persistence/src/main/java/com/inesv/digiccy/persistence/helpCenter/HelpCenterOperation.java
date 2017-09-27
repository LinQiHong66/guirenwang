package com.inesv.digiccy.persistence.helpCenter;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.CommandRedDto;
import com.inesv.digiccy.dto.CrowdFundingDto;
import com.inesv.digiccy.dto.HelpCenterDto;

import java.sql.SQLException;

/**
 * Created by JimJim on 2017/06/06 0009.
 */
@Component
public class HelpCenterOperation {
	
	private static Logger logger = LoggerFactory.getLogger(HelpCenterOperation.class);

	@Autowired
	QueryRunner queryRunner;
	
	/*
	 * 新增
	 */
	public void insertHelpCenter(HelpCenterDto helpCenterDto) throws SQLException {
		String sql = "INSERT INTO t_help_center (help_name,help_grade,help_parent,help_remark) VALUES (?,?,?,?)";
		Object params[] = { helpCenterDto.getHelp_name(),
				helpCenterDto.getHelp_grade(),
				helpCenterDto.getHelp_parent(),
				helpCenterDto.getHelp_remark()};
		queryRunner.update(sql, params);
		/*try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("新增口令红包失败");
		}*/
	}
	
	/*
	 * 修改口令红包信息
	 */
	public void updateHelpCenter(HelpCenterDto helpCenterDto) throws SQLException {
		String sql = "UPDATE t_help_center SET help_name=?,help_grade=?,help_parent=?,help_remark=? WHERE id = ?";
		Object params[] = { helpCenterDto.getHelp_name(),
				helpCenterDto.getHelp_grade(),
				helpCenterDto.getHelp_parent(),
				helpCenterDto.getHelp_remark(),
				helpCenterDto.getId()};
		queryRunner.update(sql, params);
		/*try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("修改口令红包失败");
		}*/
	}
	
	/*
	 * 删除帮助中心信息
	 */
	public void deleteHelpCenter(HelpCenterDto helpCenterDto) throws SQLException {
		String sql = "DELETE FROM t_help_center WHERE id = ?";
		Object params[] = {helpCenterDto.getId()};
		queryRunner.update(sql, params);
		/*try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("删除帮助中心功能失败");
		}*/
	}

}
