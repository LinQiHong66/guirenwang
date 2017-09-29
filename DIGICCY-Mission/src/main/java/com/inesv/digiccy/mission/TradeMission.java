package com.inesv.digiccy.mission;

import com.inesv.digiccy.validata.RmbRechargeValidate;
import com.inesv.digiccy.validata.TradeValidata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class TradeMission implements Runnable{
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeMission.class);

    @Autowired
    private RmbRechargeValidate rmbRechargeValidate;
    
    /**
     * 定时处理交易（每分钟执行一次）
     *//*
 	@Override
	@Scheduled(cron="0 0/2 * * * ?") 
 	
	public void run() {
		tradeValidata.doTrade();
		LOGGER.info("**********************定时器处理买卖成功**********************************:"+new Date());

 	} */
 	 /*
 	  *  定时处理充值单号
 	  */
 	/*@Scheduled(cron="0/5 * * * * ?")  //1分钟               
*/ 	public void run() {
 		/*LOGGER.info("=====================定时器处理充值单号成功======================:"+new Date());
 		rmbRechargeValidate.validateRechargeInfo();*/
 	} 
    
}
