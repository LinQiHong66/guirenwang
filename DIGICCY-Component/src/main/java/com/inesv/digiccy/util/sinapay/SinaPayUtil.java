package com.inesv.digiccy.util.sinapay;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.inesv.digiccy.util.sinapay.dto.HostingCollectTradeEntity;
import com.inesv.digiccy.util.sinapay.dto.HostingCollectTradeResultEntity;
import com.inesv.digiccy.util.sinapay.dto.QueryPayStatusResultEntity;
import com.inesv.digiccy.util.sinapay.utils.CallServiceUtil;
import com.inesv.digiccy.util.sinapay.utils.GsonUtil;
import com.inesv.digiccy.util.sinapay.utils.SignUtil;
import com.inesv.digiccy.util.sinapay.utils.Tools;

public class SinaPayUtil {

    /**
     * 发起新浪代收支付
     * @param createPayEntity
     * @return
     * @throws Exception
     */
    public static HostingCollectTradeResultEntity CreatePay(HostingCollectTradeEntity createPayEntity) throws Exception {
        Map<String, String> param = new HashMap<String, String>();
        param.put("service", SinaPayConstants.HOSTING_COLLECT_TRADE_SERVICE);//服务名称
        param.put("version", SinaPayConstants.VERSION);//版本信息
        param.put("request_time", Tools.getCurrentTimeString());//请求时间
        param.put("partner_id", SinaPayConstants.PARTNER_ID); //合作者ID
        param.put("_input_charset", SinaPayConstants.INPUT_CHARSET);//编码字符集
        param.put("notify_url", SinaPayConstants.NOTIFY_URL);//异步通知请求地址
        param.put("return_url", SinaPayConstants.RETURN_URL);//同步通知请求地址
        param.put("out_trade_no", createPayEntity.getOrder_id());//本应用的订单ID
        param.put("out_trade_code", SinaPayConstants.OUT_TRADE_CODE);//账户类型
        param.put("summary", createPayEntity.getSummary());//本支付请求的备注信息必填
        param.put("trade_close_time", SinaPayConstants.DEFAULT_CLOSE_TRADE_TIME);//关闭交易的时间
        param.put("payer_id", createPayEntity.getPayer_id());//支付者的帐号
        param.put("payer_identity_type", SinaPayConstants.ID_TYPE);//支付者帐号类型
        param.put("payer_ip", createPayEntity.getPayer_ip());//支付者Ip
        param.put("pay_method", String.format(SinaPayConstants.BASE_PAYMENT_TYPE, createPayEntity.getAmount()));//支付方法
        Tools.removeBlankValue(param);//去除空数据
        Tools.mapToBeEncrypt(param);//对需要加密的对象加密、
        param.put("sign", getSign(param));//签名
        param.put("sign_type", SinaPayConstants.SIGN_TYPE);//签名类型
        String queryParam = Tools.createLinkString(param, true);
        String result = URLDecoder.decode(CallServiceUtil.sendPost(SinaPayConstants.ORDER_GATE_URL, queryParam), SinaPayConstants.INPUT_CHARSET);
        Map<String, String> resultMap = GsonUtil.fronJson2Map(result);
        HostingCollectTradeResultEntity hostingCollectTradeResultEntity = new HostingCollectTradeResultEntity();
        hostingCollectTradeResultEntity.setResultString(result);
        if (!validateSign(resultMap)) {
            //发起支付返回签名不正确
            hostingCollectTradeResultEntity.setSuccess(false);
            hostingCollectTradeResultEntity.setDesc("返回数据签名不正确...");
            return hostingCollectTradeResultEntity;

        }
        //判断返回码
        if (!SinaPayConstants.SUCCESS_RESPONSE_CODE.equals(resultMap.get("response_code"))) {
            //发起支付失败
            hostingCollectTradeResultEntity.setSuccess(false);
            hostingCollectTradeResultEntity.setDesc(resultMap.get("response_message"));
            return hostingCollectTradeResultEntity;
        }
        //发起支付成功
        hostingCollectTradeResultEntity.setSuccess(true);
        hostingCollectTradeResultEntity.setRedirect_url(resultMap.get("redirect_url"));
        return hostingCollectTradeResultEntity;
    }

    /**
     * 查询支付状态
     * @param order_id
     * @return
     * @throws Exception
     */
    public static QueryPayStatusResultEntity queryPayStatus(String order_id) throws Exception {
        QueryPayStatusResultEntity queryPayStatusResultEntity = new QueryPayStatusResultEntity();
        queryPayStatusResultEntity.setOrder_id(order_id);
        if (order_id == null || "".equals(order_id.trim())) {
            queryPayStatusResultEntity.setSuccess(false);
            queryPayStatusResultEntity.setOrder_id(order_id);
            queryPayStatusResultEntity.setDesc("传入的订单号不能为空或空白");
            return queryPayStatusResultEntity;
        }
        Map<String, String> param = new HashMap<String, String>();
        param.put("service", SinaPayConstants.QUERY_PAY_RESULT_SERVICE);//服务名称
        param.put("version", SinaPayConstants.VERSION);//版本信息
        param.put("request_time", Tools.getCurrentTimeString());//请求时间
        param.put("partner_id", SinaPayConstants.PARTNER_ID);//合作者ID
        param.put("_input_charset", SinaPayConstants.INPUT_CHARSET);//编码字符集
        param.put("notify_url", SinaPayConstants.NOTIFY_URL);//异步通知请求地址
        param.put("return_url", SinaPayConstants.RETURN_URL);//同步通知请求地址
        param.put("out_pay_no", order_id);//本应用的订单ID
        Tools.removeBlankValue(param);//去除空数据
        Tools.mapToBeEncrypt(param);//对需要加密的对象加密
        param.put("sign", getSign(param));//签名
        param.put("sign_type", SinaPayConstants.SIGN_TYPE);//签名类型
        String queryParam = Tools.createLinkString(param, true);
        String result = URLDecoder.decode(CallServiceUtil.sendPost(SinaPayConstants.ORDER_GATE_URL, queryParam), SinaPayConstants.INPUT_CHARSET);
        Map<String, String> resultMap = GsonUtil.fronJson2Map(result);
        queryPayStatusResultEntity.setResultString(result);
        if (!validateSign(resultMap)) {
            //发起支付返回签名不正确
            queryPayStatusResultEntity.setSuccess(false);
            queryPayStatusResultEntity.setDesc("返回数据签名不正确...");
            return queryPayStatusResultEntity;
        }
        //判断返回码
        if (!SinaPayConstants.SUCCESS_RESPONSE_CODE.equals(resultMap.get("response_code"))) {
            //发起支付结果查询失败
            queryPayStatusResultEntity.setSuccess(false);
            queryPayStatusResultEntity.setDesc(resultMap.get("response_message"));
            return queryPayStatusResultEntity;
        }
        if (!SinaPayConstants.PAY_SUCCESS_CODE.equals(resultMap.get("pay_status"))) {
            //支付查询状态并不是成功
            queryPayStatusResultEntity.setSuccess(false);
            queryPayStatusResultEntity.setDesc(resultMap.get("response_message"));
            return queryPayStatusResultEntity;
        }
        //支付成功
        queryPayStatusResultEntity.setSuccess(true);
        queryPayStatusResultEntity.setDesc(resultMap.get("response_message"));
        return queryPayStatusResultEntity;
    }

    /**
     * 验证签名
     * @param param 将传入的Json参数转换成Map对象即可
     * @return
     * @throws Exception
     */
    public static boolean validateSign(Map<String, String> param) throws Exception {
        String signKey = null;
        if ("RSA".equalsIgnoreCase(param.get("sign_type").toString())) {
            //验签公钥
            signKey = Tools.getKey("rsa_sign_public.pem");
        }
        String sign_result = param.get("sign").toString();
        String sign_type_result = param.get("sign_type").toString();
        String _input_charset_result = param.get("_input_charset").toString();
        param.remove("sign");
        param.remove("sign_type");
        param.remove("sign_version");
        Tools.removeBlankValue(param);//去除空数据
        String like_result = Tools.trimInnerSpaceStr(Tools.createLinkString(param, false));
        return SignUtil.Check_sign(like_result.toString(), sign_result, sign_type_result, signKey, _input_charset_result);
    }

    /**
     * 获取签名字符串
     * @param param
     * @return
     * @throws Exception
     */
    private static String getSign(Map<String, String> param) throws Exception {
        //签名
        String content = Tools.trimInnerSpaceStr(Tools.createLinkString(param, false));
        //加签密钥
        String signKey = Tools.getKey("rsa_sign_private.pem");

        //计算签名
        return SignUtil.sign(content, SinaPayConstants.SIGN_TYPE, signKey, SinaPayConstants.INPUT_CHARSET);
    }

    //新浪支付--代收
    public static String sendSinaPay(String amount, String ip, String order) throws Exception {
        HostingCollectTradeEntity createPayEntity = new HostingCollectTradeEntity();
        createPayEntity.setAmount(amount);
        createPayEntity.setPayer_ip(ip);
        createPayEntity.setPayer_id(SinaPayConstants.PAYER_ID);
        createPayEntity.setOrder_id(order);
        createPayEntity.setSummary(SinaPayConstants.PAYDEMO);
        HostingCollectTradeResultEntity hostingCollectTradeResultEntity = SinaPayUtil.CreatePay(createPayEntity);
        System.out.println(hostingCollectTradeResultEntity.getResultString());
        System.out.println(hostingCollectTradeResultEntity.isSuccess());
        System.out.println(hostingCollectTradeResultEntity.getRedirect_url());
        return hostingCollectTradeResultEntity.getRedirect_url();
    }

    //测试类--查询订单支付状态
    public static void main(String[] args) throws Exception {
        String order_id = "201709062054488927748";
        QueryPayStatusResultEntity res = SinaPayUtil.queryPayStatus(order_id);
        System.out.println(res.toString());
    }

    /**
     * 测试类
     * @param args
     * @throws Exception
     */
    /* public static void main(String[] args) throws Exception {
     * HostingCollectTradeEntity createPayEntity=new HostingCollectTradeEntity();
     * createPayEntity.setAmount("1");
     * createPayEntity.setPayer_ip("192.168.1.4");
     * createPayEntity.setPayer_id("20170905205108");
     * createPayEntity.setOrder_id(Tools.getCurrentTimeString());
     * createPayEntity.setSummary("房贷还款");
     * HostingCollectTradeResultEntity hostingCollectTradeResultEntity=CreatePay(createPayEntity);
     * System.out.println(hostingCollectTradeResultEntity.getResultString());
     * System.out.println(hostingCollectTradeResultEntity.isSuccess());
     * System.out.println(hostingCollectTradeResultEntity.getRedirect_url());
     * } */

}
