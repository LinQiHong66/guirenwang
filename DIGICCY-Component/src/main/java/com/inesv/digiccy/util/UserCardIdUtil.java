package com.inesv.digiccy.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.inesv.digiccy.api.utils.PropertiesUtil;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class UserCardIdUtil {

    private static final String key             = PropertiesUtil.getPropertiesValue("componentKey.properties", "chinadatapayKey");

    private static final String chinadatapayUrl = PropertiesUtil.getPropertiesValue("componentKey.properties", "chinadatapayUrl");


    /**
     * 
     * @param name 用户名
     * @param idcard 用户身份证好
     * @return map结果示例如下
     *         {"code":"10000","message":"成功","data":{"result":"1"},"seqNo":"LD76907Y1708181641"}
     *         名称 错误码 说明
     *         code 10001 报文错误(包含MAC验证错误)
     *         code 10002 系统错误
     *         code 10000 成功
     *         code 10005 系统超时
     *         code 10006 系统繁忙
     *         code 10003 系统异常
     *         code 10004 尚未开通此业务
     *         code 10007 非法地址
     *         result 1 验证一致
     *         result 2 验证不一致
     *         result 3 异常情况
     */
    public static Map<String, Object> validateCardId(String name, String idcard) {
        HttpRequest request = HttpRequest.post(chinadatapayUrl);
        request.form("key", key);
        request.form("name", name);
        request.form("idcard", idcard);
        HttpResponse response = request.send();
        String respJson = response.bodyText();
        Map<String, Object> respMap = JSON.parseObject(respJson, Map.class);
        return respMap;
    }
}
