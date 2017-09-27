package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.validata.FinancialValidata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
@Controller
@RequestMapping("/fina")
public class FinancialController {

    @Autowired
    private FinancialValidata financialValidata;

    /**
     * create by huguokai date:2016年11月16日12:53:12
     * @param userNo
     * @return Json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "理财中心中心接口", description = "理财中心中心接口",
            model = ModelType.FINANCIAL, dtoClazz = BaseRes.class,
            reqParams = {"userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/getFinancialInfo" , method = RequestMethod.POST)
    public @ResponseBody Map<String ,Object> getFinancialInfo(Integer userNo){
        Map<String ,Object> map = financialValidata.validataFinancial(userNo);
        return map;
    }

}
