package com.inesv.digiccy.validata;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.VedioCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.VedioDto;
import com.inesv.digiccy.persistence.other.VedioOperation;
import com.inesv.digiccy.query.QueryVedio;

/**
 * 
 * @author Liukeling
 *
 */
@Component
public class VedioValidata {
	private static Logger logger = LoggerFactory.getLogger(StaticParamValidata.class);

	@Autowired
	CommandGateway commandGateway;
	
	@Autowired
	QueryVedio queryVedio;
	
	public Map<String, Object> getHomeVedio(){
		HashMap<String, Object> map = new HashMap<>();
		try{
			VedioDto dto = queryVedio.getHomeVedio();
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("result", dto);
		}catch(Exception e){
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			map.put("result", "");   
			logger.error("获取首页视频失败");
		}
		return map;  
	}
	public Map<String, Object> updateHomeVedio(String url){
		Map<String , Object> getmap = getHomeVedio();
		Object obj = getmap.get("result");
		Map<String, Object> map = new HashMap<>();
		VedioCommand command = new VedioCommand();
		command.setUrl(url);
		command.setName("首页视频");
		command.setInfo("首页视频");
		command.setId(-1);
		if(obj instanceof VedioDto && ((VedioDto)obj).getUrl()!=null && !((VedioDto)obj).getUrl().isEmpty()){
			//存在
			command.setOperation("update");
		}else{
			//不存在
			command.setOperation("insertHomeVedio");
		}
		try{
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}catch(Exception e){
			logger.error("保存首页视频失败");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
