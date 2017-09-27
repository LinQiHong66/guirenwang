package com.inesv.digiccy.validata.walletlink;

import com.inesv.digiccy.api.command.WalletLinkCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.WalletLinkDto;
import com.inesv.digiccy.query.walletlink.QueryWalletLink;
import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/20 0020.
 */
@Component
public class WalletLinkdata {
    @Autowired
    QueryWalletLink queryWalletLink;

    @Autowired
    CommandGateway commandGateway;

    /**
     * 校验所有钱包接口
     * */
    public Map<String,Object> getAllWalletLink(){
        Map<String,Object> map = new HashedMap();
        List<WalletLinkDto> walletLinkDtoList = queryWalletLink.queryAllWalletLink();
        if(walletLinkDtoList == null){
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }else{
            map.put("total",walletLinkDtoList.size());
            map.put("data",walletLinkDtoList.subList(0,walletLinkDtoList.size()));
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     *校验增加钱包接口
     * */
    public Map<String,Object> addWalletLink(Integer coin_no,String host,String post,String wallet_name,String wallet_pwd,String wallet_lockpwd){
    Map<String,Object> result = new HashedMap();
        try {
        WalletLinkCommand walletLinkCommand = new WalletLinkCommand(9851,coin_no,host,post,wallet_name,wallet_pwd,wallet_lockpwd,"insert");
        commandGateway.sendAndWait(walletLinkCommand);
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
     * 校验修改钱包地址
     * */
    public Map<String,Object> updateWalletLink(Integer id,Integer coin_no,String host,String post,String wallet_name,String wallet_pwd,String wallet_lockpwd){
        Map<String, Object> result = new HashedMap();
        try {
            WalletLinkCommand walletLinkCommand = new WalletLinkCommand(id, coin_no, host, post, wallet_name, wallet_pwd, wallet_lockpwd, "update");
            commandGateway.sendAndWait(walletLinkCommand);
            result.put("code", ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
         return result;
     }

     /**
      * 校验删除钱包地址
      * */
    public Map<String,Object> deleteWalletLink(Integer coin_no){
        Map<String,Object> result = new HashedMap();
        try{
        WalletLinkCommand walletLinkCommand = new WalletLinkCommand(2,coin_no,"","","","","","delete");
        commandGateway.sendAndWait(walletLinkCommand);
        result.put("code", ResponseCode.SUCCESS);
        result.put("desc",ResponseCode.SUCCESS_DESC);
    }catch (Exception e){
        e.printStackTrace();
        result.put("code",ResponseCode.FAIL);
        result.put("desc",ResponseCode.FAIL_DESC);
    }
        return  result;
    }
}
