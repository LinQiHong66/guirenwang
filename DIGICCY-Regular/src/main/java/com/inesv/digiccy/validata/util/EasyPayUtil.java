package com.inesv.digiccy.validata.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etonepay.b2c.entity.BackForm;
import com.etonepay.b2c.entity.PayForm;
import com.etonepay.b2c.entity.QueryForm;
import com.etonepay.b2c.service.IEtonepayService;
import com.etonepay.b2c.service.impl.EtonepayServiceImpl;
import com.etonepay.b2c.utils.HttpUtil;
import com.etonepay.b2c.utils.StringUtil;
import com.inesv.digiccy.api.utils.PropertiesUtil;

/**
 * 易付通支付接口
 * @author Administrator
 *
 */
public class EasyPayUtil {

    private static Logger       logger               = LoggerFactory.getLogger(EasyPayUtil.class);

    /**
     * 支付请求地址
     */
    private static final String payURL               = PropertiesUtil.getPropertiesValue("regular.properties", "payURL");

    /**
     * 查询请求地址
     */
    private static final String queryURL             = PropertiesUtil.getPropertiesValue("regular.properties", "queryURL");

    /**
     * 交易代码
     */
    private static final String transCode            = PropertiesUtil.getPropertiesValue("regular.properties", "transCode");

    /**
     * 商户编号
     */
    private static final String merchantId           = PropertiesUtil.getPropertiesValue("regular.properties", "merchantId");

    /**
     * 业务代码
     */
    private static final String bussID               = PropertiesUtil.getPropertiesValue("regular.properties", "bussID");

    /**
     * datakey
     */
    private static final String dataKey              = PropertiesUtil.getPropertiesValue("regular.properties", "dataKey");

    /**
     * 货币代码
     */
    private static final String currencyType         = PropertiesUtil.getPropertiesValue("regular.properties", "currencyType");

    /**
     * 页面返回地址
     */
    private static final String merURL               = PropertiesUtil.getPropertiesValue("regular.properties", "merURL");

    /**
     * 回调地址
     */
    private static final String backURL              = PropertiesUtil.getPropertiesValue("regular.properties", "backURL");

    /**
     * 查询的订单为未支付时的结果
     */
    private static final String QUERY_NOT_PAY_RESULT = "none";


    /**
     * 支付请求接口
     * @param orderId 订单ID
     * @param txnAmt 交易金额(单位分)
     * @return 返回为易福宝的交易页面地址 重定向跳转即可
     * @throws Exception
     */
    public static String pay(String orderId, Long txnAmt) throws Exception {
        //构建请求信息
        PayForm payForm = buildPayForm(orderId, txnAmt.toString());
        //发送请求
        IEtonepayService etonepayService = new EtonepayServiceImpl();
        try {
            return etonepayService.getPayURL(payForm);
        } catch (Exception e) {
            logger.error("发送易付宝支付请求失败...", e);
            throw e;
        }
    }

    /**
     * 验证回调信息是否正确
     * @param request
     * @return
     */
    public static boolean validateReturnInfo(HttpServletRequest request) {
        String respValidate = "success";
        String respCode = StringUtil.checkNullString(request.getParameter("respCode"));// 返回码
        String sysTraceNum = StringUtil.checkNullString(request.getParameter("sysTraceNum"));// 商户请求流水号
        String merOrderNum = null;// 商户订单号
        if (StringUtil.isNull(request.getParameter("merOrderNum"))) {
            return false;
        } else {
            merOrderNum = request.getParameter("merOrderNum");
        }
        String orderId = StringUtil.checkNullString(request.getParameter("orderId"));// 支付网关订单号		
        String tranAmt = StringUtil.checkNullString(request.getParameter("tranAmt"));// 实际交易金额		
        String orderAmt = StringUtil.checkNullString(request.getParameter("orderAmt"));// 订单金额		
        String bankFeeAmt = StringUtil.checkNullString(request.getParameter("bankFeeAmt"));// 支付渠道手续费		
        String integralAmt = StringUtil.checkNullString(request.getParameter("integralAmt"));// 积分抵扣金额		
        String vaAmt = StringUtil.checkNullString(request.getParameter("vaAmt"));// 虚拟账户支付金额		
        String bankAmt = StringUtil.checkNullString(request.getParameter("bankAmt"));// 支付渠道支付金额		
        String bankId = StringUtil.checkNullString(request.getParameter("bankId"));// 支付渠道ID		
        String integralSeq = StringUtil.checkNullString(request.getParameter("integralSeq"));// 积分交易流水号		
        String vaSeq = StringUtil.checkNullString(request.getParameter("vaSeq"));// 虚拟账户交易流水号		
        String bankSeq = StringUtil.checkNullString(request.getParameter("bankSeq"));// 支付机构交易流水号		
        String tranDateTime = StringUtil.checkNullString(request.getParameter("tranDateTime"));// 交易时间		
        String payMentTime = StringUtil.checkNullString(request.getParameter("payMentTime"));// 支付时间		
        String settleDate = StringUtil.checkNullString(request.getParameter("settleDate"));// 清算日		
        String orderInfo = StringUtil.checkNullString(request.getParameter("orderInfo"));// 订单信息		
        String userId = StringUtil.checkNullString(request.getParameter("userId"));// 用户ID		
        String signValue = null;// 数字签名
        if (StringUtil.isNull(request.getParameter("signValue"))) {
            return false;
        } else {
            signValue = request.getParameter("signValue");
        }
        /**
         * 创建接口实例
         */
        IEtonepayService etonepayService = new EtonepayServiceImpl();
        /**
         * 构建返回表单并赋值
         */
        BackForm backForm = buildForm(transCode, merchantId, respCode, sysTraceNum, merOrderNum, orderId, bussID, tranAmt, orderAmt, bankFeeAmt, integralAmt, vaAmt, bankAmt, bankId, integralSeq,
                vaSeq, bankSeq, tranDateTime, payMentTime, settleDate, currencyType, orderInfo, userId, signValue);
        /**
         * 生成响应
         */
        String resp = null;
        try {
            resp = etonepayService.chackSignValue(backForm);
            if (respValidate.equals(resp)) { return true; }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    /**
     * 交易查询
     * @param orderId 订单Id
     * @param txnDate 交易日期(YYYYMMDD)
     * @return 查询结果，返回null时代表未支付 查询成功时具体信息查看接口返回数据
     */
    public static Map<String, Object> query(String orderId, String txnDate) {
        /**
         * 创建Form表单
         */
        QueryForm queryForm = buildForm(orderId, txnDate);
        /**
         * 创建接口实例
         */
        IEtonepayService etonepayService = new EtonepayServiceImpl();
        String url = etonepayService.getQueryURL(queryForm);
        // 返回查询结果，String格式，商户可以自己处理
        String resp = HttpUtil.HttpGet(url.toString());
        if (QUERY_NOT_PAY_RESULT.equals(resp)) {
            //订单未支付
            return null;
        }
        String[] strArr = resp.split("&");
        Map<String, Object> result = new HashMap<String, Object>();
        for (String string : strArr) {
            if (string == null) {
                continue;
            }
            String[] field = string.split("=");
            if (field == null || field.length != 2) {
                continue;
            }
            result.put(field[0], field[1]);
        }
        return result;
    }

    /**
     * 支付表单数据
     * @param merOrderNum
     * @param tranAmt
     * @param sysTraceNum
     * @param orderInfo
     * @param bankId
     * @param stlmId
     * @param userId
     * @param userIp
     * @param reserver1
     * @param reserver2
     * @param reserver3
     * @param reserver4
     * @param tranDateTime
     * @return
     */
    private static PayForm buildPayForm(String merOrderNum, String tranAmt) {
        PayForm payForm = new PayForm();
        payForm.setPayURL(payURL);
        payForm.setTransCode(transCode);
        payForm.setMerchantID(merchantId);
        payForm.setBussID(bussID);
        payForm.setDataKey(dataKey);
        payForm.setCurrencyType(currencyType);
        payForm.setMerURL(merURL);
        payForm.setBackURL(backURL);
        payForm.setMerOrderNum(merOrderNum);
        payForm.setTranAmt(tranAmt);
        payForm.setSysTraceNum(getRandomNumString(16));
        payForm.setOrderInfo("");
        payForm.setBankId("");
        payForm.setStlmId("");
        payForm.setUserId("");
        payForm.setUserIp("");
        payForm.setReserver1("");
        payForm.setReserver2("");
        payForm.setReserver3("");
        payForm.setReserver4("");
        payForm.setTranDateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        return payForm;
    }

    /**
     * @Title: buildForm
     * @Description: 创建查询的表单，注入数据
     * @param @param merOrderNum
     * @param @param tranDate
     * @param @return
     * @return QueryForm
     * @throws
     */
    private static QueryForm buildForm(String merOrderNum, String tranDate) {
        QueryForm queryForm = new QueryForm();
        queryForm.setQueryURL(queryURL);
        queryForm.setMerchantID(merchantId);
        queryForm.setMerOrderNum(merOrderNum);
        queryForm.setDataKey(dataKey);
        queryForm.setTranDate(tranDate);
        return queryForm;
    }

    /**
     * 获取随机数
     * @param len
     * @return
     */
    private static String getRandomNumString(int len) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static BackForm buildForm(String transCode, String merchantId, String respCode, String sysTraceNum, String merOrderNum, String orderId, String bussId, String tranAmt, String orderAmt,
            String bankFeeAmt, String integralAmt, String vaAmt, String bankAmt, String bankId, String integralSeq, String vaSeq, String bankSeq, String tranDateTime, String payMentTime,
            String settleDate, String currencyType, String orderInfo, String userId, String signValue) {
        BackForm backForm = new BackForm();
        backForm.setTransCode(transCode);
        backForm.setMerchantId(merchantId);
        backForm.setRespCode(respCode);
        backForm.setSysTraceNum(sysTraceNum);
        backForm.setMerOrderNum(merOrderNum);
        backForm.setOrderId(orderId);
        backForm.setBussId(bussId);
        backForm.setTranAmt(tranAmt);
        backForm.setOrderAmt(orderAmt);
        backForm.setBankFeeAmt(bankFeeAmt);
        backForm.setIntegralAmt(integralAmt);
        backForm.setVaAmt(vaAmt);
        backForm.setBankAmt(bankAmt);
        backForm.setBankId(bankId);
        backForm.setIntegralSeq(integralSeq);
        backForm.setVaSeq(vaSeq);
        backForm.setBankSeq(bankSeq);
        backForm.setTranDateTime(tranDateTime);
        backForm.setPayMentTime(payMentTime);
        backForm.setSettleDate(settleDate);
        backForm.setCurrencyType(currencyType);
        backForm.setOrderInfo(orderInfo);
        backForm.setUserId(userId);
        backForm.setSignValue(signValue);
        return backForm;
    }
}
