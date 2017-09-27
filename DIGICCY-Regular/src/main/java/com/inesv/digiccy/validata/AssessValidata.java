package com.inesv.digiccy.validata;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.AssessCountDto;
import com.inesv.digiccy.dto.AssessDto;
import com.inesv.digiccy.query.QueryAssessInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/2 0002.
 */
@Component
public class AssessValidata {

    private static Logger logger = LoggerFactory.getLogger(AssessValidata.class);

    @Autowired
    QueryAssessInfo queryAssessInfo;

    /**
     * 校验评论统计查询
     * @param userNo
     * @return
     */
    public Map<String ,Object> getAssessCount(){
        Map<String ,Object> map = new HashMap<>();
        List<AssessCountDto> list = queryAssessInfo.queryAssessCount();
        if(list == null){
            map.put("code", ResponseCode.FAIL_BILL_INFO);
            map.put("desc",ResponseCode.FAIL_BILL_INFO_DESC);
        }else {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    public Map<String ,Object> getAssessList(){
        Map<String ,Object> map = new HashMap<>();
        List<AssessDto> list = queryAssessInfo.queryAssessList();
        if(list == null){
            map.put("code", ResponseCode.FAIL_BILL_INFO);
            map.put("desc",ResponseCode.FAIL_BILL_INFO_DESC);
        }else {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

}
