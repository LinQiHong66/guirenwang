package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.MyRecDto;
import com.inesv.digiccy.dto.MyRecUserDto;
import com.inesv.digiccy.dto.RecSubProfitDto;
import com.inesv.digiccy.dto.SubCoreDto;
import com.inesv.digiccy.dto.SubRecordDto;
import com.inesv.digiccy.dto.ThreeGenerationOfRecommandDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.event.SubRecordEvent;
import com.inesv.digiccy.persistence.finance.RecSubProfitPersistence;
import com.inesv.digiccy.persistence.operation.SubCorePer;
import com.inesv.digiccy.query.QueryMyRecInfo;
import com.inesv.digiccy.query.QuerySubCore;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by huguokai on 2016/11/9 0009.
 * 认购中心事件执行者
 */
public class SubRecordEventHangler {

    @Autowired
    private SubCorePer subCorePer;
    @Autowired
    private QuerySubCore querySubCore;
    @Autowired
    private QueryMyRecInfo queryMyRecInfo;
    @Autowired 
    private RecSubProfitPersistence recSubProfitPersistence; 

    /**
     * 认购中心事件处理
     * @param subRecordEvent
     * @throws Exception
     */
    @EventHandler
    public void subRecordEvent(SubRecordEvent subRecordEvent) throws Exception {
    	//认购记录对象
        SubRecordDto subRecordDto = new SubRecordDto(subRecordEvent.getUser_no(),subRecordEvent.getCoin_no(),subRecordEvent.getSub_name(),subRecordEvent.getSub_price(),subRecordEvent.getSub_num(),subRecordEvent.getSum_price(),subRecordEvent.getThaw_num(),subRecordEvent.getThaw_time(),subRecordEvent.getSur_frozen(),subRecordEvent.getState(),subRecordEvent.getDate(),subRecordEvent.getThaw_sum());
        subCorePer.addSubRecordTranster(subRecordDto);
    }
}
