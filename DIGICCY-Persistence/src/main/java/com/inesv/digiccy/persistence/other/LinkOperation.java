package com.inesv.digiccy.persistence.other;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.LinkDto;

@Component
public class LinkOperation {

	private static Logger logger = LoggerFactory.getLogger(UserVoucherOperation.class);

	@Autowired
	QueryRunner queryRunner;
	
	public void insert(LinkDto dto){
		String sql = "insert into t_inesv_link (link_name, link_url,link_type) values (?, ?, ?)";
		Object[] params = {dto.getLinkName(), dto.getLinkUrl(), dto.getLinkType()};
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("添加链接失败");
		}
	}
	public void delById(int id){
		String sql = "delete from t_inesv_link where id=?";
		Object[] patams = {id};
		try {
			queryRunner.update(sql, patams);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("删除链接失败");
		}
	}
	public void updateById(LinkDto dto){
		String sql = "update t_inesv_link set link_name=?, link_url=?, link_type=? where id=?";
		Object[] params = {dto.getLinkName(), dto.getLinkUrl(),dto.getLinkType(), dto.getId()};
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("更新链接失败");
		}
	}
}
