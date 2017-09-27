package com.inesv.digiccy.validata;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.MyRecUserDto;
import com.inesv.digiccy.dto.RankDto;
import com.inesv.digiccy.dto.RecProfitDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.query.QuerySpreadInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huguokai on 2016/12/14 0014.
 */
@Component
public class SpreadValidata {

    @Autowired
    private QuerySpreadInfo querySpreadInfo;

    /**
     * create by huguokai date:2016年12月14日16:00:37
     * 校验邀请码数据
     * @param userNo
     * @return
     */
    public Map<String ,Object> validataInvite(Integer userNo){
        Map<String ,Object> map = new HashMap<>();
        InesvUserDto list = querySpreadInfo.getInvite(userNo);
        if(list.getInvite_num() == null){
            map.put("code", ResponseCode.FAIL_USER_INVITE);
            map.put("desc",ResponseCode.FAIL_USER_INVITE_DESC);
        }else{
            map.put("data",list.getInvite_num());
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * create by huguokai date:2016年12月14日16:00:37
     * 校验排行榜数据
     * @return map
     */
    public Map<String ,Object> getRankInfo(){
        Map<String ,Object> map = new HashMap<>();
        List<RankDto> list = querySpreadInfo.getRankInfo();
        if(list == null){
            map.put("code", ResponseCode.FAIL_USER_RANK);
            map.put("desc",ResponseCode.FAIL_USER_RANK_DESC);
        }else{
            map.put("data",list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * create by huguokai date:2016年12月14日16:00:37
     * 校验收益排行榜数据
     * @return map
     */
    public Map<String ,Object> getDetailRankInfo(){
        Map<String ,Object> map = new HashMap<>();
        List<RankDto> list = querySpreadInfo.getDetailRankInfo();
        if(list == null){
            map.put("code", ResponseCode.FAIL_USER_RANK_DETAIL);
            map.put("desc",ResponseCode.FAIL_USER_RANK_DETAIL_DESC);
        }else{
            map.put("data",list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * create by huguokai date:2016年12月14日16:00:37
     * 校验查询已推荐用户信息和收益
     * @return map
     */
    public Map<String ,Object> getRecUser(Integer userNo){
        Map<String ,Object> map = new HashMap<>();
        List<RecProfitDto> list = querySpreadInfo.getRecUser(userNo);
        if(list == null){
            map.put("code", ResponseCode.FAIL_REC_USER);
            map.put("desc",ResponseCode.FAIL_REC_USER_DESC);
        }else{
            map.put("data",list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    /**
     * create by huguokai date:2016年12月14日16:00:37
     * 校验我的推广信息
     * @return map
     */
    public Map<String ,Object> getMyRecUserInfo(Integer userNo){
        Map<String ,Object> map = new HashMap<>();
        List<MyRecUserDto> list = querySpreadInfo.getMyRecUserInfo(userNo);
        if(list == null){
            map.put("code", ResponseCode.FAIL_REC_USER);
            map.put("desc",ResponseCode.FAIL_REC_USER_DESC);
        }else{
            map.put("data",list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

}
