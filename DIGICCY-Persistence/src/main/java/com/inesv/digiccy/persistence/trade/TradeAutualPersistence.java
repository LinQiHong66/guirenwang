package com.inesv.digiccy.persistence.trade;

import com.inesv.digiccy.dto.CoinAndWalletLinkDto;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CoinLevelProportionDto;
import com.inesv.digiccy.dto.DayMarketDto;
import com.inesv.digiccy.dto.DealDetailDto;
import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserRelations;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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
		 * 实时交易--数据兑换
		 */
		public EntrustDto buy_sell_TradeCompleteBySql(String buyTradeType,EntrustDto buyEntrust,EntrustDto sellEntrust,BigDecimal buy_poundatge,BigDecimal sell_poundatge,BigDecimal tradeNum,BigDecimal buyPrice,BigDecimal sellPrice) throws Exception{
			//买的人的虚拟币
			UserBalanceDto buyXnb=queryUserBalanceInfoByUserNoAndCoinType(buyEntrust.getUser_no(), buyEntrust.getEntrust_coin());
			//买的人的人民币
			UserBalanceDto buyRmb=queryUserBalanceInfoByUserNoAndCoinType(buyEntrust.getUser_no(), 0);
			//4-1.虚拟币
			buyXnb.setEnable_coin(buyXnb.getEnable_coin().add(tradeNum.subtract(tradeNum.multiply(buy_poundatge))));
			if(buyXnb.getEnable_coin().doubleValue()<0){
				System.out.println("================buy，买家虚拟币负数，手动抛出异常================");
				int exception=1/0;
			}
			String updateUserBalanceXnb = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where id=?";
			Object updateXnbParams[] = {buyXnb.getEnable_coin(),buyXnb.getUnable_coin(),buyXnb.getEnable_coin().add(buyXnb.getUnable_coin()),buyXnb.getId()};
			queryRunner.update(updateUserBalanceXnb, updateXnbParams);
			//4-2.人民币
			buyRmb.setUnable_coin(buyRmb.getUnable_coin().subtract(tradeNum.multiply(buyPrice)));
			if(buyRmb.getUnable_coin().doubleValue()<0){
				System.out.println("================buy，买家人民币负数，手动抛出异常================");
				int exception=1/0;
			}
			String updateUserBalanceRmb = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where id=?";
			Object updateRmbParams[] = {buyRmb.getEnable_coin(),buyRmb.getUnable_coin(),buyRmb.getEnable_coin().add(buyRmb.getUnable_coin()),buyRmb.getId()};
			queryRunner.update(updateUserBalanceRmb, updateRmbParams);
			//卖的人虚拟币
			UserBalanceDto sellXnb=queryUserBalanceInfoByUserNoAndCoinType(sellEntrust.getUser_no(), sellEntrust.getEntrust_coin());
			//卖的人人民币
			UserBalanceDto sellRmb=queryUserBalanceInfoByUserNoAndCoinType(sellEntrust.getUser_no(), 0);
			//5-1.虚拟币
			sellXnb.setUnable_coin(sellXnb.getUnable_coin().subtract(tradeNum));
			if(sellXnb.getUnable_coin().doubleValue()<0){
				System.out.println("================sell，卖家虚拟币负数，手动抛出异常================");
				int exception=1/0;
			}
			String updateUnSellUserBalanceXnb = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where id=?";
			Object updateUnSellXnbParams[] = {sellXnb.getEnable_coin(),sellXnb.getUnable_coin(),sellXnb.getUnable_coin().add(sellXnb.getEnable_coin()),sellXnb.getId()};
			queryRunner.update(updateUnSellUserBalanceXnb, updateUnSellXnbParams);
			//5-2.人民币
			sellRmb.setEnable_coin(sellRmb.getEnable_coin().add(tradeNum.multiply(sellPrice).subtract(tradeNum.multiply(sellPrice).multiply(sell_poundatge))));
			if(sellRmb.getEnable_coin().doubleValue()<0){
				System.out.println("================sell，卖家人民币负数，手动抛出异常================");
				int exception=1/0;
			}
			String updateUnSellUserBalanceRmb = "update t_inesv_user_balance set enable_coin=?,unable_coin=?,total_price=? where id=?";
			Object updateUnSellRmbParams[] = {sellRmb.getEnable_coin(),sellRmb.getUnable_coin(),sellRmb.getEnable_coin().add(sellRmb.getUnable_coin()),sellRmb.getId()};
			queryRunner.update(updateUnSellUserBalanceRmb, updateUnSellRmbParams);
				//1.修改交易对象买的委托记录
				buyEntrust.setDeal_num(buyEntrust.getDeal_num().add(tradeNum));// 修改交易数量
				if (buyEntrust.getDeal_num().compareTo(buyEntrust.getEntrust_num()) == 0) {
					buyEntrust.setState(1);// 已交易
					if (buyEntrust.getAttr1() != null
							&& !"".equals(buyEntrust.getAttr1().toString())) {
						operation.updateStatus(buyEntrust.getAttr1());
					}
				}
					String updateBuyEntrust = "update t_inesv_entrust set deal_num=?,state=? where id=?";
					Object updateBuyEntrustParams[] = { buyEntrust.getDeal_num(),
							buyEntrust.getState(), buyEntrust.getId() };
					queryRunner.update(updateBuyEntrust, updateBuyEntrustParams);
				//2.修改交易对象卖的委托记录
				sellEntrust.setDeal_num(sellEntrust.getDeal_num().add(tradeNum));
				if (sellEntrust.getEntrust_num().compareTo(
						sellEntrust.getDeal_num()) == 0) {
					sellEntrust.setState(1);
					if (sellEntrust.getAttr1() != null
							&& !"".equals(sellEntrust.getAttr1().toString())) {
						operation.updateStatus(sellEntrust.getAttr1());
					}
				}
					String opObjEntrust = "update t_inesv_entrust set deal_num=?,state=? where id=?";
					Object opObjEntrustParams[] = { sellEntrust.getDeal_num(),
							sellEntrust.getState(), sellEntrust.getId() };
					queryRunner.update(opObjEntrust, opObjEntrustParams);
			//生成一条新的买卖交易记录
			List<DayMarketDto> dayMarketDtoList=queryDayMarketInfoByCoinType(buyEntrust.getEntrust_coin());//交易货币最新行情
			if(tradeNum.doubleValue()!=0){
				//3-1.生成一条新买的交易记录
				String insertBuyDeal = "INSERT INTO t_inesv_deal_detail(user_no,coin_no,deal_type,deal_price,deal_num,sum_price,poundage,date,attr1,attr2) VALUES(?,?,?,?,?,?,?,?,?,?)";
				Object buyDealParam[] = {buyEntrust.getUser_no(),buyEntrust.getEntrust_coin(),buyEntrust.getEntrust_type(),
						buyPrice,tradeNum,tradeNum.multiply(buyPrice),
					tradeNum.multiply(buyPrice).multiply(buy_poundatge),new Date(),buyEntrust.getId(),sellEntrust.getId()};
				queryRunner.update(insertBuyDeal,buyDealParam);
				//3-2.生成一条新的卖的交易记录
				String insertSellDeal = "INSERT INTO t_inesv_deal_detail(user_no,coin_no,deal_type,deal_price,deal_num,sum_price,poundage,date) VALUES(?,?,?,?,?,?,?,?)";
				Object sellDealParam[] = {sellEntrust.getUser_no(),sellEntrust.getEntrust_coin(),sellEntrust.getEntrust_type(),
					sellPrice,tradeNum,tradeNum.multiply(sellPrice),
					tradeNum.multiply(sellPrice).multiply(sell_poundatge),new Date()};
				queryRunner.update(insertSellDeal,sellDealParam);
			}
			//4-0.添加交易详细记录
			String insertTradeDetail = "INSERT INTO t_trade_detail (coin_type,buy_user,buy_price,buy_number,buy_poundatge,sell_user,sell_price,sell_number,sell_poundatge,buy_entrust,sell_entrust,date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			Object tradeDetailParam[] = {buyEntrust.getEntrust_coin(),buyEntrust.getUser_no(),buyEntrust.getEntrust_price(),tradeNum,tradeNum.multiply(buy_poundatge),
					sellEntrust.getUser_no(),sellEntrust.getEntrust_price(),tradeNum,tradeNum.multiply(sellPrice).multiply(sell_poundatge),buyEntrust.getId(),sellEntrust.getId(),new Date()};
			queryRunner.update(insertTradeDetail,tradeDetailParam);
			//4-1.查詢貨幣最新數據
			DayMarketDto inesvDayMarketDto=queryDealDetailInfoByDayAndCoin(buyEntrust.getEntrust_coin());
			//4-2.修改货币最新市场行情表
			System.out.println("//4-2.修改货币最新市场行情表");
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
			//7.添加手续费记录
			//7-1.买产生的手续费
			String insertBuyPoundage = "INSERT INTO t_inesv_poundage(user_no,optype,type,money,date) VALUES(?,?,?,?,?)";
			Object buyPoundageParam[] = {buyEntrust.getUser_no(),buyEntrust.getEntrust_type(),buyEntrust.getEntrust_coin(),buyPrice.multiply(tradeNum).multiply(buy_poundatge),new Date()};
			queryRunner.update(insertBuyPoundage,buyPoundageParam);
			//7-1.卖产生的手续费
			String insertSellPoundage = "INSERT INTO t_inesv_poundage(user_no,optype,type,money,date) VALUES(?,?,?,?,?)";
			Object sellPoundageParam[] = {sellEntrust.getUser_no(),sellEntrust.getEntrust_type(),sellEntrust.getEntrust_coin(),sellPrice.multiply(tradeNum).multiply(sell_poundatge),new Date()};
			queryRunner.update(insertSellPoundage,sellPoundageParam);
			//买家分红
			userLevelDetailed(buyEntrust.getUser_no(),buyEntrust.getEntrust_coin(),tradeNum.multiply(buy_poundatge),buyPrice.multiply(buy_poundatge),buyEntrust.getId(),buyEntrust.getEntrust_type());
			//卖家分红
			userLevelDetailed(sellEntrust.getUser_no(),sellEntrust.getEntrust_coin(),tradeNum.multiply(sell_poundatge),sellPrice.multiply(sell_poundatge),sellEntrust.getId(),sellEntrust.getEntrust_type());
			return buyEntrust;
		}
		
		/*
		 * 交易分红
		 */
		public void userLevelDetailed(Integer user_no, Integer coin_no, BigDecimal tradeNum, BigDecimal entrustPrice, Long entrustNo, Integer entrustType) throws Exception{
			//查询币种相应上级分红比例
			CoinLevelProportionDto coinLevelProportionDto = queryByCoinNo(Long.valueOf(coin_no)); //货币分红比例
			//买家上级分红
			BigDecimal level_one = null;
			BigDecimal level_two = null;
			BigDecimal level_three = null;
			BigDecimal level_four = null;
			BigDecimal level_five = null;
			if(coinLevelProportionDto != null && coinLevelProportionDto.getState() == 0){
				if(coinLevelProportionDto.getLevel_type() == 0) {
					level_one = coinLevelProportionDto.getLevel_one().multiply(entrustPrice);
					level_two = coinLevelProportionDto.getLevel_two().multiply(entrustPrice);
					level_three = coinLevelProportionDto.getLevel_one().multiply(entrustPrice);
					level_four = coinLevelProportionDto.getLevel_two().multiply(entrustPrice);
					level_five = coinLevelProportionDto.getLevel_one().multiply(entrustPrice);
				}else {
					level_one = coinLevelProportionDto.getLevel_one().multiply(tradeNum);
					level_two = coinLevelProportionDto.getLevel_two().multiply(tradeNum);
					level_three = coinLevelProportionDto.getLevel_one().multiply(tradeNum);
					level_four = coinLevelProportionDto.getLevel_two().multiply(tradeNum);
					level_five = coinLevelProportionDto.getLevel_one().multiply(tradeNum);
				}
			}else {
				return;
			}
			
			InesvUserDto buyUserDto1 = queryUserByID(user_no);
			if(buyUserDto1 == null || buyUserDto1.getUser_no() == user_no) {
				return;
			}else {
				bonusOperation.doLevelBonus(entrustNo, level_one, coinLevelProportionDto.getLevel_type(), buyUserDto1.getUser_no(), user_no, entrustType);
			}
			InesvUserDto buyUserDto2 = queryUserByID(buyUserDto1.getUser_no());
			if(buyUserDto2 == null || buyUserDto2.getUser_no() == buyUserDto1.getUser_no()) {
				return;
			}else {
				bonusOperation.doLevelBonus(entrustNo, level_two, coinLevelProportionDto.getLevel_type(), buyUserDto2.getUser_no(), buyUserDto1.getUser_no(), entrustType);
			}
			InesvUserDto buyUserDto3 = queryUserByID(buyUserDto2.getUser_no());
			if(buyUserDto3 == null || buyUserDto3.getUser_no() == buyUserDto2.getUser_no()) {
				return;
			}else {
				bonusOperation.doLevelBonus(entrustNo, level_three, coinLevelProportionDto.getLevel_type(), buyUserDto3.getUser_no(), buyUserDto2.getUser_no(), entrustType);
			}
			InesvUserDto buyUserDto4 = queryUserByID(buyUserDto3.getUser_no());
			if(buyUserDto4 == null || buyUserDto4.getUser_no() == buyUserDto3.getUser_no()) {
				return;
			}else {
				bonusOperation.doLevelBonus(entrustNo, level_four, coinLevelProportionDto.getLevel_type(), buyUserDto4.getUser_no(), buyUserDto3.getUser_no(), entrustType);
			}
			InesvUserDto buyUserDto5 = queryUserByID(buyUserDto4.getUser_no());
			if(buyUserDto5 == null || buyUserDto5.getUser_no() == buyUserDto4.getUser_no()) {
				return;
			}else {
				bonusOperation.doLevelBonus(entrustNo, level_five, coinLevelProportionDto.getLevel_type(), buyUserDto5.getUser_no(), buyUserDto4.getUser_no(), entrustType);
			}
		}
		
		/**
		 * 委托实时买卖交易--查询货币手续费
		 */
		@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	    public void validateTradeCoinActualBySQL(EntrustDto entrustDto) throws Exception{
			List<EntrustDto> entrustList = null;
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
			//1：判断委托记录是买还是卖
	        System.out.println("-------我们来操作一下mysql交易--buy--sell");
			TradeSql("buySellTradeSQL","sql",entrustDto,entrustList,buy_poundatge,sell_poundatge);
	        System.out.println("结束买-卖的记录进行交易");
	    }
		
		/**
		 * 委托实时买卖交易----递归查询符合买卖的委托单号
		 */
		public void TradeSql(String entrustType,String sqlType,EntrustDto buy_sell_EntrustDto,List<EntrustDto> buy_sell_entrustList,BigDecimal buy_poundatge,BigDecimal sell_poundatge) throws Exception{
			List<EntrustDto> getUnSellList = null;
			if(buy_sell_EntrustDto.getEntrust_type()==0){
				//币合
				getUnSellList=queryEntrustByEntrustPriceEntrustCoinAndEntrustTypeAndState(buy_sell_EntrustDto.getEntrust_price(),buy_sell_EntrustDto.getEntrust_coin(),buy_sell_EntrustDto.getUser_no(),1,0);
				//卖方无数据不交易
				if(getUnSellList!=null && getUnSellList.size()>0){
					EntrustDto sellEntrust=getUnSellList.get(0);
					// 交易数量(取委托数量 与 交易对象的未交易数量 的较小值)
					BigDecimal tradeNum = buy_sell_EntrustDto.getEntrust_num()
							.subtract(buy_sell_EntrustDto.getDeal_num())
							.min(sellEntrust.getEntrust_num()
									.subtract(sellEntrust.getDeal_num()));
					// 先交易
					buy_sell_EntrustDto = buy_sell_TradeCompleteBySql(entrustType,buy_sell_EntrustDto,sellEntrust,
							buy_poundatge, sell_poundatge,
							tradeNum, buy_sell_EntrustDto.getEntrust_price(), sellEntrust.getEntrust_price());
					//交易完了再判断
					//如果买的用户还有未交易数量则递归
					if(buy_sell_EntrustDto.getState()==0){
						TradeSql("buyTradeSQL","sql",buy_sell_EntrustDto,getUnSellList,buy_poundatge,sell_poundatge);
					}
				}
			}
			if(buy_sell_EntrustDto.getEntrust_type()==1){
				getUnSellList=queryEntrustByEntrustPriceEntrustCoinAndEntrustTypeAndState(buy_sell_EntrustDto.getEntrust_price(),buy_sell_EntrustDto.getEntrust_coin(),buy_sell_EntrustDto.getUser_no(),0,0);
				//卖方无数据不交易
				if(getUnSellList!=null && getUnSellList.size()>0){
					EntrustDto buyEntrust=getUnSellList.get(0);
					// 交易数量(取委托数量 与 交易对象的未交易数量 的较小值)
					BigDecimal tradeNum = buy_sell_EntrustDto.getEntrust_num()
							.subtract(buy_sell_EntrustDto.getDeal_num())
							.min(buyEntrust.getEntrust_num()
									.subtract(buyEntrust.getDeal_num()));
					// 先交易
					buy_sell_EntrustDto = buy_sell_TradeCompleteBySql(entrustType,buyEntrust,buy_sell_EntrustDto,
							buy_poundatge, sell_poundatge,
							tradeNum, buyEntrust.getEntrust_price(), buy_sell_EntrustDto.getEntrust_price());
					//交易完了再判断
					//如果买的用户还有未交易数量则递归
					if(buy_sell_EntrustDto.getState()==0){
						TradeSql("sellTradeSQL","sql",buy_sell_EntrustDto,getUnSellList,buy_poundatge,sell_poundatge);
					}
				}
			}
		}
		
		/**
		 * 根据委托价格，货币类型，交易类型，委托状态查找委托记录
		 */
		public List<EntrustDto> queryEntrustByEntrustPriceEntrustCoinAndEntrustTypeAndState(BigDecimal entrustPrice, Integer entrustCoin,Integer userNo,Integer entrustType,Integer state) throws SQLException{
			String sql="select * from t_inesv_entrust where entrust_price=? and entrust_coin =? and entrust_type=? and state=? and entrust_num!=deal_num order by date asc for update";
			Object params[] = {entrustPrice,entrustCoin,entrustType,state};
			/*String sql = null;
			if(entrustType==0) {	//卖家-寻找买的用户
				sql="select * from t_inesv_entrust where entrust_price>=? and entrust_coin =? and entrust_type=? and state=? and entrust_num!=deal_num and user_no!=? order by date asc,entrust_price desc for update";
			}else if(entrustType==1){		//买家-寻找卖的用户
				sql="select * from t_inesv_entrust where entrust_price<=? and entrust_coin =? and entrust_type=? and state=? and entrust_num!=deal_num and user_no!=? order by date asc,entrust_price desc for update";
			}
			Object params[] = {entrustPrice,entrustCoin,entrustType,state,userNo};*/
			List<EntrustDto> list = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
			return list;
		}
		
		/**
		 * 根据委托单号查询记录
		 */
		public EntrustDto queryEntrustById(Long id) throws SQLException{
			String sql="select * from t_inesv_entrust where id = ? for update";
			Object params[] = {id};
			EntrustDto dto= queryRunner.query(sql,new BeanHandler<EntrustDto>(EntrustDto.class),params);
			return dto;
		}
		
		/**
		 * 根据用户ID+货币ID 查询用户资产
		 */
		private UserBalanceDto queryUserBalanceInfoByUserNoAndCoinType(Integer user_no, Integer entrust_coin) throws SQLException {
	        String querySql = "select * from t_inesv_user_balance where user_no=? and coin_type=? for update";
	        Object params[] = {user_no,entrust_coin};
	        UserBalanceDto userBalanceInfo=queryRunner.query(querySql,new BeanHandler<UserBalanceDto>(UserBalanceDto.class),params);
			return userBalanceInfo;
		}
		
		/**
		 * 根据货币ID 查询每日行情表
		 */
		private List<DayMarketDto> queryDayMarketInfoByCoinType(Integer entrust_coin) throws SQLException {
	        String querySql = "SELECT * FROM t_inesv_day_market WHERE TO_DAYS(DATE) = TO_DAYS(NOW()) and coin_type=?";
	        Object params[] = {entrust_coin};
	        List<DayMarketDto> dayMarketDtoList=queryRunner.query(querySql,new BeanListHandler<DayMarketDto>(DayMarketDto.class),params);
			return dayMarketDtoList;
		}
		
		/**
		 * 根据货币ID 查询货币最新信息
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
		 * 根据货币ID 查询货币分红比例
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
			String sql = "SELECT user_no FROM t_inesv_user WHERE org_code = (SELECT org_parent_code FROM t_inesv_user WHERE user_no = ?)";
			InesvUserDto relUser = new InesvUserDto();
			relUser = queryRunner.query(sql, new BeanHandler<InesvUserDto>(InesvUserDto.class),user_no);
			return relUser;
		}
}
