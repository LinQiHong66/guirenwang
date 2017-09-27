/**
 * 
 */
package com.inesv.digiccy.common.inout;

import org.apache.commons.lang.StringUtils;

import com.inesv.digiccy.common.constants.MobResponseCode;

/**
 * 业务层返回给控制层的基类。实现控制层与业务层解耦。
 * 
 * @Description: 业务层处理业务时，向Action控制层返回应答信息
 * @author Mico
 * @date 2013-3-14 上午09:33:38
 */
public class CommonOut
{
    
    /**
     * 应答码
     */
    private String code = MobResponseCode.SUCCESS;
    
    /**
     * 应答描述
     */
    private String desc = MobResponseCode.SUCCESS_DESC;
    
    /**
     * 手机端的应答信息，默认是desc的值
     * 
     * 为了扩展和网页版应答描述不一致的时候
     */
    private String mobDesc;
    
    public boolean isSuccess()
    {
        if(code.equals(MobResponseCode.SUCCESS))
        {
            return true;
        }
        return false;
    }
    
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
    
    public void setMobDesc(String mobDesc)
    {
        this.mobDesc = mobDesc;
    }
    
    public String getMobDesc()
    {
        if(StringUtils.isBlank(mobDesc))
        {
            return desc;
        }
        
        return mobDesc;
    }
    
    @Override
    public String toString()
    {
        return "CommonOut [code=" + code + ", desc=" + desc + ", mobDesc=" + mobDesc + "]";
    }
    
}
