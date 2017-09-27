package com.inesv.digiccy.validata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.BuyEntrustDepthDto;
import com.inesv.digiccy.query.QueryEntrustInfo;

@Component
public class BuyEntrustDepthValidata {
	
	@Autowired
	QueryEntrustInfo queryEntrustInfo;
	
	public Map<String,Object> queryEntrustByEntrustCoinOrderBy(Integer entrust_coin){
		Map<String,Object> map = new HashMap<String,Object>();
		List<BuyEntrustDepthDto> buy_list = new ArrayList<BuyEntrustDepthDto>();
		List<BuyEntrustDepthDto> sell_list = new ArrayList<BuyEntrustDepthDto>();
		buy_list = queryEntrustInfo.queryBuyEntrustByEntrustCoinOrderBy(entrust_coin);
		sell_list = queryEntrustInfo.querySellEntrustByEntrustCoinOrderBy(entrust_coin);
		if(buy_list != null || buy_list.size() != 0 || sell_list != null || sell_list.size() != 0){
			map.put("buy_list", buy_list);//深度图买的记录
			map.put("sell_list", sell_list);//深度图卖的记录
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}else{
			map.put("code", ResponseCode.FAIL_BUY_ENTRUST_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_BUY_ENTRUST_INFO_CODE_DESC);
		}
		return map;
	}
}
