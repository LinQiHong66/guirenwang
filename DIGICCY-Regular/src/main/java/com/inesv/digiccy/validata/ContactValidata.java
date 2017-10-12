package com.inesv.digiccy.validata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.inesv.digiccy.api.command.ContactCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.query.QueryContact;

/**
 * Created by Liukeling
 */
@Component
public class ContactValidata {

	@Autowired
	private CommandGateway commandGateway;
	@Autowired
	QueryContact queryContact;

	public Map<String, Object> getAllContact(String filed, String value) {
		HashMap<String, Object> map = new HashMap<>();
		List list = queryContact.getAllContact(filed, value);

		if (list != null) {
			map.put("list", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {    
			map.put("list", "none");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}  

		return map;
	}

	// 修改
	public Map<String, Object> modifyContact(int id, String email, String weixin, String wxqrurl, String qqqrurl,
			String qq, String address, String remark, boolean modifyFile, String telphone, String authority_account) {
		HashMap<String, Object> map = new HashMap<>();
		ContactCommand command = new ContactCommand();
		command.setAddress(address);
		command.setEmail(email);
		command.setId(id);
		command.setOperation("modify");
		command.setQq(qq);
		command.setWeixin(weixin);
		command.setWxqrcord(modifyFile ? wxqrurl : "none");
		command.setQqqrcord(modifyFile ? qqqrurl : "none");
		command.setRemark(remark);
		command.setTelphone(telphone);
		command.setAuthority_account(authority_account);
		try {
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {

			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	// 添加
	public Map<String, Object> addContact(String email, String weixin, String wxqrurl, String qqqrurl, String qq,
			String address, String remark, String telphone, String authority_account) {
		HashMap<String, Object> map = new HashMap<>();
		ContactCommand command = new ContactCommand();
		command.setAddress(address);
		command.setEmail(email);
		command.setOperation("insert");
		command.setQq(qq);
		command.setWxqrcord(wxqrurl);
		command.setQqqrcord(qqqrurl);
		command.setWeixin(weixin);
		command.setRemark(remark);
		command.setTelphone(telphone);
		command.setAuthority_account(authority_account);
		try {
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {

			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	// 删除
	public Map<String, Object> delContact(int id) {
		HashMap<String, Object> map = new HashMap<>();
		ContactCommand command = new ContactCommand();
		command.setOperation("delete");
		command.setId(id);
		try {
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {

			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
