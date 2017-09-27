package com.inesv.digiccy.event.handler;

import java.math.BigDecimal;
import java.sql.SQLException;

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
				event.getEntrust_coin(), event.getEntrust_type(),
				event.getEntrust_price(), event.getEntrust_num(),
				event.getDeal_num(), event.getPiundatge(), event.getState(),
				event.getDate(),event.getAttr1());
		String operation = event.getOperation();
		switch (operation) {
		case "insert":
			insertOp(entrust);
			break;
		case "inserts":
			insertOps(entrust);
			break;
		case "update":
			updateOp(entrust);
			break;
		/*case "delete":
			EntrustPersistence.deleteEntrust(entrust);
			break;*/
		case "confirm":
			EntrustPersistence.confirmEntrust(entrust);
			break;
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
	private void insertOp(EntrustDto entrust) throws Exception{
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
	}
	
	/**
	 * 添加操作：买卖操作
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
	 * 删除买卖操作
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
		if(entrust.getAttr1()!=null){
			planOperation.updateOver(entrust.getAttr1());
		}
	}
}
