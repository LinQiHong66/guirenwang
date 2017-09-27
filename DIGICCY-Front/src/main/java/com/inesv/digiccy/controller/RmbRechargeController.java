package com.inesv.digiccy.controller;

import com.inesv.digiccy.api.utils.PropertiesUtil;
import com.inesv.digiccy.dto.AddressDto;
import com.inesv.digiccy.dto.RmbRechargeDto;
import com.inesv.digiccy.persistence.finance.RmbRechargePersistence;
import com.inesv.digiccy.query.QueryRmbRechargeInfo;
import com.inesv.digiccy.util.EasyPayUtil;
import com.inesv.digiccy.util.GenerateUtil;
import com.inesv.digiccy.util.ObjectChangeUtil;
import com.inesv.digiccy.util.TableName;
import com.inesv.digiccy.validata.PlatformPaymentValidata;
import com.inesv.digiccy.validata.RmbRechargeValidate;
import com.kenai.jffi.Main;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yc on 2016/12/9 0009.
 * 人民币充值
 */

@Controller
@RequestMapping("rmbRecharge")
public class RmbRechargeController {

    @Autowired
    RmbRechargeValidate         rmbRechargeValidate;

    @Autowired
    PlatformPaymentValidata     platformPaymentValidata;

    @Autowired
    QueryRmbRechargeInfo        queryRmbRechargeInfo;

    @Autowired
    QueryRunner                 queryRunner;

    private static final String callBackUrl = PropertiesUtil.getPropertiesValue("frontKey.properties", "callbackUrl");


    /*    *//**
     * 获取平台收款银行账户
     */
    /* @RequestMapping(value = "a",method = RequestMethod.POST)
     * 
     * @ResponseBody
     * public Map<String,Object> a(){
     * int i=0;
     * Map<String, Object> map = new HashMap<String, Object>();
     * List<RmbRechargeDto> list = new ArrayList();
     * String sql = "SELECT * FROM t_inesv_rmb_recharge WHERE state  = ? AND id >=316 AND id <=364 ";
     * Object parmas[] = {1};
     * try {
     * list = (List<RmbRechargeDto>) queryRunner.query(sql,new BeanListHandler(RmbRechargeDto.class),parmas);
     * } catch (SQLException e) {
     * e.printStackTrace();
     * }
     * for(RmbRechargeDto re: list) {
     * i++;
     * String sql2 = "UPDATE t_inesv_user_balance SET enable_coin =enable_coin + ? ,total_price =total_price + ?  WHERE user_no =?  AND coin_type = 0";
     * Object parmas2[] = {re.getActual_price(),re.getActual_price(),re.getUser_no()};
     * try {
     * queryRunner.update(sql2,parmas2);
     * } catch (SQLException e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * }
     * 
     * }
     * map.put("result",i);
     * return map;
     * } */

    /**
     * 支付
     */
    /* @RequestMapping(value = "pay",method = RequestMethod.POST)
     * 
     * @RequestMapping(value = "pay")
     * 
     * @ResponseBody
     * public Map<String, Object> pay(String recharge_price,Integer userNo,Integer recType,String pwd,HttpServletRequest request){
     * Map<String, Object> map = new HashMap();
     * String order = GenerateUtil.generateOrderNo();
     * map = rmbRechargeValidate.validateGoRmbRecharge(userNo, recType, recharge_price, pwd, order);
     * return map;
     * 
     * } */

    /**
     * 及时回调
     */
    /* @RequestMapping(value = "jishi")
     * public void jishi(HttpServletRequest request){
     * if(EasyPayUtil.validateReturnInfo(request)){ //验证回调信息是否正确
     * //处理充值数据
     * String order =request.getParameter("merOrderNum");
     * RmbRechargeDto recharge = queryRmbRechargeInfo.qureyRechargeInfoByorder(order);
     * if(recharge.getState()==0){
     * rmbRechargeValidate.validateUpdateStatu(1, order); //修改为已到账
     * }
     * //跳转到成功页面
     * request.getRequestDispatcher(callBackUrl);
     * }
     * 
     * } */

    /**
     * 异步回调
     */
    /* @RequestMapping(value = "yibu")
     * 
     * @ResponseBody
     * public String yibu(HttpServletRequest request){
     * if(EasyPayUtil.validateReturnInfo(request)){ //验证回调信息是否正确
     * //处理充值数据
     * String order =request.getParameter("merOrderNum");
     * RmbRechargeDto recharge = queryRmbRechargeInfo.qureyRechargeInfoByorder(order);
     * if(recharge.getState()==0){
     * rmbRechargeValidate.validateUpdateStatu(1, order); //修改为已到账
     * }
     * return "success";
     * }else{
     * return "error";
     * }
     * } */

    /**
     * 获取平台收款银行账户
     */
    @RequestMapping(value = "getPlatfromPayment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPlatfromPayment() {
        Map<String, Object> map = platformPaymentValidata.getPlatfromPayment();
        return map;
    }

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserInfo(Long userNo) {
        Map<String, Object> map = rmbRechargeValidate.validateUserInfo(userNo);
        return map;
    }

    /**
     * 查询可用人民币及人民币充值记录信息
     */
    @RequestMapping(value = "getRmbRechargeInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getRmbRechargeInfo(int userNo, int coinType) {
        Map<String, Object> map = rmbRechargeValidate.validateRmbRechargeInfo(userNo, coinType);//0为人民币类型
        return map;
    }

    /**
     * 支付
     */
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pay(String recharge_price, Integer userNo, Integer recType, String pwd, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String order = GenerateUtil.generateOrderNo();
        map = rmbRechargeValidate.validateGoRmbRecharge(userNo, recType, recharge_price.toString(), pwd, order);
        return map;
    }

    /**
     * 修改用户的人民币充值状态(未付款 or 已付款)
     */
    /* @RequestMapping(value = "updateStatus",method = RequestMethod.POST)
     * 
     * @ResponseBody
     * public Map<String,Object> updateStatu(int status,String order){
     * Map<String, Object> map = rmbRechargeValidate.validateUpdateStatu(status,order);
     * return map;
     * } */

}
