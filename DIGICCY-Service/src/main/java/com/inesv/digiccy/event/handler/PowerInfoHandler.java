package com.inesv.digiccy.event.handler;

import java.util.ArrayList;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.AddressDto;
import com.inesv.digiccy.dto.PowerInfoDto;
import com.inesv.digiccy.dto.ProwerParamDto;
import com.inesv.digiccy.event.PowerInfoEvent;
import com.inesv.digiccy.event.ProwerParamEvent;
import com.inesv.digiccy.persistence.power.PowerInfoOperation;

public class PowerInfoHandler {
	@Autowired
	PowerInfoOperation powerInfoOperation;

	@EventHandler
	public void handler(PowerInfoEvent event) throws Exception {
		PowerInfoDto dto = new PowerInfoDto();
		ArrayList<ProwerParamEvent> eprama = event.getParams();
		ArrayList<ProwerParamDto> params = new ArrayList<>();
		if (eprama != null) {
			for (ProwerParamEvent parame : eprama) {
				ProwerParamDto pdto = new ProwerParamDto();
				pdto.setId(parame.getId());
				pdto.setParamInfo(parame.getParamInfo());
				pdto.setParamName(parame.getParamName());
				pdto.setParamValue(parame.getParamValue());
				pdto.setPowerId(parame.getPowerId());
				params.add(pdto);
			}
		}
		dto.setParams(params);
		dto.setId(event.getId());
		dto.setInfo(event.getInfo());
		dto.setTime(event.getTime());
		dto.setUrl(event.getUrl());
		dto.setUserName(event.getUserName());
		String operation = event.getOpration();
		switch (operation) {
		case "addInfo":
			powerInfoOperation.addInfo(dto);
			break;
		default:
			break;
		}
	}

}
