package com.inesv.digiccy.validata;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CommandRedDetailDto;
import com.inesv.digiccy.query.QueryCommandRedDetailInfo;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class CommandRedDetailValidata {
	
	@Autowired 
	private QueryCommandRedDetailInfo queryCommandRedDetailInfo;
	
	@Autowired
    private CommandGateway commandGateway;
	
	/**
	 * 取得指定口令紅包信息
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataCommandRedDetailInfo(String user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<CommandRedDetailDto> CommandRedDetailList = queryCommandRedDetailInfo.queryAllCommandRedDetail(user_id);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", CommandRedDetailList);
		}catch (Exception e) {
        	map.put("code", ResponseCode.FAIL_CODE_FALSE);
            map.put("desc", ResponseCode.FAIL_CODE_FALSE_DESC);
            e.printStackTrace();
        }
		return map;
	}
	
	/**
	 * 取得指定口令紅包信息
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataCommandRedDetailInfoById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CommandRedDetailDto commandRedDetail = queryCommandRedDetailInfo.queryAllCommandRedDetailById(id);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", commandRedDetail);
		}catch (Exception e) {
        	map.put("code", ResponseCode.FAIL_CODE_FALSE);
            map.put("desc", ResponseCode.FAIL_CODE_FALSE_DESC);
            e.printStackTrace();
        }
		return map;
	}

}
