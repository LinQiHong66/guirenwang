package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.StaticParamCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.StaticParamsDto;
import com.inesv.digiccy.query.QueryStaticParam;
import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/14 0014.
 */
@Component
public class StaticParamValidata {

    private static Logger logger = LoggerFactory.getLogger(StaticParamValidata.class);

    @Autowired
    CommandGateway commandGateway;

    @Autowired
    QueryStaticParam queryStaticParam;

    public Map<String,Object> getStaticParam(){
        Map<String,Object> map = new HashedMap();
        List<StaticParamsDto> param = queryStaticParam.getStaticParam();
        if(param == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("total",param.size());
            map.put("data",param);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    public Map<String,Object> addStaticParam(String param, String value){
        Map<String,Object> result = new HashedMap();
        try {
            StaticParamCommand command = new StaticParamCommand(1,param,new BigDecimal(value),"0","insert");
            commandGateway.send(command);
            result.put("code", ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }
    private String getCode() {
    	String code = "";
		for (int i = 0; i < 7; i++) {
			int intVal = (int) (Math.random() * 26 + 97);
			code = code + (char) intVal;
		}
		code = code.toUpperCase();
    	return code;
    }
    public Map<String,Object> updateStaticParam(String id,String param, String value){
        Map<String,Object> result = new HashedMap();
        try {
        	String code = getCode();
            StaticParamCommand command = new StaticParamCommand(Integer.valueOf(id),param,new BigDecimal(value),code ,"update");
            commandGateway.send(command);
            result.put("code", ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    public Map<String,Object> deleteStaticParam(String id){
        Map<String,Object> result = new HashedMap();
        try {
            StaticParamCommand command = new StaticParamCommand(Integer.valueOf(id),"",new BigDecimal(0), "", "delete");
            commandGateway.send(command);
            result.put("code", ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

}
