package com.inesv.digiccy.validata.coin;

import com.inesv.digiccy.api.command.CoinCommand;
import com.inesv.digiccy.api.command.WalletLinkCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinAndWalletLinkDto;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.SubCoreDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.VoteInfoDto;
import com.inesv.digiccy.query.QueryVoteInfo;
import com.inesv.digiccy.query.coin.QueryCoin;

import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 货币校验层 Created by JimJim on 2016/11/17 0017.
 */
@Component
public class CoinValidata {

	@Autowired
	QueryCoin queryCoin;

	@Autowired
	QueryVoteInfo queryVoteInfo;

	@Autowired
	CommandGateway commandGateway;

	/**
	 * 校验虚拟货币列表
	 * 
	 * @return
	 */
	public Map<String, Object> getAllCoin() {
		Map<String, Object> map = new HashMap();
		List<CoinAndWalletLinkDto> coins = queryCoin.queryAllCoin();
		if (coins == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		} else {
			map.put("total", coins.size());
			map.put("data", coins.subList(0, coins.size()));
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;

	}


    /**
     * 总资产（折合成人民币），人民币资产,其他币资产
     * @return
     */
    public Map<String,Object> validateBalanceInfo(Integer userNo){
        Map<String,Object> map = new HashMap();
        
        //获取总资产
        List<CoinDto> coinlist = queryCoin.queryAllCoinInfo();
        double sumrmb=0d;
        for(CoinDto coin:coinlist){
        	sumrmb+=queryCoin.queryRmb(userNo, coin.getCoin_no());
        }
 
        //人民币资产
        List<UserBalanceDto> rmbBalances = queryCoin.queryUserBalance(userNo,0); //0人民币
         
        //其他币资产
        List<UserBalanceDto> coinBalances = queryCoin.queryUserBalance(userNo,1); //1其他币
        
		map.put("sumrmb", sumrmb);
		map.put("rmbBalances", rmbBalances);
		map.put("coinBalances", coinBalances);
        map.put("code", ResponseCode.SUCCESS);
		map.put("desc", ResponseCode.SUCCESS_DESC);

		return map;
 }

    
    
    

	/**
	 * 校验新增虚拟货币
	 * 
	 * @return
	 */
	public Map<String, Object> addCoin(Integer no, String name, String code, String address, String host, String post,
			String wallet_name, String wallet_pwd, String wallet_lockpwd, BigDecimal buy_poundatge,
			BigDecimal sell_poundatge, int block,BigDecimal sell_withdraw_poundatge_one,BigDecimal sell_withdraw_poundatge_twe, BigDecimal sell_withdraw_poundatge_three) {
		Map<String, Object> result = new HashedMap();
		try {
			CoinCommand command = new CoinCommand(0L, no, name, code, 0, 1, address, "", new Date(), "", "", "insert",
					buy_poundatge, sell_poundatge, block,sell_withdraw_poundatge_one,sell_withdraw_poundatge_twe,sell_withdraw_poundatge_three);
			commandGateway.sendAndWait(command);
			WalletLinkCommand walletLinkCommand = new WalletLinkCommand(9851, no, host, post, wallet_name, wallet_pwd,
					wallet_lockpwd, "insert");
			commandGateway.sendAndWait(walletLinkCommand);
			result.put("code", ResponseCode.SUCCESS);
			result.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", ResponseCode.FAIL);
			result.put("desc", ResponseCode.FAIL_DESC);
		}
		return result;
	}
    
	
	/**
	 * 校验修改虚拟货币
	 * 
	 * @return
	 */
	public Map<String, Object> updateCoin(Integer no, String name, String code, String address, String host,
			String post, String wallet_name, String wallet_pwd, String wallet_lockpwd, BigDecimal buy_poundatge,
			BigDecimal sell_poundatge, int block,BigDecimal sell_withdraw_poundatge_one, BigDecimal sell_withdraw_poundatge_twe, BigDecimal sell_withdraw_poundatge_three) {
		Map<String, Object> result = new HashedMap();
		try {
			CoinCommand command = new CoinCommand(0L, no, name, code, 0, 1, address, "", new Date(), "", "", "update",
					buy_poundatge, sell_poundatge, block,sell_withdraw_poundatge_one, sell_withdraw_poundatge_twe, sell_withdraw_poundatge_three);
			commandGateway.sendAndWait(command);
			WalletLinkCommand walletLinkCommand = new WalletLinkCommand(123, no, host, post, wallet_name, wallet_pwd,
					wallet_lockpwd, "update");
			commandGateway.sendAndWait(walletLinkCommand);
			result.put("code", ResponseCode.SUCCESS);
			result.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", ResponseCode.FAIL);
			result.put("desc", ResponseCode.FAIL_DESC);
		}
		return result;
	}
	
    /**
     * 校验虚拟货币列表
     * @return
     */
    public Map<String,Object> getAllCrowdCoin(){
        Map<String,Object> map = new HashMap();
        List<CoinDto> coins = queryCoin.queryAllCoinInfo();
        if(coins == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("total",coins.size());
            map.put("data",coins.subList(0,coins.size()));
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

	/**
	 * 校验删除虚拟货币
	 * 
	 * @return
	 */
	public Map<String, Object> deleteCoin(Integer no) {
		Map<String, Object> result = new HashedMap();
		try {
			CoinCommand command = new CoinCommand(0L, no, "", "", 0, 1, "", "", new Date(), "", "", "delete", null,
					null, 1, null, null, null);
			commandGateway.sendAndWait(command);
			WalletLinkCommand walletLinkCommand = new WalletLinkCommand(9851, no, "", "", "", "", "", "delete");
			commandGateway.sendAndWait(walletLinkCommand);
			result.put("code", ResponseCode.SUCCESS);
			result.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", ResponseCode.FAIL);
			result.put("desc", ResponseCode.FAIL_DESC);
		}
		return result;
	}

	/**
	 * 校验修改货币状态
	 * 
	 * @return
	 */
	public Map<String, Object> changeState(Long id, Integer state) {
		Map<String, Object> result = new HashedMap();
		try {
			CoinCommand command = new CoinCommand(id, 1, "", "", state, new Date(), "", "", "state", 0);
			commandGateway.sendAndWait(command);
			result.put("code", ResponseCode.SUCCESS);
			result.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", ResponseCode.FAIL);
			result.put("desc", ResponseCode.FAIL_DESC);
		}
		return result;
	}

	/**
	 * 校验开启货币投票
	 * 
	 * @return
	 */
	public Map<String, Object> startVote(Long id, Integer vote) {
		Map<String, Object> result = new HashedMap();
		try {
			CoinCommand command = new CoinCommand(id, 1, "", "", 1, new Date(), "", "", "vote", vote);
			commandGateway.sendAndWait(command);
			result.put("code", ResponseCode.SUCCESS);
			result.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", ResponseCode.FAIL);
			result.put("desc", ResponseCode.FAIL_DESC);
		}
		return result;
	}

	/**
	 * 校验修改货币图标
	 * 
	 * @return
	 */
	public Map<String, Object> changeIcon(Long id, String icon) {
		Map<String, Object> result = new HashedMap();
		try {
			CoinCommand command = new CoinCommand(id, 1, "", "", 0, 1, "", icon, new Date(), "", "", "icon", null, null,
					1,null ,null,null);
			commandGateway.send(command);
			result.put("code", ResponseCode.SUCCESS);
			result.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", ResponseCode.FAIL);
			result.put("desc", ResponseCode.FAIL_DESC);
		}
		return result;
	}

	/**
	 * 校验根据币种查询投票情况
	 * 
	 * @return
	 */
	public Map<String, Object> getVoteInfoByCoin(Integer coin) {
		Map<String, Object> map = new HashedMap();
		VoteInfoDto voteInfo = queryVoteInfo.getVoteInfoByCoin(coin);
		if (voteInfo == null) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		} else {
			map.put("data", voteInfo);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}

	/**
	 * 查询币种是否存在货币类型表中
	 * 
	 * @return
	 */
	public Map<String, Object> checkCoinType(Integer coin) {
		Map<String, Object> map = new HashedMap();
		List<SubCoreDto> list = queryCoin.queryCoinByCoinType(coin);
		if (list == null || list.size() == 0) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	/**
	 * 得到所有货币信息
	 * 
	 * @return
	 */
	public Map<String, Object> getAllCoinInfo() {
		Map<String, Object> map = new HashMap<>();
		List<CoinDto> list = queryCoin.queryAllCoinInfo();
		if (list == null || list.size() == 0) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		} else {
			map.put("coinList", list);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}
		return map;
	}
	
	/**
     * 得到所有货币信息
     * @return
     */
	public Map<String, Object> getAllCoinRMBInfo() {
		Map<String,Object> map = new HashMap<>();
        List<CoinDto> list = queryCoin.queryCoinNoAndCoinNameOfCoinExceptRMB();
        if(list == null || list.size() == 0){
        	map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("coinList", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
	}
	
	public Map<String, Object> querySellPoundatge1(Long coin_no) {
		Map<String,Object> map = new HashMap<>();
        BigDecimal sell_poundatge = queryCoin.querySellPoundatge1(coin_no);
        if(sell_poundatge == null){
        	map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("sell_poundatge", sell_poundatge);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
	}
	public Map<String, Object> queryBuyPoundatge1(Long coin_no) {
		Map<String,Object> map = new HashMap<>();
        BigDecimal buy_poundatge = queryCoin.queryBuyPoundatge1(coin_no);
        if(buy_poundatge == null){
        	map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("buy_poundatge", buy_poundatge);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
	}
	public Map<String, Object> getCoinTypes(){
		List<CoinDto> dtos = queryCoin.getCoinTypes();
		Map<String, Object> map = new HashMap<String, Object>();
		if(dtos != null){
			map.put("result", dtos);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
		}else{
			map.put("result", "");
        	map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
