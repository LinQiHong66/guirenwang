package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.UserVoucherDto;
import com.inesv.digiccy.event.UserInviteEvent;
import com.inesv.digiccy.event.UserVoucherEvent;
import com.inesv.digiccy.persistence.finance.RecUserPersistence;
import com.inesv.digiccy.persistence.other.UserVoucherOperation;

public class UserVoucherHandler {

	@Autowired
	UserVoucherOperation userVoucherOperation;

	@EventHandler
	public void handle(UserVoucherEvent event) throws Exception {
		UserVoucherDto dto = new UserVoucherDto();
		dto.setCardId(event.getCardId());
		dto.setCardType(event.getCardType());
		dto.setId(event.getId());
		dto.setImgUrl1(event.getImgUrl1());
		dto.setImgUrl2(event.getImgUrl2());
		dto.setImgUrl3(event.getImgUrl3());
		dto.setState(event.getState());
		dto.setTrueName(event.getRealName());
		dto.setUserNo(event.getUserNo());
		dto.setEnDateDate(event.getEnDateDate());
		dto.setStartDate(event.getStartDate());
		dto.setMyvoucherType(event.getMyvoucherType());
		String operation = event.getOperating();
		
		switch (operation) {
		case "startinsert":
			userVoucherOperation.insert(dto);
			break;
		case "startupdate":
			userVoucherOperation.updatestartByUserNo(dto);
			break;
		case "modifystate":
			userVoucherOperation.modifyVoucherState(dto);
			break;
		default:
			break;
		}
	}
}
