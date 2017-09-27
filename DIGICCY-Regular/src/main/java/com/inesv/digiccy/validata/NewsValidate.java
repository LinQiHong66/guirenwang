package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.NewsCommand;
import com.inesv.digiccy.api.command.RmbRechargeCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.RmbRechargeDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.query.QueryRmbRechargeInfo;
import com.inesv.digiccy.query.QueryUserBalanceInfo;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yc on 2016/12/9 0009.
 */
@Component
public class NewsValidate {

 

    @Autowired
    private CommandGateway commandGateway;


    /**
     * 发布公告/新闻
     * lqh
     */
    
    public Map<String,Object> validatePubNews( String news_title, String news_content, String news_author, Integer type){
        Map<String,Object> map = new HashMap();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(new Date());
        		
        NewsCommand command = new NewsCommand(4646L, news_title, news_content, news_author, date, type, "insert");
        commandGateway.sendAndWait(command);//发送命令
          map.put("code", ResponseCode.FAIL);
          map.put("desc",ResponseCode.FAIL_DESC);
      
        return map;
    }


}
