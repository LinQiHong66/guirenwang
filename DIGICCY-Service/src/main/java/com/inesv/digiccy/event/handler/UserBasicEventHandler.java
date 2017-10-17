package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.UserBasicInfoDto;
import com.inesv.digiccy.event.UserBasicInfoEvent;
import com.inesv.digiccy.persistence.user.UserBasicInfoOperation;

public class UserBasicEventHandler {

	@Autowired
	UserBasicInfoOperation userBasicInfoOptation;

	private static Logger logger = LoggerFactory.getLogger(UserBasicEventHandler.class);

	@EventHandler
	public void handle(UserBasicInfoEvent event) throws Exception {
		UserBasicInfoDto dto = new UserBasicInfoDto();
		dto.setBirthday(event.getBirthday());
		dto.setJob(event.getJob());
		dto.setNationality(event.getNationality());
		dto.setSex(event.getSex());
		dto.setUserNo(event.getUserNo());
		dto.setUserName(event.getUserName());
		dto.setProvince(event.getProvince());
		dto.setDistricts(event.getDistricts());
		dto.setCities(event.getCities());
		dto.setAddressInfo(event.getAddressInfo());
		String opration = event.getOpration();
		switch (opration) {
		case "insert":
			try {
				userBasicInfoOptation.addBasicInfo(dto);
				System.out.println(dto.toString());
			} catch (Exception e) {
				logger.debug(e.getMessage());
				e.printStackTrace();
			}
			break;
		case "updateRealName":
			try {
				userBasicInfoOptation.updateRealName(dto);
			} catch (Exception e) {
				logger.debug(e.getMessage());
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
}
