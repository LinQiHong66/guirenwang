package com.inesv.digiccy.controller;

import com.inesv.digiccy.dto.KDealDetailDto;
import com.inesv.digiccy.query.QueryDealDetailInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 每日行情
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/trade")
public class TradeDealDetailsController {
    @Autowired
    private QueryDealDetailInfo queryDealDetailInfo;

    /**
     * 获取某种货币的每日行情
     * @param detailType(1：1分钟，2：3分钟，3：5分钟，4：15分钟，5：30分钟，6：1小时，7：2小时，8：4小时，9：6小时，10：12小时，11：每天)
     * @param detailType("1min"：1分钟，"3min"：3分钟，"5min"：5分钟，"15min"：15分钟，"30min"：30分钟，"1hour"：1小时，"2hour"：2小时，"4hour"：4小时，"6hour"：6小时，"12hour"：12小时，"1day"：每天)
     * @return
     */
    @RequestMapping(value ="/kline_h",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> kline_h(String needTickers,String symbol,String type,String size){
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> datasMap = new HashMap<String,Object>();
        type=getCoinDetailPriceType(type);
        //货币类型
        String priceType=symbol;
        List<KDealDetailDto> list = queryDealDetailInfo.queryDealDetailInfoByType(priceType,type);
        Object[][] kDealDetailDtoList = new Object[list.size()][6];
        for(int i=0;i<list.size();i++){
        	for(int j=0;j<6;j++){
        		if(j==0){
        			kDealDetailDtoList[i][j] = Long.valueOf(list.get(i).getEnd_date_num() + "000");
        		}if(j==1){
        			kDealDetailDtoList[i][j] = list.get(i).getBegin_price();
        		}if(j==2){
        			kDealDetailDtoList[i][j] = list.get(i).getMax_price();
        		}if(j==3){
        			kDealDetailDtoList[i][j] = list.get(i).getMin_price();
        		}if(j==4){
        			kDealDetailDtoList[i][j] = list.get(i).getEnd_price();
        		}if(j==5){
        			kDealDetailDtoList[i][j] = Double.valueOf(list.get(i).getDeal_num());
        		}
        	}
        }
        	datasMap.put("DSCCNY", 1);
        	datasMap.put("contractUnit", "DSC");
        	datasMap.put("data", kDealDetailDtoList);
        	map.put("datas",datasMap);
            map.put("des", "");
            map.put("isSuc", true);
            map.put("marketName", "MOVESAY");
            map.put("symbol", symbol);
            map.put("url", "http://localhost");
            map.put("moneyType", "cny");
        return map;
    }
    
    /*
     * 判断detailType类型
     */
    public String getCoinDetailPriceType(String detailType){
    	String type="1";
    	if(detailType.equals("1min")){
    		type="1";
    	}if(detailType.equals("3min")){
    		type="2";
    	}if(detailType.equals("5min")){
    		type="3";
    	}if(detailType.equals("15min")){
    		type="4";
    	}if(detailType.equals("30min")){
    		type="5";
    	}if(detailType.equals("1hour")){
    		type="6";
    	}if(detailType.equals("2hour")){
    		type="7";
    	}if(detailType.equals("4hour")){
    		type="8";
    	}if(detailType.equals("6hour")){
    		type="9";
    	}if(detailType.equals("12hour")){
    		type="10";
    	}if(detailType.equals("1day")){
    		type="11";
    	}
        return type;
    }
    
}
