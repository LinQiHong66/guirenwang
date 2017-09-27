package com.inesv.digiccy.event.handler;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.dto.PlanDto;
import com.inesv.digiccy.dto.StaticParamsDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.event.PlanEvent;
import com.inesv.digiccy.persistence.plan.PlanOperation;
import com.inesv.digiccy.persistence.reg.RegUserPersistence;
import com.inesv.digiccy.persistence.trade.TradePersistence;
import com.inesv.digiccy.query.QueryEntrustInfo;
import com.inesv.digiccy.query.QueryStaticParam;
import com.inesv.digiccy.query.QueryUserBalanceInfo;

public class PlanEventHandler {
	@Autowired
	PlanOperation planOperation;
	
	@Autowired
	RegUserPersistence regUserPersistence;
	
	@Autowired
	QueryUserBalanceInfo queryUserBalanceInfo;
	
	@Autowired
	QueryStaticParam queryStaticParam;
	
	@Autowired
	TradePersistence EntrustPersistence;
	
	@Autowired
	QueryEntrustInfo queryEntrustInfo;
	
	@EventHandler
	public void Handler(PlanEvent planEvent){
		String operation = planEvent.getOperation();
		
		switch (operation) {
		case "insert":
			PlanDto planDto = new PlanDto(planEvent.getId(),planEvent.getUser_id(),planEvent.getBill_id(),planEvent.getPlan_type(),planEvent.getTop_money_start(),
					planEvent.getTop_money_stop(),planEvent.getLow_money_start(),planEvent.getLow_money_stop(),planEvent.getPlan_status(),planEvent.getPlan_time(),planEvent.getRemark(),planEvent.getPlan_money());
			planOperation.insert(planDto);
			break;
		case "updateFinish":
			planOperation.updateFinish(planEvent.getId());
			break;
		case "updateOver":
			planOperation.updateOver(planEvent.getId());
			try {
				//regUserPersistence.updateEntrustStateByAttr1(planEvent.getId());
				EntrustDto entrust = new EntrustDto();
				entrust = queryEntrustInfo.queryEntrustInfoByAttr1(planEvent.getId());
				if(entrust != null){
					updateOp(entrust);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
	
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
}
