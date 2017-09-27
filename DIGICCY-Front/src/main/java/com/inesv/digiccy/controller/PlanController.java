package com.inesv.digiccy.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.PlanValidata;
import com.inesv.digiccy.validata.coin.CoinValidata;

@Controller
@RequestMapping("/Plan")
public class PlanController {
	
	@Autowired
	PlanValidata planValidata;
	
	@Autowired
	CoinValidata coinvalidata;
	
	/**
	 * 计划记录列表
	 * */
	@ResponseBody
	@RequestMapping(value="/planALL",method = RequestMethod.POST)
	public Map<String, Object> getPlanAll(){
		Map<String, Object> map = planValidata.queryPlans();
		return map;
	}
	
	/**
	 * 买卖计划
	 * */
	@ResponseBody
	@RequestMapping(value="/planByBuyorSell",method = RequestMethod.POST)
	public Map<String, Object> insertPlan(String user_id,String bill_id,String plan_type,String top_money_start,String top_money_stop,String low_money_start,
			String low_money_stop,String plan_status,String plan_money,String tradePassword){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String fordate = formatter.format(date);
		Timestamp plan_time = Timestamp.valueOf(fordate);
		Map<String, Object> map = planValidata.insert(Integer.valueOf(user_id), Integer.valueOf(bill_id), plan_type, new BigDecimal(top_money_start), 
				new BigDecimal(top_money_stop), new BigDecimal(low_money_start), new BigDecimal(low_money_stop),
				Integer.valueOf(plan_status), plan_time, null, new BigDecimal(plan_money), "insert",tradePassword);
		return map;
	}
	
	/**
	 * 取消计划
	 * */
	@ResponseBody
	@RequestMapping(value="/updateOver",method = RequestMethod.POST)
	public Map<String, Object> updateOver(String id){
		Map<String, Object> map = planValidata.updateOver(Long.valueOf(id));
		return map;
	}
	
	/**
	 * 客户对应的币种委托计划记录钱30条
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryPlanByUserAndConin",method = RequestMethod.POST)
	public Map<String,Object> queryPlanByUserAndConin(String user_id,String bill_id){
		Map<String,Object> map = new HashMap<String, Object>();
		map = planValidata.queryPlanLimit(Long.valueOf(user_id), Long.valueOf(bill_id));
		return map;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/querySellPoundatge",method = RequestMethod.POST)
	public Map<String,Object> querySellPoundatge1(String coin_no){
		Map<String,Object> map = new HashMap<String, Object>();
		map = coinvalidata.querySellPoundatge1(Long.valueOf(coin_no));
		return map;
		
	}
	@ResponseBody
	@RequestMapping(value = "/queryBuyPoundatge",method = RequestMethod.POST)
	public Map<String,Object> queryBuyPoundatge1(String coin_no){
		Map<String,Object> map = new HashMap<String, Object>();
		map = coinvalidata.queryBuyPoundatge1(Long.valueOf(coin_no));
		return map;
		
	}
}
