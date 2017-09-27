package com.inesv.digiccy.controller;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.validata.BillValidata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillValidata billValidata;

    /**
     * create by huguokai date:2016年11月16日15:21:25
     * 获取话费充值记录
     * @param userNo
     * @return json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "话费记录接口", description = "话费记录接口",
            model = ModelType.BILL, dtoClazz = BaseRes.class,
            reqParams = {"userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/getBillInfo" , method = RequestMethod.POST)
    public @ResponseBody Map<String ,Object> getBillInfo(Integer userNo){
        Map<String ,Object> map = billValidata.getBillInfo(userNo);
        return map;
    }

    /**
     * create by huguokai date:2016年11月16日15:21:38
     * 话费充值功能
     * @param userNo
     * @param phone
     * @param price
     * @param payType
     * @param dealPwd
     * @return json
     */
    /*@AutoDocMethod(author = DeveloperType.HUGUOKAI, createTime = "2016-12-2",
            cver = VersionType.V100, name = "话费充值接口", description = "话费充值接口",
            model = ModelType.BILL, dtoClazz = BaseRes.class,
            reqParams = {"userNo","phone","price","payType","dealPwd"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号@@手机号码@@金额@@充值类型@@交易密码", name = "userNo@@phone@@price@@payType@@dealPwd")*/
    @RequestMapping(value = "/billRecharge" , method = RequestMethod.POST)
    public @ResponseBody Map<String ,Object> billRecharge(Integer userNo,String phone,String price,Integer payType,String dealPwd){
        Map<String ,Object> map = billValidata.billRechargeValidata(userNo,phone,price,payType,new MD5().getMD5(dealPwd));
        return map;
    }

}
