package com.inesv.digiccy.common.dto;

import java.io.Serializable;

import com.inesv.digiccy.common.autocreate.annotation.AutoDocField;
import com.inesv.digiccy.common.constants.MobResponseCode;



/**
 * 
 * @title 思埠快购系统 - 基础应答消息
 * 
 * @Description 如果没有特殊返回的字段,此类也可直接使用
 * 
 * @author hxc
 * 
 * @date 2013-2-26 上午10:10:37
 */
public class BaseRes implements Serializable
{
    private static final long serialVersionUID = 1L;
     
    

    /**
     * 处理结果代码<br>
     * 0 - 成功
     */
    @AutoDocField(note = "处理结果代码")
    private String code = MobResponseCode.SUCCESS;
    
    /**
     * 结果描述<br>
     */
    @AutoDocField(note = "结果描述")
    private String desc = MobResponseCode.SUCCESS_DESC;
    
    
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getDesc()
    {
        return desc;
    }
    
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    
    
    @Override
    public String toString()
    {
        
        StringBuffer sb = new StringBuffer(50);
        sb.append("code: " + code).append("\n");
        sb.append("desc: " + desc).append("\n");        
        return sb.toString();
    }
    

    
 
    
}
