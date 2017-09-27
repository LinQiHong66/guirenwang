package com.inesv.digiccy.controller;


import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethod;
import com.inesv.digiccy.common.autocreate.annotation.AutoDocMethodParam;
import com.inesv.digiccy.common.autocreate.bean.DeveloperType;
import com.inesv.digiccy.common.autocreate.bean.ModelType;
import com.inesv.digiccy.common.autocreate.bean.ProgressType;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.inesv.digiccy.common.dto.BaseRes;
import com.inesv.digiccy.validata.user.InesvUserValidata;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;





import java.util.Map;


@Controller()
public class InesvUserController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    InesvUserValidata inesvUserValidata;

    /**
     * 判斷是否設置交易密碼
     * @return
     */
    @RequestMapping(value = "/inesvuser/isDealPwd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> isDealPwd(Integer userNo){
    	 return inesvUserValidata.isPealPwd(userNo);
    }

    /**
     *設置交易密碼
     * @return
     */
    @RequestMapping(value = "/inesvuser/setDealPwd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> setDealPwd(String pealpwd, Integer userNo){
    	 return inesvUserValidata.setPealPwd(pealpwd, userNo);
    }
    
    /**
     * 修改登录密码
     * @param password1
     * @param password
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
            cver = VersionType.V100, name = "测试修改登陆密码接口", description = "测试修改登陆密码接口",
            model = ModelType.LOGIN, dtoClazz = BaseRes.class,
            reqParams = {"password1","password","userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "原登陆密码@@新登陆密码@@用户编号", name = "password1@@password@@userNo")*/
    @RequestMapping(value = "/inesvuser/updateInesvUser", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateInesvUser(String password1,String password,Integer userNo){
        Map<String, Object> map = inesvUserValidata.updateInesvUser(password1,password,userNo);
        return map;
    }

    /**
     * 修改交易密码
     * @param pealpwd1
     * @param pealpwd
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
            cver = VersionType.V100, name = "测试修改交易密码接口", description = "测试修改交易密码接口",
            model = ModelType.LOGIN, dtoClazz = BaseRes.class,
            reqParams = {"pealpwd1","pealpwd","userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "原交易密码@@新交易密码@@用户编号", name = "pealpwd1@@pealpwd@@userNo")*/
    @RequestMapping(value = "/inesvuser/updateDealPwd", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateDealPwd(String pealpwd1,String pealpwd,Integer userNo){
        Map<String, Object> mac = inesvUserValidata.updateDealPwd(pealpwd1,pealpwd,userNo);
        return mac;
    }

    /**
     * 判断绑定手机号与绑定支付宝账号,双重认证的状态
     * @param phone
     * @return
     */
    @AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
            cver = VersionType.V100, name = "判断绑定手机号与绑定支付宝账号,双重认证的状态", description = "测试判断绑定手机号与绑定支付宝账号,双重认证的状态接口",
            model = ModelType.LOGIN, dtoClazz = BaseRes.class,
            reqParams = {"userNo","desc"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号@@描述(phone:判断是否绑定手机号，alipay:判断是否绑定支付宝号，validatePwd：双重认证", name = "userNo@@desc")
    @RequestMapping(value = "/inesvuser/phoneAndAlipayState", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> phoneAndAlipayState(Integer userNo,String desc){
        Map<String, Object> mapPhone = inesvUserValidata.phoneAndAlipayState(userNo,desc);
        return mapPhone;
    }
    
    /**
     * 绑定手机号
     * @param phone
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
            cver = VersionType.V100, name = "测试绑定手机接口", description = "测试绑定手机接口",
            model = ModelType.LOGIN, dtoClazz = BaseRes.class,
            reqParams = {"phone","userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "手机号码@@用户编号", name = "phone@@userNo")*/
    @RequestMapping(value = "/inesvuser/updatePhone", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updatePhone(String phone,Integer userNo){
        Map<String, Object> mapPhone = inesvUserValidata.updatePhone(phone,userNo);
        return mapPhone;
    }

    /**
     * 绑定支付宝账号
     * @param alipay
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
            cver = VersionType.V100, name = "测试绑定支付宝接口", description = "测试绑定支付宝接口",
            model = ModelType.LOGIN, dtoClazz = BaseRes.class,
            reqParams = {"alipay","userNo","dealPwd"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "支付宝账号@@用户编号@@交易密码", name = "alipay@@userNo@@dealPwd")*/
    @RequestMapping(value = "/inesvuser/updateAlipay", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateAlipay(String alipay,Integer userNo){
        Map<String, Object> mapAlipay = inesvUserValidata.updateAlipay(alipay,userNo);
        return mapAlipay;
    }

    /**
     * 交易密码输入设置
     * @param pwdState
     * @param dealPwd1
     * @return
     */
    /*@AutoDocMethod(author = DeveloperType.DEFAULT, createTime = "2016-12-2",
            cver = VersionType.V100, name = "测试交易密码输入设置接口", description = "测试交易密码输入设置接口",
            model = ModelType.LOGIN, dtoClazz = BaseRes.class,
            reqParams = {"pwdState","dealPwd1","userNo"},//有参才需要加的
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "交易密码输入状态@@交易密码@@用户编号", name = "pwdState@@dealPwd1@@userNo")*/
    @RequestMapping(value = "/inesvuser/upPwdState",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> upPwdState(Integer pwdState,String dealPwd1,Integer userNo){
        Map<String,Object> mapPwdState = inesvUserValidata.upPwdState(pwdState,dealPwd1,userNo);
        return mapPwdState;
    }

    /**
     * 根据用户编号得到用户基本信息
     * @param userNo
     * @return
     */
   /* @AutoDocMethod(author = DeveloperType.CHENWAIQING, createTime = "2016-12-14",
            cver = VersionType.V100, name = "根据用户编号得到用户信息", description = "根据用户编号得到用户基本信息接口",
            model = ModelType.LOGIN, dtoClazz = BaseRes.class,
            reqParams = {"userNo"},
            progress = ProgressType.FINISHED)
    @AutoDocMethodParam(note = "用户编号", name = "userNo")*/
    @RequestMapping(value = "/inesvuser/getUserInfoByUserNo",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getUserInfoByUserNo(Integer userNo){
        Map<String,Object> mapPwdState = inesvUserValidata.getUserInfoByUserNo(userNo);
        return mapPwdState;
    }
    
    /*
     * 好友邀请记录
     */
    @RequestMapping(value = "/inesvuser/getUserInfoByOrgCode",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getUserInfoByOrgCode(Integer userNo,Integer pageSize,Integer lineSize){
    	pageSize = ((pageSize-1) * lineSize);
        Map<String,Object> resultMap = inesvUserValidata.getUserInfoByOrgCode(userNo, pageSize, lineSize);
        return resultMap;
    }
    
    /*
     * 以往经纪人删除父亲，手动增加
     */
    @RequestMapping(value = "/inesvuser/addUserCode",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> addUserCode(Integer userNo,String parentCode){
        Map<String,Object> resultMap = inesvUserValidata.addUserInfoByOrgCode(userNo, parentCode);
        return resultMap;
    }
}
