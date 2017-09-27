package com.inesv.digiccy.common.autocreate.bean;

/**
 * @author：hxc
 * @createtime ： 2015年5月16日 上午1:56:52
 * @description 请求方式
 * @since version 初始于版本  
 */
public enum RequestModeType
{
    
    GET("GET"),
    
    POST("POST"),
    
    DEFAULT("POST");
    
    private final String des;
    
    public String getDes()
    {
        return des;
    }
    
    RequestModeType(String des)
    {
        this.des = des;
    }
}
