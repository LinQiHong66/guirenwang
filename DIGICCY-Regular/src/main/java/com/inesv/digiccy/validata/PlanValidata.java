package com.inesv.digiccy.validata;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.PlanCommand;
import com.inesv.digiccy.api.command.UserBalanceCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CoinTranAstrictDto;
import com.inesv.digiccy.dto.DealDetailDto;
import com.inesv.digiccy.dto.EntrustDto;
import com.inesv.digiccy.dto.InesvDayMarket;
import com.inesv.digiccy.dto.PlanDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.persistence.plan.PlanOperation;
import com.inesv.digiccy.persistence.trade.TradePersistence;
import com.inesv.digiccy.query.QueryDayMarketInfo;
import com.inesv.digiccy.query.QueryPlan;
import com.inesv.digiccy.query.QuerySubCore;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import com.inesv.digiccy.query.coin.QueryCoin;
import com.inesv.digiccy.query.coin.QueryCoinTranAstrict;
import com.inesv.digiccy.util.MD5;

@Component
public class PlanValidata {
	@Autowired
	private QueryPlan queryPlan;
	
	@Autowired
	private CommandGateway commandGateway;
	
	@Autowired
	private QueryDayMarketInfo queryDayMarketInfo;
	
	@Autowired
	private QueryCoin queryCoin;
	
	@Autowired
	private TradePersistence tradePersistence;
	
	@Autowired
	private PlanOperation planOperation;
	
	@Autowired
	QueryUserBalanceInfo queryUserBalanceInfo;
	
	@Autowired
	QuerySubCore querySubCore;
	
	@Autowired
	QueryCoinTranAstrict queryCoinTranAstrict;
	
	/**
	 * 鏍￠獙鏌ヨ鎵�鏈夊鎵樿鍒�
	 * */
	public Map<String,Object> queryPlans() {
		Map<String, Object> map = new HashMap<>();
		List<PlanDto> list = queryPlan.queryAll();
		if(list==null){
			map.put("list", list);
			map.put("code", ResponseCode.FAIL_PLAN_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_PLAN_INFO_CODE_DESC);
		}else{
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}
	
	/**
	 * 鏍￠獙鏍规嵁ID鏌ヨ褰撳墠濮旀墭璁″垝
	 * */
	public Map<String, Object> queryPlanById(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		PlanDto planDto = queryPlan.queryById(id);
		if (planDto==null) {
			map.put("plan", planDto);
			map.put("code", ResponseCode.FAIL_PLAN_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_PLAN_INFO_CODE_DESC);
		}else{
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}
	
	/**
	 * 鏍￠獙璁″垝璁剧疆
	 * */
	public Map<String, Object> insert(Integer user_id,Integer bill_id,String plan_type,BigDecimal top_money_start,BigDecimal top_money_stop,BigDecimal low_money_start,
			BigDecimal low_money_stop,int plan_status,Timestamp plan_time,Long remark,BigDecimal plan_money,String operation,String tradePassword){
			Map<String, Object> map = new HashMap<String, Object>();
			//Map<String , Object> map = new HashMap<>();
	        //鍒ゆ柇浜ゆ槗鏁伴噺
	        if(plan_money.intValue()<=0){
	            map.put("code",ResponseCode.FAIL);
	            map.put("desc",ResponseCode.FAIL_DESC);
	            return map;
	        }
	        //鍒ゆ柇鐢ㄦ埛鏄惁瀛樺湪
	        UserInfoDto uid = querySubCore.getUserInfo(user_id);
	        if(uid == null){
	            map.put("code",ResponseCode.FAIL);
	            map.put("desc",ResponseCode.FAIL_DESC);
	            return map;
	        }
	        //鍒ゆ柇瀵嗙爜
	        if(!new MD5().getMD5(tradePassword).equals(uid.getDeal_pwd())){
	            map.put("code",ResponseCode.FAIL_TRADE_PASSWORD);
	            map.put("desc",ResponseCode.FAIL_TRADE_PASSWORD_DESC);
	            return map;
	        }
	        //鐢ㄦ埛浜烘皯甯佺殑璐㈠姟
	        UserBalanceDto rmb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(String.valueOf(user_id), "0");
	        if(rmb==null){
	            map.put("code",ResponseCode.FAIL);
	            map.put("desc",ResponseCode.FAIL_DESC);
	            return map;
	        }
	        //鐢ㄦ埛璇ュ竵鐨勮储鍔★紝娌℃湁瀵瑰簲鐨勮揣甯佽储鍔′俊鎭氨娣诲姞鐩稿簲鐨勮储鍔′俊鎭�
	        UserBalanceDto xnb = queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(String.valueOf(user_id), String.valueOf(bill_id));
	        if(xnb==null){
	        	//涓嶅瓨鍦ㄨ鐢ㄦ埛鏌愮甯佺殑璧勪骇璁板綍锛屽垯娣诲姞璁板綍
	        	if(queryCoin.queryCoinTypeByCoinNo(Integer.valueOf(bill_id))!=null){
	        		UserBalanceCommand userBalanceCommand=new UserBalanceCommand(Long.parseLong("0"),user_id,Integer.valueOf(bill_id),BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,"",new Date(),"insert");
	            	commandGateway.sendAndWait(userBalanceCommand);
	        	}
	        	//鍐嶅垽鏂�
	        	xnb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(String.valueOf(user_id), String.valueOf(bill_id));
	        	if(xnb==null){
	        		map.put("code",ResponseCode.FAIL_TRADE_ADDTRUST_STATE);
	                map.put("desc",ResponseCode.FAIL_TRADE_ADDTRUST_STATE_DESC);
	                return map;
	        	}
	        }
	        PlanCommand planCommand = null;
	        if(plan_type=="buy"){
	        	planCommand = new PlanCommand(0L, user_id, bill_id, 0, top_money_start, top_money_stop, low_money_start, low_money_stop, 0, plan_time, null, plan_money, "insert");
	        }else if(plan_type=="sell"){
	        	planCommand = new PlanCommand(0L, user_id, bill_id, 1, top_money_start, top_money_stop, low_money_start, low_money_stop, 0, plan_time, null, plan_money, "insert");
	        }
	        try {
	        	commandGateway.sendAndWait(planCommand);
	        	map.put("code", ResponseCode.SUCCESS);
	        	map.put("desc", ResponseCode.SUCCESS_DESC);
	        }catch(Exception e){
	        	map.put("code", ResponseCode.FAIL_PLAN_INFO);
	        	map.put("desc", ResponseCode.FAIL_PLAN_INFO_DESC);
	        }
			/*try {
			//PlanCommand planCommand = new PlanCommand(0L, user_id, bill_id, plan_type, top_money_start, top_money_stop, low_money_start, low_money_stop, 0, null, remark, plan_money, "insert");
			//commandGateway.send(planCommand);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			}catch(Exception e){
			map.put("code", ResponseCode.FAIL_PLAN_INFO);
			map.put("desc", ResponseCode.FAIL_PLAN_INFO_DESC);
			}*/
		return map;
	}
	
	/**
	 * 鏍￠獙鍙栨秷璁㈠崟
	 * */
	public Map<String, Object> updateOver(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PlanCommand pCommand = new PlanCommand(id, 1, 1, 1, new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 1, new Timestamp(1), 0L, new BigDecimal(0), "updateOver");
			commandGateway.send(pCommand);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			
			map.put("code", ResponseCode.FAIL_PLAN_OVER_CODE);
			map.put("desc", ResponseCode.FAIL_PLAN_OVER_DESC);
		}
		return map;
	}
	
	/**
	 * 鏍￠獙瀹屾垚璁㈠崟
	 * */
	public Map<String, Object> updateFinish(Long id) {
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			PlanCommand planCommand = new PlanCommand(id, 1, 1, 1, new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 1, new Timestamp(1), 0L, new BigDecimal(0), "updateFinish");
			commandGateway.send(planCommand);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL_PLAN_OVER_CODE);
			map.put("desc", ResponseCode.FAIL_PLAN_OVER_DESC);
		}
		return map;
	}
	
	/**
	 * 鏍￠獙鍓�30鏉¤鐢ㄦ埛鐨勮甯佺鐨勫鎵樿鍒掕褰�
	 * */
	public Map<String, Object> queryPlanLimit(Long user_id,Long bill_id){
		Map<String, Object> map = new HashMap<String, Object>();
		List<PlanDto> list = new ArrayList<PlanDto>();
		list = queryPlan.queryPlanLimit(user_id, bill_id);
		if(list==null){
			map.put("code", ResponseCode.FAIL_PLAN_INFO_CODE);
			map.put("desc", ResponseCode.FAIL_PLAN_INFO_CODE_DESC);
		}else{
			map.put("plan", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}
	
	
	
	/**
	 * 鑷姩鐢熸垚濮旀墭璁″垝
	 * @throws SQLException 
	 * */
	public void doTrade() throws SQLException{
		List<PlanDto> planDtos = queryPlan.queryPlanIng();
		EntrustDto entrustDto = new EntrustDto();
		CoinDto coinDto = new CoinDto();
		int scale = 5;
		UserBalanceDto userBalanceDto = new UserBalanceDto();
		
		if (planDtos.size()>0) {
			for (PlanDto planDto : planDtos) {
				userBalanceDto = queryUserBalanceInfo.queryEnableCoin(planDto.getUser_id(),planDto.getBill_id());
				DealDetailDto inesvDayMarket = queryDayMarketInfo.queryNewesDeal(Integer.valueOf(planDto.getBill_id()));
				BigDecimal entrust_num = null;
				BigDecimal piundatge = null;
				BigDecimal newdeal = null;
				BigDecimal Entrust_price = null;
				if(inesvDayMarket != null){
					newdeal = inesvDayMarket.getDeal_price();
				}else{
					continue;
				}
				if(planDto.getRemark()==null){
					if(userBalanceDto!=null&&userBalanceDto.getEnable_coin().doubleValue()>=planDto.getPlan_money().doubleValue()){
						if(newdeal.doubleValue() >= planDto.getTop_money_start().doubleValue()){
							entrust_num = planDto.getPlan_money().divide(planDto.getTop_money_stop(),scale,RoundingMode.HALF_UP);
							entrustDto.setEntrust_price(planDto.getTop_money_stop());
							Entrust_price = planDto.getTop_money_stop();
							if (planDto.getPlan_type()==0) {
								coinDto = queryCoin.queryBuyPoundatge(Long.valueOf(planDto.getBill_id()));
								BigDecimal buy_poundatge = coinDto.getBuy_poundatge();
								piundatge = planDto.getTop_money_stop().multiply(entrust_num).multiply(buy_poundatge);
							}else if(planDto.getPlan_type()==1){
								coinDto = queryCoin.queryBuyPoundatge(Long.valueOf(planDto.getBill_id()));
								BigDecimal buy_poundatge = coinDto.getSell_poundatge();
								piundatge = planDto.getTop_money_stop().multiply(entrust_num).multiply(buy_poundatge);
							}
						}else if(newdeal.doubleValue() <= planDto.getLow_money_start().doubleValue()){
							entrust_num = planDto.getPlan_money().divide(planDto.getLow_money_stop(),scale,RoundingMode.HALF_UP);
							entrustDto.setEntrust_price(planDto.getLow_money_stop());
							Entrust_price = planDto.getLow_money_stop();
							if (planDto.getPlan_type()==0) {
								coinDto = queryCoin.queryBuyPoundatge(Long.valueOf(planDto.getBill_id()));
								BigDecimal buy_poundatge = coinDto.getBuy_poundatge();
								piundatge = planDto.getLow_money_stop().multiply(entrust_num).multiply(buy_poundatge);
							}else if(planDto.getPlan_type()==1){
								coinDto = queryCoin.queryBuyPoundatge(Long.valueOf(planDto.getBill_id()));
								BigDecimal buy_poundatge = coinDto.getSell_poundatge();
								piundatge = planDto.getLow_money_stop().multiply(entrust_num).multiply(buy_poundatge);
							}
						}
						//用户虚拟币
						UserBalanceDto xnb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(planDto.getUser_id().toString(), planDto.getBill_id().toString());
						//用户人民币
						UserBalanceDto rmb=queryUserBalanceInfo.queryUserBalanceInfoByUserNoAndCoinType(planDto.getUser_id().toString(), "0");
						BigDecimal EnableXnb = null;
						BigDecimal EnableRmb = null;
						if(planDto.getPlan_type()==1){//卖
							if((xnb.getEnable_coin().doubleValue()-planDto.getPlan_money().doubleValue()) < 0){
								continue ;
							}
							EnableXnb = xnb.getEnable_coin();
							entrustDto.setEntrust_num(planDto.getPlan_money());
							xnb.setUnable_coin(xnb.getUnable_coin().add(planDto.getPlan_money()));
							xnb.setEnable_coin(xnb.getEnable_coin().subtract(planDto.getPlan_money()));
						}else if(planDto.getPlan_type()==0){//买
							if((rmb.getEnable_coin().doubleValue()-(Entrust_price.doubleValue()*entrust_num.doubleValue())) < 0){
								continue ;
							}
							EnableRmb = rmb.getEnable_coin();
							entrustDto.setEntrust_num(entrust_num);
							rmb.setEnable_coin(rmb.getEnable_coin().subtract((Entrust_price.multiply(entrust_num))));
							rmb.setUnable_coin(rmb.getUnable_coin().add(Entrust_price.multiply(entrust_num)));
						}
						entrustDto.setUser_no(planDto.getUser_id());
						entrustDto.setEntrust_coin(planDto.getBill_id());
						entrustDto.setEntrust_type(planDto.getPlan_type());
						entrustDto.setDeal_num(new BigDecimal(0));
						entrustDto.setPiundatge(piundatge);
						entrustDto.setState(0);
						entrustDto.setDate(new Date());
						entrustDto.setAttr1(planDto.getId());
						if(planDto.getPlan_type()==0){
							if(EnableRmb.doubleValue()>=(Entrust_price.doubleValue()*entrust_num.doubleValue())){
								tradePersistence.addEntrust(entrustDto,xnb,rmb);
								planOperation.updateRemark(planDto.getId(), planDto.getId());//鏇存敼remark锛堢姸鎬佹爣璇嗭級锛宺emark瀛樺湪鏃跺垯涓嶄細鏂板濮旀墭璁板綍
							}else{
								continue ;
							}
						}else if(planDto.getPlan_type()==1){
							if(EnableXnb.doubleValue()>=planDto.getPlan_money().doubleValue()){
								tradePersistence.addEntrust(entrustDto,xnb,rmb);
								planOperation.updateRemark(planDto.getId(), planDto.getId());//鏇存敼remark锛堢姸鎬佹爣璇嗭級锛宺emark瀛樺湪鏃跺垯涓嶄細鏂板濮旀墭璁板綍
							}else{
								continue ;
							}
						}
					}
				}
			}
		}
	}
}
