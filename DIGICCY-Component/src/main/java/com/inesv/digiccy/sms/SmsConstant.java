package com.inesv.digiccy.sms;

import com.inesv.digiccy.api.utils.PropertiesUtil;

/**
 * 
 * @ClassName: Constant
 * @module : 常量设置
 * @comment : 常量设置
 * @Description: 常量设置
 * @author huangweihang
 * @date 2016年8月18日 上午10:24:43
 */
public class SmsConstant {

    /**
     * 短信接口URL
     */
    public static final String MESSAGE_url    = PropertiesUtil.getPropertiesValue("componentKey.properties", "MESSAGE_url");

    /**
     * 短信接口appkey
     */
    public static final String MESSAGE_appkey = PropertiesUtil.getPropertiesValue("componentKey.properties", "MESSAGE_appkey");

    /**
     * 短信接口secret
     */
    public static final String MESSAGE_secret = PropertiesUtil.getPropertiesValue("componentKey.properties", "MESSAGE_secret");

    /**
     * 1 修改密码 模板编号
     */
    public static final String sms_update     = PropertiesUtil.getPropertiesValue("componentKey.properties", "sms_update");

    /**
     * 2 支付 模板编号
     */
    public static final String sms_payment    = PropertiesUtil.getPropertiesValue("componentKey.properties", "sms_payment");
}
