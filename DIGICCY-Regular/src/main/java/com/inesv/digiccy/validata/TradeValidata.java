package com.inesv.digiccy.validata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.api.command.DayMarketCommand;
import com.inesv.digiccy.api.command.EntrustCommand;
import com.inesv.digiccy.api.command.UserBalanceCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.AssessDto;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CoinTranAstrictDto;
import com.inesv.digiccy.dto.DayMarketDto;
import com.inesv.digiccy.dto.DealDetailDto;
import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.dto.InesvDayMarket;
import com.inesv.digiccy.dto.StaticParamsDto;
import com.inesv.digiccy.dto.SubCoreDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.persistence.bonus.BonusOperation;
import com.inesv.digiccy.persistence.plan.PlanOperation;
import com.inesv.digiccy.persistence.reg.RegUserPersistence;
import com.inesv.digiccy.persistence.trade.TradePersistence;
import com.inesv.digiccy.query.QueryAssessInfo;
import com.inesv.digiccy.query.QueryCoinLevelProportion;
import com.inesv.digiccy.query.QueryDayMarketInfo;
import com.inesv.digiccy.query.QueryDealDetailInfo;
import com.inesv.digiccy.query.QueryEntrustDealInfo;
import com.inesv.digiccy.query.QueryEntrustInfo;
import com.inesv.digiccy.query.QueryStaticParam;
import com.inesv.digiccy.query.QuerySubCore;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import com.inesv.digiccy.query.QueryUserRelations;
import com.inesv.digiccy.query.coin.QueryCoin;
import com.inesv.digiccy.query.coin.QueryCoinTranAstrict;
import com.inesv.digiccy.util.MD5;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class TradeValidata {
	private static Logger log = LoggerFactory.getLogger(TradeValidata.class);
	@Autowired
	private QuerySubCore querySubCore;

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private QueryAssessInfo queryAssessInfo;

	@Autowired
	private QueryDealDetailInfo queryDealDetailInfo;

	@Autowired
	private QueryEntrustInfo queryEntrustInfo;

	@Autowired
	private QueryUserBalanceInfo queryUserBalanceInfo;

	@Autowired
	QueryDayMarketInfo queryDayMarketInfo;

	@Autowired
	QueryEntrustDealInfo queryEntrustDealInfo;

	@Autowired
	QueryStaticParam queryStaticParam;

	@Autowired
	QueryCoin queryCoin;

	@Autowired
	QueryCoinLevelProportion queryProportion;

	@Autowired
	QueryUserRelations queryRelations;

	@Autowired
	TradePersistence tradePersistence;

	@Autowired
	BonusOperation bonusOperation;

	@Autowired
	QueryCoinTranAstrict queryCoinTranAstrict;

	@Autowired
	RegUserPersistence regUserPersistence;

	@Autowired
	PlanOperation planOperation;

	@Autowired
	QueryCoinLevelProportion queryCoinLevelProportion;

	/**
	 * 买卖交易
	 * 
	 * @param userNo
	 *            用户编号
	 * @param tradeNum
	 *            交易数量
	 * @param tradePrice
	 *            交易价格
	 * @param poundatge
	 *            手续费
	 * @param tradePassword
	 *            交易密码
	 * @param coinType
	 *            币种类型
	 * @param tradeType
	 *            交易类型
	 * @return
	 */
	/*public void pressureUtil(String userNo, BigDecimal tradeNum, BigDecimal tradePrice, BigDecimal poundatge,
			String tradePassword, String coinType, String tradeType, BigDecimal poundageRate) {

		UserInfoDto uid = querySubCore.getUserInfo(Integer.parseInt(userNo));
		// 用户人民币的财务
		UserBalanceDto rmb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, "0");
		// 用户该币的财务，没有对应的货币财务信息就添加相应的财务信息
		UserBalanceDto xnb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, coinType);
		if (xnb == null) {
			// 不存在该用户某种币的资产记录，则添加记录
			if (queryCoin.queryCoinTypeByCoinNo(Integer.parseInt(coinType)) != null) {
				UserBalanceCommand userBalanceCommand = new UserBalanceCommand(Long.parseLong("0"),
						Integer.parseInt(userNo), Integer.parseInt(coinType), BigDecimal.ZERO, BigDecimal.ZERO,
						BigDecimal.ZERO, "", new Date(), "insert");
				commandGateway.sendAndWait(userBalanceCommand);
			}
			// 再判断
			xnb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, coinType);
		}
		// 手续费
		EntrustCommand command = null;
		if (tradeType.equals("buy")) {
			if ((poundatge == null || ("").equals(poundatge))) {
				if (poundageRate != null && !poundageRate.equals("")) {
					poundageRate = queryCoin.queryBuyPoundatge(Long.valueOf(coinType)).getBuy_poundatge();
					poundatge = tradePrice.multiply(tradeNum).multiply(poundageRate);
					command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 0,
							tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "insert");
				}
			} else {
				if (poundageRate != null && !poundageRate.equals("")) {
					if (tradePrice.multiply(tradeNum).multiply(poundageRate).compareTo(poundatge) != 0) {
						poundageRate = queryCoin.queryBuyPoundatge(Long.valueOf(coinType)).getSell_poundatge();
						poundatge = tradePrice.multiply(tradeNum).multiply(poundageRate);
						command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(),
								Integer.parseInt(coinType), 0, tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0,
								new Date(), "insert");
					}
				}
			}
		} else if (tradeType.equals("sell")) {
			if ((poundatge == null || ("").equals(poundatge))) {
				if (poundageRate != null && !poundageRate.equals("")) {
					poundageRate = queryCoin.queryBuyPoundatge(Long.valueOf(coinType)).getBuy_poundatge();
					poundatge = tradePrice.multiply(tradeNum).multiply(poundageRate);
					command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 1,
							tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "insert");

				}
			} else {
				if (poundageRate != null && !poundageRate.equals("")) {
					if (tradePrice.multiply(tradeNum).multiply(poundageRate).compareTo(poundatge) != 0) {
						poundageRate = queryCoin.queryBuyPoundatge(Long.valueOf(coinType)).getSell_poundatge();
						poundatge = tradePrice.multiply(tradeNum).multiply(poundageRate);
						command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(),
								Integer.parseInt(coinType), 1, tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0,
								new Date(), "insert");
					}
				}
			}
		}
		commandGateway.sendAndWait(command);
	}*/

	/**
	 * 委托记录
	 * 
	 * @return
	 */
	public Map<Object, Object> validataEntrustListByUserNo(String userNo) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<EntrustDto> EntrustList = queryEntrustInfo.queryEntrustInfoByUserNo(userNo);
		if (EntrustList != null) {
			map.put("EntrustList", EntrustList);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 所有的评论
	 * 
	 * @return
	 */
	public Map<Object, Object> validataAssessList() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<AssessDto> assessList = queryAssessInfo.queryAssessList();
		if (assessList != null) {
			map.put("assessList", assessList);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 交易记录
	 * 
	 * @return
	 */
	public Map<Object, Object> validataDealDetailListByUserNo(String userNo) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<DealDetailDto> dealDetailList = queryDealDetailInfo.queryDealDetailInfoByUserNo(userNo);
		if (dealDetailList != null) {
			map.put("dealDetailList", dealDetailList);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 撤销委托记录
	 * 
	 * @param id
	 * @param userNo
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public Map<String, Object> validateDelEntrust(Long id, Integer userNo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		// 判断该记录是否存在
		EntrustDto ed = queryEntrustDealInfo.queryEntrustInfoByID(id, userNo);
		if (ed == null) {
			map.put("code", ResponseCode.FAIL_TRADE_DELENTRUST);
			map.put("desc", ResponseCode.FAIL_TRADE_DELENTRUST_DESC);
			return map;
		}
		if (ed.getState() != 0) {
			map.put("code", ResponseCode.FAIL_TRADE_DELENTRUST_STATE);
			map.put("desc", ResponseCode.FAIL_TRADE_DELENTRUST_STATE_DESC);
			return map;
		}
		// 判断用户是否存在
		UserInfoDto user = querySubCore.getUserInfo(userNo);
		if (user == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		// 用户该币的财务
		UserBalanceDto xnb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo.toString(),
				ed.getEntrust_coin().toString());
		// 用户人民币的财务
		UserBalanceDto rmb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo.toString(), "0");
		if (xnb == null || rmb == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		// 0：买, 1：卖
		/*
		 * if(ed.getEntrust_type().equals(0)){
		 * if(xnb.getUnable_coin().subtract((ed.getEntrust_num().subtract(ed.getDeal_num
		 * ()))).signum()<0 ||
		 * rmb.getUnable_coin().subtract((ed.getEntrust_price().multiply((ed.
		 * getEntrust_num().subtract(ed.getDeal_num())))).add(ed.getPiundatge())).signum
		 * ()<0){ map.put("code",ResponseCode.FAIL_TRADE_DELENTRUST_STATE);
		 * map.put("desc",ResponseCode.FAIL_TRADE_DELENTRUST_STATE_DESC); return map; }
		 * }else if(ed.getEntrust_type().equals(1)){
		 * if(xnb.getUnable_coin().subtract((ed.getEntrust_num().subtract(ed.getDeal_num
		 * ()))).signum()<0 ||
		 * rmb.getUnable_coin().subtract(ed.getEntrust_price().multiply(ed.
		 * getEntrust_num().subtract(ed.getDeal_num())).add(ed.getPiundatge())).signum()
		 * <0){ map.put("code",ResponseCode.FAIL_TRADE_DELENTRUST_STATE);
		 * map.put("desc",ResponseCode.FAIL_TRADE_DELENTRUST_STATE_DESC); return map; }
		 * }
		 */
		// 手续费比率
		/*
		 * StaticParamsDto
		 * staticParams=queryStaticParam.getStaticParamByParam("poundageRate");
		 * BigDecimal poundatgeRate=staticParams.getValue();
		 */
		if (ed.getEntrust_type() == 0) {
			BigDecimal returnrmb = ed.getEntrust_price().multiply(ed.getEntrust_num().subtract(ed.getDeal_num()));
			if (rmb.getUnable_coin().subtract(returnrmb).signum() < 0) {
				map.put("code", ResponseCode.FAIL_TRADE_DELENTRUST_STATE);
				map.put("desc", ResponseCode.FAIL_TRADE_DELENTRUST_STATE_DESC);
				return map;
			}
		} else if (ed.getEntrust_type() == 1) {
			BigDecimal returnpound = (ed.getEntrust_num().subtract(ed.getDeal_num()));
			if (xnb.getUnable_coin().subtract(returnpound).signum() < 0) {
				map.put("code", ResponseCode.FAIL_TRADE_DELENTRUST_STATE);
				map.put("desc", ResponseCode.FAIL_TRADE_DELENTRUST_STATE_DESC);
				return map;
			}
		}
		// 发送命令
		try {
			regUserPersistence.updateBalanceEntrust(ed);// 回滚用户数据
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL_TRADE_DELENTRUST_STATE);
			map.put("desc", ResponseCode.FAIL_TRADE_DELENTRUST_STATE_DESC);
		}
		return map;
	}

	/**
	 * 买卖交易
	 * 
	 * @param userNo
	 *            用户编号
	 * @param tradeNum
	 *            交易数量
	 * @param tradePrice
	 *            交易价格
	 * @param poundatge
	 *            手续费
	 * @param tradePassword
	 *            交易密码
	 * @param coinType
	 *            币种类型
	 * @param tradeType
	 *            交易类型
	 * @return
	 */
	/*public Map<String, Object> validateTradeCoin(String userNo, BigDecimal tradeNum, BigDecimal tradePrice,
			BigDecimal poundatge, String tradePassword, String coinType, String tradeType) {
		Map<String, Object> map = new HashMap<>();
		// 判断交易数量
		if (tradeNum.intValue() <= 0) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		// 判断用户是否存在
		UserInfoDto uid = querySubCore.getUserInfo(Integer.parseInt(userNo));
		if (uid == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		// 判断密码
		if (!new MD5().getMD5(tradePassword).equals(uid.getDeal_pwd())) {
			map.put("code", ResponseCode.FAIL_TRADE_PASSWORD);
			map.put("desc", ResponseCode.FAIL_TRADE_PASSWORD_DESC);
			return map;
		}
		// 用户人民币的财务
		UserBalanceDto rmb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, "0");
		if (rmb == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		// 用户该币的财务，没有对应的货币财务信息就添加相应的财务信息
		UserBalanceDto xnb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, coinType);
		if (xnb == null) {
			// 不存在该用户某种币的资产记录，则添加记录
			if (queryCoin.queryCoinTypeByCoinNo(Integer.parseInt(coinType)) != null) {
				UserBalanceCommand userBalanceCommand = new UserBalanceCommand(Long.parseLong("0"),
						Integer.parseInt(userNo), Integer.parseInt(coinType), BigDecimal.ZERO, BigDecimal.ZERO,
						BigDecimal.ZERO, "", new Date(), "insert");
				commandGateway.sendAndWait(userBalanceCommand);
			}
			// 再判断
			xnb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, coinType);
			if (xnb == null) {
				map.put("code", ResponseCode.FAIL_TRADE_ADDTRUST_STATE);
				map.put("desc", ResponseCode.FAIL_TRADE_ADDTRUST_STATE_DESC);
				return map;
			}
		}
		// 手续费
		EntrustCommand command = null;
		BigDecimal poundageRate = null;
		DealDetailDto dealDetailDto = new DealDetailDto();
		CoinTranAstrictDto coinTranAstrictDto = queryCoinTranAstrict
				.queryAllCoinTranAstrictByCoinNo(String.valueOf(coinType));
		DealDetailDto inesvDayMarket = queryDayMarketInfo.queryNewesDeal(Integer.valueOf(coinType));
		BigDecimal newdeal = null;
		if (inesvDayMarket != null) {
			newdeal = inesvDayMarket.getDeal_price();
		} else {
			newdeal = new BigDecimal(0);
		}
		if (tradeType.equals("buy")) {
			if ((poundatge == null || poundatge.doubleValue() == 0)) {
				poundageRate = queryCoin.queryBuyPoundatge(Long.valueOf(coinType)).getBuy_poundatge();
				if (poundageRate == null) {
					poundageRate = new BigDecimal(0);
				}
				poundatge = tradePrice.multiply(tradeNum).multiply(poundageRate);
				if (rmb.getEnable_coin().subtract(tradeNum.multiply(tradePrice).add(poundatge)).signum() < 0) {
					map.put("code", ResponseCode.FAIL_TRADE_INSUFFICIENT);
					map.put("desc", ResponseCode.FAIL_TRADE_INSUFFICIENT_DESC);
					return map;
				}
				if (coinTranAstrictDto.getState() == 0) {
					command = new EntrustCommand(0L, uid.getUser_no(), Integer.parseInt(coinType), 0,
							tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
				} else {
					if (coinTranAstrictDto.getBuy_min_price().doubleValue() >= tradePrice.doubleValue()
							|| coinTranAstrictDto.getBuy_max_price().doubleValue() <= tradePrice.doubleValue()) {
						map.put("code", "202");
						map.put("desc", "不在交易指定范围内，暂时无法受理");
						return map;
					}
					if (tradeNum.multiply(tradePrice).doubleValue() >= coinTranAstrictDto.getSingle_max_price()
							.doubleValue()
							|| coinTranAstrictDto.getSingle_min_price().doubleValue() >= tradeNum.multiply(tradePrice)
									.doubleValue()) {
						map.put("code", "202");
						map.put("desc", "不在交易指定范围内，暂时无法受理");
						return map;
					}
					BigDecimal rose_astrict = null;
					BigDecimal drop_astrict = null;
					dealDetailDto = queryDayMarketInfo.queryNewesDealOfBuy(Integer.valueOf(coinType));// 查询开盘前最后一条买的记录
					if (dealDetailDto != null) {
						rose_astrict = dealDetailDto.getDeal_price().add(dealDetailDto.getDeal_price()
								.multiply(new BigDecimal(coinTranAstrictDto.getRose_astrict().doubleValue() / 100)));
						// 开盘前最后一条买的记录的成交价+开盘前最后一条买的记录的成交价*涨幅
						drop_astrict = dealDetailDto.getDeal_price().add(dealDetailDto.getDeal_price()
								.multiply(new BigDecimal(coinTranAstrictDto.getDrop_astrict().doubleValue() / 100)));
						// 开盘前最后一条买的记录的成交价+开盘前最后一条买的记录的成交价*跌幅
						if (rose_astrict.doubleValue() < tradePrice.doubleValue()
								|| drop_astrict.doubleValue() > tradePrice.doubleValue()) {
							map.put("code", "202");
							map.put("desc", "不在交易指定范围内，暂时无法受理");
							return map;
						}
					}
					command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 0,
							tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
				}
			}
		} else if (tradeType.equals("sell")) {
			if ((poundatge == null || poundatge.doubleValue() == 0)) {
				poundageRate = queryCoin.queryBuyPoundatge(Long.valueOf(coinType)).getSell_poundatge();
				if (poundageRate == null) {
					poundageRate = new BigDecimal(0);
				}
				poundatge = tradePrice.multiply(tradeNum).multiply(poundageRate);
				if (rmb.getEnable_coin().subtract(poundatge).signum() < 0
						|| xnb.getEnable_coin().subtract(tradeNum).signum() < 0) {
					map.put("code", ResponseCode.FAIL_TRADE_INSUFFICIENT);
					map.put("desc", ResponseCode.FAIL_TRADE_INSUFFICIENT_DESC);
					return map;
				}
				if (coinTranAstrictDto == null) {
					command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 0,
							tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
				}
				if (coinTranAstrictDto.getState() == 0) {
					command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 1,
							tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
				} else if (coinTranAstrictDto.getState() == 0) {
					command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 0,
							tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
				} else {
					if (coinTranAstrictDto.getSell_min_price().doubleValue() >= tradePrice.doubleValue()
							|| coinTranAstrictDto.getSell_max_price().doubleValue() <= tradePrice.doubleValue()) {
						map.put("code", "202");
						map.put("desc", "不在交易指定范围内，暂时无法受理");
						return map;
					}
					if (tradeNum.multiply(tradePrice).doubleValue() > coinTranAstrictDto.getSingle_max_price()
							.doubleValue()
							|| coinTranAstrictDto.getSingle_min_price().doubleValue() > tradeNum.multiply(tradePrice)
									.doubleValue()) {
						map.put("code", "202");
						map.put("desc", "不在交易指定范围内，暂时无法受理");
						return map;
					}
					BigDecimal rose_astrict = null;
					BigDecimal drop_astrict = null;
					dealDetailDto = queryDayMarketInfo.queryNewesDealOfBuy(Integer.valueOf(coinType));// 查询开盘前最后一条买的记录
					if (dealDetailDto != null) {
						rose_astrict = dealDetailDto.getDeal_price().add(dealDetailDto.getDeal_price()
								.multiply(new BigDecimal(coinTranAstrictDto.getRose_astrict().doubleValue() / 100)));
						// 开盘前最后一条买的记录的成交价+开盘前最后一条买的记录的成交价*涨幅
						drop_astrict = dealDetailDto.getDeal_price().add(dealDetailDto.getDeal_price()
								.multiply(new BigDecimal(coinTranAstrictDto.getDrop_astrict().doubleValue() / 100)));
						// 开盘前最后一条买的记录的成交价+开盘前最后一条买的记录的成交价*跌幅
						if (rose_astrict.doubleValue() < tradePrice.doubleValue()
								|| drop_astrict.doubleValue() > tradePrice.doubleValue()) {
							map.put("code", "202");
							map.put("desc", "不在交易指定范围内，暂时无法受理");
							return map;
						}
					}
					command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 0,
							tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
				}
			}
		}
		// 发送命令
		try {
			commandGateway.sendAndWait(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}*/
	
	/**
	 * 实时买卖交易
	 * 
	 * @param userNo
	 *            用户编号
	 * @param tradeNum
	 *            交易数量
	 * @param tradePrice
	 *            交易价格
	 * @param poundatge
	 *            手续费
	 * @param tradePassword
	 *            交易密码
	 * @param coinType
	 *            币种类型
	 * @param tradeType
	 *            交易类型
	 * @return
	 */
	 @Transactional(rollbackFor={Exception.class, RuntimeException.class})
	    public Map<String , Object> validateTradeCoinActual(String userNo,BigDecimal tradeNum,BigDecimal tradePrice,BigDecimal poundatge,String tradePassword, String coinType, String convertType, String tradeType){
	    	Map<String , Object> map = new HashMap<>();
	        //判断交易数量
	        if(tradeNum.doubleValue()<0.01){
	            map.put("code",ResponseCode.FAIL);
	            map.put("desc","交易数量不能少于0.01！");
	            return map;
	        }
	        //判断交易价格
	        if(tradePrice.doubleValue()<=0){
	            map.put("code",ResponseCode.FAIL);
	            map.put("desc","交易金额异常！");
	            return map;
	        }
	        //判断购买货币和兑换货币是否一致
	        if(coinType.equals(convertType)) {
	        	map.put("code",ResponseCode.FAIL);
	            map.put("desc","购买货币与兑换货币不能一致！");
	            return map;
	        }
	        //判断用户是否存在
	        UserInfoDto uid = querySubCore.getUserInfo(Integer.parseInt(userNo));
	        if(uid == null){
	            map.put("code",ResponseCode.FAIL);
	            map.put("desc",ResponseCode.FAIL_DESC);
	            return map;
	        }
	        //判断用户是否机构或者子机构
	        if(uid.getOrg_type() != null){
	        	if(uid.getOrg_type() == 0 || uid.getOrg_type() == 1) {
	        		map.put("code",ResponseCode.FAIL);
	                map.put("desc","机构或子机构用户不能交易，请见谅！");
	                return map;
	        	}
	        }
	        //判断密码
	        if(!new MD5().getMD5(tradePassword).equals(uid.getDeal_pwd())){
	            map.put("code",ResponseCode.FAIL_TRADE_PASSWORD);
	            map.put("desc",ResponseCode.FAIL_TRADE_PASSWORD_DESC);
	            return map;
	        }
	        //判断货币交易状态
	        CoinDto coinDto = queryCoinLevelProportion.queryCoinDto(coinType);
	        if(coinDto.getState() == 2) {
	        	map.put("code",ResponseCode.FAIL);
	            map.put("desc","该货币暂不能交易，请见谅！");
	            return map;
	        }
	        //用户交易使用的人民币或者虚拟币的财务
	        UserBalanceDto xnb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, convertType);
	        UserBalanceDto rmb = new UserBalanceDto();
	        if(xnb == null) {
	        	map.put("code",ResponseCode.FAIL);
	            map.put("desc",ResponseCode.FAIL_DESC);
	            return map;
	        }
	        List<InesvDayMarket> dtoList = new ArrayList<InesvDayMarket>();
	        DealDetailDto dealDto = new DealDetailDto();
	        if(Integer.valueOf(convertType) != 0) {
	        	rmb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, convertType);
	        	dtoList = queryDayMarketInfo.getDayMarketInfoByCoin(Integer.valueOf(convertType));
	        	if(dtoList.size() != 0) {
	        		if(dtoList.get(0).getDeal_price().doubleValue() == 0 || dtoList.get(0).getDeal_price() == null) {
	        			map.put("code",ResponseCode.FAIL);
	        			map.put("desc","选择兑换货币最新成交价异常，委托失败！");
	        			return map;
	        		}
	        	}else {
	        		dealDto = queryDayMarketInfo.queryNewesDeal(Integer.valueOf(convertType));
	        		if(dealDto == null) {
	        			map.put("code",ResponseCode.FAIL);
	        			map.put("desc","选择兑换货币最新成交价异常，委托失败！");
	        			return map;
	        		}
	        		if(dealDto.getDeal_price().doubleValue() == 0 || dealDto.getDeal_price() == null) {
	        			map.put("code",ResponseCode.FAIL);
	        			map.put("desc","选择兑换货币最新成交价异常，委托失败！");
	        			return map;
	        		}
	        	}
	        }else {
	        	rmb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, convertType);
	            if(rmb == null){
	                map.put("code",ResponseCode.FAIL);
	                map.put("desc",ResponseCode.FAIL_DESC);
	                return map;
	            }
	        }
	        //用户该币的财务，没有对应的货币财务信息就添加相应的财务信息
	        xnb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, coinType);
	        if(xnb==null){
	        	//不存在该用户某种币的资产记录，则添加记录
	        	if(queryCoin.queryCoinTypeByCoinNo(Integer.parseInt(coinType))!=null){
	        		UserBalanceCommand userBalanceCommand=new UserBalanceCommand(Long.parseLong("0"),Integer.parseInt(userNo),Integer.parseInt(coinType),BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,"",new Date(),"insert");
	            	commandGateway.sendAndWait(userBalanceCommand);
	        	}
	        	//再判断
	        	xnb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, coinType);
	        	if(xnb==null){
	        		map.put("code",ResponseCode.FAIL_TRADE_ADDTRUST_STATE);
	                map.put("desc",ResponseCode.FAIL_TRADE_ADDTRUST_STATE_DESC);
	                return map;
	        	}
	        }
	        //手续费
	        EntrustCommand command=null;
	        DealDetailDto dealDetailDto = new DealDetailDto();
	        CoinTranAstrictDto coinTranAstrictDto = queryCoinTranAstrict.queryAllCoinTranAstrictByCoinNo(String.valueOf(coinType));
	        if (tradeType.equals("buy")) {
	        		if(Integer.valueOf(convertType) != 0) {
	        			if(dtoList.size() == 0 && dealDto != null) {
	        				if(tradeNum.doubleValue() * tradePrice.doubleValue() > rmb.getEnable_coin().doubleValue()*dealDto.getDeal_price().doubleValue()) {
		        				map.put("code",ResponseCode.FAIL);
		        				map.put("desc","抱歉，兑换货币兑换当前交易价支付，余额不足，委托失败！");
		        				return map;
		        			}
	        			}
	        			if(tradeNum.doubleValue() * tradePrice.doubleValue() > rmb.getEnable_coin().doubleValue()*dtoList.get(0).getNewes_deal().doubleValue()) {
	        				map.put("code",ResponseCode.FAIL);
	        				map.put("desc","抱歉，兑换货币兑换当前交易价支付，余额不足，委托失败！");
	        				return map;
	        			}
	        		}else {
	        			if(rmb.getEnable_coin().subtract(tradeNum.multiply(tradePrice)).signum()<0){
	            			map.put("code",ResponseCode.FAIL_TRADE_INSUFFICIENT);
	                        map.put("desc",ResponseCode.FAIL_TRADE_INSUFFICIENT_DESC);
	                        return map;
	            		}
	        		}
	    			if(coinTranAstrictDto == null ||coinTranAstrictDto.getState()==0){
	    				if(Integer.parseInt(convertType) == 0) {
	    					command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),new BigDecimal(1),0,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    				}else {
	    					if(dtoList.size() == 0 && dealDto != null) {
	    						command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),dealDto.getDeal_price(),0,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    					}
	    					command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),dtoList.get(0).getNewes_deal(),0,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    				}
	    			}else{
	    				if(coinTranAstrictDto.getBuy_min_price().doubleValue() > tradePrice.doubleValue() ||
								coinTranAstrictDto.getBuy_max_price().doubleValue() < tradePrice.doubleValue()){
	    					map.put("code", "202");
	    					map.put("desc", "不在交易指定范围内，暂时无法受理");
							return map;
	    				}
						if(tradeNum.multiply(tradePrice).doubleValue()>coinTranAstrictDto.getSingle_max_price().doubleValue() || 
								coinTranAstrictDto.getSingle_min_price().doubleValue()>tradeNum.multiply(tradePrice).doubleValue()){
							map.put("code", "202");
	    					map.put("desc", "不在交易指定范围内，暂时无法受理");
							return map;
						}
						BigDecimal rose_astrict = null;
						BigDecimal drop_astrict = null;
							dealDetailDto = queryDayMarketInfo.queryNewesDealOfBuy(Integer.valueOf(coinType));//查询开盘前最后一条买的记录
						if(dealDetailDto==null || dealDetailDto.getDeal_price() == null){
							map.put("code", "202");
	    					map.put("desc", "最新成交价获取异常，委托失败！");
							return map;
						}
						rose_astrict = dealDetailDto.getDeal_price().add(dealDetailDto.getDeal_price().multiply(new BigDecimal(coinTranAstrictDto.getRose_astrict().doubleValue()/100)));
						//开盘前最后一条买的记录的成交价+开盘前最后一条买的记录的成交价*涨幅
						drop_astrict = dealDetailDto.getDeal_price().subtract(dealDetailDto.getDeal_price().multiply(new BigDecimal(coinTranAstrictDto.getDrop_astrict().doubleValue()/100)));
						//开盘前最后一条买的记录的成交价-开盘前最后一条买的记录的成交价*跌幅
						if(rose_astrict.doubleValue() < tradePrice.doubleValue() || drop_astrict.doubleValue() > tradePrice.doubleValue()){
							map.put("code", "202");
	    					map.put("desc", "不在交易指定范围内，暂时无法受理");
							return map;
						} 
						if(Integer.parseInt(convertType) == 0) {
	    					command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),new BigDecimal(1),0,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    				}else {
	    					if(dtoList.size() == 0 && dealDto != null) {
	    						command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),dealDto.getDeal_price(),0,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    					}
	    					command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),dtoList.get(0).getNewes_deal(),0,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    				}
	    			}
	        }else if(tradeType.equals("sell")){
	    			if(xnb.getEnable_coin().subtract(tradeNum).signum()<0){
	        			map.put("code",ResponseCode.FAIL_TRADE_INSUFFICIENT);
	                    map.put("desc",ResponseCode.FAIL_TRADE_INSUFFICIENT_DESC);
	                    return map;
	        		}if(coinTranAstrictDto == null || coinTranAstrictDto.getState()==0){
	        			if(Integer.parseInt(convertType) == 0) {
	    					command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),new BigDecimal(1),1,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    				}else {
	    					if(dtoList.size() == 0 && dealDto != null) {
	    						command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),dealDto.getDeal_price(),0,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    					}
	    					command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),dtoList.get(0).getNewes_deal(),1,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    				}
	    			}else{
	    				if(coinTranAstrictDto.getSell_min_price().doubleValue() > tradePrice.doubleValue() ||
								coinTranAstrictDto.getSell_max_price().doubleValue() < tradePrice.doubleValue()){
	    					map.put("code", "202");
	    					map.put("desc", "不在交易指定范围内，暂时无法受理");
							return map;
	    				}
						if(tradeNum.multiply(tradePrice).doubleValue() > coinTranAstrictDto.getSingle_max_price().doubleValue() || 
								coinTranAstrictDto.getSingle_min_price().doubleValue() > tradeNum.multiply(tradePrice).doubleValue()){
							map.put("code", "202");
	    					map.put("desc", "不在交易指定范围内，暂时无法受理");
							return map;
						}
						BigDecimal rose_astrict = null;
						BigDecimal drop_astrict = null;
						dealDetailDto = queryDayMarketInfo.queryNewesDealOfBuy(Integer.valueOf(coinType));//查询开盘前最后一条买的记录
						if(dealDetailDto!=null){
							if(dealDetailDto==null || dealDetailDto.getDeal_price() == null){
								map.put("code", "202");
		    					map.put("desc", "最新成交价获取异常，委托失败！");
								return map;
							}
						}
						rose_astrict = dealDetailDto.getDeal_price().add(dealDetailDto.getDeal_price().multiply(new BigDecimal(coinTranAstrictDto.getRose_astrict().doubleValue()/100)));
						//开盘前最后一条买的记录的成交价+开盘前最后一条买的记录的成交价*涨幅
						drop_astrict = dealDetailDto.getDeal_price().subtract(dealDetailDto.getDeal_price().multiply(new BigDecimal(coinTranAstrictDto.getDrop_astrict().doubleValue()/100)));
						//开盘前最后一条买的记录的成交价+开盘前最后一条买的记录的成交价*跌幅
						if(rose_astrict.doubleValue() < tradePrice.doubleValue() || drop_astrict.doubleValue() > tradePrice.doubleValue()){
							map.put("code", "202");
	    					map.put("desc", "不在交易指定范围内，暂时无法受理");
							return map;
						} 
						if(Integer.parseInt(convertType) == 0) {
	    					command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),new BigDecimal(1),1,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    				}else {
	    					if(dtoList.size() == 0 && dealDto != null) {
	    						command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),dealDto.getDeal_price(),0,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    					}
	    					command = new EntrustCommand(Long.parseLong("100"),uid.getUser_no(),Integer.parseInt(coinType),Integer.parseInt(convertType),dtoList.get(0).getNewes_deal(),1,tradePrice,tradeNum,BigDecimal.ZERO,poundatge,0,new Date(),"inserts");
	    				}
	    			}
	    		}
	    	//发送命令
	    	try {
	    		commandGateway.sendAndWait(command);
	            map.put("code",ResponseCode.SUCCESS);
	            map.put("desc",ResponseCode.SUCCESS_DESC);
	        }catch (Exception e){
	            e.printStackTrace();
	            map.put("code",ResponseCode.FAIL);
	            map.put("desc","交易委托失败！");
	        }
	        return map;
	    }
	/*@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public Map<String, Object> validateTradeCoinActual(String userNo, BigDecimal tradeNum, BigDecimal tradePrice,
			BigDecimal poundatge, String tradePassword, String coinType, String tradeType) {
		Map<String, Object> map = new HashMap<>();
		// 判断交易数量
		if (tradeNum.doubleValue() < 0.01) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "交易数量不能少于0.01！");
			return map;
		}
		// 判断交易价格
		if (tradePrice.doubleValue() <= 0) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "交易金额异常！");
			return map;
		}
		// 判断用户是否存在
		UserInfoDto uid = querySubCore.getUserInfo(Integer.parseInt(userNo));
		if (uid == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		// 判断用户是否机构或者子机构
		if (uid.getOrg_type() != null) {
			if (uid.getOrg_type() == 0 || uid.getOrg_type() == 1) {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", "机构或子机构用户不能交易，请见谅！");
				return map;
			}
		}
		// 判断密码
		if (!new MD5().getMD5(tradePassword).equals(uid.getDeal_pwd())) {
			map.put("code", ResponseCode.FAIL_TRADE_PASSWORD);
			map.put("desc", ResponseCode.FAIL_TRADE_PASSWORD_DESC);
			return map;
		}
		CoinDto coinDto = queryCoinLevelProportion.queryCoinDto(coinType);
		if (coinDto.getState() == 2) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", "该货币暂不能交易，请见谅！");
			return map;
		}
		// 用户人民币的财务
		UserBalanceDto rmb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, "0");
		if (rmb == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
			return map;
		}
		// 用户该币的财务，没有对应的货币财务信息就添加相应的财务信息
		UserBalanceDto xnb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, coinType);
		if (xnb == null) {
			// 不存在该用户某种币的资产记录，则添加记录
			if (queryCoin.queryCoinTypeByCoinNo(Integer.parseInt(coinType)) != null) {
				UserBalanceCommand userBalanceCommand = new UserBalanceCommand(Long.parseLong("0"),
						Integer.parseInt(userNo), Integer.parseInt(coinType), BigDecimal.ZERO, BigDecimal.ZERO,
						BigDecimal.ZERO, "", new Date(), "insert");
				commandGateway.sendAndWait(userBalanceCommand);
			}
			// 再判断
			xnb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, coinType);
			if (xnb == null) {
				map.put("code", ResponseCode.FAIL_TRADE_ADDTRUST_STATE);
				map.put("desc", ResponseCode.FAIL_TRADE_ADDTRUST_STATE_DESC);
				return map;
			}
		}
		// 手续费
		EntrustCommand command = null;
		 BigDecimal poundageRate = null; 
		DealDetailDto dealDetailDto = new DealDetailDto();
		CoinTranAstrictDto coinTranAstrictDto = queryCoinTranAstrict
				.queryAllCoinTranAstrictByCoinNo(String.valueOf(coinType));
		if (tradeType.equals("buy")) {
			
			 * poundageRate =
			 * queryCoin.queryBuyPoundatge(Long.valueOf(coinType)).getBuy_poundatge();
			 * if(poundageRate == null){ poundageRate = new BigDecimal(0); }
			 * poundatge=tradePrice.multiply(tradeNum).multiply(poundageRate);
			 
			if (rmb.getEnable_coin().subtract(tradeNum.multiply(tradePrice)).signum() < 0) {
				map.put("code", ResponseCode.FAIL_TRADE_INSUFFICIENT);
				map.put("desc", ResponseCode.FAIL_TRADE_INSUFFICIENT_DESC);
				return map;
			}
			if (coinTranAstrictDto == null) {
				command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 0,
						tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
			} else if (coinTranAstrictDto.getState() == 0) {
				command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 0,
						tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
			} else {
				if (coinTranAstrictDto.getBuy_min_price().doubleValue() > tradePrice.doubleValue()
						|| coinTranAstrictDto.getBuy_max_price().doubleValue() < tradePrice.doubleValue()) {
					map.put("code", "202");
					map.put("desc", "不在交易指定范围内，暂时无法受理");
					return map;
				}
				if (tradeNum.multiply(tradePrice).doubleValue() > coinTranAstrictDto.getSingle_max_price().doubleValue()
						|| coinTranAstrictDto.getSingle_min_price().doubleValue() > tradeNum.multiply(tradePrice)
								.doubleValue()) {
					map.put("code", "202");
					map.put("desc", "不在交易指定范围内，暂时无法受理");
					return map;
				}
				BigDecimal rose_astrict = null;
				BigDecimal drop_astrict = null;
				dealDetailDto = queryDayMarketInfo.queryNewesDealOfBuy(Integer.valueOf(coinType));// 查询开盘前最后一条买的记录
				if (dealDetailDto != null) {
					rose_astrict = dealDetailDto.getDeal_price().add(dealDetailDto.getDeal_price()
							.multiply(new BigDecimal(coinTranAstrictDto.getRose_astrict().doubleValue() / 100)));
					// 开盘前最后一条买的记录的成交价+开盘前最后一条买的记录的成交价*涨幅
					drop_astrict = dealDetailDto.getDeal_price().subtract(dealDetailDto.getDeal_price()
							.multiply(new BigDecimal(coinTranAstrictDto.getDrop_astrict().doubleValue() / 100)));
					// 开盘前最后一条买的记录的成交价-开盘前最后一条买的记录的成交价*跌幅
					if (rose_astrict.doubleValue() < tradePrice.doubleValue()
							|| drop_astrict.doubleValue() > tradePrice.doubleValue()) {
						map.put("code", "202");
						map.put("desc", "不在交易指定范围内，暂时无法受理");
						return map;
					}
				}
				command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 0,
						tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
			}
		} else if (tradeType.equals("sell")) {
			
			 * poundageRate =
			 * queryCoin.queryBuyPoundatge(Long.valueOf(coinType)).getSell_poundatge();
			 * if(poundageRate == null){ poundageRate = new BigDecimal(0); }
			 * poundatge=tradePrice.multiply(tradeNum).multiply(poundageRate);
			 
			if (xnb.getEnable_coin().subtract(tradeNum).signum() < 0) {
				map.put("code", ResponseCode.FAIL_TRADE_INSUFFICIENT);
				map.put("desc", ResponseCode.FAIL_TRADE_INSUFFICIENT_DESC);
				return map;
			}
			if (coinTranAstrictDto == null) {
				command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 1,
						tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
			} else if (coinTranAstrictDto.getState() == 0) {
				command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 1,
						tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
			} else {
				if (coinTranAstrictDto.getSell_min_price().doubleValue() > tradePrice.doubleValue()
						|| coinTranAstrictDto.getSell_max_price().doubleValue() < tradePrice.doubleValue()) {
					map.put("code", "202");
					map.put("desc", "不在交易指定范围内，暂时无法受理");
					return map;
				}
				if (tradeNum.multiply(tradePrice).doubleValue() > coinTranAstrictDto.getSingle_max_price().doubleValue()
						|| coinTranAstrictDto.getSingle_min_price().doubleValue() > tradeNum.multiply(tradePrice)
								.doubleValue()) {
					map.put("code", "202");
					map.put("desc", "不在交易指定范围内，暂时无法受理");
					return map;
				}
				BigDecimal rose_astrict = null;
				BigDecimal drop_astrict = null;
				dealDetailDto = queryDayMarketInfo.queryNewesDealOfBuy(Integer.valueOf(coinType));// 查询开盘前最后一条买的记录
				if (dealDetailDto != null) {
					rose_astrict = dealDetailDto.getDeal_price().add(dealDetailDto.getDeal_price()
							.multiply(new BigDecimal(coinTranAstrictDto.getRose_astrict().doubleValue() / 100)));
					// 开盘前最后一条买的记录的成交价+开盘前最后一条买的记录的成交价*涨幅
					drop_astrict = dealDetailDto.getDeal_price().subtract(dealDetailDto.getDeal_price()
							.multiply(new BigDecimal(coinTranAstrictDto.getDrop_astrict().doubleValue() / 100)));
					// 开盘前最后一条买的记录的成交价+开盘前最后一条买的记录的成交价*跌幅
					if (rose_astrict.doubleValue() < tradePrice.doubleValue()
							|| drop_astrict.doubleValue() > tradePrice.doubleValue()) {
						map.put("code", "202");
						map.put("desc", "不在交易指定范围内，暂时无法受理");
						return map;
					}
				}
				command = new EntrustCommand(Long.parseLong("100"), uid.getUser_no(), Integer.parseInt(coinType), 1,
						tradePrice, tradeNum, BigDecimal.ZERO, poundatge, 0, new Date(), "inserts");
			}
		}
		// 发送命令
		try {
			commandGateway.sendAndWait(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 查询用户某种货币信息
	 * 
	 * @return
	 */
	public Map<Object, Object> validataUserBalanceInfoByUserNoAndCoinType(String userNo, String coinType) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		UserBalanceDto userBalanceInfo = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(userNo, coinType);
		if (userBalanceInfo != null) {
			map.put("balanceInfo", userBalanceInfo);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 定时添加每日详情
	 * 
	 * @return
	 */
	public Map<Object, Object> validataUserBalanceInfoByUserNoAndCoinTypeAndDealDetail(String userNo, String coinType) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<UserBalanceDto> userBalanceInfo = queryUserBalanceInfo
				.queryUserBalanceInfoByUserNoAndCoinTypeDealDetail(userNo, coinType);
		if (userBalanceInfo != null) {
			map.put("balanceInfo", userBalanceInfo);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public void addDayMarket() {
		// 得到当天的交易额
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String day=sdf.format(new java.util.Date());
		List<DealDetailDto> yesCoinNoList = queryDayMarketInfo.queryYesCoin();
		// 会产生笛卡尔积的接口
		// List<DayMarketDto>
		// dayMarketList=queryDayMarketInfo.queryDealDetailInfoByDay();
		List<DayMarketDto> dayMarketList = new ArrayList<>();
		DayMarketDto dayMarketDto = null;
		for (int i = 0; i < yesCoinNoList.size(); i++) {
			dayMarketDto = queryDayMarketInfo.queryDealDetailInfoByDayAndCoinNo(yesCoinNoList.get(i).getCoin_no());
			dayMarketList.add(dayMarketDto);
		}
		List<SubCoreDto> noCoinNoList = queryCoin.queryNoCoin(dayMarketList);
		// 有交易记录的货币
		if (dayMarketList.size() != 0) {
			for (DayMarketDto inesvDayMarket : dayMarketList) {
				// 取今日最新行情
				System.out.println("=======凌晨启动有交易记录货币每日最新行情=======");
				List<InesvDayMarket> inesvDayMarketList = queryDayMarketInfo
						.getDayMarketInfoByCoin(inesvDayMarket.getCoin_type());
				// 今日没有最新行情，创建一条新纪录
				if (inesvDayMarketList.size() == 0) {
					// 发命令操作数据库
					DayMarketCommand command = new DayMarketCommand(0L, inesvDayMarket.getCoin_type(),
							inesvDayMarket.getNewes_deal(), inesvDayMarket.getBuy_price(),
							inesvDayMarket.getSell_price(), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0),
							inesvDayMarket.getMax_price(), inesvDayMarket.getMin_price(), 0, new Date(), "insert");
					commandGateway.send(command);
				}
			}
		}
		if (noCoinNoList.size() != 0) {
			// 没有交易记录的货币
			for (SubCoreDto subCoreDto : noCoinNoList) {
				System.out.println("=======凌晨启动没有交易记录货币每日最新行情=======");
				List<InesvDayMarket> inesvDayMarketList = queryDayMarketInfo
						.getDayMarketInfoByCoin(subCoreDto.getCoin_type());
				// 今日没有最新行情，创建一条新纪录
				if (inesvDayMarketList.size() == 0) {
					// 发命令操作数据库
					DayMarketCommand command = new DayMarketCommand(0L, subCoreDto.getCoin_type(), new BigDecimal(0),
							new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0),
							new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0, new Date(), "insert");
					commandGateway.send(command);
				}
			}
		}
	}

	/*public Map<String, Object> confirmEntrust(String id, String user, String icon, String type, String price,
			String num, String piundatge) {
		Map<String, Object> result = new HashMap<>();
		try {
			EntrustCommand command = new EntrustCommand(Long.valueOf(id), Integer.valueOf(user), Integer.valueOf(icon),
					Integer.valueOf(type), new BigDecimal(price), new BigDecimal(num), BigDecimal.ZERO,
					new BigDecimal(piundatge), 1, new Date(), "confirm");
			commandGateway.sendAndWait(command);
			result.put("code", ResponseCode.SUCCESS);
			result.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", ResponseCode.FAIL);
			result.put("desc", ResponseCode.FAIL_DESC);
		}
		return result;
	}*/

	/**
	 * 得到该用户前10条未交易的委托记录
	 * 
	 * @param userNo
	 * @return
	 */
	public Map<String, Object> getNotTradeEntrustOfTradeCenter(Integer userNo, Integer state, Integer number) {
		Map<String, Object> map = new HashMap<>();
		List<EntrustDto> NotTradeEntrustOfTradeCenter = queryEntrustInfo.getNotTradeEntrustOfTradeCenter(userNo, state,
				number);
		if (NotTradeEntrustOfTradeCenter != null) {
			map.put("NotTradeEntrustOfTradeCenter", NotTradeEntrustOfTradeCenter);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 得到某种货币前15条的买卖委托记录
	 * 
	 * @param entrustCoin
	 * @param entrustType
	 * @return
	 */
	public Map<String, Object> validataGetEntrustOfTradeCenter(Integer entrustCoin, Integer entrustType,
			Integer number) {
		Map<String, Object> map = new HashMap<>();
		List<EntrustDto> EntrustOfTradeCenterList = queryEntrustInfo.queryEntrustInfoOfTradeCenter(entrustCoin,
				entrustType, number);
		if (EntrustOfTradeCenterList != null) {
			map.put("EntrustOfTradeCenterList", EntrustOfTradeCenterList);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 得到静态参数手续费
	 * 
	 * @return
	 */
	public Map<String, Object> getPoundageRate(String param) {
		Map<String, Object> map = new HashMap<>();
		StaticParamsDto StaticParam = queryStaticParam.getStaticParamByParam(param);
		if (StaticParam == null) {
			if (param.equals("poundageRate")) {
				StaticParam = new StaticParamsDto(null, "poundageRate", new BigDecimal(0.01),"");
				map.put("code", ResponseCode.SUCCESS);
				map.put("desc", ResponseCode.SUCCESS_DESC);
				map.put("StaticParams", StaticParam);
				return map;
			} else {
				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);
				return map;
			}
		} else {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("StaticParams", StaticParam);
			return map;
		}
	}

}
