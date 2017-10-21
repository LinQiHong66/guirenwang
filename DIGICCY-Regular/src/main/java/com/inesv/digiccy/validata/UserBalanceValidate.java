package com.inesv.digiccy.validata;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinCountDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.WalletAddressDto;
import com.inesv.digiccy.dto.pageDto;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/6 0006.
 */
@Component
public class UserBalanceValidate {

    @Autowired
    QueryUserBalanceInfo queryUserBalanceInfo;

    /**
     * 用户资产
     * @return
     */
    public Map<String, Object> queryAllUserWallet(String userCode, String phone, String realName,String coinType,String startData,String endData,pageDto page) {
        Map<String, Object> map = new HashMap<>();
        List<WalletAddressDto> list = queryUserBalanceInfo.queryAllUserWallet(userCode, phone, realName, coinType, startData, endData, page);
        map.put("total", queryUserBalanceInfo.queryAllUserWalletSize(userCode, phone, realName, coinType, startData, endData));
        map.put("rows", list);
        return map;
    }

    /**
     * 货币总额
     * @return
     */
    public Map<String, Object> validataQueryCoinCount() {
        Map<String, Object> map = new HashMap<>();
        List<CoinCountDto> list = queryUserBalanceInfo.queryCoinCount();
        if (list != null) {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
    }

    /**
     *财务中心用户资产信息
     */
    public Map<String,Object> validateQureyByUserNo(String userNo){
        Map<String,Object> map = new HashMap();
        List<UserBalanceDto> list = queryUserBalanceInfo.queryUserBalanceInfoByUserNo(userNo);
        if(!list.isEmpty()){
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }else{
            map.put("code", ResponseCode.FAIL);
            map.put("desc", ResponseCode.FAIL_DESC);
        }
        return map;
    }
 

}
