package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.SubRecordDto;
import com.inesv.digiccy.dto.UserBalanceDto;
import com.inesv.digiccy.event.ThawEvent;
import com.inesv.digiccy.persistence.operation.SubCorePer;
import com.inesv.digiccy.query.QuerySubCore;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class ThawEventHandler {

    @Autowired
    private SubCorePer subCorePer;
    @Autowired
    private QuerySubCore querySubCore;

    /**
     * create by huguokai date:2016年11月18日16:58:55
     * 解冻事件处理
     * @param thawEvent
     * @throws Exception
     */
    @EventHandler
    public void editUserRecordInfo(ThawEvent thawEvent) throws Exception {
        List<SubRecordDto> list = querySubCore.getSubRecordInfoById(thawEvent.getId());
        SubRecordDto subRecordDto = list.get(0);
        subCorePer.editUserRecordInfo(thawEvent.getId(),thawEvent.getThaw_num(),new Date(),thawEvent.getSur_frozen(),subRecordDto.getThaw_sum().add(thawEvent.getThaw_sum()),thawEvent.getState());
        UserBalanceDto userBalanceDto = querySubCore.getUserBalance(subRecordDto.getUser_no(),subRecordDto.getCoin_no());
        subCorePer.editUserBalanceInfo(subRecordDto.getUser_no(),subRecordDto.getCoin_no(),userBalanceDto.getEnable_coin().add(subRecordDto.getSur_frozen().divide(new BigDecimal(subRecordDto.getThaw_num()))),userBalanceDto.getUnable_coin().subtract(subRecordDto.getSur_frozen().divide(new BigDecimal(subRecordDto.getThaw_num()))));
    }

}
