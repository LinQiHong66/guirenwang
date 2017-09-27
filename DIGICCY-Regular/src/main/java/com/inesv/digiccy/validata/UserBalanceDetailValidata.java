package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.CommandRedCommand;
import com.inesv.digiccy.api.command.CommandRedDetailCommand;
import com.inesv.digiccy.api.command.CrowdFundingCommand;
import com.inesv.digiccy.api.command.RegUserCommand;
import com.inesv.digiccy.api.command.UserBalanceDetailCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CommandRedDetailDto;
import com.inesv.digiccy.dto.CommandRedDto;
import com.inesv.digiccy.dto.CrowdFundingDto;
import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.dto.UserBalanceDetailDto;
import com.inesv.digiccy.query.QueryCommandRedInfo;
import com.inesv.digiccy.query.QueryCrowdFundingInfo;
import com.inesv.digiccy.query.QueryUserBalanceDetailInfo;
import com.inesv.digiccy.util.MD5;

import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class UserBalanceDetailValidata {
	
	@Autowired 
	QueryUserBalanceDetailInfo queryUserBalanceDetailInfo;
	
	@Autowired
    private CommandGateway commandGateway;
	
	/**
	 * 增加口令红包
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataAddDetail(Integer user_no, String admin_no,Integer coin_type, Integer coin_price, String remark){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserBalanceDetailCommand command = new UserBalanceDetailCommand(0L,user_no,admin_no,coin_type,new BigDecimal(coin_price),remark,new Date(),"insertDetail");
	        commandGateway.send(command);
	        map.put("code", ResponseCode.SUCCESS);
	        map.put("desc",ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 取得所有口令红包
	 * @return
	 */
	public Map<String, Object> validataAllDetail() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<UserBalanceDetailDto> detailDtoList = queryUserBalanceDetailInfo.queryAllUserBalanceDetail();
			if (detailDtoList != null) {
				map.put("code", ResponseCode.SUCCESS);
				map.put("desc", ResponseCode.SUCCESS_DESC);
				map.put("total", detailDtoList.size());
				map.put("data", detailDtoList);
			}else {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);
			}
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

}
