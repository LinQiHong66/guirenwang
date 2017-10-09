package com.inesv.digiccy.event.handler;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;

import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.dto.StaticParamsDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.event.EntrustEvent;
import com.inesv.digiccy.persistence.plan.PlanOperation;
import com.inesv.digiccy.persistence.trade.TradePersistence;
import com.inesv.digiccy.query.QueryStaticParam;
import com.inesv.digiccy.query.QueryUserBalanceInfo;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JimJim on 2016/11/9 0009.
 */
public class TradeEventHandler {

    @Autowired
    TradePersistence EntrustPersistence;
    @Autowired
    QueryUserBalanceInfo queryUserBalanceInfo;
    @Autowired
    QueryStaticParam queryStaticParam;
    @Autowired
    PlanOperation planOperation;

	@EventHandler
	public void handle(EntrustEvent event) throws Exception {
		EntrustDto entrust = new EntrustDto(event.getId(),event.getUser_no(),
				event.getEntrust_coin(), event.getConvert_coin(),
				event.getConvert_price(),event.getEntrust_type(),
				event.getEntrust_price(), event.getEntrust_num(),
				event.getDeal_num(), event.getPiundatge(), event.getState(),
				event.getDate());
		String operation = event.getOperation();
		switch (operation) {
		/*case "insert":
			insertOp(entrust);
			break;*/
		case "inserts":
			insertOps_convert(entrust);
			//insertOps(entrust);
			break;
		case "update":
			updateOp(entrust);
			break;
		case "confirm":
			EntrustPersistence.confirmEntrust(entrust);
			break;
		/*case "delete":
			EntrustPersistence.deleteEntrust(entrust);
			break;*/
		default:
			break;
		}
	}
	
	/**
	 * 添加操作：买卖操作
	 * @param event
	 * @param Entrust
	 * @throws SQLException 
	 * @throws Exception
	 */
	/*private void insertOp(EntrustDto entrust) throws Exception{
		//虚拟币	
		UserBalanceDto xnb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(entrust.getUser_no().toString(), entrust.getEntrust_coin().toString());
		//人民币
		UserBalanceDto rmb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(entrust.getUser_no().toString(), "0");
		if(entrust.getEntrust_type().equals(0)){//买    
			//xnb.setUnable_coin(xnb.getUnable_coin().add(entrust.getEntrust_num()));
			rmb.setEnable_coin(rmb.getEnable_coin().subtract((entrust.getEntrust_price().multiply(entrust.getEntrust_num())).add(entrust.getPiundatge())));
			rmb.setUnable_coin(rmb.getUnable_coin().add(entrust.getEntrust_price().multiply(entrust.getEntrust_num())).add(entrust.getPiundatge()));
		}
		if(entrust.getEntrust_type().equals(1)){//卖
			xnb.setUnable_coin(xnb.getUnable_coin().add(entrust.getEntrust_num()));
			xnb.setEnable_coin(xnb.getEnable_coin().subtract(entrust.getEntrust_num()));
			rmb.setEnable_coin(rmb.getEnable_coin().subtract(entrust.getPiundatge()));
			rmb.setUnable_coin(rmb.getUnable_coin().add(entrust.getPiundatge()));
		}
		EntrustPersistence.addEntrust(entrust,xnb,rmb);
	}*/
	
	/**
	 * 人民币-买卖操作
	 * @param event
	 * @param Entrust
	 * @throws SQLException 
	 * @throws Exception
	 */
	private void insertOps(EntrustDto entrust) throws Exception{
		//虚拟币	
		UserBalanceDto xnb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(entrust.getUser_no().toString(), entrust.getEntrust_coin().toString());
		//人民币
		UserBalanceDto rmb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(entrust.getUser_no().toString(), "0");
		if(String.valueOf(entrust.getEntrust_type()).equals("0")){//买   
			if(rmb.getEnable_coin().doubleValue() - (entrust.getEntrust_price().multiply(entrust.getEntrust_num())).doubleValue() < 0) { //人民币余额不足
				int numException = 1/0;
			}
			rmb.setEnable_coin(rmb.getEnable_coin().subtract((entrust.getEntrust_price().multiply(entrust.getEntrust_num()))));
			rmb.setUnable_coin(rmb.getUnable_coin().add(entrust.getEntrust_price().multiply(entrust.getEntrust_num())));
		}
		if(String.valueOf(entrust.getEntrust_type()).equals("1")){//卖
			if(xnb.getEnable_coin().doubleValue() - entrust.getEntrust_num().doubleValue() < 0){//虚拟币余额不足
				int numException = 1/0;
			}
			xnb.setUnable_coin(xnb.getUnable_coin().add(entrust.getEntrust_num()));
			xnb.setEnable_coin(xnb.getEnable_coin().subtract(entrust.getEntrust_num()));
		}
		EntrustPersistence.addEntrustActual(entrust,xnb,rmb);
	}
	
	/**
	 * 虚拟币-买卖操作
	 * @param event
	 * @param Entrust
	 * @throws SQLException 
	 * @throws Exception
	 */
	private void insertOps_convert(EntrustDto entrust) throws Exception{
		//虚拟币	
		UserBalanceDto xnb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(entrust.getUser_no().toString(), entrust.getEntrust_coin().toString());
		//人民币
		UserBalanceDto rmb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(entrust.getUser_no().toString(), entrust.getConvert_coin().toString());
		if(String.valueOf(entrust.getEntrust_type()).equals("0")){//买 
			if(entrust.getConvert_coin() == 0) {
				if(rmb.getEnable_coin().doubleValue() < (entrust.getEntrust_price().multiply(entrust.getEntrust_num())).doubleValue()) { //人民币余额不足
					int numException = 1/0;
				}
				rmb.setEnable_coin(rmb.getEnable_coin().subtract((entrust.getEntrust_price().multiply(entrust.getEntrust_num()))));
				rmb.setUnable_coin(rmb.getUnable_coin().add(entrust.getEntrust_price().multiply(entrust.getEntrust_num())));
			}else {
				if(entrust.getConvert_price().doubleValue() * rmb.getEnable_coin().doubleValue() < (entrust.getEntrust_price().multiply(entrust.getEntrust_num())).doubleValue()) {
					int numException = 1/0;
				}
				double enable_coin = rmb.getEnable_coin().doubleValue()-(entrust.getEntrust_price().multiply(entrust.getEntrust_num())).doubleValue() / entrust.getConvert_price().doubleValue();
				double unable_coin = rmb.getUnable_coin().doubleValue()+(entrust.getEntrust_price().multiply(entrust.getEntrust_num())).doubleValue() / entrust.getConvert_price().doubleValue();
				BigDecimal en_bg = new BigDecimal(enable_coin);  
				BigDecimal un_bg = new BigDecimal(unable_coin);  
				rmb.setEnable_coin(new BigDecimal(en_bg.setScale(6,BigDecimal.ROUND_DOWN).toString()));
				rmb.setUnable_coin(new BigDecimal(un_bg.setScale(6,BigDecimal.ROUND_DOWN).toString()));
			}
		}
		if(String.valueOf(entrust.getEntrust_type()).equals("1")){//卖
			if(xnb.getEnable_coin().doubleValue() - entrust.getEntrust_num().doubleValue() < 0){//虚拟币余额不足
				int numException = 1/0;
			}
			xnb.setUnable_coin(xnb.getUnable_coin().add(entrust.getEntrust_num()));
			xnb.setEnable_coin(xnb.getEnable_coin().subtract(entrust.getEntrust_num()));
		}
		EntrustPersistence.addEntrustActual(entrust,xnb,rmb);
	}
	
	/**
	 * 人民币-买卖操作
	 * @param event
	 * @param Entrust
	 * @throws SQLException 
	 * @throws Exception
	 */
	private void updateOp(EntrustDto entrust) throws SQLException{
		//人民币
		UserBalanceDto xnb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(entrust.getUser_no().toString(), entrust.getEntrust_coin().toString());
		//虚拟币
		UserBalanceDto rmb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(entrust.getUser_no().toString(),"0");
		/*if(entrust.getEntrust_type().equals(0)){//买
			//xnb.setUnable_coin(xnb.getUnable_coin().subtract(entrust.getEntrust_num()));
			rmb.setEnable_coin(rmb.getEnable_coin().add(entrust.getEntrust_price().multiply(entrust.getEntrust_num())).add(entrust.getPiundatge()));
			rmb.setUnable_coin(rmb.getUnable_coin().subtract(entrust.getEntrust_price().multiply(entrust.getEntrust_num())).add(entrust.getPiundatge()));
		}
		if(entrust.getEntrust_type().equals(1)){//卖
			xnb.setUnable_coin(xnb.getUnable_coin().subtract(entrust.getEntrust_num()));
			xnb.setEnable_coin(xnb.getEnable_coin().add(entrust.getEntrust_num()));
			rmb.setEnable_coin(rmb.getEnable_coin().add(entrust.getPiundatge()));
			rmb.setUnable_coin(rmb.getUnable_coin().subtract(entrust.getPiundatge()));
		}*/
		//手续费比率
		StaticParamsDto staticParams=queryStaticParam.getStaticParamByParam("poundageRate");
		BigDecimal poundatgeRate=staticParams.getValue();
		if(entrust.getEntrust_type().equals(0)){//买
			//xnb.setUnable_coin(xnb.getUnable_coin().subtract(entrust.getEntrust_num()));
			BigDecimal returnrmb=poundatgeRate.multiply(entrust.getEntrust_price().multiply(entrust.getEntrust_num().subtract(entrust.getDeal_num())));
			rmb.setEnable_coin(rmb.getEnable_coin().add(returnrmb));
			rmb.setUnable_coin(rmb.getUnable_coin().subtract(returnrmb));
		}
		if(entrust.getEntrust_type().equals(1)){//卖
			BigDecimal returnpound=(entrust.getEntrust_num().subtract(entrust.getDeal_num())).multiply(entrust.getEntrust_price()).multiply(poundatgeRate);
			xnb.setUnable_coin(xnb.getUnable_coin().subtract(entrust.getEntrust_num().subtract(entrust.getDeal_num())));
			xnb.setEnable_coin(xnb.getEnable_coin().add(entrust.getEntrust_num().subtract(entrust.getDeal_num())));
			rmb.setEnable_coin(rmb.getEnable_coin().add(returnpound));
			rmb.setUnable_coin(rmb.getUnable_coin().subtract(returnpound));
		}
		entrust.setState(2);
		EntrustPersistence.updateEntrust(entrust,xnb,rmb);
	}
	
	/**
	 * 币对币-买卖操作
	 * @param event
	 * @param Entrust
	 * @throws SQLException 
	 * @throws Exception
	 */
	private void updateOp_convert(EntrustDto entrust) throws SQLException{
		//虚拟币
		UserBalanceDto xnb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(entrust.getUser_no().toString(), entrust.getEntrust_coin().toString());
		//兑换币
		UserBalanceDto rmb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(entrust.getUser_no().toString(),"0");
		//手续费比率
		StaticParamsDto staticParams=queryStaticParam.getStaticParamByParam("poundageRate");
		BigDecimal poundatgeRate=staticParams.getValue();
		if(entrust.getEntrust_type().equals(0)){//买
			//xnb.setUnable_coin(xnb.getUnable_coin().subtract(entrust.getEntrust_num()));
			BigDecimal returnrmb=poundatgeRate.multiply(entrust.getEntrust_price().multiply(entrust.getEntrust_num().subtract(entrust.getDeal_num())));
			rmb.setEnable_coin(rmb.getEnable_coin().add(returnrmb));
			rmb.setUnable_coin(rmb.getUnable_coin().subtract(returnrmb));
		}
		if(entrust.getEntrust_type().equals(1)){//卖
			BigDecimal returnpound=(entrust.getEntrust_num().subtract(entrust.getDeal_num())).multiply(entrust.getEntrust_price()).multiply(poundatgeRate);
			xnb.setUnable_coin(xnb.getUnable_coin().subtract(entrust.getEntrust_num().subtract(entrust.getDeal_num())));
			xnb.setEnable_coin(xnb.getEnable_coin().add(entrust.getEntrust_num().subtract(entrust.getDeal_num())));
			rmb.setEnable_coin(rmb.getEnable_coin().add(returnpound));
			rmb.setUnable_coin(rmb.getUnable_coin().subtract(returnpound));
		}
		entrust.setState(2);
		EntrustPersistence.updateEntrust(entrust,xnb,rmb);
	}
}
