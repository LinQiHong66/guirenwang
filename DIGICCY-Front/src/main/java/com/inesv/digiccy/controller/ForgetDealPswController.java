package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.validata.ForgetDealPswValidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by yc on 2016/12/9 0009.
 *修改交易密码
 */
@Controller
@RequestMapping("forget")
public class ForgetDealPswController {


    @Autowired
    ForgetDealPswValidate forgetDealPswValidate;


    /*@AutoDocMethod(author = DeveloperType.YANCHAO, createTime = "2016-12-12",
            cver = VersionType.V100, name = "财务中心忘记交易密码", description = "忘记交易密码接口",
            model = ModelType.FINANCIAL, dtoClazz = BaseRes.class,
            reqParams = {"username","phone","code"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户名@@手机号@@手机验证码", name = "username@@phone@@code")*/
    @RequestMapping(value = "/goUpdatePsw",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> goUpdatePsw(String username,String phone,String code,String updatePwd){
        Map<String,Object> map = forgetDealPswValidate.validateUpdateDealPwd(username,phone,code,updatePwd);
        return map;
    }


    /**
     *发送短信验证码
     */
    @RequestMapping(value = "sendCode",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> sendCodeCode(String phone){
        Map<String,Object> map = forgetDealPswValidate.validatePhoneCode(1,phone);
        return map;
    }

    




}
