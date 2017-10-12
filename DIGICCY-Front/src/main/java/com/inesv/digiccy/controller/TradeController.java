package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.RequestModeType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.validata.TradeValidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Controller
public class TradeController {
	
	@Autowired
    private TradeValidata tradeValidata;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 买卖
	 * @param request
	 * @param buyPrice
	 * @param buyNum
	 * @param buyMum
	 * @param buyPayPassword
	 * @param coinType币的种类
	 * @param type交易类型
	 * @return
	 */
	@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-1",cver = VersionType.V100, name = "交易接口", description = "用户买卖交易操作,添加委托记录接口", model = ModelType.TRADE, dtoClazz = BaseRes.class, reqParams = {
			"userNo", "buyPrice", "buyNum","poundatge", "buyMum", "buyPayPassword",
			"coinType", "type","poundageRate"}, progress = ProgressType.TESTING,requestMode=RequestModeType.POST)
	@AutoDocMethodParam(note = "用户编号@@交易价格@@交易数量@@交易所需手续费@@最大可交易数量@@交易密码@@货币类型@@兑换货币类型@@交易类型(卖:'sell'/买:'buy')@@交易手续费率", name = "userNo@@buyPrice@@buyNum@@poundatge@@buyMum@@buyPayPassword@@coinType@@type@@poundageRate")
	@RequestMapping(value = "/trade/goTrade", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> goBuy(HttpServletRequest request,@RequestParam String userNo,@RequestParam String buyPrice,@RequestParam String buyNum,@RequestParam String poundatge,@RequestParam String buyPayPassword,
    		@RequestParam String coinType,@RequestParam String convertType,@RequestParam String type, @RequestParam String sign){
		Map<String, Object> map = new HashMap<>();
		/*String tradeSign = (String) redisTemplate.opsForValue().get("trade_" + userNo);
		if(tradeSign == null || !tradeSign.equals(sign)) {
			redisTemplate.opsForValue().set("trade_" + userNo, sign, 2, TimeUnit.MINUTES);*/
			return tradeValidata.validateTradeCoinActual(userNo, new BigDecimal(buyNum), new BigDecimal(buyPrice), 
					new BigDecimal(poundatge), buyPayPassword,coinType,convertType,type);
		/*}else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "抱歉，交易正在处理中，请勿重复提交！");
			return map;
		}*/
    }
	
	/**
	 * 买卖
	 * @param request
	 * @param buyPrice
	 * @param buyNum
	 * @param buyMum
	 * @param buyPayPassword
	 * @param coinType币的种类
	 * @param type交易类型
	 * @return
	 */
	/*@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-1",cver = VersionType.V100, name = "交易接口", description = "用户买卖交易操作,添加委托记录接口", model = ModelType.TRADE, dtoClazz = BaseRes.class, reqParams = {
			"userNo", "buyPrice", "buyNum","poundatge", "buyMum", "buyPayPassword",
			"coinType", "type","poundageRate"}, progress = ProgressType.TESTING,requestMode=RequestModeType.POST)
	@AutoDocMethodParam(note = "用户编号@@交易价格@@交易数量@@交易所需手续费@@最大可交易数量@@交易密码@@货币类型@@交易类型(卖:'sell'/买:'buy')@@交易手续费率", name = "userNo@@buyPrice@@buyNum@@poundatge@@buyMum@@buyPayPassword@@coinType@@type@@poundageRate")
	@RequestMapping(value = "/trade/goTradeActual", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> goBuyActual(HttpServletRequest request,@RequestParam String userNo,@RequestParam String buyPrice,@RequestParam String buyNum,@RequestParam String poundatge,@RequestParam String buyPayPassword,@RequestParam String coinType,@RequestParam String type){
        return tradeValidata.validateTradeCoin(userNo, new BigDecimal(buyNum), new BigDecimal(buyPrice),new BigDecimal(poundatge), buyPayPassword,coinType,type);
		return tradeValidata.validateTradeCoinActual(userNo, new BigDecimal(buyNum), new BigDecimal(buyPrice),new BigDecimal(poundatge), buyPayPassword,coinType,type);
	}*/
	
	/**
	 * 撤销交易委托
	 * @param request
	 * @param userNo
	 * @param id
	 * @return
	 * 60 40 5000 100
	 */
	/*@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-1",cver = VersionType.V100, name = "撤销委托", description = "用户撤销交易委托记录操作接口", model = ModelType.TRADE, dtoClazz = BaseRes.class, reqParams = {
			"userNo","id"}, progress = ProgressType.TESTING,requestMode=RequestModeType.POST)
	@AutoDocMethodParam(note = "用户编号@@委托交易id", name = "userNo@@id")*/
	@RequestMapping(value = "/trade/delEntrust", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> delEntrust(HttpServletRequest request,@RequestParam Integer userNo,@RequestParam Long id) throws Exception{
		return tradeValidata.validateDelEntrust(id, userNo);
    }
    
    /**
     * 得到所有的评论
     * @param request
     * @return
     */
	/*@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-1",cver = VersionType.V100, name = "评论", description = "得到所有的评论记录接口", model = ModelType.TRADE, dtoClazz = BaseRes.class,
		progress = ProgressType.TESTING,requestMode=RequestModeType.GET)
	@AutoDocMethodParam(name = "", note = "")*/
    @RequestMapping(value = "/trade/getAssessList",method = RequestMethod.POST)
    public @ResponseBody Map<Object,Object> getAssessList(HttpServletRequest request){
    	return tradeValidata.validataAssessList();
    }
    
    /**
     * 得到交易记录
     * @param request
     * @return
     */
	/*@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-1",cver = VersionType.V100, name = "交易记录", description = "得到某个用户的买卖交易记录接口", model = ModelType.TRADE, dtoClazz = BaseRes.class, reqParams = {
		"userNo"}, progress = ProgressType.TESTING)
	@AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/trade/getDealDetailList",method = RequestMethod.POST)
    public @ResponseBody Map<Object,Object> getDealDetailList(HttpServletRequest request,@RequestParam String userNo){
       return tradeValidata.validataDealDetailListByUserNo(userNo);
    }
    
    /**
     * 得到用户财务
     * @param request
     * @return
     */
	/*@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-1",cver = VersionType.V100, name = "用户财务", description = "得到某个用户的某种货币的财务接口",
			model = ModelType.TRADE, dtoClazz = BaseRes.class, reqParams = {
	"userNo","coinType"}, progress = ProgressType.TESTING)
	@AutoDocMethodParam(note = "用户编号@@货币类型", name = "userNo@@coinType")*/
    @RequestMapping(value = "/trade/getUserBalanceInfo",method = RequestMethod.POST)
    public @ResponseBody Map<Object,Object> getUserBalanceInfo(HttpServletRequest request,String userNo,String coinType){
    	return tradeValidata.validataUserBalanceInfoByUserNoAndCoinType(userNo, coinType);
    }
    
    /**
     * 交易中心-得到用户财务
     * @param request
     * @return
     */
	/*@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-1",cver = VersionType.V100, name = "用户财务", description = "得到某个用户的某种货币的财务接口",
			model = ModelType.TRADE, dtoClazz = BaseRes.class, reqParams = {
	"userNo","coinType"}, progress = ProgressType.TESTING)
	@AutoDocMethodParam(note = "用户编号@@货币类型", name = "userNo@@coinType")*/
    @RequestMapping(value = "/trade/getUserBalanceInfoByDealDetail",method = RequestMethod.POST)
    public @ResponseBody Map<Object,Object> getUserBalanceInfoByDealDetail(HttpServletRequest request,String userNo,String coinType){
        String coinTypes = "0," + coinType;
    	return tradeValidata.validataUserBalanceInfoByUserNoAndCoinTypeAndDealDetail(userNo, coinTypes);
    }
	
    /**
     * 得到某种货币前15条的买卖委托记录
	 * @param entrustCoin
     * @param request
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-1",cver = VersionType.V100, name = "得到某种货币前15条的买卖委托记录", description = "得到某种货币前15条的买卖委托记录接口", model = ModelType.TRADE, dtoClazz = BaseRes.class,
    		reqParams = {"entrustCoin","entrustType","number"},progress = ProgressType.TESTING,requestMode=RequestModeType.POST)
    @AutoDocMethodParam(note= "货币类型@@交易类型(卖:'1'/买:'0')@@查询的条数", name = "entrustCoin@@entrustType@@number")*/
    @RequestMapping(value = "/trade/getEntrustOfTradeCenter",method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getEntrustOfTradeCenter(Integer entrustCoin,Integer entrustType,Integer number){
        Map<String , Object> map = tradeValidata.validataGetEntrustOfTradeCenter(entrustCoin,entrustType,number);
        return map;
    }
    
    /**
     * 得到该用户前10条未交易的委托记录
     * @param request
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-1",cver = VersionType.V100, name = "得到该用户前10条未交易的委托记录", description = "得到该用户前10条未交易的委托记录接口", model = ModelType.TRADE, dtoClazz = BaseRes.class,
    		reqParams = {"userNo","state","number"},progress = ProgressType.TESTING,requestMode=RequestModeType.POST)
    @AutoDocMethodParam(note= "用户编号@@状态(0:委托中 1：已完成 2：已撤销)@@查询的条数", name = "userNo@@state@@number")*/
    @RequestMapping(value = "/trade/getNotTradeEntrustOfTradeCenter",method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getNotTradeEntrustOfTradeCenter(Integer userNo,Integer state,Integer number){
        Map<String , Object> map = tradeValidata.getNotTradeEntrustOfTradeCenter(userNo,state,number);
        return map;
    }
    
    /**
     * 得到静态参数手续费
     * @return
     */
    @AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-1",cver = VersionType.V100, name = "得到静态参数手续费", description = "得到静态参数手续费接口", model = ModelType.TRADE, dtoClazz = BaseRes.class,
    		reqParams = {"param"},progress = ProgressType.TESTING,requestMode=RequestModeType.POST)
    @AutoDocMethodParam(note= "静态参数对应param值{poundageRate:交易手续费率 firstRate:第一代返佣比率 secondRate:第二代返佣比率 thirdRate:第三代返佣比率}", name = "param")
    @RequestMapping(value = "/trade/getPiundatgeRate",method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getPoundageRate(String param){
        Map<String , Object> map = tradeValidata.getPoundageRate(param);
        return map;
    }
}
