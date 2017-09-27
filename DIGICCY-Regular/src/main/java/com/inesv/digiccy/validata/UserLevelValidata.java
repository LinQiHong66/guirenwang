package com.inesv.digiccy.validata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.UserLevelCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserAddressLevelDto;
import com.inesv.digiccy.dto.UserLevelDto;
import com.inesv.digiccy.query.QueryUserLevel;

@Component
public class UserLevelValidata {
	
	@Autowired
	QueryUserLevel queryUserLevel;
	
	@Autowired
	CommandGateway commandGateway;
	
	public Map<String,Object> queryAll(){
		Map<String,Object> map = new HashMap<String, Object>();
		List<UserLevelDto> list = new ArrayList<UserLevelDto>();
		list = queryUserLevel.queryAll();
		if(list != null||list.size() != 0){
			map.put("list", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}else{
			map.put("code", ResponseCode.FAIL_USER_LEVEL_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_USER_LEVEL_INFO_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> queryByLevelId(Long level_id){
		Map<String,Object> map = new HashMap<String, Object>();
		UserLevelDto userLevelDto = new UserLevelDto();
		userLevelDto = queryUserLevel.queryByLevelId(level_id);
		if(userLevelDto != null){
			map.put("userlevel", userLevelDto);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}else{
			map.put("code", ResponseCode.FAIL_USER_LEVEL_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_USER_LEVEL_INFO_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> update(Boolean status,Long user_id,Integer level){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			UserLevelCommand userLevelCommand = new UserLevelCommand(1L, user_id, level, status, "updateLevel");
			commandGateway.send(userLevelCommand);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL_USER_LEVEL_UPDATE_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_USER_LEVEL_UPDATE_INFO_CODE_DESC);
		}		
		return map;
	}
	
	public Map<String,Object> insert(Long user_id,Integer level){
		Map<String, Object> map = new HashMap<String, Object>();
		UserLevelCommand userLevelCommand = new UserLevelCommand(0L, user_id, level, false, "addLevel");
		commandGateway.send(userLevelCommand);
		return map;
	}
	
	public Map<String,Object> queryByUserId(Long user_id,Integer level){
		Map<String,Object> map = new HashMap<String, Object>();
		//UserLevelDto userLevelDto = new UserLevelDto();
		//userLevelDto = queryUserLevel.queryByUserId(user_id);
		Integer count = queryUserLevel.queryByUserId(user_id, level);
		if(count == 1){
			map.put("count", count);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}else{
			map.put("code", ResponseCode.FAIL_USER_LEVEL_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_USER_LEVEL_INFO_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> queryByOff(){
		Map<String,Object> map = new HashMap<String, Object>();
		List<UserLevelDto> list = new ArrayList<UserLevelDto>();
		list = queryUserLevel.queryByOff();
		if(list != null||list.size() != 0){
			map.put("list", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}else{
			map.put("code", ResponseCode.FAIL_USER_LEVEL_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_USER_LEVEL_INFO_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> queryByStatus(){
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println("+++++++++++++++++1111111111111111");
		List<InesvUserAddressLevelDto> list = new ArrayList<InesvUserAddressLevelDto>();
		list = queryUserLevel.queryByStatus();
		if(list != null||list.size() != 0){
			map.put("list", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}else{
			map.put("code", ResponseCode.FAIL_USER_LEVEL_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_USER_LEVEL_INFO_CODE_DESC);
		}
		return map;
	}
}
