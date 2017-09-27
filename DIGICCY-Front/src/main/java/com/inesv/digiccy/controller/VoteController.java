package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.validata.VoteValidata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Controller
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteValidata voteValidata;

    /**
     * create by huguokai date:2016年11月17日14:38:09
     * 获取投票数据
     * @return json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "投票记录接口", description = "投票记录接口",
            model = ModelType.VOTE, dtoClazz = BaseRes.class,
            //reqParams = {"tid","uid"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "", name = "")*/
    @RequestMapping(value = "/voteInfo" , method = RequestMethod.POST)
    public @ResponseBody Map<String ,Object> getVoteInfo(){
        Map<String ,Object> map = voteValidata.getVoteInfo();
        return map;
    }

    /**
     * 新币投票功能
     * create by huguokai date:2016年11月17日14:40:20
     * @param userNo
     * @param coinType
     * @return json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "新币投票接口", description = "新币投票接口",
            model = ModelType.VOTE, dtoClazz = BaseRes.class,
            reqParams = {"userNo","coinType","voteType"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号@@货币类型@@投票类型", name = "userNo@@coinType@@voteType")*/
    @RequestMapping(value = "/voteByCoinType" , method = RequestMethod.POST)
    public @ResponseBody Map<String ,Object> voteByCoinType(Integer userNo,Integer coinType,Integer voteType){
        Map<String ,Object> map = voteValidata.addVoteInfoByUserNo(userNo,coinType,voteType);
        return map;
    }

}
