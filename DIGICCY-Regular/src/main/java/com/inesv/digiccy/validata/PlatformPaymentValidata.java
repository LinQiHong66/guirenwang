package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.BillCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.BillDto;
import com.inesv.digiccy.dto.PlatformPaymentDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.persistence.operation.BillPer;
import com.inesv.digiccy.query.QueryBill;
import com.inesv.digiccy.query.QueryPlatformPayment;
import com.inesv.digiccy.query.QuerySubCore;
import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
@Component
public class PlatformPaymentValidata {

    private static Logger logger = LoggerFactory.getLogger(PlatformPaymentValidata.class);

    @Autowired
    private QueryPlatformPayment queryPlatformPayment;
 

    /**
     * lqh
     *  查询平台收款银行账户
     * @param userNo
     * @return map
     */
    public Map<String ,Object> getPlatfromPayment(){
        Map<String ,Object>  map = new HashMap<>();
         PlatformPaymentDto platf  = queryPlatformPayment.queryBankAccount();
        if(platf == null  ){
            map.put("code", ResponseCode.FAIL);
            map.put("desc","不存在收款账户");
        }else {
            map.put("bankinfo", platf);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }


}
