package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.MessageLogDto;
import com.inesv.digiccy.dto.MessageSetDto;

@Component
public class QueryMessageSet {
	@Autowired
	QueryRunner queryRunner;

	public MessageSetDto getMessageSet() {
		String sql = "select id, limit_date, limit_number,limit_ip, limit_name,update_time from t_inesv_messageset";
		try {
			List<MessageSetDto> list = queryRunner.query(sql, new BeanListHandler<MessageSetDto>(MessageSetDto.class));
			if (list != null && !list.isEmpty()) {
				return list.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getMessageSetCount() {
		String sql = "select id, limit_date, limit_number,limit_ip, limit_name,update_time from t_inesv_messageset";
		try {
			List<MessageSetDto> list = queryRunner.query(sql, new BeanListHandler<MessageSetDto>(MessageSetDto.class));
			if (list != null && !list.isEmpty()) {
				return list.size();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<MessageLogDto> getMessageLogs(String phoneNumber) {
		List<MessageLogDto> list = null;
		String sql = "select id, phone_number, receive_name,sms_content, update_time,user_id from t_inesv_message_log where phone_number=? ORDER BY update_time DESC ";
		Object params[] = { phoneNumber };
		try {
			list = queryRunner.query(sql, new BeanListHandler<MessageLogDto>(MessageLogDto.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Map<String, Object> getMessageLogs() {
		List<MessageLogDto> list = null;
		 Map<String,Object> map=new HashMap<>();
		String sql = "select id, phone_number, receive_name,sms_content, update_time,user_id from t_inesv_message_log  ORDER BY update_time DESC ";
		try {
			list = queryRunner.query(sql, new BeanListHandler<MessageLogDto>(MessageLogDto.class));
			System.out.println("*********************" + list.size());
			map.put("data", list);
			map.put("total", list.size());
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (SQLException e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
}
