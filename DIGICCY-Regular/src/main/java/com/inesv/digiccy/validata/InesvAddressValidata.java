package com.inesv.digiccy.validata;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.AddressCommand;
import com.inesv.digiccy.api.command.InesvAddressCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.AddressDto;
import com.inesv.digiccy.dto.InesvAddressDto;
import com.inesv.digiccy.persistence.inesvaddress.InesvAddressOper;
import com.inesv.digiccy.query.QueryInesvAddress;


@Component
public class InesvAddressValidata {
	
	@Autowired
	QueryInesvAddress queryInesvAddress;
	
	@Autowired
	InesvAddressOper inesvAddressOper;
	
	@Autowired
	CommandGateway commandGateway;
	
	public Map<String,Object> QueryAll(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<InesvAddressDto> list = new ArrayList<InesvAddressDto>();
		list = queryInesvAddress.queryAll();
		if(list != null && list.size() != 0){
			map.put("list", list);
			map.put("code", ResponseCode.SUCCESS);
	        map.put("desc", ResponseCode.SUCCESS_DESC);			
		}else{
			map.put("code", ResponseCode.FAIL_ADDRESS_INFO_CODE);
			map.put("code", ResponseCode.FAIL_ADDRESS_INFO_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> queryLevel(String address_name){
		Map<String, Object> map = new HashMap<String, Object>();
		InesvAddressDto inesvAddressDto = new InesvAddressDto();
		inesvAddressDto = queryInesvAddress.queryByAddressName(address_name);
		if(inesvAddressDto != null){
			map.put("address", inesvAddressDto);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}else{
			map.put("code", ResponseCode.FAIL_ADDRESS_NAME_CODE);
			map.put("desc", ResponseCode.FAIL_ADDRESS_NAME_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> updateLevel(Long id,int address_level){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			InesvAddressCommand inesvAddressCommand = new InesvAddressCommand(id, "", 1, "", "", address_level, "updateLevel");			
			commandGateway.send(inesvAddressCommand);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL_ADDRESS_UPDATE_LEVEL_CODE);
			map.put("desc", ResponseCode.FAIL_ADDRESS_UPDATE_LEVEL_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> getAddressByUser(String userNo){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			List<AddressDto> inesvAddressDto = queryInesvAddress.queryByUserNo(userNo);
			map.put("data", inesvAddressDto);
			map.put("total", inesvAddressDto.size());
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL_ADDRESS_UPDATE_LEVEL_CODE);
			map.put("desc", ResponseCode.FAIL_ADDRESS_UPDATE_LEVEL_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> updateAddressByUser(String userNo,String name,String phone,String remark_address,String address,String card){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			AddressCommand inesvAddressCommand = new AddressCommand(1L, userNo, remark_address, name, card, phone, address, new Date(), "", "", "updateByUser");			
			commandGateway.send(inesvAddressCommand);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
