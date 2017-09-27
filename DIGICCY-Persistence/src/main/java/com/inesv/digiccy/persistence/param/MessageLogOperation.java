package com.inesv.digiccy.persistence.param;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.MessageLogDto;
import com.inesv.digiccy.util.ObjectChangeUtil;

@Component
public class MessageLogOperation {
	private static Logger logger = LoggerFactory.getLogger(MessageLogOperation.class);

	@Autowired
	QueryRunner queryRunner;

	/**
	 * 新增后台参数
	 * 
	 * @param messageSetDto
	 */
	public void addMessageLog(MessageLogDto messageLogDto) {                           
		String sql = new ObjectChangeUtil<MessageLogDto>().objectToSql(messageLogDto, "t_inesv_message_log");
		Object params[] = new ObjectChangeUtil<MessageLogDto>().objectToArray(messageLogDto);
		System.out.println("add:" + messageLogDto.toString());
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
	public void updateMessageLog(MessageLogDto messageLogDto) {
		String sql = "UPDATE t_inesv_message_log SET phone_number =?,receive_name=? ,sms_content=?,update_time=?,user_id =? where id =?";
		Object params[] = { messageLogDto.getPhone_number(), messageLogDto.getReceive_name(),
				messageLogDto.getSms_content(), messageLogDto.getUpdate_time(), messageLogDto.getUser_id(),
				messageLogDto.getId() };
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
	public void deteleMessageLog(Long id) {
		String sql = "DELETE FROM t_inesv_message_log WHERE id = ?";
		Object params[] = { id };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("删除后台参数错误");
			e.printStackTrace();
		}
	}

}
