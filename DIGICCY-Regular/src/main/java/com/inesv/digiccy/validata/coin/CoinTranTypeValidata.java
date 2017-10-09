package com.inesv.digiccy.validata.coin;

import com.inesv.digiccy.api.command.CoinTranTypeCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinTranTypeDto;
import com.inesv.digiccy.query.coin.QueryCoin;
import com.inesv.digiccy.query.coin.QueryCoinTranType;

import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 货币校验层
 * Created by JimJim on 2016/11/17 0017.
 */
@Component
public class CoinTranTypeValidata {

    @Autowired
    QueryCoin queryCoin;

    @Autowired
    QueryCoinTranType queryCoinTranType;

    @Autowired
    CommandGateway commandGateway;


    /**
     * 校验虚拟货币列表
     * @return
     */
    public Map<String,Object> get(){
        Map<String,Object> map = new HashMap<>();
         List<CoinTranTypeDto> coins = queryCoinTranType.queryAllCoinTradeType();
        if(coins == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("total",coins.size());
            map.put("data",coins);
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
        CoinTranTypeDto coin = queryCoinTranType.queryAllCoinTradeTypeById(id);
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
    public CoinTranTypeDto getByType_id(String id){
        CoinTranTypeDto coin = queryCoinTranType.queryAllCoinTradeTypeById(id);
        return coin;
    }
    
    /**
     * 校验虚拟货币列表
     * @return
     */
    public Map<String,Object> getByCoinNo(String coinNo){
        Map<String,Object> map = new HashMap<>();
        List<CoinTranTypeDto> coins = queryCoinTranType.queryAllCoinTradeTypeByCoinNo(coinNo);
        if(coins == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("data",coins.get(0));
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }
    
    /**
     * 校验虚拟货币列表
     * @return
     */
    public List<CoinTranTypeDto> getByCoin_no(String coinNo){
        Map<String,Object> map = new HashMap<>();
        List<CoinTranTypeDto> coins = queryCoinTranType.queryAllCoinTradeTypeByCoinNo(coinNo);
        return coins;
    }
    
    /**
     * 校验虚拟货币列表
     * @return
     */
    public List<CoinTranTypeDto> getByCoin_no(String coinNo,String tranCoinNo){
        Map<String,Object> map = new HashMap<>();
        List<CoinTranTypeDto> coins = queryCoinTranType.queryAllCoinTradeTypeByCoinNo(coinNo,tranCoinNo);
        return coins;
    }

    /**
     * 校验新增虚拟货币
     * @return
     */
    public Map<String,Object> add(Integer coin_no,Integer state,Integer type, Date date){
        Map<String,Object> result = new HashMap<>();
        try {
            CoinTranTypeCommand command = new CoinTranTypeCommand(0,coin_no,type,state,date,"","","insert");
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
    public Map<String,Object> edit(Integer id,Integer coin_no,Integer state,Integer type, Date date){
        Map<String,Object> result = new HashedMap();
        try {
        	CoinTranTypeCommand command = new CoinTranTypeCommand(id,coin_no,type,state,date,"","","update");
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
    public Map<String,Object> delete(Integer id){
        Map<String,Object> result = new HashedMap();
        try {
        	CoinTranTypeCommand command = new CoinTranTypeCommand(id,0,0,0,new Date(),"","","delete");
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
