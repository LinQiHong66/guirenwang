package com.inesv.digiccy.validata;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvPrizeDto;
import com.inesv.digiccy.query.QueryPrizeInfo;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
@Component
public class PrizeValidate {
    @Autowired
    QueryPrizeInfo queryPrizeInfo;

    public Map<String,Object> validatePrize(String userNo){
        Map<String,Object> map = new HashedMap();
        List<InesvPrizeDto> list = queryPrizeInfo.getPrizeInfo(Integer.parseInt(userNo));
        if(list.isEmpty()){
            map.put("code", ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
        }else {
            map.put("data",list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

}
