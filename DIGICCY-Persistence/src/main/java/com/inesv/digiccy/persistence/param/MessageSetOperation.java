package com.inesv.digiccy.persistence.param;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.MessageSetDto;
import com.inesv.digiccy.util.ObjectChangeUtil;

@Component
public class MessageSetOperation {
	private static Logger logger = LoggerFactory.getLogger(MessageSetOperation.class);

	@Autowired
	QueryRunner queryRunner;

	/**
	 * 新增后台参数
	 * 
	 * @param messageSetDto
	 */
	public void addMessageSet(MessageSetDto messageSetDto) {
		String sql = new ObjectChangeUtil<MessageSetDto>().objectToSql(messageSetDto, "t_inesv_messageset");
		Object params[] = new ObjectChangeUtil<MessageSetDto>().objectToArray(messageSetDto);
		System.out.println("add:" + messageSetDto.toString());
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("新增后台参数错误");
			e.printStackTrace();
		}
	}

	/**
	 * 修改后台参数
	 * 
	 * @param staticParams
	 */
	public void updateMessageSet(MessageSetDto messageSetDto) {
		String sql = "UPDATE t_inesv_messageset SET limit_date =?,limit_number=? ,limit_ip=?,limit_name=?,update_time =? where id =?";
		Object params[] = { messageSetDto.getLimit_date(), messageSetDto.getLimit_number(), messageSetDto.getLimit_ip(),
				messageSetDto.getLimit_name(), new Date(), messageSetDto.getId() };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("修改后台参数错误");
			e.printStackTrace();
		}
	}

	/**
	 * 删除后台参数
	 */
	public void deteleMessageSet(Integer id) {
		String sql = "DELETE FROM t_inesv_messageset WHERE id = ?";
		Object params[] = { id };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("删除后台参数错误");
			e.printStackTrace();
		}
	}

}
