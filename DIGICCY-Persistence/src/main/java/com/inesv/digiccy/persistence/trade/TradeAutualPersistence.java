package com.inesv.digiccy.persistence.trade;

import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CoinLevelProportionDto;
import com.inesv.digiccy.dto.ContactDto;
import com.inesv.digiccy.dto.DayMarketDto;
import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.ResultFunctionDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.persistence.bonus.BonusOperation;
import com.inesv.digiccy.persistence.plan.PlanOperation;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 交易中心 委托记录 增删改
 * @author qing
 *
 */
@Transactional
@Component
public class TradeAutualPersistence {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TradeAutualPersistence.class);
	
	@Autowired
	QueryRunner queryRunner;
	
	@Autowired
	PlanOperation operation;
	
	@Autowired
	BonusOperation bonusOperation;
	
	/*
	 * 实时交易-贵人交易平台
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public EntrustDto buy_sell_TradeAutual(String buyTradeType,EntrustDto buyEntrust,EntrustDto sellEntrust,BigDecimal buy_poundatge,BigDecimal sell_poundatge,BigDecimal tradeNum,BigDecimal buyPrice,BigDecimal sellPrice) throws Exception{
		//委托记录排他锁,防止交易的同事，用户撤销委托
		EntrustDto buyEntrustDto = queryEntrustByID(buyEntrust.getId(),buyEntrust.getUser_no());
		if(buyEntrustDto.getDeal_num().doubleValue() != buyEntrust.getDeal_num().doubleValue() || buyEntrustDto.getState() != buyEntrust.getState()) {
			throw new Exception("异常，买委托记录异常");
		}
		EntrustDto sellEntrustDto = queryEntrustByID(sellEntrust.getId(),sellEntrust.getUser_no());
		if(sellEntrustDto.getDeal_num().doubleValue() != sellEntrust.getDeal_num().doubleValue() || sellEntrustDto.getState() != sellEntrust.getState()) {
			throw new Exception("异常，卖委托记录异常");
		}
		//买的人的虚拟币
		UserBalanceDto buyXnb=queryUserBalanceInfoByUserNoAndCoinType(buyEntrust.getUser_no(), buyEntrust.getEntrust_coin());//购买货币
		//买的人的人民币
		UserBalanceDto buyRmb=queryUserBalanceInfoByUserNoAndCoinType(buyEntrust.getUser_no(), buyEntrust.getConvert_coin());//兑换货币
		//买家资金-虚拟币
		buyXnb.setEnable_coin(buyXnb.getEnable_coin().add(tradeNum.subtract(tradeNum.multiply(buy_poundatge))));
		if(buyXnb.getEnable_coin().doubleValue() < 0){
			throw new Exception("异常，买家虚拟币负数");
		}
		String updateUserBalanceXnb = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where id=?";
		Object updateXnbParams[] = {buyXnb.getEnable_coin(),buyXnb.getUnable_coin(),buyXnb.getEnable_coin().add(buyXnb.getUnable_coin()),buyXnb.getId()};
		queryRunner.update(updateUserBalanceXnb, updateXnbParams);
		//买家资金-人民币
		if(buyEntrust.getConvert_coin() == 0) {
			buyRmb.setUnable_coin(buyRmb.getUnable_coin().subtract(tradeNum.multiply(buyPrice)));
			if(buyRmb.getUnable_coin().doubleValue() < 0){
				throw new Exception("异常，买家人民币负数");
			}
		}else {
			double trade_price = buyEntrust.getEntrust_price().doubleValue()/buyEntrust.getConvert_price().doubleValue()*tradeNum.doubleValue();
			BigDecimal bg = new BigDecimal(trade_price);  
			buyRmb.setUnable_coin(buyRmb.getUnable_coin().subtract(new BigDecimal(bg.setScale(6,BigDecimal.ROUND_DOWN).toString())));
			if(buyRmb.getUnable_coin().doubleValue() < 0) {
				throw new Exception("异常，买家人民币负数");
			}
		}
		String updateUserBalanceRmb = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where id=?";
		Object updateRmbParams[] = {buyRmb.getEnable_coin(),buyRmb.getUnable_coin(),buyRmb.getEnable_coin().add(buyRmb.getUnable_coin()),buyRmb.getId()};
		queryRunner.update(updateUserBalanceRmb, updateRmbParams);
		//交易对象虚拟币
		UserBalanceDto sellXnb=queryUserBalanceInfoByUserNoAndCoinType(sellEntrust.getUser_no(), sellEntrust.getEntrust_coin());//消耗虚拟币
		//交易对象人民币
		UserBalanceDto sellRmb=queryUserBalanceInfoByUserNoAndCoinType(sellEntrust.getUser_no(), sellEntrust.getConvert_coin());//得到兑换货币
		//卖家资金-虚拟币
		sellXnb.setUnable_coin(sellXnb.getUnable_coin().subtract(tradeNum));
		if(sellXnb.getUnable_coin().doubleValue()<0){
			throw new Exception("异常，卖家虚拟币负数");
		}
		String updateUnSellUserBalanceXnb = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where id=?";
		Object updateUnSellXnbParams[] = {sellXnb.getEnable_coin(),sellXnb.getUnable_coin(),sellXnb.getUnable_coin().add(sellXnb.getEnable_coin()),sellXnb.getId()};
		queryRunner.update(updateUnSellUserBalanceXnb, updateUnSellXnbParams);
		if(sellRmb.getEnable_coin().doubleValue()<0){
			throw new Exception("异常，卖家人民币负数");
		}
		BigDecimal sellSumPrice = new BigDecimal("0");
		BigDecimal sellPoundatgePrice = new BigDecimal("0");
		if(sellEntrust.getConvert_coin() == 0) {
			sellRmb.setEnable_coin(sellRmb.getEnable_coin().add(tradeNum.multiply(sellPrice).subtract(tradeNum.multiply(sellPrice).multiply(sell_poundatge))));
		}else {
			double sellSumPriceDouble = (tradeNum.doubleValue()*(sellPrice.doubleValue())/(sellEntrust.getConvert_price().doubleValue()));
			sellSumPrice = new BigDecimal(sellSumPriceDouble).setScale(6,BigDecimal.ROUND_DOWN);//交易金额
			double sellPoundatgePriceSouble = (sellSumPrice.doubleValue()*sell_poundatge.doubleValue());
			sellPoundatgePrice = new BigDecimal(sellPoundatgePriceSouble).setScale(6,BigDecimal.ROUND_DOWN);//手续费
			BigDecimal bg = (sellSumPrice.subtract(sellPoundatgePrice)).setScale(6,BigDecimal.ROUND_DOWN);//扣除手续费后的金额
			sellRmb.setEnable_coin(sellRmb.getEnable_coin().add(bg));
		}
		String updateUnSellUserBalanceRmb = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where id=?";
		Object updateUnSellRmbParams[] = {sellRmb.getEnable_coin(),sellRmb.getUnable_coin(),sellRmb.getEnable_coin().add(sellRmb.getUnable_coin()),sellRmb.getId()};
		queryRunner.update(updateUnSellUserBalanceRmb, updateUnSellRmbParams);
			//1.修改交易对象买的委托记录
			buyEntrust.setDeal_num(buyEntrust.getDeal_num().add(tradeNum));// 修改交易数量
			if (buyEntrust.getDeal_num().compareTo(buyEntrust.getEntrust_num()) == 0) {
				buyEntrust.setState(1);// 已交易
				if(buyEntrust.getConvert_coin() == 0) {
					String updateBuyEntrust = "update t_inesv_entrust set deal_num = ? , state = ? where id = ?";
					Object updateBuyEntrustParams[] = { buyEntrust.getDeal_num(),
							buyEntrust.getState(), buyEntrust.getId() };
					queryRunner.update(updateBuyEntrust, updateBuyEntrustParams);
				}else {
					//币币交易，解决小数点除不尽问题
					buyRmb=queryUserBalanceInfoByUserNoAndCoinType(buyEntrust.getUser_no(), buyEntrust.getConvert_coin());//交易扣除货币金额后买方的RMB
					double trade_price = (buyEntrust.getEntrust_price().doubleValue()*tradeNum.doubleValue())/buyEntrust.getConvert_price().doubleValue();//此次交易扣除的货币金额
					BigDecimal deal_bg = new BigDecimal(new BigDecimal(trade_price).setScale(6,BigDecimal.ROUND_DOWN).toString());//此次交易扣除的货币金额（6位小数）
					BigDecimal remainder_bg =  buyEntrust.getConvert_sum_price().subtract(deal_bg).subtract(buyEntrust.getConvert_deal_price());//交易完成，产生的余数
					buyRmb.setUnable_coin(buyRmb.getUnable_coin().subtract(remainder_bg));
					if(buyRmb.getUnable_coin().doubleValue()<0){
						throw new Exception("异常，买家人民币负数");
					}
					updateUserBalanceRmb = "update t_inesv_user_balance set enable_coin = ?,unable_coin = ?,total_price = ? where id = ?";
					Object updateRmbParam[] = {buyRmb.getEnable_coin(),buyRmb.getUnable_coin(),buyRmb.getEnable_coin().add(buyRmb.getUnable_coin()),buyRmb.getId()};
					queryRunner.update(updateUserBalanceRmb, updateRmbParam);
					//修改委托记录
					String updateBuyEntrust = "update t_inesv_entrust set convert_deal_price = ? , deal_num = ? , state = ? where id = ?";
					Object updateBuyEntrustParams[] = {buyEntrust.getConvert_sum_price(), buyEntrust.getDeal_num(),
							buyEntrust.getState(), buyEntrust.getId() };
					queryRunner.update(updateBuyEntrust, updateBuyEntrustParams);
				}
			}else {
				if(buyEntrust.getConvert_coin() == 0) {
					String updateBuyEntrust = "update t_inesv_entrust set deal_num = ? , state = ? where id = ?";
					Object updateBuyEntrustParams[] = { buyEntrust.getDeal_num(),
							buyEntrust.getState(), buyEntrust.getId() };
					queryRunner.update(updateBuyEntrust, updateBuyEntrustParams);
				}else {
					double price = (buyEntrust.getEntrust_price().doubleValue()*tradeNum.doubleValue())/buyEntrust.getConvert_price().doubleValue();
					BigDecimal deal_bg = new BigDecimal(new BigDecimal(price).setScale(6,BigDecimal.ROUND_DOWN).toString());
					buyEntrust.setConvert_deal_price(buyEntrust.getConvert_deal_price().add(deal_bg));
					String updateBuyEntrust = "update t_inesv_entrust set convert_deal_price = ? , deal_num = ? , state = ? where id = ?";
					Object updateBuyEntrustParams[] = {buyEntrust.getConvert_deal_price(), buyEntrust.getDeal_num(),
							buyEntrust.getState(), buyEntrust.getId() };
					queryRunner.update(updateBuyEntrust, updateBuyEntrustParams);
				}
			}
			//2.修改交易对象卖的委托记录
			sellEntrust.setDeal_num(sellEntrust.getDeal_num().add(tradeNum));
			if (sellEntrust.getEntrust_num().compareTo(sellEntrust.getDeal_num()) == 0) {
				sellEntrust.setState(1);
			}
				String opObjEntrust = "update t_inesv_entrust set deal_num=?,state=? where id=?";
				Object opObjEntrustParams[] = { sellEntrust.getDeal_num(),
						sellEntrust.getState(), sellEntrust.getId() };
				queryRunner.update(opObjEntrust, opObjEntrustParams);
		//交易货币最新行情
		List<DayMarketDto> dayMarketDtoList=queryDayMarketInfoByCoinType(buyEntrust.getEntrust_coin());
		if(tradeNum.doubleValue()!=0){//(买的货币的数量-买的货币数量*手续费)(deal_num-poundage)((attr1的币种)attr2-poundage)
			//3-1.生成一条新买的交易记录
			String insertBuyDeal = "INSERT INTO t_inesv_deal_detail(user_no,coin_no,deal_type,deal_price,deal_num,sum_price,poundage_scale,poundage,date,attr1,attr2) VALUES(?,?,?,?,?,?,?,?,now(),?,?)";
			Object buyDealParam[] = {buyEntrust.getUser_no(),buyEntrust.getEntrust_coin(),buyEntrust.getEntrust_type(),
					buyPrice,tradeNum,tradeNum.multiply(buyPrice),buy_poundatge,tradeNum.multiply(buy_poundatge),buyEntrust.getEntrust_coin(),tradeNum};
			queryRunner.update(insertBuyDeal,buyDealParam);
			
			//3-2.生成一条新的卖的交易记录
			if(sellEntrust.getConvert_coin() == 0) {//(卖的货币数量*卖的价格-卖的货币数量*卖的价格*手续费)(tradeNum.multiply(sellPrice)-tradeNum.multiply(sellPrice).multiply(sell_poundatge))((attr1币种)attr2-poundage)
				String insertSellDeal = "INSERT INTO t_inesv_deal_detail(user_no,coin_no,deal_type,deal_price,deal_num,sum_price,poundage_scale,poundage,date,attr1,attr2) VALUES(?,?,?,?,?,?,?,?,now(),?,?)";
				Object sellDealParam[] = {sellEntrust.getUser_no(),sellEntrust.getEntrust_coin(),sellEntrust.getEntrust_type(),
					sellPrice,tradeNum,tradeNum.multiply(sellPrice),sell_poundatge,tradeNum.multiply(sellPrice).multiply(sell_poundatge),sellEntrust.getConvert_coin(),tradeNum.multiply(sellPrice)};
				queryRunner.update(insertSellDeal,sellDealParam);
			}else {//(卖的货币数量*卖的价格-卖的货币数量*卖的价格*手续费)(tradeNum.multiply(sellPrice)-tradeNum.multiply(sellPrice).multiply(sell_poundatge))((attr1币种)attr2-poundage)
				String insertSellDeal = "INSERT INTO t_inesv_deal_detail(user_no,coin_no,deal_type,deal_price,deal_num,sum_price,poundage_scale,poundage,date,attr1,attr2) VALUES(?,?,?,?,?,?,?,?,now(),?,?)";
				Object sellDealParam[] = {sellEntrust.getUser_no(),sellEntrust.getEntrust_coin(),sellEntrust.getEntrust_type(),
					sellPrice,tradeNum,tradeNum.multiply(sellPrice),sell_poundatge,sellPoundatgePrice,sellEntrust.getConvert_coin(),sellSumPrice};
				queryRunner.update(insertSellDeal,sellDealParam);
			}
		}
		String insertTradeDetail = "INSERT INTO t_trade_detail (coin_type,convert_type,buy_user,buy_price,buy_number,buy_poundatge,sell_user,sell_price,sell_number,sell_poundatge,buy_entrust,sell_entrust,date,end_buy_rmb_price,end_buy_coin_price,end_sell_rmb_price,end_sell_coin_price)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object tradeDetailParam[] = {buyEntrust.getEntrust_coin(),buyEntrust.getConvert_coin(),buyEntrust.getUser_no(),buyEntrust.getEntrust_price(),tradeNum,tradeNum.multiply(buy_poundatge),
				sellEntrust.getUser_no(),sellEntrust.getEntrust_price(),tradeNum,tradeNum.multiply(sellPrice).multiply(sell_poundatge),buyEntrust.getId(),sellEntrust.getId(),
				new Date(),buyRmb.getTotal_price(),buyXnb.getTotal_price(),sellRmb.getTotal_price(),sellXnb.getTotal_price()};
			queryRunner.update(insertTradeDetail,tradeDetailParam);
		//4-1.查詢貨幣最新數據
		DayMarketDto inesvDayMarketDto=queryDealDetailInfoByDayAndCoin(buyEntrust.getEntrust_coin());
		//4-2.修改货币最新市场行情表
		if(dayMarketDtoList.size()!=0){
			String updateDayMarket = "UPDATE t_inesv_day_market SET "
					+ " newes_deal = ? , buy_price = ? , sell_price = ? , deal_num = ? ,"
					+ " deal_price = ? , day_percent = ? , max_price = ? , min_price = ?"
					+ " WHERE coin_type = ? AND TO_DAYS(DATE) = TO_DAYS(NOW())";
			Object dayMarketParam[] = {inesvDayMarketDto.getNewes_deal() , inesvDayMarketDto.getBuy_price() , inesvDayMarketDto.getSell_price() , inesvDayMarketDto.getDeal_num() , 
					inesvDayMarketDto.getDeal_price(),inesvDayMarketDto.getDay_percent(),inesvDayMarketDto.getMax_price(),inesvDayMarketDto.getMin_price(),buyEntrust.getEntrust_coin()};
			queryRunner.update(updateDayMarket,dayMarketParam);
		}else{
			String insertDayMarket = "INSERT INTO t_inesv_day_market (coin_type,newes_deal,buy_price,sell_price,deal_num,deal_price,day_percent,max_price,min_price,state,date) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			Object dayMarketParam[] = {buyEntrust.getEntrust_coin(),inesvDayMarketDto.getNewes_deal() , inesvDayMarketDto.getBuy_price() , inesvDayMarketDto.getSell_price() , inesvDayMarketDto.getDeal_num() , 
					inesvDayMarketDto.getDeal_price(),inesvDayMarketDto.getDay_percent(),inesvDayMarketDto.getMax_price(),inesvDayMarketDto.getMin_price(),0,new Date()};
			queryRunner.update(insertDayMarket,dayMarketParam);
		}
		//差价记录
		String insertDifference = "INSERT INTO t_trade_detail_difference(buy_user,sell_user,buy_entrust_no,sell_entrust_no,entrust_coin,convert_coin,trade_num,buy_price,buy_sum_price,"
				+ "sell_price,sell_sum_price,rmb_difference_price,xnb_difference_price,date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if(buyEntrust.getConvert_coin() == 0) {
			BigDecimal rmbDifferencePrice = (buyEntrust.getEntrust_price().multiply(tradeNum).subtract(sellEntrust.getEntrust_price().multiply(tradeNum)));
			rmbDifferencePrice = rmbDifferencePrice.setScale(6,BigDecimal.ROUND_DOWN);
			Object differenceParam[] = {buyEntrust.getUser_no(),sellEntrust.getUser_no(),buyEntrust.getId(),sellEntrust.getId(),buyEntrust.getEntrust_coin(),buyEntrust.getConvert_coin(),
					tradeNum,buyEntrust.getEntrust_price(),buyEntrust.getEntrust_price().multiply(tradeNum),sellEntrust.getEntrust_price(),sellEntrust.getEntrust_price().multiply(tradeNum),
					rmbDifferencePrice,"0",new Date()};
			queryRunner.update(insertDifference,differenceParam);
		}else {
			BigDecimal rmbDifferencePrice = (buyEntrust.getEntrust_price().multiply(tradeNum)).subtract(sellEntrust.getEntrust_price().multiply(tradeNum));
			rmbDifferencePrice = rmbDifferencePrice.setScale(6,BigDecimal.ROUND_DOWN);
			double buyPrice0Double = (buyEntrust.getEntrust_price().doubleValue()*tradeNum.doubleValue())/(buyEntrust.getConvert_price().doubleValue());
			BigDecimal buyPrice0 = new BigDecimal(buyPrice0Double).setScale(6,BigDecimal.ROUND_DOWN);
			double sellPrice0Double = (sellEntrust.getEntrust_price().doubleValue()*tradeNum.doubleValue())/(sellEntrust.getConvert_price().doubleValue());
			BigDecimal sellPrice0 = new BigDecimal(sellPrice0Double).setScale(6,BigDecimal.ROUND_DOWN);
			BigDecimal xnbDifferencePrice = buyPrice0.subtract(sellPrice0);
			Object differenceParam[] = {buyEntrust.getUser_no(),sellEntrust.getUser_no(),buyEntrust.getId(),sellEntrust.getId(),buyEntrust.getEntrust_coin(),buyEntrust.getConvert_coin(),
					tradeNum,buyEntrust.getEntrust_price(),buyEntrust.getEntrust_price().multiply(tradeNum),sellEntrust.getEntrust_price(),sellEntrust.getEntrust_price().multiply(tradeNum),
					rmbDifferencePrice,xnbDifferencePrice,new Date()};
			queryRunner.update(insertDifference,differenceParam);
		}
		//添加手续费记录
		InesvUserDto buyUserDto = queryUserInfoByUserNo(buyEntrust.getUser_no());
		if(buyUserDto.getOrg_code() == null) {
			buyUserDto.setOrg_code("");
		}
		InesvUserDto sellUserDto = queryUserInfoByUserNo(sellEntrust.getUser_no());
		if(sellUserDto.getOrg_code() == null) {
			sellUserDto.setOrg_code("");
		}
		//1.买产生的手续费
		String insertBuyPoundage = "INSERT INTO t_inesv_poundage(user_no,user_name,user_code,optype,type,money,sum_money,date,attr1) VALUES(?,?,?,?,?,?,?,?,?)";
		Object buyPoundageParam[] = {buyEntrust.getUser_no(),buyUserDto.getUsername(),buyUserDto.getOrg_code(),buyEntrust.getEntrust_type(),buyEntrust.getEntrust_coin(),tradeNum.multiply(buy_poundatge),tradeNum,new Date(),buyEntrust.getEntrust_coin()};
		queryRunner.update(insertBuyPoundage,buyPoundageParam);
		//2.卖产生的手续费
		if(sellEntrust.getConvert_coin() == 0) {
			String insertSellPoundage = "INSERT INTO t_inesv_poundage(user_no,user_name,user_code,optype,type,money,sum_money,date,attr1) VALUES(?,?,?,?,?,?,?,?,?)";
			Object sellPoundageParam[] = {sellEntrust.getUser_no(),sellUserDto.getUsername(),sellUserDto.getOrg_code(),sellEntrust.getEntrust_type(),sellEntrust.getEntrust_coin(),tradeNum.multiply(sell_poundatge).multiply(sellPrice),tradeNum.multiply(sellPrice),new Date(),sellEntrust.getConvert_coin()};
			queryRunner.update(insertSellPoundage,sellPoundageParam);
		}else {
			String insertSellPoundage = "INSERT INTO t_inesv_poundage(user_no,user_name,user_code,optype,type,money,sum_money,date,attr1) VALUES(?,?,?,?,?,?,?,?,?)";
			Object sellPoundageParam[] = {sellEntrust.getUser_no(),sellUserDto.getUsername(),sellUserDto.getOrg_code(),sellEntrust.getEntrust_type(),sellEntrust.getEntrust_coin(),sellPoundatgePrice,sellSumPrice,new Date(),sellEntrust.getConvert_coin()};
			queryRunner.update(insertSellPoundage,sellPoundageParam);
		}
		//买家分润
		userLevelDetailed(buyEntrust.getUser_no(),buyEntrust.getEntrust_coin(),tradeNum.multiply(buy_poundatge),buyPrice.multiply(buy_poundatge),tradeNum.multiply(buy_poundatge),buyEntrust.getId(),buyEntrust.getEntrust_type(),buyEntrust.getEntrust_coin());
		//卖家分润
		if(sellEntrust.getConvert_coin() == 0) {
			userLevelDetailed(sellEntrust.getUser_no(),sellEntrust.getEntrust_coin(),tradeNum.multiply(sell_poundatge),sellPrice.multiply(sell_poundatge),sellPrice.multiply(tradeNum).multiply(sell_poundatge),sellEntrust.getId(),sellEntrust.getEntrust_type(),0);
		}else {
			userLevelDetailed(sellEntrust.getUser_no(),sellEntrust.getEntrust_coin(),tradeNum.multiply(sell_poundatge),sellPrice.multiply(sell_poundatge),sellPoundatgePrice,sellEntrust.getId(),sellEntrust.getEntrust_type(),sellEntrust.getConvert_coin());
		}
		if(buyTradeType.equals("0")) {
			return buyEntrust;
		}
		return sellEntrust;
	}
	
	/*
	 * 交易分润
	 */
	public void userLevelDetailed(Integer user_no, Integer coin_no, BigDecimal tradeNum, BigDecimal entrustPrice, BigDecimal poundatgePrice, Long entrustNo, Integer entrustType, Integer levelCoinType) throws Exception{
		//查询币种相应上级分润比例
		CoinLevelProportionDto coinLevelProportionDto = queryByCoinNo(Long.valueOf(coin_no)); //货币分润比例
		ContactDto contactDto = queryByContact(); //官方账号
		if(contactDto.getAuthority_account() == null) {
			contactDto.setAuthority_account("*");
		}
		//上级分润金额
		BigDecimal level_one = null;
		BigDecimal level_two = null;
		BigDecimal level_three = null;
		BigDecimal level_four = null;
		BigDecimal level_five = null;
		BigDecimal authority_level = null;
		if(coinLevelProportionDto != null && coinLevelProportionDto.getState() == 1){
			level_one = coinLevelProportionDto.getLevel_one().multiply(poundatgePrice);//经纪人1
			level_one = level_one.setScale(6,BigDecimal.ROUND_DOWN);
			level_two = coinLevelProportionDto.getLevel_two().multiply(poundatgePrice);//经纪人2
			level_two = level_two.setScale(6,BigDecimal.ROUND_DOWN);
			level_three = coinLevelProportionDto.getLevel_three().multiply(poundatgePrice);//子机构
			level_three = level_three.setScale(6,BigDecimal.ROUND_DOWN);
			level_four = coinLevelProportionDto.getLevel_four().multiply(poundatgePrice);//机构
			level_four = level_four.setScale(6,BigDecimal.ROUND_DOWN);
		}else {
			return;
		}
		BigDecimal sum_level_price = poundatgePrice.setScale(6,BigDecimal.ROUND_DOWN);//总分润金额
		//分润逻辑
		InesvUserDto levelUserDto = new InesvUserDto();
		InesvUserDto userDto = new InesvUserDto();
		ResultFunctionDto functionDto = new ResultFunctionDto();
		Integer userNo = 0;
		Double already_level_price = 0D;
		for(int i=0 ; i < 4 ; i++) {//0：经纪人，1：经纪人，2：子机购，3：机构
			if(i == 0) {
				userNo = user_no;
			}
			userDto = queryUserInfoByUserNo(userNo);//产生分润用户
			if(i != 2) {
				levelUserDto = queryUserByID(userNo);//获得分润用户
				if(levelUserDto == null || levelUserDto.getUser_no() == userNo || levelUserDto.getOrg_type() == 3) {
					break;
				}
			}
			if(i == 0) {//经纪人1分润
				if(levelUserDto.getOrg_type() == 2) {//经纪人
					already_level_price += level_one.doubleValue();
					bonusOperation.doLevelBonus(entrustNo, level_one, sum_level_price, levelCoinType, levelUserDto.getUser_no(), userDto.getUsername(), levelUserDto.getOrg_code(), userDto.getUser_no(), userDto.getOrg_code(), entrustType,coinLevelProportionDto.getLevel_one(),"经纪人1-交易分润");
				}
				if(levelUserDto.getOrg_type() == 1) {//子机构
					i = 2;
					already_level_price += level_three.doubleValue();
					bonusOperation.doLevelBonus(entrustNo, level_three, sum_level_price, levelCoinType, levelUserDto.getUser_no(), userDto.getUsername(), levelUserDto.getOrg_code(), userDto.getUser_no(), userDto.getOrg_code(), entrustType,coinLevelProportionDto.getLevel_three(),"子机构-交易分润");
				}
				if(levelUserDto.getOrg_type() == 0) {//机构
					i = 4;
					already_level_price += level_four.doubleValue();
					bonusOperation.doLevelBonus(entrustNo, level_four, sum_level_price, levelCoinType, levelUserDto.getUser_no(), userDto.getUsername(), levelUserDto.getOrg_code(), userDto.getUser_no(), userDto.getOrg_code(), entrustType,coinLevelProportionDto.getLevel_four(),"机构-交易分润");
				}
				userNo = levelUserDto.getUser_no();
				continue;
			} 
			if(i == 1) {//经纪人2分润
				if(levelUserDto.getOrg_type() == 2) {//经纪人
					already_level_price += level_two.doubleValue();
					bonusOperation.doLevelBonus(entrustNo, level_two, sum_level_price, levelCoinType, levelUserDto.getUser_no(), userDto.getUsername(), levelUserDto.getOrg_code(), userDto.getUser_no(), userDto.getOrg_code(), entrustType,coinLevelProportionDto.getLevel_two(),"经纪人2-交易分润");
				}
				if(levelUserDto.getOrg_type() == 1) {//子机构
					i = 2;
					already_level_price += level_three.doubleValue();
					bonusOperation.doLevelBonus(entrustNo, level_three, sum_level_price, levelCoinType, levelUserDto.getUser_no(), userDto.getUsername(), levelUserDto.getOrg_code(), userDto.getUser_no(), userDto.getOrg_code(), entrustType,coinLevelProportionDto.getLevel_three(),"子机构-交易分润");
				}
				if(levelUserDto.getOrg_type() == 0) {//机构
					i = 4;
					already_level_price += level_three.doubleValue();
					bonusOperation.doLevelBonus(entrustNo, level_four, sum_level_price, levelCoinType, levelUserDto.getUser_no(), userDto.getUsername(), levelUserDto.getOrg_code(), userDto.getUser_no(), userDto.getOrg_code(), entrustType,coinLevelProportionDto.getLevel_four(),"机构-交易分润");
				}
				userNo = levelUserDto.getUser_no();
				continue;
			}
			if(i == 2) {//子机构分润
				//函数调用
				functionDto = queryByFunction(userNo,2);
				if(functionDto  == null || functionDto.getLevel_user_no() == null) {
					break;
				}
				levelUserDto = queryUserInfoByUserNo(functionDto.getLevel_user_no());
				if(levelUserDto.getOrg_type() == 3 || levelUserDto.getOrg_type() == 2) {
					break;
				}
				if(levelUserDto.getOrg_type() == 1) {
					already_level_price += level_three.doubleValue();
					bonusOperation.doLevelBonus(entrustNo, level_three, sum_level_price, levelCoinType, functionDto.getLevel_user_no(), userDto.getUsername(), levelUserDto.getOrg_code(), userDto.getUser_no(), userDto.getOrg_code(), entrustType,coinLevelProportionDto.getLevel_three(),"子机构-交易分润");
				}if(levelUserDto.getOrg_type() == 0) {
					i = 4;
					already_level_price += level_four.doubleValue();
					bonusOperation.doLevelBonus(entrustNo, level_four, sum_level_price, levelCoinType, functionDto.getLevel_user_no(), userDto.getUsername(), levelUserDto.getOrg_code(), userDto.getUser_no(), userDto.getOrg_code(), entrustType,coinLevelProportionDto.getLevel_four(),"机构-交易分润");
				}
				userNo = levelUserDto.getUser_no();
				continue;
			}
			if(i == 3) {//机构分润
				already_level_price += level_four.doubleValue();
				bonusOperation.doLevelBonus(entrustNo, level_four, sum_level_price, levelCoinType, levelUserDto.getUser_no(), userDto.getUsername(), levelUserDto.getOrg_code(), userDto.getUser_no(), userDto.getOrg_code(), entrustType,coinLevelProportionDto.getLevel_four(),"机构-交易分润");
				userNo = levelUserDto.getUser_no();
				continue;
			}
		}
			//官方账号分润
			InesvUserDto authorityUserDto = queryUserInfoByPhone(contactDto.getAuthority_account());//官方账号的指定用户
			if(authorityUserDto == null) {
				return;
			}else {
				if(authorityUserDto.getOrg_code() == null) {
					authorityUserDto.setOrg_code("");
				}
				authority_level = sum_level_price.subtract(new BigDecimal(already_level_price.toString()));
				bonusOperation.doLevelBonus(entrustNo, authority_level, sum_level_price, levelCoinType, authorityUserDto.getUser_no(),"",authorityUserDto.getOrg_code(), 0, "", entrustType,new BigDecimal("0"),"官方账号-交易分润");//剩余的给官方账号
			}
	}
	
	
	/**
	 * 委托实时买卖交易--数据库
	 * @param entrustDto
	 * @return
	 *//*
    public void validateTradeCoinActualBySQL(EntrustDto entrustDto) throws Exception{
      	Integer coinType = entrustDto.getEntrust_coin();	//委托货币类型
      	CoinDto coinDto = new CoinDto();
		String poundatgeSql = "select buy_poundatge,sell_poundatge from t_inesv_coin_type where coin_no = ?";
			coinDto = queryRunner.query(poundatgeSql, new BeanHandler<CoinDto>(CoinDto.class),Long.valueOf(coinType));
		BigDecimal buy_poundatge = new BigDecimal(0);//买手续费
		BigDecimal sell_poundatge = new BigDecimal(0);//卖手续费
		if(coinDto != null){
			if(coinDto.getBuy_poundatge()!=null){
				buy_poundatge = coinDto.getBuy_poundatge();
			}
			if(coinDto.getSell_poundatge()!=null){
				sell_poundatge = coinDto.getSell_poundatge();
			}
		}
		LOGGER.debug("-------" + entrustDto.getUser_no() + "-的-" + entrustDto.getId() + "-交易开始--------");
		TradeSql("buySellTradeSQL","sql",entrustDto,buy_poundatge,sell_poundatge,0L);
		LOGGER.debug("-------交易结束--------");
    }
	
	*//**
	 * 委托交易----买卖
	 * @param entrustDto
	 * @return
	 *//*
	public void TradeSql(String entrustType,String sqlType,EntrustDto buy_sell_EntrustDto,BigDecimal buy_poundatge,BigDecimal sell_poundatge,Long noSelectId) throws Exception{
		List<EntrustDto> getUnSellList = null;
		if(buy_sell_EntrustDto.getEntrust_type()==0){
			getUnSellList=queryEntrustByEntrustPriceEntrustCoinAndEntrustTypeAndState(buy_sell_EntrustDto,noSelectId);
			//卖方无数据不交易
			if(getUnSellList!=null && getUnSellList.size()>0){
				EntrustDto sellEntrust=getUnSellList.get(0);
				// 交易数量(取委托数量 与 交易对象的未交易数量 的较小值)
				BigDecimal tradeNum = buy_sell_EntrustDto.getEntrust_num()
						.subtract(buy_sell_EntrustDto.getDeal_num())
						.min(sellEntrust.getEntrust_num()
								.subtract(sellEntrust.getDeal_num()));
				// 交易
				try {
					buy_sell_EntrustDto = buy_sell_TradeAutual(buy_sell_EntrustDto.getEntrust_type().toString(),buy_sell_EntrustDto,sellEntrust,
							buy_poundatge, sell_poundatge,
							tradeNum, buy_sell_EntrustDto.getEntrust_price(), sellEntrust.getEntrust_price());
				} catch (Exception e) {
					e.printStackTrace();
				}
				//交易完了再判断,如果买的用户还有未交易数量则递归
				if(buy_sell_EntrustDto.getState()==0){
					TradeSql("buyTradeSQL","sql",buy_sell_EntrustDto,buy_poundatge,sell_poundatge,getUnSellList.get(0).getId());
				}
			}
		}
		if(buy_sell_EntrustDto.getEntrust_type()==1){
			getUnSellList=queryEntrustByEntrustPriceEntrustCoinAndEntrustTypeAndState(buy_sell_EntrustDto,noSelectId);
			//卖方无数据不交易
			if(getUnSellList!=null && getUnSellList.size()>0){
				EntrustDto buyEntrust=getUnSellList.get(0);
				// 交易数量(取委托数量 与 交易对象的未交易数量 的较小值)
				BigDecimal tradeNum = buy_sell_EntrustDto.getEntrust_num()
						.subtract(buy_sell_EntrustDto.getDeal_num())
						.min(buyEntrust.getEntrust_num()
								.subtract(buyEntrust.getDeal_num()));
				// 交易
					buy_sell_EntrustDto = buy_sell_TradeAutual(buy_sell_EntrustDto.getEntrust_type().toString(),buyEntrust,buy_sell_EntrustDto,
							buy_poundatge, sell_poundatge,
							tradeNum, buyEntrust.getEntrust_price(), buy_sell_EntrustDto.getEntrust_price());
				//交易完了再判断,如果买的用户还有未交易数量则递归
				if(buy_sell_EntrustDto.getState()==0){
					TradeSql("sellTradeSQL","sql",buy_sell_EntrustDto,buy_poundatge,sell_poundatge,getUnSellList.get(0).getId());
				}
			}
		}
	}
	
	*//**
	 * 根据委托价格，货币类型，交易类型，委托状态查找委托记录
	 * @param entrustPrice
	 * @param entrustCoin
	 * @param entrustType
	 * @param state
	 * @return
	 * @throws SQLException 
	 *//*
	public List<EntrustDto> queryEntrustByEntrustPriceEntrustCoinAndEntrustTypeAndState(EntrustDto buy_sell_EntrustDto,Long noSelectId) throws Exception{
		String sql = null;
		List<EntrustDto> list = null;
		if(buy_sell_EntrustDto.getEntrust_type() == 0) {
			sql = "SELECT * FROM t_inesv_entrust WHERE entrust_price <= ? AND entrust_coin = ? AND convert_coin = ? AND entrust_type = ? AND state = ? AND user_no != ? AND id != ? AND entrust_num!=deal_num ORDER BY DATE ASC LIMIT 1 FOR UPDATE";
			Object params[] = {buy_sell_EntrustDto.getEntrust_price(),buy_sell_EntrustDto.getEntrust_coin(),buy_sell_EntrustDto.getConvert_coin(),1,0,buy_sell_EntrustDto.getUser_no(),noSelectId};
			list = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
		}else if(buy_sell_EntrustDto.getEntrust_type() == 1) {
			sql = "SELECT * FROM t_inesv_entrust WHERE entrust_price >= ? AND entrust_coin = ? AND convert_coin = ? AND entrust_type = ? AND state = ? AND user_no != ? AND id != ? AND entrust_num!=deal_num ORDER BY DATE ASC LIMIT 1 FOR UPDATE";
			Object params[] = {buy_sell_EntrustDto.getEntrust_price(),buy_sell_EntrustDto.getEntrust_coin(),buy_sell_EntrustDto.getConvert_coin(),0,0,buy_sell_EntrustDto.getUser_no(),noSelectId};
			list = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
		}
		return list;
	}*/
	
	/**
	 * 根据委托单号查询记录
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public EntrustDto queryEntrustById(Long id) throws Exception{
		String sql="select * from t_inesv_entrust where id = ? for update";
		Object params[] = {id};
		EntrustDto dto= queryRunner.query(sql,new BeanHandler<EntrustDto>(EntrustDto.class),params);
		return dto;
	}
	
	/**
	 * 用户信息
	 * @param user_no
	 * @return
	 * @throws SQLException
	 */
	private InesvUserDto queryUserInfoByUserNo(Integer user_no) throws SQLException {
        String querySql = "select user_no,username,org_type,org_code from t_inesv_user where user_no=?";
        Object params[] = {user_no};
        InesvUserDto userInfo=queryRunner.query(querySql,new BeanHandler<InesvUserDto>(InesvUserDto.class),params);
		return userInfo;
	}
	
	/**
	 * 用户信息
	 * @param phone
	 * @return
	 * @throws SQLException
	 */
	private InesvUserDto queryUserInfoByPhone(String phone) throws SQLException {
        String querySql = "select user_no,username,org_code from t_inesv_user where phone=? limit 1";
        Object params[] = {phone};
        InesvUserDto userInfo=queryRunner.query(querySql,new BeanHandler<InesvUserDto>(InesvUserDto.class),params);
		return userInfo;
	}
	
	/**
	 * 用户资产
	 * @param user_no
	 * @param entrust_coin
	 * @return
	 * @throws SQLException
	 */
	private UserBalanceDto queryUserBalanceInfoByUserNoAndCoinType(Integer user_no, Integer entrust_coin) throws SQLException {
        String querySql = "select * from t_inesv_user_balance where user_no=? and coin_type=? for update";
        Object params[] = {user_no,entrust_coin};
        UserBalanceDto userBalanceInfo=queryRunner.query(querySql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),params);
		return userBalanceInfo;
	}
	
	/**
	 * 用户资产
	 * @param user_no
	 * @param entrust_coin
	 * @return
	 * @throws SQLException
	 */
	private UserBalanceDto queryUserBalanceInfoByUserNoAndCoinTypeInTradeDeal(Integer user_no, Integer entrust_coin) throws SQLException {
        String querySql = "select total_price from t_inesv_user_balance where user_no=? and coin_type=? for update";
        Object params[] = {user_no,entrust_coin};
        UserBalanceDto userBalanceInfo=queryRunner.query(querySql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),params);
		return userBalanceInfo;
	}
	
	/**
	 * 货币每日行情表
	 * @param entrust_coin
	 * @return
	 * @throws SQLException
	 */
	private List<DayMarketDto> queryDayMarketInfoByCoinType(Integer entrust_coin) throws SQLException {
        String querySql = "SELECT * FROM t_inesv_day_market WHERE TO_DAYS(DATE) = TO_DAYS(NOW()) and coin_type=?";
        Object params[] = {entrust_coin};
        List<DayMarketDto> dayMarketDtoList=queryRunner.query(querySql,new BeanListHandler<DayMarketDto>(DayMarketDto.class),params);
		return dayMarketDtoList;
	}
	
	/**
     * 貨幣最新信息
     * @return
     * @throws SQLException 
     */
	public DayMarketDto queryDealDetailInfoByDayAndCoin(Integer coin_no) throws Exception{
		DayMarketDto inesvDayMarketDto = new DayMarketDto();
		String querySql = " SELECT coin_no AS coin_type, " 
				+ " @num1:=IFNULL((SELECT d1.deal_price FROM t_inesv_deal_detail d1 WHERE d1.coin_no = d.coin_no ORDER BY id DESC LIMIT 1),0) AS  newes_deal, "
				+ " IFNULL((SELECT d2.deal_price FROM t_inesv_deal_detail d2 WHERE d2.deal_type = 0 AND d2.coin_no = d.coin_no AND TO_DAYS(DATE) = TO_DAYS(NOW()) ORDER BY id DESC LIMIT 1),0) AS  buy_price, "
				+ " IFNULL((SELECT d3.deal_price FROM t_inesv_deal_detail d3 WHERE d3.deal_type = 1 AND d3.coin_no = d.coin_no AND TO_DAYS(DATE) = TO_DAYS(NOW()) ORDER BY id DESC LIMIT 1),0) AS  sell_price, "
				+ " SUM(deal_num) AS 'deal_num', "
				+ " SUM(deal_price*deal_num) AS 'deal_price', "
				+ " @num2:=MAX(deal_price) AS 'max_price', "
				+ " MIN(deal_price) AS 'min_price', "
				+ " @num3:=IFNULL((SELECT d4.deal_price FROM t_inesv_deal_detail d4 WHERE d4.coin_no = d.coin_no AND TO_DAYS(DATE) = TO_DAYS(NOW()) LIMIT 0,1),0) AS 'begin_price', "
				+ " FORMAT(IFNULL((((@num1-@num3)/@num3)*100),0),2) AS 'day_percent', "
				+ " DATE_FORMAT(DATE,'%Y-%m-%d') AS DATE "
				+ " FROM t_inesv_deal_detail d WHERE TO_DAYS(DATE) = TO_DAYS(NOW())  AND coin_no = ? GROUP BY coin_no ";
		Object params[] = {coin_no};
			inesvDayMarketDto = queryRunner.query(querySql,new BeanHandler<DayMarketDto>(DayMarketDto.class),params);
		return inesvDayMarketDto;
	}
	
	/*
	 * 查询官方账号
	 */
	public ContactDto queryByContact() throws Exception{
		String sql = "SELECT authority_account FROM t_inesv_contact ORDER BY id DESC LIMIT 1";
		ContactDto contactDto = new ContactDto();
		contactDto = queryRunner.query(sql, new BeanHandler<ContactDto>(ContactDto.class));
		return contactDto;
	}
	
	/*
	 * 函数调用
	 */
	public ResultFunctionDto queryByFunction(Integer user_no,Integer level_grade) throws Exception{
		String sql = "SELECT get_level_orgcode(?,?) AS level_user_no";
		Object params[] = {user_no , level_grade};
		ResultFunctionDto resultDunctionDto = queryRunner.query(sql, new BeanHandler<ResultFunctionDto>(ResultFunctionDto.class),params);
		return resultDunctionDto;
	}

	/*
	 * 根据货币ID 查询货币分润比例
	 */
	public CoinLevelProportionDto queryByCoinNo(Long coin_no) throws Exception{
		String sql = "select * from t_coin_level_proportion where coin_no = ? and state != 2";
		CoinLevelProportionDto coinLevelProportionDto = new CoinLevelProportionDto();
			coinLevelProportionDto = queryRunner.query(sql, new BeanHandler<CoinLevelProportionDto>(CoinLevelProportionDto.class),coin_no);
		return coinLevelProportionDto;
	}
	
	/*
	 * 根据用户ID 查询用户上级
	 */
	public InesvUserDto queryUserByID(Integer user_no) throws Exception{
		String sql = "SELECT user_no,username,org_type,org_code FROM t_inesv_user WHERE org_code = (SELECT org_parent_code FROM t_inesv_user WHERE user_no = ?)";
		InesvUserDto relUser = new InesvUserDto();
		relUser = queryRunner.query(sql, new BeanHandler<InesvUserDto>(InesvUserDto.class),user_no);
		return relUser;
	}
	
	/*
	 * 根据委托ID 查询委托记录
	 */
	public EntrustDto queryEntrustByID(Long id,Integer user_no) throws Exception{
		String sql = "SELECT * FROM t_inesv_entrust WHERE id = ? AND user_no = ? FOR UPDATE";
		Object params[] = {id , user_no};
		EntrustDto entrustDto = new EntrustDto();
		entrustDto = queryRunner.query(sql, new BeanHandler<EntrustDto>(EntrustDto.class),params);
		return entrustDto;
	}
	
}
