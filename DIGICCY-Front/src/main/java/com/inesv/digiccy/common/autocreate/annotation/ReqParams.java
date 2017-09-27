/**
 * @className ReqParamsConstants.java
 * @date 2014-8-1下午5:48:04
 *
 * Copyright merchant  2014
 * All right reserved.
 */
package com.inesv.digiccy.common.autocreate.annotation;

/**
 * @description: 请求参数
 * @author hxc
 * @date 2014-8-1 下午5:48:04
 */
public class ReqParams
{
    /**
     * 接口方法，如果没有标注reqParams的值，代表请求参数是所有。
     */
    public static final String DEFAULT = "ALL";
    
    /**
     * 如果接口没有请求参数，使用此标记位
     */
    public static final String NONE = "NONE";
    
}
