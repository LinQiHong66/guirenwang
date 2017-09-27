package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.validata.SpreadValidata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
@Controller
@RequestMapping("/spread")
public class SpreadController {

    @Autowired
    private SpreadValidata spreadValidata;

    /**
     * 查询用户邀请码
     * @param userNo
     * @return json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-15",
            cver = VersionType.V100, name = "推广返佣接口", description = "用户邀请码接口",
            model = ModelType.INVITE, dtoClazz = BaseRes.class,
            reqParams = {"userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/invite" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> getInvite(Integer userNo){
        Map<String ,Object> map = spreadValidata.validataInvite(userNo);
        return map;
    }

    /**
     * 查询邀请排行榜
     * @return json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-15",
            cver = VersionType.V100, name = "推广返佣接口", description = "邀请排行榜接口",
            model = ModelType.INVITE, dtoClazz = BaseRes.class,
            reqParams = {""},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "", name = "")*/
    @RequestMapping(value = "/getRankInfo" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> getRankInfo(){
        Map<String ,Object> map = spreadValidata.getRankInfo();
        return map;
    }

    /**
     * 查询收益排行榜
     * @return json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-15",
            cver = VersionType.V100, name = "推广返佣接口", description = "收益接口",
            model = ModelType.INVITE, dtoClazz = BaseRes.class,
            reqParams = {""},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "", name = "")*/
    @RequestMapping(value = "/getDetailRankInfo" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> getDetailRankInfo(){
        Map<String ,Object> map = spreadValidata.getDetailRankInfo();
        return map;
    }

    /**
     * 查询用户已推荐人信息
     * @return json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-15",
            cver = VersionType.V100, name = "推广返佣接口", description = "已推荐人接口",
            model = ModelType.INVITE, dtoClazz = BaseRes.class,
            reqParams = {"userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/getRecUser" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> getRecUser(Integer userNo){
        Map<String ,Object> map = spreadValidata.getRecUser(userNo);
        return map;
    }

    /**
     * 查询我的推广信息
     * @return json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-15",
            cver = VersionType.V100, name = "推广返佣接口", description = "我的推广接口",
            model = ModelType.INVITE, dtoClazz = BaseRes.class,
            reqParams = {"userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/getMyRecUserInfo" , method = RequestMethod.POST)
    public @ResponseBody Map<String , Object> getMyRecUserInfo(Integer userNo){
        Map<String ,Object> map = spreadValidata.getMyRecUserInfo(userNo);
        return map;
    }
}
