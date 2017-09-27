package com.inesv.digiccy.validata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.LinkCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.LinkDto;
import com.inesv.digiccy.query.QueryLink;

/**
 * 
 * @author Liukeling
 *
 */
@Component
public class LinkValidate {

	@Autowired
	CommandGateway commandGateway;
	
	@Autowired
	QueryLink queryLink;
	
	public Map<String, Object> getById(int id){

		HashMap<String, Object> map = new HashMap<>();

		map.put("code", ResponseCode.FAIL);
		map.put("desc", ResponseCode.FAIL_DESC);
		map.put("result", "");
		List<LinkDto> list = queryLink.getAllLink("id", id+"");
		if(list != null && !list.isEmpty()){
			map.put("result", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);  
		}     
		return map;
	}
	
	public Map<String, Object> getAllLink(){
		HashMap<String, Object> map = new HashMap<>();

		map.put("code", ResponseCode.FAIL);
		map.put("desc", ResponseCode.FAIL_DESC);
		map.put("result", "");
		List list = queryLink.getAllLink("", "");
		if(list != null && !list.isEmpty()){
			map.put("result", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}
	public Map<String, Object> getGroupLink(){
		HashMap<String, Object> map = new HashMap<>();

		map.put("code", ResponseCode.FAIL);
		map.put("desc", ResponseCode.FAIL_DESC);
		map.put("result", "");
		Map list = queryLink.getGroupLink();
		if(list != null && !list.isEmpty()){
			map.put("result", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}
	public Map<String, Object> addLink(String linkName, String linkUrl, String linkType){
		Map<String, Object> map = new HashMap<>();
		LinkCommand command = new LinkCommand();
		command.setLinkName(linkName);
		command.setLinkUrl(linkUrl);
		command.setLinkType(linkType);
		command.setOperation("insert");
		try{
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;   
	}
	public Map<String, Object> delLink(int id){
		Map<String, Object> map = new HashMap<>();
		LinkCommand command = new LinkCommand();
		command.setId(id);
		command.setOperation("delete");
		try{
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	public Map<String, Object> updateLink(int id,String linkName, String linkUrl, String linkType){
		Map<String, Object> map = new HashMap<>();
		LinkCommand command = new LinkCommand();
		command.setLinkName(linkName);
		command.setLinkUrl(linkUrl);
		command.setId(id);
		command.setLinkType(linkType);
		command.setOperation("update");
		try{
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}catch(Exception e){
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
