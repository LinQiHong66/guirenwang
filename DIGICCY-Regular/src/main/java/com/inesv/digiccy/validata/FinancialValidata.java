package com.inesv.digiccy.validata;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.FinancialDto;
import com.inesv.digiccy.query.QueryFinancial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
@Component
public class FinancialValidata {

    @Autowired
    private QueryFinancial queryFinancial;

    /**
     * create by huguokai date:2016年11月16日12:53:56
     * 校验理财中心数据
     * @param userNo
     * @return map
     */
    public Map<String ,Object> validataFinancial(Integer userNo){
        Map<String ,Object> map = new HashMap<>();
        List<FinancialDto> list = queryFinancial.getFinancialInfo(userNo);
        if (list != null || list.size() == 0) {
            map.put("EntrustList", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        } else {
            map.put("code", ResponseCode.FAIL_FINANCIAL);
            map.put("desc", ResponseCode.FAIL_FINANCIAL_DESC);
        }
        return map;
    }

}
