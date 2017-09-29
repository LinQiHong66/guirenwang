package com.inesv.digiccy.validata;

import java.math.BigDecimal;
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
import com.inesv.digiccy.dto.CoinTranAstrictDto;
import com.inesv.digiccy.query.QueryCoinLevelProportion;

@Component
public class CoinLevelProportionValidata {
	
	@Autowired
	CommandGateway commandGateway;
	
	@Autowired
	QueryCoinLevelProportion query;
	
	public Map<String,Object> queryAll(){
		Map<String,Object> map = new HashMap<String, Object>();
		List<CoinLevelProportionDto> list = query.queryCoinLevel();
		if(list.size() != 0){
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
	
	public Map<String,Object> queryById(String id){
		Map<String,Object> map = new HashMap<String, Object>();
		CoinLevelProportionDto coinLevelProportionDto = new CoinLevelProportionDto();
		coinLevelProportionDto = query.queryById(Long.valueOf(id));
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
	
	public Map<String,Object> insert(CoinLevelProportionDto levelDto){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			CoinLevelProportionCommand command = new CoinLevelProportionCommand(0L, levelDto.getCoin_no(), levelDto.getLevel_one(), levelDto.getLevel_two(),
					levelDto.getLevel_three(), levelDto.getLevel_four(), levelDto.getLevel_five(), levelDto.getLevel_type(), levelDto.getState(), "", "", "insert");
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL_COIN_LEVEL_PROPORTION_INSERT_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_COIN_LEVEL_PROPORTION_INSERT_INFO_CODE_DESC);
		}
		return map;
	}
	
	public Map<String,Object> updateLevelById(CoinLevelProportionDto levelDto){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			CoinLevelProportionCommand command = new CoinLevelProportionCommand(levelDto.getId(),levelDto.getCoin_no(), levelDto.getLevel_one(), levelDto.getLevel_two(),
					levelDto.getLevel_three(), levelDto.getLevel_four(), levelDto.getLevel_five(), levelDto.getLevel_type(), levelDto.getState(), "", "", "update");
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
	
	public Map<String,Object> delete(String id){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			CoinLevelProportionCommand command = new CoinLevelProportionCommand(Long.valueOf(id),0L,new BigDecimal(0), new BigDecimal(0), new BigDecimal(0),
					new BigDecimal(0), new BigDecimal(0), 0, 2, "", "", "delete");
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL_COIN_LEVEL_PROPORTION_UPDATE_INFO_CODE);
			map.put("desc", "删除失败！");
		}
		return map;
	}
	
	/**
     * 校验虚拟货币列表
     * @return
     */
    public CoinLevelProportionDto getByCoin_no(Long coin_no){
    	return query.queryByCoinNo(Long.valueOf(coin_no));
    }
}
