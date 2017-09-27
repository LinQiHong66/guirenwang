package com.inesv.digiccy.validata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.CoinLevelProportionCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinAndCoinProportion;
import com.inesv.digiccy.dto.CoinLevelProportionDto;
import com.inesv.digiccy.query.QueryCoinLevelProportion;

@Component
public class CoinLevelProportionValidata {
	
	@Autowired
	CommandGateway commandGateway;
	
	@Autowired
	QueryCoinLevelProportion query;
	
	public Map<String,Object> queryAll(){
		Map<String,Object> map = new HashMap<String, Object>();
		List<CoinAndCoinProportion> list = query.queryCoinLevel();
		if(list.size() != 0){
			System.out.println("++++++++++++++++++" + list.size());
			map.put("total", list.size());
			map.put("data", list);
			map.put("code",ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}else{
			map.put("code", ResponseCode.FAIL_COIN_LEVEL_PROPORTION_INFO_CODE);
			map.put("code", ResponseCode.FAIL_COIN_LEVEL_PROPORTION_INFO_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> queryByCionNo(String coin_no){
		Map<String,Object> map = new HashMap<String, Object>();
		CoinLevelProportionDto coinLevelProportionDto = new CoinLevelProportionDto();
		coinLevelProportionDto = query.queryByCoinNo(Long.valueOf(coin_no));
		if(coinLevelProportionDto != null){
			map.put("dto", coinLevelProportionDto);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}else{
			map.put("code", ResponseCode.FAIL_COIN_LEVEL_PROPORTION_COINNO_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_COIN_LEVEL_PROPORTION_COINNO_INFO_CODE_INFO_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> insert(Long coin_no,BigDecimal level_one,BigDecimal level_two,BigDecimal level_three){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			CoinLevelProportionCommand command = new CoinLevelProportionCommand(0L, coin_no, level_one, level_two, level_three, "insert");
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL_COIN_LEVEL_PROPORTION_INSERT_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_COIN_LEVEL_PROPORTION_INSERT_INFO_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> updateLevelByCoinNO(Long id,BigDecimal level_one,BigDecimal level_two,BigDecimal level_three){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			CoinLevelProportionCommand command = new CoinLevelProportionCommand(id,0L, level_one, level_two,
					level_three, "updateLevelByCoinNo");
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL_COIN_LEVEL_PROPORTION_UPDATE_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_COIN_LEVEL_PROPORTION_UPDATE_INFO_CODE_DESC);
		}
		return map;
	}
}
