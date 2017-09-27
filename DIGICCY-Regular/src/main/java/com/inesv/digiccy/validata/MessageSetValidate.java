package com.inesv.digiccy.validata;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.MessageSetCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.MessageLogDto;
import com.inesv.digiccy.dto.MessageSetDto;
import com.inesv.digiccy.query.QueryMessageSet;

@Component
public class MessageSetValidate {

	@Autowired
	QueryMessageSet queryMessageSet;
	@Autowired
	CommandGateway commandGateway;

	/**
	 * 获取短信频率设置
	 * 
	 * @return
	 */
	public Map<String, Object> getMessageSet() {
		HashMap<String, Object> map = new HashMap<>();
		MessageSetDto dto = queryMessageSet.getMessageSet();
		if (dto != null) {
			map.put("result", dto);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("result", "none");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public Map<String, Object> modifyMessageSet(int id, int limitDate, int limit_number, String limit_ip,
			String limit_name) {
		int recordCount = queryMessageSet.getMessageSetCount();
		MessageSetCommand command = new MessageSetCommand();
		command.setId(id);
		command.setLimit_date(limitDate);
		command.setLimit_number(limit_number);
		command.setLimit_ip(limit_ip);
		command.setLimit_name(limit_name);
		command.setUpdate_time(new Date());
		if (recordCount > 0) {
			command.setOperation("update");
		} else {
			command.setOperation("insert");
		}
		HashMap<String, Object> map = new HashMap<>();
		try {
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public Map<String, Object> modifyMessageLog(int id, int limitDate, int limit_number, String limit_ip,
			String limit_name) {
		HashMap<String, Object> map = new HashMap<>();
		return map;

	}

	public List<MessageLogDto> queryMessageLogByPhoneNumber(String phoneNumber) {
		List<MessageLogDto> dtos = queryMessageSet.getMessageLogs(phoneNumber);
		return dtos;
	}
	public Map<String,Object> queryAllMessageLog() {
		Map<String,Object> dtos = queryMessageSet.getMessageLogs();
		return dtos;
	}
}
