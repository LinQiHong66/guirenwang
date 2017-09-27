package com.inesv.digiccy.back.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.dto.MessageLogDto;
import com.inesv.digiccy.validata.MessageSetValidate;

/**
 * 短信频率设置
 * 
 * @author lumia
 *
 */
@Controller
@RequestMapping("/message")
public class MessageSetController {
	@Autowired
	MessageSetValidate messageSetValidate;

	@RequestMapping(value = "/messageset.do", method = RequestMethod.GET)
	public String gotoMessageSet() {
		return "/param/messageset";
	}

	@RequestMapping(value = "/getmessageset.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMessageSet() {
		Map<String, Object> map = messageSetValidate.getMessageSet();
		return map;
	}

	@RequestMapping(value = "/modifymessageset.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyMessageSet(int id, int limit_date, int limit_number, String limit_ip,
			String limit_name) {
		System.out.println("id:" + id + ",limit_date:" + limit_date + ",limit_number：" + limit_number + ",limit_ip："
				+ limit_ip + ",limit_name:" + limit_name);
		return messageSetValidate.modifyMessageSet(id, limit_date, limit_number, limit_ip, limit_name);
	}

	@RequestMapping(value = "/messagequery.do", method = RequestMethod.GET)
	public String gotoMessageQuery() {
		return "/param/messagequery";
	}

	@RequestMapping(value = "/querymessage", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> queryMessage() {
		Map<String,Object> map = messageSetValidate.queryAllMessageLog();
		return map;
	}
}
