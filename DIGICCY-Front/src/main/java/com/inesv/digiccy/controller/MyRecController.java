package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.validata.MyRecValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by yc on 2016/12/9 0009.
 * 财务中心我的推荐人
 */

@Controller
@RequestMapping("/recommend")
public class MyRecController {

    @Autowired
    MyRecValidate myRecValidate;


    /*@AutoDocMethod(author = DeveloperType.YANCHAO, createTime = "2016-12-12",
            cver = VersionType.V100, name = "财务中心我的推荐", description = "查询我的推荐",
            model = ModelType.FINANCIAL, dtoClazz = BaseRes.class,
            reqParams = {"userNo"},//有参才需要加的
            progress = ProgressType.TESTING)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/getMyrec",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getMyrec(String userNo){
        if(!userNo.equals("") && userNo!= null){
            Map<String,Object> map = myRecValidate.validateMyRec(userNo);
            return map;
        }
        return null;
    }

}
