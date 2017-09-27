package com.inesv.digiccy.validata;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.MyRecDto;
import com.inesv.digiccy.query.QueryMyRecInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yc on 2016/12/9 0009.
 * 校验我的推荐
 */
@Component
public class MyRecValidate {


    @Autowired
    QueryMyRecInfo queryMyRecInfo;


    /**
     *校验我的用户查询信息
     */
    public Map<String,Object> validateMyRec(String userNo){
        Map<String,Object> map = new HashMap();
        List<MyRecDto> list = queryMyRecInfo.queryMyRecInfoByUserNo(userNo);
        if(!list.isEmpty()){
            map.put("data",list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }else{
            map.put("code", ResponseCode.FAIL_FINANCIAL);
            map.put("desc", ResponseCode.FAIL_FINANCIAL_DESC);
        }
    return map;
    }






}
