package com.inesv.digiccy.common.autocreate.bean;

/**
 * 
 * @description: 接口开发进度
 * @author hxc 
 * @date 2014-8-14 下午2:28:24
 */
public enum ProgressType
{
    /**
     * 接口已定义好
     */
    DEFINITION("已定义好"),
    
    /**
     * 接口正在开发中
     */
    DEVELOPING("开发中"),
    
    /**
     * 接口正在测试
     */
    TESTING("测试中"),
    
    FINISHED("已完成");
    
    private final String desc;
    
    ProgressType(String desc)
    {
        this.desc = desc;
    }
    
    public String getDesc()
    {
        return desc;
    }
}
