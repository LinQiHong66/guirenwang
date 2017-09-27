package com.inesv.digiccy.validata.coin;

import com.inesv.digiccy.api.command.CoinCommand;
import com.inesv.digiccy.api.command.CoinTranAstrictCommand;
import com.inesv.digiccy.api.command.WalletLinkCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinAndWalletLinkDto;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.CoinTranAstrictDto;
import com.inesv.digiccy.dto.SubCoreDto;
import com.inesv.digiccy.dto.VoteInfoDto;
import com.inesv.digiccy.query.QueryVoteInfo;
import com.inesv.digiccy.query.coin.QueryCoin;
import com.inesv.digiccy.query.coin.QueryCoinTranAstrict;

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
 * 货币校验层
 * Created by JimJim on 2016/11/17 0017.
 */
@Component
public class CoinTranAstrictValidata {

    @Autowired
    QueryCoin queryCoin;

    @Autowired
    QueryCoinTranAstrict queryCoinTranAstrict;

    @Autowired
    CommandGateway commandGateway;


    /**
     * 校验虚拟货币列表
     * @return
     */
    public Map<String,Object> get(){
        Map<String,Object> map = new HashMap<>();
        List<CoinTranAstrictDto> coins = queryCoinTranAstrict.queryAllCoinTranAstrict();
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
     * 校验虚拟货币列表
     * @return
     */
    public Map<String,Object> getById(String id){
        Map<String,Object> map = new HashMap<>();
        CoinTranAstrictDto coin = queryCoinTranAstrict.queryAllCoinTranAstrictById(id);
        if(coin == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("data",coin);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }
    /**
     * 校验虚拟货币列表
     * @return
     */
    public CoinTranAstrictDto getByCoin_id(String id){
    	return queryCoinTranAstrict.queryAllCoinTranAstrictById(id);
    }
    
    /**
     * 校验虚拟货币列表
     * @return
     */
    public CoinTranAstrictDto getByCoin_no(String coin_no){
    	return queryCoinTranAstrict.queryAllCoinTranAstrictByCoinNo(coin_no);
    }

    /**
     * 校验新增虚拟货币
     * @return
     */
    public Map<String,Object> add(Integer coin_no,BigDecimal buy_min_price,BigDecimal buy_max_price,BigDecimal sell_min_price,
    		BigDecimal sell_max_price,BigDecimal single_min_price,BigDecimal single_max_price,Double rose_astrict,Double drop_astrict,
    		Date begin_date,Date end_date,Integer state){
        Map<String,Object> result = new HashMap<>();
        try {
            CoinTranAstrictCommand command = new CoinTranAstrictCommand(0L,coin_no,buy_min_price,buy_max_price,sell_min_price,
            		sell_max_price,single_min_price,single_max_price,rose_astrict,drop_astrict,begin_date,end_date,state,"insert");
            commandGateway.send(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     * 校验修改虚拟货币
     * @return
     */
    public Map<String,Object> edit(Long id,Integer coin_no,BigDecimal buy_min_price,BigDecimal buy_max_price,BigDecimal sell_min_price,
    		BigDecimal sell_max_price,BigDecimal single_min_price,BigDecimal single_max_price,Double rose_astrict,Double drop_astrict,
    		Date begin_date,Date end_date,Integer state){
        Map<String,Object> result = new HashedMap();
        try {
        	CoinTranAstrictCommand command = new CoinTranAstrictCommand(id,coin_no,buy_min_price,buy_max_price,sell_min_price,
            		sell_max_price,single_min_price,single_max_price,rose_astrict,drop_astrict,begin_date,end_date,state,"update");
            commandGateway.send(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     * 校验删除虚拟货币
     * @return
     */
    public Map<String,Object> delete(Long id){
        Map<String,Object> result = new HashedMap();
        try {
        	CoinTranAstrictCommand command = new CoinTranAstrictCommand(id,Integer.valueOf("0"),new BigDecimal(0),new BigDecimal(0),
            		new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),
            		0d,0d,new Date(),new Date(),0,"delete");
            commandGateway.send(command);
            result.put("code",ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

}
