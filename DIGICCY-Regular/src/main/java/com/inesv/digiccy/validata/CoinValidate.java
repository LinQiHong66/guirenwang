package com.inesv.digiccy.validata;

import com.alibaba.fastjson.JSON;
import com.inesv.digiccy.api.command.RmbRechargeCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CurpriceDto;
import com.inesv.digiccy.dto.DealDetailDto;
import com.inesv.digiccy.dto.RmbRechargeDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserBalanceViewDto;
import com.inesv.digiccy.query.QueryRmbRechargeInfo;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import com.inesv.digiccy.query.coin.QueryCoin;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yc on 2016/12/9 0009.
 */
@Component
public class CoinValidate {

    @Autowired
    QueryCoin querycoin;
 

    @Autowired
    private CommandGateway commandGateway;

/**
 * 根据no查询coin
 */
public Map<String, Object> getInfoByNo(int coinNo){
	List<CoinDto> dtos = querycoin.queryCoinTypeByCoinNo(coinNo);
	HashMap<String, Object> map = new HashMap<>();
	if(dtos.size() > 0){
        map.put("code", ResponseCode.SUCCESS);
		map.put("desc", ResponseCode.SUCCESS_DESC);
		map.put("result", dtos.get(0));
	}else{
		map.put("code", ResponseCode.FAIL);
		map.put("desc",ResponseCode.FAIL_DESC);
	}
	return map;
}
    /**
     * 资产记录
     * @return
     */
    public Map<String,Object> validateBalanceBecord(Integer userNo){
        Map<String,Object> map = new HashMap();
        List<UserBalanceViewDto> balanceViews=new ArrayList<UserBalanceViewDto>();
        //人民币资产
        List<UserBalanceDto> rmbBalances = querycoin.queryUserBalance(userNo,0); //0人民币
         
        //其他币资产
        List<UserBalanceDto> coinBalances = querycoin.queryUserBalance(userNo,1); //1其他币
        
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        
        if(rmbBalances.size()!=0){
        	  BigDecimal enable = rmbBalances.get(0).getEnable_coin();
        	  BigDecimal unable =  rmbBalances.get(0).getUnable_coin();
        	  BigDecimal rmb = enable.add(unable); //人民币总资产
        	  
        	  List<CoinDto> coindtos = querycoin.queryCoinTypeByCoinNo(rmbBalances.get(0).getCoin_type()); //获取币种名称 
        	  
        	  UserBalanceViewDto bv=new UserBalanceViewDto();
        	  bv.setDate(rmbBalances.get(0).getDate() == null? null : sf.format(rmbBalances.get(0).getDate()));
        	  bv.setCoin(coindtos.get(0).getCoin_name());
        	  bv.setEnable_coin(rmbBalances.get(0).getEnable_coin());
        	  bv.setUnable_coin(rmbBalances.get(0).getUnable_coin());
        	  bv.setTotal_price(rmb);
              System.out.println(bv.toString());
        	  balanceViews.add(bv);
        }       
        for(UserBalanceDto b:coinBalances){
        	 List<CoinDto> coindtos = querycoin.queryCoinTypeByCoinNo(b.getCoin_type()); //获取币种名称 
        	double rmbdouble = querycoin.queryRmb(userNo,b.getCoin_type());
        	 BigDecimal rmb =new BigDecimal(rmbdouble);  //折合成人民币
        	  UserBalanceViewDto bv=new UserBalanceViewDto();
        	  bv.setDate(b==null?null:sf.format(b.getDate()));
        	  bv.setCoin(coindtos.get(0).getCoin_name());
        	  bv.setEnable_coin(b.getEnable_coin());
        	  bv.setUnable_coin(b.getUnable_coin());
        	  bv.setTotal_price(rmb);
        	  bv.setCoinCode(coindtos.get(0).getCoin_core());
        	  balanceViews.add(bv);
        }
        map.put("balance", balanceViews);
		return map;
 }

    /**
     * 总资产（折合成人民币），人民币资产,其他币资产
     * @return
     */
    public Map<String,Object> validateBalanceInfo(Integer userNo){
        Map<String,Object> map = new HashMap();
        
        //人民币资产
        List<UserBalanceDto> rmbBalances = querycoin.queryUserBalance(userNo,0); //0人民币
        
        //获取总资产
        List<CoinDto> coinlist = querycoin.queryAllCoinInfo();  
        double sumrmb=0d;
        for(CoinDto coin:coinlist){ 
        	if(coin.getCoin_no()!=0){ //其他币资产
        	CurpriceDto curprice = querycoin.getcurPrice(userNo, coin.getCoin_no());
        	    sumrmb+=curprice.getSumcurprice();
        	}else{   //人民 币资产
        		sumrmb+=rmbBalances.get(0).getTotal_price().doubleValue();
        	}
        }
 
        //其他币资产
        List<UserBalanceDto> coinBalances = querycoin.queryUserBalance(userNo,1); //1其他币
        for(UserBalanceDto balance: coinBalances)
        {
        	//币种名称，代号
        	List<CoinDto> coins =  querycoin.queryCoinTypeByCoinNo(balance.getCoin_type());
        	CoinDto coin=coins.get(0);
        	balance.setCoinName(coin.getCoin_name());
        	balance.setCoinCode(coin.getCoin_core());
        	//当前市价
        
        	CurpriceDto curprice = querycoin.getcurPrice(userNo, balance.getCoin_type());
        	System.out.println("coin:"+balance.getCoin_type()+",curprice:"+curprice.getCurprice()+",Sumcurprice:"+curprice.getSumcurprice());
        	balance.setCurPrice(curprice.getCurprice());
        	 //总市价
        	balance.setSumCurPrice(curprice.getSumcurprice());
        }
        
		map.put("sumrmb", sumrmb);
		map.put("rmbBalances", rmbBalances);
		map.put("coinBalances", coinBalances);
        map.put("code", ResponseCode.SUCCESS);
		map.put("desc", ResponseCode.SUCCESS_DESC);

		return map;
 }
    
    
 
    
    /**
     * 获取虚拟币交易信息
     */
    public Map<String,Object> validateCoinTrateInfo(){
        Map<String,Object> resultmap = new HashMap();
        List<Map<Object,Object>> coins=new ArrayList<>();
        List<CoinDto> cointypes = querycoin.queryAllCoinInfo(); //所有币种
        Double ps=new Double(0); //涨跌幅
        for(CoinDto cointype : cointypes)
        {
            Map<Object, Object> date0 = querycoin.queryOpenClose(0,cointype.getCoin_no()); //获取该币今天开盘\收盘价
        	Map<Object, Object> date1 = querycoin.queryOpenClose(1,cointype.getCoin_no()); //获取该币昨天开盘\收盘价
            //Map<Object, Object> date2 = querycoin.queryOpenClose(2,cointype.getCoin_no()); //获取该币前天开盘\收盘价
        	Map<Object, Object> pricesSums=querycoin.queryPriceSum(cointype.getCoin_no()); ////24小时成交额\量
        	BigDecimal today=new BigDecimal(date0.get("closePrice").toString());  //今天收盘价
        	BigDecimal yestoday=new BigDecimal(date1.get("closePrice").toString()); //昨天收盘价
        	 
        	ps = (today.doubleValue()-yestoday.doubleValue())/yestoday.doubleValue(); 
            if(yestoday.doubleValue()==0)
            {
            	ps=0d;
            }
        	
            //3天交易波動
            String treed="[";
            List<Double> deals = querycoin.getThressTrate(cointype.getCoin_no());
            for(int i=0;i<deals.size();i++){
            	treed+="["+i+","+deals.get(i)+"],";
            	if((deals.size()-1)==i){
            		treed=treed.substring(0,treed.length()-1);
            		treed+="]";
            	}
            }
            
/*        	BigDecimal [][] trend = new BigDecimal [6][2]; //存放价格趋势
        	//前天
        	trend[0][0]=new BigDecimal(0);
        	trend[0][1]= (BigDecimal) date2.get("openPrice");
        	trend[1][0]=new BigDecimal(1);
        	trend[1][1]= (BigDecimal) date2.get("closePrice");
        	//昨天
        	trend[2][0]=new BigDecimal(2);
        	trend[2][1]= (BigDecimal) date1.get("openPrice");
        	trend[3][0]=new BigDecimal(3);
        	trend[3][1]= (BigDecimal) date1.get("closePrice");
        	//今天
        	trend[4][0]=new BigDecimal(4);;
        	trend[4][1]= (BigDecimal) date0.get("openPrice");
        	trend[5][0]=new BigDecimal(5);;
        	trend[5][1]= (BigDecimal) date0.get("closePrice");*/
            
            //最新交易价
            DealDetailDto deal = querycoin.querynewPrice(cointype.getCoin_no());
            System.out.println("<<<<<<<<<<<<<<<<< "+deal);
            double newprice = 0d;
            if(deal==null||deal.getDeal_price()==null){
            	newprice = 0d; 
            }else{
            	newprice = deal.getDeal_price().doubleValue();
            }
        	
        	 Map<Object,Object> map = new HashMap();
        	 map.put("name",cointype.getCoin_name());
        	 map.put("price",newprice); //最新成交价
        	 map.put("allprice",pricesSums.get("prices"));     //24小时成交额
        	 map.put("num", pricesSums.get("sums"));         //24小时成交量
        	 map.put("ps", ps);                     //涨跌
        	 map.put("img", cointype.getIcon());   //货币图标
        	 map.put("data",JSON.parseArray(treed));                //3天价格趋势
             map.put("cointype",cointype.getCoin_no());  //幣種
        	 coins.add(map);
        }
        
 
        if(!cointypes.isEmpty()){
            
        	resultmap.put("data",coins);
        	resultmap.put("code", ResponseCode.SUCCESS);
        	resultmap.put("desc", ResponseCode.SUCCESS_DESC);
        }else{
        	resultmap.put("code", ResponseCode.FAIL);
        	resultmap.put("desc",ResponseCode.FAIL_DESC);
        }
        return resultmap;
    }

    /**
     *幣種列表
     */
    public Map<String,Object> validateCoinTypeInfo(){
        Map<String,Object> map = new HashMap();
        List<CoinDto> cointypes = querycoin.queryAllCoinInfo();
        
        if(!cointypes.isEmpty()){
            
            map.put("data",cointypes);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }else{
            map.put("code", ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
        }
        return map;
    }
 

}
