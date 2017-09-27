package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.VoteCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.VoteDto;
import com.inesv.digiccy.dto.VoteInfoDto;
import com.inesv.digiccy.query.QueryVoteInfo;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Component
public class VoteValidata {

    @Autowired
    private QueryVoteInfo queryVoteInfo;
    @Autowired
    private CommandGateway commandGateway;


    /**
     * create by huguokai date:2016年11月17日14:31:39
     * 校验投票记录
     * @return map
     */
    public Map<String ,Object> getVoteInfo(){
        Map<String ,Object> map = new HashMap<>();
        List<VoteInfoDto> list = queryVoteInfo.getVoteInfo();
        if(list == null || list.size() == 0){
            map.put("code", ResponseCode.FAIL_VOTE_INFO);
            map.put("desc",ResponseCode.FAIL_VOTE_INFO_DESC);
        }else{
            map.put("data",list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);  
        }
        return map;
    }

    /**
     * 添加投票记录
     * @param userNo
     * @param coinType
     * @param voteType
     * @return
     */
    public Map<String ,Object> addVoteInfoByUserNo(Integer userNo,Integer coinType,Integer voteType){
        Map<String ,Object> map = new HashMap<>();
        List<VoteDto> list = queryVoteInfo.getVoteInfoByUserNoCoinType(userNo,coinType);
        if(list == null || list.size() == 0){
            try{
                VoteCommand voteCommand = new VoteCommand(userNo,voteType,coinType,new Date());
                commandGateway.sendAndWait(voteCommand);
                map.put("code", ResponseCode.SUCCESS);
                map.put("desc",ResponseCode.SUCCESS_DESC);
            }catch (Exception e){
                e.printStackTrace();
                map.put("code", ResponseCode.FAIL);
                map.put("desc",ResponseCode.FAIL_DESC);
            }
        }else{
            map.put("code", ResponseCode.FAIL_VOTE_TO_COIN);
            map.put("desc",ResponseCode.FAIL_VOTE_TO_COIN_DESC);
        }
        return map;
    }

}
