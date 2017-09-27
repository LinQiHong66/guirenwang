package com.inesv.digiccy.util.sinapay;

import com.inesv.digiccy.api.utils.PropertiesUtil;

public class SinaPayConstants {

    /** 创建代收请求的服务名(无需改动) */
    public final static String HOSTING_COLLECT_TRADE_SERVICE = "create_hosting_collect_trade";

    /** 支付结果查询(无需改动) */
    public final static String QUERY_PAY_RESULT_SERVICE      = "query_pay_result";

    /** 接口版本(无需改动) */
    public final static String VERSION                       = "1.2";

    /** 数据编码类型(无需改动) */
    public final static String INPUT_CHARSET                 = "UTF-8";

    /** 数据加密类型(无需改动) */
    public final static String SIGN_TYPE                     = "RSA";

    /** 支付等待最长时间(无需改动) */
    public final static String DEFAULT_CLOSE_TRADE_TIME      = "15m";

    /** 支付者帐号类型(无需改动) */
    public final static String ID_TYPE                       = "UID";

    /** 支付方法的基础字符串(无需改动) */
    public final static String BASE_PAYMENT_TYPE             = "online_bank^%s^SINAPAY,DEBIT,C";

    /** 请求成功的返回码(无需改动) */
    public final static String SUCCESS_RESPONSE_CODE         = "APPLY_SUCCESS";

    /** 支付状态成功的状态码(无需改动) */
    public final static String PAY_SUCCESS_CODE              = "SUCCESS";

    /** 请求的支付网关地址(需要根据实际情况改动) */
    public final static String ORDER_GATE_URL                = PropertiesUtil.getPropertiesValue("componentKey.properties", "ORDER_GATE_URL");

    /** 支付网关异步通知地址(需要根据实际情况改动) */
    public final static String NOTIFY_URL                    = PropertiesUtil.getPropertiesValue("componentKey.properties", "NOTIFY_URL");

    /** 支付网关即时通知地址(需要根据实际情况改动) */
    public final static String RETURN_URL                    = PropertiesUtil.getPropertiesValue("componentKey.properties", "RETURN_URL");

    /** 中转帐号类型(需要根据实际情况改动) */
    public final static String OUT_TRADE_CODE                = "1001";

    /** 合作者ID(需要根据实际情况改动) */
    public final static String PARTNER_ID                    = PropertiesUtil.getPropertiesValue("componentKey.properties", "PARTNER_ID");

    /** 支付者新浪帐号 (需要根据实际情况改动) */
    public final static String PAYER_ID                      = PropertiesUtil.getPropertiesValue("componentKey.properties", "PAYER_ID");

    /** 代收备注信息（自定义） */
    public final static String PAYDEMO                       = "贵人网收款";
}
