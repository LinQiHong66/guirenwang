package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.BonusDetailCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.BonusDetailDto;
import com.inesv.digiccy.dto.BonusRecordDto;
import com.inesv.digiccy.dto.BounsInfoDto;
import com.inesv.digiccy.query.QueryBonus;
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
 * Created by Administrator on 2016/11/14 0014.
 */
@Component
public class BonusValidata {

    @Autowired
    private QueryBonus queryBonus;

    @Autowired
    CommandGateway commandGateway;

    /**
     * create by huguokai date:2016年11月14日17:39:41
     * 校验分红记录
     * @param userNo
     * @return map
     */
    public Map<String ,Object> validataBonus(Integer userNo){
        Map<String ,Object> map = new HashMap<>();
        List<BonusRecordDto> list = queryBonus.getBonusRecord(userNo);
        if(list == null || list.size() == 0){
            map.put("code", ResponseCode.FAIL_BONUS_RECORD);
            map.put("desc",ResponseCode.FAIL_BONUS_RECORD_DESC);
        }else {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    public Map<String ,Object> validataBonus(){
        Map<String ,Object> map = new HashMap<>();
        List<BonusRecordDto> list = queryBonus.getBonusRecord();
        if(list == null || list.size() == 0){
            map.put("code", ResponseCode.FAIL_BONUS_RECORD);
            map.put("desc",ResponseCode.FAIL_BONUS_RECORD_DESC);
        }else {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * create by huguokai date:2016年11月16日10:23:07
     * 校验分红中心
     * @param userNo
     * @return map
     */
    public Map<String ,Object> validataBonusInfo(Integer userNo){
        Map<String ,Object> map = new HashMap<>();
        List<BounsInfoDto> list = queryBonus.getBonusInfo(userNo);
        if(list == null || list.size() == 0){
            map.put("code", ResponseCode.FAIL_BONUS_INFO);
            map.put("desc",ResponseCode.FAIL_BONUS_INFO_DESC);
        }else {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * create by huguokai date:2016年11月16日10:23:07
     * 校验分红
     * @return map
     */
    public Map<String ,Object> validataBonusDetail(){
        Map<String ,Object> map = new HashMap<>();
        List<BonusDetailDto> list = queryBonus.getBonusDetail();
        if(list == null){
            map.put("code", ResponseCode.FAIL_BONUS_INFO);
            map.put("desc",ResponseCode.FAIL_BONUS_INFO_DESC);
        }else {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    public Map<String,Object> addBonusDetailed(String bonusName, Integer coin, BigDecimal num){
        Map<String,Object> result = new HashedMap();
        try {
            BonusDetailCommand command = new BonusDetailCommand(0,bonusName,coin,num,new Date(),1,"insert");
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

    public Map<String,Object> updateBonusDetailed(Integer id,String bonusName, Integer coin, BigDecimal num){
        Map<String,Object> result = new HashedMap();
        try {
            BonusDetailCommand command = new BonusDetailCommand(id,bonusName,coin,num,new Date(),1,"update");
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

    public Map<String,Object> deleteBonusDetailed(Integer id){
        Map<String,Object> result = new HashedMap();
        try {
            BonusDetailCommand command = new BonusDetailCommand(id,"",0,new BigDecimal(0),new Date(),1,"delete");
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

    public Map<String,Object> doBonusDetailed(String bonusName,Integer coinId,BigDecimal num){
        Map<String,Object> result = new HashedMap();
        try {
            BonusDetailCommand command = new BonusDetailCommand(1,bonusName,coinId,num,new Date(),1,"bonus");
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
