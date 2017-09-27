package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.AddressCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.AddressDto;
import com.inesv.digiccy.query.QueryAddress;

import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
@Component
public class AddressValidata {

	@Autowired
	QueryAddress queryAddress;

	@Autowired
	CommandGateway commandGateway;

	public Map<String, Object> queryAddress(String userNo) {
		Map<String, Object> map = new HashMap<>();
		List<AddressDto> list = queryAddress.queryAddressByUser(userNo);
		if (list == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		} else {
			if (list.size() > 0) {
				map.put("data", list.get(0));
			} else {
				map.put("data", "none");
			}
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	public Map<String, Object> insertAddress(String userNo, String remarkAddress, String name, String card,
			String phone, String address) {
		
		try {
			remarkAddress = new String(remarkAddress.getBytes("iso-8859-1"), "utf-8");
			userNo = new String(userNo.getBytes("iso-8859-1"), "utf-8");
			name = new String(name.getBytes("iso-8859-1"), "utf-8");
			card = new String(card.getBytes("iso-8859-1"), "utf-8");
			phone = new String(phone.getBytes("iso-8859-1"), "utf-8");
			address = new String(address.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("*******************");
		System.out.println(remarkAddress+"------"+name+"-----"+address);
		System.out.println("*******************");
		
		Map<String, Object> result = new HashMap<>();
		List<AddressDto> addresss = queryAddress.queryAddressByUser(userNo);
		if (addresss.size() == 0) {
			try {
				AddressCommand command = new AddressCommand(0L, userNo, remarkAddress, name, card, phone, address,
						new Date(), "", "", "insert");
				commandGateway.send(command);
				result.put("code", ResponseCode.SUCCESS);
				result.put("desc", ResponseCode.SUCCESS_DESC);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("code", ResponseCode.FAIL);
				result.put("desc", ResponseCode.FAIL_DESC);
			}
		} else {
			AddressDto mas = addresss.get(0);
			result.putAll(updateAddress(mas.getId().toString(), userNo, name, phone, address));
		}
		return result;
	}

	public Map<String, Object> updateAddress(String id, String userNo, String name, String phone, String address) {
		Map<String, Object> result = new HashMap<>();
		try {
			AddressCommand command = new AddressCommand(Long.valueOf(id), userNo, "", name, "", phone, address,
					new Date(), "", "", "update");
			commandGateway.send(command);
			result.put("code", ResponseCode.SUCCESS);
			result.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", ResponseCode.FAIL);
			result.put("desc", ResponseCode.FAIL_DESC);
		}
		return result;
	}

	public Map<String, Object> deleteAddress(String id) {
		Map<String, Object> result = new HashMap<>();
		try {
			AddressCommand command = new AddressCommand(Long.valueOf(id), "", "", "", "", "", "", new Date(), "", "",
					"delete");
			commandGateway.send(command);
			result.put("code", ResponseCode.SUCCESS);
			result.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", ResponseCode.FAIL);
			result.put("desc", ResponseCode.FAIL_DESC);
		}
		return result;
	}

}
