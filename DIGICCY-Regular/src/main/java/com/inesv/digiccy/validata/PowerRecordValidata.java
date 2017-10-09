package com.inesv.digiccy.validata;

import java.util.ArrayList;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.PowerInfoCommand;
import com.inesv.digiccy.api.command.ProwerParamCommand;
import com.inesv.digiccy.dto.PowerInfoDto;
import com.inesv.digiccy.dto.ProwerParamDto;

/**
 * 权限记录验证类
 * @author liukeling
 *
 */
@Component
public class PowerRecordValidata {

    @Autowired
    private CommandGateway commandGateway;
    
	//添加权限记录信息
	public void addPowerInfo(PowerInfoDto infoDto) {
		PowerInfoCommand command = new PowerInfoCommand();
		ArrayList<ProwerParamDto> params = infoDto.getParams();
		ArrayList<ProwerParamCommand> paramsc = new ArrayList<>();
		if(params != null) {
			for(ProwerParamDto pdto : params) {
				ProwerParamCommand prama = new ProwerParamCommand();
				prama.setId(pdto.getId());
				prama.setParamInfo(pdto.getParamInfo());
				prama.setParamName(pdto.getParamName());
				prama.setParamValue(pdto.getParamValue());
				prama.setPowerId(pdto.getPowerId());
				paramsc.add(prama);
			}
		}
		command.setParams(paramsc);
		command.setId(infoDto.getId());
		command.setInfo(infoDto.getInfo());
		command.setTime(infoDto.getTime());
		command.setUrl(infoDto.getUrl());
		command.setUserName(infoDto.getUserName());
		command.setOpration("addInfo");
		commandGateway.send(command);
	}
	//查询权限记录信息
	public void selectPowerInfo() {
		
	}
}
