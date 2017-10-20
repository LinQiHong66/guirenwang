package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.validata.BonusValidata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * create by huguokai date:2016年11月14日17:41:14
 * 分润记录控制层
 */
@Controller
@RequestMapping("/bonus")
public class BonusController {

    @Autowired
    private BonusValidata bonusValidata;

    /**
     * 查询分润记录
     * @param userNo
     * @return Json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "分润记录接口", description = "分红记录接口",
            model = ModelType.BONUS, dtoClazz = BaseRes.class,
            reqParams = {"userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/getBonusRecord" , method = RequestMethod.POST)
    public @ResponseBody Map<String ,Object> getBonusRecord(Integer userNo){
        Map<String ,Object> map = bonusValidata.validataBonus(userNo);
        return map;
    }

    /**
     * 查询分润中心
     * @param userNo
     * @return Json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "分润中心接口", description = "分红中心接口",
            model = ModelType.BONUS, dtoClazz = BaseRes.class,
            reqParams = {"userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/getBonusInfo" , method = RequestMethod.POST)
    public @ResponseBody Map<String ,Object> getBonusInfo(Integer userNo){
        Map<String ,Object> map = bonusValidata.validataBonusInfo(userNo);
        return map;
    }

}
