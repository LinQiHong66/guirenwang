package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.dto.CoinDetailDto;
import com.inesv.digiccy.dto.InesvDayMarket;
import com.inesv.digiccy.dto.KDealDetailDto;
import com.inesv.digiccy.query.QueryDealDetailInfo;
import com.inesv.digiccy.dto.DealDetailDto;
import com.inesv.digiccy.dto.EntrustDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 每日行情
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dealDetail")
public class DealDetailController {
    @Autowired
    private QueryDealDetailInfo queryDealDetailInfo;

    /**
     * 获取某种货币的每日行情
     * @param detailType(1：1分钟，2：3分钟，3：5分钟，4：15分钟，5：30分钟，6：1小时，7：2小时，8：4小时，9：6小时，10：12小时，11：每天)
     * @param detailType("1min"：1分钟，"3min"：3分钟，"5min"：5分钟，"15min"：15分钟，"30min"：30分钟，"1hour"：1小时，"2hour"：2小时，"4hour"：4小时，"6hour"：6小时，"12hour"：12小时，"1day"：每天)
     * @return
     */
    @RequestMapping(value ="/getDealDetail",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getDealDetail(String priceType,String detailType){
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> datasMap = new HashMap<String,Object>();
        String type=getCoinDetailPriceType(detailType);
        List<KDealDetailDto> list = queryDealDetailInfo.queryDealDetailInfoByType(priceType,type);
        //判断当前时间段是否有数据
        KDealDetailDto minuteKDealDetailDto = queryDealDetailInfo.queryMinuteDealDetailInfoByType(priceType,type);
        //手动加入最后一条数据
        if(minuteKDealDetailDto==null){
        	KDealDetailDto kDealDetailDto = queryDealDetailInfo.queryEndDealDetailInfoByType(priceType,type);
        	if(kDealDetailDto!=null){
        		list.add(0, kDealDetailDto);
        	}
        }
        String[][] kDealDetailDtoList = new String[list.size()][6];
        for(int i=0;i<list.size();i++){
        	for(int j=0;j<6;j++){
        		if(j==0){
        			kDealDetailDtoList[i][j] = list.get(i).getEnd_date_num();
        		}if(j==1){
        			kDealDetailDtoList[i][j] = list.get(i).getBegin_price().toString();
        		}if(j==2){
        			kDealDetailDtoList[i][j] = list.get(i).getMax_price().toString();
        		}if(j==3){
        			kDealDetailDtoList[i][j] = list.get(i).getMin_price().toString();
        		}if(j==4){
        			kDealDetailDtoList[i][j] = list.get(i).getEnd_price().toString();
        		}if(j==5){
        			kDealDetailDtoList[i][j] = list.get(i).getDeal_num().toString();
        		}
        	}
        }
        datasMap.put("DSCCNY", 1);
        datasMap.put("contractUnit", "DSC");
        datasMap.put("data", kDealDetailDtoList);
        if(!list.isEmpty()){
        	map.put("code", ResponseCode.SUCCESS);
            map.put("msg",ResponseCode.SUCCESS_DESC);
            map.put("dealDetailList",datasMap);
        }else{
        	map.put("code", ResponseCode.FAIL);
            map.put("msg",ResponseCode.FAIL_DESC);
        }
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
    
    /**
     * 获取某种货币的最新状况
     * @param priceType
     * @return
     */
    @RequestMapping(value ="/getCoinDetail",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getCoinDetail(String priceType){
        Map<String,Object> map = new HashMap<String,Object>();

        CoinDetailDto coin = queryDealDetailInfo.queryCoinDetailInfo(priceType);
        CoinDetailDto coinDto = queryDealDetailInfo.queryCoinDetailInfoDynamic(priceType);
        if(coin == null){
        	coin = queryDealDetailInfo.queryCoinDetailInfo(priceType);
        }
        if(coinDto == null){
        	coinDto = queryDealDetailInfo.queryCoinDetailInfoDynamic(priceType);
        }
        	if(coin.getNewPrice().doubleValue()==Double.valueOf(coinDto.getAttr1())){
        		coin.setAttr1("0");//相等
        	}else if(coin.getNewPrice().doubleValue()>Double.valueOf(coinDto.getAttr1())){
        		coin.setAttr1("1");//大于
        	}else if(coin.getNewPrice().doubleValue()<Double.valueOf(coinDto.getAttr1())){
        		coin.setAttr1("2");//小于
        	}
        if(priceType!=null){
        	if(coin!=null){
        		map.put("code", ResponseCode.SUCCESS);
            	map.put("msg",ResponseCode.SUCCESS_DESC);
            	map.put("dealDetailList",coin);
        	}else{
        		map.put("code", ResponseCode.FAIL);
        		map.put("msg",ResponseCode.FAIL_DESC);
        	}
        }else{
        	map.put("code", ResponseCode.FAIL);
            map.put("msg",ResponseCode.FAIL_DESC);
        }
        return map;
    }
    
    /**
     * 根据币种显示交易记录
     * */
    @ResponseBody
    @RequestMapping(value="/queryDealDetailByCoinno",method = RequestMethod.POST)
    public Map<String, Object> queryDealDetailByCoin_no(String coin_no,String deal_type,String limit){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<DealDetailDto> dealDetailDtos = queryDealDetailInfo.queryDealDetailByCoinNo(Integer.valueOf(coin_no),Integer.valueOf(deal_type),Integer.valueOf(limit));
    	 if(!dealDetailDtos.isEmpty()){
         	map.put("code", ResponseCode.SUCCESS);
             map.put("msg",ResponseCode.SUCCESS_DESC);
             map.put("dealDetailList",dealDetailDtos);
         }else{
         	map.put("code", ResponseCode.FAIL);
             map.put("msg",ResponseCode.FAIL_DESC);
         }
    	 return map;
    }
    /*
     * 根据币种查询委托记录
     */
    @ResponseBody
    @RequestMapping(value="/queryEntrustByCoinno",method = RequestMethod.POST)
    public Map<String, Object> queryEntrustByCoin_no(String coin_no,String deal_type,String limit){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<EntrustDto> dealDetailDtos = queryDealDetailInfo.queryEntrustByCoinNo(Integer.valueOf(coin_no),Integer.valueOf(deal_type),Integer.valueOf(limit));
        	map.put("code", ResponseCode.SUCCESS);
            map.put("msg",ResponseCode.SUCCESS_DESC);
            map.put("dealDetailList",dealDetailDtos);
    	return map;
    }
    
    /**
     * 根据币种查询最近50条记录
     * 
     */
    @ResponseBody
    @RequestMapping(value="/queryDealDetailByCoinnoList",method = RequestMethod.POST)
    public Map<String, Object> queryDealDetailByCoinnoList(String coin_no){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<DealDetailDto> dealDetailDtos = queryDealDetailInfo.queryDetailByCoinNo(coin_no);
         	map.put("code", ResponseCode.SUCCESS);
            map.put("msg",ResponseCode.SUCCESS_DESC);
            map.put("dealDetailList",dealDetailDtos);
    	return map;
    }
    
}
